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
comments: []
---
<p>I have started rewriting [debianart.dk][] to use the Smarty template engine. I gives me better overview of the coding and it also provides me with tools which helps me create a better website. Although I have already encountered a few quirks with it. I had trouble concating smarty variables with smarty variables and text strings with smarty variables within a smarty function. I use the <a href="http:&#47;&#47;smarty.incutio.com&#47;?page=PNGImagePlugin">Smarty plugin: PNG image with Alpha Transparency<&#47;a> to display PNG images with transparency in IE.</p>
<p>Here is my ugly workaround. Notice the 'src' and 'extra' parameter:</p>
<p>    {* It have to be possible to do this better... but for now it will suffice *}<br />
    {* It seems to impossible to call modifiers &#47; functions from within a function<br />
       and at the same time concat with a string, with default modifies &#47; functions *}<br />
    {* concat is a custom plugin *}<br />
    {assign var="thumb_title" value=$submission.file_size|filesizeSize}<br />
    {png_image src="`$thumb_path``$submission.thumb`" Height="0" width="0"<br />
     alt="Thumb for `$submission.file_name`"<br />
     extra='class=\"thumb" title="File size '|concat:$thumb_title|concat:'"'}</p>
<p>[debianart.dk]: http:&#47;&#47;www.debianart.dk&#47;</p>
