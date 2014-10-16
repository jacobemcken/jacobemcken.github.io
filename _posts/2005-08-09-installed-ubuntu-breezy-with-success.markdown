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
comments: []
---
<p>...well it didn't come working out of the box :-D</p>
<p>1.  I installed Hoary from CD and upgraded Hoary packages from the internet (though I dont think upgrading was nessesary).<br />
2.  Logged into a console (Alt+F1) and shutting down X:</p>
<p>        rivendell:~# &#47;etc&#47;init.d&#47;gdm stop</p>
<p>3.  Changed all Hoary soruces to Breezy sources in `&#47;etc&#47;apt&#47;sources.list`<br />
4.  Then i dist-upgraded Hoary:</p>
<p>        rivendell:~# apt-get -u dist-upgrade</p>
<p>    I had to upgrade a few times before all packages was upgraded.<br />
5.  Now I tried to configure X with:</p>
<p>        rivendell:~# dpkg-reconfigure xserver-xorg</p>
<p>    Didn't read what it was saying just pressing enter, enter, enter...</p>
<p>6.  Now I tried to start X:</p>
<p>        rivendell:~# &#47;etc&#47;init.d&#47;gdm start</p>
<p>    But got the following errors in the logfile in `&#47;var&#47;log&#47;gdm&#47;:0.log`:</p>
<p>        sh: &#47;usr&#47;X11R6&#47;lib&#47;X11&#47;xkb&#47;xkbcomp: No such file or directory<br />
        Couldn't compile keymap file<br />
        (EE) Couldn't load XKB keymap, falling back to pre-XKB keymap</p>
<p>    And:</p>
<p>        Fatal server error:<br />
        could not open default font 'fixed';<br />
        the X server's font paths might be misconfigured, remote font server(s)<br />
        may be unreachable, and&#47;or local fonts may not be installed or are not<br />
        configured correctly.</p>
<p>    **Note:** I had trouple using my keyboard at this point but I waas able to get through all the messages wit the 'd' key, and back into the console.</p>
<p>7.  Surfing the Ubuntuforums.org got me to the following:</p>
<p>        rivendell:~# apt-get install xkb-utils<br />
        rivendell:~# apt-get install --reinstall xfonts-base</p>
<p>    `xkbutils` gives the needed binary `xkbcomp`.<br />
    Actually I don't think I had xfonts-base installed at all after upgrading to Breezy, so the option `--reinstall` might not be nessessary.</p>
<p>8.  Now try restarting GDM and perhapes you are as lucky as me, and will now be greeted by the Ubuntu graphical login screen:</p>
<p>        rivendell:~# &#47;etc&#47;init.d&#47;gdm restart</p>
<p>My computer is equiped with a fairly old Nvidia graphic card, dunno if that makes any difference.</p>
