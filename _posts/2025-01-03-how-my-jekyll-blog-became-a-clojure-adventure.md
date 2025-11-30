---
layout: post
title: How my Jekyll blog became a Clojure adventure
description: Exploring what it would take for Clojure to replace Ruby apparently takes you unexpected places. Could Clojure be used to replace Jekyll?
image: /assets/img/a_clojure_adventure.png
image_alt: "Midjourney prompt: A blogging adventure in the programming language Clojure. A scientific approach. Post-processing (GIMP): Overlaying a Clojure logo on top of the moon."
categories:
- Programming
tags:
- Clojure
- Blog
- Jekyll
comments: true
---

On the 10th anniversary of [Jekyll][2] being my blog engine of choice,
my curiosity (and spare time during the holidays) got the better of me.
I wanted to explore how well Clojure would fare
compared to the original Jekyll implementation in Ruby.

My reasons for having stuck with Jekyll for such a long time:
- No database simplifies infrastructure for running/hosting.
- Static files minimize the attack surface for hackers
  and simplify content delivery.
- Avoiding JavaScript was a bonus, preventing dependency-related breakages.
- [Liquid][6] - a simple template "engine"
  that didn’t require knowledge of a specific programming language.
  Being able to read and write plain HTML was enough for most tasks.
- Jekyll is a first-class citizen in GitHub Pages.
  - Easy and free hosting.
  - Significant adoption has provided a wealth of resources for help
    in the form of blog posts and videos.
    All these accumulated experiences have made a lot possible
    with this otherwise "ancient" tool.

A few times, I wanted to extend Jekyll's functionality
but was quickly deterred by what felt like overwhelming amounts of Ruby code.
It seems I’ve been spoiled by the conciseness of Clojure. 😅

Back in 2012, I briefly experimented with implementing a blog in Clojure
as an alternative to WordPress.
That idea was quickly shelved when I discovered Jekyll shortly after.
Jekyll solved the problems exactly the way I wanted.
During the Christmas holidays in 2015,
I migrated my old WordPress blog to Jekyll.
Now, it feels like things have come full circle.
This Christmas, I found myself exploring the idea of making Clojure do what Jekyll does
— not in an abstract sense, as tools like [Cryogen][3] and [quickblog][4] already handle that well.
Instead, I wanted to preserve my existing Jekyll file structure
and replace the code for generation.
In theory, Clojure seems like a perfect fit for this kind of problem.

After spending several days on it I paused,
as I was afraid of just "wasting my time".
Getting things to work wasn't as easy as I had hoped,
but it wasn't as hard as I had feared either.
Something in me wanted to keep going,
and not because I had a strong wish to replace Jekyll.
This endeavor was taking me to so many unexpected places.
But without people to share my journey with, it just wasn't as motivating.


## Writing hypotheses

I eventually decided to ask the Clojure community on the [Clojurian Slack][1]
if they would find this work interesting.
This also forced me to reflect on what a result could look like.

- How difficult is it allowed to be?
- How much code (Lines of Code) would it require?
- How would performance compare to other implementations?

These questions resulted in a few hypotheses...


### Difficulty

I suspect that I would be able to evaluate the maturity of the Clojure ecosystem.
Hypothesis:

> The more mature, the easier it would be to implement
> and the less code I would need to write/maintain.


### Lines of code (LoC)

Hypothesis:

> 80-85% feature complete with Jekyll should be enough for me to run my own blog,
> and it would probably require less than 800 LoC.
> 100% feature completeness is probably not possible, but close to it (maybe 98-99%)
> would mean that most Jekyll users could just use the tool as a drop-in replacement,
> and it would probably require less than 3,500 LoC.

For context the [Ruby implementation][7] uses a little over 10,000 LoC
(`find lib/jekyll -name '*.rb' | xargs wc -l`),
while the [Go implementation][8] which isn't at feature parity uses 6,300 LoC
(`find . -name '*.go'|grep -v test | xargs wc -l`).
All LoC counts are on purpose without test cases.


### Performance

The Go implementation brags about a x20 performance compared to Ruby (and rightfully so).
Hypothesis:

> A Clojure implementation would be faster than Ruby...
> but I have no idea if it can compete with Go without a substantial effort.


## What happens now

My hypotheses aren't very scientific,
among other things I have no formula for measuring "ecosystem maturity" or "feature completeness".
Still, I believe the results can still be compelling
if the process of exploring these hypotheses is clear.

There is still some way to go before I can test my hypotheses,
but I've gathered several notes on the experiences so far that are equally intriguing.
Ranging from the more philosophical end like: *What does it mean to maintain an Open Source project?*
To the more technical bits: *Why code ended up as it did?*

I haven’t settled on the next topic for the series yet.
It could explore my approach to building the CLI tool
(how I break things down)
or the missing features in the Clojure Liquid template lib ([Wet][5]).
Maybe you have a better idea or angle... let me know.

[1]: https://clojurians.slack.com/archives/C03S1KBA2/p1735742059647469
[2]: https://jekyllrb.com/
[3]: http://cryogenweb.org/
[4]: https://github.com/borkdude/quickblog
[5]: https://github.com/flocktory/wet
[6]: https://shopify.github.io/liquid/
[7]: https://github.com/jekyll/jekyll
[8]: https://github.com/osteele/gojekyll