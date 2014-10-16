---
layout: post
status: publish
published: true
title: Microsoft will use equipment located in California and other states
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
wordpress_id: 204
wordpress_url: http://emcken.dk/wp/archives/204-microsoft-will-use-equipment-located-in-california-and-other-states.html
date: '2007-10-18 14:03:22 +0200'
date_gmt: '2007-10-18 14:03:22 +0200'
categories:
- Uncategorized
tags: []
comments:
- id: 152
  author: sniper
  author_email: ''
  author_url: ''
  date: '2008-01-26 23:23:34 +0100'
  date_gmt: '2008-01-26 22:23:34 +0100'
  content: "because cali is the first state to have anti-spam laws on the book. So
    if you send spam to a server in that state, you can be sued.\r\n\r\nbasically."
- id: 161
  author: Jacob Emcken
  author_email: jacob@emcken.dk
  author_url: http://www.emcken.dk/
  date: '2008-02-27 22:35:25 +0100'
  date_gmt: '2008-02-27 21:35:25 +0100'
  content: Cool nice to know...I still think that last sentence is a bit weird :)
---
<p>I have been meaning to blog about this for a while now. When contacting the Hotmail mail servers to send email your mail server will be presented with strange message in my opinion.</p>
<p>First find the servers which receives email for the hotmail.com domain:</p>
<p>    dig mx hotmail.com</p>
<p>which will return something like the following:</p>
<p>    hotmail.com.            3600    IN      MX      5 mx1.hotmail.com.<br />
    hotmail.com.            3600    IN      MX      5 mx2.hotmail.com.<br />
    hotmail.com.            3600    IN      MX      5 mx3.hotmail.com.<br />
    hotmail.com.            3600    IN      MX      5 mx4.hotmail.com.</p>
<p>Now try contact one of these on port 25 (just like mail servers does):</p>
<p>    telnet mx1.hotmail.com 25</p>
<p>Notice the greetings message from the server:</p>
<p>    Sending unsolicited commercial or bulk e-mail to Microsoft's computer network is prohibited.<br />
    Other restrictions are found at http:&#47;&#47;privacy.msn.com&#47;Anti-spam&#47;.<br />
    Violations will result in use of equipment located in California and other states. </p>
<p>Bringing it to peoples attention that sending unsolicited commercial or bulk e-mails is prohibited is fair enought even though I doubt many will read it :) But is it really nessesary to threat to use equipment, and why is California emphasised?</p>
