---
layout: post
title: Heroicons from ClojureScript
categories:
- Programming
tags:
- cljs
- Clojure
- ClojureScript
- Heroicons
- Tailwind
comments: true
---

**Update 2022-02-20:** Requiring single icons using `:refer` causes ALL Hero
icons to be included in builds (even optimized). Instead, use `:as` (multiple
times). Examples below have been updated.

In my opinion, Clojure and ClojureScript is lacking in the documentation
department, especially when it comes to integrations with things outside the
Clojure ecosystem.

I want to share how using [Heroicons][1] from within a ClojureScript
[Reagent][2] (including Re-frame) application works. Some might find the
following obvious. But if you (like me) aren't using ClojureScript for frontend
development every day... it might not be.

Which is sad because it is actually quite easy... when you already have
[Shadow-cljs][3] configured 😎

First install it ([just like described in the documentation][4]):

```bash
npm install @heroicons/react
```

Now imagine already having a reagent component named
`my-component-missing-icon`:

```clojure
(ns my-app.core
  (:require
   [reagent.core ...] ; The three dots (...) means there is more
   ...))              ; but it isn't important for the example.

...

(defn my-component-missing-icon [] ; Shamelessly copied from Reagents website
   [:div
   [:p "I am a component!"]
   [:p.someclass
    "I have " [:strong "bold"]
    [:span {:style {:color "red"}} " and red "] "text."]])

...
```

Require a specific icon from `@heroicons/react/solid` and insert it in the
component (e.g. the icon `CheckIcon`):

```clojure
(ns my-app.core
  (:require
   ["@heroicons/react/solid/CheckIcon" :as CheckIcon] ; <- new stuff
   [reagent.core ...]
   ...))

...

(defn my-component-with-icon [] ; Component now slightly changed.
  [:div
   [:p "I am a component!"]
   [:> CheckIcon {:style {:height "5rem" :width "5rem"}}]
   [:p.someclass
    "I have " [:strong "bold"]
     [:span {:style {:color "red"}} " and red "] "text."]])
```

Notice `:>` which means "[creating a Reagent component from a React one][5]."

The *"having to convert a React component to Reagent"* was the thing I was
missing.

If Tailwind is thrown into the mix, it will be possible to style Heroicons like
`[:> CheckIcon {:class "h-5 w-5"}]`.

If build sizes are a concern, then avoid using `:refer` which otherwise could
seem like the obvious way to reference multiple icons.

**DON'T DO THIS - builds will include ALL icons from `@heroicons/react/solid`**:

```clojure
(ns my-app.core
  (:require
   ["@heroicons/react/solid" :refer [ChatIcon CheckIcon]] ; NOT optimizeable
   ...
```


To allow build tools (like Shadow-cljs) to properly optimize builds instead
require icons individually, even though it is more verbose:

```clojure
(ns my-app.core
  (:require
   ["@heroicons/react/solid/ChatIcon" :as ChatIcon]
   ["@heroicons/react/solid/CheckIcon" :as CheckIcon]]
   ...
```


Now go enjoy the JavaScript and React ecosystem from the comfort of
ClojureScript.

[1]: https://heroicons.com/
[2]: https://reagent-project.github.io/
[3]: https://github.com/thheller/shadow-cljs
[4]: https://github.com/tailwindlabs/heroicons#react
[5]: https://cljdoc.org/d/reagent/reagent/1.1.0/doc/tutorials/interop-with-react#creating-reagent-components-from-react-components