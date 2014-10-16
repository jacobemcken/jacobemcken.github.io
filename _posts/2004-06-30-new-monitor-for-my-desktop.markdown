---
layout: post
status: publish
published: true
title: New monitor for my desktop
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
wordpress_id: 50
wordpress_url: http://emcken.dk/wp/archives/50-new-monitor-for-my-desktop.html
date: '2004-06-30 21:40:40 +0200'
date_gmt: '2004-06-30 21:40:40 +0200'
categories:
- Computer
tags: []
comments:
- id: 14
  author: Jacob Emcken
  author_email: ''
  author_url: http://www.emcken.dk/
  date: '2004-10-17 01:10:33 +0200'
  date_gmt: '2004-10-17 00:10:33 +0200'
  content: "I haven't installed any specific drivers but you need to configure X right.\r\n\r\nI
    have put the following in my XF86Config-4:\r\n\r\nSection \"Monitor\"\r\n        Identifier
    \     \"Samsung SyncMaster 172X\"\r\n        HorizSync       30-81\r\n        VertRefresh
    \    56-75\r\n        Option          \"DPMS\"\r\nEndSection\r\n\r\n\r\n\r\nSection
    \"Screen\"\r\n        Identifier      \"Computer Screen flat\"\r\n        Device
    \         \"Your graphic card configuration\"\r\n        Monitor         \"Samsung
    SyncMaster 172X\"\r\n        DefaultDepth    24\r\n        SubSection \"Display\"\r\n
    \               Depth           1\r\n                Modes           \"1280x1024\"
    \"1024x768\" \"800x600\" \"640x480\"\r\n        EndSubSection\r\n        SubSection
    \"Display\"\r\n                Depth           4\r\n                Modes           \"1280x1024\"
    \"1024x768\" \"800x600\" \"640x480\"\r\n        EndSubSection\r\n        SubSection
    \"Display\"\r\n                Depth           8\r\n                Modes           \"1280x1024\"
    \"1024x768\" \"800x600\" \"640x480\"\r\n        EndSubSection\r\n        SubSection
    \"Display\"\r\n                Depth           15\r\n                Modes           \"1280x1024\"
    \"1024x768\" \"800x600\" \"640x480\"\r\n        EndSubSection\r\n        SubSection
    \"Display\"\r\n                Depth           16\r\n                Modes           \"1280x1024\"
    \"1024x768\" \"800x600\" \"640x480\"\r\n        EndSubSection\r\n        SubSection
    \"Display\"\r\n                Depth           24\r\n                Modes           \"1280x1024\"
    \"1024x768\" \"800x600\" \"640x480\"\r\n        EndSubSection\r\nEndSection"
- id: 16
  author: Yanming
  author_email: wuyanming@hotmail.com
  author_url: ''
  date: '2004-10-14 08:32:11 +0200'
  date_gmt: '2004-10-14 07:32:11 +0200'
  content: "Hi, \r\n\r\n   Do you know any Linux driver available for Samsung SnycMaster
    172X LCD monitor?\r\n\r\n  Thanks in advance.\r\n\r\nYanming"
---
<p>Finnaly I pulled myself together and found a TFT monitor. I have looked on severel but I ended up buying a Samsung 172X because it think it was the best preforming and looking monitor I could find for the price. The Samsung 710T was also a candidate with slightly better specs but it looked ugly <abbr title="In My Opinion">IMO<&#47;abbr>.</p>
<p>My new TFT monitor is to replace my old CRT Samsung SyncMaster 959NF which still works. But it is quite big and after the last netparty I attended it got a scratch :-(</p>
<p>I'm looking forward to friday where it should arrive :-D</p>
