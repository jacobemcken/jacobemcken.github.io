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
tags:
- debian
- thinkpad
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
I was really nerveus about getting wireless to work...

**Update**, 24. July 2004, an hour later: Well it was bound to be an impossible mission from the start. The computer dosn't even have an Intel fucker of not supported death wireless card I thought it had :)
What a waste of time.

I have an <a href="http://madwifiwiki.thewebhost.de/wiki/IBM80211aBGWirelessLANMiniPCIAdapter">Atheros (AR5212 802.11abg)</a> card instead.</i>

After googeling I found that my wireless card is an Intel 2200BG which at the time of the writing of the guides I found wasn't supported very well, but the linked to the <a href="http://ipw2200.sourceforge.net/">ipw2200 site on SourceForge</a> developing the Linux driver.
But what the hell - I'll give it a try... it might work.

There is a few requirements for the driver to work:

*   The kernel have to be a 2.6 kernel and have "CONFIG_NET_RADIO" enabled (`CONFIG_NET_RADIO=y`).
    This is all okay if you are using a precompiled kernel from the Debian Sarge archive.
    I used kernel-image-2.6.7-1-686 and the corresponding kernel headers.
*   Wireless tools is needed an is apt-getable with the following:

        apt-get install wireless-tools
*   <a href="http://sourceforge.net/projects/ipw2200/">Source code for the driver</a> downloadable from SourceForge
*   <a href="http://ipw2200.sourceforge.net/firmware.php">Firmware for the wireless card</a> downloadable from the projects website

To compile the source code you also need the packages gcc and make.
For a more detailed howto consult the project website.

Now compile the source code:

    debian:~# tar xzvf ipw2200-0.2.tgz
    debian:~# cd ipw2200-0.2
    debian:~# make

Unpack the firmware files to /usr/lib/hotplug/firmware:

    moprobe firmware_class

If this isn't loaded you will get an error like this "<i>-1 Unknown symbol in module</i>"

and finally run :

    insmod ipw2200.ko

Well I didn't get any errors... now I have to figure out how this works :)
Step 2... test that it is actually working.

dmesg dosn't detect any new ahrdware when I load the modules, so I don't think that it is working?!

