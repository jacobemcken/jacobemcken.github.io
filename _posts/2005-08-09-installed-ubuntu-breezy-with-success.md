---
layout: post
status: publish
published: true
title: Installed Ubuntu Breezy with success
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
wordpress_id: 126
wordpress_url: http://emcken.dk/wp/archives/126-installed-ubuntu-breezy-with-success.html
date: '2005-08-09 21:28:00 +0200'
date_gmt: '2005-08-09 21:28:00 +0200'
categories:
- Ubuntu
tags: []
comments: false
---
...well it didn't come working out of the box :-D

1.  I installed Hoary from CD and upgraded Hoary packages from the internet (though I dont think upgrading was nessesary).
2.  Logged into a console (Alt+F1) and shutting down X:

        rivendell:~# /etc/init.d/gdm stop

3.  Changed all Hoary soruces to Breezy sources in `/etc/apt/sources.list`
4.  Then i dist-upgraded Hoary:

        rivendell:~# apt-get -u dist-upgrade

    I had to upgrade a few times before all packages was upgraded.
5.  Now I tried to configure X with:

        rivendell:~# dpkg-reconfigure xserver-xorg

    Didn't read what it was saying just pressing enter, enter, enter...

6.  Now I tried to start X:

        rivendell:~# /etc/init.d/gdm start

    But got the following errors in the logfile in `/var/log/gdm/:0.log`:

        sh: /usr/X11R6/lib/X11/xkb/xkbcomp: No such file or directory
        Couldn't compile keymap file
        (EE) Couldn't load XKB keymap, falling back to pre-XKB keymap

    And:

        Fatal server error:
        could not open default font 'fixed';
        the X server's font paths might be misconfigured, remote font server(s)
        may be unreachable, and/or local fonts may not be installed or are not
        configured correctly.

    **Note:** I had trouple using my keyboard at this point but I waas able to get through all the messages wit the 'd' key, and back into the console.

7.  Surfing the Ubuntuforums.org got me to the following:

        rivendell:~# apt-get install xkb-utils
        rivendell:~# apt-get install --reinstall xfonts-base

    `xkbutils` gives the needed binary `xkbcomp`.
    Actually I don't think I had xfonts-base installed at all after upgrading to Breezy, so the option `--reinstall` might not be nessessary.

8.  Now try restarting GDM and perhapes you are as lucky as me, and will now be greeted by the Ubuntu graphical login screen:

        rivendell:~# /etc/init.d/gdm restart

My computer is equiped with a fairly old Nvidia graphic card, dunno if that makes any difference.

