---
layout: post
status: publish
published: true
title: Using PHP to connect to an Active Directory
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
wordpress_id: 167
wordpress_url: http://emcken.dk/wp/archives/167-using-php-to-connect-to-an-active-directory.html
date: '2006-11-02 13:39:52 +0100'
date_gmt: '2006-11-02 13:39:52 +0100'
categories:
- Random hacks
- Work
- Linux
- Windows
tags: []
comments:
- id: 113
  author: Vidya
  author_email: ''
  author_url: ''
  date: '2007-02-16 12:29:26 +0100'
  date_gmt: '2007-02-16 11:29:26 +0100'
  content: Gr8 it worked for me... :-) after doing the required changes for the dn
    and stuff..
- id: 332
  author: greenface
  author_email: greenface@gmail.com
  author_url: http://nowebsite
  date: '2009-08-26 19:39:30 +0200'
  date_gmt: '2009-08-26 18:39:30 +0200'
  content: |
    Thanks a lot... it worked for me too!
    -in a world without barriers who need gates and windows!!!

- id: 337
  author: sandrar
  author_email: sandrar@gmail.com
  author_url: http://google.com/uzkbs
  date: '2009-09-10 14:44:06 +0200'
  date_gmt: '2009-09-10 13:44:06 +0200'
  content: |
    Hi! I was surfing and found your blog post... nice! I love your blog.  :) Cheers! Sandra. R.

- id: 412
  author: Joel Owen
  author_email: Ringelspaugh38@gmail.com
  author_url: http://www.120mmfan.net
  date: '2010-05-05 21:38:16 +0200'
  date_gmt: '2010-05-05 20:38:16 +0200'
  content: |
    I usually submit 300 word articles on article directories to help me gain backlinks and readers.';-

- id: 935
  author: minecraft pocket edition download
  author_email: lonnymargarot@zoho.com
  author_url: http://202.162.207.101/index.php?option=com_content&amp;view=article&amp;id=101654:meutya-hafid-tetap-fokus-pilkada-binjai&amp;catid=77&amp;Itemid=131
  date: '2014-07-22 03:03:16 +0200'
  date_gmt: '2014-07-22 02:03:16 +0200'
  content: |
    For most recent information you have to visit world-wide-web and on web I found this web site as a best web site for most up-to-date updates.

---
I am looking into authenticating users on Solaris 9 via Active Directory (AD) as an LDAP server. To chop the problem into smaller problems I started to try and connect to the LDAP interface of the AD from a platform which I know. I'm no Solaris expert :)

So I installed Ubuntu edgy (server install from alternative CD) and a evaluation Windows 2003 R2 server in the free VMware Server product. Then I install an Active Directory (and a DNS server) on the Windows Server.
The I tried to connect to the AD with PHP scripts to test how it worked. I found a [good article on www.developer.com about PHP LDAP connections to AD][1]. I also found an article about various [handy LDAP search filters for Active Directory][2].

First create a normal Windows user in the AD which you use to connect to the AD with. You don't need to add this user to any special groups to allow it to connect to the AD. Just a plain normal user. You might wanna disable password expiration if you are gonna use it in a production environment :)

The I made a php script on my Ubuntu server somewhat like the following:

    #!/usr/bin/php

# Trouble shooting

## 49: Invalid credentials

Remember when you tell PHP script which user you want to connect with, also supply the realm in which the user resides. In my test setup I used my own user `je` (Jacob Emcken), and my realm `testdomain.com` which means I'm connecting with to LDAP with the following user:

    je@testdomain.com

## 1: Operations error

This error can come from to things:

1.  You have used DN instead of DC in you distinct name:

        DN=testdomain,DN=com (didn't work for me)

    This worked for me:

        DC=testdomain,DC=com

2.  You get this if you are trying to search the root of the tree and you haven't set the following:

        ldap_set_option($ldap_connect_resource, LDAP_OPT_REFERRALS, 0);

[1]: http://www.developer.com/lang/php/article.php/10941_3100951_2
[2]: http://www.petri.co.il/ldap_search_samples_for_windows_2003_and_exchange.htm

