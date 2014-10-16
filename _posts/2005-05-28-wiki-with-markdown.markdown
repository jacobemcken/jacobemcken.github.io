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
comments: []
---
<p>After I stumbled upon the cool [Markdown syntax][] and the [Markdown text parser][], some time ago. I have been looking for a wiki system which uses this cool syntax. We use a wiki system at work to document customer setups in, and a wiki with a decent syntax would be nice.<br />
A guy has made [PHP Markdown][] an port of Markdown to PHP and I hoped it would be easy to find a wiki system using markdown syntax. So far I only found [Instiki][] who lives up to this demand fully, which works [very][1] [well][2] under [Ubuntu][].<br />
Other wikis - like [DokuWiki][] - are able to use <a href="http:&#47;&#47;wiki.ioslo.net&#47;dokuwiki&#47;markdown">markdown as a rendering plugin<&#47;a> but it never feels fully integraded.<br />
With DokuWiki you need to wrap you text in tags like this:</p>
<p>    text with markdown syntax here</p>
<p>... and why would I want to do that on every page I create?<br />
Another thing that turned me away from DokuWiki was the fact that I wasn't able to make it work :-D</p>
<p>Thought I find Instiki really cool it has it's drawbacks too:</p>
<p>1.   The first I noticed was that it is written in ruby.<br />
     Not that that Ruby is a bad thing but I would have prefeered a wiki system in PHP<br />
     so I would understand the code myself. This I can live with.<br />
2.   The fact that it is written in Ruby, makes unable to run it on port 80 where my<br />
     Apache server is running. Perhapes I would be able to solve this using some sort of<br />
     proxy thing with Apache. Requsts on a certain domains&#47;page gets forwarded to<br />
     another port (for Instiki that would be 2500)... I think this is also the way Zope<br />
     usually works. If this would endeed work I could live with that. I havn't searched<br />
     for this "problem" on their website though...<br />
3.   I'm not able to upload files not even images and that is a thing I cannot live without.</p>
<p>When image (file) upload enters Instiki it will contain all I need for a great wiki. I trust they will implement it in a good way... all the other things they made is.<br />
It would be cool if I could use the syntax in my weblog... but that is only a matter of doing a little footwork.</p>
<p>[Markdown text parser]: http:&#47;&#47;daringfireball.net&#47;projects&#47;markdown&#47;<br />
[Markdown syntax]: http:&#47;&#47;daringfireball.net&#47;projects&#47;markdown&#47;syntax<br />
[Instiki]: http:&#47;&#47;instiki.org&#47;<br />
[PHP Markdown]: http:&#47;&#47;www.michelf.com&#47;projects&#47;php-markdown&#47;<br />
[DokuWiki]: http:&#47;&#47;wiki.ioslo.net&#47;dokuwiki&#47;markdown<br />
[1]: http:&#47;&#47;70.84.29.148:2500&#47;instiki&#47;show&#47;InstikiOnUbuntu "Installing under Ubuntu"<br />
[2]: http:&#47;&#47;70.84.29.148:2500&#47;instiki&#47;show&#47;UbuntuInitScript "Automatic startup script"<br />
[Ubuntu]: http:&#47;&#47;www.ubuntulinux.org&#47;</p>
