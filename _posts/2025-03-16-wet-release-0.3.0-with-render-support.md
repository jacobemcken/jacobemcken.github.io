---
layout: post
title: "A Clojure Jekyll adventure: A Wet side quest (Wet 0.3.0 released)"
description: "A new version of the Clojure library for rendering Liquid templates has been released, sadly without 'Whitespace control' this time around."
image: /assets/img/knocked_over_flask_on_ground.webp
image_alt: "Midjourney prompt: A knocked over empty elixir flask lying on the ground with a bit of liquid dripping from it, on a cold morning with mountains in the horizon. Using image from first blog post as style reference."
categories:
- Programming
tags:
- Clojure
- Blog
- Jekyll
- liquid
comments: true
excerpt_separator: <!--more-->
---

I ventured far and wide on my Clojure Jekyll adventure right after the last check-in.
Then, fatigue (and life) caught up with me,
and I had to set up camp for a little while.

The other day, I went on a little side quest and officially released
my fork's changes to the Liquid templating library [Wet 💧][1].

This is the third part of the series: ["A Clojure Jekyll adventure"][2].
If you’re curious about what happened on the previous part of my journey,
check out: [Getting my feet wet with Liquid][3].
<!--more-->

The library is now at version `0.3.0`, indicating that new features have been added,
but I still don't think it is ready to be tagged as version 1.0.0.

For a quick recap, here’s what’s new:

- `render` tag with parameters, allowing to isolate reusable template parts.
- `comment` tag, because not everything needs to end up in the "final product".
- `empty` type for assertions like: {% raw %}`{% if coll == empty %}`{% endraw %}

I put some [serious effort into cracking *"Whitespace control"*][4]
(stripping unnecessary adjacent whitespaces controlled by special Liquid tags),
but it proved to be a tough nut.
I suspect it will take fundamental changes to the original implementation to get it right
without overcomplicating the code.
However, it might just be me
— I still don’t fully understand the Wet library and Instaparse.
Any help would be much appreciated!

As I teased earlier, I also made significant progress on the Clojure Jekyll clone.
But after being defeated by 'Whitespace control',
I needed some time to recover before I had the energy to write a blog post.

Originally, I planned to share details about the clone's progress in this post,
but it became too lengthy and unfocused,
so I decided to dedicate a separate blog post to it instead.

Stay tuned.

Plus, I get to create another awesome picture for it with Midjourney. 🖼️

[1]: https://github.com/jacobemcken/wet
[2]: /programming/2025/01/03/how-my-jekyll-blog-became-a-clojure-adventure/
[3]: /programming/2025/01/06/getting-my-feet-wet-with-liquid-template/
[4]: https://github.com/jacobemcken/wet/pull/2
