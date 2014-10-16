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
<p>The first thing i did when I returned from vacation in Paris with my girlfriend was to check out all my regular open source information websites to keep me up to date (More on Paris later). On [planet.debian.org][1] [Adam Kessel][2] wrote some [interesting things for ThinkPad X40 owners][3].<br />
I have stolen the text from his blog entry in fear that the link to his weblog won't work one day.</p>
<p>> A couple of extremely useful recent discoveries on my IBM Thinkpad X40:<br />
><br />
> *   `&#47;proc&#47;acpi&#47;ibm`, provided by the `ibm-acpi` package. You can control all sorts of<br />
>     Thinkpad-specific behaviors in here&mdash;including my favorite, which is the automatic<br />
>     display switching when you open and close the lid or dock&#47;undock. You can turn off<br />
>     automatic display switching with:<br />
><br />
>         echo auto_disable > &#47;proc&#47;acpi&#47;ibm&#47;video<br />
><br />
>     You can also turn the light keyboard light on and off with:<br />
><br />
>         echo on > &#47;proc&#47;acpi&#47;ibm&#47;light<br />
>         echo off > &#47;proc&#47;acpi&#47;ibm&#47;light<br />
><br />
>     Etc. Go IBM!<br />
> *   Display corruption: this brings me to my biggest problem running GNU&#47;Linux on the<br />
>     Thinkpad X40&mdash;display corruption. When you switch from internal LCD to external CRT,<br />
>     or sleep and resume, or close&#47;open the lid (with the automatic switch behavior described above),<br />
>     the display moves down 15-20 pixels and the top lines are corrupted garbage.<br />
>     I&rsquo;d post a screenshot, but of course the screen doesn&rsquo;t realize it&rsquo;s corrupted,<br />
>     so it would have to be a digital photo.<br />
>     In any case, I just discovered [this experimental driver to replace i810_drv.o][4]&middot;<br />
>     which makes the problem go away entirely. Just drop it in over the `i810_drv.o` in<br />
>     `&#47;usr&#47;X11R6&#47;lib&#47;modules&#47;drivers&#47;` (bad behavior for Debian&mdash;will be overwritten by<br />
>     an upgrade of course). Hopefully this driver will make its way into the mainline<br />
>     X drivers and eventually back into Debian. This makes using my Thinkpad at least 20%<br />
>     less annoying.<br />
><br />
> Now if only someone would write a driver for the internal SD card reader (apparently [no<br />
> one has gotten it to work][5]&middot;), I think I would have 100% usage of my laptop&rsquo;s features.</p>
<p>[1]: http:&#47;&#47;planet.debian.org<br />
[2]: http:&#47;&#47;adam.rosi-kessel.org&#47;weblog&#47;<br />
[3]: http:&#47;&#47;adam.rosi-kessel.org&#47;weblog&#47;free_software&#47;code&#47;thinkpad_x40_discoveries.html<br />
[4]: http:&#47;&#47;www.fairlite.demon.co.uk&#47;intel.html<br />
[5]: http:&#47;&#47;lists.infradead.org&#47;pipermail&#47;linux-pcmcia&#47;2004-September&#47;001113.html</p>
