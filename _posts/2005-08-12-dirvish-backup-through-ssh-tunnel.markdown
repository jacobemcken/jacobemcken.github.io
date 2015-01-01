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
For some time now I have been trying to switch to [Dirvish][1] (from rdiff-backup) mostly because you can use a limit bandwidth which is a very important factor for me. The following is an example of configuring dirvish to use a ssh tunnel to backup through.

I assume you have created a master config file `/etc/dirvish/master.conf`.
Lets jump right into configuring a vault `/dirvish/default.conf`:

    client: root@localhost
    tree: /
    xdev: 0
    index: gzip
    image-default: %Y%m%d
    exclude:
        var/cache/apt/archives
        var/cache/man
        tmp
        var/tmp

**Note: [xdev needs to be 0][2]**, not false, no, off or anything like it. (I was tricked by this because the [dirvish for Debian guide][3] uses the value true for xdev which AFAIK is wrong.

All this until now is standard dirvish stuff. Now comes the tunnel part:

    pre-server: ssh -f -L 20014:rivendell:22 root@fw.emcken.dk sleep 14400
    rsh: ssh -o HostKeyAlias=1114 -p 20014

`pre-server` is a command to run before the backup starts. The command above will create a ssh tunnel through fw.emcken.dk to rivendell on port 20014 and go into the background. The `sleep 14400` keeps the tunnel open for 4 hours when idle. Don't worry!... your backup won't be terminated if it is still in progress after 4 hours. The tunnel will be kept open for as long as the runnel is in use. The remote execution is just an ugly hack to ensure that the tunnel is automatically closed after use.

`rsh` is the important stuff. The `-o HostKeyAlias=1114` makes us able to connect to localhost without being told that someone might be trying to do a 'man-in-the-middle attack' and refuse to connect to the tunnel. But to use HostKeyAlias we need to specify this in `/root/.ssh/config` more on this further down.
`-p 20014` specifies the port we want to connect to.

The following might be useful when making backups over the internet:

    zxfer: true
    speed-limit: 90

`zxfer` compress all data transmitted and `speed-limit` limits the bandwidth that dirvish will use.

Now to complete the setup the machine being backed up is defined in `/root/.ssh/config`:

    Host rivendell
    Port 20014
    HostKeyAlias 1114

This is placed in root's home dir because (on my system) root (cron) runs my backups. It is possible to create a separate user for it but I didn't think it nessesary.

Now when you have copied your public ssh key to the machine that is going to be backed up, you are all set.

[1]: http://www.dirvish.org/
[2]: http://www.dirvish.org/pipermail/dirvish/2005-February/000096.html
[3]: http://www.dirvish.org/debian.howto.html

