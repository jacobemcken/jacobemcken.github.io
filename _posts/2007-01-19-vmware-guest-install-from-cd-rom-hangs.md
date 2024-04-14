---
layout: post
status: publish
published: true
title: VMware guest install from CD-ROM hangs
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
wordpress_id: 176
wordpress_url: http://emcken.dk/wp/archives/176-vmware-guest-install-from-cd-rom-hangs.html
date: '2007-01-19 12:13:16 +0100'
date_gmt: '2007-01-19 12:13:16 +0100'
categories:
- Work
- Linux
tags: []
comments:
- id: 101
  author: Doug
  author_email: ''
  author_url: ''
  date: '2007-01-31 16:45:49 +0100'
  date_gmt: '2007-01-31 15:45:49 +0100'
  content: Thanks, worked for me
- id: 114
  author: JP
  author_email: ''
  author_url: ''
  date: '2007-02-17 01:08:48 +0100'
  date_gmt: '2007-02-17 00:08:48 +0100'
  content: Works Perfect!! thanks!!
---
Today I wanted to install Suse Linux Enterprise Server 10 (SLES10) from my USB DVD drive on my VMware Server (the free edition). I went though the wizard and powered on the machine but when the SUSE installer started to read the initial ramdisk it never got any further.

Troubleshooting VMware issues...
First I seached Google but didn't really found anything usefull. My problem was to generic. Then I found that each VMware machine has its own log file at the same location as the vmx files etc. I found that when the machine stopped responding the log file would say something like this:

    Jan 19 12:04:22: vcpu-0| VIDE: (0x170) Rep INSW ATAPI Unknown Cmd 0x52 Data len 8
    Jan 19 12:04:22: vcpu-0| VIDE: (0x170) Rep INSW ATAPI Unknown Cmd 0x52 Data len 28
    Jan 19 12:04:22: vmx| CDROM_SG: AIOCallbackSGIO: Unexpected errno: Input/output error (5)
    Jan 19 12:04:22: vmx| VIDE: ATAPI DMA 0x28 Failed: key 0x2, asc 0x0, ascq 0x0

Okay now this seems to be a CD/DVD drive problem. I found another working version of SLES 10 on VMware and compared the 2 vmx files.

    diff working.vmx not_working.vmx
    ...
    11,12c10,11
    < ide1:0.fileName = "/dev/cdrom"
     ide1:0.fileName = "/dev/scd0"
    > ide1:0.deviceType = "cdrom-raw"

Now shut down the virtual machine and edited the vmx fil to use `atapi-cdrom` and now I was able to install.

