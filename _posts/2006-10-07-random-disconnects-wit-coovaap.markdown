---
layout: post
status: publish
published: true
title: Random disconnects wit CoovaAP
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
wordpress_id: 155
wordpress_url: http://emcken.dk/wp/archives/155-random-disconnects-wit-coovaap.html
date: '2006-10-07 17:38:25 +0200'
date_gmt: '2006-10-07 17:38:25 +0200'
categories:
- Linux
tags: []
comments:
- id: 85
  author: cow
  author_email: dogstar@dodgeit.com
  author_url: ''
  date: '2006-12-24 02:44:36 +0100'
  date_gmt: '2006-12-24 01:44:36 +0100'
  content: "Have you checked out the new beta? it is self contained hotspot.. but
    it also has radius, look in their software repository on the system tab.\r\nI
    havent had any random disconnects yet.\r\nI am torn between dd-wrt and coova as
    dd-wrt lets you set up multiple sids on one access point, so you can have different
    hotspots or one encrypted and one not. etc.. I did have random disconnects with
    the beta of dd-wrt and for me was a bit more complex in getting the hotspot working(not
    much)\r\nI have been coova for about a month and very happy with it.\r\nCheck
    the software tab anyway.. lots of mods you can add to coova.. i really like that
    part.. add what you need."
- id: 90
  author: Jacob Emcken
  author_email: jacob@emcken.dk
  author_url: http://www.emcken.dk/
  date: '2006-12-30 19:27:11 +0100'
  date_gmt: '2006-12-30 18:27:11 +0100'
  content: "I haven't tried the new beta yet.\r\n\r\nI found out why I got those \"random\"
    disconnects :)\r\nTotally my own fault. I had enabled the hotspot feature, but
    I hadn't finished configuring it.... so after a while when ChiliSpot couldn't
    start correctly and tried to restart I would experience the \"random\" disconnect.
    I won't got into the technical things here, but dont enable the hotspot if you
    dont set it up correctly. ;)"
- id: 97
  author: Cow
  author_email: ''
  author_url: ''
  date: '2007-01-27 20:01:21 +0100'
  date_gmt: '2007-01-27 19:01:21 +0100'
  content: "Cool glad to see you got it up.. I really like the new beta.\r\nI have
    been running mine without much trouble for a while now. It is an interesting way
    to get to know the neighbors."
---
Through the last week I have played around with CoovaAP on my Linksys WRT54GL... I was just too curious to try it out after my colleague Tomas told me about it :D
CoovaAP is a slightly customized version of OpenWRT to make it easy to setup a wireless HotSpot like the one you can connect to on caf&eacute;s, hotels and such.

CoovaAP comes with pretty much everything out of the box except the FreeRADIUS server. There are some free RADIUS servers around the net which seems to be Coovas intention that people should use. It seems like a really nice pice of software, but for me it was a NO GO.

After installing Coova on my access point I would get randomly kicked off World of Warcraft, even though I was using a wired connection on that machine. Teamspeak and other things seems to be unaffected. I haven't been digging into what caused this problem, I don't really have the experience or the time to do anything about it.

