---
layout: post
title: What makes software stable
categories:
- Programming
comments: true
---

Having been given a new area of responsibility as team lead inspired
me to think about aspects of coding beyond just typing it.

I've experienced we where chasing our own tail. Trying to close issues
"fast" and hurry on to the next in line. But something wasn't working.
Every time we changed a comma in one end, something in the other end
would break horribly. Bug fixing was feeling like killing a
hydra. I started blaming the "fast" way of doing things.

I needed to figure out what was wrong with our approach and transition
into one that would result in "making stable software". My gut feeling
told me that we needed to become better at "decoupling" and "breaking
the code into smaller pieces".

But I was missing the direct link between my gut feeling and how it
would translate into "stable software" until I saw a tweet which
brought the things together:

Source [@frankvilhelmsen][1]

> one of the most important quality parameters for stable code is that
> it not change too much - apart from the essential bugs of course #hint

Okay I could easily follow the logic: Code that doesn't change much,
is usually a sign of stable code (why change it, if it works -
right?).

The solution would be to write code that doesn't need to be changed in
the first place, and when "decoupling" and "breaking code into smaller
pieces" are done correctly, it does exactly that. Code impacted by
changes is greatly reduced.


Example:

Imagine a piece of software consisting of a single function. Now every
time a change is made (a bug is fixed or a feature is implemented) in
that function 100% of the system is changed. If instead it was
possible to split this function up into 3 functions then having to
change a function would only mean that 33% of the system was changed.

33% beats 100% every time.


And before someone says it I would like to entertain the possibility
that the exact same code from the 3 functions was gathered in 1
function. What would be the difference?

The computational results of the two pieces of code right then and
there would of course be the same. But the fact that the code isn't
already split up is in my experience a sign of too tightly coupled
code. Also having the code split up usually makes it both easier to
test and reason about (win/win).

Having to change a long chain of functions to fix a bug or implement a
new feature is usually ALSO a sign of too tightly coupled code. Just
to point out that having a lot of functions doesn't necessarily
decouple the code :-)

Anyways... for identifying both what and how to "decouple" and "break
down" code, I use my "hands on" experience from functional programming
and async systems. Also reading about [S.O.L.I.D. principles][2] is i
good source of inspiration, though nothing beats "hands on".


[1]: https://twitter.com/frankvilhelmsen/status/781050860260433920
[2]: https://en.wikipedia.org/wiki/SOLID_(object-oriented_design)
