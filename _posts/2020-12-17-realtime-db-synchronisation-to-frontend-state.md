---
layout: post
title: Realtime DB synchronisation to frontend
categories:
- Programming
- Database
tags:
- Clojure
- Graph database
comments: true
---

I've set out to solve: How to synchronize "low volume" parts of a database to a
frontend in realtime exclusively for reads. In this scenario, "low volume" means
few entries with a low update frequency. Let's say we're talking about less than
a thousand entries affected by fewer than ten updates every minute across all
entries.

The backend database will use a "insert-only" pattern, causing a single entry to
take up an extra row (relational DB) or node (graph DB) for every change. The
"insert-only" pattern also hinders using internal database ids (like auto
increment) for referencing an entry since every "update" will generate new ids.
Instead, use an application managed entry id like a UUID or, for a
human-recognizable id, a slug.

[Watch this video for a bit of background of why I find a "insert-only" pattern
interesting.][4]

The frontend is only concerned about the newest version of an entry, throwing
away the old version on an update. When it comes to the availability of entries
in the frontend, we can compare it to a "database view," from which data can
only be `Read`. The rest of the `CRUD` operations (`Create`, `Update`, and
`Delete`) must go to the backend, from where changes will propagate to all
connected frontends.

Only caring for "low volume" data, disregarding "historical data" and a one-way
data flow, requirements for the frontend "database" storage naturally becomes
easy to meet.

The following example will assume the backend database being a [graph
database][3], but the same pattern should work with a relational database. The
frontend will assume the use of a state management pattern like Redux or Vuex,
and data changes will be transferred using a WebSocket.


## Initial state

Overview of the data of a specific node type in the Graph database:

| Node | Properties                           | Relation(s) |
|------|--------------------------------------|-------------|
| A    | `{"slug": "car", "color": "blue"}`   |             |
| B    | `{"slug": "fish", "color": "red"}`   |             |
| C    | `{"slug": "car", "color": "marine"}` | REPLACES->A |


Which should result in something like the following in the UI state:

```json
{
  "car":  {"slug": "car",  "color": "marine"},
  "fish": {"slug": "fish", "color": "red"}
}
```

## Create

Now a new entry gets added:

| Node | Properties                           | Relation(s) |
|------|--------------------------------------|-------------|
| A    | `{"slug": "car", "color": "blue"}`   |             |
| B    | `{"slug": "fish", "color": "red"}`   |             |
| C    | `{"slug": "car", "color": "marine"}` | REPLACES->A |
| D    | `{"slug": "bike", "color": "cyan"}`  |             |


Which should result in something like the following in the UI state:

```json
{
  "car":  {"slug": "car",  "color": "marine"},
  "fish": {"slug": "fish", "color": "red"},
  "bike": {"slug": "bike", "color": "cyan"}
}
```

## Update

Now the "car" node gets updated:

| Node | Properties                           | Relation(s) |
|------|--------------------------------------|-------------|
| A    | `{"slug": "car", "color": "blue"}`   |             |
| B    | `{"slug": "fish", "color": "red"}`   |             |
| C    | `{"slug": "car", "color": "marine"}` | REPLACES->A |
| D    | `{"slug": "bike", "color": "cyan"}`  |             |
| E    | `{"slug": "car", "color": "azure"}`  | REPLACES->C |


Which should result in something like the following in the UI state:

```json
{
  "car":  {"slug": "car",  "color": "azure"},
  "fish": {"slug": "fish", "color": "red"},
  "bike": {"slug": "bike", "color": "cyan"}
}
```

## Delete

Archive might be a better term than delete, but delete is something commonly
known from CRUD operations. Now both the "fish" and "car" node gets deleted:

| Node | Properties                           | Relation(s)            |
|------|--------------------------------------|------------------------|
| A    | `{"slug": "car", "color": "blue"}`   |                        |
| B    | `{"slug": "fish", "color": "red"}`   | <-DELETED              |
| C    | `{"slug": "car", "color": "marine"}` | REPLACES->A            |
| D    | `{"slug": "bike", "color": "cyan"}`  |                        |
| E    | `{"slug": "car", "color": "azure"}`  | REPLACES->C, <-DELETED |


