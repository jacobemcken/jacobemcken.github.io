---
layout: post
title: S3 presigned URL generation with Babashka
image: /assets/img/steampunk_rabbit_signing_documents_in_cloud.png
image_alt: "Midjourney prompt: A cute steampunk rabbit with googles holding a worn feather pen in its paw signing old documents in colourful cloud surroundings. The image is in water color style --ar 2:1 --v 6.0 - Upscaled (Creative)"
description: Generating presigned URLs for S3 in AWS using Babashka (Clojure scripting) wasn't straightforward - but it is now 😉
categories:
- Programming
tags:
- Clojure
- Babashka
- Cloud
- aws
- s3
comments: true
excerpt_separator: <!--more-->
---

A while back, I needed to generate presigned URLs for S3 objects in Amazon Web Services.
I wanted to use [Babashka][2] (Clojure scripting), to avoid my painful friend from the past - Bash.

I looked all the usual places for a Clojure-friendly approach,
but even [Cognitect's AWS API did not have any means to presign URLs][1].
Everybody seemed to reluctantly tolerate having to use AWS Java SDK directly for presigning URLs.

— Java interop, oh joy 😣😅

Being forced to use AWS Java SDK would mean a no-go for Babashka.
Also, based on how often similar questions pop up, I felt it deserved a better solution.

<!--more-->

Let's imagine for a moment that the AWS Java SDK would work just fine with Babashka,
the dependency complexity still matters.

I am not using any scientific way of measuring it, I just follow my intuition.
I notice when dependencies take up many <abbr title="Megabyte">MB</abbr>,
when they themselves have many dependencies,
or when they contain lots of unneeded code (which potentially could contain security issues).

The `com.amazonaws/aws-java-sdk-s3` dependency contains several hundred Java classes,
of which - I assume - only a few would be needed to sign URLs.
The dependency would also increase the total dependency size by around 7MB.
At least to me, that is disproportionate compared to the less than 250 lines of Clojure code,
that made up the initial take on solving URL signing.

<!--
com.amazonaws/aws-java-sdk-s3 {:mvn/version "1.12.655"}
4817605 4,6M vs. 12034074 12M
-->

6 months ago I released the code in a [reusable library named `aws-simple-sign`][3],
stitched together from code snippets in GitHub issues, Gists, blog posts, mailing lists
and long hopeless stares at the screen.

Since then, I have made a few small improvements,
like support for signing HTTP requests
and opt-in support for the legacy "path style" URLs to name some.
But I never got around to implementing support for [container credential providers][4].

A couple of days ago, I found myself needing exactly this,
and suddenly I got the idea that leveraging Cognitects AWS API client
would give me the functionality "for free".

Blinded by "I need it now",
the ego-stroking notion of having a good idea,
and only requiring the functionality in the JVM,
I failed to ask the most important question:<br/>
*Will it work with Babashka?*

Surprise... it doesn't, but alas, the Clojure community heroes came to the rescue.
It turns out that I am not the only person who finds it important
to have good tooling available in Babashka.

I was kindly pointed in the direction of [awyeah-api][5] (aka. `aws-api` for Babashka),
which creates a client that is compatible with Cognitect's AWS API client.<br/>
— Thank you ❤️

By outsourcing "providing credentials and config" using an external client,
I simplified the code by having less responsibility
(still less than 250 lines of code with more features).
Also, these clients are battle-tested (or at least "better tested"),
compared to my old sad excuse for a "client implementation".
On top, the external clients are faster because they cache credentials and config,
avoiding re-reading files from disk and environment variables.

But *"How does using the library look?"* you ask.

```clojure
(require '[com.grzm.awyeah.client.api :as aws]
         '[aws-simple-sign.core :as aws-sign])

(def client
  (aws/client {:api :s3}))

(aws-sign/generate-presigned-url client "somebucket" "someobject.txt" {})
; "https://somebucket.s3.us-east-1.amazonaws.com/someobject.txt?X-Amz-Security-Token=FwoG..."
```

It has been tested both using an AWS S3 bucket
and locally using [MinIO][6], a Cloud storage server compatible with Amazon S3.
[MinIO has an excellent Docker image][7], perfect for testing S3 code in a local setup.

```clojure
(def client
  (aws/client {:api :s3
               :endpoint-override {:protocol :http
                                   :hostname "localhost"
                                   :port 9000}}))

(aws-sign/generate-presigned-url client "somebucket" "someobject.txt"
                                 {:path-style true})
; http://localhost:9000/somebucket/someobject.txt?X-Amz-Algorithm=AWS4-HMAC-SHA256&..."
```

According to ["Use endpoints in the AWS CLI" documentation][8], endpoints can be provided by
either using the environment variables `AWS_ENDPOINT_URL` and `AWS_ENDPOINT_URL_S3`
or using the `endpoint_url` setting within a profile
**- BUT neither client libraries pick up on that.** 🤷

For now use `:endpoint-override` as shown above.

I would love to get some feedback from people using this on non-Amazon clouds
like Google, Azure and Digital Ocean among others.
There are lots of things I still don't understand
about all the different scenarios in which the S3 technology is used.

On a final note, support for upload URLs is still on the TODO list,
but I suspect (hope?) it wouldn't be too hard to crack.

I really hope this will be useful for others than me.

[1]: https://github.com/cognitect-labs/aws-api/issues/5
[2]: https://github.com/babashka/babashka
[3]: https://github.com/jacobemcken/aws-simple-sign
[4]: https://docs.aws.amazon.com/sdkref/latest/guide/feature-container-credentials.html
[5]: https://github.com/grzm/awyeah-api/
[6]: https://min.io/
[7]: https://hub.docker.com/r/minio/minio
[8]: https://docs.aws.amazon.com/cli/latest/userguide/cli-configure-endpoints.html
