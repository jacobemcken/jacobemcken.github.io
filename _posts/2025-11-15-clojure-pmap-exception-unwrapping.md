---
layout: post
title: Exception handling differences between Clojure map & pmap
image: /assets/img/digital_concurrent_gates.webp
image_alt: "Midjourney prompt: Seven digital gates are placed next to each other on a long line with even spacing between them. Separated by closed gates, two open gates have digital threads flowing into the circuit board floor. Using image from core.async post as style reference."
description: "On the surface, Clojure's map and pmap functions appear interchangeable, but their behavior regarding exceptions differs."
categories:
- Programming
tags:
- Clojure
comments: true
support: true
excerpt_separator: <!--more-->
---

With this post, I am in deeper waters than usual.
What might sound like a recommendation in the following
could be a potential disaster in disguise. Be warned.

Personally, I prefer not to know about implementation details about the function I'm calling.
Although that was the situation I suddenly found myself in,
when a function I call replaced `map` with `pmap`.

Here is how I approached the weirdness with exceptions tangled with `pmap`.

<!--more-->

On the surface, `map` and `pmap` appear interchangeable,
since they both return a lazy sequence.
But the data contract breaks due to how exceptions are handled.

The following example showcases the behavior that caught me by surprise,
because I had expected it to return `{:error-code 42}`:

```clojure
(try
    (->> (range 1)
         (pmap (fn [_] (throw (ex-info "Oh noes" {:error-code 42}))))
         doall)
    (catch Exception e
      (ex-data e)))
; => nil
```

It did not. But using a normal `map` does:
```clojure
(try
    (->> (range 1)
         (map (fn [_] (throw (ex-info "Oh noes" {:error-code 42}))))
         doall)
    (catch Exception e
      (ex-data e)))
; => {:error-code 42}
```

`doall` is necessary to ensure the exception is triggered while inside the `try-catch` block,
instead of just returning an (unrealized) lazy sequence,
which will cause havoc later.

As far as I know, `pmap` uses futures somewhere behind the scenes,
which might be the reason why exceptions caused during mapping
are wrapped in a `java.util.concurrent.ExecutionException`.

Since I am in control of the function replacing `map` with `pmap`,
I decided to put the unwrapping where `pmap` is called,
to hide the implementation detail from the caller:

```clojure
(try
  (->> coll
       (pmap #(occasionally throw-exception %))
       doall)) ; realize lazy seq to trigger exceptions
  (catch Exception e
    ; Unwrap potentially wrapped exception by `pmap`
    (throw (if (instance? java.util.concurrent.ExecutionException e)
             (ex-cause e)
             e)))))
```

The conditional unwrapping allows for a slightly more complex implementation in the `try` block
that can throw exceptions outside `pmap` as well.

The above implementation assumes that an `ExecutionException` always has a cause,
which might not be the case - I don't know.

Use with caution.


[1]: https://clojuredocs.org/clojure.core/pmap
