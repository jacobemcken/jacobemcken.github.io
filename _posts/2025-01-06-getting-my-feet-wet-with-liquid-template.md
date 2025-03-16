---
layout: post
title: "A Clojure Jekyll adventure: Getting my feet wet with Liquid"
description: "Taking the plunge into the Clojure Liquid templating library for my Jekyll clone was anything but straightforward."
image: /assets/img/feet_getting_wet_in_lake.png
image_alt: "Midjourney prompt: Lower legs with pants rolled up, standing in shallow water of a big lake in a fantasy national park. Close up on legs frog view --no people, animals. Using image from last blog post as style reference."
categories:
- Programming
tags:
- Clojure
- Blog
- Jekyll
comments: true
excerpt_separator: <!--more-->
---

The [Liquid][1] templating language is essential for Jekyll and its themes.
While a Clojure implementation exists in the form of a library named [Wet 💧][2],
ironically, the library is missing most functionality categorized as
[*Tags > Template* in the Liquid documentation][3].

Though we'll touch on "writing code" in this next part of my adventure,
it perfectly illustrates how problem-solving is more about thinking than just writing code.

This is the second part of the series: "A Clojure Jekyll adventure",
exploring how Clojure fares from a "Jekyll perspective".
You can find the previous part here: [How my Jekyll blog became a Clojure adventure][0].
<!--more-->


## Different forks

To find a good starting point for implementing the missing functionality,
I first needed to familiarize myself with the differences between the Wet library's forks.
I reviewed the latest commit and issues history of the repositories,
to assess its state and direction.
The main repository's last activity was back in February 2019 (almost six years ago),
and it had a single unanswered issue from October 2018,
which usually signals: Unmaintained 👻.

The first fork had no changes compared to its source,
making it easy to rule out.
The remaining two forks showed some activity
but seemed to have different goals.
One fork, [(`amperity/wet`)][4], had recent activity (4 months old)
and focused primarily on resolving ClojureScript build warnings.
The other fork, [(`dvdreddy/wet`)][5], which hadn't been updated in over four years,
had more comprehensive changes
like alterations to the core parsing grammar handled by [`instaparse`][13].

Both forks had one thing in common though, they were sending the signal:
*I'm just scratching my own itch, move along.*
Neither allowed issues to be created
nor did they include mechanisms to expose regressions in their fixes.

Choosing not to take on the responsibility of an Open Source project
is completely reasonable — it’s a big commitment.
I was hesitant to publicly fork the Wet library myself,
worried about potential expectations I might not be able to meet.

Without instaparse experience to vet the updated grammar
or new test cases in the forks,
I didn't feel confident trusting the changes.
Using the original repository as my starting point
felt like the safest option.
Cherry-picking commits from the forks could always be done later
if the need arose.


## Overview of what needs implementing

One might think I’d be ready to start coding at this point,
having established a starting point and all.
But it wasn’t as straightforward as I had hoped.
Jekyll ties theme files together using the `include` tag,
which also allows parameters.
Yet, the Liquid documentation doesn't mention parameters at all.
Moreover, the Liquid documentation emphasizes:

> The `include` tag is deprecated; please use `render` instead.
>
> ...
>
> It has been deprecated because
> the way that it handles variables reduces performance
> and makes Liquid code harder to both read and maintain.

Luckily, the `render` tag does allow for parameters,
even using multiple different syntaxes...
but none that match the Jekyll `include` tag syntax. 😵‍💫

[Liquid syntaxes][6] examples:
{% raw %}
```
{% render "product", product: featured_product %}
{% render "product" with featured_product as product %}
```
{% endraw %}

[Jekyll syntax][7] examples:
{% raw %}
```
{% include image.html url="http://jekyllrb.com" %}
```
{% endraw %}

Jekyll was supposed to be using the Liquid template language,
but apparently Jekyll has just "rolled its own",
although heavily inspired by Liquid.
My head is spinning with conflict of interests at this point.

Should I stay true to the library description?:

> wet is a pure Clojure port of the Liquid template language...

or should I focus on solving my own problem,
and let Jekyll requirements bleed into the library.

Sadly, there are even more things to consider.
Jekyll's `include` tag relies on internal global state,
due to the way it handles variables across templates.
It is also tightly coupled to the file system (yay even more state).
Coupling to the filesystem
might also be the intended behavior for the `render` tag,
but the Liquid documentation is a bit vague on this point.

Both internal global state and being tied to the filesystem
will likely increase complexity and/or brittleness
in template-parsing code.

`/me exhales` 😮‍💨

