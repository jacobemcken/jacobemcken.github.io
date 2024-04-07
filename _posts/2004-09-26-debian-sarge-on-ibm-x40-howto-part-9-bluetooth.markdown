---
layout: post
status: publish
published: true
title: 'Debian Sarge on IBM X40 howto - Part 9: Bluetooth'
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
wordpress_id: 63
wordpress_url: http://emcken.dk/wp/archives/63-debian-sarge-on-ibm-x40-howto-part-9-bluetooth.html
date: '2004-09-26 16:17:00 +0200'
date_gmt: '2004-09-26 16:17:00 +0200'
categories:
- Debian
tags:
- debian
- thinkpad
comments: false
---
Today, while I was fiddeling around with my laptop, I tried transfering a file from my <a href="http://www.nokia.com/nokia/0,,47665,00.html">Nokia 6630</a> mobile phone to my laptop. I didn't expect it to work becauce last time it didn't.
But this time it just worked!!

I have installed a few bluetooth [Debian][1] packages like: `bluez-pin` and `bluez-utils`. I use [Bluetooth for GNOME][2] (also packaged for Debian).

I have no idear what I have changed since the last time I tried it, but now I can extract all the pictures I have taken to my laptop. The only thing I remember having altered from the default settings is the device section in `/etc/bluetooth/hcid.conf`:

    device {
        name "IBM X40";
        class 0x100100;
        ...

Look at the [gnome-bluetooth mailinglist][4] for more information about why I altered this.

[1]: http://www.debian.org/
[2]: http://usefulinc.com/software/gnome-bluetooth
[4]: http://lists.usefulinc.com/pipermail/gnome-bluetooth/2004-August/000645.html

