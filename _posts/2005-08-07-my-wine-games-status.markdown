---
layout: post
status: publish
published: true
title: My WINE games status
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
wordpress_id: 125
wordpress_url: http://emcken.dk/wp/archives/125-my-wine-games-status.html
date: '2005-08-07 02:42:42 +0200'
date_gmt: '2005-08-07 02:42:42 +0200'
categories:
- Linux
- Ubuntu
- Games
tags: []
comments: false
---
To night I tried to play a few games with WINE (NOT Cadega) on my Ubuntu Hoary system.
Here is what I found out so far:

## World of Warcraft
NOGO
WoW version 1.6.1. Patches apply, game starts, you can login and select a character og play with it. But it there is a bug which makes it nearly impossible to click on any in-game items such as NPC's, mailboxes etc. I read on some mailinglists that if you put the camara just right you are able to click on them. Preferable when you can have nothing but sky behind the object you want to clock at (yeah something like running around looking up in the sky all the time). From what I have read on the mailing lists it seems to be **a Blizzard bug**... a few Windows users suffers from this too.
Comon Blizzard... we are so close now. The game seems to run alot faster under Linux in WINE than on my Windows XP partition (though it havn't been reinstalled for years now, so perhapes it isn't a fair comparisson).

**Update:** It is actually a YEAH. Take a look at my post about [WoW i Ubuntu Breezy][3]

## Sid Meier's Pirates
NOGO
Can't make the game install. I get an error code 1628 at once.
It seems that the game uses a MSI installer, but I havn't found any guides (yet) on how you install the game, but perhapes installaing a native MSI would solve the problem (guide located in the bottom of this post).
Haven't tried to copy from my Windows partition

## Risk II
NOGO
Yeah this is an old game which I usually play with the guys when we make some of our small lan-parties. There is no visual evidence (spalsh screen, menu, mouse cursor etc.) that the game actually starts, it just dies almost before it is excecuted.

## Warcraft III
YEAH
Remove movies from the `Movies/` directory. Apply a no cd patch and the game will run with the `-opengl` option. Haven't looked into playing on Battle.Net... yet.

Here is a few good reads:

*   [Franks Corner][1]
*   [Linux Games][2]

[1]: http://frankscorner.org/
[2]: http://www.linux-gamers.net/modules/wfsection/article.php?articleid=2
[3]: {% post_url 2005-08-12-world-of-warcraft-under-wine-with-kernel-2612 %}
