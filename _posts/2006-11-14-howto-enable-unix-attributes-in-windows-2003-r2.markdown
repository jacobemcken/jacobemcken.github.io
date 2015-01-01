---
layout: post
status: publish
published: true
title: Howto enable Unix Attributes in Windows 2003 R2
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
wordpress_id: 171
wordpress_url: http://emcken.dk/wp/archives/171-howto-enable-unix-attributes-in-windows-2003-r2.html
date: '2006-11-14 13:24:58 +0100'
date_gmt: '2006-11-14 13:24:58 +0100'
categories:
- Work
- Linux
- Windows
tags: []
comments: false
---
With the latest version of Windows Server 2003 R2 it is no longer needed to install Windows Services for UNIX, since this is now a part of Windows Server 2003, though not enabled by default.

<a href='/public/media/EnableUnixSettingsonActiveDirectory.png'><img width='110' height='83' style="float: left;" src="/public/media/EnableUnixSettingsonActiveDirectory.thumb.png" alt="" /></a> To enable it open `Control Panel -> Add or Remove Programs`. Now click on `Add/Remove Windows Components`. Double click on `Active Directory Services` and select `Identity Management for Unix`.

After this... surprise... you have to restart the Windows Server :-D

Now you should be able to see the "Unix Attributes"-tab when looking on properties for a user.

<img src="/public/media/Unixsettingsforusers.png" alt="" />

