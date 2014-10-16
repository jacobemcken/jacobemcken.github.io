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
comments: []
---
<p>Some time ago I think it was when I made the switch from Gaim 1.x to 2.x sending my instant messages with Ctrl + Enter stopped working. I have lived with it for along time but it kept annoying me...  a good friend of mine found [the solution on another website][1] and today I took the time to actually do it.</p>
<p>The thing beneath is kinda ripoff &#47; copy-paste :D</p>
<p>Create the file `.gtkrc-2.0` in your home directory with the following content:</p>
<p>    gtk-key-theme-name = "Emacs"<br />
    gtk-can-change-accels = 1<br />
    binding "gaim" {<br />
        bind "Return" { "message_send" () }<br />
        bind "Return" { "insert-at-cursor" ("\n") }<br />
    }<br />
    widget "*gaim_gtkconv_entry" binding "gaim"</p>
<p>[1]: http:&#47;&#47;thened.net&#47;gaim-2.0-ctrl-enter-2006-11-07-16-40</p>
