---
layout: post
status: publish
published: true
title: Solution to vmnet1 down after suspend
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
wordpress_id: 168
wordpress_url: http://emcken.dk/wp/archives/168-solution-to-vmnet1-down-after-suspend.html
date: '2006-11-02 20:18:22 +0100'
date_gmt: '2006-11-02 20:18:22 +0100'
categories:
- Random hacks
- Ubuntu
tags: []
comments:
- id: 78
  author: Fredrik Espinoza
  author_email: ''
  author_url: ''
  date: '2006-12-05 09:51:49 +0100'
  date_gmt: '2006-12-05 08:51:49 +0100'
  content: This did the trick for me, thanks!
- id: 89
  author: Laurent
  author_email: hl.cd@laposte.net
  author_url: ''
  date: '2006-12-30 18:24:58 +0100'
  date_gmt: '2006-12-30 17:24:58 +0100'
  content: "Thank you very much for this tip !\r\n\r\nA Ubuntu and Archlinux lover."
---
I rarely shut down my laptop, but I use suspend all the time. Right now I have like 14 days of "uptime" which would have been a lot more if it wasn't because I just installed Edgy :) I have VMware Server installed on it which I use for my work. I have all the machines on a `host only` network which works just great. But every time I suspend my laptop the virtually device `vmnet1` seems to "go down" and I have to make a:

    sudo ip link set vmnet1 up

to be able to connect from my laptop to the VMware machines again.

Now I created a file at the following location:

    /etc/acpi/resume.d/89-enable-vmware-host-only-net.sh

With the following content:

    #!/bin/sh

    ip link set vmnet1 up

Now I don't have do it manually any more, horay :-D

