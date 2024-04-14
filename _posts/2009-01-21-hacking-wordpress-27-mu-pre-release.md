---
layout: post
status: publish
published: true
title: '"Hacking" Wordpress 2.7 MU pre-release'
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
wordpress_id: 212
wordpress_url: http://emcken.dk/wp/archives/212-hacking-wordpress-27-mu-pre-release.html
date: '2009-01-21 23:18:51 +0100'
date_gmt: '2009-01-21 23:18:51 +0100'
categories:
- Computer
- Random hacks
tags: []
comments:
- id: 193
  author: kapil
  author_email: kbhara@gmail.com
  author_url: ''
  date: '2009-02-05 05:17:22 +0100'
  date_gmt: '2009-02-05 04:17:22 +0100'
  content: i am using wordpress mu 2.6.5 and was stuck with a similar problem. Modifying
    wp_site seems to work ... although I did not make the changes to the wp_blog table
    ... will try it out ... in the meantime - it will be good to know if you faced
    any other problems with this change ..., thanks
- id: 197
  author: Jacob Emcken
  author_email: jacob@emcken.dk
  author_url: http://www.emcken.dk/
  date: '2009-02-05 19:56:22 +0100'
  date_gmt: '2009-02-05 18:56:22 +0100'
  content: "Try read my [second post about mulitple domains in Wordpress MU][1].\r\n\r\n[1]:
    http://www.emcken.dk/weblog/archives/213-Hacking-Wordpress-2.7-MU-Part-2.html"
- id: 295
  author: adikcilak
  author_email: adikcilak@gmail.com
  author_url: http://www.adikcilak.com
  date: '2009-03-16 16:17:23 +0100'
  date_gmt: '2009-03-16 15:17:23 +0100'
  content: "good articles brother\r\n\r\n\r\n----------\r\n<a href=\"http://blog.adikcilak.com\"
    rel=\"nofollow\">internet blog</a>"
---
Alright... one of my good friends wanted a Wordpress blog... I've been thinking of migrating this blog (Jacob's Weblog - Time is always against me) along with another blog on my webserver to Wordpress... that's 1 2... 3 Wordpress installations.

I went to the [Wordpress website][1] and finds they recently released a new version (version 2.7). After downloading it and falling in love my good friend points me to [Wordpress MU][2] Why maintain 3 Wordpress installations when you can maintain a single probably 10x times as complex one?... I like a challenge.

The first part of the challenge was that Wordpress MU hasn't been released in a 2.7 version yet. Well I fetched it right from [trunk (revision 1616)][3] and installed. Then created two blogs on two different domains and created users for the blogs on the different domains. Second part of the challenge was that users attached to the second domain couldn't login and was just redirected back to the login screen.

I [described my problem in detail on the Wordpress forum][4] in the hope of some mighty Wordpress MU guru would serve me the solution on a silver platter... that wasn't the case.

I can get stubborn sometimes and the last 2 months we had a php development project on work so I figured: "How hard can it be?"
After an a few hours (first spend googling for debugging Wordpress) I ended up using  lots of `echo`, `if` and `exit` - there is no school like the old school :D

Now I think I found a solution and it wasn't code changes. I needed som changes to the data in the database. You will find a table named `wp_site` where you will need to add your second domain (and third... etc.) Now browse the table `wp_blogs` and change the site_id so it matchs the site id for the domain in `wp_site`.

I still need to test this out but so far it looks good :D

The third challenge will be to make my domains work with www. prepended. But I'll save that for another time.

[1]: http://www.wordpress.org/
[2]: http://mu.wordpress.org/
[3]: http://trac.mu.wordpress.org/browser/trunk
[4]: http://mu.wordpress.org/forums/topic.php?id=10824

