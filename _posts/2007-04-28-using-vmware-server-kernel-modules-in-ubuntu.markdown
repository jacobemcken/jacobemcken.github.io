---
layout: post
status: publish
published: true
title: Using vmware-server-kernel-modules in Ubuntu
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
wordpress_id: 191
wordpress_url: http://emcken.dk/wp/archives/191-using-vmware-server-kernel-modules-in-ubuntu.html
date: '2007-04-28 10:29:50 +0200'
date_gmt: '2007-04-28 10:29:50 +0200'
categories:
- Ubuntu
- Virtualization
tags: []
comments:
- id: 134
  author: Harald
  author_email: chk@cfrq.net
  author_url: http://blog.cfrq.net/chk/
  date: '2007-06-09 19:32:04 +0200'
  date_gmt: '2007-06-09 18:32:04 +0200'
  content: "You can now use the feisty commercial repository to get VMware from canonical
    in the \"vmware-server\" package:\r\n\r\n# Canonical Commercial packages\r\ndeb
    http:&#47;&#47;archive.canonical.com feisty-commercial main\r\n\r\nYou also need
    to add \"multiverse\" packages to get \"vmware-server-kernel-modules\", upon which
    vmware-server depends."
- id: 141
  author: Jacob Emcken
  author_email: jacob@emcken.dk
  author_url: http://www.emcken.dk/weblog/
  date: '2007-07-14 19:35:05 +0200'
  date_gmt: '2007-07-14 18:35:05 +0200'
  content: "Yeah I know :)\r\n\r\nBut I didn't know at that time. Thanks for posting
    about it here so people who find this post will also know. I wrote a post about
    VMware Server in the Ubuntu repositories my self [here][1].\r\n\r\n[1]: http:&#47;&#47;www.emcken.dk&#47;weblog&#47;archives&#47;193-VMware-server-from-Ubuntu-official-repositories.html"
---
<p>In the lastest version of Ubuntu (version 7.04 - Feisty Fawn) kernel modules for VMware Server is available in the package [vmware-server-kernel-modules][1] from Ubuntus package repository.<br />
The VMware Server itself is at the moment not. You still need to install VMware Server from the tar.gz download from [www.vmware.com][6].</p>
<p>I found documentation on [how to make the VMware server kernel modules package work together with the VMware Server installed from tar.gz][2] in the [Ubuntu Documentation][3]. I also found the reference to this documentation on the [Ubuntu Forums][4].</p>
<p>The cool thing about this is that you don't need to run `vmware-config.pl` every time you update your kernel.</p>
<p>If you upgraded to 7.04 from a previous version of Ubuntu you might run into a problem where you can't get the vmware console to start. The output on the console will look something like this:</p>
<p>    &#47;usr&#47;lib&#47;vmware&#47;bin&#47;vmware: &#47;usr&#47;lib&#47;vmware&#47;lib&#47;libpng12.so.0&#47;libpng12.so.0: no version information available (required by &#47;usr&#47;lib&#47;libcairo.so.2)</p>
<p>I solved it by removing the following packages:</p>
<p>     sudo apt-get --purge remove libdbus-1-2 libnautilus-burn3</p>
<p>Don't worry new packages have taken their spots as far as I know:</p>
<p>    libdbus-1-3<br />
    libnautilus-burn4</p>
<p>I found [a thread on the VMware forums][5] but my solution seemed easier :)</p>
<p>[1]: http:&#47;&#47;packages.ubuntulinux.org&#47;feisty&#47;devel&#47;vmware-server-kernel-modules<br />
[6]: http:&#47;&#47;www.vmware.com&#47;products&#47;server&#47;<br />
[2]: https:&#47;&#47;help.ubuntu.com&#47;community&#47;VMware&#47;Server<br />
[3]: https:&#47;&#47;help.ubuntu.com&#47;<br />
[4]: http:&#47;&#47;ubuntuforums.org&#47;showthread.php?t=338305&amp;page=2<br />
[5]: http:&#47;&#47;www.vmware.com&#47;community&#47;thread.jspa?messageID=484981</p>
