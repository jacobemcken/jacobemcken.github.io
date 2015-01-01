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
comments: false
---
Today i played around with syslogd and here is what I learned:

To enable syslog to recieve logs from remote machines you have to give the syslogd an <i>-r</i> parameter. I Debian you have edit /etc/inet.d/syslogd to enable remote logging. A bug has allready been filed about this <a href="http://bugs.debian.org/cgi-bin/bugreport.cgi?bug=205239">#205239</a>

On other systems like RedHat you can configure it in <i>/etc/sysconfig/syslog</i> where you set SYSLOGD_PARAMS="-r"

Now restart syslog with:

    machine:~# /etc/init.d/sysklogd restart

To configure the clients edit `/etc/syslog.conf`:

    #Remote logging to 10.0.0.5
    *.* @10.0.0.5

And restart syslog like above (now on you client).

<i>NOTE - You can only have comments on newlines. This example is WRONG!!</i>

    *.* @10.0.0.5 #My log machine<

