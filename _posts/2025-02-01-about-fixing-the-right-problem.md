---
layout: post
title: "About fixing the right problem"
description: "It still baffles me how we continue treating symptoms in our software instead of addressing the actual problem. Here’s a real-world example."
image: /assets/img/people_bringing_buckets_for_worn_roof.webp
image_alt: "Midjourney prompt: A long line of tired and sad people are carrying empty buckets into a house with a leaky roof. Another line of people are carrying filled buckets out of the house. A person is watching and pointing to the roof but is being dismissed. The roof is old and in poor condition. It is rainy. The image is colorful."
categories:
- Programming
tags:
- Clojure
- software-musings
comments: true
excerpt_separator: <!--more-->
---

It is fascinating how we humans often choose to treat the symptom
instead of addressing the actual problem
— like endlessly replacing water-filled buckets with empty ones
instead of fixing the leaking roof.

At least that is how it felt at work the other day... again.
This time though, the problem was easier to spot in the code than usual.
<!--more-->

Our code needs to sort a map numerically (`:X29`, `:X137`, `:X1108`)
rather than lexically (~~`:X1108`, `:X137`, `:X29`~~).

But the business hands down specifications to my team
where these keys sometimes include letters, like `:X137a`.

For reasons now shrouded in the mists of time,
we opted to replace letters with numbers in the code.
Usually `:X137a` is represented by `:X137.1`.

The gap between business specifications and the limitations of the technical solution isn't obvious.
New developers fall into this trap almost inevitably,
and even seasoned developers would occasionally forget that letters aren't allowed.

So today I found the team
~~fixing the sorting to account for letter postfixes 🚀~~
creating a test case that blocks "merge to main branch"
when letters are mistakenly introduced. 🥺 😭

I suspect that treating symptoms annoys me more than the average person for some reason.
This time, my annoyance got the better of me,
and I found the code responsible for sorting:

```clojure
(into (sorted-map) (reduce (fn [acc [k v]]
                             (conj acc [(Float/parseFloat (apply str (rest (name k)))) v]))
                           []
                           some-map))
```

It should probably have been written like the following for readability:

```clojure
(->> some-map
     (reduce (fn [acc [k v]]
               (conj acc [(Float/parseFloat (apply str (rest (name k)))) v]))
             [])
     (into (sorted-map))
```

The code `(apply str (rest (name k)))` is used to remove the `X` prefix,
and I found that the keys in the resulting map
weren't used for anything other than sorting.

My gut feeling told me that `sorted-map-by` was the way to go.
Even if the "converted to float" keys would be expected later in the call chain,
it seemed likely that a trivial code change would be enough
to support the original keyword keys.

Not having sorted things for a while, I needed to refresh my understanding.
A vector representation proved to get the job done:

```clojure
(sort [[137 "a"] [29 "b"] [1108 "x"]])
; ([29 "b"] [137 "a"] [1108 "x"])
```

All that was left was a function that could
convert the special keyword to this vector format:

```clojure
(defn make-sortable
  [n]
  (-> (name n)
      (subs 1) ; removes "X"
      (str/split #"(?=[^\d\.])" 2) ; splits number from non-number
      (update 0 #(Float/parseFloat %))))
```

While writing this blog post,
I suddenly noticed the following sorting behavior,
which would occur since `str/split` cannot make any guarantees
about length of the returned vector:

```clojure
(sort [[137 "a"] [137] [29 "b"] [1108 "x"]])
; ([137] [29 "b"] [137 "a"] [1108 "x"])
```

Having `137` sorted before `29b` was not going to cut it,
so I made the following amendments:

```clojure
(defn pad-vector
  [v len pad-val]
  (vec (take len (concat v (repeat pad-val)))))

(defn make-sortable
  [n]
  (-> (name n)
      (subs 1) ; removes "K"
      (str/split #"(?=[^\d\.])" 2) ; splits number from non-number
      (pad-vector 2 nil) ; enforce same length for consitent sort
      (update 0 #(Float/parseFloat %))))
```

Now, back from the detour,
the above would allow "sorting code" to look like the following:

```clojure
(->> some-map
     (into (sorted-map-by #(compare (make-sortable %1)
                                    (make-sortable %2)))))
```

Instead of adding 30 lines of test suite code to prevent using letters,
we wrote 12 lines that allow letters in keys while maintaining functionality — implemented slightly faster.
Most importantly, the software behavior is now more intuitive, leading to less confusion.

Way too often, we avoid exploring the unknown,
and instead reach for an empty bucket.
It's extra frustrating because my team is filled with both intelligent and competent people.
Though most are young and inexperienced,
I think the main problem is rooted elsewhere.

In the organization where we work,
there's a culture that often frowns upon suggesting fixing the root cause.
Management assumes it's generally faster to delay and work around problems,
and mentioning "technical debt" is a no-no.
Do we really want to teach the next generation of developers to "fix" problems like this?
How much of the code AI is learning from
is addressing symptoms rather than root causes?

Not knowing any better, you might think that at least this time, we got the roof fixed.
But the reason we need sorting in the first place
is that we never implemented a "dependency resolver".
Instead, we rely on sorting to guarantee dependency availability,
following the convention:
*Never depend on something with a higher number.*

How could this ever go wrong? 🤣

Can we please fix the flipping roof already?
