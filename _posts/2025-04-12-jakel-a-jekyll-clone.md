---
layout: post
title: "A Clojure Jekyll adventure: Jakel materializes from the mist (a Jekyll clone)"
description: "Jakel, a Jekyll clone, written in Clojure, is starting to come together. Everything you need for a night of adventure."
image: /assets/img/book_at_a_stone_terrace.webp
image_alt: "Midjourney prompt: In a clearing in a forrest is a round stone terrace with a pedestal holding an open holy book. Using image from first blog post as style reference."
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

I'm flipping through my quest log to reflect on the trials I've faced recently.
Most improvements to the Clojure-based Jekyll clone
focused on making the blog navigable.

This is the fourth part of the series: [*“A Clojure Jekyll Adventure”*][1].
If you're curious about what happened in the previous chapter of my journey,
check out: [A Wet side quest][2].

<!--more-->

First, the work I've done so far on
[the Jekyll clone in Clojure has been named ***Jakel*** and published on GitHub][3].
My blog is now fully renderable and navigable with a subset of recent posts
— once I remove a few instances of the infamous *Whitespace control*
(which I've ranted about at length in previous posts).

Navigation is now possible because Jakel includes
a built-in web server serving the generated files.
Reacting live to file changes (like editing a Markdown file) is still on my to-do list.

But instead of dwelling on what's still missing,
let's dive into what's actually there and working.

You can follow the commit history to retrace the path I took during development.
I've made a strong effort to keep commits focused (single responsibility)
and to write meaningful commit messages.

It was very satisfying to write the [function that applies *layouts* recursively][7],
which later got refactored into:

```clojure
(defn apply-layouts
  "Takes a template and a Liquid context which must match `wet.core/render` options.
   The template data structure looks as the following:

       {:layout \"default\"
        :body \"some template\"
        ...}

   The `liquid-context` contains templates that can be referenced by a layout
   using the `render` tags (Wet specific)."
  [initial-template liquid-context layouts]
  (loop [layout-name (:layout initial-template)
         template initial-template]
    (if-not layout-name
      template
      (let [{:keys [body params layout]} (get layouts layout-name)]
        (recur layout
               (->> (wet/render body (update liquid-context :params
                                             assoc :content (:body template) :layout params))
                    (assoc template :body)))))))

```

For those unfamiliar with Jekyll lingo,
[*layouts*][9] are a sort of template that wrap content
— like headers and footers around a page.
Layouts can also include external templates using the `include` tag.
Though both templates have some of the same characteristics,
one is used around content while the other is used within.

But I didn't feel the real breakthrough until I had [pagination][4] working.
The implementation is based on [`jekyll-paginate-v2`][4c] and supports most of its features.
Only later did I discover that GitHub Pages is stuck on version 1.
Fortunately, it's backward compatible 😅

I've deliberately avoided side effects in the pagination code.
Instead, filenames and content are passed down,
assuming a generic “content writer”
will be better suited for change detection and parallel file writing.

Clojure's terse syntax continues to amaze me.
I've managed to cover a wide range of scenarios with simple, readable, and robust code
— yet the entire pagination logic fits in just 80 lines.
For comparison, the [Ruby][4a] and [Go][4b] versions (version 1) are 145 and 49 lines, respectively, excluding comments.
To be fair, the Ruby version handles some filesystem tasks
that seem imposed by Jekyll's architecture,
which likely inflates its size.
Since the Clojure version also supports several config options unique to version 2 of the plugin,
I'd say it holds up really well.

I've addressed an issue mentioned in a previous post,
about Jekyll only supporting the now-deprecated Liquid `include` tag.
I resolved this by pre-processing Liquid files to [convert include tags into render tags][5],
allowing the Wet library to process templates as if `include` tags never existed. 😎

Some minor polish changes around dates and links also found their way into the code,
and I refactored parts of the codebase to prepare for upcoming features and improve maintainability.

My original hypothesis was
that I could generate my own Jekyll blog with fewer than 800 lines in Clojure.
That mark is approaching fast, even without counting the Liquid templating library (Wet).
Using 22 lines just to support [ordinal dates][8] doesn't help.
And with plugins for *Sitemap* and *RSS feed* generation still missing,
chances are looking slim.

One could argue that a significant portion of the code counting toward my target
are plugins, and thus "external," much like in the Ruby implementation of Jekyll.
But to be honest, that wasn't how I saw it when making my hypothesis.
It's safe to say I'm not as confident about staying within my target anymore. 😥

On a closing note, it is worth highlighting a few known issues.
The Clojure Liquid template implementation in Wet still doesn't support Whitespace control,
and the compression plugin behaves differently than with Jekyll, which it shouldn't.
[`markdown-clj` doesn't handle HTML comments][6] as expected,
which is particularly annoying when using `<!-- more -->` as an *excerpt separator*.

Jakel has it all: rough edges, exotic and mysterious error messages,
and maybe even plot twists.
Everything you need for a night of adventure.

It's been quite the journey so far, and there's still plenty to explore.
If you've got thoughts, feedback, or just want to chat about Clojure and templating,
I'd love to hear from you!

[1]: /programming/2025/01/03/how-my-jekyll-blog-became-a-clojure-adventure/
[2]: /programming/2025/03/16/wet-release-0.3.0-with-render-support/
[3]: https://github.com/jacobemcken/jakel
[4]: https://github.com/jacobemcken/jakel/blob/0c85e7fbf96f895a5f1d1646911c7686b02d99fa/src/pagination.clj
[4a]: https://github.com/jekyll/jekyll-paginate
[4b]: https://github.com/osteele/gojekyll/blob/0e00fbbf7fb60eb8a6b344f734f6d9694d5a9ab5/plugins/paginate.go#L26
[4c]: https://github.com/sverrirs/jekyll-paginate-v2
[5]: https://github.com/jacobemcken/jakel/commit/0c85e7fbf96f895a5f1d1646911c7686b02d99fa
[6]: https://github.com/yogthos/markdown-clj/issues/114
[7]: https://github.com/jacobemcken/jakel/commit/7e74437fc3e44844ef05a0740f80c1b3ffdd9531
[8]: https://github.com/jacobemcken/jakel/commit/7ae3b1c74c6a633ee4abbf43e95eb6f5f5976ac0
[9]: https://jekyllrb.com/docs/layouts/
