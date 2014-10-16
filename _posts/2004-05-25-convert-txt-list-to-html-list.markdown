---
layout: post
status: publish
published: true
title: Convert txt-list to HTML-list
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
wordpress_id: 33
wordpress_url: http://emcken.dk/wp/archives/33-convert-txt-list-to-html-list.html
date: '2004-05-25 23:29:00 +0200'
date_gmt: '2004-05-25 23:29:00 +0200'
categories:
- Random hacks
tags: []
comments: []
---
<p>The following PHP code converts a txt list to at html list. Look at example below.<br />
Nothing new in this... alot of Wiki pages can do that, I just thought it was cool. After using alot of time to figure out how (new to regular expressions) it makes you wanna show it to the world:</p>
<p>    &#47;*<br />
     * This function isn't supposed to be called directly<br />
     * But through encodeList()<br />
     ************************************&#47;</p>
<p>    function encodeListElements($text) {<br />
      return preg_replace("&#47;^\s?(([\*|\-]) (.*?))\n&#47;m", "
<li>\\3<&#47;li>", $text);<br />
    }</p>
<p>    function encodeList($text) {<br />
      return preg_replace("&#47;\n((\s?(([\*|\-]) (.*?))\n)+)&#47;sie", "'
<ul>' . encodeListElements('$1') .'<&#47;ul>'", $text);<br />
    }</p>
<p>**Note:** Your browser might split up the code lines, so make a double check.</p>
<p># Example</p>
<p>txt:</p>
<p>    * Milk<br />
    * Water<br />
    * Coca Cola</p>
<p>html:</p>
<ul>
<li>Milk<&#47;li>
<li>Water<&#47;li>
<li>Coca Cola<&#47;li><br />
    <&#47;ul></p>
