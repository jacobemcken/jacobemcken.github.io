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
comments: false
---

The following PHP code converts a txt list to at html list. Look at
example below.  Nothing new in this... alot of Wiki pages can do that,
I just thought it was cool. After using alot of time to figure out how
(new to regular expressions) it makes you wanna show it to the world:

```php
/*
 * This function isn't supposed to be called directly
 * But through encodeList()
 ************************************/

function encodeListElements($text) {
    return preg_replace("/^\s?(([\*|\-]) (.*?))\n/m", "
<li>\\3</li>", $text);
}

function encodeList($text) {
    return preg_replace("/\n((\s?(([\*|\-]) (.*?))\n)+)/sie", "'
<ul>' . encodeListElements('$1') .'</ul>'", $text);
}
```

**Note:** Your browser might split up the code lines, so make a double check.


# Example

txt:

    * Milk
    * Water
    * Coca Cola


html:

```html
<ul>
<li>Milk</li>
<li>Water</li>
<li>Coca Cola</li>
</ul>
```

