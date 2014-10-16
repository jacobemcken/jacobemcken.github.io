---
layout: post
status: publish
published: true
title: chmod (distinguish between files and directories)
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
wordpress_id: 37
wordpress_url: http://emcken.dk/wp/archives/37-chmod-distinguish-between-files-and-directories.html
date: '2004-05-30 18:29:25 +0200'
date_gmt: '2004-05-30 18:29:25 +0200'
categories:
- Random hacks
tags: []
comments:
- id: 3
  author: Per Marker Mortensen
  author_email: marker@anc.dk
  author_url: http://anc.dk/
  date: '2004-06-16 08:36:22 +0200'
  date_gmt: '2004-06-16 07:36:22 +0200'
  content: "One could use someting like\r\n\r\nchmod ug+X instead \r\n\r\n:)\r\n\r\nnotice
    the capital letter X."
---
<p>I hate having execute bit set on files not supposed to be executable. But I have always found it a lot easier to set it to keep directories browseable when chmod'ing a directory recursive. Now I thrown a little bash line together to easily only chmod files or directories.</p>
<p>This only chmod's files:</p>
<p>    debian:~# find -type f  | xargs -i chmod 640 {}</p>
<p>This only chmod's directories:</p>
<p>    debian:~#find -name '*' -type d  | xargs -i chmod 750 {}</p>
<p>The <b>-name '*'<&#47;b> makes sure that it is only sub directories is processed (and not the current directory).</p>
