---
layout: post
title: Creating a Clojure client library for InfluxDB
categories:
- Programming
tags:
- Clojure
- Database
- Time series
- InfluxDB
comments: true
---

I'm working on a project where a time series database makes sense and the choice
fell on InfluxDB. I found [mnuessler/influxdb-clojure][1] an aged Clojure
wrapping an older version of the [Java InfluxDB client][2]. Being all excited
about diving into this new area I thought it would be best to leverage the
existing efforts put into making InfluxDB accessible in Clojure. It took me a
while to realize that I wasn't comfortable with all the layers put between me
and InfluxDB server.

[1]: https://github.com/mnuessler/influxdb-clojure
[2]: https://github.com/influxdata/influxdb-java


First I dropped the existing Clojure library which hasn't been updated for
almost 3 years. It seemed unlikely that the project would miraculously be
revived. Therefore I opted for just depending on the newest Java client (2.14)
directly which included the following dependencies:

```
Retrieving org/influxdb/influxdb-java/2.14/influxdb-java-2.14.jar from central
Retrieving com/squareup/retrofit2/retrofit/2.4.0/retrofit-2.4.0.jar from central
Retrieving com/squareup/moshi/moshi/1.5.0/moshi-1.5.0.jar from central
Retrieving org/msgpack/msgpack-core/0.8.16/msgpack-core-0.8.16.jar from central
Retrieving com/squareup/retrofit2/converter-moshi/2.4.0/converter-moshi-2.4.0.jar from central
Retrieving com/squareup/okhttp3/okhttp/3.11.0/okhttp-3.11.0.jar from central
Retrieving com/squareup/okio/okio/1.14.0/okio-1.14.0.jar from central
Retrieving com/squareup/okhttp3/logging-interceptor/3.11.0/logging-interceptor-3.11.0.jar from central
```

But digging deeper I realized that the only thing the Java client was doing was
sending request directly to the InfluxDB HTTP API. It just rubs me the wrong way
having 8 extra dependencies (around 1 Mb worth of jar files), dealing with
transforming back and forth between Java objects and Clojure data structures and
having to browse 8k lines of Java code across 85 files, when I wanted know a bit
more of what was under the hood. After all, since we are talking about doing 2-3
different HTTP requests to InfluxDB, it seemed overly complicated.

I got this nagging feeling that, for my use case, I would be better of just doing
it myself. How hard can it be?...

Well to be honest it was a bit harder than I had expected. I started down a path
where I would make a data structure that should represent a `SELECT` query. It
quickly became complex due to the desire to deliver the same use cases as when
using a string i.e. [basic arithmetic][3]. Also I couldn't decide on the best
way to deal with the fact that fields and tags can have the same name,in which
case, you are obligated to specify exactly which type you are referring to. Not
being convinced that representing the select query as a Clojure data structure
was actually a good idea, I took a step back and started my implementation
assuming that I already had the "select statement" string.

[3]: https://docs.influxdata.com/influxdb/v1.7/query_language/data_exploration/#select-a-specific-field-from-a-measurement-and-perform-basic-arithmetic

I also wanted the code to interact nicely with application state handled with
[Mount][4]. For that I decided to have the InfluxDB connection be represented by
a map:

```clojure
{:url "http://localhost:8086"
 :username "root"
 :password "root"}
```

Where the `:username` and `:password` would be optional.

[4]: https://github.com/tolitius/mount


This is what I ended up with for supporting the [`/query` HTTP endpoint][5]:

```clojure
(ns dk.emcken.influxdb-client
  (:require [cheshire.core :as json]
            [clj-http.client :as http-client]
            [clojure.string :as str]))

(defn prep-query-params
  "Convenience middleware to populate username and password from the connection if wanted."
  [{:keys [username password] :as conn} influx & additionals]
  (let [auth-params (when (and username password) {"u" username "p" password})]
    (apply merge (conj additionals influx auth-params))))

(def available-methods
  {::read http-client/get
   ::manage http-client/post})

(defn query
  "The query argument q can be either a string or a list/vector of strings. For
  valid influx-params see
  https://docs.influxdata.com/influxdb/v1.7/tools/api/#query-string-parameters-1"
  ([conn method q]
   (query conn method q {}))
  ([conn method q influx-params]
   (let [request-fn (or (method available-methods)
                        (throw (ex-info "Unknown query method." {:method method})))]
     (request-fn
      (str (:url conn) "/query")
      {:query-params (prep-query-params conn influx-params
                                        {"q" (if (string? q) q (str/join ";" q))})}))))

(defn unwrap
  "Takes a http response from the API endpoint and converts it to a Clojure data
  structure for convenience."
  [response]
  (-> response
      :body
      (json/parse-string)
      (get "results")))
```

[5]: https://docs.influxdata.com/influxdb/v1.7/tools/api/#query-http-endpoint

The above would allow for things like:

```clojure
user >  (require '[dk.emcken.influxdb-client :as client :refer [unwrap query]])
nil

user > (def conn {:url "http://localhost:8086"})
#'user/conn

user > (unwrap (query conn ::client/read "SHOW DATABASES"))
[{"series" [{"values" [["_internal"]], "columns" ["name"], "name" "databases"}], "statement_id" 0}]

user > (unwrap (query conn ::client/manage ["CREATE DATABASE mydb1" "CREATE DATABASE mydb2"]))
[{"statement_id" 0} {"statement_id" 1}]
```

I'm pretty happy with my 40-ish lines of Clojure code to do queries, and I might
even end up releasing it if my "write" implementation feels solid. The Java
client does have one really cool thing going for it, and that is asynchronous
writes. I assume this feature would be VERY handy with heavy data loads. But
with a difference in lines of code on almost 8k and 84 files, I think it would
possible to do something similarly awesome in Clojure.

What can I say... I love working with Clojure.
