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
comments: []
---
<p>I have been playing around with syslog for an hour or two, trying to figure out how to log sshd related messages in a separate file. When I found out that it is impossible to specify a log-file for the ssh daemon only I thought: "<i>What CRAP!"<&#47;i>.</p>
<p>... but only for a short while until an intelligent person pointed out the obvious:</p>
<p>    debian:~# grep sshd &#47;var&#47;log&#47;auth.log > &#47;tmp&#47;sshd</p>
<p><a href="http:&#47;&#47;groups.google.com&#47;groups?hl=en&amp;lr=&amp;ie=UTF-8&amp;oe=UTF-8&amp;frame=right&amp;th=a5d618e4a780b078&amp;seekm=b1k2k2%24ok1%241%40nntp.itservices.ubc.ca#link5"><br />
Original Goolge Groups message<&#47;a></p>
