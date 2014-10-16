---
layout: post
status: publish
published: true
title: VMware server from Ubuntu official repositories
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
wordpress_id: 193
wordpress_url: http://emcken.dk/wp/archives/193-vmware-server-from-ubuntu-official-repositories.html
date: '2007-05-08 09:09:48 +0200'
date_gmt: '2007-05-08 09:09:48 +0200'
categories:
- Ubuntu
- Virtualization
tags: []
comments: []
---
<p>I think its kinda hidden. You might have wondered why vmware-server kernel modules was available from the repositories but no vmware-server? The answer is: It is available!</p>
<p>Just like Real Player, Opera and other commercial software you can install VMware-server from the following repository, which you add to `System -> Administration -> Software Sources` and `Third-Party Software`:</p>
<p>    deb http:&#47;&#47;archive.canonical.com&#47;ubuntu feisty-commercial main</p>
<p>After that you run:</p>
<p>    sudo apt-get update<br />
    sudo apt-get install vmware-server</p>
<p>A colleague pointed me a page about adding [Adding Canonical Commercial Repositories][1] in Ubuntu, which is actually referenced from the [page about  Installtion VMware Server in Ubuntu][2]<br />
[1]: https:&#47;&#47;help.ubuntu.com&#47;community&#47;Repositories&#47;Ubuntu#head-b75a0c6c7e357640731529980d3f3ad3614b9a76<br />
[2]: https:&#47;&#47;help.ubuntu.com&#47;community&#47;VMware&#47;Server</p>
