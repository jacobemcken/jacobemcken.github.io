---
layout: post
title: Constructing dummy JSON Web Token (JWT) using ClojureScript
categories:
- Programming
tags:
- Clojure
- ClojureScript
comments: true
excerpt_separator: <!--more-->
---

[JWT][1] seems broadly used around the internet for all kinds of services
and I wanted to use it for a service of my own.

To increase testing speed while interacting with the backend,
I wanted to be able to rapidly create new JWTs
to see the effect of different payload structures.

<!--more-->

I ended up writing a bit of code to be able to construct dummy tokens,
without having to talk to a 3rd party service like [Auth0][3].
No re-authentication, no fiddling with service setup
and no adding/removing permissions etc.

This is the code:

```clojure
(ns jwt
  "Generate JTW using ClojureScript in browser.
   **WARNING** Never store signing key / secret client side (in browser).
   It isn't safe.

   Play around with different tokens scenarios in a local environment,
   without round-tripping 3. party services like Auth0 for every combination.
  
   Implementation reference: https://jwt.io/introduction
   Decode tokens in browser: https://jwt.io/"
  (:require [clojure.string :as str]
            [goog.crypt :as crypt]
            [goog.crypt.base64 :as base64])
  (:import [goog.crypt Hmac Sha256]))

(def hasher
  (Sha256.))

(defn sign
  [message secret]
  (let [hmacer (Hmac. hasher (crypt/stringToByteArray secret))]
    (.getHmac hmacer (crypt/stringToByteArray message))))

(defn clj->json
  [m]
  (.stringify js/JSON (clj->js m)))

(defn unsigned-token
  [header payload]
  (->> [header payload]
       (map (comp #(base64/encodeString % base64/Alphabet.WEBSAFE_NO_PADDING) clj->json))
       (str/join ".")))

(defn get-jwt
  ([payload secret]
   (get-jwt payload secret {}))
  ([payload secret extra-headers]
   (let [header (assoc extra-headers
                       "alg" "HS256" 
                       "typ" "JWT")
         token-base (unsigned-token header payload)
         signature (sign token-base secret)]
     (str token-base "." (base64/encodeByteArray
                          (js/Uint8Array. signature)
                          base64/Alphabet.WEBSAFE_NO_PADDING)))))

(comment
  (println (get-jwt {"sub" "1234567890"
                     "name" "John Doe"
                     "iat" 1516239022}
                    "your-256-bit-secret"))
)
```

[The code can also be found in a Gist][2].
I might update the code to support other signing algorithms than `HS256` at a later time.
The code has only been tested in a browser
but should work just the same in Node.js.


[1]: https://jwt.io/introduction
[2]: https://gist.github.com/jacobemcken/1c1d6caac8277b5e53fd1b2cec8552e3
[3]: https://auth0.com