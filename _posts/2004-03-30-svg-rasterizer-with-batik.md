---
layout: post
status: publish
published: true
title: SVG rasterizer with Batik
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
wordpress_id: 25
wordpress_url: http://emcken.dk/wp/archives/25-svg-rasterizer-with-batik.html
date: '2004-03-30 15:25:52 +0200'
date_gmt: '2004-03-30 15:25:52 +0200'
categories:
- Graphics
tags: []
comments: false
---
At work I sit at a fairly slow machine so it takes forever to transform svg files to high resolution bitmap pictures. Also Java has a default upper memory limit which is quickly excited when exporting to high resolution bitmap pictures. This problem is solved when the default upper limit is increased: <b>-Xmx128m</b> (use 128MB RAM)

I ssh'ed home to my desktop machine and tried to use Batik there. But I got the exception:

    Error: Can't connect to X11 window server using ':0.0' as the value of the DISPLAY variable.

This happens when no X server is running but I googled around for while and found a solution to the problem: `-Djava.awt.headless=true`

So I ended up with something like this:

    user@debian:~$ java -Xmx128m -Djava.awt.headless=true -jar batik-rasterizer.jar -dpi 300 -d out.png in.svg

