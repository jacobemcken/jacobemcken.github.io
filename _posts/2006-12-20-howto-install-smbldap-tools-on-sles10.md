---
layout: post
status: publish
published: true
title: HOWTO install smbldap-tools on SLES10
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
wordpress_id: 173
wordpress_url: http://emcken.dk/wp/archives/173-howto-install-smbldap-tools-on-sles10.html
date: '2006-12-20 10:53:53 +0100'
date_gmt: '2006-12-20 10:53:53 +0100'
categories:
- Work
- Linux
tags: []
comments:
- id: 135
  author: Daniele
  author_email: d.baldanza@tiscali.it
  author_url: ''
  date: '2007-06-11 00:10:21 +0200'
  date_gmt: '2007-06-10 23:10:21 +0200'
  content: "Great!!!\r\n\r\nMany thanks!!!"
- id: 162
  author: Denis
  author_email: denis@udec.cl
  author_url: ''
  date: '2008-03-03 12:24:08 +0100'
  date_gmt: '2008-03-03 11:24:08 +0100'
  content: "Hi there! Can u help me a little more? I tried everything and only get
    errors when I try to execute some smbldap-* scripts .... invalid file references
    and stuff .... any clue?\r\n\r\nThanx!"
- id: 163
  author: Jacob Emcken
  author_email: jacob@emcken.dk
  author_url: http://www.emcken.dk/
  date: '2008-03-04 13:02:10 +0100'
  date_gmt: '2008-03-04 12:02:10 +0100'
  content: "When you said you tried everything be more specific.\r\n\r\nCopy paste
    the exact path and script you are trying to run along with the error message.
    Without that I can't do anything to help."
- id: 186
  author: Mikhail
  author_email: ''
  author_url: ''
  date: '2008-11-18 08:15:46 +0100'
  date_gmt: '2008-11-18 07:15:46 +0100'
  content: The decision was not so difficult as i thinked, thank you very very much.
---
You can [download smbldap-tools from their homepage][1]... **don't!**

If have searched yast for `smbldap-tools`, you will probably (like me) have found nothing. I tried to install the platform independent RPM package. Only to find that I was missing:

    perl(Unicode::MapUTF8) is needed by smbldap-tools

Well there is not specific package for smbldap-tools for SLES 10... but I found that the perl scrips is included in the `samba-doc` package. After you have installed the `samba-doc` package, you will be able to find somewhere like this:

    /usr/share/doc/packages/samba/examples/LDAP/smbldap-tools-0.9.1

The version might vary when new service packs arrive.

Now copy the the following files to `/usr/local/sbin/`:

    smbldap-groupadd
    smbldap-groupmod
    smbldap-passwd
    smbldap-tools.spec
    smbldap-userdel
    smbldap-usermod
    smbldap_tools.pm
    smbldap-groupdel
    smbldap-groupshow
    smbldap-populate
    smbldap-useradd
    smbldap-userinfo
    smbldap-usershow

And copy the following configuration files to `/etc/smbldap-tools`:

    smbldap.conf
    smbldap_bind.conf

From here you are on your own :)

[1]: http://sourceforge.net/projects/smbldap-tools/

