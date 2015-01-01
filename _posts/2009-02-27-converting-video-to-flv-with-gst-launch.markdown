---
layout: post
status: publish
published: true
title: converting video to flv with gst-launch
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
wordpress_id: 248
wordpress_url: http://emcken.dk/weblog/?p=248
date: '2009-02-27 11:12:04 +0100'
date_gmt: '2009-02-27 10:12:04 +0100'
categories:
- Uncategorized
tags:
- gstreamer
comments: false
---
I had quite some trouble finding a way to convert an avi video file to flv.
I really wanted to use gstreamer since it had worked very well for me for other conversions in the past. The reason for this conversion was that I had a video on my digital Canon camera (in avi format) and I wanted to publish it on the web using the defacto standard... flash video (flv).

After lots of googling I found [FLV Conversion Tips][1] by Brendan Howell so I'm not to take credit for this.

I didn't want the sound so I removed it from Brendans original example:

    gst-launch -v filesrc location=canon.avi ! decodebin name=d  ffmux_flv name=mux ! filesink location=output.flv d. ! queue ! videoscale !  video/x-raw-yuv,width=320,height=256 ! ffenc_flv ! mux.

I my search for a solution I found [another link with some gst-launch examples][2] you might find interesting.

[1]: http://blogs.btk-fh.de/webdev/?p=10
[2]: http://news.nopcode.org/miau/wk/GStreamer

