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
    <p>Thanks for that, really useful, though pretty horrible you have to work around gnome-shell like that. Can't figure out anything else that works though:)<&#47;p>
- id: 516
  author: Tomek Dubrownik
  author_email: t.dubrownik@gmail.com
  author_url: ''
  date: '2011-12-10 22:57:05 +0100'
  date_gmt: '2011-12-10 21:57:05 +0100'
  content: |
    <p>...and turns out there is a new mechanism for it, though only in mutter 3.3.2+. For more info check out http:&#47;&#47;blogs.gnome.org&#47;fmuellner&#47;2011&#47;11&#47;22&#47;gnome-shell-gsettings-and-keybindings&#47; and google meta<em>display<&#47;em>add_keybinding<&#47;p>
---
<p>Today I took the plunge into something I've been wanting to do for a very long time: help developing Open Source Software. I've started off small :) I've been messing around with gnome-shell and looking into how extensions work. What better way to start than scratching and itch? I found it annoying to press Alt+F2 followed by "lg" and Enter to open Looking Glass (Gnome 3's integrated debugger and inspector tool) all the time.</p>
<p>Now I've created a small extension which binds opening Looking Glass to a keyboard shortcut for Gnome 3.2.</p>
<p>To install: [download Quick Launch Looking Glass][1] and install it using gnome-tweak-tool or by extraction it in:</p>
<p>    ~&#47;.local&#47;share&#47;gnome-shell&#47;extensions&#47;</p>
<p>Followed by a restart of gnome-shell.</p>
<p>I haven't been able to figure out how to add a new custom key binding for a gnome-shell extension so I've used the reserved "run command 9" by Metacity until I figure out something better.</p>
<p>If you haven't bound it already - here is an example on how to bind it to the key F12:</p>
<p>    gconftool-2 -s --type string "&#47;apps&#47;metacity&#47;global_keybindings&#47;run_command_9" 'F12'</p>
<p>If the extension is enabled then Looking Glass will open when you press F12 (just close it by hitting Escape like normal).</p>
<p>[1]: http:&#47;&#47;www.emcken.dk&#47;quick-launch-looking-glass@gnome.emcken.dk.zip</p>
