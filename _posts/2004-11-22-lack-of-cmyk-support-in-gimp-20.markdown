---
layout: post
status: publish
published: true
title: Lack of CMYK support in GIMP 2.0
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
wordpress_id: 88
wordpress_url: http://emcken.dk/wp/archives/88-lack-of-cmyk-support-in-gimp-20.html
date: '2004-11-22 14:19:34 +0100'
date_gmt: '2004-11-22 14:19:34 +0100'
categories:
- Debian
- Graphics
- Work
tags: []
comments: []
---
<p>At work I have been working on some graphics for an OpenOffice.org CD production. But the graphics for print needs to be delivered in TIFF CMYK format. For this I found a <a href="http:&#47;&#47;www.blackfiveservices.co.uk&#47;separate.shtml">CMYK plug-in for GIMP<&#47;a>.</p>
<p>The installation is a bit tricky for GIMP 2.0 under Linux (haven't tried the other versions&#47;platforms).<br />
First the installation guide doesn't mention GIMP 2.0 for Linux second it differs from the 1.2 installation guide.</p>
<p>The binary provided in the file for GIMP 2.0 under Linux isn't compiled for GIMP 2.0 but for the development version 1.3. This means that you either have to make symlinks on your system for the plug-in to hit the correct libs, or compile yourself.</p>
<p>I compiled the source myself and installed the plug-in i my .gimp-2.0&#47;plug-ins&#47;-dir because I don't like to tinker to much with filesystem outside &#47;home.</p>
<p>To compile you need the following dev-libs:</p>
<p>    rohan:~# apt-get install libgimp2.0-dev liblcms1-dev libtiff4-dev</p>
<p>I also had to modify the Makefile to use GIMP 2.0 libs instead of 1.3.</p>
<p>The GIMP 1.2 installation also mentions the file: <i>sRGB Color Space Profile.icm<&#47;i><br />
But this file isn't provided by the GIMP 2.0 files you can download on the homepage. I found the file in the download for GIMP 1.2 instead.</p>
<p>I would be nice if the plug-in was provided by a Debian package.</p>
