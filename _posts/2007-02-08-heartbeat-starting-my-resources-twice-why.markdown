---
layout: post
status: publish
published: true
title: Heartbeat starting my resources twice... why?
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
wordpress_id: 183
wordpress_url: http://emcken.dk/wp/archives/183-heartbeat-starting-my-resources-twice-why.html
date: '2007-02-08 12:53:00 +0100'
date_gmt: '2007-02-08 12:53:00 +0100'
categories:
- Random hacks
- Work
- Linux
tags: []
comments: []
---
<p>While working on implementing failover for a JBoss application in heartbeat I had it sometimes fail miserably. After examining the logs files for a while I noticed that it tried to start my service twice.. why?</p>
<p>This was due to multiple errors from my side:</p>
<p>1.  I hadn't implemented the status call for my heartbeat resource script<br />
2.  My script didn't return true when asked to start and it was already started.</p>
<p>According to LSB standard your start &#47; stop scripts should return true even though your service is already started.</p>
<p>**Note to self:** Learn to read the documentation and not just assume you know how it works (especially not with failover clusters... they are meant to have uptime you know.</p>
<p>Good readings about heartbeat and the [resouces][1] [scripts][2].</p>
<p>[1]: http:&#47;&#47;www.linux-ha.org&#47;OCFResourceAgent<br />
[2]: http:&#47;&#47;wiki.linux-ha.org&#47;HeartbeatResourceAgent</p>
