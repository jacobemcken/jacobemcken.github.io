---
layout: post
title: Using idempotency for application interfaces like REST APIs
image: /assets/img/cloth_submerged_in_water_by_harbor_with_bus.webp
image_alt: "Midjourney prompt: A hand is submerging a small beige cloth into water. In the background drives a bus by the harbor promenade. Simple picture. Teal is the primary color. - Did a few edits to remove 'noise'."
description: "Fault-tolerant REST APIs are possible with idempotency, even though retry strategies needed to mitigate outages, expose the 'exactly once delivery' problem."
categories:
- Programming
tags:
- Clojure
- idempotency
- exactly-once
- API
- CRUD
comments: true
support: true
excerpt_separator: <!--more-->
---

Applying retry strategies on API calls to make them more robust
often brings its own set of problems.
Occasionally, rather than losing an entity-creating call,
you end up with multiple,
and trust me — duplicates are a pain to clean up.

However, idempotency can neutralise the inconveniences from retries,
making the pair a powerful combo. 💪

<!--more--> 

More specifically, retries expose you to **[the "exactly once delivery" problem][1]**.
This is a very common challenge in all kinds of decoupled systems.
If you are not already familiar with the problem,
I recommend learning about it.

But before diving into the technical details,
I want to explain how I understand *Idempotency*:

> Regardless of how many times an operation is applied (more than zero),
> it gives the same result.

It took me a few attempts to truly understand idempotency.
If you are like me, a couple of analogies from the physical world
might help clarify the meaning and make it stick:
- Pull an open door to close it, and pull it again, and it stays shut.
- Submerge a cloth in water to make it wet, submerge it again, and it stays wet.
- Pushing "Stop" on a bus multiple times only stops the bus once.

I use Clojure for most of my programming needs,
and even though idempotency is central to Clojure,
it doesn't automatically make APIs idempotent.
In that regard, Clojure is like most other programming languages.
It is all about the implementation approach,
because the programming language is just what connects a storage, like a database,
with a REST API exposing <abbr title="Create, Read, Update & Delete">CRUD</abbr> operations.


## CRUD as idempotent operations

The *Read* operation is the easiest to map to an idempotent operation,
because it is already idempotent.
A request for `GET /car/{id}` will always return the entity identified by the value of `id`.


It becomes slightly less obvious what to do with the *Delete* operation.
I've seen many APIs where a `DELETE /car/{id}` request
has returned a `200 OK` response to the initial request,
followed by a `404 Not Found` response on all subsequent requests.
For an idempotent API, it shouldn't be like this.

It all depends on how the system **interprets** the operation.
Does it mean *"delete entity with ID={id}"*,
or does it mean *"make sure entity with ID={id} is non-existent"*?
An entity can be deleted only once, but when the actual delete action becomes an **implementation detail**,
the API can return `200 OK` regardless of whether the entity was just deleted,
had been deleted previously, or never existed.
This behavior is often precisely what you need and leads to a more forgiving API,
allowing simpler, more robust code for the API consumer.

As we move to the *Create* operation with `POST` requests,
it gets even murkier.
Consider the following `POST /car` request body:

```json
{
    "model": "Nimbus",
    "year": 2022
}
```

Two `POST` requests will result in the creation of two entities.
The API server enables this
by automatically assigning each entity an ID before storing it (e.g., in a database).

There are at least two ways to deal with this.
One way is to leverage the [`Idempotency-Key` header][2]
containing a unique ID for a single request across multiple retries.
However, this is not a magic trick that automatically fixes everything.
The implementation must respect the header.
In other words: It is still the API provider's responsibility
to limit multiple identical requests to a single execution.

The other method is slightly less explicit,
but it has served me well many times.
Instead of having the server auto-generate IDs for entities,
I force the caller to provide an ID for the entity:

```json
{
    "id": "79b72ba6-939b-40f7-a8e9-44c3d52da926",
    "model": "Nimbus",
    "year": 2022
}
```

As with *Delete*, it's down to the **interpretation** of the operation.
Does it mean *"create entity with the ID={id}"*,
or does it mean *"make sure entity with the ID={id} exists"*.
The latter can, in good conscience,
return `200 OK` even though it didn't create the entity by itself.

The great thing about this approach is
that it's impossible to use the API without supporting retries.
The consumer doesn't need to understand or even see the word "idempotency".

Many storage systems make this behavior straightforward to implement.
The Postgres database, for instance, has an [`ON CONFLICT` clause][3]
to specify an alternative action when the ID already exists.
Instead of raising an error or creating a duplicate, the database can `DO NOTHING`.

Lastly, the *Update* operation using a `PATCH /car/{id}` request
is similar to the *Create* operation in many ways.
But it takes slightly more effort to avoid introducing non-idempotent behavior.
I.e., auto-incrementing a counter would break idempotency.
Although you can still achieve idempotency by using the `Idempotency-Key` header,
this would likely make the implementation more complex.

💡**Note**<br>
Remember to include only the properties that you want to update in a `PATCH` request.
If you want to overwrite the entire thing, you are probably looking for the `PUT` request.


## Wrapping up

Consuming an idempotent API definitely makes fault-tolerant systems both
simpler to implement and easier to reason about.
Fault tolerance is essential for decoupled systems (such as microservices)
to mitigate issues like network failures and overloaded services
and to build a reliable system. 🛟
The same technique for leveraging idempotency in APIs
also applies when consuming messages from a queue.

You will probably start seeing the benefits of idempotency in even more places.
It is not for nothing, that idempotent data structures are a cornerstone in Clojure.

I hope this will help you build impeccable application interfaces.
Idempotency is a superpower. 🚀


[1]: https://exactly-once.github.io/posts/exactly-once-delivery/
[2]: https://developer.mozilla.org/en-US/docs/Web/HTTP/Reference/Headers/Idempotency-Key
[3]: https://www.postgresql.org/docs/current/sql-insert.html#SQL-ON-CONFLICT
