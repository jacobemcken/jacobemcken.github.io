---
layout: post
status: publish
published: true
title: Cleaning my inbox
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
wordpress_id: 11
wordpress_url: http://emcken.dk/wp/archives/11-cleaning-my-inbox.html
date: '2004-03-13 11:49:34 +0100'
date_gmt: '2004-03-13 11:49:34 +0100'
categories:
- Computer
tags: []
comments: []
---
<p>I send a lot of emails to my self with stuff I find around the net. Stuff that I actually believe I might use some day. :) My problem is that it clutters up my inbox... but where to put things that should reside inside ones head?</p>
<p>I tend to forget how to do things if I haven't used it myself (only read about it). So now I will use my blog to help me archive some stuff.</p>
<p>First a little command that lists used network ports on the computer</p>
<p>    debian:~# netstat -lnp</p>
<p>Second I have a javascript:</p>
<p>    function deObfuscate(email) {</p>
<p>      &#47;&#47;Obfuscate the e-mail address to prevent spam<br />
      var emailaddress;<br />
      emailaddress = email.replace('', '@');<br />
      emailaddress = emailaddress.replace(&#47;[|]+&#47;g,'');<br />
      window.location.href='mailto:' + emailaddress;<br />
    }</p>
<p>I have made a change to the weblog system (Serendipity) so now I have a button named <i>"Code"<&#47;i> which inserts the following:</p>
<div class='code'><&#47;div></p>
<p>I have used it to create the 3 code examples in this entry of my blog.</p>
<p>**Update:** I don't use this div-code-ting anymore... only the markdown syntax :)</p>
