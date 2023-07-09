---
layout: post
title: A recurring core.async pattern
categories:
- Programming
tags:
- Clojure
- core.async
comments: true
---

I've found myself using Clojure `core.async` in a very specific way on several
occasions, and I thought it might be worth sharing.

```clojure
(require '[clojure.core.async :as async])

(def c
  (async/chan))

(async/go-loop []
  (when-let [msg (async/<! c)]
    (do-stuff msg)
    (recur)))
```

To test the code `do-stuff` could be replaced with `println`.

The cool thing about the above pattern is that the `go-loop` is automatically
terminated when the channel `c` is closed.

As the complexity of `do-stuff` grows, it is more likely that it will throw an
exception which will cause the `go-loop` to terminate. Extend the code with the
following to avoid premature (silent) terminations:


```clojure
(require '[clojure.core.async :as async])

(def c
  (async/chan))

(async/go-loop []
  (when-let [msg (async/<! c)]
    (try
      (do-stuff msg)
      (catch Exception e ; Consider if catching Throwable is needed
        ;; Replace println with faveorite logging library
        (println (str "ERROR: " (ex-message e))))
    (recur)))
```

I've used the above pattern in combination with a WebSocket ([Haslett][1]) and a
file system watcher ([Beholder][2]) among others.

[1]: https://github.com/weavejester/haslett
[2]: https://github.com/nextjournal/beholder


`core.async` is very versetile.