---
layout: post
title: How to write concise code in Clojure
categories:
- Programming
tags:
- Clojure
comments: true
---

During code reviews I've seen the following repetition pattern a lot. I am going
to use Clojure to illustrate, but it also happens in other programming
languages:

```clojure
(ns myapp.butterfly)

(defn create-butterfly
  [butterfly-attributes]
  ..)

(defn calculate-butterfly-wing-size
  [butterfly-type]
  ..)
```

Notice how `butterfly` is being repeated. Imagine how using unnecessary long
symbols over and over again will lengthen the code, and slowly shroud the
purpose of the function in unnecessary noise.

`butterfly-attributes`are already in the context of a `create-butterfly`
function, which in turn already resides in a `.butterfly` namespace.

I will argue that the following is better. The code is more concise without
loosing its meaning because the namespace provides a meaningful context.

```clojure
(ns myapp.butterfly)

(defn create
  [attr]
  ..)

(defn calc-wing-size
  [type]
  ..)
```

I took the liberty to shorten `attributes` and `calculate` with common
abbreviations, just like Clojure does with `concat` over "concatenate".

Code from a different namespace, would have looked like the following:

```clojure
(ns myapp.other-ns
  (:require [myapp.butterfly :refer [create-butterfly]]))

(create-butterfly {:name "Brimstone" :color "green"})
```

and now it can look like this:

```clojure
(ns myapp.other-ns
  (:require [myapp.butterfly :as butterfly]))

(butterfly/create {:name "Brimstone" :color "green"})
```

It is not about making the code as short as possible... but it is. Just not at
the cost of context / readability. Clever use of namespaces can help with that.
