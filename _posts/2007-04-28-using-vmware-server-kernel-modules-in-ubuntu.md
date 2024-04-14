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
    http://archive.canonical.com feisty-commercial main\r\n\r\nYou also need
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
    VMware Server in the Ubuntu repositories my self [here][1].\r\n\r\n[1]: http://www.emcken.dk/weblog/archives/193-VMware-server-from-Ubuntu-official-repositories.html"
---
In the lastest version of Ubuntu (version 7.04 - Feisty Fawn) kernel modules for VMware Server is available in the package [vmware-server-kernel-modules][1] from Ubuntus package repository.
The VMware Server itself is at the moment not. You still need to install VMware Server from the tar.gz download from [www.vmware.com][6].

I found documentation on [how to make the VMware server kernel modules package work together with the VMware Server installed from tar.gz][2] in the [Ubuntu Documentation][3]. I also found the reference to this documentation on the [Ubuntu Forums][4].

The cool thing about this is that you don't need to run `vmware-config.pl` every time you update your kernel.

If you upgraded to 7.04 from a previous version of Ubuntu you might run into a problem where you can't get the vmware console to start. The output on the console will look something like this:

    /usr/lib/vmware/bin/vmware: /usr/lib/vmware/lib/libpng12.so.0/libpng12.so.0: no version information available (required by /usr/lib/libcairo.so.2)

I solved it by removing the following packages:

     sudo apt-get --purge remove libdbus-1-2 libnautilus-burn3

Don't worry new packages have taken their spots as far as I know:

    libdbus-1-3
    libnautilus-burn4

I found [a thread on the VMware forums][5] but my solution seemed easier :)

[1]: http://packages.ubuntulinux.org/feisty/devel/vmware-server-kernel-modules
[6]: http://www.vmware.com/products/server/
[2]: https://help.ubuntu.com/community/VMware/Server
[3]: https://help.ubuntu.com/
[4]: http://ubuntuforums.org/showthread.php?t=338305&amp;page=2
[5]: http://www.vmware.com/community/thread.jspa?messageID=484981

