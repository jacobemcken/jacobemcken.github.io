---
layout: post
title: Symptoms of lacking software quality
categories:
- Programming
tags:
- Clojure
- Git
- Test
comments: true
---

What makes up good software quality is different from person to person, making
it somewhat subjective and hard to define measurably.

It is discouraging working on projects where you feel that every time the team
fixes a bug, it introduces two more. Having a hard time tracking down the bug
and understand how it hit production in the first place makes matters worse.
Unable to confidently give assurance that it will not happen again is downright
frustrating.

Having been through a few different projects myself, I noticed that some
symptoms seem to reappear.


### Symptom 1 - None to too few test cases

As a developer, having automatic tests makes you feel more confident making changes
to the codebase, knowing there is a safety net that reduces the risk of
introducing bugs. A codebase with few automatic test cases can indicate that the
task of writing tests is complicated. Test complexity is usually proportional to
the complexity of the code subject to testing.

Code complexity should NOT be confused with complex business logic. You can have
straightforward code with very complex business logic. In my experience, code
complexity increases over time, mostly because the code tends to become harder
and harder coupled. Coupling is just what happens by default without careful
consideration of every new feature and bug fix.

Demanding a high degree of test coverage of an overcomplicated codebase is going
to VERY time consuming and very likely not worth the time investment. Instead,
focus on how to simplify (decouple) code.

I find it an excellent strategy to concentrate the side-effects i.e., in specific
functions and namespaces. Simple code will beg for test cases.


### Symptom 2 - Loads of build/runtime warnings

On the one hand, a warning in itself is not a problem. It is just a warning. But
warnings are noise in which a new (maybe important) warning can hide. On the
other hand, suppressing all warnings isn't a solution either, as you might miss
when that critical warning suddenly shows up.

We should not treat warnings superficially - say, solely by running the software
and concluding *"hey - it still seems to work"*. A warning is often about some
special case that will trigger unwanted behavior. Understand *why* the warning
is there. Evaluate if it could have an impact either now or in the future. Make
keeping a warning around a well-considered choice and document your reasoning.
Such documentation could be as simple as a comment by the line of code that
introduced the warning. The comment should include the warning itself, so it is
easy to search for (i.e., in the future, when you have forgotten or a new team
member doesn't understand why).

This approach is not about having a religious "zero warnings" policy. Warnings
describe potential problems, and ignoring them will deprive you of the
opportunity to make a deliberate choice. Furthermore, having too many warnings
will hide new warnings in the noise. Find the right balance.


### Symptom 3 - Sparse code documentation

Lots of languages allow for documentation as part of the code. Simply demanding
this documentation to be present does not solve the problem. I've seen lots of
documentation that repeats a function name or repeats what the function does
line by line. You don't want that. Documentation is HARD.

It is worth repeating... **Documentation is HARD.**

Good documentation isn't just a long term investment, "only" useful for
developers somewhere in a distant future. For writing documentation like
[JavaDoc][1], Clojure doc-strings, etc., I've experienced that I need to
understand things more thoroughly to articulate the meaning for somebody else.
By immersing myself, I found a better solution or identified a problem while
writing documentation. It is almost like ["rubber ducking"][2], except you do it
when you think you got it all figured out.

[1]: https://en.wikipedia.org/wiki/Javadoc
[2]: https://en.wikipedia.org/wiki/Rubber_duck_debugging


### Symptom 4 - Poor commit hygiene

`Fix bug` and `Fix bug for realzies this time` are just outright useless commit
messages. Poor commit hygiene signals reviewing and bug-hunting as low
priorities. Well-formed commits allow for a smoother review process. Bug hunters
are now so much better off because the commit history does provide them context.
Temporarily fixing a bug might be as easy as reverting a commit, which would not
be possible with poor commit hygiene, such as squeezing multiple changes into a
single commit.

You help your team and *yourself* when providing quality commit messages. I will
use this opportunity to direct your attention to Chris' excellent guide: [How to
Write a Git Commit Message][3].

Remember that the commit history IS NOT ABOUT YOU (developers come and go), but
about how the software evolved. Six months from now, nobody cares that you
`correct code based on review feedback`. Imagine having baked a great batch of
cookies; nobody is interested in the two failed attempts that got thrown away.
What is interesting is the "recipe" to make those cookies (assuming you came
prepared).

I strongly encourage sanitizing your commits via "rewrites" in a feature branch
because it allows describing how the software evolved. Rewriting commits leaves
behind all detours and dead ends (usually as comments in the PR). "Lessons
learned" and other things worthwhile remembering belong in the documentation or
as comments in the code - never in a commit message or worse: Somewhat derivable
from the "commit history."

[3]: https://chris.beams.io/posts/git-commit/


### Symptom 5 - Poor review process (LGTM)

A good review process can help to reduce "poor commit hygiene." But the review
process itself adds so much more value:

* the debate on a solution is a learning tool for submitter, reviewer, and
  readers snooping in,
* it can assist with finding bugs early, and
* it increases code quality i.e., with better function naming and more
  understandable documentation, etc.

I've seen loads of `LGTM` (read: Looks Good To Me) approvals on PR's, and when
that is the norm, it signals the team doesn't take the review process seriously
enough.

[Angie Jones (@techgirl1908)][4] did a [superb blog post (The 10 commandments of
navigating code reviews)][5] on how to embrace code reviews.

[4]: https://twitter.com/techgirl1908
[5]: https://techbeacon.com/app-dev-testing/10-commandments-navigating-code-reviews


## Conclusion

Clinging to easy to measure metrics like test and doc coverage, enforcing
linting or rules about commit messages (length and line breaks), etc., can not
and will never guarantee high-quality software on its own.

The common denominator of all the symptoms above is that they usually appear
when we cut corners, not taking the required time.

The actual code is only a small piece in the big puzzle of software quality. A
change in processes like planning and review, communication, documentation,
available tools, tooling usage, culture, and prioritizing time can significantly
affect the quality of software, both for better and worse.

Avoid symptom treatment. Look for the real problem.
