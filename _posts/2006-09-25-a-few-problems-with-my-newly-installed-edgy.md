---
layout: post
status: publish
published: true
title: A few problems with my newly installed Edgy
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
wordpress_id: 147
wordpress_url: http://emcken.dk/wp/archives/147-a-few-problems-with-my-newly-installed-edgy.html
date: '2006-09-25 10:53:51 +0200'
date_gmt: '2006-09-25 10:53:51 +0200'
categories:
- Ubuntu
tags: []
comments: false
---
## Wireless

After installing Edgy Eft a couple of days ago I noticed a weird behavior of Network Manager. My wireless card was identified as a wired network card after resuming from suspend. Today I got some time to search for the bug on Launchpad and it seems it is a [known bug][1]:

Anyways I added my comments, and will be following it closely.

**Update:** It seems there is a fix for the bug... I'm running with it now, and so far it seems fine.

## VMware

**Update:** I found out why VMware wouldn't start and made [a post about it][2].

I haven't been able to get VMware up and running yet. I get this error when trying to start VMware:

    je@rohan:~$ sudo /etc/init.d/vmware start
    Password:
    VMware Server is installed, but it has not been (correctly) configured
    for the running kernel. To (re-)configure it, invoke the
    following command: /usr/bin/vmware-config.pl.

The modules have been compiled for my current kernel and I'm sure it has been compiled with the same gcc version (if that have any effect?).

My kernel:

    uname -a
    Linux rohan 2.6.17-9-generic #2 SMP Fri Sep 22 10:41:59 UTC 2006 i686 GNU/Linux

Installed headers:

    dpkg -l |grep linux-headers-2.6.17-9-generic
    ii  linux-headers-2.6.17-9-generic            2.6.17-9.23

Kernel info:

    cat /proc/version
    Linux version 2.6.17-9-generic (root@rothera) (gcc version 4.1.2 20060920 (prerelease) (Ubuntu 4.1.1-13ubuntu3)) #2 SMP Fri Sep 22 10:41:59 UTC 2006 (Ubuntu 2.6.17-9.23-generic)

Compiler is gcc 4.1, which is the only one I have installed at the moment:

    ls -l /usr/bin/gcc
    lrwxrwxrwx 1 root root 7 2006-09-23 03:37 /usr/bin/gcc -> gcc-4.1

Running `/usr/bin/vmware-config.pl` as root (with sudo) doesn't report any errors. The install seems to be exactly like on Dapper.

I really cant figure out why it doesn't work.
But besides these 2 things, Edgy is looking great. I feel suspend and resume is a bit faster than with Dapper. Startup and login is faster. All other things looks the same to me... and that isn't a bad thing :)

[1]: https://launchpad.net/distros/ubuntu/+source/network-manager/+bug/59981
[2]: 2006-09-29-my-vmware-problem.md
