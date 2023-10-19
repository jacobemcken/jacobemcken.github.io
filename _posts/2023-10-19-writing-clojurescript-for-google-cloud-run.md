---
layout: post
title: Writing an app for Google Cloud Run using ClojureScript
categories:
- Programming
tags:
- Clojure
- ClojureScript
- Docker
- Cloud
- Google Cloud
- Shadow-cljs
comments: true
excerpt_separator: <!--more-->
---

As part of a project I needed to handle webhooks from [Hubspot][] (a CRM),
and since the implementation turned out nicely,
I thought it would be worth blogging about.

The reason why the app ended up in [Cloud Run][] and written ClojureScript
was to avoid the hard coupling to the main application.

All the [code is available on GitHub][5].

<!--more-->

A small, separate app, only responsible for receiving the events,
would be simple and fast.
Simple and fast because with fewer things happening,
fewer things can go wrong and complexity and processing time goes down.

The prospect of the main application not having the responsibility of receiving webhook events was appealing.
If the main application for whatever reason would stop working,
events could continue to be offloaded.
As soon as the main application would come back up "handling" could continue.

To avoid the slow startup time often experienced when using Clojure on the JVM,
an alternative would be something either involving GraalVM or ClojureScript.
Both options brought me out of my comfort zone,
but I opted for ClojureScript because I had good experiences with it from a frontend development with re-frame
and because I found a promising [ClojureScript example interacting with Google Cloud Node.js SDK][1].


[Cloud Run]: https://cloud.google.com/run/
[Hubspot]: https://www.hubspot.com/
[1]: https://github.com/nbrandaleone-blog/hello-secret/tree/main


## Setting up the development environment

### Shadow-cljs

Shadow-cljs is very mature and has great documentation.
However, its many configuration knobs often lead to unnecessary complex setups. I've seen developers not understanding the `:dev` and `:release` mode
and instead setting up two different build targets.

The `hello-secret` example has a few other common unnecessary noisy configurations like repeating `:main server.main/main` for the `:release` mode
and setting up `:compiler-options {:optimizations :none}` for `:dev` mode
which is already the default.

If you need an optimization different than the `:target` default,
you should add a comment about the reason for it (for future reference).

Setups like this get copy-pasted unquestioned into countless new projects
which then start with an unnecessarily complex build target configuration.


### Fast feedback loop

Having a fast feedback loop is one of the most important things during development.
Luckily Shadow-cljs (also) delivers on this account
with excellent REPL integration and "hot reload".

Using a ClojureScript REPL is a two-step process and the details might vary depending on your IDE. But overall you need to...

1. Build (watch) the application, which outputs `target/main.js`
   and on top starts a REPL on a port:

       npx shadow-cljs watch hubspot

2. Provide a Node.js runtime, by starting the (now built) application:

       GCLOUD_PROJECT_ID=furry-whale-12345 \
       LEADS_HUBSPOT_PLUGIN_PUBSUB_TOPIC_ID=hubspot-webhooks \
       node target/main.js 8080 hubspot_secret.txt

The runtime is needed for the REPL to have somewhere to send the ClojureScript code (transpiled into JavaScript), for evaluation.

The lengthy CLI command to start the application,
is me playing with both environment variables and CLI arguments to configure the application.
Both are great ways to separate config from code,
which is [one of the cornerstones of The 12-Factor App][4].

Which brings us to "Hot reload".

Application configuration must be persisted somewhere
to avoid it being lost during "hot reloads".
I got inspired by how [Mount][] handles [CLI arguments][2],
by storing them in an atom.
The [Shadow-cljs documentation about Hot Code Reload][3] also hints the use of atoms for application state,
but it wasn't immediately obvious to me that application config was the "same thing".

For a simple, yet super effective Shadow-cljs setup, see [`shadow-cljs.edn`][7].

[Mount]: https://github.com/tolitius/mount/
[2]: https://github.com/tolitius/mount/blob/master/doc/runtime-arguments.md#passing-runtime-arguments
[3]: https://shadow-cljs.github.io/docs/UsersGuide.html#NodeHotCodeReload
[4]: https://12factor.net/config
[7]: https://github.com/full-spectrum/hubspot-webhooks/blob/a2cc32d8041ade07975c05bd10b13e9520d448aa/shadow-cljs.edn


