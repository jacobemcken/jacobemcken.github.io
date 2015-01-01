---
layout: post
status: publish
published: true
title: Ubuntu (and Linux in general) proving its worth
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
wordpress_id: 172
wordpress_url: http://emcken.dk/wp/archives/172-ubuntu-and-linux-in-general-proving-its-worth.html
date: '2006-12-16 12:52:14 +0100'
date_gmt: '2006-12-16 12:52:14 +0100'
categories:
- Ubuntu
tags: []
comments: false
---
Yesterday I updated my kernel on my laptop after a record uptime (for me on my laptop anyways ;)):

    16:26:58 up 42 days,  7:21,  3 users,  load average: 1.12, 1.46, 1.04

42 days without an update to the kernel in Ubuntu... I dunno if thats good or bad. But one thing that is really cool is that in all this time I used my suspend to ram function, and the laptop haven't died on me once. It occasionally did in all the previous versions of Ubuntu. So I just wanna send a kudos to the developers.

<img width='262' height='304' style="float: right;border: 0px;padding-left: 5px;padding-right: 5px" src="/weblog/uploads/mmcdisk.png" alt="" />Whenever the update manager notifies me about upgrades I usually just install it, though with a kernel update I try to push it to avoid rebooting. But this time I was excited because I noticed that the kernel apparently had a fix for the [bug reading large SD cards][1] I mentioned in [an earlier post][2].

I can confirm that this is now fixed... YAY!

Anyways... im now gonna put [an encrypted file system on the SD card][3] for my gpg key, ssh key, VPN keys etc. I hope I can give it another label as well.

[1]: https://launchpad.net/distros/ubuntu/+source/linux-source-2.6.17/+bug/61758
[2]: http://www.emcken.dk/weblog/archives/169-Large-SD-card-hangs-Ubuntu-Edgy.html
[3]: http://www.emcken.dk/weblog/archives/164-Encrypted-USB-drive-in-Ubuntu.html

