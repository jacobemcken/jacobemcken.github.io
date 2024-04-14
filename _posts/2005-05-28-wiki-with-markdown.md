---
layout: post
status: publish
published: true
title: Wiki with Markdown
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
wordpress_id: 108
wordpress_url: http://emcken.dk/wp/archives/108-wiki-with-markdown.html
date: '2005-05-28 09:04:15 +0200'
date_gmt: '2005-05-28 09:04:15 +0200'
categories:
- Work
- Linux
- Ubuntu
tags: []
comments: false
---
After I stumbled upon the cool [Markdown syntax][] and the [Markdown text parser][], some time ago. I have been looking for a wiki system which uses this cool syntax. We use a wiki system at work to document customer setups in, and a wiki with a decent syntax would be nice.
A guy has made [PHP Markdown][] an port of Markdown to PHP and I hoped it would be easy to find a wiki system using markdown syntax. So far I only found [Instiki][] who lives up to this demand fully, which works [very][1] [well][2] under [Ubuntu][].
Other wikis - like [DokuWiki][] - are able to use <a href="http://wiki.ioslo.net/dokuwiki/markdown">markdown as a rendering plugin</a> but it never feels fully integraded.
With DokuWiki you need to wrap you text in tags like this:

    text with markdown syntax here

... and why would I want to do that on every page I create?
Another thing that turned me away from DokuWiki was the fact that I wasn't able to make it work :-D

Thought I find Instiki really cool it has it's drawbacks too:

1.   The first I noticed was that it is written in ruby.
     Not that that Ruby is a bad thing but I would have prefeered a wiki system in PHP
     so I would understand the code myself. This I can live with.
2.   The fact that it is written in Ruby, makes unable to run it on port 80 where my
     Apache server is running. Perhapes I would be able to solve this using some sort of
     proxy thing with Apache. Requsts on a certain domains/page gets forwarded to
     another port (for Instiki that would be 2500)... I think this is also the way Zope
     usually works. If this would endeed work I could live with that. I havn't searched
     for this "problem" on their website though...
3.   I'm not able to upload files not even images and that is a thing I cannot live without.

When image (file) upload enters Instiki it will contain all I need for a great wiki. I trust they will implement it in a good way... all the other things they made is.
It would be cool if I could use the syntax in my weblog... but that is only a matter of doing a little footwork.

[Markdown text parser]: http://daringfireball.net/projects/markdown/
[Markdown syntax]: http://daringfireball.net/projects/markdown/syntax
[Instiki]: http://instiki.org/
[PHP Markdown]: http://www.michelf.com/projects/php-markdown/
[DokuWiki]: http://wiki.ioslo.net/dokuwiki/markdown
[1]: http://70.84.29.148:2500/instiki/show/InstikiOnUbuntu "Installing under Ubuntu"
[2]: http://70.84.29.148:2500/instiki/show/UbuntuInitScript "Automatic startup script"
[Ubuntu]: http://www.ubuntulinux.org/

