---
layout: post
status: publish
published: true
title: Linksys WRT54GL
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
wordpress_id: 151
wordpress_url: http://emcken.dk/wp/archives/151-linksys-wrt54gl.html
date: '2006-10-01 13:52:15 +0200'
date_gmt: '2006-10-01 13:52:15 +0200'
categories:
- Linux
tags: []
comments: false
---
I've bought a Linksys wireless access point some time ago from [FON][1], because I like the concept and it was really easy to setup also. Underneath is an embedded Linux controlling it all. Which is cool in itself.

But I have an IP phone which needs a big port range to be forwarded to it through the firewall. I wasn't able to define port ranges in the FON webinterface, so to day I finally got the time to try install the software FON have build there product upon: [Open Wrt][2].

What I'm really missing is: The Radius thing FON used. It was a really cool way of securing your wireless connection. I can see OpenWrt have optional packages for this support, but I would rather want it just to work.

Then a colleague pointed me to [Coova][3], which like FON builds upon OpenWrt but it seems it focus on the Radius support. I'll try that next week... if I got the time.
One of Coova's "Key Features" is traffic shaping, which I would like to play around with now that I have a ip-phone.

For now I'll just enjoy that I finally got my ip-phone working againg after several months.

[1]: http://en.fon.com/
[2]: http://openwrt.org/
[3]: http://www.coova.org/

