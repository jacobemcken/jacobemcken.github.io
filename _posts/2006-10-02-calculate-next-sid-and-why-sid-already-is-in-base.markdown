---
layout: post
status: publish
published: true
title: Calculate next SID, and why SID already is in base?
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
wordpress_id: 153
wordpress_url: http://emcken.dk/wp/archives/153-calculate-next-sid-and-why-sid-already-is-in-base.html
date: '2006-10-02 10:18:04 +0200'
date_gmt: '2006-10-02 10:18:04 +0200'
categories:
- Work
- Linux
tags: []
comments: []
---
<p>Today at work I got an error like the following while I tried to add a new user:</p>
<p>    ldapsam_add_sam_account: SID 'S-1-5-21-xxxxxxxxx-xxxxxxxxxx-xxxxxxxxx-3048' already in the base, with samba attributes</p>
<p>I searched the net to find out what the problem was. On some site I found how the SID is calculated:</p>
<p>    User-SID: uid*2+sambaAlgorithmicRidBase<br />
    Group-SID: gid*2+sambaAlgorithmicRidBase+1</p>
<p>My user had uid 1024 so it seemed to fit just right (I guess sambaAlgorithmicRidBase=1000). But why had another user already got the SID 3048, that would mean that another user had uid 1024?... Exactly! :)</p>
<p>Users for Samba is stored in LDAP. I have split my user- and Windows workstaion accounts (which also needs to be Linux users) into different containers named `Users` and `Computers`. When a normal account is created, it only checked for existing uid's in the `Users`-container. Because of this I now have 2 users with the uid 1024 (both Windows workstation account and my newly created user account) and now a SID conflict. I should fix the uid check when adding a new user to also check in the `Computers`-container. Hopes this helps some people with similar problems.</p>
