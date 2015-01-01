---
layout: post
status: publish
published: true
title: Ubuntu Dapper Drake on my IBM X40
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
wordpress_id: 143
wordpress_url: http://emcken.dk/wp/archives/143-ubuntu-dapper-drake-on-my-ibm-x40.html
date: '2006-03-14 19:50:00 +0100'
date_gmt: '2006-03-14 19:50:00 +0100'
categories:
- Ubuntu
tags: []
comments:
- id: 64
  author: Jacob Emcken
  author_email: jacob@emcken.dk
  author_url: www.emcken.dk
  date: '2006-08-19 14:47:49 +0200'
  date_gmt: '2006-08-19 13:47:49 +0200'
  content: I wouldn't count on it... it havn't got a 3D card. But actually I havn't
    tried it out for size.
- id: 106
  author: Zaiden
  author_email: zaiden@gmail.com
  author_url: ''
  date: '2006-04-07 17:34:40 +0200'
  date_gmt: '2006-04-07 16:34:40 +0200'
  content: "Hey, can you run 3d games with WINE on this laptop.\r\n\r\nI have the
    same video card, but it was impossible to me to run Jedi Academy or Dungeon Siege
    2 with WINE\r\n\r\nZaiden."
---
I installed [Dapper Drake][1] on my laptop and it went pretty well. At the time of writing it isn't released as stable yet. I didn't make a clean install but jut upgraded my current Breezy installation.

The first thing I noticed was the my SD card reader started working. YAY - kudos to the developer(s) how made that happen. Even if you dont use Ubuntu you should be able make it work on you machine too, because it was an addition in kernel 2.6.15 which now supports the SD card reader in IBM's X40 (which is a  Ricoh R5C822). I dunno which other SD card readers now work but take a look at [this site][4]

The reason I upgraded to Dapper was because I hoped that the problems with using an external monitor (or projector) would be solved. As you might have guessed they where not. Instead I found a SourceForge project: [intel 855GM crt video out driver][5]. This is a small program which can enable ad disable the extarnal monitor port. I works very well, and I can now use my laptop for presentations now that I can have both my laptop screen and the extarnal monitor enbaled at the same time.

A little bonus I got from upgrading Ubuntu was:

*   Speedups in GNOME which I can feel (not much, but they are there)
*   Firefox 1.5, yay - tab reordering
*   Gedit can now save files to remote locations trasparent via gnome-vfs (ie. ssh)
*   Better performance with gnome-terminal
*   Quicker startup time (I dont use that much because I usually just suspends my laptop to RAM)
    **Important:** Right now suspend doesn't work right. It seems alot faster to suspend and resume but redraws of the screen totally fucks up after resume. Ie. scrolling on a homepage or moveing windows is behaving very weird. I'm planning to try installing from the flight 5 CD's to test if it has something to do with my upgrade from Breezy.

[1]: http://ubuntu.com/
[4]: http://mmc.drzeus.cx/wiki/Controllers
[5]: http://sourceforge.net/projects/i855crt

