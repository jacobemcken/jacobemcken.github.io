---
layout: post
status: publish
published: true
title: '"My" VMware problem'
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
wordpress_id: 149
wordpress_url: http://emcken.dk/wp/archives/149-my-vmware-problem.html
date: '2006-09-29 20:50:47 +0200'
date_gmt: '2006-09-29 20:50:47 +0200'
categories:
- Linux
tags:
- vmware
comments:
- id: 57
  author: Josh
  author_email: josh@ubuntuos.com
  author_url: ubuntuos.com
  date: '2006-11-11 10:54:17 +0100'
  date_gmt: '2006-11-11 09:54:17 +0100'
  content: "I just want to say thanks for this.\r\n\r\nI have a wireless card and
    Edgy, and I had the exact same problem trying to get vmware to work.\r\n\r\nI
    thought it may have had something with my wireless card (as it failed when finished
    configuring), and I was pleased when I found this post.\r\n\r\nThanks :)"
---
Now I researched my VMware problem some more, and it has nothing to do with the link I posted in my last entry. Even though I had 2 versions of dbus installed at some point I think I actually removed the old version (a few hours) before installing VMware.

I looked at the start scripts and found that it checks for a file called `/etc/vmware/not_configured`, and if it exsist I refuses to start. After er manually removed it and loaded the kernel modules I actually got it all started, installed Windows XP, and rejoiced. It worked!! I could start my virtual machines.

... until my next reboot.

Now the `not_configured`-file was there again?

After "debugging" the vmware start-script I found that it failed when it bridged my ath0 -> vmnet0. I had also bridged my eth0 -> vmnet2.

I wasn't able to remove the ath0 -> vmnet0 bridge with `vmware-configure.pl` (or I couldn't figure out how), so I uninstalled with `vmware-uninstall.pl` and reinstalled, this time not bridgeing ath0. Now it works. Exactly why I doesn't like my ath0 device seems to be the atheros drivers... but who knows. A search on the words "[bridged network ath0][1]" on VMwares forums gives a few resultsI can live with this for now.

[1]: http://www.vmware.com/community/search.jspa?q=bridged+network+ath0

