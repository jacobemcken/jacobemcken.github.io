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
comments: []
---
<p>## Wireless</p>
<p>After installing Edgy Eft a couple of days ago I noticed a weird behavior of Network Manager. My wireless card was identified as a wired network card after resuming from suspend. Today I got some time to search for the bug on Launchpad and it seems it is a [known bug][1]:</p>
<p>Anyways I added my comments, and will be following it closely.</p>
<p>**Update:** It seems there is a fix for the bug... I'm running with it now, and so far it seems fine.</p>
<p>## VMware</p>
<p>**Update:** I found out why VMware wouldn't start and made [a post about it][2].</p>
<p>I haven't been able to get VMware up and running yet. I get this error when trying to start VMware:</p>
<p>    je@rohan:~$ sudo &#47;etc&#47;init.d&#47;vmware start<br />
    Password:<br />
    VMware Server is installed, but it has not been (correctly) configured<br />
    for the running kernel. To (re-)configure it, invoke the<br />
    following command: &#47;usr&#47;bin&#47;vmware-config.pl.</p>
<p>The modules have been compiled for my current kernel and I'm sure it has been compiled with the same gcc version (if that have any effect?).</p>
<p>My kernel:</p>
<p>    uname -a<br />
    Linux rohan 2.6.17-9-generic #2 SMP Fri Sep 22 10:41:59 UTC 2006 i686 GNU&#47;Linux</p>
<p>Installed headers:</p>
<p>    dpkg -l |grep linux-headers-2.6.17-9-generic<br />
    ii  linux-headers-2.6.17-9-generic            2.6.17-9.23</p>
<p>Kernel info:</p>
<p>    cat &#47;proc&#47;version<br />
    Linux version 2.6.17-9-generic (root@rothera) (gcc version 4.1.2 20060920 (prerelease) (Ubuntu 4.1.1-13ubuntu3)) #2 SMP Fri Sep 22 10:41:59 UTC 2006 (Ubuntu 2.6.17-9.23-generic)</p>
<p>Compiler is gcc 4.1, which is the only one I have installed at the moment:</p>
<p>    ls -l &#47;usr&#47;bin&#47;gcc<br />
    lrwxrwxrwx 1 root root 7 2006-09-23 03:37 &#47;usr&#47;bin&#47;gcc -> gcc-4.1</p>
<p>Running `&#47;usr&#47;bin&#47;vmware-config.pl` as root (with sudo) doesn't report any errors. The install seems to be exactly like on Dapper.</p>
<p>I really cant figure out why it doesn't work.<br />
But besides these 2 things, Edgy is looking great. I feel suspend and resume is a bit faster than with Dapper. Startup and login is faster. All other things looks the same to me... and that isn't a bad thing :)</p>
<p>[1]: https:&#47;&#47;launchpad.net&#47;distros&#47;ubuntu&#47;+source&#47;network-manager&#47;+bug&#47;59981<br />
[2]: http:&#47;&#47;www.emcken.dk&#47;weblog&#47;archives&#47;149-My-VMware-problem.html</p>
