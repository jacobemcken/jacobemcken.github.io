---
layout: post
status: publish
published: true
title: Testing gnome-blog with Serendipity
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
wordpress_id: 114
wordpress_url: http://emcken.dk/wp/archives/114-testing-gnome-blog-with-serendipity.html
date: '2005-06-15 09:29:00 +0200'
date_gmt: '2005-06-15 09:29:00 +0200'
categories:
- Linux
- Ubuntu
tags:
- gnome
comments:
- id: 20
  author: starryalley
  author_email: starryalley@gmail.com
  author_url: http://starryalley.twbbs.org/blog
  date: '2005-08-02 16:53:06 +0200'
  date_gmt: '2005-08-02 15:53:06 +0200'
  content: "Hello. I accidentally visit here via google's keyword search with \"serendipity
    ubuntu ...\" or something else. I had a question related to Serendipity installed
    on a Ubuntu machine. I'd glad to visit your blog since you had experience alike.
    \r\nFollowing the link I gave as Homepage in this reply, this site (my blog, and
    sorry something there should display Chinese in UTF-8 encoding) has a displaying
    problem with firefox. But in Konqueror or MS IE, it looks OK. What the problem
    is is that CSS doesn't seem to have any effects. Only text is displaying with
    a firefox browser. \r\nI think you might also be familir with serendipity and
    ubuntu. I'm also wondering if you had a problem alike? I can't find any problem
    in apache. Since recently I changed my OS from FC3 to Ubuntu 5.04, this happens
    so I guess it's due to Ubuntu. \r\nIf you have time, I'd be very grateful to you
    if you may check it out for me. Thank you in advance.\r\n\r\nsincerely, \r\nstarryalley,
    Taiwan."
---
I found `gnome-blog` a while back which is a small application from which you can add entries to you weblog. It also integrates with your GNOME panel so a blog entry is only one click away. At least it should integrate with the gnome-panel (running Ubuntu Hoary) but everytime I try to add it get this error:

> The panel encountered a problem while loading "OAFIID:GNOME_BlogApplet".

At the time of finding, I though it would be great if it actually worked with Serendipity (my weblog), and maybe just maybe if I had actually taken the time to look into it, I would have found that is does.
In `gnome-blog` Preferences you have to set XML-RPC URL to point on the `serendipity_xmlrpc.php`.

I have testet it now - but I'm not that impressed :-(
It puts in html tags around paragraphs which I'm not interested in, since I want the markdown post-processor to do that for me.
Also when using BloggerAPI protocol the title of the post wasn't saved in the title field as supposed to, but instead saved as the first line in the entry body. Last but not least my new post wasn't activated (only saved as draft), so I have to open the webinterface anyways to activate it after posting. So much for trying to go around the webinterface...
I dunno if it is `gnome-blog` or Serendipity that fucks it up.

Using the MetaWeblog protocol seems to work okay... now if it would just stop adding html tags!

Yeah yeah Frank I know it is Open Source :-D

