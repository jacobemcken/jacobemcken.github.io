---
layout: post
status: publish
published: true
title: My server was hacked
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
wordpress_id: 134
wordpress_url: http://emcken.dk/wp/archives/134-my-server-was-hacked.html
date: '2005-09-27 19:24:06 +0200'
date_gmt: '2005-09-27 19:24:06 +0200'
categories:
- Linux
tags: []
comments: false
---
Yesterday evening while struggeling to keep up with work I noticed my internet connection was acting strangly. I checked my server (which have a fairly small amount of visitors) to see who the visitor was and noticed that I had a strange connection:

    shire:/var/www# netstat -tanp
    Active Internet connections (servers and established)
    Proto Recv-Q Send-Q Local Address           Foreign Address         State       PID/Program name
    tcp        0      0 0.0.0.0:37              0.0.0.0:*               LISTEN     1770/inetd
    tcp        0      0 0.0.0.0:31337           0.0.0.0:*               LISTEN     3694/httpd -DSSL
    tcp        0      0 0.0.0.0:9               0.0.0.0:*               LISTEN     1770/inetd
    tcp        0      0 127.0.0.1:3306          0.0.0.0:*               LISTEN     1822/mysqld
    tcp        0      0 0.0.0.0:139             0.0.0.0:*               LISTEN     1985/smbd
    tcp        0      0 0.0.0.0:13              0.0.0.0:*               LISTEN     1770/inetd
    tcp        0      0 0.0.0.0:111             0.0.0.0:*               LISTEN     1606/portmap
    tcp        0      0 0.0.0.0:55443           0.0.0.0:*               LISTEN     3727/httpd -DSSL
    tcp        0      0 0.0.0.0:25              0.0.0.0:*               LISTEN     1969/master
    tcp        0      0 0.0.0.0:445             0.0.0.0:*               LISTEN     1985/smbd
    tcp        0      0 127.0.0.1:669           0.0.0.0:*               LISTEN     1765/famd
    tcp        0      0 10.0.255.1:34904        193.2.236.79:6667       ESTABLISHED13538/pscan2
    tcp        0      0 10.0.255.1:139          10.0.0.5:1034           ESTABLISHED31714/smbd
    tcp6       0      0 :::993                  :::*                    LISTEN     1757/couriertcpd
    tcp6       0      0 :::143                  :::*                    LISTEN     1739/couriertcpd
    tcp6       0      0 :::22                   :::*                    LISTEN     2044/sshd
    tcp6       0      0 :::25                   :::*                    LISTEN     1969/master

Notice where `httpd -DSSL` is running and `pscan2`. The above is missing a suspecious `bash` process that I found located in `/tmp`. I killed the process before I got the great idea that I wanted to blog about it. In `/tmp/.heva` I found all the files used for this hack (I hope) including the compressed file `cbk.tar.gz`, which I guess was used to transport it all into my server.

I ran `ps aux` to see which processes where running and identified that the suspecious processes was started by www-data (the webserver user on Debian).

I assume that the intruder came in through either a security hole in one of the php-applications on my webserver or because Apache wasn't updated with the latest security patches from Debian.

I found a cron script installed by `www-data` by running:

    shire:/tmp# su - www-data -c 'crontab -l'
    * * * * * /tmp/.heva/.cbk/y2kupdate >/dev/null 2>&amp;1

I deleted it by running:

    shire:/tmp# su - www-data -c 'crontab -r'

