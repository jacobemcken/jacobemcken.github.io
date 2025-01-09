---
layout: post
title: "New release of Clojure lib for AWS presigned URLs & requests"
description: "Promoted the pre-release of the Clojure library aws-simple-sign after six months. Lots of goodies in there since the last stable release."
image: /assets/img/zeppelin_above_clouds.png
image_alt: "Midjourney prompt: Just above the clouds in the sky is a white zeppelin with a huge red wax seal on the middle of the side. The seal is the Amazon logo. Golding hour and a clear blue sky. Illustrated using simple vectors."
categories:
- Programming
tags:
- Clojure
- AWS
- Cloud
comments: true
excerpt_separator: <!--more-->
---

[The Clojure library `aws-simple-sign`][1] was in pre-release for more than six months.
With a modest [download count of just above 2,000][5] and no reported issues,
I finally found the time to promote the `2.0.0-alpha1` release of to a stable version.

For those unfamiliar with `aws-simple-sign`,
it generates presigned URLs for S3 objects
and signs HTTP requests for AWS.
<!--more-->

The library is intended for those who for whatever reason
want to avoid the `com.amazonaws/aws-java-sdk-s3` Java dependency,
or simply cannot use it (e.g., in Babashka).

A summary of changes in version 2.0.0
(since the previous stable release 1.6):

- **No dependencies** (no forced ones anyway).
- **Improved [documentation][4]**.
- **Credentials resolution outsourced**. You can do it manually,
  but most likely you want to use a *"client"* from either
  [`awyeah-api`][2] or [Cognitect `aws-api`][3].
- **"Container credential provider" supported** (indirectly).
  Use one of the external *"clients"*
  when used in an environment that rotates credentials like:
  Amazon <abbr title="Elastic Container Service">ECS</abbr>
  or Amazon <abbr title="Elastic Kubernetes Service">EKS</abbr>.
- **Minor API breakage** 🫣 but for a more sane function signature
  with these new *"clients"*.
- **Change license** to MIT, and actually included the license now.

If you are using `2.0.0-alpha1`, you can just update without breakage.

Enjoy 🚀

[1]: https://github.com/jacobemcken/aws-simple-sign
[2]: https://github.com/grzm/awyeah-api
[3]: https://github.com/cognitect-labs/aws-api
[4]: https://cljdoc.org/d/dk.emcken/aws-simple-sign/2.0.0/api/aws-simple-sign.core
[5]: https://clojars.org/dk.emcken/aws-simple-sign/versions/2.0.0-alpha1