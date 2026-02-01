---
layout: post
status: publish
published: true
title: Running Tales of Monkey Island with Wine
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
wordpress_id: 297
wordpress_url: http://emcken.dk/weblog/?p=297
date: '2009-12-22 21:54:10 +0100'
date_gmt: '2009-12-22 20:54:10 +0100'
categories:
- Uncategorized
tags:
- games
- wine
comments: false
redirect_from:
  - /weblog/archives/297-running-tales-of-monkey-island-with-wine.html
---
I'm a proud adventure game fan boy and have enjoyed countless hours in the company of Monkey Island, Day of the Tentacle, Legend of Kyrandia among others. ScummVm have helped to re enjoy many of these titles after replacing my preferred desktop system with Linux.

When the news reached my ears about "Tales of Monkey Island" I was very exited. But also nervous if would be able to play these episodes without Windows... but fear no more.

It is possible!

My system:

*   Nvidia graphic card
*   Ubuntu Karmic 64bit
*   Wine from special package archive (to get native pulse audio support)

The episodes install on all Wine versions I've tried on so far (several guides on the internet suggests not to check for DirectX, though I haven't personally experienced any difference).
When the game starts you are asked to provide the serial number. In this phase I've encountered trouble several times. Earlier providing the serial number only worked in old versions of Wine (like version 1.0) but this time I got it working with the newest version of Wine (which at the time was 1.1.32). Also I had to delete my .wine folder in order to reset wine because I apparently had some IE6 leftovers in there which otherwise would mess up the registration.

After activating the episodes you need to install D3DX9_41.dll into `.wine/drive_c/windows/system32`. Remember Linux is case sensitive so it is possible to have several different files with the same name except for the case. If it still doesn't work make sure you only have one d3dx9_41.dll and that it's not the one provided by wine (which won't work).

Now the game starts... but for me the sound jittered. After looking around I found that it was ALSA interface in Pulseaudio which kept loosing the "connection" re initiating it. My saviour was [Neil Wilson][1] who have packaged Wine with a native pulseaudio audio plugin. This did the trick for me and Tales of Monkey Island is now very much playable at least for me :D

[1]: https://launchpad.net/~neil-aldur/+archive/ppa/+packages

