---
layout: post
status: publish
published: true
title: Migrate emails to Zimbra using imapsync
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
wordpress_id: 205
wordpress_url: http://emcken.dk/wp/archives/205-migrate-emails-to-zimbra-using-imapsync.html
date: '2007-10-30 14:32:29 +0100'
date_gmt: '2007-10-30 14:32:29 +0100'
categories:
- Work
- Linux
tags: []
comments: false
---
I've installed Zimbra on SLES9 for a costumer because they wanted Zimbra on Suse. The costumer wanted the community maintained version and I felt it was too risky to install Zimbra 5 release candidate.

First a little Suse bashing (sorry but I just get irritated about this over and over again). Zimbra recommends using imapsync to migrate emails to Zimbra and it seems to be a fine piece of software.

1.  I started out by using yast to search for this tool but as I expected nothing.
2.  After downloading it from the website and trying to run it I got a message that I was missing `Mail::IMAPClient` lib.
3.  I tried to find SLES9 rpm packages (or just RPM packages) with the `Mail::IMAPClient `lib.
4.  I tried to install this with cpan:

        cpan
        cpan> install Mail::IMAPClient
        ....
        Writing Makefile for Mail::IMAPClient::MessageSet
        Warning: prerequisite Parse::RecDescent 1.94 not found. We have 1.80.

    I'm no cpan / perl expert so I couldn't figure out why I couldn't get `Parse::RecDescent` installed.
5.  Anyway what is the benefit of having a supported enterprise version of Suse if you trash it with all sorts of unsupported software.

You could argue that Zimbra should provide imapsync as some part of migration tools.

Anyway the solution was that I installed imapsync on my Ubuntu Gutsy laptop:

    sudo apt-get install imapsync

I just gets so disappointed that things are so "hard" in a professional Linux compared to Debian or Ubuntu.

When I'm done I'll just uninstall it and all its dependencies again:

    imapsync libdigest-hmac-perl libdigest-sha1-perl libio-socket-ssl-perl libmail-imapclient-perl libnet-ssleay-perl libparse-recdescent-perl

**Important note:** I had imapsync on Ubuntu Gutsy hang when it connected to the Zimbra server. I found that passing `--noauthmd5` with the example in [User Migration in Zimbras wiki][1] made imapsync not hang. An earlier version of Ubuntu (Edgy with an earlier version of imapsync) didn't need this.

[1]: http://wiki.zimbra.com/index.php?title=User_Migration#Migrating_from_an_existing_IMAP_server_.28Recommended_Method.29

