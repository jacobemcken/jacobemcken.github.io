---
layout: post
status: publish
published: true
title: A blog in Clojure
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
wordpress_id: 359
wordpress_url: http://www.emcken.dk/weblog/?p=359
date: '2012-07-20 22:08:53 +0200'
date_gmt: '2012-07-20 21:08:53 +0200'
categories:
- Random hacks
tags: []
comments: false
---
I decided to write a blog in [Clojure][1] - my reasons:

  * It is alot more motivating working on something "real" - and I need practice to learn
  * Make [markdown syntax][2] for blog posts a first class citizen. This is is a tough fight with Wordpress (was? - haven't poked at for some time now). Though wysiwyg (html) is just as important... now I just have to figure out a way to make both work well in parallel :D
  * Instead of just storing posts in the database and generating pages dynamic on request, I want the blog to generate static html pages whenever a post is created or updated. The same with comments.

I have put my code on [Github][3] and I'll share my design ideas as it progresses.

[1]: http://clojure.org/
[2]: http://daringfireball.net/projects/markdown/
[3]: https://github.com/jacobemcken/clojure-blog

