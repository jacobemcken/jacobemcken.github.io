---
layout: post
title: Upload files to SharePoint using Babashka
image: /assets/img/pipeline_putting_things_in_the_cloud.png
image_alt: "Midjourney prompt: A long factory pipeline with conveyor bands and pipes automatically delivers into the sky/clouds using a single pipe. The image is using a simple futuristic cartoon style, mainly using green and orange colors."
categories:
- Programming
tags:
- Clojure
- Babashka
- SharePoint
- DevOps
comments: true
excerpt_separator: <!--more-->
---

My team and I were publishing some files several times weekly
and we were obligated to put them on Microsoft SharePoint for non-technical people to be able to find them.
After all, it is fair to not assume everyone knows their way around GitHub
or how to run small pieces of code.

The tedious file upload to SharePoint was done manually until recently
when [Babashka][] came to the rescue and helped leverage our CI/CD pipeline.

<!--more-->

**tl;dr** [Link to a Babashka script that can upload files to SharePoint][3]
— you need to fill out your authentication information.

## Getting an app-only principal

Since it was a CI/CD pipeline that was supposed to do all the work,
we wanted to [be granted access using SharePoint App-Only][1].
Being in a huge organization, the people handling such things are "far away",
and they are required to follow a process with a lot of bureaucratic hoops.

I am not sure exactly how all the specifics were done,
but I am told we got an app-only principal with the following setup:

```xml
<AppPermissionRequests AllowAppOnlyPolicy="true">
  <AppPermissionRequest Scope="http://sharepoint/content/sitecollection/web" Right="FullControl"/>
</AppPermissionRequests>
```

Though I don't think it matters for this use case,
`App Domain` and `Redirect URI` were using the following settings:

```
App Domain: www.localhost.com
Redirect URI: https://www.localhost.com
```


## Acquiring an access token

Looking at the SharePoint API documentation it seemed fairly straightforward,
but acquiring an access token that the SharePoint API would accept,
proved to be the hardest part.

Documentation and guides both official and community
suggested acquiring access tokens from `login.microsoftonline.com` in one way or another.
But I kept getting rejected by the SharePoint API regardless,
with cryptic error messages that didn't help me understand the issue.

I was tempted to give up on several occasions,
but I also dreaded the thought of maintaining code,
that would be unnecessarily long and complex.
I got a taste when a colleague showed me some Python code using the [Office365-REST-Python-Client library][4].
The Python code was using A LOT more lines of code — even with the library —
than I suspected would be necessary in Clojure without a library.

After several weeks on and off, whenever I could find a bit of time in between my normal work,
I stumbled over the blog post [How To Perform A SharePoint App-Only Authentication In Power Automate][2],
which was acquiring an access token from `accounts.accesscontrol.windows.net`:

```clojure
(def sharepoint-principal-id
  "00000003-0000-0ff1-ce00-000000000000")

(defn get-token
  [{:keys [client-id client-secret tenant-id tenant-name]}]
  (let [params {"client_id" (str client-id "@" tenant-id )
                "client_secret" client-secret
                "grant_type" "client_credentials"
                "resource" (str sharepoint-principal-id "/" tenant-name ".sharepoint.com@" tenant-id) }]
    (-> (http/post (str "https://accounts.accesscontrol.windows.net/" tenant-id "/tokens/OAuth/2")
                   {:form-params params})
        :body
        (json/decode true))))

(comment
  ;; replace client and tenant dummy information with your own
  (def access-token
    (-> (get-token {:client-id "71e0b131-84a1-40ed-a9f1-366fbecfd05f"
                    :client-secret "NDMzODQ3NWQtZTh/Tk0YzYtOGEzZjI3+MDc+2NWM3Cg="
                    :tenant-id "contoso.com"
                    :tenant-name "contoso"})
        :access_token))
)
```

... and just like that, we were back in business 😅


## Interacting with SharePoint REST API

The rest fell into place easily, with the [SharePoint API documentation][5] in hand,
and file was quickly uploaded.

```clojure
(defn percent-encode
  [s]
  (-> s
      (URLEncoder/encode)
      (str/replace "+" "%20")))

(defn upload-file
  [access-token endpoint {:keys [input-stream name destination] :as _file-info} opts]
  (let [mime-type (or (:mime-type opts)
                      (not-empty (URLConnection/guessContentTypeFromName name)))]
    (when-not mime-type
      (throw (ex-info "Unable to automatically determine mime-type" {:file-name name})))

    (println "Uploading" (str destination "/" name))
    (http/post (str endpoint "/_api/web"
                    "/GetFolderByServerRelativeUrl('" (percent-encode destination) "')"
                    "/Files/add(url='" (percent-encode name) "',overwrite=true)")
               {:headers {"Authorization" (str "Bearer " access-token)
                          "Content-Type" mime-type}
                :body input-stream})))

(comment
  (def endpoint
    (let [tenant-name "contoso"
          site "somesite"
      (str "https://" tenant-name ".sharepoint.com/sites/" site))

  (upload-file access-token endpoint "foldere that exists"
               {:input-stream (io/file "test-file.txt")
                :mime-type "text/plain" ; optional - should be auto detected for most filetypes
                :name "super-file.txt"})
)
```

**Notice:** `percent-encode` which is necessary when dealing with file and folder names containing space.

Also, it didn't take long before I found out that you cannot upload a file to a folder that doesn't exist.
Luckily, the API can also be used to create such folders.

```clojure
(defn create-folder
  [access-token endpoint folder-name]
  (http/post (str endpoint "/_api/web/Folders/add('" (percent-encode folder-name) "')" )
             {:version :http1.1 ; SharePoint API gets confused about empty body when using HTTP v. 2
              :headers {"Authorization" (str "Bearer " access-token)}}))
```

**Notice:** `:version :http1.1` which is required to work around a bug
(presumably in the SharePoint API).
Anyway the combination of Java HTTP client used by Babashka and HTTP2 protocol,
causes SharePoint to complain about missing `content-type` header (for an empty body).

I have put the [Babashka script for uploading files to SharePoint in a GitHub Gist][3],
which includes a nice helper function that takes a local folder
and uploads all its contents to SharePoint (including nested folders and files).

The script only takes up 111 lines of code, which are all simple and easy to read
(maybe with the exception of `upload-info-keep-relative-path`).

Damn, Clojure ROCKS 🚀

[Babashka]: https://github.com/babashka/babashka
[1]: https://learn.microsoft.com/en-us/sharepoint/dev/solution-guidance/security-apponly-azureacs
[2]: https://www.c-sharpcorner.com/article/how-to-perform-sharepoint-app-only-authentication-in-power-automate/
[3]: https://gist.github.com/jacobemcken/6846e4c92b27960529a7794367784cd8
[4]: https://github.com/vgrem/Office365-REST-Python-Client
[5]: https://learn.microsoft.com/en-us/sharepoint/dev/sp-add-ins/working-with-folders-and-files-with-rest
