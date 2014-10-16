---
layout: post
status: publish
published: true
title: FLAC and Ogg&#47;Vorbis in iTunes
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
wordpress_id: 188
wordpress_url: http://emcken.dk/wp/archives/188-flac-and-oggvorbis-in-itunes.html
date: '2007-04-14 18:35:00 +0200'
date_gmt: '2007-04-14 18:35:00 +0200'
categories:
- Windows
tags: []
comments:
- id: 125
  author: Kim Tholstorf
  author_email: kim.tholstorf@gmail.com
  author_url: www.ipunk.dk
  date: '2007-04-15 18:21:30 +0200'
  date_gmt: '2007-04-15 17:21:30 +0200'
  content: "The XiphQT still does not support native FLAC file format. It's listed
    as a known limitation in their release notes. For now only Ogg FLAC are supported.
    And this means that for now only compressed FLAC data encapsulated in an Ogg transport
    layer (ogg container file) is the closest thing that your girlfriend can get to
    the high-def FLAC experience...\r\nBut you can put your money on (and in some
    way you already did with that Squeezebox) that in a short while the native FLAC
    file format will be supported by XiphQT :-) The framework is basically already
    in the code..."
---
<p>After buying my Squeezebox I wanted to rip all my music to a lossless music format to get maximum quality out of the little thing. And the Open Source lover that I am the choice fell on FLAC. One problem though...</p>
<p>The problem is that my girlfriend is using Windows with iTunes which isn't compatible with FLAC or Ogg for that matter.</p>
<p>I googled around for FLAC&#47;Ogg Vorbis support for iTunes and found xiph.org which has a [FLAC&#47;Ogg Vorbis plugin for QuickTime][1] ( and therefor also iTunes). iTunes uses the QuickTimes components to playback different audio and video types.</p>
<p>To bad that their latest version 0.1.7 is released only for Mac.<br />
It seems that you need to have FLAC contained in Ogg containers to be able to playback FLAC.</p>
<p>This link: a [blog about Xiphs development][2] deserves attention as well.</p>
<p>[1]: http:&#47;&#47;www.xiph.org&#47;quicktime&#47;<br />
[2]: http:&#47;&#47;barelyfocused.net&#47;blog&#47;category&#47;development&#47;xiphqt&#47;</p>
