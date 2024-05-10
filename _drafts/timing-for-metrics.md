---
layout: post
title: Refactoring timing metrics in Clojure
categories:
- Programming
tags:
- refactoring
- Cloud
- Clojure
comments: true
---

Lately, I've had a lot of code deployed in the Cloud
and I quickly got used to having access to a lot of cool services including gathering metrics
which this blog post is about.
The purpose of this blog post is not to give some perfect code ready for copy-pasting
but rather to explain my thought process and how I choose a direction.
Hopefully, this will give inspiration for other refactorings.

Throughout the code base I was working on
it was very likely to find something like the following:

```clojure
(let [start-a (System/nanoTime)
      result-a (some-heavy-calculation :option "some option value")
      latency-a (/ (- (System/nanoTime) start-a) 1000000)
      _ (save-metric {:name "A" :value latency-a :unit :ms})
      start-b (System/nanoTime)
      result-b (some-not-so-heavy-calculation :quick-calc true)
      latency-b (/ (- (System/nanoTime) start-b) 1000000)
      _ (save-metric {:name "B" :value latency-b :unit :ms})]
  ...
```

Pretty straightforward, but at the same time it felt unnecessary verbose.
I wanted to explore if I could find an approach that is both more readable and testable.

First, let's encapsulate the latency calculation in a function to make it reusable:

```clojure
(def ns-per-ms 1000000)

(defn ms-since
  "Takes a starting point in nano seconds and return mili seconds since that point in time.
   Also see java.lang.System.nanoTime()"
  [start-ns]
  (/ (- (System/nanoTime) start-ns) ns-per-ms))
```

The `ns-per-ms` is a "constant" to avoid [Magic numbers][1].

This would make the code slightly "lighter":

```clojure
(let [start-a (System/nanoTime)
      result-a (some-heavy-calculation :option "some option value")
      latency-a (ms-since start-a)
      _ (save-metric {:name "A" :value latency-a :unit :ms})
      start-b (System/nanoTime)
      result-b (some-not-so-heavy-calculation :quick-calc true)
      latency-b (ms-since start-b)
      _ (save-metric {:name "B" :value latency-b :unit :ms})]
  ...

```

Refactoring the timing itself is a bit more tricky,
but it could look something like the following,
if you - like me - want to avoid macros:

```clojure
(defn timed
  "Takes a function with zero args (wrap in an annonymous fn if needed)
   and times the function call.
   Returns a tuple with the function call result and latency
   (mili seconds it took for the function to return a result)."
  [f]
  (let [start (System/nanoTime)
        rs (f)]
    (vector rs (ms-since start))))
```

The above function has the limitation,
that it needs logic to be wrapped in an actual function.
But that fits my needs perfectly
because I try to avoid having too much inline code.
Usually, I want to encapsulate functionality in a function for two reasons.
1. To force myself to describe "what is actually happening"
   through its name and sometimes a doc-string.
2. It makes it easier to test.
   I want to be able to test the function without throwing other things
   (like metric calculations) into the mix.

```clojure
(let [[result-a latency-a] (timed #(some-heavy-calculation :option "some option value"))
      _ (save-metric {:name "A" :value latency-a :unit :ms})
      [result-b latency-b] (timed #(some-not-so-heavy-calculation :quick-calc true))
      _ (save-metric {:name "B" :value latency-b :unit :ms})]
  ...

```

Instead of having this code have two individual side effects for saving,
metrics can be accumulated and returned instead. Easier to test (having to mock less).
Also, side-effects are concentrated like in this talk.

```clojure
(let [[result-a latency-a] (timed #(some-heavy-calculation :option "some option value"))
      [result-b latency-b] (timed #(some-not-so-heavy-calculation :quick-calc true))]
      [{:name "A" :value latency-a :unit :ms}
       {:name "B" :value latency-b :unit :ms}]
  ...

```

Beware that timing functions that return a lazy sequence will give incorrect timings.

A way to work around lazy sequences is to force evaluating them (which can be done with `(clojure.walk/postwalk identity ...`:

```clojure
(defn timed
  "Takes a function and all its args and times the function call. Returns a vector of
   both the function call and latency (mili seconds it took for the function to return)."
  [f & args]
  (let [start (System/nanoTime)
        rs (->>
             (apply f args) 
             (clojure.walk/postwalk identity))]
    (vector rs (ms-since start))))
```

Pushing side effects to "the edges".

[1]: https://en.wikipedia.org/wiki/Magic_number_(programming)