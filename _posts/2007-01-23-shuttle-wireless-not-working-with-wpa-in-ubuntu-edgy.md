---
layout: post
status: publish
published: true
title: Shuttle wireless not working with WPA in Ubuntu Edgy
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
wordpress_id: 177
wordpress_url: http://emcken.dk/wp/archives/177-shuttle-wireless-not-working-with-wpa-in-ubuntu-edgy.html
date: '2007-01-23 21:50:30 +0100'
date_gmt: '2007-01-23 21:50:30 +0100'
categories:
- Ubuntu
tags: []
comments: false
---
Today I used my Ubuntu Linux workstation at home for several hours. I usually only use it for playing World of Warcraft (which until now have been on Windows). When even I need to do some Linux stuff I usually just fire up my trusty IBM x40 laptop, which only takes a few seconds since I always suspend to RAM.

Well while I was playing around with some different things (including getting "World of Warcraft - The Burning Crusade" to run with wine), I got the crazy idea to try use the wireless net instead of the wired. My workstation is a Shuttle and I bought the "special" Shuttle wireless (USB) card, which doesn't take up the precious single free PCI slot.

The module the card is using is:

    zd1211rw

When set up from the commandline WPA is working just nicely... but together with Network Manager I can only connect to WEP encryptet networks... [this is a know bug][1] :(

[1]: https://launchpad.net/ubuntu/+source/linux-restricted-modules-2.6.17/+bug/61094

