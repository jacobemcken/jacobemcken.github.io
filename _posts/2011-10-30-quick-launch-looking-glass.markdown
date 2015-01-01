---
layout: post
status: publish
published: true
title: Quick Launch Looking Glass
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
wordpress_id: 342
wordpress_url: http://www.emcken.dk/weblog/?p=342
date: '2011-10-30 20:05:25 +0100'
date_gmt: '2011-10-30 19:05:25 +0100'
categories:
- Random hacks
- Linux
tags:
- gnome
- gnome-shell
- extension
comments:
- id: 515
  author: Tomek Dubrownik
  author_email: t.dubrownik@gmail.com
  author_url: ''
  date: '2011-12-10 20:51:47 +0100'
  date_gmt: '2011-12-10 19:51:47 +0100'
  content: |
    Thanks for that, really useful, though pretty horrible you have to work around gnome-shell like that. Can't figure out anything else that works though:)
- id: 516
  author: Tomek Dubrownik
  author_email: t.dubrownik@gmail.com
  author_url: ''
  date: '2011-12-10 22:57:05 +0100'
  date_gmt: '2011-12-10 21:57:05 +0100'
  content: |
    ...and turns out there is a new mechanism for it, though only in mutter 3.3.2+. For more info check out http://blogs.gnome.org/fmuellner/2011/11/22/gnome-shell-gsettings-and-keybindings/ and google meta<em>display</em>add_keybinding
---
Today I took the plunge into something I've been wanting to do for a very long time: help developing Open Source Software. I've started off small :) I've been messing around with gnome-shell and looking into how extensions work. What better way to start than scratching and itch? I found it annoying to press Alt+F2 followed by "lg" and Enter to open Looking Glass (Gnome 3's integrated debugger and inspector tool) all the time.

Now I've created a small extension which binds opening Looking Glass to a keyboard shortcut for Gnome 3.2.

To install: [download Quick Launch Looking Glass][1] and install it using gnome-tweak-tool or by extraction it in:

    ~/.local/share/gnome-shell/extensions/

Followed by a restart of gnome-shell.

I haven't been able to figure out how to add a new custom key binding for a gnome-shell extension so I've used the reserved "run command 9" by Metacity until I figure out something better.

If you haven't bound it already - here is an example on how to bind it to the key F12:

    gconftool-2 -s --type string "/apps/metacity/global_keybindings/run_command_9" 'F12'

If the extension is enabled then Looking Glass will open when you press F12 (just close it by hitting Escape like normal).

[1]: http://www.emcken.dk/quick-launch-looking-glass@gnome.emcken.dk.zip

