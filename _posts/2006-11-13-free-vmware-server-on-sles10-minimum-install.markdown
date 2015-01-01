---
layout: post
status: publish
published: true
title: Free VMware server on SLES10 minimum install
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
wordpress_id: 170
wordpress_url: http://emcken.dk/wp/archives/170-free-vmware-server-on-sles10-minimum-install.html
date: '2006-11-13 14:01:17 +0100'
date_gmt: '2006-11-13 14:01:17 +0100'
categories:
- Work
- Linux
tags: []
comments: false
---
First install a minimum SLES10 (only using selectiong `Server Base`).

I have a few issues with the minimum install thing in SLES10 (I had kind of the same feeling with SLES9):

1.  Why do I have to use CD2 to install a 76 kilobyte `Zenwork Managemnet Daemon`. Why not put it on CD1? It seems really lame that you cannot make a minimum install with CD1.
2.  No I really don't want the `Network Mmanager` to manage my ethernet interface on my server... I want to give it a static ip. Ahhh I can disabled it. But why do SLES10 still want to install the network-manager package?... and worse all its dependencies is the reason why I choose the minimum install in the first place.
    Luckily after the install you can choose to use the old way of configuring network from within YAST.

Besides from that SLES10 have so far made a good impression on me.

I usually turn off the services `slpd` and `portmap`.

# Preparing server for VMware

Extra packages needed:

    xorg-x11-libs
    gcc
    kernel-source

Now install the the free `VMware Server` from [www.vmware.com][1]:

[1]: http://www.vmware.com/download/server/

