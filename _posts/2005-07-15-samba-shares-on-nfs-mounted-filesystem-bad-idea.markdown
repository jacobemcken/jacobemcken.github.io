---
layout: post
status: publish
published: true
title: Samba shares on NFS mounted filesystem - bad idea
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
wordpress_id: 120
wordpress_url: http://emcken.dk/wp/archives/120-samba-shares-on-nfs-mounted-filesystem-bad-idea.html
date: '2005-07-15 22:45:00 +0200'
date_gmt: '2005-07-15 22:45:00 +0200'
categories:
- Linux
tags: []
comments: false
---
Today I implemented a strage server at a customer... the result wasn't satisfying.
I shared the files with NFS to the other servers. Samba shares of NFS mounted filesystems are NOT recommendable!
It makes Outlook fuck up if you have your pst files on a samba share and the finacial system C5 can only have one user logged in because NFS make some locks on files (at least I think that is the reason).

Anyways... just wanted to tell you all to test twice (or some more) before you do something like that.
I have't looked further into it, to see if it is possible to optimize on some settings.

