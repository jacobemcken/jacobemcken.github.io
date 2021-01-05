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
it somewhat subjective and hard to define in a measurable way.

It is discouraging working on projects where you get the feeling that every time
a bug is fixed, two new are introduced. Having a hard time tracking down the bug
and understand how it hit production in the first place, makes matters worse.
Unable to confidently give assurance that it will not happen again is down right
frustrating.

Having been through a few different projects some symptoms seemed to reappear.


### Symptom 1 - None to few test cases

As a developer, having automatic tests makes you feel more confident making
changes to the code base, knowing there is a safety net that reduces the risk of
introducing bugs. When a code base is having few automatic test cases, it is
often a sign of writing tests is complicated. Test complexity is usually
proportional with the complexity of the code being tested. Code complexity
should NOT be confused with complex business logic. You can have very simple
code with very complex business logic. In my experience code complexity
increases over time mostly because the code have a tendency to become harder and
harder coupled. This is just what happens by default unless great care is taken
with every new feature and bug fix. Demanding a high degree of test coverage of
an overcomplicated code base is going to VERY time consuming, and very likely
not worth the time investment. Instead focus on how to simplify (decouple) code.
Personally I find it a good strategy to concentrate side-effects. Simple code
will beg for test cases.


### Symptom 2 - Loads of build/runtime warnings

So a warning in it self isn't a problem, after all it is just a warning. But
warnings are unnecessary noise in which a new (maybe important) warning can
hide. Suppressing all warnings isn't a solution, again you will not catch when
that extra important warning suddenly shows up. Warnings should not be allowed
to stay solely by quickly running the software and concluding *"hey - it still
seems to work"*. A warning is often times about some special case that will
trigger unwanted behaviour. Understand why the warning is there. Evaluate if it
could have an impact either now or in the future. Make keeping a warning around
a well considered choice and document why. Such documentation could be just a
comment by the line of code that introduce the warning. The comment should
include the warning itself so it is easy to search for (i.e. in the future when
you have forgotten or a new team member doesn't understand why).

This is not about having a religiously "zero warnings" policy. Warnings
describes potential problems and ignoring them will deprive you the opportunity
to make a deliberate choice, and having too many warnings will hide new warnings
in the noise. Find a good balance.


### Symptom 3 - Sparse code documentation

Lots of languages allow for documentation as part of the code. Just demanding
this documentation to be present does not solve the problem. I've seen lots of
documentation that basically just repeats a function name, or repeats what the
function does line for line. You don't want that. Documentation is HARD.

This is worth repeating... **Documentation is HARD.**
 
Good documentation isn't just a long term investment, "only" useful for
developers somewhere in a distant future. Writing documentation like
[JavaDoc][1], Clojure doc-strings etc. I've experienced that I need to
understand things more thoroughly to be able to articulate the meaning for
somebody else. By immersing myself, on many occasions I found a better solution
or identified a problem while writing documentation. It is almost like ["rubber
ducking"][2], except you do it when you think you actually got it all figured
out.

[1]: https://en.wikipedia.org/wiki/Javadoc
[2]: https://en.wikipedia.org/wiki/Rubber_duck_debugging


### Symptom 4 - Poor commit hygiene

`Fix bug` and `Fix bug for realzies this time` are just outright useless commit
messages. Poor commit hygiene signals reviewing and bug-hunting is down
prioritized. Well formed commits allows for an easier and more effective review
process. Bug hunters are now so much better off because the commit history
actually provides context. Temporarily fixing a bug might be as easy as
reverting a commit. This isn't possible with poor commit hygiene where multiple
changes have been squeezed into a single commit maybe even without a good commit
message.

You empower your colleagues to help yourself by providing quality commit
messages. I will use this opportunity to to direct your attention to Chris'
excellent guide: [How to Write a Git Commit Message][3].

Remember that the commit history IS NOT ABOUT YOU (developers come and go), but
about how the software evolved. Six months from now nobody cares that you
`correct code based on review feedback`. Imagine having baked an awesome batch
of cookies, nobody is interested in the 2 failed attempts that got thrown away.
What is interesting is the "recipe" to make those cookies (assuming you came
prepared). This is why I strongly encourage commit "rewrites" in a feature
branch, because it gives an opportunity to clearly tell the story of how the
software evolved. All detours and dead ends are left behind in the PR (usually
as comments). While "lessons learned" and other things worthwhile remembering
belongs in documentation or as comments in the code - never in a commit message
or worse: Somewhat derivable from the "commit history".

[3]: https://chris.beams.io/posts/git-commit/


### Symptom 5 - Poor review process (LGTM)

A good review process can help with reducing "poor commit hygiene". But it can
add so much more value. Solutions can be debated from which both submitter and
reviewer (and readers snooping in) can learn from, bugs can be found early,
quality increased i.e. with better function naming and clearer documentation
etc.

I've seen loads of `LGTM` (Looks Good To Me) approvals on PR's, and when that is
the norm it signals the review process isn't being taking seriously enough.

[Angie Jones (@techgirl1908)][4] did a [super blog post (The 10 commandments of
navigating code reviews)][5] on how to embrace code reviews.

[4]: https://twitter.com/techgirl1908
[5]: https://techbeacon.com/app-dev-testing/10-commandments-navigating-code-reviews


## Conclusion

Clinging to easy to measure metrics like test and doc coverage, enforcing
linting or rules about commit messages (length and line breaks) etc. cannot and
will never guarantee high quality software on its own.

What the above symptoms all have in common is that they usually appear when we
cut corners not taking the required time.

The actual code is only a small piece in the big puzzle of software quality. A
change in processes like planning and review, communication, documentation,
tools available and how they are used, culture and how time is prioritized, can
all have a significant effect on the quality both for better and worse. 

Avoid symptom treatment and look for the real problem.
