---
layout: post
title: How to compare JSON files offline
image: /assets/img/woman_comparing_piles_of_pebble_variation_2.png
image_alt: A woman with long hair sitting on the ground alone outside, thoughtfully observing two piles of pebble further away in front of her in a grassy scene. The woman is looking slightly to the side. The style is cartoon. --v 6.0
categories:
- Programming
tags:
- Linux
- JSON
- diff
- DevOps
comments: true
excerpt_separator: <!--more-->
---

At work, I needed to compare some huge (almost identical) JSON data structures.

Since the data was potentially sensitive, I wanted to do it offline.
Sending data over the wire to one of the myriad of online services was a no-go.

There are probably built-in tools for IDE's like VS Code, Emacs and the like,
but I wanted something I could use on the Linux <abbr title="Command Line Interface (a.k.a. terminal)">CLI</abbr>

<!--more-->

First I looked for a single tool. I read someone referencing a tool in Python,
but the guides I found deterred me due to the "many" steps and dependencies.

I also stumbled over a Windows user, [asking for advice on how to diff JSON on StackExchange][1].

When I by accident realized I could get `jq` to sort all the keys in a JSON file,
I decided to combine `jq` and `diff` to solve my problem.

```bash
jq -S . A.json > A-sorted.json
jq -S . B.json > B-sorted.json
diff A-sorted.json B-sorted.json
```

Since I already had both tools installed and I use both tools regularly,
it seemed like the best solution for me.

[1]: https://softwarerecs.stackexchange.com/questions/90102/offline-json-diff-tool