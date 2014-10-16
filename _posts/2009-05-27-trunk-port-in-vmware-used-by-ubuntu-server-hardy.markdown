---
layout: post
status: publish
published: true
title: Trunk port in VMware used by Ubuntu Server (Hardy)
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
wordpress_id: 274
wordpress_url: http://emcken.dk/weblog/?p=274
date: '2009-05-27 08:52:37 +0200'
date_gmt: '2009-05-27 07:52:37 +0200'
categories:
- Linux
- Ubuntu
- Virtualization
tags:
- vlan
- vmware
- nmap
comments: []
---
<p>At work I had to setup the network on Ubuntu Server (Hardy) so it was able to scan several networks with nmap as if they where local networks (and thus able to get MAC addresses). Until now the networks was scanned through a router which means the MAC addresses was lost. The reason for the importance of the MAC addresses was to identify whether a machine was likely a virtual machine or a physical machine.</p>
<p>The machine scanning is a virtual machine in VMware. Seven networks needed the ability to scan MAC adresses. VMware has a limitation of only 6 hardware devices (at least in ESX 3.5) which meant that having two harddisks it was only able to scan four out of the seven networks giving the machine a virtual NIC in each network.<br />
The ESX server separated the networks with VLAN tags so to work around this we created a virtual trunk NIC.</p>
<p>Notice: The VLAN ID is optional but an empty VLAN ID means it cannot see VLAN tags. If you want a VMware NIC. To see all VLAN tags the VLAN ID must be 4095 (this might be VMware specific). This gave me and a colleague quite a headache before we figured it out.</p>
<p>To create trunked NIC in VMware:</p>
<p>*   Click on the ESX server you want to create it on.<br />
*   Click on the "Configuration" tab to the right.<br />
*   Find the virtual switch in which the trunked NIC should reside in and click on "Properties...".<br />
*   Click on the "Add..." button at the bottom to start the wizard.<br />
*   For "Connection Types:" select "Virtual Machine" and press "Next".<br />
*   Give the NIC a name ie. "Trunk" and set the "VLAN ID (Optional):" to 4095 and press "Next".<br />
*   Now just press "Finish" and notice the right information pane now indicating VLAN ID is set to "All".</p>
<p>For Ubuntu Server (Hardy) to play nice with this new trunked NIC I found some [help on the Ubuntu forums on how to setup VLAN][1].</p>
<p>First you have to install the vlan package:</p>
<p>    sudo aptitude install vlan</p>
<p>Now enable the module:</p>
<p>    sudo modprobe 8021q</p>
<p>And make sure it gets automatically loaded the next time the machine starts up:</p>
<p>    sudo  sh -c 'grep -q 8021q &#47;etc&#47;modules || echo 8021q >> &#47;etc&#47;modules'</p>
<p>Now configure your NIC in the following file:</p>
<p>    &#47;etc&#47;network&#47;interfaces</p>
<p>The following example sets up the ip 192.168.1.100 with 8 as a VLAN tag on eth0:</p>
<p>    auto eth0.8<br />
    iface eth0.8 inet static<br />
        address 192.168.1.100<br />
        netmask 255.255.255.0<br />
        gateway 192.168.1.1</p>
<p>Now bring up the interface with:</p>
<p>    sudo ifup eth0.8</p>
<p>[1]: http:&#47;&#47;ubuntuforums.org&#47;showthread.php?t=703387</p>
