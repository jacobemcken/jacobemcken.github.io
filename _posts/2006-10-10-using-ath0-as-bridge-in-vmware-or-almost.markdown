---
layout: post
status: publish
published: true
title: Using ath0 as bridge in VMware... or almost
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
wordpress_id: 156
wordpress_url: http://emcken.dk/wp/archives/156-using-ath0-as-bridge-in-vmware-or-almost.html
date: '2006-10-10 12:25:52 +0200'
date_gmt: '2006-10-10 12:25:52 +0200'
categories:
- Work
- Linux
tags: []
comments:
- id: 50
  author: Manolis
  author_email: sonic@diktia.dyndns.org
  author_url: http://debian12.dyndns.org
  date: '2006-10-12 14:31:44 +0200'
  date_gmt: '2006-10-12 13:31:44 +0200'
  content: "Hi\r\n\r\nI thought there would be a solution like this, I have a configuration
    very similar to yours, with an atheros based wireless card and vmware fails to
    bind to it. I was almost certain I could do it with a forwarding. Thanks for sharing
    the details. Will try it very soon."
- id: 54
  author: Alfredo
  author_email: aar_cl@yahoo.com
  author_url: ''
  date: '2006-11-02 19:43:59 +0100'
  date_gmt: '2006-11-02 18:43:59 +0100'
  content: "Check &#47;etc&#47;vmware&#47;locations\r\n\r\nanswer BINDIR &#47;usr&#47;bin\r\nanswer
    INITDIR &#47;etc\r\nanswer INITSCRIPTSDIR &#47;etc&#47;init.d\r\nanswer SBINDIR
    &#47;usr&#47;sbin\r\nanswer LIBDIR &#47;usr&#47;lib&#47;vmware-player\r\nanswer
    DOCDIR &#47;usr&#47;share&#47;doc&#47;vmware-player\r\nanswer RUNDIR &#47;var&#47;run&#47;vmware\r\nanswer
    RUN_CONFIGURATOR yes\r\nanswer NETWORKING yes\r\n\r\n\r\nanswer VNET_0_INTERFACE
    ath0"
- id: 139
  author: Chetan Patil
  author_email: ''
  author_url: ''
  date: '2007-06-26 10:54:53 +0200'
  date_gmt: '2007-06-26 09:54:53 +0200'
  content: "I have managed to bridge to ath0 in VMWare.\r\nMy post may be of help.\r\n\r\nhttp:&#47;&#47;justbarebones.blogspot.com&#47;2007&#47;06&#47;setting-up-ubuntu-704-feisty-as-vmware.html\r\n\r\nThx\r\nChetan"
---
<p>Well I couldn't make my Atheros (ath0) work as a bridged network with VMware... but I made a workaround which I want to share with you guys. Anyways I'll have it documented if I cant remember what I did later on. I have installed VMware on my Ubuntu laptop and a edgy-alternative (server) as a guest OS within VMware.<br />
The way I did this was making my laptop into a router between the "VMware host only" net and my wireless net.</p>
<p>You need to have configured a host only network for your VMware machines, mine is called `vmnet1` and is using the network:</p>
<p>    192.168.154.0&#47;24</p>
<p>My laptop has the following ip's:</p>
<p>    wireless:    ath0     192.168.20.197 (provided by DHCP)<br />
    wired:       eth0     * (not used in this example)<br />
    VMware net:  vmnet1   192.168.154.1 (static)<br />
    gateway:     default  192.168.20.1</p>
<p>My Ubuntu edgy server has the following ip's:</p>
<p>    wired:       eth0     192.168.154.2 (static)</p>
<p>On my laptop I have made a script that does the following:</p>
<p>    INTERNAL=vmnet1<br />
    EXTERNAL=ath0</p>
<p>    # Enable router functionality<br />
    echo 1 > &#47;proc&#47;sys&#47;net&#47;ipv4&#47;ip_forward</p>
<p>    # Enabling SNAT (MASQUERADE) functionality on $EXTERNAL<br />
    iptables -t nat -A POSTROUTING -o $EXTERNAL -j MASQUERADE</p>
<p>All my rules are set to accept as default, if yours are not you might want to add something like this:</p>
<p>    iptables -A FORWARD -i $EXTERNAL -o $INTERNAL -m state --state ESTABLISHED,RELATED -j ACCEPT<br />
    iptables -A FORWARD -i $INTERNAL -o $EXTERNAL -j ACCEPT</p>
<p>On my VMware guest I have configured `eth0` to have the static ipadress `192.168.154.2` and to use `192.168.154.1` as default gateway.<br />
The way you set this up depends on you guest OS, but you can also do this manually with:</p>
<p>    INTERNAL=eth0</p>
<p>    ip a add dev $INTERNAL 192.168.154.2&#47;24<br />
    ip link set $INTERNAL up<br />
    route add default gw 192.168.154.1</p>
<p>Now test by pinging you host's gateway (my laptop):</p>
<p>    ping 192.168.20.1</p>
<p>**Note:** You propably want to set you guest OS to be able to use a DNS server.</p>
