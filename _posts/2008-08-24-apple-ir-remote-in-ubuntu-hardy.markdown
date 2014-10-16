---
layout: post
status: publish
published: true
title: Apple IR remote in Ubuntu Hardy
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
wordpress_id: 209
wordpress_url: http://emcken.dk/wp/archives/209-apple-ir-remote-in-ubuntu-hardy.html
date: '2008-08-24 11:35:12 +0200'
date_gmt: '2008-08-24 11:35:12 +0200'
categories:
- Linux
tags: []
comments:
- id: 179
  author: Jacob Emcken
  author_email: jacob@emcken.dk
  author_url: http://www.emcken.dk/weblog/
  date: '2008-08-25 12:21:03 +0200'
  date_gmt: '2008-08-25 11:21:03 +0200'
  content: "I found a Blog entry about some of the same stuff:\r\n\r\nhttp:&#47;&#47;jaykinzer.blogspot.com&#47;2008&#47;02&#47;setting-up-remote-control-lirc.html\r\n\r\nThis
    guys use a tool in Ubuntu called `mythbuntu-lirc-generator` which can generate
    lircrc configuration for several applications."
- id: 180
  author: Russ
  author_email: ''
  author_url: ''
  date: '2008-09-20 06:09:53 +0200'
  date_gmt: '2008-09-20 05:09:53 +0200'
  content: DUDE! Holy crap it took me all night to find this page. Thank you, I couldn't
    get my mac mini remote working in Hardy otherwise, Thanks a million for posting
    this.
- id: 185
  author: Scaine
  author_email: scaine@scaine.net
  author_url: ''
  date: '2008-11-16 18:07:56 +0100'
  date_gmt: '2008-11-16 17:07:56 +0100'
  content: "Do you find that when you reboot your MacMini, it sometimes fails to load
    LIRC correctly?  I do and I've found that it's because I have two &#47;dev&#47;usb&#47;hiddev
    devices (0 and 1) and I need to choose one to go into hardware.conf.\r\n\r\nThe
    problem seems to be that the Mac internally will activate only one of these and
    if it's not the one I have in hardware.conf, I need to edit, then restart LIRC.
    \ It's minor, but it's irritating."
- id: 194
  author: Jacob Emcken
  author_email: jacob@emcken.dk
  author_url: http://www.emcken.dk/
  date: '2009-02-05 19:48:12 +0100'
  date_gmt: '2009-02-05 18:48:12 +0100'
  content: I rarely reboot the Mac Mini so I havn't noticed this.
- id: 471
  author: Roque Pinel
  author_email: repinel@gmail.com
  author_url: http://www.cos.ufrj.br/~repinel/
  date: '2011-02-05 17:36:44 +0100'
  date_gmt: '2011-02-05 16:36:44 +0100'
  content: |
    <p>Check out this GNOME applet.<&#47;p>

    <p>http:&#47;&#47;code.google.com&#47;p&#47;ir-switcher&#47;<&#47;p>
---
<p>This weekend I decided to try and get my Apple remote to work on my Mac mini which I just upgraded to Hardy the other day.</p>
<p>Configuring the package lirc you are able to select the apple remote from a list but I couldn't get it to work that way. So I found a way to create my own configuration file for the Apple remote:</p>
<p>    sudo irrecord -H macmini -d &#47;dev&#47;usb&#47;hiddev0 macmini.conf</p>
<p>-H tels irrecord which driver to use<br />
-d is the device irrecord listens for input on<br />
macmini.conf is the file where irrecord saves the configuration when done</p>
<p>First you need to hold a button. I had to wait almost 25 sec before it was satisfied and went on with the configuration. Now just follow the on screen instructions and in the end you should have you own configuration file. I recommend to compare the following file with the one you just created to check out the differences:</p>
<p>    &#47;usr&#47;share&#47;lirc&#47;remotes&#47;apple&#47;lircd.conf.macmini</p>
<p>I made sure to call my buttons the same as in `lircd.conf.macmini`.</p>
<p>After that I altered these two files to point to my newly created configuration file and removed pointers to `lircd.conf.macmini`:</p>
<p>    hardware.conf<br />
    lircd.conf</p>
