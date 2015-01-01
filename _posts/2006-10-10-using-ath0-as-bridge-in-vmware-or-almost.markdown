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
  content: "Check /etc/vmware/locations\r\n\r\nanswer BINDIR /usr/bin\r\nanswer
    INITDIR /etc\r\nanswer INITSCRIPTSDIR /etc/init.d\r\nanswer SBINDIR
    /usr/sbin\r\nanswer LIBDIR /usr/lib/vmware-player\r\nanswer
    DOCDIR /usr/share/doc/vmware-player\r\nanswer RUNDIR /var/run/vmware\r\nanswer
    RUN_CONFIGURATOR yes\r\nanswer NETWORKING yes\r\n\r\n\r\nanswer VNET_0_INTERFACE
    ath0"
- id: 139
  author: Chetan Patil
  author_email: ''
  author_url: ''
  date: '2007-06-26 10:54:53 +0200'
  date_gmt: '2007-06-26 09:54:53 +0200'
  content: "I have managed to bridge to ath0 in VMWare.\r\nMy post may be of help.\r\n\r\nhttp://justbarebones.blogspot.com/2007/06/setting-up-ubuntu-704-feisty-as-vmware.html\r\n\r\nThx\r\nChetan"
---
Well I couldn't make my Atheros (ath0) work as a bridged network with VMware... but I made a workaround which I want to share with you guys. Anyways I'll have it documented if I cant remember what I did later on. I have installed VMware on my Ubuntu laptop and a edgy-alternative (server) as a guest OS within VMware.
The way I did this was making my laptop into a router between the "VMware host only" net and my wireless net.

You need to have configured a host only network for your VMware machines, mine is called `vmnet1` and is using the network:

    192.168.154.0/24

My laptop has the following ip's:

    wireless:    ath0     192.168.20.197 (provided by DHCP)
    wired:       eth0     * (not used in this example)
    VMware net:  vmnet1   192.168.154.1 (static)
    gateway:     default  192.168.20.1

My Ubuntu edgy server has the following ip's:

    wired:       eth0     192.168.154.2 (static)

On my laptop I have made a script that does the following:

    INTERNAL=vmnet1
    EXTERNAL=ath0

    # Enable router functionality
    echo 1 > /proc/sys/net/ipv4/ip_forward

    # Enabling SNAT (MASQUERADE) functionality on $EXTERNAL
    iptables -t nat -A POSTROUTING -o $EXTERNAL -j MASQUERADE

All my rules are set to accept as default, if yours are not you might want to add something like this:

    iptables -A FORWARD -i $EXTERNAL -o $INTERNAL -m state --state ESTABLISHED,RELATED -j ACCEPT
    iptables -A FORWARD -i $INTERNAL -o $EXTERNAL -j ACCEPT

On my VMware guest I have configured `eth0` to have the static ipadress `192.168.154.2` and to use `192.168.154.1` as default gateway.
The way you set this up depends on you guest OS, but you can also do this manually with:

    INTERNAL=eth0

    ip a add dev $INTERNAL 192.168.154.2/24
    ip link set $INTERNAL up
    route add default gw 192.168.154.1

Now test by pinging you host's gateway (my laptop):

    ping 192.168.20.1

**Note:** You propably want to set you guest OS to be able to use a DNS server.

