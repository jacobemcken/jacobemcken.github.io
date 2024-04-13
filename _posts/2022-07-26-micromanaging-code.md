---
layout: post
title: Stop micromanaging your code
categories:
- Programming
tags:
- Clojure
- php
- software-musings
comments: true
excerpt_separator: <!--more-->
---

This rant is about a bad habit some developers pick up and seem to have a hard
time ditching again... even after gaining lots of experience.

I guess it is to be expected. After having been burned one too many times by
missing error handling, in the software they work on, they become
overprotective. But it often overcomplicates the code and leaves room (extra
lines of code) to place "a fix", where "the fix" does not belong. Of course,
there are plenty of gray areas, murky waters and personal opinions of ...
exactly where to slice the cake.

<!--more-->

The "empty list" is a great example of overprotective code.


```php
// PHP
function executeActions($actions) {
  if (is_empty($actions)) {
    return;
  }
  else {
    foreach ($actions as $action) {
      execute($action);
    }
  }
}
```

Clojure equivalent:
```clojure
(defn execute-actions
  [actions]
  (when-not (empty? actions)
    (doseq [action actions]
      (execute action))))
```

But it could be even worse 😅
```php
// PHP
function executeActions($actions) {
  if (is_empty($actions)) {
    throw new Exception("Actions cannot be empty");
  }
  else {
    foreach ($actions as $action) {
      execute($action);
    }
  }
}
```

Clojure equivalent:
```clojure
(defn execute-actions
  [actions]
  (if (empty? actions)
    (throw (ex-info "Actions cannot be empty"))
    (doseq [action actions]
      (execute action))))
```


To fully understand the implications this code introduces, you have to put
yourself in the shoes of whoever calls `executeActions`. Picture how the code
will look on their end. The caller will need to know that empty lists are not
accepted (which I don't think a type system can nor should protect you from):

```php
// PHP
$actions = generateActions();

if (! is_empty($actions)) {
  executeActions($actions);
}
```

Clojure equivalent:
```clojure
(let [actions (generate-actions)]
  (when-not (empty? actions)
    (execute-actions actions)))
```

Often the special case pictured above is avoidable for both caller and callee of
the function. I would argue that the following code is much simpler to reason
about:

```php
// PHP
function executeActions($actions) {
  foreach ($actions as $action) {
    execute($action);
  }
}

...

$actions = generateActions();

executeActions($actions);
```


Clojure equivalent:
```clojure
(defn execute-actions
  [actions]
  (doseq [action actions]
    (execute action)))

...

(let [actions (generate-actions)]
  (execute-actions actions))
```


Usually, it will not hurt looping over an empty list. When it does hurt,
consider if error handling has been placed correctly.

> 💡 Hint: Checking for empty lists should often have been done much earlier
> than right before the loop (fail early).

Considering "empty lists" as an error, usually falls in the category of
"business errors". Postponing the "empty check" until right before the loop, can
be a sign that business logic is placed wrongly (too late).

I urge you to think twice when you are tempted to do an "empty check".
Stop micromanaging your code - learn to let go.