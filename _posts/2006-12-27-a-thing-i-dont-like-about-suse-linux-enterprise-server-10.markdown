---
layout: post
status: publish
published: true
title: A thing I dont like about Suse Linux Enterprise Server 10
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
wordpress_id: 175
wordpress_url: http://emcken.dk/wp/archives/175-a-thing-i-dont-like-about-suse-linux-enterprise-server-10.html
date: '2006-12-27 13:46:33 +0100'
date_gmt: '2006-12-27 13:46:33 +0100'
categories:
- Work
- Linux
tags: []
comments:
- id: 93
  author: Kirk
  author_email: ''
  author_url: ''
  date: '2007-01-08 20:58:01 +0100'
  date_gmt: '2007-01-08 19:58:01 +0100'
  content: The reason for this...hb_gui -- an integral part of the heartbeat-2.0.5-7.10
    package.
- id: 95
  author: Jacob Emcken
  author_email: jacob@emcken.dk
  author_url: http://www.emcken.dk/
  date: '2007-01-09 19:50:51 +0100'
  date_gmt: '2007-01-09 18:50:51 +0100'
  content: "Sorry for not being clear... I know about the dependencies ;)\r\n\r\nWhat
    I dont understand is why Suse have made the choice to make hb_gui an integral
    part of their package and not just an optional&#47;recommended package."
---
<p>When I install a server I usually make a minimal install and put on the software which is needed for the task the machine is to perform. Less packages which needs security updates the better IMO. Today I was setting up a heartbeat cluster on a SLES10. When the heartbeat package is installed the system also installs shaitloads of other packages like gtk pango and some X-libs... and I simply dont get why?!?</p>
<p>It feels so Windows like.... eeeeekkk.</p>
<p>Sorry just had to get it out.</p>
