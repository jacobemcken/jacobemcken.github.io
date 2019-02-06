---
layout: post
title: Recent findings on Clojure testing
categories:
- Programming
tags:
- Clojure
- Unit tests
comments: true
---

These days I'm spending time looking into testing in Clojure. I've been writing
my share of test cases in PHP at work. There we have several tests "freezing"
time or mocking services. Both practices are common and something worth
learning for Clojure.


## Freeze time

Personally I often find it easier to reason about/test the code when providing a
reference date to the function. The "new" Date/Time introduced in Java 8, as
well as the [Clojure wrappings][1], are both very well crafted. Even though I'm
not a big fan of "freezing" time, it has its uses, and I was very delighted to
find how easy it is to achieve in Clojure:

```clojure
(ns my-ns.core
  (:require [java-time :as time]))

(defn >10-secs-ago
  [my-instant]
  (time/after? my-instant (time/plus (time/instant) (time/seconds 10))))


(ns my-ns.core-test
  (:require [clojure.test :as t]
            [java-time :as time]
            [my-ns.core :as sut]))

(t/deftest >10-secs-ago
  (t/testing "More than 10 seconds ago check on instant that is"
    ;; The clock is mocked using milli seconds
    ;; 1549411200000 = 2019-02-06T00:00:00.000Z
    (time/with-clock (time/mock-clock 1549411200000)
      (t/testing "less than 10 seconds ago"
        (t/is (false? (sut/>10-secs-ago (time/instant 1549411200999)))))
      (t/testing "exactly 10 seconds ago"
        (t/is (false? (sut/>10-secs-ago (time/instant 1549411210000)))))
      (t/testing "more than 10 seconds ago"
        (t/is (true? (sut/>10-secs-ago (time/instant 1549411210001))))))))
```


## Mount mock

[Mount][2] is framework for managing life-cycle and dependencies for an
application with runtime state. So far, I'm really impressed with it and I enjoy
it over other frameworks trying to solve the same thing.

Testing code that relies on application state had me puzzled for a little while.
Some examples I found suggested using `start-with`, but even though I was able
to swap part of the application state, the entire application was mounted. Since
I'm only interested in testing parts of the application I wanted to avoid
starting or mocking things untouched by the test.

This is how I did it:

```clojure
(ns my-ns.core
  (:require [mount.core :as mount]))

(mount/defstate sessions
  :start (atom {}))

(defn open-session []
  (let [id (gensym)]
    (swap! sessions assoc id ::open)
    id))

(defn close-session
  [id]
  (swap! sessions dissoc id))


(ns my-ns.core-test
  (:require [clojure.test :as t]
            [mount.core :as mount]
            [my-ns.core :as sut]))

(t/deftest open-session
  (t/testing "that session is being registered upon open"
    (let [sessions (atom {})]
      (->
        (mount/only #{#'my-ns.core/sessions})
        (mount/swap {#'my-ns.core/sessions sessions})
        (mount/start))
      (let [session-id (sut/open-session)]
        (t/is (contains? @sessions session-id)))
      (mount/stop))))

(t/deftest close-session
  (t/testing "that session is being deregistered upon close"
    (let [session-id (gensym)
          sessions (atom {session-id :sut/open})]
      (->
        (mount/only #{#'my-ns.core/sessions})
        (mount/swap {#'my-ns.core/sessions sessions})
        (mount/start))
      (sut/close-session session-id)
      (t/is (not (contains? @sessions session-id)))
      (mount/stop))))
```

I'm sure the `only`, `swap`, `start` and `stop` can be shortened with a helper
function or macro but that will be for another time. I'm wondering if Mount has
a smarter way of doing the above, that I just didn't find yet?

And if you (like a recent version of me) don't know [what SUT means][3],
Wikipedia is our friend. Happy testing.


[1]: https://github.com/dm3/clojure.java-time
[2]: https://github.com/tolitius/mount
[3]: https://en.wikipedia.org/wiki/System_under_test
