---
layout: post
status: publish
published: true
title: Backup with rdiff-backup
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
wordpress_id: 82
wordpress_url: http://emcken.dk/wp/archives/82-backup-with-rdiff-backup.html
date: '2004-10-17 22:55:36 +0200'
date_gmt: '2004-10-17 22:55:36 +0200'
categories:
- Computer
tags: []
comments: false
---
Why do I have to learn the hard way?!?

When you want to write <b>rm *~</b> dont stop half way there (<b>rm *</b>).
Luckily It was only a few templates for the developer version of <a href="http://www.debianart.dk/">Debianart</a> that got wiped, but it has delayed kick off yet again. Perhapes next weekend?

But the event made me look at a way to backup my important files. A colleague recommended <b>rdiff-backup</b> for backup and I have look at it and found it worthy :)
I have used <a href="http://rdiff-backup.stanford.edu/examples.html">some examples</a> I found on the net to get a picture on how it works. The good news is that <a href="http://packages.debian.org/cgi-bin/search_packages.pl?searchon=names&amp;subword=1&amp;version=all&amp;release=all&amp;keywords=rdiff-backup&amp;sourceid=mozilla-search">rdiff-backup exists as a Debian package</a>.

