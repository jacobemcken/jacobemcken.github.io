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
comments: false
---
<b>Well I have!!</b>

Some days ago I found a link to <a href="http://support.microsoft.com/default.aspx?scid=kb;en-us;Q294714">a hack</a> on <a href="http://jimmac.musichall.cz/">Jakub 'jimmac' Steiner's homepage</a>.

I have now made a function in <a href="http://www.php.net/">PHP</a> which automaticaly uses this workaround in <abbr title="Internet Explorer">IE</abbr>. Take a look at the code below.

<pre>
$localDocumentRoot = str_replace($_SERVER['PHP_SELF'],
"", $_SERVER['SCRIPT_FILENAME']);

//$image is an array
/* This is a hack to use PNG transparentsy in IE */
function transparentImage($image, $ie) {
  global $localDocumentRoot;
  //The str_replace makes sure that images with spaces is also recognized
  $imageFile = getimagesize( str_replace(' ','%20',
  $localDocumentRoot.$image['src'] ) );
  if($ie) {
?>

<div
<?=((isset($image['id'])) ? ' id="'.$image[id].'"' : '')?>
<?=((isset($image['class'])) ? ' class="'.$image['class'].'"' : '')?>
style="<?=' height:'.$imageFile[1].'px;'?> filter:progid:DXImageTransform.Microsoft.\AlphaImageLoader(src='',
sizingMethod='scale');" >&nbsp;</div>
<?php
  }
  else {
?>
<img src="<?=$image['src']?>"
<?=((isset($image['id'])) ? ' id="'.$image['id'].'"' : '')?>
<?=((isset($image['class'])) ? ' class="'.$image['class'].'"' : '')?>
<?=((isset($image['alt'])) ? ' alt="'.$image['alt'].'"' : '')?>
 width="<?=$imageFile[0]?>"
 height="<?=$imageFile[1]?>"
<?=((isset($image['title'])) ? ' title="'.$image['title'].'"' : '')?>
/>
<?php
  }
}
?>

//Now run the function
$image['src'] = "/gallery/image.png";
$image['id'] = "unique_image";
$image['class'] = "image";
$image['alt'] = "Image example with transparent PNG images";
$image['title'] = "Tooltip for image";

transparentImage($image, true);

</pre>

Tomorrow I will take a look at this post to make sure that I havet posted shitty code ;)
Perhapes I can find a way so that Serendipity doesn't fuck with the layout?!

