---
layout: post
status: publish
published: true
title: Scaleable icon for Pidgin - Gnome-Do
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
wordpress_id: 257
wordpress_url: http://emcken.dk/weblog/?p=257
date: '2009-03-19 07:11:13 +0100'
date_gmt: '2009-03-19 06:11:13 +0100'
categories:
- Graphics
- Random hacks
- Ubuntu
tags:
- gnome-do
- bug
comments: false
---
I like my desktop to look good (cool effects not needed) and I'm a heavy Gnome-Do user so it has been annoying me for quite some time that when starting Pidgin it looks like this:

<img src="/public/media/pidgin-gnome-do-before.png" alt="pidgin-gnome-do-before" width="394" height="219" class="alignnone size-full wp-image-258" />

So I started searching Launchpad for a bugreport and actually found 2:

  * [216460][bug 1]
  * [337374][bug 2]

Thos bugs led me to [a bug report in Pidgins own trac][bug 3] about the same issue. Which led me to download the source code and an usable svg file which I manually copied to my system:

    sudo cp ./pidgin-2.5.5/pidgin/pixmaps/icons/hicolor/48x48/apps/scalable/pidgin.svg /usr/share/icons/hicolor/scalable/apps/

Long story short... now launching Piding from Gnome-Do looks like this:

<img src="/public/media/pidgin-gnome-do-after.png" alt="pidgin-gnome-do-after" width="396" height="221" class="alignnone size-full wp-image-259" />

YAY :D

[bug 1]: https://bugs.launchpad.net/ubuntu/+source/pidgin/+bug/216460
[bug 2]: https://bugs.launchpad.net/ubuntu/+source/pidgin/+bug/337374
[bug 3]: http://developer.pidgin.im/ticket/8556

