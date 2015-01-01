---
layout: post
status: publish
published: true
title: 'Debian Sarge on IBM X40 howto - Part 2: Battery monitor'
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
wordpress_id: 59
wordpress_url: http://emcken.dk/wp/archives/59-debian-sarge-on-ibm-x40-howto-part-2-battery-monitor.html
date: '2004-07-23 23:36:04 +0200'
date_gmt: '2004-07-23 23:36:04 +0200'
categories:
- Debian
tags: []
comments: false
---
After I had a console which worked correctly I installed the usual stuf like:
ssh less modconf emacs21

Then I installed GNOME with the following

<pre>apt-get install x-windows-system gnome-core gdm</pre>
... and waited a little.

Autoconfiguring my harware didn't work but after replacing the /etc/X11/XF86Config-4 with the content from <a href="http://www.maths.warwick.ac.uk/~wcasey/LINUX/IBMx40.htm">a guide</a> found on linux-laptop.net it worked like a charm.

Now I wanted a graphical battery indicator and I added "Utility" -> "Battery Charge Monitor" to my GNOME panel. But an error occured after adding it to my panel: <b>"Can't access ACPI events in /var/run/acpid.socket!"</b>

<pre>apt-get install acpid</pre>
Solved the problem.

