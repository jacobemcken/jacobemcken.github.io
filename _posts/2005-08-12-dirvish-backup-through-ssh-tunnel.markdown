---
layout: post
status: publish
published: true
title: Dirvish backup through ssh tunnel
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
wordpress_id: 129
wordpress_url: http://emcken.dk/wp/archives/129-dirvish-backup-through-ssh-tunnel.html
date: '2005-08-12 21:53:19 +0200'
date_gmt: '2005-08-12 21:53:19 +0200'
categories:
- Work
- Linux
tags: []
comments:
- id: 181
  author: loko
  author_email: loko@loko.com
  author_url: ''
  date: '2008-10-19 07:37:26 +0200'
  date_gmt: '2008-10-19 06:37:26 +0200'
  content: 'Culd you, please explain a little bit more each step and what are we doing.
    To begin with: Are we backingup to or from.....'
- id: 184
  author: Jacob Emcken
  author_email: jacob@emcken.dk
  author_url: http://www.emcken.dk/weblog/
  date: '2008-11-06 17:31:11 +0100'
  date_gmt: '2008-11-06 16:31:11 +0100'
  content: With Dirvish you always pull backup from a remote (or local) machine. In
    this example the back server which pulls the backup is rivendell. The server I'm
    connecting to (or through) is fw.emcken.dk
---
<p>For some time now I have been trying to switch to [Dirvish][1] (from rdiff-backup) mostly because you can use a limit bandwidth which is a very important factor for me. The following is an example of configuring dirvish to use a ssh tunnel to backup through.</p>
<p>I assume you have created a master config file `&#47;etc&#47;dirvish&#47;master.conf`.<br />
Lets jump right into configuring a vault `&#47;dirvish&#47;default.conf`:</p>
<p>    client: root@localhost<br />
    tree: &#47;<br />
    xdev: 0<br />
    index: gzip<br />
    image-default: %Y%m%d<br />
    exclude:<br />
        var&#47;cache&#47;apt&#47;archives<br />
        var&#47;cache&#47;man<br />
        tmp<br />
        var&#47;tmp</p>
<p>**Note: [xdev needs to be 0][2]**, not false, no, off or anything like it. (I was tricked by this because the [dirvish for Debian guide][3] uses the value true for xdev which AFAIK is wrong.</p>
<p>All this until now is standard dirvish stuff. Now comes the tunnel part:</p>
<p>    pre-server: ssh -f -L 20014:rivendell:22 root@fw.emcken.dk sleep 14400<br />
    rsh: ssh -o HostKeyAlias=1114 -p 20014</p>
<p>`pre-server` is a command to run before the backup starts. The command above will create a ssh tunnel through fw.emcken.dk to rivendell on port 20014 and go into the background. The `sleep 14400` keeps the tunnel open for 4 hours when idle. Don't worry!... your backup won't be terminated if it is still in progress after 4 hours. The tunnel will be kept open for as long as the runnel is in use. The remote execution is just an ugly hack to ensure that the tunnel is automatically closed after use.</p>
<p>`rsh` is the important stuff. The `-o HostKeyAlias=1114` makes us able to connect to localhost without being told that someone might be trying to do a 'man-in-the-middle attack' and refuse to connect to the tunnel. But to use HostKeyAlias we need to specify this in `&#47;root&#47;.ssh&#47;config` more on this further down.<br />
`-p 20014` specifies the port we want to connect to.</p>
<p>The following might be useful when making backups over the internet:</p>
<p>    zxfer: true<br />
    speed-limit: 90</p>
<p>`zxfer` compress all data transmitted and `speed-limit` limits the bandwidth that dirvish will use.</p>
<p>Now to complete the setup the machine being backed up is defined in `&#47;root&#47;.ssh&#47;config`:</p>
<p>    Host rivendell<br />
    Port 20014<br />
    HostKeyAlias 1114</p>
<p>This is placed in root's home dir because (on my system) root (cron) runs my backups. It is possible to create a separate user for it but I didn't think it nessesary.</p>
<p>Now when you have copied your public ssh key to the machine that is going to be backed up, you are all set.</p>
<p>[1]: http:&#47;&#47;www.dirvish.org&#47;<br />
[2]: http:&#47;&#47;www.dirvish.org&#47;pipermail&#47;dirvish&#47;2005-February&#47;000096.html<br />
[3]: http:&#47;&#47;www.dirvish.org&#47;debian.howto.html</p>
