---
layout: post
status: publish
published: true
title: Slide transitions between html sections using jquery
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
wordpress_id: 376
wordpress_url: http://www.emcken.dk/weblog/?p=376
date: '2012-08-30 23:12:10 +0200'
date_gmt: '2012-08-30 22:12:10 +0200'
categories:
- Random hacks
tags: []
comments: []
---
I needed to make the picture in my head of the interface and workflow for my pet project [clojure-blog][] more concrete.

To split tasks up in multiple steps for the user (not the communication to the server) was essential. The [transition Gnome 3 uses in "System Settings"][2] was what I was looking for.

I've googled for it several hours only to find complex examples of something the almost did what I wanted.

I finally gave in and started to put something together which I expected to be at least as complex as all the code I had already seen through my search for a solution... it wasn't :)

The following is some very nice (short and readable) piece of very generic code which uses the new html5 tag section and normal links with internal references to structure the content.

You can demo an example of it here on [jsFiddle][3]

Both the html and js is clean... styles for body and h1 can safely be removed together with all css properties defining colors and margin.

[clojure-blog]: https://github.com/jacobemcken/clojure-blog
[2]: http://www.youtube.com/watch?feature=player_embedded&v=rsSV33XxePk
[3]: http://jsfiddle.net/er8be/3/

