---
layout: post
status: publish
published: true
title: Bash prompt pimping
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
wordpress_id: 203
wordpress_url: http://emcken.dk/wp/archives/203-bash-prompt-pimping.html
date: '2007-10-18 13:37:24 +0200'
date_gmt: '2007-10-18 13:37:24 +0200'
categories:
- Work
- Linux
tags: []
comments: false
---
Today at work over lunch I read an article in [Linux Magazine][1] called Pimped Prompt.

It inspired me to try different stuff out. I often missed an indication on when I was doing different stuff in the terminal... this is what I ended up with:

    export PS1='\[\e[0;34m\][\@\e[1D]\[33[0m\] \u@\h:\w\$ '

<a class='serendipity_image_link' href='/weblog/uploads/prompt.png'><!-- s9ymdb:43 --><img width='110' height='73' style="float: left;border: 0px;padding-left: 5px;padding-right: 5px" src="/weblog/uploads/prompt.thumb.png" alt="" /></a> This is how it looks like.

To make it permanent put it in your `.bashrc` file in your home directory. Remember that this variable is propably already set so you either need to replace the line or instert closer to the bottom of the file.

[1]: http://www.linux-magazine.com/

