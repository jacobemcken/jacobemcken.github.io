---
layout: post
status: publish
published: true
title: Ever biched over IE not being able to use transparent PNG's?
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
wordpress_id: 52
wordpress_url: http://emcken.dk/wp/archives/52-ever-biched-over-ie-not-being-able-to-use-transparent-pngs.html
date: '2004-07-03 22:28:14 +0200'
date_gmt: '2004-07-03 22:28:14 +0200'
categories:
- Random hacks
tags: []
comments: []
---
<p><b>Well I have!!<&#47;b></p>
<p>Some days ago I found a link to <a href="http:&#47;&#47;support.microsoft.com&#47;default.aspx?scid=kb;en-us;Q294714">a hack<&#47;a> on <a href="http:&#47;&#47;jimmac.musichall.cz&#47;">Jakub 'jimmac' Steiner's homepage<&#47;a>.</p>
<p>I have now made a function in <a href="http:&#47;&#47;www.php.net&#47;">PHP<&#47;a> which automaticaly uses this workaround in <abbr title="Internet Explorer">IE<&#47;abbr>. Take a look at the code below.</p>
<pre>
$localDocumentRoot = str_replace($_SERVER['PHP_SELF'],<br />
"", $_SERVER['SCRIPT_FILENAME']);</p>
<p>&#47;&#47;$image is an array<br />
&#47;* This is a hack to use PNG transparentsy in IE *&#47;<br />
function transparentImage($image, $ie) {<br />
  global $localDocumentRoot;<br />
  &#47;&#47;The str_replace makes sure that images with spaces is also recognized<br />
  $imageFile = getimagesize( str_replace(' ','%20',<br />
  $localDocumentRoot.$image['src'] ) );<br />
  if($ie) {<br />
?></p>
<div<br />
<?=((isset($image['id'])) ? ' id="'.$image[id].'"' : '')?><br />
<?=((isset($image['class'])) ? ' class="'.$image['class'].'"' : '')?><br />
style="<?=' height:'.$imageFile[1].'px;'?> filter:progid:DXImageTransform.Microsoft.\AlphaImageLoader(src='',<br />
sizingMethod='scale');" >&nbsp;<&#47;div><br />
<?php<br />
  }<br />
  else {<br />
?><br />
<img src="<?=$image['src']?>"<br />
<?=((isset($image['id'])) ? ' id="'.$image['id'].'"' : '')?><br />
<?=((isset($image['class'])) ? ' class="'.$image['class'].'"' : '')?><br />
<?=((isset($image['alt'])) ? ' alt="'.$image['alt'].'"' : '')?><br />
 width="<?=$imageFile[0]?>"<br />
 height="<?=$imageFile[1]?>"<br />
<?=((isset($image['title'])) ? ' title="'.$image['title'].'"' : '')?><br />
&#47;><br />
<?php<br />
  }<br />
}<br />
?></p>
<p>&#47;&#47;Now run the function<br />
$image['src'] = "&#47;gallery&#47;image.png";<br />
$image['id'] = "unique_image";<br />
$image['class'] = "image";<br />
$image['alt'] = "Image example with transparent PNG images";<br />
$image['title'] = "Tooltip for image";</p>
<p>transparentImage($image, true);</p>
<p><&#47;pre></p>
<p>Tomorrow I will take a look at this post to make sure that I havet posted shitty code ;)<br />
Perhapes I can find a way so that Serendipity doesn't fuck with the layout?!</p>
