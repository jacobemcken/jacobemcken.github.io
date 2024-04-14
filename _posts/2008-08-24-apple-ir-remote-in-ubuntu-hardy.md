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
  content: "I found a Blog entry about some of the same stuff:\r\n\r\nhttp://jaykinzer.blogspot.com/2008/02/setting-up-remote-control-lirc.html\r\n\r\nThis
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
    LIRC correctly?  I do and I've found that it's because I have two /dev/usb/hiddev
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
    Check out this GNOME applet.


    http://code.google.com/p/ir-switcher/

---
This weekend I decided to try and get my Apple remote to work on my Mac mini which I just upgraded to Hardy the other day.

Configuring the package lirc you are able to select the apple remote from a list but I couldn't get it to work that way. So I found a way to create my own configuration file for the Apple remote:

    sudo irrecord -H macmini -d /dev/usb/hiddev0 macmini.conf

-H tels irrecord which driver to use
-d is the device irrecord listens for input on
macmini.conf is the file where irrecord saves the configuration when done

First you need to hold a button. I had to wait almost 25 sec before it was satisfied and went on with the configuration. Now just follow the on screen instructions and in the end you should have you own configuration file. I recommend to compare the following file with the one you just created to check out the differences:

    /usr/share/lirc/remotes/apple/lircd.conf.macmini

I made sure to call my buttons the same as in `lircd.conf.macmini`.

After that I altered these two files to point to my newly created configuration file and removed pointers to `lircd.conf.macmini`:

    hardware.conf
    lircd.conf

I then restarted lircd with:

    sudo /etc/init.d/lirc restart

now you should be able to check that it is working with the command irw:

    irw

Just Ctrl+c when you are satisfied with the result.

At some point I would like to use my remote with [Elisa][1] which just [released a 0.5.6][2] which have DVD playback support, but for now I'll just stick to Totem just to try getting it to work.

To use the remote in Totem you first have to enable the IR plugin (Edit -> Plugins...). Then you have to configure what to buttons are supposed to do which is done in a configuration file in you home folder:

    ~/.lircrc

Content could look something like this (read more [about syntax on LIRC's website][4]):

    begin
        prog = Totem
        remote = Apple_A1156
        button = vol+
        config = volume_up
    end

Since the Apple remote have a combined Play/Pause button I wanted to find out if that was possible. I had a bit of trouble figuring out what was going in the `config =` line and looked through all documentation I could find on LIRC's website. Suddenly it hit me that the values in config a specific for the application. Not much about this on the Totem website so I downloaded [the source code for Totem][3]. Don't worry this isn't going to be hardcore. I unpacked the source code and searched for files with the name lirc:

    $ cd totem-2.22.1
    $ find . -name \*lirc\*
    ./data/lirc_example
    ./src/plugins/lirc
    ./src/plugins/lirc/totem-lirc.c
    ./src/plugins/lirc/lirc.totem-plugin.in

I opened the file `totem-lirc.c` and found about 25 definitions for IR commands something like the following:

    #define TOTEM_IR_COMMAND_PLAY "play"
    #define TOTEM_IR_COMMAND_PAUSE "pause"
    #define TOTEM_IR_COMMAND_NEXT "next"
    #define TOTEM_IR_COMMAND_PREVIOUS "previous"
    #define TOTEM_IR_COMMAND_SEEK_FORWARD "seek_forward"
    #define TOTEM_IR_COMMAND_SEEK_BACKWARD "seek_backward"
    #define TOTEM_IR_COMMAND_VOLUME_UP "volume_up"
    #define TOTEM_IR_COMMAND_VOLUME_DOWN "volume_down"
    #define TOTEM_IR_COMMAND_FULLSCREEN "fullscreen"
    #define TOTEM_IR_COMMAND_QUIT "quit"
    #define TOTEM_IR_COMMAND_UP "up"
    #define TOTEM_IR_COMMAND_DOWN "down"
    #define TOTEM_IR_COMMAND_LEFT "left"
    #define TOTEM_IR_COMMAND_RIGHT "right"
    #define TOTEM_IR_COMMAND_SELECT "select"
    #define TOTEM_IR_COMMAND_MENU "menu"
    #define TOTEM_IR_COMMAND_PLAYPAUSE "play_pause"
    #define TOTEM_IR_COMMAND_ZOOM_UP "zoom_up"
    #define TOTEM_IR_COMMAND_ZOOM_DOWN "zoom_down"
    #define TOTEM_IR_COMMAND_SHOW_PLAYING "show_playing"
    #define TOTEM_IR_COMMAND_SHOW_VOLUME "show_volume"
    #define TOTEM_IR_COMMAND_EJECT "eject"
    #define TOTEM_IR_COMMAND_PLAY_DVD "play_dvd"
    #define TOTEM_IR_COMMAND_MUTE "mute"
    #define TOTEM_IR_COMMAND_TOGGLE_ASPECT "toggle_aspect"

Other multimedia software might be better docuemented when it comes to IR but I guess you would be able to use something similar to figure out what possible config-options you have in the lircrc for the software.

Anyways Totem have a Play/Pause toggle and I ended up with:

    begin
        prog = Totem
        remote = Apple_A1156
        button = play
        config = play_pause
    end

[1]: http://elisa.fluendo.com/
[2]: http://elisa.fluendo.com/news/12/
[3]: http://ftp.acc.umu.se/pub/GNOME/sources/totem/
[4]: http://www.lirc.org/html/configure.html#lircrc_format