Causing new state in UI:

```json
{
  "bike": {"slug": "bike", "color": "cyan"}
}
```


## Update external id

The external id "bike" is replaced with "ball," and changing properties at the
same time works just the same:

| Node | Properties                           | Relation(s)            |
|------|--------------------------------------|------------------------|
| A    | `{"slug": "car", "color": "blue"}`   |                        |
| B    | `{"slug": "fish", "color": "red"}`   | <-DELETED              |
| C    | `{"slug": "car", "color": "marine"}` | REPLACES->A            |
| D    | `{"slug": "bike", "color": "cyan"}`  |                        |
| E    | `{"slug": "car", "color": "azure"}`  | REPLACES->C, <-DELETED |
| F    | `{"slug": "ball", "color": "navy"}`  | REPLACES->D            |


Causing new state in UI:

```json
{
  "ball": {"slug": "ball", "color": "navy"}
}
```


## Syncing the frontend

Now that we have an overview of the data operations, let's look at the data flow
between the back- and the frontends. A single event representing a change to a
single entry can handle all the cases above. The event should carry the new data
along with the id of the entry that is now obsolete.


### Create

```json
{
  "event":   "changed",
  "new":     {"slug" : "bike", "color" : "cyan"},
  "replace": null
}
```


### Update

```json
{
  "event":   "changed",
  "new":     {"slug" : "car", "color" : "azure"},
  "replace": "car"
}
```


### Delete

The deletion of two entries will trigger two `changed` events.

```json
{
  "event":   "changed",
  "new":     null,
  "replace": "fish"
}

{
  "event":   "changed",
  "new":     null,
  "replace": "car"
}
```


### Update external id

```json
{
  "event":   "changed",
  "new":     {"slug" : "ball", "color" : "navy"},
  "replace": "bike"
}
```


## Implementation

The following implementation is made with Clojure to simulate "state
management," but most of the code below is the data for `changed` events. The
function with all the juicy stuff is `change-state` (function body with four
lines of code). This function takes the current state and event that is changing
the state and calculates a new state. Leveraging [Clojure Atoms][2] for state
changes ensures atomic changes (comparable to database transactions).

[Run and play with the code on repl.it.][1]


```clojure
(def ui-state
  "Atom containing UI global state - assuming it has already been initialised."
  (atom {"car"  {"slug" "car", "color" "marine"}
         "fish" {"slug" "fish", "color" "red"}}))

(def create-event
  {"event"   "changed",
   "new"     {"slug" "bike", "color" "cyan"},
   "replace" nil})

(def update-event1
  {"event"   "changed",
   "new"     {"slug" "car", "color" "azure"},
   "replace" "car"})

(def delete-event1
  {"event"   "changed",
   "new"     nil,
   "replace" "fish"})

(def delete-event2
  {"event"   "changed",
   "new"     nil,
   "replace" "car"})

(def update-event2
  {"event"   "changed",
   "new"     {"slug" "ball", "color" "navy"},
   "replace" "bike"})

(defn change-state
  "Takes the current state along with a \"changed\"-event and returns the new state."
  [state event]
  (-> state
      (dissoc (get event "replace"))
      (conj (when-let [k (get-in event ["new" "slug"])]
              [k (get event "new")]))))

;; Apply all events to the UI state in order
(swap! ui-state change-state create-event)
(swap! ui-state change-state update-event1)
(swap! ui-state change-state delete-event1)
(swap! ui-state change-state delete-event1)
(swap! ui-state change-state delete-event2)
(swap! ui-state change-state update-event2)

```


## Conclusion

We can easily apply the pattern above to keep several parts of a backend
database in sync with a "read view" in the frontend. The frontend will only need
a single event listener for an entry type to create, update, and delete actions
to keep data in sync.

This was definitely simpler than I expected when I set out to figure this out.


[1]: https://repl.it/@JacobEmcken/DarkturquoiseShinyParallelalgorithm#main.clj
[2]: https://clojure.org/reference/atoms
[3]: https://en.wikipedia.org/wiki/Graph_database
[4]: https://www.youtube.com/watch?v=I3uH3iiiDqY
