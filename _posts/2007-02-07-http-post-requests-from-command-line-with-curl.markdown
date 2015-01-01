---
layout: post
status: publish
published: true
title: HTTP POST requests from command line with curl
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
wordpress_id: 182
wordpress_url: http://emcken.dk/wp/archives/182-http-post-requests-from-command-line-with-curl.html
date: '2007-02-07 14:54:38 +0100'
date_gmt: '2007-02-07 14:54:38 +0100'
categories:
- Work
tags: []
comments: false
---
One of our customers have a JBoss application which they wanted to monitor with a script (in the long run Heartbeat2).
By making a specific HTTP POST request to which the answer is known, it is possible to check if the server is running as expected. The HTTP POST request consist of a header and a body. The header is automaticaly generate from the parameters you provide `curl` and the body is provided in the `--data` parameter.

The following is an example close to what I used, and beneath a description of the parameters used parameters:

    curl --insecure \
         --user monitor_user:heykcnhre \
         --header extra-header:12345678 \
         --include \
         --data '

        7
        get_email
    ' https://localhost:5011/check

*   `--insecure` ignores unverified SSL certificates
*   `--user ` authentication information need to access the server and make the http request in the first place.
*   `--header ` provides extra header information. You can add as many of theese as you need.
*   `--include` includes the header in the response (not only the body)

Curl takes many different parameters use `man curl` for more info.

