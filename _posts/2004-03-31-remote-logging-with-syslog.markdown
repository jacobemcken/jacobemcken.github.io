---
layout: post
status: publish
published: true
title: Remote logging with syslog
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
wordpress_id: 27
wordpress_url: http://emcken.dk/wp/archives/27-remote-logging-with-syslog.html
date: '2004-03-31 13:48:35 +0200'
date_gmt: '2004-03-31 13:48:35 +0200'
categories:
- Computer
tags: []
comments: []
---
<p>Today i played around with syslogd and here is what I learned:</p>
<p>To enable syslog to recieve logs from remote machines you have to give the syslogd an <i>-r<&#47;i> parameter. I Debian you have edit &#47;etc&#47;inet.d&#47;syslogd to enable remote logging. A bug has allready been filed about this <a href="http:&#47;&#47;bugs.debian.org&#47;cgi-bin&#47;bugreport.cgi?bug=205239">#205239<&#47;a></p>
<p>On other systems like RedHat you can configure it in <i>&#47;etc&#47;sysconfig&#47;syslog<&#47;i> where you set SYSLOGD_PARAMS="-r"</p>
<p>Now restart syslog with:</p>
<p>    machine:~# &#47;etc&#47;init.d&#47;sysklogd restart</p>
<p>To configure the clients edit `&#47;etc&#47;syslog.conf`:</p>
<p>    #Remote logging to 10.0.0.5<br />
    *.* @10.0.0.5</p>
<p>And restart syslog like above (now on you client).</p>
<p><i>NOTE - You can only have comments on newlines. This example is WRONG!!<&#47;i></p>
<p>    *.* @10.0.0.5 #My log machine<</p>
