---
layout: post
status: publish
published: true
title: Show progress of dd
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
wordpress_id: 36
wordpress_url: http://emcken.dk/wp/archives/36-show-progress-of-dd.html
date: '2004-05-27 12:35:30 +0200'
date_gmt: '2004-05-27 12:35:30 +0200'
categories:
- Random hacks
tags: []
comments:
- id: 318
  author: wdef
  author_email: wdef200@gmail.com
  author_url: ''
  date: '2009-05-13 11:17:50 +0200'
  date_gmt: '2009-05-13 10:17:50 +0200'
  content: "Doesn't work.  Is there a typo?\r\n\r\nbash: syntax error near unexpected
    token `&amp;'"
- id: 319
  author: wdef
  author_email: wdef200@gmail.com
  author_url: ''
  date: '2009-05-13 11:21:34 +0200'
  date_gmt: '2009-05-13 10:21:34 +0200'
  content: "Hang on - this is sdd, not dd.\r\n\r\nHowever I still can't see what 2&amp;gt
    is supposed to be doing from man sdd\r\n\r\nCan someone explain this?"
- id: 320
  author: Jacob Emcken
  author_email: jacob@emcken.dk
  author_url: http://www.emcken.dk/
  date: '2009-05-27 09:13:37 +0200'
  date_gmt: '2009-05-27 08:13:37 +0200'
  content: |-
    @wdef

    After migrating my blog from Serenditpity to Wordpress it messed with some of my examples... I've corrected the code now.
    Please try again
- id: 506
  author: fantomas
  author_email: fantomas@fantomas.sk
  author_url: http://www.fantomas.sk
  date: '2011-08-07 17:15:26 +0200'
  date_gmt: '2011-08-07 16:15:26 +0200'
  content: |
    it causes redirect stderr to the same file handle as stdout.
redirect_from:
  - /weblog/archives/36-Show-progress-of-dd.html
---
Here is a perl code example which shows progress of dd made by my friend <a href="http://www.dreier.nu/">Thor Dreier</a>:

    sdd if=/boot.img.gz of=/dev/sda -pg bs=1M 2>&1 | perl -e 'print $i++." MB\n" while(read(STDIN,$c,3));'

