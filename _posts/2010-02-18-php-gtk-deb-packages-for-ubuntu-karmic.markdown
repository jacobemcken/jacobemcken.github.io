---
layout: post
status: publish
published: true
title: Php-gtk deb packages for Ubuntu Karmic
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
wordpress_id: 305
wordpress_url: http://emcken.dk/weblog/?p=305
date: '2010-02-18 10:35:35 +0100'
date_gmt: '2010-02-18 09:35:35 +0100'
categories:
- Debian
- Work
- Linux
- Ubuntu
- FriFinans
tags: []
comments:
- id: 415
  author: David Kerr
  author_email: davek@davek.com
  author_url: ''
  date: '2010-05-10 20:36:03 +0200'
  date_gmt: '2010-05-10 19:36:03 +0200'
  content: |
    Great job!  I wonder, Does your PHP GTK package work on Lucid Lynx?  I will try it later when I get a chance.


    What would be even more awesome is getting your php5-gtk2 deb into the Ubuntu repositories!

- id: 419
  author: Jacob Emcken
  author_email: jacob@emcken.dk
  author_url: http://www.emcken.dk/
  date: '2010-06-11 08:24:32 +0200'
  date_gmt: '2010-06-11 07:24:32 +0200'
  content: |
    The packages doesn't work on Lucid atm. I'll hopefully get time to look at it at some point.

- id: 434
  author: Kasper Johansen
  author_email: k@spernj.org
  author_url: ''
  date: '2010-07-05 13:57:20 +0200'
  date_gmt: '2010-07-05 12:57:20 +0200'
  content: |
    Did you include MozEmbed, Extra and all the other cool stuff, that I myself found very hard to compile?

- id: 444
  author: Jacob Emcken
  author_email: jacob@emcken.dk
  author_url: http://www.emcken.dk/
  date: '2010-08-08 15:52:34 +0200'
  date_gmt: '2010-08-08 14:52:34 +0200'
  content: |
    I believe so... though it is quite some time ago now so I can't remember.


    To bad the package doesn't work with Lucid :-(

---
At work I was assigned to package some software to make it easier to distribute and update. One of those software packages was php-gtk which with one patch to the build/configure files now cleanly builds on Ubuntu. You will be able to find the package on my [Ubuntu PPA][1]. You will be able to find builds for both 32bit and 64bit platforms.

I'm also in the process of uploading packages for [FriFinans][2] which is an Open Source economy / accounting application. This should be easier... lets see how it goes :)

[1]: https://launchpad.net/~jacob-emcken/+archive/ppa
[2]: http://www.frifinans.org/

