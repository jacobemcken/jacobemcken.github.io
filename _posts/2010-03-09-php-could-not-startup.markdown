---
layout: post
status: publish
published: true
title: 'php: Could not startup.'
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
wordpress_id: 308
wordpress_url: http://emcken.dk/weblog/?p=308
date: '2010-03-09 12:48:04 +0100'
date_gmt: '2010-03-09 11:48:04 +0100'
categories:
- Linux
tags:
- php-gtk
comments: []
---
<p>Informative error message... NOT.</p>
<p>I've installed php-gtk on a machine and got the above error when trying to start php from cli via ssh.</p>
<p>After a while it suddenly hit me that I hadn't forwarded X when I logged in via ssh. After that I was able to use php again.<br />
php with php-gtk is unable to start without a X server.</p>
