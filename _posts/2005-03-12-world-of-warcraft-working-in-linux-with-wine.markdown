---
layout: post
status: publish
published: true
title: World of Warcraft working in Linux with WINE
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
wordpress_id: 101
wordpress_url: http://emcken.dk/wp/archives/101-world-of-warcraft-working-in-linux-with-wine.html
date: '2005-03-12 22:40:00 +0100'
date_gmt: '2005-03-12 22:40:00 +0100'
categories:
- Linux
- Ubuntu
tags: []
comments:
- id: 63
  author: Alex badcock
  author_email: a_bad_cock@hotmail.com
  author_url: ''
  date: '2005-12-12 01:08:15 +0100'
  date_gmt: '2005-12-12 00:08:15 +0100'
  content: "I want to play world of warcraft on ubuntu but i do not know where to
    get the patch can you please point me in the right direction\r\n\r\n\r\ni'm not
    useing windows because my dad hates it\r\n;-)"
- id: 355
  author: Does WoW work in Wine? - QuestionBin - Intelligent Answers for Smart Questions::Answer
  author_email: ''
  author_url: http://www.questionbin.com/answers/Does-WoW-work-in-Wine.html
  date: '2009-12-01 18:58:41 +0100'
  date_gmt: '2009-12-01 17:58:41 +0100'
  content: |
    [...] Yes, World of Warcraft work in Wine. It actually runs REALLY well (considering). All you need to do is install wine and install from your WoW DVD/CDs the normal way (wine setup.exe).   I found some articles about it: http://ubuntu-tutorials.com/2006/12/19/how-to-install-play-world-of-warcraft-ubuntu-510-6061-610/ http://info.rsow.com/ubuntu-wine-and-world-of-warcraft/ http://www.blog.highub.com/linux/world-of-warcraft-configuration-configwtf-on-ubuntu/ http://emcken.dk/weblog/archives/101-world-of-warcraft-working-in-linux-with-wine.html [...]

---
A few days ago I tired to get [World of Warcraft][] to work under Linux using [WINE][] (Not WineX - now know as [Cadega][]). Installing the game was a success. Though some buttons was missing their text I pulled myself through. A while back I expirenced an annoying problem while installing a game (I cant remember which or when). I was prompted to change CD but WINE locked the drive, so I was unable to unmount the drive (at least I think WINE was the problem). Anyway I don't care if it was WINE, Ubuntu or whoever fixed it, now it just works!

After installation I started the game with the opengl parameter which is neassesary for the game to run:

    je@rivendell:~$ wine c/Program\ Files/World\ of\ Warcraft/WoW.exe -opengl

I heard that they are pretty close at having the DirectX9 implementation in a state where it is possible to play WoW with that.

Starting the game I was greeted with the well know login screen (a least for people regulary playing - Those who rarely or don't play at all or those who never logs out (yes there are a few those also) might recall this screen as a blur?!?)

When I logged in a patch was downloaded with a peer-to-peer program which also just worked. The patch was applied without any trouble (again only some bottons was missing their text).

At my second login I was forced to patch again... but this time the patch program crashed and after e few tries I gave up :-( This morning I saw that the WINE release 20050310 had ben packaged for Debian in WINEs Debian repository, see [my earlier post][1].

I tried again and now it just worked. I experience a few problems though:

*   My fouthe mouse botton dosn't work which means that I can't enable auto run.
*   &AElig;&Oslash;&Aring; and &aelig;&oslash;&aring; danish characters dosn't work.
*   The sound sometimes scratces, but it is really not that much.

Are you still reading... go play your farvorite game on your farvorite OS :-D

[1]: {% post_url 2005-02-10-wine-repository-for-debian-ubuntu %}
[World of Warcraft]: http://www.worldofwarcraft.com/
[WINE]: http://www.winehq.org/
[Cadega]: http://www.transgaming.com/

