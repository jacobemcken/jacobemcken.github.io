---
layout: post
title: What's so cool about Clojure - Lazy sequences
categories:
- Programming
tags:
- Clojure
- Lazy sequences
- Random hacks
comments: true
---
If you want some background on why I'm trying to answer my own question:

> What's so cool about Clojure?

please read [what sparked the question][1].

This post doesn't require any knowledge about Clojure but don't cheat yourself for the fun of playing around in the REPL :)

<!-- [1]: % post_url 2014-12-09-whats-so-cool-about-clojure %} -->

### Lazy

The following is how I would describe what a lazy sequence is - it's a
simplification but I believe that it lets you get to where the "action" is and
the last few tecnical details will automatically fall into place once there.

First [lazy][lazy-evaluation] means that the program carries around a formular
(function) to calculate a result, instead of the actual result. The result wont
be executed/computed/realized/evaluated until it is actually needed.

This is good for 2 reasons:

  * No need to use CPU resources to calculate stuff which is never used
  * Formular takes up less memory than the actual result

It is kinda the same effect you get using `if` statements and short circuiting
`and`/`or` operator.

``` php
<?php

function A() {
    echo "A was executed\n";
    return true;
}

function B() {
    echo "B was executed\n";
    return false;
}

function C() {
    echo "C was executed\n";
    return true;
}

if ( true ) {
    A();
}
else {
    B(); // Imagine that B is a heavy computation taking up lots of resources
}

/*
 Output will be:
 A was executed
*/


echo "and operator\n"
$return_and = A() && B() && C(); // Terminates at first false

echo "or operator\n"
$return_or  = B() || C() || A(); // Terminates at first true

/*
 Output will be:
 and operator
 A was executed
 B was executed
 or operator
 B was executed
 C was executed
*/
```

The reason that we can see which functions are executed is because we use `echo`
(which is a side-effects but that is another story). The above demonstrates how
we can take advantage of describing our intentions in the code without making
the computer actually calculate it which is my definition of "lazy" is.

### So what about sequences...

A lazy sequence is sequence from which only a small part is realised at a time.
This is awesome because it lets you describe **infinite sequences** without
running out of memory. I will agure that infinite sequences can be used to model
the code to represent the world around us more correctly and thus makes the code
easier to reason about. A lazy sequence isn't necessarily and infinite sequence
- far from it, BUT I'm hoping that it will better get my point across. After all
if your code works with an infinite sequence it can propably also handle a
finite one :-)

`range` is one of Clojures core functions which produces an infinite sequence. You could say the `range` is what describes an infinite sequence from 0 to well... infinite.

A difinition of an infinite sequence will only take you so far... the limits of hardware still applies.
The following tries to realize all numbers in `range`.

```
user> (range)
OutOfMemoryError Java heap space  java.util.Arrays.copyOf (Arrays.java:3332)
```

But the cool thing about lazy sequences is that they can be both filtered and
transfomed while retaining their laziness.


#### Filtering

No matter at which number the above realization of `range` died with insufficient
memory, the following (returning only even numbers):

``` clojure
(filter even? (range))
```

will realizate roughly the same sequence lenght before running out of memory
but get around twice as far into the `range` sequence before it does. This of
couse depends on garbage collection, and propably a bunch of other stuff too.

Let's imagine a function called `random-name` which returns a random name.
An infinite random name generator can the be defined with the help of [`repeatedly`][repeatedly]:

``` clojure
(def name-seq (repeatedly random-name))
```

A sequence is like a faucet that can be tapped of data. The sequence definition
control how data i generated, and infinite sequences can be tapped (consumed)
endlessly. What Clojure solves so elegantly is the way sequence generation and
consumation is decoupled. Also a sequence producer can easily be extended,
simply by applying an extra layer which doesn't touch the original producer, ie.:

``` clojure
(filter #(re-matches #"^[AEIOUY]..*" %) name-seq)
```

The above filters only names starting with (capital) vovels. Evaluation still
stops when there isn't need for any more data.

So to wrap up... the thing I love about Clojures sequences is the elegant
decoupling, the language semantics encurage you to use them because they are
easy to write.


TODO: An example using take
TODO: Mention infinite sequences where only a few elements are used will take long computation time.
TODO: Transformation "Take last name" capitalize first name

[lazy-evaluation]: http://en.wikipedia.org/wiki/Lazy_evaluation
http://clojure.org/sequences
http://theatticlight.net/posts/Lazy-Sequences-in-Clojure/
[repeatedly]: https://clojuredocs.org/clojure.core/repeatedly