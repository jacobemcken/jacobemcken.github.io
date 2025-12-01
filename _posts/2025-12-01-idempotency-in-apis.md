---
layout: post
title: Expanding idempotency superpower to application interfaces (like REST APIs)
image: /assets/img/cloth_submerged_in_water_by_harbor_with_bus.webp
image_alt: "Midjourney prompt: A hand is submerging a small beige cloth into water. In the background drives a bus by the harbor promenade. Simple picture. Teal is the primary color. - Did a few edits to remove 'noise'."
description: "With idempotency, fault-tolerant REST APIs are possible, even though retry strategies needed to mitigate outages, expose the 'exactly once delivery' problem."
categories:
- Programming
tags:
- Clojure
- idempotency
- exactly-once
- API
comments: true
support: true
excerpt_separator: <!--more-->
---

Applying retry strategies on API calls to make them more robust
often brings its own set of problems.
Instead of occasionally losing a call creating an entity,
you occasionally double a call, creating a duplicate.

But idempotency can neutralise the inconveniences from retries,
making the pair a powerful combo. 💪

<!--more--> 

More specifically, retries expose you to **[the "exactly once delivery" problem][1]**,
which is a very common challenge in all kinds of decoupled systems.
If you are not already familiar with the problem,
I recommend learning about it.

But before diving into the technical details,
I want to explain how I understand *Idempotency*:

> Regardless of how many times an operation is applied (more than zero),
> it gives the same result.

I didn't truly understand idempotency the first many times I encountered it.
If you are like me, I hope a couple of analogies from the physical world
will clarify the meaning and help make it stick:
- Pull an open door to close it, and pull it again, and it stays shut.
- Submerge a cloth in water to make it wet, submerge it again, and it stays wet.
- Pushing "Stop" on a bus multiple times only stops the bus once.

I use Clojure for the majority of my programming needs,
and even though idempotency is central to Clojure,
it doesn't make APIs idempotent by default.
In that regard, it is like most other programming languages.
It is all about the implementation approach,
because the programming language is just what connects a REST API
exposing <abbr title="Create, Read, Update & Delete">CRUD</abbr> operations
with a storage, like a database.

The *Read* operation is the easiest to map to an idempotent operation, because it already is.
Requesting `GET /car/{id}` will always return the same entity identified by `id`.
It becomes slightly less obvious what to do with the *Delete* operation.
I've seen many examples of a `DELETE /car/{id}` request
that only returns `200 OK` on the first request and `404 Not Found` on all subsequent requests.
But it doesn't have to (and probably shouldn't) be like this.
It all comes down to how the operation is interpreted.
Does it mean *"delete entity with ID={id}"*,
or does it mean *"make sure entity with ID={id} is non-existent"*?
An entity can be deleted only once, but when the actual delete action becomes an implementation detail,
the API can return `200 OK` regardless of whether the entity was just deleted,
previously deleted, or maybe never existed.
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

Two `POST` requests will result in two entities being created.
The API server enables this
by automatically assigning each entity an ID before storing it (e.g., in a database).

There are a couple of ways to deal with this.
One way is to leverage the [`Idempotency-Key` header][2]
containing a unique ID for a single request across multiple retries.
But this is no magic trick that will fix everything
— it still requires the implementation to respect the header.
In other words: It is still the API provider's responsibility
to limit multiple identical requests to a single execution.

The other way is slightly less explicit about it,
but has served me well on many occasions.
Instead of having the server auto-generate IDs for entities,
I force the caller to provide an ID for the entity:

```json
{
    "id": "79b72ba6-939b-40f7-a8e9-44c3d52da926",
    "model": "Nimbus",
    "year": 2022
}
```

Again, just like with *Delete*, it is about the interpretation of the operation.
Does it mean *"create entity with the ID={id}"*,
or does it mean *"make sure entity with the ID={id} exists"*.
The latter can, in good conscience,
return `200 OK` even though it didn't create the entity by itself.

What I like about this approach is,
that it is impossible to opt out of using the API in a way that doesn't support retries.
The consumer doesn't need to understand or even see the word "idempotency".

Many storage systems make this behavior straightforward to implement.
The Postgres database for instance, has an [`ON CONFLICT` clause][3]
to specify an alternative action when the ID already exists.
Instead of raising an error or creating a duplicate, the database action can be `DO NOTHING`.

Lastly, the *Update* operation using a `PATCH /car/{id}` request
is similar to the *Create* operation in many ways.
But it takes slightly more effort to avoid introducing non-idempotent behavior.
I.e., auto-incrementing a counter would break idempotency.
Idempotency could still be achieved by using the `Idempotency-Key` header instead,
but the implementation would become more complex.

Remember to include only the properties that you want to update in a `PATCH` request.
If you want to overwrite the entire thing, you are probably looking for the `PUT` request.

For consumers, the above approach definitely makes fault-tolerant systems both
easier to reason about and simpler to implement.
For decoupled systems (like microservices),
fault tolerance is a must to mitigate outages like network issues and overloaded services,
and create a reliable system 🛟
The same strategies described above for APIs
can be used when consuming messages from a queue.

I hope this will help you build impeccable application interfaces. 🚀


[1]: https://exactly-once.github.io/posts/exactly-once-delivery/
[2]: https://developer.mozilla.org/en-US/docs/Web/HTTP/Reference/Headers/Idempotency-Key
[3]: https://www.postgresql.org/docs/current/sql-insert.html#SQL-ON-CONFLICT
