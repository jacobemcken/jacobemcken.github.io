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
tags: []
comments: []
---
<p>I'm a proud adventure game fan boy and have enjoyed countless hours in the company of Monkey Island, Day of the Tentacle, Legend of Kyrandia among others. ScummVm have helped to re enjoy many of these titles after replacing my preferred desktop system with Linux.</p>
<p>When the news reached my ears about "Tales of Monkey Island" I was very exited. But also nervous if would be able to play these episodes without Windows... but fear no more.</p>
<p>It is possible!</p>
<p>My system:</p>
<p>*   Nvidia graphic card<br />
*   Ubuntu Karmic 64bit<br />
*   Wine from special package archive (to get native pulse audio support)</p>
<p>The episodes install on all Wine versions I've tried on so far (several guides on the internet suggests not to check for DirectX, though I haven't personally experienced any difference).<br />
When the game starts you are asked to provide the serial number. In this phase I've encountered trouble several times. Earlier providing the serial number only worked in old versions of Wine (like version 1.0) but this time I got it working with the newest version of Wine (which at the time was 1.1.32). Also I had to delete my .wine folder in order to reset wine because I apparently had some IE6 leftovers in there which otherwise would mess up the registration.</p>
<p>After activating the episodes you need to install D3DX9_41.dll into `.wine&#47;drive_c&#47;windows&#47;system32`. Remember Linux is case sensitive so it is possible to have several different files with the same name except for the case. If it still doesn't work make sure you only have one d3dx9_41.dll and that it's not the one provided by wine (which won't work).</p>
<p>Now the game starts... but for me the sound jittered. After looking around I found that it was ALSA interface in Pulseaudio which kept loosing the "connection" re initiating it. My saviour was [Neil Wilson][1] who have packaged Wine with a native pulseaudio audio plugin. This did the trick for me and Tales of Monkey Island is now very much playable at least for me :D</p>
<p>[1]: https:&#47;&#47;launchpad.net&#47;~neil-aldur&#47;+archive&#47;ppa&#47;+packages</p>
