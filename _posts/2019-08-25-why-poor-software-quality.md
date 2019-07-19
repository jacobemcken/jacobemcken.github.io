---
layout: post
title: Why do we allow poor software quality?
categories:
- Programming
tags:
comments: true
---


## Work environment and software

As a professional, you need to have a good work environment for optimal
performance. As a software developer, this goes beyond the desk, chair,
computer, and colleagues. The entire toolchain like editor, CI/CD, project
management, etc., is a big part of this environment. Regardless of these tools
being virtual or not countless hours are spent here. Sadly, we often neglect one
of the most critical parts of our work environment: The software itself.

I've experienced people mistake a refactoring for "just aesthetics" or "gold
plating," but a tidy piece of software is simply more maintainable. It is easier
to navigate, understand, and reason about, which makes the developer more
confident when faced with having to implement changes.

How come we allow for poor quality code?! We have to work "in" it every day. I'm
confident that if a chef or a carpenter were forced to work in a mess, it would
only be a matter of time before they went on a cleaning spree! But developers
are not allowed to clean when and however they want to.

In a poor working environment, you lose the overview. You make mistakes. It
pulls you down - eventually to the bottom.

As a junior developer, I only saw the software as what was being produced, the
output, the "final" result. But software isn't that final. Nowadays, when a
colleague would describe our software or parts of it as "done" or "finished," I
would annoyingly remind them that:

> As long as someone uses the software, the "stream of wishes for features and
> bugfixes" will flow, and we (developers) will never be truly done.

Developers might not describe their software as part of their work environment,
or even think of it as such. They don't have to. How they describe their
frustrations speaks volumes: "I wish we would have time to improve this." or "Oh
no, I've been assigned a story in THAT part of the code... AGAIN!"


## Nobody invited "poor quality"?!?

There are three things I attribute to poor quality software: inexperience, lack
of time, and decay. An inexperienced or sloppy programmer with limited
supervision probably has the most direct impact of the code quality. Being a
direct cause is NOT the same as being the most significant one, but it is the
easiest to explain (and the easiest to blame).

Having a sloppy programmer is slightly different from an inexperienced one
because the result of poor quality is caused by a deliberate choice not to use
the necessary time. However, having both the experience and the willingness will
only take you so far if you aren't allowed to spend enough time.

> "Nothing important comes into being overnight; even grapes or figs need time
> to ripen. If you say that you want a fig now, I will tell you to be patient."
>
> — Epictetus

While a company can replace a sloppy programmer, it might not be enough. That
company will see hard times ahead if its decision making people fail to accept or
even **understand** the fact that good software takes time. **Lack of time is
software quality's real enemy.**

Doing a half-arsed job, either by choice or circumstances, will at the least
result in harder to maintain software. On top of that, chances are that it is
also poorly specified, tested, and documented (if at all). This is how software
quality gets sacrificed at the altar of the "deliver now" philosophy, so common
among managers on competitive markets.

It doesn't matter what your excuse or your reason is. If you keep choosing
"fast" over "quality" for deliveries, at some point, it becomes IMPOSSIBLE to
deliver fast. Also turning such a piece of software around will take a massive
amount of effort and time - therefore money.

Lastly, software has a tendency to deteriorate over time, even (and sometimes
especially) those parts that aren't changed. This happens because the light in
which the software is seen, changes as a developer gains experience or new
business requirements are introduced. Suddenly flawed decisions are highlighted.
Avoiding flawed decisions is unrealistic, and the software will ALWAYS have some
code lying around that could be improved. That is OK. It is possible to navigate
around a few inconveniences. Problems arise when poor code quality (like weeds)
isn't kept in check. Adding a time constraint on top will prevent the team from
addressing these issues and, at the same time, make it less likely to choose
optimal implementations for upcoming features.


## How do we improve our situation?

The issue runs deep. We are dealing with sloppy or inexperienced programmers,
deadlines, poor change management, hasty decisions under competitive pressure,
and the widespread practice of evaluating code quality with "but it works?!" -
And the list could continue. This way of working is embedded in company cultures
big and small and can't be changed on an individual level. A single person
trying to "take enough time" can end up (wrongly) being identified as being
slow, and even when allowed to stick to their methods the amounts of crap being
produced elsewhere will quickly flood the good parts.

The first step of changing the circumstances is admitting that there is an
actual problem and that you most likely are part of it yourself.

**Demand a good working environment and acknowledge that (software) quality
takes time along with continuous care.**
