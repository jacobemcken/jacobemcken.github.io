---
layout: post
status: publish
published: true
title: Debianart using Smarty... soon
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
wordpress_id: 72
wordpress_url: http://emcken.dk/wp/archives/72-debianart-using-smarty-soon.html
date: '2004-09-08 14:00:00 +0200'
date_gmt: '2004-09-08 14:00:00 +0200'
categories:
- Computer
tags: []
comments: false
---
I have started rewriting [debianart.dk][] to use the Smarty template engine. I gives me better overview of the coding and it also provides me with tools which helps me create a better website. Although I have already encountered a few quirks with it. I had trouble concating smarty variables with smarty variables and text strings with smarty variables within a smarty function. I use the <a href="http://smarty.incutio.com/?page=PNGImagePlugin">Smarty plugin: PNG image with Alpha Transparency</a> to display PNG images with transparency in IE.

Here is my ugly workaround. Notice the 'src' and 'extra' parameter:

```
{* It have to be possible to do this better... but for now it will suffice *}
{* It seems to impossible to call modifiers / functions from within a function
   and at the same time concat with a string, with default modifies / functions *}
{* concat is a custom plugin *}
{assign var="thumb_title" value=$submission.file_size|filesizeSize}
{png_image src="`$thumb_path``$submission.thumb`" Height="0" width="0"
 alt="Thumb for `$submission.file_name`"
 extra='class=\"thumb" title="File size '|concat:$thumb_title|concat:'"'}
```

[debianart.dk]: http://www.debianart.dk/

