---
layout: post
status: publish
published: true
title: Packaging virt-manager for Ubuntu
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
wordpress_id: 197
wordpress_url: http://emcken.dk/wp/archives/197-packaging-virt-manager-for-ubuntu.html
date: '2007-06-04 10:33:07 +0200'
date_gmt: '2007-06-04 10:33:07 +0200'
categories:
- Ubuntu
- Virtualization
tags: []
comments:
- id: 143
  author: Jacob Emcken
  author_email: jacob@emcken.dk
  author_url: http://www.emcken.dk/weblog/
  date: '2007-07-14 20:07:07 +0200'
  date_gmt: '2007-07-14 19:07:07 +0200'
  content: Just so people who find this post know. `virt-manager` have been packaged
    for Ubuntu. I dont know if the packages have been accepted into Ubuntus official
    repository yet but I'm sure they will soon. Just follow the above link to the
    Ubuntu bug about virt-manager to learn more about the progress.
- id: 154
  author: Ahmad
  author_email: ahmadz@inet.sy
  author_url: ''
  date: '2007-12-16 22:20:25 +0100'
  date_gmt: '2007-12-16 21:20:25 +0100'
  content: "Hi, yesterday I tried to make a package for this program. I couldn't include
    all the dependences and remote host connection is disabled. So I think it will
    work only on Redhat distribution. \r\n\r\nThanks"
- id: 155
  author: Jacob Emcken
  author_email: jacob@emcken.dk
  author_url: http://www.emcken.dk/weblog/
  date: '2007-12-22 14:50:05 +0100'
  date_gmt: '2007-12-22 13:50:05 +0100'
  content: "Hi mate.\r\n\r\nI haven't tried this for a while but you should follow
    [bug 116897][] if you are interested in virt-manager for Ubuntu.\r\n\r\n[bug 116897]:
    https://bugs.launchpad.net/ubuntu/+bug/116897"
- id: 190
  author: Rob
  author_email: rob@hendelman.net
  author_url: ''
  date: '2008-12-23 22:43:08 +0100'
  date_gmt: '2008-12-23 21:43:08 +0100'
  content: "I found this post googling...I am trying to compile virt-manager 0.60.
    \ I found the way to get this working\r\n\r\nthis works for me\r\n\r\nexport PYTHON_VERSION=2.5
    && ./configure \r\n\r\nmake sure you have python-gtk2 & python-gtk2-dev"
- id: 191
  author: Plastic packaging
  author_email: giftdeals@yahoo.com
  author_url: http://www.vistatubes.com
  date: '2009-01-06 06:43:35 +0100'
  date_gmt: '2009-01-06 05:43:35 +0100'
  content: thanks for your advise i tried it today and it works great
---
Last week I attended a Xen course on SLES10 (SP1 RC5). We used [virt-manager][].
I looked for it in Ubuntu but wasn't able to find it. Instead I found this request for [packaging virt-manager for Ubuntu][1].

I have been trying to do it myself but it isn't as easy as I thought... I have never really tried packaging anything before (apart from my wallpapers which doesn't really have any dependencies or make files).

Virtual Machine Manager has the following dependancies (taken from `virt-manager` homepage):

    python >= 2.4
    pygtk2 >= 1.99.12-6
    gnome-python2-gconf >= 1.99.11-7
    libvirt-python >= 0.2.1
    dbus-python >= 0.61
    gnome-python-desktop >= 2.15.4
    libxml2-python >= 2.6.23
    vte >= 0.12.2
    virtinst >= 0.103.0

Identified corespondent packages in Ubuntu:

    python                  Version: 2.5.1-0ubuntu3
    python-gtk2             Version: 2.10.4-0ubuntu3
    python-gconf            Version: 2.18.0-0ubuntu1
    python-libvirt          Version: 0.1.8-0ubuntu2
    python-dbus             Version: 0.80.2-1ubuntu2
    python-gnome2-desktop   Version: 2.18.0-0ubuntu3
    python-libxml2          Version: 2.6.27.dfsg-1ubuntu3
    libvte9                 Version: 1:0.16.1-0ubuntu1

    virtinst (downloadable from `virt-manager`'s homepage)

I guess `virtinst` would need packaging as well... [download `virtinst`][2].
The version of `phyton-libvirt` isn't new enough either.

I have been looking at a [Ubuntu packaging guide][3] but have already run into trouble (even before getting to phyton-libvirt):

    ...
    checking for PYGTK2... configure: error: Package requirements (pygtk-2.0 >= 1.99.11) were not met:

    No package 'pygtk-2.0' found

    Consider adjusting the PKG_CONFIG_PATH environment variable if you
    installed software in a non-standard prefix.

    Alternatively, you may set the environment variables PYGTK2_CFLAGS
    and PYGTK2_LIBS to avoid the need to call pkg-config.
    See the pkg-config man page for more details.

    make: *** [config.status] Error 1

[virt-manager]: http://virt-manager.et.redhat.com/
[1]: https://bugs.launchpad.net/ubuntu/+bug/116897/
[2]: http://virt-manager.et.redhat.com/download/sources/virtinst/virtinst-0.103.0.tar.gz
[3]: http://ubuntuforums.org/showthread.php?t=51003