Eventually, I decided to go with a `render` tag solution,
but with the assumption that templates are preloaded into memory.
This approach requires migrating my Jekyll template/theme files,
but I prefer tackling the "right" problem upfront,
even when it's more challenging.
Avoiding global state across templates,
at the cost of a little search-replace work now,
feels like the right move.

I’m barely out of the gate,
and I’ve already had to make compromises
— but I believe they will pay off in the long run.


## Can I codez now plox?

Never having used instaparse before,
I had to start reading its documentation and play a bit around.
I ended up spending several hours "preparing" before writing any code in the Wet library.

I set up at little test "Jekyll site" file structure,
with a single blog post and all my template files,
for my input data to be as small as possible.
It speeds up the feedback look while developing
and makes it easier to keep an overview.
A little Babashka script would scan the directory structure
and use Wet for parsing the template files.
Then I enabled/disabled parts of the templates
as I worked my way through all the parser exceptions.

[I've forked the Wet library][8] for this blog post,
and [created a PR][9] with the all the changes required for a full render of a post.
The PR and each commit are deliberatly structured to make sense in isolation.
If you are interested in the code, go check it out.

The most confusing aspect of the Wet library was the two "dispatch layers":
One for parsing and one for rendering.
Parsing converts `instaparse` data structures into (Wet) Clojure records,
while rendering uses multi-method dispathing on said records.
I found working with records a bit cumbersome,
though this might be due to my limited knowledge of them.

To be honest I had not added test cases initially 😇
But since I noticed how test cases could have helped med asses the other forks
I revisited my code. *"Be the change"* they say.

To generate a single blog post, I had to implement the template tags
`comment` and `render` (with and without params),
but I also had to implement a new [`empty` type][12].

I skipped implementing *[Whitespace control][10]*,
which strips whitespace adjacent to a rendered tag.
Instead, I removed its usage from my templates,
as I was only using *Whitespace control* sparsely in trivial cases.

The `empty` type was only used by the ["compression" template][11],
which is supposed to remove unecessary extra whitespace (such as indentation) from the final files.
Though implementing `empty` resolved the exceptions,
the Wet library is still leaving some whitespace which [`liquid-c`][14], used by Jekyll, does not.

Also, while writing test cases for the newly implemented `empty` type,
I noticed something that is most likely a bug.
The Wet library and Jekyll interpret the following differently:
{% raw %}
```
{% assign item_count = "" | split: "," | size %}
{{ item_count }}
```
{% endraw %}

Wet library:
```
1
```

Jekyll:
```
0
```

As I am starting to feel at home in the Wet library,
it is also time to move on to other parts of this adventure.

I am leaving a couple of loose ends, that I can come back to later:

- Whitespace control.
- Explore why the "compress" template doesn't fully remove unnecessary whitespace.
- Explore why `split` empty string works differently than Jekyll.
- Protection against circular template dependencies.
- Alternative syntaxes for `render` with params,
- All the other Liquid features that my theme/templates aren't using,
- and a ClojureScript test suite would be nice.


## Wrapping up

This experience reminded me of how easy it is to underestimate a task,
especially when we're blind to the complexities hidden beneath the surface.

That said, I’m confident in my decision to go with the `render` tag
instead of attempting to implement the `include` tag.
From experience,
the time spent analyzing and assessing a problem before choosing a solution,
always pays back multiple times over.

I also think this is a good opportunity
to reflect on my hypothesis about the maturity of the Clojure ecosystem.
While a single library doesn't represent the ecosystem as a whole,
this experience suggests a potential weakness.
Clojure is undeniably strong at its core 💪,
but when it comes to more exotic or niche features,
the community's size makes it difficult to maintain high-quality,
feature-complete libraries for every need.

The next post will most likely be about my experiences using Babashka.
Don't hesitate to reach out.


[0]: /programming/2025/01/03/how-my-jekyll-blog-became-a-clojure-adventure/
[1]: https://shopify.github.io/liquid/
[2]: https://github.com/flocktory/wet
[3]: https://shopify.github.io/liquid/tags/template/
[4]: https://github.com/amperity/wet
[5]: https://github.com/dvdreddy/wet
[6]: https://shopify.github.io/liquid/tags/template/#render
[7]: https://jekyllrb.com/docs/includes/#passing-parameter-variables-to-includes
[8]: https://github.com/jacobemcken/wet
[9]: https://github.com/jacobemcken/wet/pull/1
[10]: https://shopify.github.io/liquid/basics/whitespace/
[11]: https://github.com/penibelst/jekyll-compress-html
[12]: https://shopify.github.io/liquid/basics/types/#emptydrop
[13]: https://github.com/Engelberg/instaparse
[14]: https://github.com/Shopify/liquid-c
