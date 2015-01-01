---
layout: post
status: publish
published: true
title: 'Debian Sarge on IBM X40 howto - Part 10: Suspend'
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
wordpress_id: 84
wordpress_url: http://emcken.dk/wp/archives/84-debian-sarge-on-ibm-x40-howto-part-10-suspend.html
date: '2004-11-04 00:16:10 +0100'
date_gmt: '2004-11-04 00:16:10 +0100'
categories:
- Debian
tags: []
comments: false
---
I have just installed a <a href="http://kernel.org/">2.6.9 kernel</a> and now my laptop wakes after suspend with acpi. But as soon it has awoken it stats to shutdown :(

I have to find out why this happens... but it is getting there YE YE :-D

**Update:** Suspend actually works now. Just me who thinks you have to wake the computer up with the power button. (I had disabled lid-suspend because it didn't work with earlier kernels).

