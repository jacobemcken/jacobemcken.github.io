---
layout: post
title: Becoming friends with Clojure protocols
categories:
- Programming
tags:
- Clojure
- Test
- Mongo
comments: true
support: true
---
I've been programming Clojure for several years, and yet I've managed to avoid
protocols during all that time (I've also avoided macros, but that is another
story). I found myself always having a colleague do the "dirty work" or some sad
excuse as of why it wasn't necessary right now. No more... this week I got my
hands dirty.

For me, Clojure protocols solves the same problem, that I previously used
interfaces in Java and PHP for: Dependency Injection (DI) and Iversion of
Control (IoC). This kind of abstraction probably have several purposes, but I
use it for being able to reason about a "service" without the knowledge of its
implementation.

Having your services "hidden" behind a protocol will make it very pleasant to
test functions that would normally require external access causing side effects
(like API endpoints, database and queues). But it also ties well in with
applications state management libraries like [Mount][] and [Component][], when
needing a "standin" for one of these external resources, e.g. for some manual
testing in the REPL.

As soon as I dived into [the example about protocols found on the Clojure
website][1], I found that it was too superficial for someone like me. I've never
approached programming very academically. For some unknown reason, most things
with fancy words (polymorphism included) just refuse to stick to the inside of
my skull until I see and feel it in action. My pleading for help was heard by
[Clojurian Slack][Slack], and after I understood (a bit more), I decided to
create a more elaborate example, that maybe others would find useful.


## The protocol (interface)

For a more realistic example than the one on the Clojure website, imagine some
entity in a database with CRUD operations (Create, Read, Update & Delete):

```clojure
(defprotocol EntityStore
  (create [this id] [this id initial-data])
  (fetch [this id])
  (save [this id data])
  (delete [this id]))
```

For the Read operation I choose to use a function named `fetch` (over `get` and
`read`) and for the Update operation I use `save` (over `update` and `replace`).
I think both `fetch` and `save` clearly describes the intention of the operation
without conflicting with existing function names in `clojure.core`. The
otherwise overlap of naming could confuse for developers, and at the same time
the choice avoids linting warnings like `… already refers to …`.

Adding doc-strings prior implementation, will force you to evaluate the exact
needs of your protocol in order to articulate them. I found myself finding
errors in my design on several occasions during this:

```clojure
(defprotocol EntityStore
  "All operations to the store are atomic (e.g. a DB implementation
   would use transactions or something similar)."
  (create [this id] [this id initial-data]
    "Creates a new entity in the store, and returns a map representing
     the new entity.")
  (fetch [this id]
    "Fetches (reads) an entity from the store or returns nil if it
     doesn't exist.")
  (save [this id data]
    "Saves (updates) an entity with the id `id` overwriting its data,
     returns a map representing the updated entity.")
  (delete [this id]
    "Deletes an entity with the id `id` from the store and returns
     nil."))
```

I decided to put the protocol definition in the namespace
`my-app.service.entity-store`, because it would allow me to use it in the code
like so:

```clojure
(ns my-app.core
  (:require [my-app.service.entity-store :as entity-store-service]
            ...))

...
(let [entity-a (entity-store-service/fetch entity-store "id-for-A")
  ...
```

The `service` part of the NS, emphasizes that implementation details are "hidden
away" on purpose, and I think `entity-store-service/fetch` read very well in the
code.

Not having the protocol definition in the same namespace as where it is used,
tricked me at first and caused the error: `Unable to resolve symbol: <symbol name> in this context`.
It took me a while to figure out that methods defined using `defprotocol` "live"
in the same namespace as the namespace where they are defined.


## The (mock) implementation

I'm going to start a bit backwards with a mock of the entity store, because it
will be simpler in the sense that it does not require any third party libraries
and such to implement.

```clojure
(ns my-app.service.impl.in-memory-entity-store
  (:require [my-app.service.entity-store :as entity-store-service]))

(defn create
  ([store-atom id]
   (create store-atom id {}))
  ([store-atom id data]
   (swap! store-atom assoc id data)))

(defn fetch
  [store-atom id]
  (get @store-atom id))

(defn save
  [store-atom id data]
  (swap! store-atom assoc id data))

(defn delete
  [store-atom id]
  (swap! store-atom dissoc id))

(deftype InMemoryEntityStore [store-atom]
  entity-store-service/EntityStore
  (create [_this id] (create store-atom id))
  (create [_this id data] (create store-atom id data))
  (fetch [_this id] (fetch store-atom id))
  (save [_this id data] (save store-atom id data))
  (delete [_this id] (delete store-atom id)))
```

