---
layout: post
title: Reflecting on static types
image: /assets/img/stone_meets_wind.png
image_alt: "Midjourney prompt: Stone and air faceoff in simple Chinese style. Midjourney edit prompt (upon small region): Flying bird in simple Chinese style."
description: 'As software grows and challenges our ability to reason about it, we often think static types are a solution... but is it?'
categories:
- Programming
tags:
- Clojure
- software-musings
comments: true
excerpt_separator: <!--more-->
updated_date: 2025-12-18 22:16:30 +0100
---

Every time I heard people praise static types,
I wondered why I didn’t share their enthusiasm.
After careful reflection,
I realized that static types don’t significantly influence how I approach or solve problems.
I also didn’t experience fewer bugs, easier debugging,
or greater team productivity when using them.

As software grows and challenges our ability to reason about it,
we seek ways to regain clarity and confidence when making changes.
I believe static types provide a sense of security
as they are intended to help us manage complexity.

However, I think this sense of security can lead to complacency, causing developers to overlook more effective solutions.<!--more-->

Instead of asking:
> ~~How can I manage complexity?~~

The question should be:
> How can I reduce complexity?

Reducing the size of application parts,
that can be reasoned about in isolation,
is a great way to begin.

These parts can be found by identifying natural boundaries within the application.
Reasoning about behavior inside any boundaries
requires asserting expectations about data entering and exiting.
I find this to be true at any level: From the entire application to smaller parts
down to method or function calls.

But before diving into the smaller internal boundaries,
let's first examine the more apparent outer boundaries, such as APIs and databases.


## APIs

Whether an API serves humans (via <abbr title="User Interface">UI</abbr>s)
or automated systems, introducing validation is standard practice.
It is obvious that we can't and shouldn't
trust data outside of our control.

To some degree validating data entering a system via an API
overlaps with the agenda of static types.
But it goes beyond types when business validation is required
such as avoiding duplicate categories or an age above 13 years.
Business validation might need to be applied across several statically typed objects
or to be altered runtime through configuration.
More than static types are needed.


## Databases

Databases offer numerous methods to enforce constraints on data within them,
designed to reduce the risk of storing invalid or incomplete data.

At first look,
it can seem like data in the database is internal to the application, but in many ways,
it is external just like when dealing with APIs.

There are several scenarios that make the data in the database untrustworthy:

- Writing database migrations that forget to account for some corner cases.
- Running batch jobs "fixing" data... almost.
- Write access by a user - even yourself 😅.
- Different application versions (with different assumptions) store data over time.

The database cannot make correctness guarantees about the data.
The application must take this responsibility
before passing on data to the business logic
and again static types cannot do it alone.


## Domains within the application

Both APIs and databases stand out as obvious boundaries because they are "outer" boundaries.

But as an application grows it often starts to span multiple domains and sub-domains,
and less obvious boundaries inside the application start to emerge.
Initially, reusing an object like `Customer` across internal domains seems practical.

As time passes, domains often require different things from "the same" object,
triggering different assumptions about the same object depending on the context.
Aligning an object across domains can get tricky
and eventually even impossible without bigger refactorings.

Code that doesn't change is usually a sign of stable and robust code,
so changing code outside boundaries,
just to appease the type checker is undesirable.

Imagine having a previous customer who wants to be "GDPR deleted".
At the same time, you are obligated by anti-fraud law
to store invoices related to this customer for several years,
to document that your company isn't doing money laundering.
When you delete the customer, do the finance parts of your system still work?...<br>
it should.

Having objects flow across multiple domains isn't an ideal situation,
even if the type checker is giving an A-OK 👌

I am not arguing that you should not use static types,
but I am arguing that it is probably helping you less than you think.


## What I found works for me

It requires less headspace when coupling is reduced
because the applications become easier to reason about.

Avoid implicit assumptions by stating them clearly (explicitly) in the code.
Then reaffirm assumptions when data crosses boundaries (a type isn't always enough),
which includes boundaries to internal domains and sub-domains.

I often perceive the application's internal domains and sub-domains,
much like I would external ones.
Regardless of whether I write my own implementation or use external services or libraries to handle a specific area of responsibility,
the interface (e.g. function signature) should be similar.
Your objects wouldn't travel from your code into external services or libraries either.

If the problem doesn't fit in your head,
it is probably time for refactoring.
I feel that smart IDEs and static typing postpone the pain
that usually leads us to that realization.

Personally, I had more benefit from a REPL, immutable data structures, and a functional concise language than static types in my quest for maintainable and robust software.

Luckily, simplifying and decoupling code works in any language<br>
— not just Clojure.



In the spirit of simple code and refactoring,
I feel it is worth sharing the following talk by Sandi Metz with a quote from it:

> ... code can know less, we can do smaller things.

RailsConf 2014 - ["All the Little Things"][1] 

Sandi Metz is a genius.


**Update 18. Dec 2025**

## Further reading

I suspect many Clojurists reflect on type systems from time to time.
Today, I found the blog post
[The Wrong Question About Type Systems][2] by *Furkan Bayraktar*,
where he does precisely that (really well written).


[1]: https://www.youtube.com/watch?v=8bZh5LMaSmE
[2]: https://furkan3ayraktar.github.io/blog/the-wrong-question-about-type-systems.html