## Webserver

The `hello-secret` example uses Express.js webserver which seems popular in JavaScript.
But using functions (`app.get('/' ...)`) to register routes was rubbing me the wrong way,
so I went looking for an alternative (ClojureScript-friendly) approach,

I found [Macchiato][] which seems of high quality,
but it also shows signs of "age":

- It uses `lein`, `cljsbuild` instead of Shadow-cljs and 'native' `npm`.
- For routing it uses Bidi instead of Reitit.
- Documentation not updated for 5 years.

I am not entirely sold on Macchiato yet
and might end up just using the built-in webserver in Node.js for this project
(since I don't need much flexibility with routing anyway).

Apart from the "age",
I dislike the "batteries included" (`lein new macchiato myapp`) approach.
Not because the batteries are old,
but because I prefer to add the things I need as I need them,
instead of everything (and more) from the get-go.
That way I also get a better understanding of how all the different things work together.

For an example of how Macchiato gives me a feeling of being "overcomplicated",
compare the project file from [Getting Started (under "The Project File")][6]
with the Shadow-cljs config file I'm using for my project.

[Macchiato]: https://macchiato-framework.github.io/
[Getting Started]: https://macchiato-framework.github.io/docs/getting-started.html


## SHA & crypt with Google Closure Tools

For signature verification the [Google Closure compiler][] comes out of the box with functionality for exactly this purpose.
There is no need to install third party libraries.

Unlike Clojure on the JVM,
where [Buddy][] (and all its transitive dependencies),
seems to be the go-to for such things.

Implementing signature verification in ClojureScript was also fairly easy,
because several good examples appears on Google searches.

[Google Closure compiler]: https://www.clojurescript.org/about/closure
[Buddy]: https://github.com/funcool/buddy-core


## Build using Docker

It was the first time I stumbled upon a `Dockerfile` with multi-stage build,
when going through the files in [`hello-secret`][1].

Pretty neat.

I tweaked the `Dockerfile` to reduce the number of steps,
that required rebuilding (unnecessarily) everytime the code changed.
I bumped the Java version (from 11 LTS to 17 LTS),
and also added a cache for the Java dependencies,
to avoid re-downloading dependencies on every re-build.


## Deploy to Google Cloud

I haven't automated deploying of the webhook application yet.
And with my limited amount of experience with Google Cloud,
I might not be the best person to hand out advice in this area anyway.

I can however highlight the different Google Cloud products
I needed to familiarize myself with
in order to get the application up and running:

- IAM & Admin
- Pub/Sub
- Artifact Registry
- Cloud Run
- Security (Secret manager)


*IAM & Admin* was needed for all aspects of the setup,
to manage permissions (access to resources)
even when running the code locally on my laptop (for Pub/Sub access).

To store the built Docker images (`docker push ...`),
with the webhook application, an *Artifact Registry* is required.
Without it, I wasn't able to choose the image from *Cloud Run*.

Even though environment variables seems to be best practic
when it comes to configuring applications,
it is far from optimal
when dealing with sensitive information like passwords, private keys and other "secrets".

Instead secrets can be injected into the container as a file mounted upon container start. Very handy.


# Final thoughts

It is really hard to avoid the JavaScript feel,
when interop'ing with JavaScript libraries or Node.js itself.
The way `async` and `await` is used everywhere in JS
forces you to use promises or `core.async` in places
where you probably wouldn't have under other circumstances.

An example of how JS Promises affect the ClojureScript code,
can bee seen in the [handler `handle-webhook`][8].

The project is still missing a `README.md`.
One one hand I want this code to be public
because I think the Clojure community benefit from the availability of examples like
this.
But on the other hand I need the documentation to match my personal needs.
I guess we'll find out if I crack it.

[5]: https://github.com/full-spectrum/hubspot-webhooks
[6]: https://macchiato-framework.github.io/docs/getting-started
[8]: https://github.com/full-spectrum/hubspot-webhooks/blob/a2cc32d8041ade07975c05bd10b13e9520d448aa/src/leads/plugins/hubspot/core.cljs#L57-L59
