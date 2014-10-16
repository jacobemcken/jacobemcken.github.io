---
layout: post
status: publish
published: true
title: A plugin system for clojure-blog
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
wordpress_id: 368
wordpress_url: http://www.emcken.dk/weblog/?p=368
date: '2012-07-21 22:48:46 +0200'
date_gmt: '2012-07-21 21:48:46 +0200'
categories:
- Random hacks
tags: []
comments: []
---
In order to support different syntax for posts, smileys and hook-ins in general I searched the web for inspiration and found the following link which have some cool info:

http://stackoverflow.com/questions/10272559/how-best-to-structure-and-build-clojure-apps-with-plugins

I've started small and decided to support changing the post content via a hook-in before handing it over to the html generator Enlive. The way it works is by registering any number of functions which can transform post content in a chain (vector).<br />
Zero functions means no transformation and the post content is serves as-is.

Here is some example code which can demo the thoughts behind the design so far:

    (defonce changes (atom []))

    (defn reg-change<br />
      "Registers a fn (change) to a hook"<br />
      [hook-name f]<br />
      (swap! hook-name conj f))

    (defn apply-changes<br />
      "Applies all changes in the vector to the input"<br />
      [change-vec input]<br />
      (reduce #(%2 %1) input @change-vec))

    (apply-changes changes "Test is good")

    (defn good-is-bad [text] (clojure.string/replace text "good" "bad"))

    (reg-change changes good-is-bad)

    (apply-changes changes "Test is good")

