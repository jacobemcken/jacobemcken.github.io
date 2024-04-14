---
layout: post
status: publish
published: true
title: Substitute "Mac" newlines with unix ones
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
wordpress_id: 67
wordpress_url: http://emcken.dk/wp/archives/67-substitute-mac-newlines-with-unix-ones.html
date: '2004-08-18 23:12:41 +0200'
date_gmt: '2004-08-18 23:12:41 +0200'
categories:
- Random hacks
tags: []
comments:
- id: 10
  author: Frank Bille Jensen
  author_email: yaemck@frank-bille.dk
  author_url: http://frank-bille.dk/
  date: '2004-08-23 19:44:17 +0200'
  date_gmt: '2004-08-23 18:44:17 +0200'
  content: Just remember that INPUTFILE and OUTPUTFILE must NOT be the same. Be it
    shouldn't be too difficult to hack a little shell script if you want that functionality
    :-)
- id: 11
  author: Frank Bille Jensen
  author_email: yaemck@frank-bille.dk
  author_url: http://frank-bille.dk/
  date: '2004-08-24 22:17:04 +0200'
  date_gmt: '2004-08-24 21:17:04 +0200'
  content: 'Ohh and by the way: It converts both mac and dos line endings into unix
    line endings. :-D'
---
At work we have been struggling with bad newlines in php-pages created with dreamweaver on a Mac. The newlines screwed up `grep` results and was mainly just one big pain in the ***. This is a little note to my self about how to solve it.

This solution (parsed around by one of the guys at work, thanks [Frank][1]) should be carved in stone to be remember for eternity. The second best would be writing it on the Internet and I don't have any stones not already written on at the moment:

    tr '\r\n' '\n'  OUTPUTFILE

[1]: http://frank-bille.dk/

