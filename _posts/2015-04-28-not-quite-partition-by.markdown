---
layout: post
title: Not quite like partition-by ... but still
categories:
- Programming
tags:
- Clojure
comments: true
---
I had a problem where I needed to partition a collection when ever a specific
element occurred.

The data I was working with wasn't numbers but for the sake of making an example
lets imagine something like the following where `1` indicates a new partition:

``` clojure
(def coll [1 2 3 4 5 1 2 3 1 2 3 4])
```

I started looking a partition-by which resulted in:

``` clojure
> (partition-by #(= 1 %) coll)
((1) (2 3 4 5) (1) (2 3) (1) (2 3 4))
```

After a short while I came to the conclusion that non of the existing core
functions `partition`, `partition-all`, `partition-by`, `split-at`, `split-with`
did what I needed. The closest function I could think of was `clojure.string/split`
but I wasn't working with a string and also the "splitter" needed to be kept.

I needed an end result like the following:

``` clojure
((1 2 3 4 5) (1 2 3) (1 2 3 4))
```

I went down several mental paths trying to find a solution that didn't feel clumpsy.
Suddenly realize that `partition-by` actually wasn't that far off.

I got the following working and named my function `partition-when`:

``` clojure
(defn partition-when
  [f coll]
  (map (fn [[a b]] (concat a b))
       (partition-all 2 (partition-by f coll))))
```


I didn't like the above anonymous function which led me to the followin end result:

``` clojure
(defn partition-when
  "Like partition-by except it splits every other time f returns a new value."
  [f coll]
  (map #(apply concat %) (partition-all 2 (partition-by f coll))))
```


I haven't tested the performance of it compared a loop-recur solution, but I'm
pretty happy about the readability of it. I find my self time and again solving
a problem in one way, just to realize that there is a better (often shorter aka.
more readable and resource effecient) way.

I don't think Clojure will ever stop amaze me with the endless compositions of
core functions.
