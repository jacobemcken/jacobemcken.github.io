---
layout: post
status: publish
published: true
title: How to spend a friday night.... kernel compiling
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
wordpress_id: 10
wordpress_url: http://emcken.dk/wp/archives/10-how-to-spend-a-friday-night-kernel-compiling.html
date: '2004-03-12 21:50:38 +0100'
date_gmt: '2004-03-12 21:50:38 +0100'
categories:
- Debian
tags: []
comments: false
---
For I while now I have been having <a href="http://lkml.org/lkml/2004/1/11/61">problems with my keyboard</a> (danish layout). The button with an apostrophe (') and a star (*) was totally dead. Nothing at all happend when pressing it, neither in X or console... WTF?!?

Last night I found out by coincidence that it was my 2.6.1 kernel (look at link above). A guy i the <a href="http://www.debianforum.dk/">Danish Debian forum</a> had the exact same problem. So now I have compiled myself a new kernel (2.6.4). As always I had to compile a few times before I got it all working :)

First I forgot UDMA support and after that I forgot to compile USB support. Now I think I'm all set but we'll see :-D
It is kind a annoying that you have to recompile the NVIDIA driver every time you recompile your kernel. I have to look into packaging the driver into a deb.

Now I only need to find out why video playback have strange colors... I think it happend after updating XFree86 from 4.2 -> 4.3.

