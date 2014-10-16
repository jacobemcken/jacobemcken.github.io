---
layout: post
status: publish
published: true
title: New infrastructure at home
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
excerpt: "Trough the last year I have been really wanting to do something to my home
  infrastructure :)\r\nI got wires running all over, and my little trusty server makes
  to much noice. I would like to hook up all my clients (2 workstations and a laptop)
  to a wireless network, maybe even my server, to get rid of all the wires cluttering
  my floor. I have been playing around with the thought of of trashing my current
  server totally, and find a new one wich is more home friendly (less power and noise).
  Even though webalizer tells me I have 1.5G traffic to emcken.dk each month, its
  not like I need a 2.0GHz processor and 1GB ram for it.\r\n\r\nI have searched the
  net for devices which could help me do what I want with a minimum of devices. Beneath
  I have gathered all I found out so far but first I want to sum up my list of my
  requirements.\r\n\r\nHere is what I want my home network and computers to do:\r\n\r\n*
  \  Linux server\r\n    *   Minimum noise\r\n    *   Minimum power consumption\r\n
  \   *   Fair amount of disk space (minimum 60GB)\r\n    *   Apache &#47; PHP &#47;
  MySQL (for my website &#47; blog)\r\n    *   Samba server (to share files to my
  Windows machines)\r\n*   Wireless access\r\n*   IP telephony\r\n*   A firewall\r\n
  \   *   Preferable Linux\r\n    *   With the possibility for QoS &#47; traffic shaping
  to make IP phone work under heavy network load.\r\n    \r\n\r\nIn the future I might
  want the following:\r\n\r\n*   Support for [Slimdevices][1] and their [slimserver][2]\r\n*
  \  Support for multimedia streaming server like MythTV and stuff\r\n*   Have a running
  [Teamspeak][3] server\r\n\r\n[1]: http:&#47;&#47;slimdevices.com&#47;\r\n[2]: http:&#47;&#47;wiki.slimdevices.com&#47;index.cgi?SlimServer\r\n[3]:
  http:&#47;&#47;www.teamspeak.org&#47;\n"
wordpress_id: 166
wordpress_url: http://emcken.dk/wp/archives/166-new-infrastructure-at-home.html
date: '2006-10-31 09:15:29 +0100'
date_gmt: '2006-10-31 09:15:29 +0100'
categories:
- Linux
tags: []
comments:
- id: 83
  author: n3ldan
  author_email: n3ldan@gmail.com
  author_url: http://n3ldan.info
  date: '2006-12-21 05:40:28 +0100'
  date_gmt: '2006-12-21 04:40:28 +0100'
  content: Try lighttpd - it's truly great.  I almost bought an nslu2, but decided
    I would build a cheap desktop (cheap being relative, it was ~$300) with 500gb
    storage, cheap&#47;slow&#47;cool AMD sempron 2800+, 1gb ram.  Runs all my assorted
    file servings (http,ssh2,samba,nfs), teamspeak, works as a mythtv backend, and
    let's me startx to play wow every now and then.  I use an XBOX as the media front
    end for televisions, as well as stream to my laptop.  Quite handy.
---
<p>Trough the last year I have been really wanting to do something to my home infrastructure :)<br />
I got wires running all over, and my little trusty server makes to much noice. I would like to hook up all my clients (2 workstations and a laptop) to a wireless network, maybe even my server, to get rid of all the wires cluttering my floor. I have been playing around with the thought of of trashing my current server totally, and find a new one wich is more home friendly (less power and noise). Even though webalizer tells me I have 1.5G traffic to emcken.dk each month, its not like I need a 2.0GHz processor and 1GB ram for it.</p>
<p>I have searched the net for devices which could help me do what I want with a minimum of devices. Beneath I have gathered all I found out so far but first I want to sum up my list of my requirements.</p>
<p>Here is what I want my home network and computers to do:</p>
<p>*   Linux server<br />
    *   Minimum noise<br />
    *   Minimum power consumption<br />
    *   Fair amount of disk space (minimum 60GB)<br />
    *   Apache &#47; PHP &#47; MySQL (for my website &#47; blog)<br />
    *   Samba server (to share files to my Windows machines)<br />
*   Wireless access<br />
*   IP telephony<br />
*   A firewall<br />
    *   Preferable Linux<br />
    *   With the possibility for QoS &#47; traffic shaping to make IP phone work under heavy network load.</p>
