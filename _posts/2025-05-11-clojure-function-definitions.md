---
layout: post
title: Consistent code style for Clojure function definitions
image: /assets/img/virtual_abstract_code_blocks.webp
image_alt: "Midjourney prompt: A surreal landscape shaped like layered code blocks, gradients of thought and logic, metaphor for clarity in programming. The illustration is a rough sketch style and in vibrant colors. Background is brimming with tech vibes, circuits and pulses. Using image from core.async post as style reference."
description: "Explore a consistent code style for defining Clojure functions that improves readability, diffs, and structure."
categories:
- Programming
tags:
- Clojure
comments: true
support: true
excerpt_separator: <!--more-->
---
A Clojure function is often defined with name and arguments on a single line:

```clojure
(defn some-function [with-several arguments]
  ...
```

and the following post is about why I will advocate for a multi-line alternative:

```clojure
(defn some-function
  [with several arguments]
  ...
```

<!--more-->
At first, everything on a single line looks like the only "right way" to do it.
After all, if you squint, this is how it looks in other programming languages:

```php
// JavaScript
function some_function(with_several, arguments) {
    ...

// PHP
function someFunction($withSeveral, $arguments) {
    ...

// C#
private static string SomeFunction(string withSeveral, string arguments)
{
    ...
```

And to be fair, it probrably feels more familiar for someone who never read Clojure before,
to have the function name and its arguments on the same line.

Regardless, here is why I think a multi-line solution is better.

When dealing with lengthy function names and/or argument lists,
the horizontal scrolling threshold is suddenly significantly further away,
with the multi-line solution.
Throwing "type hints" into the mix just makes the point more salient.

```clojure
(defn some-necessary-lengthy-function-name ^String [including an-abnormal ^Integer enormous list-of-arguments]
  ...

; vs.

(defn some-necessary-lengthy-function-name
  ^String [including an-abnormal ^Integer enormous list-of-arguments]
  ...
```

Diffs, when having to add a doc-string (or metadata, for that matter),
also becomes cleaner.
Notice how existing lines are unmodified and only new lines are added:

```diff
-(defn some-function [with several arguments]
+(defn some-function
+  "A detailed doc-string describing important information about the function
+   on multiple lines."
+  [with several arguments]
   ...

 ; vs.

 (defn some-function
+  "A detailed doc-string describing important information about the function
+   on multiple lines."
   [with several arguments]
   ...
```

Hopefully, the multi-line approach can also reduce
how often the doc-string is wrongly placed after the arguments.
As far as I can tell, this error is quite common:

```clojure
 (defn some-function [with several arguments]
+  "WRONGLY PLACED doc-string describing important information about the function
+   on multiple lines."
   ...
```

Doc-string for `defn`:
```clojure
(defn name doc-string? attr-map? [params*] prepost-map? body)
```

A single line for both function name and the argument list
also makes it slightly harder to distinguish whether a change
affected the function name, the argument list or both in a diff.

The same multiline pattern emerges when working with multi-arity functions:

```clojure
(defn some-function
  [with several arguments]
  ...

(defn a-different-function
  ([also with arguments]
   (a-different-function also with arguments {}))
  ([also with arguments opts]
   ...
```

The code for single and multi arity functions
will have the same "shape".

Personally, I find the code easier on the eyes
(easier to read) when the code follows a similar pattern.
I have a better reading flow when the content is predictable.


Though I may be more sensitive to messiness than the average person
(it feels like my brain gets interrupted),
I am convinced that it helps most people read the code to some degree.

Some might argue it is unnecessary work to ensure consistency in structuring.
But the feeling of "putting in effort" disappears, as it becomes a habit
— like with most difficult things.
Also, tooling can most likely do it for you.

In my opinion, we should optimize for reading code.
Even with AI, humans will need to vet the code and help debug it in the foreseeable future.
We need someone to blame when it doesn't work 😅

If you like to read more about Clojure code style,
check out my old post, ["Concise code"][1].

[1]: 2019-05-31-concise-code.md
