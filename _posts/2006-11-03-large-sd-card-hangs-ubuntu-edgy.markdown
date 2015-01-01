---
layout: post
status: publish
published: true
title: Large SD card hangs Ubuntu Edgy
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
wordpress_id: 169
wordpress_url: http://emcken.dk/wp/archives/169-large-sd-card-hangs-ubuntu-edgy.html
date: '2006-11-03 10:33:26 +0100'
date_gmt: '2006-11-03 10:33:26 +0100'
categories:
- Ubuntu
tags: []
comments:
- id: 82
  author: n3ldan
  author_email: n3ldan@gmail.com
  author_url: http://n3ldan.info
  date: '2006-12-21 05:27:18 +0100'
  date_gmt: '2006-12-21 04:27:18 +0100'
  content: Yeah I have the same problem.  You have an X40?  I can read my 256mb card
    just fine, but my not my 2gb card.  It's a bummer, but I just use my camera as
    an SD reader.
- id: 119
  author: stocks29
  author_email: ''
  author_url: stocks29.blogspot.com
  date: '2007-03-09 02:20:27 +0100'
  date_gmt: '2007-03-09 01:20:27 +0100'
  content: Same problem here too. I have an Dell xps m140 laptop.  Exact same situation
    with my 2GB SD Card. Any word on a fix.
- id: 120
  author: Jacob Emcken
  author_email: jacob@emcken.dk
  author_url: http://www.emcken.dk/
  date: '2007-03-09 10:25:29 +0100'
  date_gmt: '2007-03-09 09:25:29 +0100'
  content: "The bug was fixed a while back.\r\nMy kernel:\r\n\r\n    Linux rohan 2.6.17-11-generic
    #2 SMP Thu Feb 1 19:52:28 UTC 2007 i686 GNU/Linux\r\n\r\nAnd my 2GB SD card
    is now working. You got the same card reader hardware?\r\n\r\nMine is (output
    from `lspci`):\r\n\r\n    02:00.0 CardBus bridge: Ricoh Co Ltd RL5c476 II (rev
    8d)\r\n    02:00.1 Class 0805: Ricoh Co Ltd R5C822 SD/SDIO/MMC/MS/MSPro
    Host Adapter (rev 13)"
---
Damn... never brag about uptime :-(

Today I received a 2GB SD card which I'm going to use to store ssh and gpg keys on. Of cause I'm going to [encrypt it][1] like I wrote about some days ago. When I inserted the SD card into my laptop everything just froze... it guess I'm hit by [bug #61758][2].
Ubuntu Edgy uses kernel 2.6.17 which seems to be affected by this bug. It should be fixed in 2.6.18 though.

I'll try to patch my kernel module... god I hoped those days where over. Exciting to see if I can even remember how to do it. I hope this gets fixed soon, life is to short to compile kernel modules. My laptop should just work.

[1]: http://www.emcken.dk/weblog/archives/164-Encrypted-USB-drive-in-Ubuntu.html
[2]: https://launchpad.net/distros/ubuntu/+source/linux-source-2.6.17/+bug/61758

