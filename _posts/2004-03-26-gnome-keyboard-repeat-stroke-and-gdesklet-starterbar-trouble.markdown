---
layout: post
status: publish
published: true
title: GNOME keyboard repeat stroke- and gDesklet Starterbar trouble
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
wordpress_id: 23
wordpress_url: http://emcken.dk/wp/archives/23-gnome-keyboard-repeat-stroke-and-gdesklet-starterbar-trouble.html
date: '2004-03-26 23:43:00 +0100'
date_gmt: '2004-03-26 23:43:00 +0100'
categories:
- Debian
tags: []
comments: false
---
Today after the regualy update of my Debian unstable desktop a had some serious problems with my keyboard in gnome :( When holding down a key the stroke wasn't repeated. Very annoying when using arrow keys (in console mode).

After a little research I found out that even though the new packages introduced this crappy thing the problem wasn't within the new packages but instead in my configuration i my home-dir.

I used more that a hour to locate the exact configration file:

    ~/.gconf/desktop/gnome/peripherals/keyboard

Most of the time was used googleing for an answer but after a while I decided to try figure out the problem myself and I eventually found the file above.

Another bad thing about my update was that the gdesklet package introduced a broken starterbar. After some research I found out that I now needed the python-xdg package to run... I have made a bug report in this.

**Update:** - It seems that it wasn't a bug after all... just my reading skills ;)