A classic mistake to make at this point is to remove either `create` or `save`
on the protocol, since the implementation is identical. But they are only
identical (for now), because this mock is a very naive implementation. Also
remember, the protocol should never know about the implementation details of the
exposed functionality.


## For convenience

Consider adding an extra convenience function in the "implementation" namespace
(in above example: `my-app.service.impl.in-memory-entity-store`). Such a
function allows you to avoid importing the class that `deftype` creates, which
would otherwise require your code to look something like:

```clojure
(ns my-app.core
  (:require [my-app.service.impl.in-memory-entity-store])
  (:import [my-app.service.impl.in-memory-entity-store InMemoryEntityStore]))

...

(InMemoryEntityStore. (atom {}))
```

Instead, add a function like `new-store`:
```clojure
(ns my-app.service.impl.in-memory-entity-store
  ...

(defn new-store
  "Convenience function for creating an in memory entity store."
  [store-atom]
  (InMemoryEntityStore. store-atom))
```

Which would allow something like:
```clojure
(ns my-app.core
  (:require [my-app.service.impl.in-memory-entity-store :as in-memory-entity-store]))

...

(in-memory-entity-store/new-store (atom {}))
```


## Real implementation

The following NoSQL implementation using [Monger, a Clojure client for
MongoDB][3] is also very naive: 😅

```clojure
(ns my-app.service.impl.mongo-entity-store
  (:require [monger.collection :as mongo-document]
            [monger.core :as mongo]
            [my-app.service.entity-store :as entity-store-service]))

(def coll
  "Collection in which entities are stored in MongoDB."
  "entities")

(defn create
  ([db oid]
   (create db oid {}))
  ([db oid data]
   (mongo-document/insert-and-return db coll (assoc data :_id oid))))

(defn fetch
  [db oid]
  (mongo-document/find-map-by-id db coll oid))

(defn save
  [db oid data]
  (mongo-document/update-by-id db coll oid data))

(defn delete
  [db oid]
  (mongo-document/remove-by-id db coll oid))

(deftype MongoEntityStore [db]
  entity-store-service/EntityStore
  (create [_this id] (create db id))
  (create [_this id data] (create db id data))
  (fetch [_this id] (fetch db id))
  (save [_this id data] (save db id data))
  (delete [_this id] (delete db id)))

(defn new-store
  "Convenience function for creating a NoSQL entity store."
  [uri]
  (let [{:keys [db]} (mongo/connect-via-uri uri)]
    (MongoEntityStore. db)))
```

On the surface, the above solution looks fine and dandy, but it has (at least)
one flaw. It requires that the `id` given through the protocol is a BSON
`ObjectId` (MongoDB specific Java object). Though in-memory implementation using
an atom would not complain about using `ObjectId` as lookup keys, it is often
preferable to avoid bleeding DB specifics outside the protocol. The following
three functions (`hexify`, `pad` & `s->oid`) is a somewhat hacky attempt to work
around it and use strings instead (here be dragons 🔥🐉):

```clojure
(ns my-app.service.impl.mongo-entity-store
  (:require [monger.collection :as mongo-document]
            [monger.core :as mongo]
            [my-app.service.entity-store :as entity-store-service])
  (:import [org.bson.types ObjectId]))

; Shamelessly copied from https://stackoverflow.com/questions/10062967/clojures-equivalent-to-pythons-encodehex-and-decodehex
(defn hexify
  "Convert byte sequence to hex string"
  [coll]
  (let [hex [\0 \1 \2 \3 \4 \5 \6 \7 \8 \9 \a \b \c \d \e \f]]
    (letfn [(hexify-byte [b]
              (let [v (bit-and b 0xFF)]
                [(hex (bit-shift-right v 4)) (hex (bit-and v 0x0F))]))]
      (apply str (mapcat hexify-byte coll)))))

;; Strongly inspired by https://stackoverflow.com/questions/27262268/idiom-for-padding-sequences
(defn pad
  [n val coll]
  (take n (concat coll (repeat val))))

(defn s->oid
  [^String s]
  (->> (.getBytes s)
       (pad 12 0xFF)
       (hexify)
       (ObjectId.)))

(def coll
  "Collection in MongoDB in which entities are stored."
  "entities")

(defn create
  ([db id]
   (create db id {}))
  ([db id data]
   (mongo-document/insert-and-return db coll (assoc data :_id (s->oid id)))))

(defn fetch
  [db id]
  (mongo-document/find-map-by-id db coll (s->oid id)))

(defn save
  [db id data]
  (mongo-document/update-by-id db coll (s->oid id) data))

(defn delete
  [db id]
  (mongo-document/remove-by-id db coll (s->oid id)))

(deftype MongoEntityStore [db]
  entity-store-service/EntityStore
  (create [_this id] (create db id))
  (create [_this id data] (create db id data))
  (fetch [_this id] (fetch db id))
  (save [_this id data] (save db id data))
  (delete [_this id] (delete db id)))

(defn new-store
  "Convenience function for creating a NoSQL entity store."
  [uri]
  (let [{:keys [db]} (mongo/connect-via-uri uri)]
    (MongoEntityStore. db)))
```

