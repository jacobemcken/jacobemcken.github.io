---
layout: post
status: publish
published: true
title: Hacking Wordpress MU - Part 3
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
wordpress_id: 217
wordpress_url: http://emcken.dk/weblog/?p=217
date: '2009-03-16 17:51:58 +0100'
date_gmt: '2009-03-16 16:51:58 +0100'
categories:
- Random hacks
tags:
- wordpress
comments: false
---
I found the first problem with running this Wordpress installation on multiple domains. The installation generates a `.htaccess` filen in the installation directory. This won't work when the blogs are in differet directory levels.

What I did was adding the rewite rules to the virtual host instead... that way all my domains could have different rewrite settings.

I also tried to move the upload directory away from the central Wordpress installation and into the document roots for the different domains. But this seems to be impossible due to a [bug in Wordpress MU][1].

The last issue I found with my solution to the multiple domains with Wordpress MU is that the site admin might not be able to see the blogs on the other domains but if you know the id of the blog you can just change that in the URL and gain edit access to the blog anyway.

# Example:

The user `domain1_admin` is site admin for domain1.com. On the domain domain2.com the a blog with the id 3 is attached. So admin logs in on domain1.com and uses this url:

    http://domain1.com/wp-admin/wpmu-blogs.php?action=editblog&amp;id=3

The last few issues I can live with... so I wont be experimenting with Wordpress for a while I guess. Hope someone can use this.

[1]: http://trac.mu.wordpress.org/ticket/732

