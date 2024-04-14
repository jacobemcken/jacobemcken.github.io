---
layout: post
status: publish
published: true
title: 'Debian Sarge on IBM X40 howto - Part 7: Mouse track dot'
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
wordpress_id: 65
wordpress_url: http://emcken.dk/wp/archives/65-debian-sarge-on-ibm-x40-howto-part-7-mouse-track-dot.html
date: '2004-07-31 10:39:25 +0200'
date_gmt: '2004-07-31 10:39:25 +0200'
categories:
- Debian
tags:
- debian
- thinkpad
comments: false
---
I have experienced that my mouse isn't working after a reboot a few times. I think it happens after a system update... and this morning I was hit again. When starting X the mouse pointer wouldn't move.

After googleing around for a while I found out that the `psmouse` module wasn't loaded. I compared an `lsmod` output I had found on the net with my own and discovered that the above module wasn't loaded:

    modprobe psmouse

Did the trick and even without reloading X.

