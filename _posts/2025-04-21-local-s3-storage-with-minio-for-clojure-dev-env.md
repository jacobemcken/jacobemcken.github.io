---
layout: post
title: "Local S3 storage with MinIO for your Clojure dev environment"
description: "Simple Storage Service or S3 Cloud Object Storage is versatile and easy to setup and use in a local Clojure development environment with Docker using Minio."
image: /assets/img/cloud_silo.webp
image_alt: "Midjourney prompt: A single huge digital storage bucket in the clouds using mainly green and orange colors. Image style reference using featured image from blog post: Upload files to SharePoint using Babashka"
categories:
- Programming
tags:
- Clojure
- S3
- AWS
comments: true
support: true
excerpt_separator: <!--more-->
---

Simple Storage Service or
<abbr title="Simple Storage Service">S3</abbr> Cloud Object Storage
is a versatile and cheap storage.
I've used it for invoices, contracts, media, and configuration snapshots,
among other things.
It’s a perfect fit when the object key
(or what many might think of as a filename or path)
can be used as a unique key to retrieve what you need
— and doing it from Clojure is no exception.
<!--more-->

💡 This post assumes you have the following tools installed:
Docker, Docker Compose, `aws` CLI tool and Clojure ofcourse.

S3 is best for write-once-read-many use cases,
and in a well-designed application architecture,
you can often derive an object key (path) from already available data,
like a customer number.

Object key (path) examples:
```
customer-58461/invoice-1467.pdf
marketing-campaign-14/revision-3.template
```

Though S3 was originally an Amazon product introduced in 2006,
many cloud providers now offer fully compatible services.
S3 is cheaper than traditional block storage
(also known as volume storage or disk storage),
and while it is also slower, it scales extremely well,
even across regions (like EU and US).

I probably wouldn’t be so enthusiastic about S3
if it weren’t so easy to use in a local development environment.
That’s where [MinIO, running in a Docker container][1], comes in.

I usually commit a `docker-compose.yml` file to the repo
alongside any code that requires S3-compatible object storage:

```yml
services:
  s3:
    image: minio/minio
    ports:
      - "9000:9000"     # S3 API service endpoint
      - "9001:9001"     # Web interface
    volumes:
      - './data/minio:/data' # Persist data; path must match command 👇
    command: server /data --console-address ":9001"
    environment:
      MINIO_DOMAIN: localhost:9000 # Requried for virtual-host bucket lookups
      MINIO_ROOT_USER: AKIAIOSFODNN7EXAMPLE
      MINIO_ROOT_PASSWORD: wJalrXUtnFEMI/K7MDENG/bPxRfiCYEXAMPLEKEY
```

Amazon S3 typically runs on the default HTTPS port (`443`),
while MinIO defaults to port `9000`.
The web interface on port `9001` is excellent for browsing bucket content
where using code or CLI is "too much".
Storing data on a volume outside the container
makes it easy to update MinIO without losing data
since “updating” usually means stopping and deleting the old container
and then starting a new container.

A new version of the Docker image can be pulled with:

    docker pull minio/minio

Setting the `MINIO_DOMAIN` environment variable is required
to support "virtual host"-style presigned URLs (more on that later).
Root user credentials
are configured using `MINIO_ROOT_*` environment variables,
and these credentials work for both logging into the web interface and generating presigned URLs.

Before starting the service,
it's possible to pre-create buckets
by creating local folders inside the data volume:

    mkdir -p data/minio/mybucket1

**⚠️ Warning:** Buckets created this way will be [missing metadata][2],
which can cause issues when listing ALL buckets.

Start the S3-compatible storage (MinIO) with:

    docker compose up s3  # (Press CTRL+C to stop)

For using older versions of Docker Compose
it can look like: `docker-compose up s3` (notice the dash).

An AWS profile is handy when using the AWS CLI, among other things.
The following will create a profile named `minio`:

Add these lines to `$HOME/.aws/credentials`:

```ini
[minio]
aws_access_key_id = AKIAIOSFODNN7EXAMPLE
aws_secret_access_key = wJalrXUtnFEMI/K7MDENG/bPxRfiCYEXAMPLEKEY
```

And these to `$HOME/.aws/config`:
```ini
[profile minio]
region = us-east-1
endpoint_url = http://localhost:9000
```

**Notice:** MinIO's default region is `us-east-1`,
and the endpoint URL matches the configuration in `docker-compose.yml`.

Make sure that everything is configured correctly by checking read access:

    aws s3 ls mybucket1 --profile minio

This should return a non-error but empty result.

Now, add a file to the bucket:

    echo "Hello, World!" > hello.txt
    aws s3 cp hello.txt s3://mybucket1/ --profile minio

... then verify the new content:

    aws s3 ls mybucket1 --profile minio

Lo and behold — the bucket now has content.

Alternatively, set the environment variable `AWS_PROFILE`
(using `export AWS_PROFILE=minio`)
to avoid repeating `--profile minio` in each command.

Having a local S3 bucket, let's start interacting with it from Clojure.

