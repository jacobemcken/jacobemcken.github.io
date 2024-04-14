---
layout: post
status: publish
published: true
title: phpwiki problems with VirtualHost
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
wordpress_id: 47
wordpress_url: http://emcken.dk/wp/archives/47-phpwiki-problems-with-virtualhost.html
date: '2004-06-25 22:47:01 +0200'
date_gmt: '2004-06-25 22:47:01 +0200'
categories:
- Debian
tags: []
comments: false
---
I tried installing the package <b>phpwiki</b> and the installation went (like almost always with Debian) smooth. But I use severel `VirtualHost`'s and when i tried to access phpwiki I was met with an error 404 :(

After googling around I found that RewriteRules isn't inheritet from the default apache settings into a VirtualHost neither is the RewriteEngine setting.

Put this in each VirtualHost which should have phpwiki enabled:

    RewriteEngine on
    RewriteOptions inherit

Note if you have more global RewriteRules than phpwiki these will also be enabled.

