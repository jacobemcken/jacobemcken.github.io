---
layout: post
status: publish
published: true
title: Whishing for more...
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
wordpress_id: 210
wordpress_url: http://emcken.dk/wp/archives/210-whishing-for-more.html
date: '2008-10-09 21:21:37 +0200'
date_gmt: '2008-10-09 21:21:37 +0200'
categories:
- Linux
- Virtualization
tags: []
comments:
- id: 182
  author: SLES-USR
  author_email: ''
  author_url: ''
  date: '2008-10-30 13:52:31 +0100'
  date_gmt: '2008-10-30 12:52:31 +0100'
  content: Your Page ist really Great! Hopefully you create more Blog Adds in Future.
---
Lately I found myself wishing for more in the world of Linux and Open Source. Yesterday I installed the free [VMware Server 2.0][1] which in my opinion is an upgrade worth while. I didn't need to patch anything to get the modules running on my Ubuntu Intrepid system, and on my laptop where I'm still running Ubuntu Hardy matching binary modules was actually included in the installation... **no need to compile them** ... I like that. [Bridging the Atheros wireless on my laptop still doesn't work][6], but I guess thats not VMwares fault.

Anyways after installing VMware Server I installed a virtual Ubuntu 8.04 server (Jeos - Just enought operation system) and wanted to install the VMware Tools on the machine. But honestly would like to just install a deb package. I searched the web for a bit to see if someone had created some debs ready to use.... nothing.
At some point Ubuntu hard Debian packages for both the VMware server itself and the VMware Tools. I want that!
Well a package with [Open Virtual Machine Tools][2] would do as well. I think my choice on Ubuntu (or Debian for that matter) mainly is because the gigantic repository of quality packages. But this is one of the few times when it comes short. As far as I can tell [open-vm-tools][3] will be packaged for Intrepid... looking forward to it :)

I'm still ripping all my music to FLAC and today I found some old pieces of music which had been tagged wrong. I was browsing my music library with Rhythmbox and right clicked to change the tag but I couldn't edit it :(
I found a [few][4] [bugs][5] which seems to describe my problem... and it seems to be fixed in gstreamer-plugins-good version 0.10.11... to bad Intrepid only have 0.10.10 atm :(

Last but not least I wish the nvidia driver would support Xrandr... though I'm not holding my breath :D

I've bumped into several other wishes but these was the ones I could remember on top of my head. It seems that for every time the overall quality Linux raises I just raise my expectations by the same degree.

[1]: http://vmware.com/products/server/
[2]: http://open-vm-tools.sourceforge.net/
[3]: http://packages.ubuntu.com/intrepid/open-vm-tools
[4]: http://bugzilla.gnome.org/show_bug.cgi?id=76524
[5]: http://bugzilla.gnome.org/show_bug.cgi?id=413841
[6]: {% post_url 2006-10-10-using-ath0-as-bridge-in-vmware-or-almost %}