The S3 clients from [Cognitect AWS API][3] and [Aw Yeah][4] (AWS API for Babashka),
implicitly resolve credentials the same way the Amazon Java SDK and the `aws` CLI tool do.

By leveraging the `AWS_PROFILE` environment variable,
both the Clojure code and the `aws` CLI tool will use the exact same configuration.
This makes any credential-related issues easier to reproduce outside of your code.
In theory, this also helps avoid the need for code along the lines
`(if production? ...` and `(if dev? ...`
— which is usually a sign of poor software design.

There are many ways to manage environment variables,
and each IDE handles them differently.
To check if the REPL has picked up the expected environment configuration,
use the following:

```clojure
(System/getenv "AWS_PROFILE") ; Should return "minio"
```

In other contexts, I'd recommend avoiding implicit configuration,
which using `AWS_PROFILE` is.
However, in this case, it aligns with how AWS tooling is typically set up,
making it intuitive for anyone already familiar with AWS.
Plus, a wealth of resources (official docs, StackOverflow, etc.)
rely on this convention.

To try out some code with the MinIO setup,
start a REPL with `AWS_PROFILE` set
and add the following dependencies to your `deps.edn`:

```clojure
{:deps {com.cognitect.aws/api       {:mvn/version "0.8.741"}
        com.cognitect.aws/endpoints {:mvn/version "871.2.30.22"}
        com.cognitect.aws/s3        {:mvn/version "871.2.30.22"}
        dk.emcken/aws-simple-sign   {:mvn/version "2.1.0"}}}
```

To set up an S3 client:

```clojure
(require '[cognitect.aws.client.api :as aws])

(def s3
  (aws/client {:api :s3 :endpoint-override {:protocol :http :hostname "localhost" :port 9000}}))
```

... and interact with the S3 bucket:

```clojure
(aws/invoke s3 {:op :ListBuckets})
(aws/invoke s3 {:op :ListObjectsV2 :request {:Bucket "mybucket1"}})
```

Unfortunately, neither Cognitect’s `aws-api` nor `awyeah-api`,
respect profiles or environment variables specifying an alternative endpoint,
which is required when working with MinIO.

It forces the application to conditionally override the endpoint,
which kinda ends up looking like `(if dev? ...` code 😭

[I've reported the issue on GitHub][5].
But until it's fixed,
I found the following generic code to be an acceptable compromise
for working with MinIO locally:

```clojure
(import '[java.net URL])

(defn url->map
  "Convenience function for providing a single connection URL over multiple
   individual properties."
  [^URL url]
  (let [port (.getPort url)
        path (not-empty (.getPath url))]
    (cond-> {:protocol (keyword (.getProtocol url))
             :hostname (.getHost url)}
      (not= -1 port)
      (assoc :port port)

      path
      (assoc :path path))))

(defn create-s3-client
  "Takes an endpoint-url (or nil if using default) and returns a S3 client."
  [endpoint-url]
  (let [url (some-> endpoint-url not-empty (URL.))]
    (cond-> {:api :s3}
      url (assoc :endpoint-override (url->map url))
      :always (aws/client))))

(def s3 ; local environment (using MinIO)
  (create-s3-client "http://localhost:9000"))

(def s3 ; production environment (using Amazon S3)
  (create-s3-client nil))
```

Now, replace the hardcoded local URL with an environment variable
that is only set in a local development environment.
The URL in the environment variable must match the value of the AWS profile setup.

```clojure
; export MY_APP_S3_URL=http://locahost:9000
; only set MY_APP_S3_URL locally
(def s3
  (create-s3-client (System/getenv "MY_APP_S3_URL")))
```

The post could have ended here, but often it is beneficial
to provide presigned URLs for the content in an S3 bucket.
Luckily, [`aws-simple-sign` presign URLs][6]
and work seamlessly with both previously mentioned S3 clients:

```clojure
(require '[aws-simple-sign.core :as aws-sign])

; Using the already configured client from above:
(aws-sign/generate-presigned-url s3 "mybucket1" "hello.txt" {})
; Open URL in your browser and see "Hello, World!"
```

"Virtual hosted-style" presigned URLs only works,
because the `MINIO_DOMAIN` environment variable is configured in `docker-compose.yml`.
Alternatively, use path-style URLs.
Checkout [Amazons official documentation on the different styles][7],
if you need a refresher.

This illustrates just how simple working with S3 from Clojure is.
All the way from code iterations in the local development environment
to transitioning the application into production.

Best of all, no AWS Java SDK is required. 🚀

[1]: https://github.com/minio/minio/blob/master/docs/docker/README.md
[2]: https://github.com/aws/aws-cli/issues/8339
[3]: https://github.com/cognitect-labs/aws-api
[4]: https://github.com/grzm/awyeah-api
[5]: https://github.com/cognitect-labs/aws-api/issues/269
[6]: https://github.com/jacobemcken/aws-simple-sign
[7]: https://docs.aws.amazon.com/AmazonS3/latest/userguide/VirtualHosting.html
