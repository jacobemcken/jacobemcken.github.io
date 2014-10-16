---
layout: post
status: publish
published: true
title: Mass converting flac to mp3 with Gstreamer from cli
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
wordpress_id: 202
wordpress_url: http://emcken.dk/wp/archives/202-mass-converting-flac-to-mp3-with-gstreamer-from-cli.html
date: '2007-09-13 22:29:14 +0200'
date_gmt: '2007-09-13 22:29:14 +0200'
categories:
- Linux
tags: []
comments:
- id: 580
  author: Kenneth Geisshirt
  author_email: kenneth@geisshirt.dk
  author_url: http://kenneth.geisshirt.dk/
  date: '2012-04-19 09:03:54 +0200'
  date_gmt: '2012-04-19 08:03:54 +0200'
  content: |
    <p>Thanks for a great tip. If you need to convert many files, you can do something like:<&#47;p>

    <p>find . -name '*.flac' | while read f ; do n=$(basename "$f" .flac) ; d=$(dirname "$f") ; mkdir -p "..&#47;Musik_som_mp3&#47;$d" ; gst-launch-0.10 filesrc location="$f" ! flacdec ! audioconvert ! lame ! id3mux name=tag ! filesink location="..&#47;Musik_som_mp3&#47;$d&#47;$n.mp3" ; done<&#47;p>
---
<p>I'm extracting all my CD's to flac files but my girlfriend is using iPod and iTunes on Windows which won't play flac files. So I looked into converting all the music to mp3 so she could use it as well. I wanted a way to do it from the command line and I knew [Gstreamer][1] was up for the job:</p>
<p>    gst-launch-0.10 filesrc location="music.flac" ! flacdec ! audioconvert ! lame ! id3mux name=tag v2-tag=true v1-tag=true ! filesink location="music.mp3"</p>
<p>The cool thing is that tags is preserved.</p>
<p>**Note:** Actually first I thought tags wasn't preserved during the the gstreamer conversion, but that was because I used Totem with the Xine backend which apparently cant show mp3 tags.</p>
<p>Now I only need to write a bash script to run through all the music... lets see when I find the time :D</p>
<p>[1]: http:&#47;&#47;gstreamer.freedesktop.org&#47;</p>
