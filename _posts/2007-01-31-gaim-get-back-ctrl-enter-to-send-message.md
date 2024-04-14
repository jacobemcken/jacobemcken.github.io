---
layout: post
status: publish
published: true
title: 'Gaim: get back Ctrl + Enter to send message'
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
wordpress_id: 180
wordpress_url: http://emcken.dk/wp/archives/180-gaim-get-back-ctrl-enter-to-send-message.html
date: '2007-01-31 11:57:45 +0100'
date_gmt: '2007-01-31 11:57:45 +0100'
categories:
- Linux
tags: []
comments: false
---
Some time ago I think it was when I made the switch from Gaim 1.x to 2.x sending my instant messages with Ctrl + Enter stopped working. I have lived with it for along time but it kept annoying me...  a good friend of mine found [the solution on another website][1] and today I took the time to actually do it.

The thing beneath is kinda ripoff / copy-paste :D

Create the file `.gtkrc-2.0` in your home directory with the following content:

    gtk-key-theme-name = "Emacs"
    gtk-can-change-accels = 1
    binding "gaim" {
        bind "Return" { "message_send" () }
        bind "Return" { "insert-at-cursor" ("\n") }
    }
    widget "*gaim_gtkconv_entry" binding "gaim"

[1]: http://thened.net/gaim-2.0-ctrl-enter-2006-11-07-16-40

