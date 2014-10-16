---
layout: post
status: publish
published: true
title: 'Debian Sarge on IBM X40 howto - Part 4: Wireless'
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
wordpress_id: 61
wordpress_url: http://emcken.dk/wp/archives/61-debian-sarge-on-ibm-x40-howto-part-4-wireless.html
date: '2004-07-24 00:34:26 +0200'
date_gmt: '2004-07-24 00:34:26 +0200'
categories:
- Debian
tags: []
comments:
- id: 8
  author: Jacob's Weblog
  author_email: ''
  author_url: http://www.emcken.dk/weblog/archives/64_Debian_Sarge_trouble_shooting_on_IBM_X40_part_6_-_wireless_Take_2.html
  date: '2004-07-30 22:39:39 +0200'
  date_gmt: '2004-07-30 21:39:39 +0200'
  content: As I mentioned in an earlier post, I found out that I had been using a
    completly wrong driver for my wireless card. The juice for my card
---
<p>I was really nerveus about getting wireless to work...</p>
<p>**Update**, 24. July 2004, an hour later: Well it was bound to be an impossible mission from the start. The computer dosn't even have an Intel fucker of not supported death wireless card I thought it had :)<br />
What a waste of time.</p>
<p>I have an <a href="http:&#47;&#47;madwifiwiki.thewebhost.de&#47;wiki&#47;IBM80211aBGWirelessLANMiniPCIAdapter">Atheros (AR5212 802.11abg)<&#47;a> card instead.<&#47;i></p>
<p>After googeling I found that my wireless card is an Intel 2200BG which at the time of the writing of the guides I found wasn't supported very well, but the linked to the <a href="http:&#47;&#47;ipw2200.sourceforge.net&#47;">ipw2200 site on SourceForge<&#47;a> developing the Linux driver.<br />
But what the hell - I'll give it a try... it might work.</p>
<p>There is a few requirements for the driver to work:</p>
<p>*   The kernel have to be a 2.6 kernel and have "CONFIG_NET_RADIO" enabled (`CONFIG_NET_RADIO=y`).<br />
    This is all okay if you are using a precompiled kernel from the Debian Sarge archive.<br />
    I used kernel-image-2.6.7-1-686 and the corresponding kernel headers.<br />
*   Wireless tools is needed an is apt-getable with the following:</p>
<p>        apt-get install wireless-tools<br />
*   <a href="http:&#47;&#47;sourceforge.net&#47;projects&#47;ipw2200&#47;">Source code for the driver<&#47;a> downloadable from SourceForge<br />
*   <a href="http:&#47;&#47;ipw2200.sourceforge.net&#47;firmware.php">Firmware for the wireless card<&#47;a> downloadable from the projects website</p>
<p>To compile the source code you also need the packages gcc and make.<br />
For a more detailed howto consult the project website.</p>
<p>Now compile the source code:</p>
<p>    debian:~# tar xzvf ipw2200-0.2.tgz<br />
    debian:~# cd ipw2200-0.2<br />
    debian:~# make</p>
<p>Unpack the firmware files to &#47;usr&#47;lib&#47;hotplug&#47;firmware:</p>
<p>    moprobe firmware_class</p>
<p>If this isn't loaded you will get an error like this "<i>-1 Unknown symbol in module<&#47;i>"</p>
<p>and finally run :</p>
<p>    insmod ipw2200.ko</p>
<p>Well I didn't get any errors... now I have to figure out how this works :)<br />
Step 2... test that it is actually working.</p>
<p>dmesg dosn't detect any new ahrdware when I load the modules, so I don't think that it is working?!</p>