<p>In the future I might want the following:</p>
<p>*   Support for [Slimdevices][1] and their [slimserver][2]<br />
*   Support for multimedia streaming server like MythTV and stuff<br />
*   Have a running [Teamspeak][3] server</p>
<p>[1]: http:&#47;&#47;slimdevices.com&#47;<br />
[2]: http:&#47;&#47;wiki.slimdevices.com&#47;index.cgi?SlimServer<br />
[3]: http:&#47;&#47;www.teamspeak.org&#47;<br />
<a id="more"></a><a id="more-166"></a><br />
# Wireless</p>
<p>My first wireless was a Zyxel 2000. Spec's was what I needed and the design of the actual access point was good. Random disconnects and the need for power cycling the acces point made me look for something new. Then I bought a [Linksys WRT54GL][1] on [Fon][2]'s website and tried that one out. FON is a really cool wireless community. Go read about them... you might like it.</p>
<p>I wasn't able to open port ranges in the firewall on the Linksys using the FON firmware. So I tried a firmware from the [OpenWRT project][3] which FON actually builds upon for their Linksys WRT54GL devices. Linux on small devices rocks.</p>
<p>For some reason I had a high latency when playing World of Warcraft on the wireless... I never got the time to look into this before I stumbled upon another project using the OpenWRT project as base: [Coova][4]. Coova is a really cool project even though I had some issuses with it. Before I found the solution to the problem, which I later learned was caused by mysellf my colleague [Tomas Krag][5] had already introduced me to "La Fonera", the latest access point from [Fon][3]. It is small, looks really slick and the wireless connections is very stable... so I'm gonna stick with this one for now.</p>
<p># The server</p>
<p>I have been looking a various possible server alternatives to my current "slim desktop PC" server.</p>
<p>First I thought about building a micro-atx machine. Though I would really like to make my server as small, noise free and with as low power consumption as possible. Then I thought I'd use my Lynksys WRT54GL, but it doesn't have enought diskspace for my websites. Then I looked at different NAS solutions like [Thecus N2100][6], and in the end I stumbled upon the [Linksys NSLU2][7]. You can install [linux on the Linksys NSLU2][8], you can attach USB disks, its small and it is cheap :-D</p>
<p>I'm not sure if the processor is powerful enough. One of my mates reminded me that might be able to use alternatives to Apache which is more lightweight. Anyways I think I'm gonna buy one and find out for my self. For disk space I'd buy a laptop disk (2,5"). Perhaps a Seagate disk in a [RaidSonic Icybox closure][9]. It seems people have made the slimserver run on it which is cool. Don't know if it can run a Teamspeak and it can't run as a MythTV box for sure. But I might wanna make a separate box for all that multimedia stuff later on, perhaps based on micro or nano ATX motherboard.</p>
<p># The other stuff</p>
<p>I already go an IP telephone, or an [IP2analog][10] converter. Which works okay, good enough for me anyway.<br />
About the firewall I might want to use my current Linksys WRT54GL as firewall behind my Zyxel 650 router provided by ISP. The Zyxel 650 doesn't use a normal RJ45 plug for the wan interface. I hope I will be able to find a device with a decent firewall and QoS &#47; traffic shaping that can replace my Zyxel 650. So I don't have to use 2 devices to get router and firewall functionality.</p>
<p>[1]: http:&#47;&#47;www.linksys.com&#47;servlet&#47;Satellite?c=L_Product_C2&amp;childpagename=US%2FLayout&amp;cid=1133202177241&amp;pagename=Linksys%2FCommon%2FVisitorWrapper<br />
[2]: http:&#47;&#47;www.fon.com&#47;<br />
[3]: http:&#47;&#47;www.openwrt.org&#47;<br />
[4]: http:&#47;&#47;www.coovaap.org&#47;<br />
[5]: http:&#47;&#47;multiplicity.dk&#47;<br />
[6]: http:&#47;&#47;www.thecus.com&#47;products_over.php?cid=1&amp;pid=1&amp;PHPSESSID=1eba9048329dca02b4fe2d7dd55c09ce<br />
[7]: http:&#47;&#47;www.linksys.com&#47;servlet&#47;Satellite?c=L_Product_C2&amp;childpagename=US%2FLayout&amp;cid=1115416906769&amp;pagename=Linksys%2FCommon%2FVisitorWrapper<br />
[8]: http:&#47;&#47;www.nslu2-linux.org&#47;</p>
<p>[9]: http:&#47;&#47;www.raidsonic.de&#47;de&#47;pages&#47;products&#47;external_cases.php?we_objectID=4366<br />
[10]: http:&#47;&#47;www.sipura.com&#47;products&#47;spa2000.htm</p>
