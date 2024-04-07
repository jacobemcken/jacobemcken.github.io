---
layout: post
status: publish
published: true
title: 'Debian Sarge on IBM X40 howto - Part 6: Wireless (Take 2)'
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
wordpress_id: 64
wordpress_url: http://emcken.dk/wp/archives/64-debian-sarge-on-ibm-x40-howto-part-6-wireless-take-2.html
date: '2004-07-30 22:33:00 +0200'
date_gmt: '2004-07-30 22:33:00 +0200'
categories:
- Debian
tags:
- debian
- thinkpad
comments: false
---
As I mentioned in [an earlier post][1], I found out that I had been using a completly wrong driver for my wireless card. The juice for my card is a [Multiband Atheros Driver for WiFi (MADWIFI)][2]. I found a [Debian specific driver compile guide][3] on their homepage.

It is a good guide but for Debian users with less tech-knowlegde and rutine it misses a few things <abbr title="I my humble opinion">IMHO</abbr>. I have addressed the problems I ran into here.

A part from the packages mentioned in the guide I was missing `shareutils` (providing `uudecode`), `lib6-dev` and `sysutils` to follow the guide and avoiding error messages ofcource. The missing uudecode was beginning to give me grey hair (in an age of 24 that is not a good sign). The warning (error) message was hidden in the output code many lines before the make-file actually worte 'Error' and exited:

    apt-get install sharutils lib6-dev sysutils

I'm using Debian pre-compiled kernels (at the writing time a 2.6.7-1) which tricked me a bit.
I used the <s>make-kpkg --append-to-version "-686" --revision 2.6.7-1</s> which is wrong!

Instead use (look in guide to know where and when):

    make-kpkg --append-to-version "-1-686" --revision 2.6.7 --config old configure
    make-kpkg --append-to-version "-1-686" --revision 2.6.7 --added-modules madwifi modules_image

Writing this post in my weblog on my laptop sitting in my bed without any wires connected at all - Now everything is forgiven and forgotten.

[1]: {% post_url 2004-07-24-debian-sarge-on-ibm-x40-howto-part-4-wireless %}
[2]: http://sourceforge.net/projects/madwifi
[3]: http://www.marlow.dk/site.php/tech/madwifi/simple