The above solution have the following advantages:
- Mongo specific implementation (the `ObjectId` class) is entirely hidden behind
  the protocol (almost - I'll get back to this).
- There is no need to add extra indexes on the collection in the Mongo database,
  which using an alternative field would have strongly encouraged.
- The CRUD functions are all simple because they can leverage
  `...-by-id`-functions in the Clojure Mongo driver (Monger).

There are still a bit of Mongo hiding in the shadows because the `id` must be a
string and only the first 12 bytes are considered for magically generating the
`ObjectId` behind the scenes. Also, not being able to easily correlate the id
`"my-juicy-idA"` with `ObjectId("6d792d6a756963792d696441")` is a bit of a
bummer.

It might be possible to [use UUID's encapsulated in Mongo BSON `Binary`][2]
though, but that is outside the scope of this post.


## The business logic

Leaving all the exiting challenges with Mongo behind and moving on...

An "Entity store service" is now available, which business logic can leverage
oblivious to its implementation.

Consider the following code describing some super important business logic:

```clojure
(ns my-app.core
  (:require [my-app.service.entity-store :as entity-store-service]
            [my-app.service.impl.mongo-entity-store :as mongo-entity-store]))

(def entity-store
  (mongo-entity-store/new-store "mongodb://admin:secret@172.21.0.2/customer1"))

(defn apply-business-logic
  [{:keys [entity-id id] :as _event}]
  (when-let [entity (entity-store-service/fetch entity-store entity-id)]
    (if-not (= (:name entity) "Donald Duck")
      entity
      (do ; Someone have been testing (again) - cleanup
        (entity-store-service/delete entity-store entity-id)
        nil))))
```

The code in `apply-business-logic`, doesn't care if `entity-store` is of the
type `MongoEntityStore` or `InMemoryEntityStore`. This is very useful for
testing, among other things.


## Tests (using the mock)

Notice how the following test allows testing of `apply-business-logic` without
having a database available during testing, or preparing test data in the
database (and cleaning data in the database afterwards).

```clojure
(ns my-app.core-test
  (:require [clojure.test :refer [deftest is testing]]
            [my-app.core :as sut] ; System Under Testing
            [my-app.service.impl.in-memory-entity-store :as in-memory-entity-store]))

(deftest apply-business-logic
  (testing "Normal entity"
    (with-redefs [my-app.core/entity-store
                  (in-memory-entity-store/new-store
                    (atom {"123" {:name "John Doe"}}))]
      (is (= {:name "John Doe"} (sut/apply-business-logic {:entity-id "123"})))))
  (testing "Bad entity"
    (let [store-atom (atom {"123" {:name "Donald Duck"}})]
      (with-redefs [my-app.core/entity-store
                    (in-memory-entity-store/new-store store-atom)]
        (is (contains? @store-atom "123"))
        (is (nil? (sut/apply-business-logic {:entity-id "123"})))
        (is (not (contains? @store-atom "123"))))))
  (testing "Unknown entity"
    (with-redefs [my-app.core/entity-store
                  (in-memory-entity-store/new-store (atom {}))]
      (is (nil? (sut/apply-business-logic {:entity-id "non-existing"}))))))
```

The above code can be found on [GitHub][].

This post is getting long... so before I even make the stubborn and enduring
people tired, I will stop with:

> Protocols are your friend (that maybe you just need to get to know). 💜


[Mount]: https://github.com/tolitius/mount
[Component]: https://github.com/stuartsierra/component
[1]: https://clojure.org/reference/protocols
[Slack]: https://clojurians.slack.com
[2]: https://www.mongodb.com/docs/manual/core/document/#the-_id-field
[3]: http://clojuremongodb.info/
[GitHub]: https://github.com/jacobemcken/clojure-protocol-example