<p>I then restarted lircd with:</p>
<p>    sudo &#47;etc&#47;init.d&#47;lirc restart</p>
<p>now you should be able to check that it is working with the command irw:</p>
<p>    irw</p>
<p>Just Ctrl+c when you are satisfied with the result.</p>
<p>At some point I would like to use my remote with [Elisa][1] which just [released a 0.5.6][2] which have DVD playback support, but for now I'll just stick to Totem just to try getting it to work.</p>
<p>To use the remote in Totem you first have to enable the IR plugin (Edit -> Plugins...). Then you have to configure what to buttons are supposed to do which is done in a configuration file in you home folder:</p>
<p>    ~&#47;.lircrc</p>
<p>Content could look something like this (read more [about syntax on LIRC's website][4]):</p>
<p>    begin<br />
        prog = Totem<br />
        remote = Apple_A1156<br />
        button = vol+<br />
        config = volume_up<br />
    end</p>
<p>Since the Apple remote have a combined Play&#47;Pause button I wanted to find out if that was possible. I had a bit of trouble figuring out what was going in the `config =` line and looked through all documentation I could find on LIRC's website. Suddenly it hit me that the values in config a specific for the application. Not much about this on the Totem website so I downloaded [the source code for Totem][3]. Don't worry this isn't going to be hardcore. I unpacked the source code and searched for files with the name lirc:</p>
<p>    $ cd totem-2.22.1<br />
    $ find . -name \*lirc\*<br />
    .&#47;data&#47;lirc_example<br />
    .&#47;src&#47;plugins&#47;lirc<br />
    .&#47;src&#47;plugins&#47;lirc&#47;totem-lirc.c<br />
    .&#47;src&#47;plugins&#47;lirc&#47;lirc.totem-plugin.in</p>
<p>I opened the file `totem-lirc.c` and found about 25 definitions for IR commands something like the following:</p>
<p>    #define TOTEM_IR_COMMAND_PLAY "play"<br />
    #define TOTEM_IR_COMMAND_PAUSE "pause"<br />
    #define TOTEM_IR_COMMAND_NEXT "next"<br />
    #define TOTEM_IR_COMMAND_PREVIOUS "previous"<br />
    #define TOTEM_IR_COMMAND_SEEK_FORWARD "seek_forward"<br />
    #define TOTEM_IR_COMMAND_SEEK_BACKWARD "seek_backward"<br />
    #define TOTEM_IR_COMMAND_VOLUME_UP "volume_up"<br />
    #define TOTEM_IR_COMMAND_VOLUME_DOWN "volume_down"<br />
    #define TOTEM_IR_COMMAND_FULLSCREEN "fullscreen"<br />
    #define TOTEM_IR_COMMAND_QUIT "quit"<br />
    #define TOTEM_IR_COMMAND_UP "up"<br />
    #define TOTEM_IR_COMMAND_DOWN "down"<br />
    #define TOTEM_IR_COMMAND_LEFT "left"<br />
    #define TOTEM_IR_COMMAND_RIGHT "right"<br />
    #define TOTEM_IR_COMMAND_SELECT "select"<br />
    #define TOTEM_IR_COMMAND_MENU "menu"<br />
    #define TOTEM_IR_COMMAND_PLAYPAUSE "play_pause"<br />
    #define TOTEM_IR_COMMAND_ZOOM_UP "zoom_up"<br />
    #define TOTEM_IR_COMMAND_ZOOM_DOWN "zoom_down"<br />
    #define TOTEM_IR_COMMAND_SHOW_PLAYING "show_playing"<br />
    #define TOTEM_IR_COMMAND_SHOW_VOLUME "show_volume"<br />
    #define TOTEM_IR_COMMAND_EJECT "eject"<br />
    #define TOTEM_IR_COMMAND_PLAY_DVD "play_dvd"<br />
    #define TOTEM_IR_COMMAND_MUTE "mute"<br />
    #define TOTEM_IR_COMMAND_TOGGLE_ASPECT "toggle_aspect"</p>
<p>Other multimedia software might be better docuemented when it comes to IR but I guess you would be able to use something similar to figure out what possible config-options you have in the lircrc for the software.</p>
<p>Anyways Totem have a Play&#47;Pause toggle and I ended up with:</p>
<p>    begin<br />
        prog = Totem<br />
        remote = Apple_A1156<br />
        button = play<br />
        config = play_pause<br />
    end</p>
<p>[1]: http:&#47;&#47;elisa.fluendo.com&#47;<br />
[2]: http:&#47;&#47;elisa.fluendo.com&#47;news&#47;12&#47;<br />
[3]: http:&#47;&#47;ftp.acc.umu.se&#47;pub&#47;GNOME&#47;sources&#47;totem&#47;<br />
[4]: http:&#47;&#47;www.lirc.org&#47;html&#47;configure.html#lircrc_format</p>
