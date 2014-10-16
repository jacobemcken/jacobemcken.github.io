---
layout: post
status: publish
published: true
title: 'Debian Sarge on IBM X40 howto - Part 8: Wireless (Take 3)'
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
wordpress_id: 70
wordpress_url: http://emcken.dk/wp/archives/70-debian-sarge-on-ibm-x40-howto-part-8-wireless-take-3.html
date: '2004-09-02 14:41:38 +0200'
date_gmt: '2004-09-02 14:41:38 +0200'
categories:
- Debian
tags: []
comments:
- id: 416
  author: Brian Hughes
  author_email: iongreen34@msn.com
  author_url: http://www.bodycleanser.info
  date: '2010-05-11 19:31:34 +0200'
  date_gmt: '2010-05-11 18:31:34 +0200'
  content: |
    <p>Led lights are great because they are long lasting and consumes less electricity.,';<&#47;p>
---
<p>I wanted to update my kernel to the 2.6.8 kernel but I had some troubles compiling the Madwifi source code because a change in the kernel source code. I found a patch for the 2.6.8 kernel (only) and a <a href="http:&#47;&#47;www.linux-wireless.org&#47;Wireless&#47;Install-HOWTO&#47;Drivers&#47;madwifi&#47;madwifi-20040827-linux-2.6.8.1.Patch-Howto.txt">Howto for applying the patch<&#47;a>.</p>
<p>In my search around the net for info on my laptop, I found out how to enable the led that indicates activity on the wireless card. Before compiling the driver you have to set an environmental variable:</p>
<p>    export COPTS="$COPTS -DSOFTLED"</p>
<p>Now that I recompiled the driver anyway I enabled the variable and BINGO... now my led lights up as soon the driver is loaded. And thats just the half of it... sometimes it also blinks ;)<br />
Though I havn't testet it under heavy load (to test if the blinking increases under heavy traffic).<br />
HURRAY... I have an extra led blinking on my laptop.</p>
