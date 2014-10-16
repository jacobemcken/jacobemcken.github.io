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
<p>And you don't even need any physical disks for it....</p>
<p>I don't use Linux software raid tool `mdadm` that often so I quickly forget how it works. This is something I used on several occasions, when trying to refresh my mind. The cool thing is that you don't need physical disks or a lot of space for it to work.<br />
The following might vary a bit depending on you system (mine is Ubuntu Edgy Eft on IBM x40).</p>
<p>First create a few "disks"... by creating some empty files and making them into block devices:</p>
<p>    dd if=&#47;dev&#47;zero of=disk1 bs=1M count=1 seek=30<br />
    dd if=&#47;dev&#47;zero of=disk2 bs=1M count=1 seek=30<br />
    dd if=&#47;dev&#47;zero of=disk3 bs=1M count=1 seek=30<br />
    losetup &#47;dev&#47;loop0 disk1<br />
    losetup &#47;dev&#47;loop1 disk2<br />
    losetup &#47;dev&#47;loop2 disk3</p>
<p>This creates 3 files (`disk1`, `disk2` and `disk3`) with the size of 1MB in the current directory and makes them into block devices (just like normal disks is).</p>
<p>Now create your raid, example:</p>
<p>    mdadm --create &#47;dev&#47;md0 --level=5 --raid-devices=2 --spare-devices=1 &#47;dev&#47;loop0 &#47;dev&#47;loop1 &#47;dev&#47;loop2</p>
<p>If you get the error:</p>
<p>    mdadm: error opening &#47;dev&#47;md0: No such file or directory</p>
<p>Add the parameter `--auto=md` to the raid create command.</p>
<p>Now you can see you raid status with:</p>
<p>    cat &#47;proc&#47;mdstat</p>
<p>Now play around with it all you want</p>
<p># Cleanup</p>
<p>When you are done you stop the raid and remove it with the following:</p>
<p>    mdadm --stop &#47;dev&#47;md0<br />
    mdadm --remove &#47;dev&#47;md0</p>
<p>Perhapes you want to remove the `md0` device again with (only if you needed the `--auto=md` parameter:</p>
<p>    rm &#47;dev&#47;md0</p>
<p>Cleanup the "disks":</p>
<p>    losetup -d &#47;dev&#47;loop2<br />
    losetup -d &#47;dev&#47;loop1<br />
    losetup -d &#47;dev&#47;loop0<br />
    rm disk3<br />
    rm disk2<br />
    rm disk1</p>
<p>Now you computer wont have a trace of you your software raid disks... besides you shell history :)</p>
