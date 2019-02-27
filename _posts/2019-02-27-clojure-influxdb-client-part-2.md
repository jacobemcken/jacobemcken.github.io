---
layout: post
title: Clojure InfluxDB client - part 2
categories:
- Programming
tags:
- Clojure
- Time series
comments: true
---

I've release a small [Clojure InfluxDB client library][1] since my [last
post][2] on the same subject, and as I mentioned I wanted to explore ways to
leverage the `/write` endpoint.

[1]: https://github.com/jacobemcken/clj-influxdb-client
[2]: http://www.emcken.dk/programming/2019/02/19/clojure-influxdb-client/

Though that didn't require much:

```clojure
(defn write
  [conn db data query-params]
  (http-client/post
     (str (:url conn) "/write")
     {:content-type :x-www-form-urlencoded
      :body data
      :query-params (prep-query-params conn query-params {"db" db})}))
```

Just like `SELECT`-statements for the `/query` endpoint, the above assumes that
`data` is already in the correct format. This however doesn't really add much value
for me as a programmer... but then - what does?

Going down that rabbit hole, gave me a prime example of how Clojure makes my
life an absolute pleasure. Exactly why that is the case, and a few thoughts about
my design decisions, is what I want to share in the remainder of the blog post.


## Into the rabbit hole

The [Line Protocol][3] describes the format of the `data` that the `write` function
takes, and here is the syntax:

> `<measurement>[,<tag_key>=<tag_value>[,<tag_key>=<tag_value>]] <field_key>=<field_value>[,<field_key>=<field_value>] [<timestamp>]`

I needed a way to represent a "measurement point" in code, and without jumping
hoops transform that to the Line Protocol. I guess they came to the same
conclusion for the Java client. They too have a [point representation
(`org.influxdb.dto.Point`)][4].

[3]: https://docs.influxdata.com/influxdb/v1.7/write_protocols/line_protocol_reference/
[4]: https://github.com/influxdata/influxdb-java/blob/d9c9e4919cf1eb4df7abea9d2a8c164f20928ae0/src/main/java/org/influxdb/dto/Point.java

Among Clojure's many strong points are representing stuff in data and manipulate
that data. The following are all valid representations of a point using
hash-map:

```clojure
;; minimal data required by the Line Protocol
{:measurement "cpu"
 :fields {:value 0.64}}

;; now also including a few tags
{:measurement "cpu"
 :tags {:host "serverA" :region "us_west"}
 :fields {:value 0.64}}

;; now with multiple fields and different data types along with a timestamp
{:measurement "cpu"
 :fields {:value 0.64 :verified true :count 4}
 :time 1434067467000000000}
```

To convert the above point representation to the Line Protocol, I set out to
address the following challenges:
- optional data (tags and time),
- different [data types][5], and
- different [time precision][6].

[5]: https://docs.influxdata.com/influxdb/v1.7/write_protocols/line_protocol_reference/#data-types
[6]: https://docs.influxdata.com/influxdb/v1.7/tools/api/#query-string-parameters-2


The optional data was fairly easy to solve and you'll see the implementation in
`point->line` (point to line):

```clojure
(defn point->line
  "Takes a point (hash-map) and optionally a precision and returns a string in
  the Line Protocol syntax."
  [{:keys [measurement fields tags time] :as point}]
  (str (str/join "," (conj (key-val->str tags) measurement))
       " "
       (str/join "," (key-val->str fields))
       (when time
         (str " " time))))
```

The `(when time` for an optional timestamp is straight forward. Though the
reverse order of measurement and optional tags, might look a bit odd if you
aren't familiar with `conj`. Use this as an excuse to [play with how `conj`
behavior][replit1] differs depending on whether it works on a list or a vector
(`key-val->str` always returns a list).

[replit1]: https://repl.it/repls/ClearcutTriflingGraphicslibrary

To solve the different data types was also straight forward because Clojure has
corresponding data types:

```clojure
(defn val->str
  [v]
  (cond
    (float? v) v
    (boolean? v) (if v "t" "f")
    (int? v) (str v "i")
    :else (str "\"" v "\"")))
```

I did however spend some time exploring the different integer types in Clojure
(Integer, Long and BigInt) before I felt comfortable with the above solution.

Two down, one to go. The final version of `point->line` ended up using a
slightly different implementation to handle time precision:

```clojure
(defn point->line
  "Takes a point (hash-map) and optionally a precision and returns a string in
  the Line Protocol syntax."
  [{:keys [measurement fields tags time] :as point} precision]
  (str (str/join "," (conj (key-val->str tags) measurement))
       " "
       (str/join "," (key-val->str fields))
       (when time
         (str " " (adjust-precision time precision)))))
```

The reason why `adjust-precision` is so important, is due to the following
recommendation in the official InfluxDB documentation:

> We recommend using the least precise precision possible as this can result
> in significant improvements in compression.

With this in mind, I wanted to make it possible for developers to be able use
alternatives to integers for representing an "instant" (like Java 8 Date Time
API or Joda-time). I chose to solve it using [multimethod][7].

[7]: https://clojuredocs.org/clojure.core/defmulti


```clojure
(def ratios
  {::ns 1
   ::u  1000
   ::ms 1000000
   ::s  1000000000})

(defmulti ->nano
  "Takes an instant and returns it as nano seconds since epoch."
 (fn [inst] (type inst)))

(defmethod ->nano :default
  [inst]
  (identity inst))

(defn adjust-precision
  "Takes an instant representation and returns the adjusted instant according to
  the precision. Use nil as precision to leave the instant as-is i.e. when
  already represented in the correct precision."
  [inst precision]
  (if-let [ratio (precision ratios)]
    (long (/ (->nano inst) ratio))
    inst))
```


Using a multimethod implementation eliminates the necessity of adding date
library dependencies for the InfluxDB client library. While still making it easy
to extend.

Here is how it would look for the Java 8 Date Time API (`java.time.Instant`):

```clojure
(defmethod ->nano java.time.Instant
  [^java.time.Instant inst]
  (+ (* (.getEpochSecond inst) 1000000000) (.getNano inst)))
```

Something similar could be implemented for Joda-Time.


## Now what?

My current implementation for creating Line Protocol data from a point doesn't
handle [special characters][8]. There are also some InfluxDB API end points that
the Clojure client still doesn't support. Since I'm not very familiar with them,
I would like to get some more experience with them. Better designs always emerge
when I have better insight, better context and better understanding of how these
endpoints help make my life easier.

I would also like to explore performance-related topics i.e. is the multimethod
for different "instant" representation a performance killer. Last but not least
the Java clients async writes have me intrigued... does something similar
belong in the Clojure InfluxDB client library?

[8]: https://docs.influxdata.com/influxdb/v1.7/write_protocols/line_protocol_reference/#special-characters
