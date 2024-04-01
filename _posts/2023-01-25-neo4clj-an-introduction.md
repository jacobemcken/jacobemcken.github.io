---
layout: post
title: Neo4clj - an introduction
categories:
- Programming
tags:
- Clojure
- Graph database
- Database
- Neo4J
- Unit test
- Test
comments: true
excerpt_separator: <!--more-->
---

It started with a business idea a few years ago... a business idea that would
require a graph database, specifically [Neo4j][].

We did explore the existing libraries at the time, but decided to "roll our own"
for reasons that now eludes me... and work began on [Neo4clj][].

<!--more-->

At first, we were trying to create a library that placed Clojure data structures
first and center. Not only for return values, but also for the input (querying).
Allowing not only nodes and relations but also their connections and ultimately
entire graphs to be represented by Clojure data structures.

It could probably have been done prettier and simpler, but we tried to implement
something that could be used in every thinkable case. Having to opt out of using
data structures and back to plain Cypher query string, as soon as the complexity
grew over a certain threshold, smelled of failure.

Though our intentions were good, using our own library felt clunky, and the
queries were hard to read (and thus reason about) - a DSL on top of a DSL proved
to be a beast difficult to tame. Kinda the same experienced I've had with ORM's
in other languages.

The thing about real applications is that you often need more than the simple
"Getting Started" example, like below. Working with graph databases is no
exception.

```clojure
(require '[neo4clj.client :as neo4j])

(def conn
  (neo4j/connect "bolt://localhost:7687" "neo4j" "password"))

(neo4j/create-node! conn
  {:ref-id "person" :labels [:person] :props {:first-name "Thomas"}})
```

> 📌 Notice: The keyword `:first-name` corresponds to a property with the name
> `firstName` in Neo4J due to [Neo4J naming recommendations][].

I the end, we circled back to Cypher. Embracing the many hours someone smarter
than us had put into creating the Cypher query language, which also has the
advantage of transparency.

```clojure
(neo4j/execute-read conn
  "MATCH (p:Person) WHERE p.firstName = $firstName RETURN p"
  {:firstName "Thomas"})
```

Errors referring to a specific line in some Cypher was suddenly referencing the
query I had written and not something a library had produced "under the hood".
It felt like I was talking the same language as the database... no unnecessary
indirection.

Another attempt to reduce complexity in our Cyphers was to split Cyphers up and
"glue" them together with code and transactions. But often times this didn't
have the desired effect (reducing complexity). Rather, we just ended up shoving
complexity around.

Using code as "glue" also went against a personal preference of mine: *Leave the
heavy lifting to the database, for which it is designed for*. To improve the
situation we needed to be comfortable with the necessary complexity, and for
that we required tests proving the correctness of the application (the Cypher in
the application).

A test suite was born. At first, it lived inside our application, but we quickly
decided to move it into Neo4clj as an optional dependency (`neo4clj-test`) that
could be excluded from the artifacts going into production.

Since the Neo4J in-memory DB leveraged by the library is a bit heavy, it might
be preferable to isolate tests specific to database integration. This way, it is
easy to opt in and out. Using `lein` (`profiles.clj`), it could look like the
following:

```
...
  :test-paths ["test/unit"]
  :profiles {:db-test {:test-paths ["test/db"]
                       :dependencies [[com.github.full-spectrum/neo4clj-test "1.1.0"]]}}
...
```


An actual test case would be structured like the following:

```clojure
(ns test-neo4clj.cypher-test
  (:require [clojure.test :refer [deftest is testing]]
            [neo4clj.client :as neo4j]
            [neo4clj.test-utils :as neo4j-test-utils]))

(def cypher
  "MATCH (p:Person) WHERE p.firstName = $firstName RETURN p")

(def initial-data-queries
  [
   "CREATE (:Person {firstName: 'Thomas', lastName: 'Anderson'})"
   ;; Alternatively seed the database with a CQL file
   #_(slurp "initial_data.cql")])
  ])

(deftest complext-cypher
  (neo4j-test-utils/with-db [conn {:initial-data initial-data-queries}]

    (testing "Super complex Cypher"
      (let [person-node (-> (neo4j/execute-read conn cypher {:firstName "Thomas"})
                            first
                            (get "p"))]
        (is (= [:person] (:labels person-node)))
        (is (= {:first-name "Thomas"
                :last-name "Anderson"} (:props person-node)))))))
```


Using [Mount][] for managing application state (lifecycle) aka. "Dependency
Injection", it would look something along the lines:

```clojure
(deftest complext-cypher
  (test-utils/with-db conn {:initial-data [(slurp "initial_data.cql")]}
    (mount/start-with {#'db/conn conn})

    ;; Test code with assertions

    (mount/stop)))
```


I hope you enjoy using the library as much as we do.


[Neo4J]: https://neo4j.com/
[Neo4clj]: https://github.com/full-spectrum/neo4clj
[Mount]: https://github.com/tolitius/mount/
[Neo4J naming recommendations]: https://neo4j.com/docs/cypher-manual/5/syntax/naming/
