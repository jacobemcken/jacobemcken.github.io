---
layout: post
status: publish
published: true
title: Playing around with software raid
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
wordpress_id: 157
wordpress_url: http://emcken.dk/wp/archives/157-playing-around-with-software-raid.html
date: '2006-10-17 13:06:00 +0200'
date_gmt: '2006-10-17 13:06:00 +0200'
categories:
- Work
- Linux
- Ubuntu
tags: []
comments:
- id: 96
  author: manu
  author_email: kamazu@gmx.de
  author_url: ''
  date: '2007-01-10 05:41:48 +0100'
  date_gmt: '2007-01-10 04:41:48 +0100'
  content: "Thank you, the md=auto really helped me!!\r\n\r\ncheers\r\nManu"
---
And you don't even need any physical disks for it....

I don't use Linux software raid tool `mdadm` that often so I quickly forget how it works. This is something I used on several occasions, when trying to refresh my mind. The cool thing is that you don't need physical disks or a lot of space for it to work.
The following might vary a bit depending on you system (mine is Ubuntu Edgy Eft on IBM x40).

First create a few "disks"... by creating some empty files and making them into block devices:

    dd if=/dev/zero of=disk1 bs=1M count=1 seek=30
    dd if=/dev/zero of=disk2 bs=1M count=1 seek=30
    dd if=/dev/zero of=disk3 bs=1M count=1 seek=30
    losetup /dev/loop0 disk1
    losetup /dev/loop1 disk2
    losetup /dev/loop2 disk3

This creates 3 files (`disk1`, `disk2` and `disk3`) with the size of 1MB in the current directory and makes them into block devices (just like normal disks is).

Now create your raid, example:

    mdadm --create /dev/md0 --level=5 --raid-devices=2 --spare-devices=1 /dev/loop0 /dev/loop1 /dev/loop2

If you get the error:

    mdadm: error opening /dev/md0: No such file or directory

Add the parameter `--auto=md` to the raid create command.

Now you can see you raid status with:

    cat /proc/mdstat

Now play around with it all you want

# Cleanup

When you are done you stop the raid and remove it with the following:

    mdadm --stop /dev/md0
    mdadm --remove /dev/md0

Perhapes you want to remove the `md0` device again with (only if you needed the `--auto=md` parameter:

    rm /dev/md0

Cleanup the "disks":

    losetup -d /dev/loop2
    losetup -d /dev/loop1
    losetup -d /dev/loop0
    rm disk3
    rm disk2
    rm disk1

Now you computer wont have a trace of you your software raid disks... besides you shell history :)

