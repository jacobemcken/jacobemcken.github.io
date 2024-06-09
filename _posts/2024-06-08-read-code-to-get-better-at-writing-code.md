---
layout: post
title: Read code to get better at writing code
image: /assets/img/young_boy_sitting_crosslegged_reading_among_stacks_of_books.png
image_alt: "Midjourney prompt: A young and content boy sitting crosslegged reading a book. A simple solid teal color background. There are a few stacks of books in all colors and sizes around him. The style is Pixar."
description: 'In seventh grade, my teacher left me and my classmates with a quote, which I think applies to programming as well.'
categories:
- Programming
tags:
- software-musings
comments: true
excerpt_separator: <!--more-->
---

Plus 30 years ago, probably around seventh grade, I remember my teacher asking the classroom:
*How do you get better a writing essays and short stories?*

She answered the question herself, and because it took me by surprise, I still remember it today.
I had heard grown-ups with their wise words: *If you want to be good at X, you have to practice X.*
X being anything from football to piano to cooking.
My younger self had assumed that the best way to get better at writing would be:<br>
*To write.*

But it wasn't.<!--more--> *If you want to get better at writing, you need to __read__*, she told us,
and *if you want to be really good, you have to read A LOT.*
Diversity was implied, different genres and different authors, to broaden our horizons. 📚

The reason for this trip down memory lane is that I want to make the point:
If you want to get better a programming (writing code), you should read code,
and if you want to get really good you should read A LOT of code.
Not your own code - obviously. You need new input to avoid stagnating - some kind of creative stimulus.
You need to see and understand alternative ways of solving the same problem.

With more possible solutions to pick from,
I found that it is more likely that at least one of them is a really good fit.
Relentlessly trying to get better, has pushed my boundaries
for what is possible and how simple it can be achieved.
Often a good solution solves and avoids several problems in a fairly simple way.

I do my fair share of "code reading", but in several different contexts.

In my work, I review a lot of code.
During these reviews, I find everything from nitpicks like sub-optimal or absent comments,
to serious oversights.
A review is of course an opportunity to find errors,
but it is also an opportunity to avoid potential future errors.
There are many decisions made today that affect the risk of introducing a bug six months from now.
Even when *the software behaves correctly* from a business point of view,
the code should still be as readable and maintainable as possible.
This is where experience makes a huge difference.
For someone who has already made all the mistakes countless times,
it is easier to notice poor naming of things like functions and namespaces,
and blurry responsibility separation, coupling code in inappropriate ways.

I always reflect on why I notice things and I try to be concrete in my feedback.
Like giving an alternative sentence for the documentation that captures the essence better,
or a use-case that would make the code behave in an undesirable way.
Sometimes I use the opportunity to write small code snippets with alternative ways of approaching a problem
along with the advantages AND disadvantages of the proposed alternative.

From reviewing code and the dialogs that follow, I have learned a lot.
Of course, there are several other ways of "reading code".

Occasionally, I find myself inside the source code of one of the libraries I use (hurray for Open Source).
Either during a bug hunt or because I am curious.
Reading someone else's code is a great way to find new inspiration.
I am not suggesting reading an entire repository of code, which would be overwhelming (at least to me).
By starting on known territory,
like the entry point where my application calls the library,
it doesn't feel so intimidating.

By seeing how other people solve their problems, I get to reflect on why they chose exactly that solution.
It helps me understand why a bug occurs, or where some limitations come from.
Sometimes I snoop in on reported issues, both open and closed, for an even deeper understanding.
There I find reasonings about why maintainers discard: *Why don't you just do X to fix it?*
Though reported issues aren't code per se, it is highly relevant,
because it shows how a specific solution has impacted the "real world".

Just like with "issues", there are lots of less code-heavy ways of learning more about writing code.

Both blog posts, StackOverflow questions, forum posts etc. contain lots of gems.
Also, understanding Cloud products and software architecture
will help you solve the problems in the simplest way possible.
The code with the fewest bugs is the code you don't need.
