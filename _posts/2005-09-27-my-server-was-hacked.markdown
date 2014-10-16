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
comments: []
---
<p>Yesterday evening while struggeling to keep up with work I noticed my internet connection was acting strangly. I checked my server (which have a fairly small amount of visitors) to see who the visitor was and noticed that I had a strange connection:</p>
<p>    shire:&#47;var&#47;www# netstat -tanp<br />
    Active Internet connections (servers and established)<br />
    Proto Recv-Q Send-Q Local Address           Foreign Address         State       PID&#47;Program name<br />
    tcp        0      0 0.0.0.0:37              0.0.0.0:*               LISTEN     1770&#47;inetd<br />
    tcp        0      0 0.0.0.0:31337           0.0.0.0:*               LISTEN     3694&#47;httpd -DSSL<br />
    tcp        0      0 0.0.0.0:9               0.0.0.0:*               LISTEN     1770&#47;inetd<br />
    tcp        0      0 127.0.0.1:3306          0.0.0.0:*               LISTEN     1822&#47;mysqld<br />
    tcp        0      0 0.0.0.0:139             0.0.0.0:*               LISTEN     1985&#47;smbd<br />
    tcp        0      0 0.0.0.0:13              0.0.0.0:*               LISTEN     1770&#47;inetd<br />
    tcp        0      0 0.0.0.0:111             0.0.0.0:*               LISTEN     1606&#47;portmap<br />
    tcp        0      0 0.0.0.0:55443           0.0.0.0:*               LISTEN     3727&#47;httpd -DSSL<br />
    tcp        0      0 0.0.0.0:25              0.0.0.0:*               LISTEN     1969&#47;master<br />
    tcp        0      0 0.0.0.0:445             0.0.0.0:*               LISTEN     1985&#47;smbd<br />
    tcp        0      0 127.0.0.1:669           0.0.0.0:*               LISTEN     1765&#47;famd<br />
    tcp        0      0 10.0.255.1:34904        193.2.236.79:6667       ESTABLISHED13538&#47;pscan2<br />
    tcp        0      0 10.0.255.1:139          10.0.0.5:1034           ESTABLISHED31714&#47;smbd<br />
    tcp6       0      0 :::993                  :::*                    LISTEN     1757&#47;couriertcpd<br />
    tcp6       0      0 :::143                  :::*                    LISTEN     1739&#47;couriertcpd<br />
    tcp6       0      0 :::22                   :::*                    LISTEN     2044&#47;sshd<br />
    tcp6       0      0 :::25                   :::*                    LISTEN     1969&#47;master</p>
<p>Notice where `httpd -DSSL` is running and `pscan2`. The above is missing a suspecious `bash` process that I found located in `&#47;tmp`. I killed the process before I got the great idea that I wanted to blog about it. In `&#47;tmp&#47;.heva` I found all the files used for this hack (I hope) including the compressed file `cbk.tar.gz`, which I guess was used to transport it all into my server.</p>
<p>I ran `ps aux` to see which processes where running and identified that the suspecious processes was started by www-data (the webserver user on Debian).</p>
<p>I assume that the intruder came in through either a security hole in one of the php-applications on my webserver or because Apache wasn't updated with the latest security patches from Debian.</p>
<p>I found a cron script installed by `www-data` by running:</p>
<p>    shire:&#47;tmp# su - www-data -c 'crontab -l'<br />
    * * * * * &#47;tmp&#47;.heva&#47;.cbk&#47;y2kupdate >&#47;dev&#47;null 2>&amp;1</p>
<p>I deleted it by running:</p>
<p>    shire:&#47;tmp# su - www-data -c 'crontab -r'</p>
