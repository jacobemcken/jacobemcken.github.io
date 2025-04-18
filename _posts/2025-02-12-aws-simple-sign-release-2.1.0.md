---
layout: post
title: Release 2.1.0 of Clojure lib for AWS presigned URLs & requests
image: /assets/img/woman_reaching_for_book.webp
image_alt: "Midjourney prompt: Woman reaching up with one hand and putting a closed book on the top shelf. High angle shot. Midjourney edits, lots of them, trying to get the other arm to not reach and stay by her side instead."
categories:
- Programming
tags:
- aws-simple-sign
- Clojure
- AWS
- Cloud
comments: true
support: true
---

With a little help, [aws-simple-sign now supports PUT URLs][1]
and I have released a new version (`2.1.0`) of the library.

I noticed a few issues with some of the examples in the README
which are now also fixed.

For those unfamiliar with [`aws-simple-sign`][2],
it generates presigned URLs for S3 objects
and signs HTTP requests for AWS.
It doesn't require any dependencies (Java or otherwise)
which is pretty handy for using it with Babashka.

Enjoy 🚀

[1]: https://github.com/jacobemcken/aws-simple-sign/pull/14
[2]: https://github.com/jacobemcken/aws-simple-sign