---
layout: post
status: publish
published: true
title: 'Debian Sarge on IBM X40 howto - Part 5: CPU frequency scaling'
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
wordpress_id: 62
wordpress_url: http://emcken.dk/wp/archives/62-debian-sarge-on-ibm-x40-howto-part-5-cpu-frequency-scaling.html
date: '2004-07-24 00:48:47 +0200'
date_gmt: '2004-07-24 00:48:47 +0200'
categories:
- Debian
tags: []
comments: []
---
<p>This was quite easy:</p>
<pre>apt-get install cpufreqd<br />
apt-get install gnome-cpufreq-applet<&#47;pre></p>
<p>Finally add the applet to the GNOME panel.</p>
<p>Make sure that the <b>acpi<&#47;b> module is loaded in order to get cpufreqd to run, because without cpufreqd no frequency scaling will happen.</p>
<p>Now when ever you pull the power for your laptop the frequency will be scaled down to 66% until the system requires the recources. This can be cunfigured in &#47;etv&#47;cpufreqd.conf.</p>
