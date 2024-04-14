---
layout: post
status: publish
published: true
title: Adam Kessel's Thinkpad X40 discoveries
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
wordpress_id: 112
wordpress_url: http://emcken.dk/wp/archives/112-adam-kessels-thinkpad-x40-discoveries.html
date: '2005-06-13 00:29:47 +0200'
date_gmt: '2005-06-13 00:29:47 +0200'
categories:
- Computer
- Linux
tags: []
comments:
- id: 105
  author: Wade Mealing
  author_email: wmealing@gmail.com
  author_url: http://blog.subverted.net
  date: '2006-04-04 16:07:26 +0200'
  date_gmt: '2006-04-04 15:07:26 +0200'
  content: The card reader works in FC6 Rawhide today.
---
The first thing i did when I returned from vacation in Paris with my girlfriend was to check out all my regular open source information websites to keep me up to date (More on Paris later). On [planet.debian.org][1] [Adam Kessel][2] wrote some [interesting things for ThinkPad X40 owners][3].
I have stolen the text from his blog entry in fear that the link to his weblog won't work one day.

> A couple of extremely useful recent discoveries on my IBM Thinkpad X40:
>
> *   `/proc/acpi/ibm`, provided by the `ibm-acpi` package. You can control all sorts of
>     Thinkpad-specific behaviors in here&mdash;including my favorite, which is the automatic
>     display switching when you open and close the lid or dock/undock. You can turn off
>     automatic display switching with:
>
>         echo auto_disable > /proc/acpi/ibm/video
>
>     You can also turn the light keyboard light on and off with:
>
>         echo on > /proc/acpi/ibm/light
>         echo off > /proc/acpi/ibm/light
>
>     Etc. Go IBM!
> *   Display corruption: this brings me to my biggest problem running GNU/Linux on the
>     Thinkpad X40&mdash;display corruption. When you switch from internal LCD to external CRT,
>     or sleep and resume, or close/open the lid (with the automatic switch behavior described above),
>     the display moves down 15-20 pixels and the top lines are corrupted garbage.
>     I&rsquo;d post a screenshot, but of course the screen doesn&rsquo;t realize it&rsquo;s corrupted,
>     so it would have to be a digital photo.
>     In any case, I just discovered [this experimental driver to replace i810_drv.o][4]&middot;
>     which makes the problem go away entirely. Just drop it in over the `i810_drv.o` in
>     `/usr/X11R6/lib/modules/drivers/` (bad behavior for Debian&mdash;will be overwritten by
>     an upgrade of course). Hopefully this driver will make its way into the mainline
>     X drivers and eventually back into Debian. This makes using my Thinkpad at least 20%
>     less annoying.
>
> Now if only someone would write a driver for the internal SD card reader (apparently [no
> one has gotten it to work][5]&middot;), I think I would have 100% usage of my laptop&rsquo;s features.

[1]: http://planet.debian.org
[2]: http://adam.rosi-kessel.org/weblog/
[3]: http://adam.rosi-kessel.org/weblog/free_software/code/thinkpad_x40_discoveries.html
[4]: http://www.fairlite.demon.co.uk/intel.html
[5]: http://lists.infradead.org/pipermail/linux-pcmcia/2004-September/001113.html

