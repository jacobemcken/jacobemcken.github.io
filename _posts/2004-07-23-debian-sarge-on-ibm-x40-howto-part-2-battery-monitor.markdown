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
comments: []
---
<p>After I had a console which worked correctly I installed the usual stuf like:<br />
ssh less modconf emacs21</p>
<p>Then I installed GNOME with the following</p>
<pre>apt-get install x-windows-system gnome-core gdm<&#47;pre><br />
... and waited a little.</p>
<p>Autoconfiguring my harware didn't work but after replacing the &#47;etc&#47;X11&#47;XF86Config-4 with the content from <a href="http:&#47;&#47;www.maths.warwick.ac.uk&#47;~wcasey&#47;LINUX&#47;IBMx40.htm">a guide<&#47;a> found on linux-laptop.net it worked like a charm.</p>
<p>Now I wanted a graphical battery indicator and I added "Utility" -> "Battery Charge Monitor" to my GNOME panel. But an error occured after adding it to my panel: <b>"Can't access ACPI events in &#47;var&#47;run&#47;acpid.socket!"<&#47;b></p>
<pre>apt-get install acpid<&#47;pre><br />
Solved the problem.</p>
