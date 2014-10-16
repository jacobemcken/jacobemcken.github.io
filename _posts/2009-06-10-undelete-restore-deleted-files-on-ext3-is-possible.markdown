---
layout: post
status: publish
published: true
title: Undelete &#47; restore deleted files on ext3 is possible
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
wordpress_id: 283
wordpress_url: http://emcken.dk/weblog/?p=283
date: '2009-06-10 06:27:34 +0200'
date_gmt: '2009-06-10 05:27:34 +0200'
categories:
- Linux
tags:
- ext3
- undelete
- restore
comments: []
---
<p>For a long time I thought the only way to restore something deleted on ext3 was to `cat` the device and `grep` for known strings from the deleted files.<br />
Which:</p>
<p>  1. only worked for text files like like config and code files.<br />
  2. was very cumbersome and error prone.</p>
<p>Anyway thanks to Carlo Wood, ext3grep and this fine tutotial about [HOWTO recover deleted files on an ext3 file system][1] which has proven me wrong. Not that I need it right now but I've been there and will probably end up in the situation again.</p>
<p>[1]: http:&#47;&#47;www.xs4all.nl&#47;~carlo17&#47;howto&#47;undelete_ext3.html</p>
