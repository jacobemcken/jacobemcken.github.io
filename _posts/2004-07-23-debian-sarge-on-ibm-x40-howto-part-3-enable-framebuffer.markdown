---
layout: post
status: publish
published: true
title: 'Debian Sarge on IBM X40 howto - Part 3: Enable framebuffer'
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
wordpress_id: 60
wordpress_url: http://emcken.dk/wp/archives/60-debian-sarge-on-ibm-x40-howto-part-3-enable-framebuffer.html
date: '2004-07-23 23:55:54 +0200'
date_gmt: '2004-07-23 23:55:54 +0200'
categories:
- Debian
tags: []
comments: []
---
<p>The IBM X40 seems to like using framebuffer so might find it usefull to put the following in you grub &#47;boot&#47;grub&#47;menu.lst</p>
<pre># kopt=root=&#47;dev&#47;hda3 ro vga=771<&#47;pre><br />
Notice the <b>vga=771<&#47;b></p>
<p>Whenever you install a new kernel and grub-update is run the "vga=771" will automatically be appended to every entry in the menu.lst<br />
Now you don't have to enter it manualy everytime you install a new kernel.</p>
