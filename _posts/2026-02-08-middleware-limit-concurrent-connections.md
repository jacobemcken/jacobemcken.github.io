---
layout: post
title: Limit concurrent HTTP connections to avoid crippeling overload
image: /assets/img/four_people_stuck_in_door_blocking_it.webp
image_alt: "Midjourney prompt: Three people too big to squeeze through a door simultaneously are blocking the entrance for a whole line of people behind them."
description: "Prevent 504 Gateway Timeout errors by limiting concurrent HTTP connections with middleware, reducing unnecessary load and cloud costs for your API services."
categories:
- Programming
tags:
- Clojure
- API
- middleware
- concurrency
- cloud
comments: true
support: true
excerpt_separator: <!--more-->
---

Blazing fast webservers make no difference
if the work exposed via their APIs is very CPU-intensive.
The slow response times will totally cripple the service
when coupled with an API gateway
that (for good reasons) refuses to wait for a response for more than 29 seconds.

A simple middleware helped me avoid `504 - Gateway Timeout` responses
and reduce most unnecessary load on a service API.

<!--more-->

If a single request on average takes 5 seconds to calculate a response,
I assumed that at least five responses would be able to make it through
before hitting a timeout of 29 seconds
(which is the maximum of Amazon's API gateway):

<img src="/public/media/assumed concurrent connections.svg"
     alt="Visualization of how I assumed concurrent HTTP connections would put load on the CPU."
     loading="lazy" />

But to my surprise, it behaved entirely differently in reality.
In retrospect, it makes sense, of course.

Resources are divided equally, causing all the responses to become equally slow:

<img src="/public/media/actual concurrent connections.svg"
     alt="Visualization of how the concurrent HTTP connections actually put load on the CPU."
     loading="lazy" />

I found myself in a situation where even a mediocre load
would make the system incapable of responding within the time limit.
The API gateway would return `504 - Gateway Timeout` on behalf of my service,
while my unaware service would occupy CPU resources for responses
that would never be used for anything,
slowing everything even further.

A sure way to contribute to climate change and get a high cloud bill,
while delivering zero value.

Oh wait...  
a caller is very likely to want to retry a request, indicating a temporary problem,
which the HTTP response code  `504 - Gateway Timeout` does.
Now multiply your already high cloud bill by the retry count.

In other words: A disaster. ☠️

An entirely different architecture,
maybe involving a queue or some async response mechanism,
would probably have been a better solution.
But sometimes, we need to work with what we've got.

Since my CPU load is fairly similar for all different requests,
it was easy to predict the number of concurrent connections
that could make it through within the timeout limit.

With the following middleware, I limit concurrent active connections
to ensure high CPU utilization while still responding within the timeout:

```clojure
(defn wrap-limit-concurrent-connections
  "Middleware that limits the number of concurrent connections to ´max-connections´,
   via the atom `current-connections-atom`.
   This means that the middleware can be applied in several different places
   while still sharing an atom if necessary."
  [handler current-connections-atom max-connections]
  (fn [request]
    (let [connection-no (swap! current-connections-atom inc)]
      (try
        (if (>= max-connections connection-no)
          (handler request)
          {:status 503 :body "Service Unavailable"})
        (finally
          (swap! current-connections-atom dec))))))
```

The middleware implementation is very naive and assumes that,
the service only exposes work with a similar load profile
so that the same middleware (and coordination atom)
can be reused across the service.

Though the middleware does make the  `504 - Gateway Timeout` responses go away,
they are replaced with slightly fewer `503 - Service Unavailable` errors.
The important part is that the maximum possible number of `200 - OK` are allowed to pass through,
making the system partially responsive while scaling up (deploying more instances).

<img src="/public/media/middleware concurrent connections.svg"
     alt="Visualization of how the concurrent HTTP connections actually put load on the CPU with the middleware applied."
     loading="lazy" />

I ran tests to find the right value for `max-connections`
that matches the given work and hardware the service was running on.

Endpoints with low CPU intensity, such as health checks,
should not be wrapped in the middleware.
You don't want a service instance terminated and restarted
just because the health check can't communicate:
*I'm still doing important stuff*.

A more sophisticated rate-limiting middleware is possible
using the same scaffolding as above.
Maybe something that times requests
and reduces concurrency as response time goes up,
or something with different weights
instead of just incrementing and decrementing by one.
But if this starts getting hairy,
you might be better off with an entirely different architecture.

Use with caution. 💚
