---
layout: post
title: Realtime DB synchronisation to frontend
categories:
- Programming, Database
tags:
comments: true
---

I've set out to solve: How to synchronize parts of a database to a frontend in
realtime for reads on "low volume" data. In this scenario "low volume" means up
to a few hundred and maybe even few thousand entries with a fairly low update
frequency (5-10 updates a minute). On top, add the fact that the backend
database uses a "write-only" pattern which means an entry is always given a new
internal id on every change to its properties.

TODO: Link to why write only databases are interesting.

Neo4J (a graph database) is assumed to be the backend database in the following
examples, but synchronization should follow the same pattern for a relational
database.

The synchronisation isn't allowed to use internal database id's for a better
decoupling. Instead an application managed id is used which is perfect to expose
outside the application i.e. for API's. An UUID would work great for such an
external id, but a slug is also an option when looking for something that should
be recognizable by humans (i.e. in URL's).

The data in the frontend is essentially a both smaller and simpler "read-only
view" of the backend database. Smaller because it only contains some selected
"low volume" parts and simpler because history is thrown away. The frontend
cannot manipulate "the view" directly, instead all data changes (create, update
and delete) are directed to the backend database, which propagates changes to
all connected frontends including the one that caused the change.


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

The external id "bike" is replaced with "ball", and changing properties at the
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

Now that an overview of how data changes have been established, it is time to
look at how to implement this. All above cases can be handled using a single
event representing a change to a single node. The event should carry the new
data along with the id of the node that is now obsolete.

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

Since two nodes was deleted it will trigger, two `changed` events.

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

The following implementation is made with Clojure but the majority of the code
below is the change events. The function with all the juicy stuff is
`change-state` (function body with 4 lines of code). This function takes the
current state along with event that is changing the state and calculates a new
state in an atomic way. Leveraging Clojure Atoms for state changes ensures
atomic changes (comparable to database transactions).

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
  "Takes the current state along with a \"change\"-event and returns the new state."
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

The above pattern can easily be applied to keep several parts of a database in
sync. Either several read views each representing a part, or a single view
containing multiple parts. The frontend only needing a single event listener for
each part of the data that it needs to keep in sync.


[1]: https://repl.it/@JacobEmcken/DarkturquoiseShinyParallelalgorithm#main.clj
