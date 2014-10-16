---
layout: post
status: publish
published: true
title: Scaleable icon for Pidgin - Gnome-Do
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
wordpress_id: 257
wordpress_url: http://emcken.dk/weblog/?p=257
date: '2009-03-19 07:11:13 +0100'
date_gmt: '2009-03-19 06:11:13 +0100'
categories:
- Graphics
- Random hacks
- Ubuntu
tags:
- gnome-do
- bug
comments: []
---
<p>I like my desktop to look good (cool effects not needed) and I'm a heavy Gnome-Do user so it has been annoying me for quite some time that when starting Pidgin it looks like this:</p>
<p><img src="http:&#47;&#47;emcken.dk&#47;weblog&#47;files&#47;2009&#47;03&#47;pidgin-gnome-do-before.png" alt="pidgin-gnome-do-before" width="394" height="219" class="alignnone size-full wp-image-258" &#47;></p>
<p>So I started searching Launchpad for a bugreport and actually found 2:</p>
<p>  * [216460][bug 1]<br />
  * [337374][bug 2]</p>
<p>Thos bugs led me to [a bug report in Pidgins own trac][bug 3] about the same issue. Which led me to download the source code and an usable svg file which I manually copied to my system:</p>
<p>    sudo cp .&#47;pidgin-2.5.5&#47;pidgin&#47;pixmaps&#47;icons&#47;hicolor&#47;48x48&#47;apps&#47;scalable&#47;pidgin.svg &#47;usr&#47;share&#47;icons&#47;hicolor&#47;scalable&#47;apps&#47;</p>
<p>Long story short... now launching Piding from Gnome-Do looks like this:</p>
<p><img src="http:&#47;&#47;emcken.dk&#47;weblog&#47;files&#47;2009&#47;03&#47;pidgin-gnome-do-after.png" alt="pidgin-gnome-do-after" width="396" height="221" class="alignnone size-full wp-image-259" &#47;></p>
<p>YAY :D</p>
<p>[bug 1]: https:&#47;&#47;bugs.launchpad.net&#47;ubuntu&#47;+source&#47;pidgin&#47;+bug&#47;216460<br />
[bug 2]: https:&#47;&#47;bugs.launchpad.net&#47;ubuntu&#47;+source&#47;pidgin&#47;+bug&#47;337374<br />
[bug 3]: http:&#47;&#47;developer.pidgin.im&#47;ticket&#47;8556</p>
