---
layout: post
title: About cutting corners
categories:
- Programming
comments: true
---

It takes time to understand a problem, because before you can fully
understand it, you need to also understand its context. As a code base
grows, depending on how well the code is structured, the context in
which you need to understand the problem can (and often will) grow.

When a problem or its context is misunderstood there is a tendency to
oversimplify the problem. I'm all for finding a simple and elegant
solution but not at the cost of correctness. Oversimplified solutions
will not be able to handle all special cases, and special cases lead
to crashes or data corruption... or both. This in turn leads to
maintenance and clean up by the programmer which I would much rather
have coding on this next big thing.

The available time to implement a solution is not only challenged by a
push for quick deliveries but also a lack of respect that *creating
software is actually hard*. I've experience this lack of respect from
all involved parties (including the programmer).

Even though I strive to "do things right", I also think it is okay and
often necessary not to. *But it should be a conscious decision*.

I.e. making sure to handle two use cases (corner cases) will increase
development time but at the same time those a very unlikely to happen
or if they happen they are very easy to correct manually. If someone
makes a conscious decision that these cases should be handled outside
the software, then it is okay.

It is NOT okay (and very short sighted) when the pressure doesn't
allow collecting the necessary information to make that decision.

So by all means cut corners - but only do so together with proper risk
assessment.
