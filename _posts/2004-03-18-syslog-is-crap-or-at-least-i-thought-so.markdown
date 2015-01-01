---
layout: post
status: publish
published: true
title: Syslog is crap - or at least I thought so
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
wordpress_id: 15
wordpress_url: http://emcken.dk/wp/archives/15-syslog-is-crap-or-at-least-i-thought-so.html
date: '2004-03-18 09:59:18 +0100'
date_gmt: '2004-03-18 09:59:18 +0100'
categories:
- Computer
tags: []
comments: false
---
I have been playing around with syslog for an hour or two, trying to figure out how to log sshd related messages in a separate file. When I found out that it is impossible to specify a log-file for the ssh daemon only I thought: "<i>What CRAP!"</i>.

... but only for a short while until an intelligent person pointed out the obvious:

    debian:~# grep sshd /var/log/auth.log > /tmp/sshd

<a href="http://groups.google.com/groups?hl=en&amp;lr=&amp;ie=UTF-8&amp;oe=UTF-8&amp;frame=right&amp;th=a5d618e4a780b078&amp;seekm=b1k2k2%24ok1%241%40nntp.itservices.ubc.ca#link5">
Original Goolge Groups message</a>

