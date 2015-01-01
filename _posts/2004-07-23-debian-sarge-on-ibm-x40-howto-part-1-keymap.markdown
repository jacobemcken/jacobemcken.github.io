---
layout: post
status: publish
published: true
title: 'Debian Sarge on IBM X40 howto - Part 1: Keymap'
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
wordpress_id: 58
wordpress_url: http://emcken.dk/wp/archives/58-debian-sarge-on-ibm-x40-howto-part-1-keymap.html
date: '2004-07-23 23:09:48 +0200'
date_gmt: '2004-07-23 23:09:48 +0200'
categories:
- Debian
tags: []
comments: false
---
After installing a clean Sarge system from an USB key with some of the nightly builds of the new Debian installer I ran into the first troubles.
My keyboard didn't function properly... even though I could write danish characters like "&aelig;&oslash;&aring;", I wasn't able to shift through the virtual consoles with (Ctrl+Alt+F1 - F6).

<pre>dpkg-reconfigure console-data</pre>
revealed that the "specific keymap" was set to "Apple USB".
changing this to "Standard" helped.

