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

To understand what a lazy sequence is, it's necessary to understand the concept
lazy first. The following is how I would describe "lazy" - it's a simplification
but I believe that it lets you get to where the "action" is and the last few
technical details will automatically fall into place once there.

First [lazy][lazy-evaluation] means that the program carries around a formula
(function) to calculate a result, instead of the actual result. The result wont
be realized until it is actually needed.

This is good for 2 reasons:

  * No need to use CPU resources to calculate stuff which is never used
  * Formula takes up less memory than the actual result

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
the computer actually calculate it - this is my definition of "lazy".


### The producer

Lazy sequenzes needs to come from somewhere
Clojure have several functions which produces a lazy sequenze.
Iterate/inc -> range
Illustration
Finite vs. infinite


### So what about sequences...

A lazy sequence is a sequence from which only a small part of its elements are
realized at a time. What makes a sequence lazy is that the amount of elements
produced, is automatically throttled by the demand in the end.

Imagine an assembly line with workers along it. The assembly line carries the
elements of a sequence through a chain of workers (functions) manipulating the
sequence along the way. That which produces elements to supply the input of the
assembly line directly affects the **possible max amount** of elements which the
assembly line can output. While the demand off the assembly lines end, controls
the **actual max amount** of elements.

The **actual max amount** can never exceed the **possible max amount**.

The following examples shows sequences at play using "lumps of clay" as elements.
The numbers to the right are the max possible elements that can pass a point.

<style>

.assembly-line {
  float: left;
  width: 220px;
  padding: 0 15px 15px 15px;
  margin-bottom: 20px;
  background-color: #efefef;
  border-radius: 5px;
  margin-right: 5px;
}

.assembly-line h4 {
  margin-top:
}

.assembly-line .belt {
  clear: both;
  position:relative;
}

.belt div {
  line-height: 40px;
  height: 40px;
  margin-bottom: 5px;
}

.assembly-line .belt .icon {
  background-size: contain;
  text-align: center;
  width: 40px;
  color: white;
  float: left;
  clear: both;
}

.assembly-line .belt .worker {
  background-image: url('/public/media/worker.svg');
}

.assembly-line .belt .arrow {
  background-image: url('/public/media/down-arrow.svg');
}

.assembly-line .belt .count {
  float: right;
  width: 20px;
  border-left: 1px solid #202020;
  padding-left: 10px;
}

.assembly-line .belt .description {
  margin-left: 45px;
  padding-left: 10px;
  font-size: 75%;
}
</style>

<div class="assembly-line">
  <h4>Assembly line 1</h4>
  <div class="belt">
    <div class="icon arrow"></div>
    <div class="count">3</div>
    <div class="description">Lumps of clay</div>
    <div class="icon worker">A</div>
    <div class="count">3</div>
    <div class="description">Boxing</div>
    <div class="icon arrow"></div>
    <div class="count">3</div>
    <div class="description">Take all</div>
  </div>
</div>

<div class="assembly-line">
  <h4>Assembly line 2</h4>
  <div class="belt">
    <div class="icon arrow"></div>
    <div class="count">3</div>
    <div class="description">Lumps of clay</div>
    <div class="icon worker">A</div>
    <div class="count">9</div>
    <div class="description">Slice into ⅓</div>
    <div class="icon worker">B</div>
    <div class="count">9</div>
    <div class="description">Boxing</div>
    <div class="icon arrow"></div>
    <div class="count">4</div>
    <div class="description">Take only 4</div>
  </div>
</div>

<div class="assembly-line">
  <h4>Assembly line 3</h4>
  <div class="belt">
    <div class="icon arrow"></div>
    <div class="count">6</div>
    <div class="description">Lumps of clay</div>

    <div class="icon worker">A</div>
    <div class="count">6</div>
    <div class="description">Incremental id</div>

    <div class="icon worker">B</div>
    <div class="count">3</div>
    <div class="description">Remove odd ids</div>

    <div class="icon worker">C</div>
    <div class="count">3</div>
    <div class="description">Boxing</div>

    <div class="icon arrow"></div>
    <div class="count">3</div>
    <div class="description">Take only 5</div>
  </div>
</div>

<div style="clear:both;">&nbsp;</div>

Ekempler på

  - **Assembly line 1** - is just a simple (boring) example turning 3 lumps of
    clay into 3 boxed lumps of clay to present how the assembly line is visually
    presented.
  - **Assembly line 2** - is an example of an assembly line which is capable of
    overproducing - but wont because of the laziness which down throttles the
    initial producer.
  - **Assembly line 3** - shows how the max possible amount affects the actual
    max amount.

Now we've seen how finite amount of elements are affected, and how "Assembly line
3" wasn't able to supply the demand. This issue disappears when working with
infinite sequences:

<div class="assembly-line">
  <h4>Assembly line 3-B</h4>
  <div class="belt">
    <div class="icon arrow"></div>
    <div class="count">∞</div>
    <div class="description">Lumps of clay</div>

    <div class="icon worker">A</div>
    <div class="count">∞</div>
    <div class="description">Incremental id</div>

    <div class="icon worker">B</div>
    <div class="count">∞</div>
    <div class="description">Remove odd ids</div>

    <div class="icon worker">C</div>
    <div class="count">∞</div>
    <div class="description">Boxing</div>

    <div class="icon arrow"></div>
    <div class="count">∞</div>
    <div class="description">Take all</div>
</div>
</div>

<div style="clear:both;">&nbsp;</div>


Without limiting the output in "Assembly line 3-B" it will keep producing
elements.

Clojure have several core functions to produce sequences.
One is called [`range`][range] which produces an infinite sequence of whole
numbers from 0:

    0, 1, 2, 3, 4, 5... n (infinity)

A definition of an infinite sequence will only take you so far... the limits of
hardware still applies. Evaluating the following from a REPL tries to realize
all numbers in `range`.

```
user> (range)
OutOfMemoryError Java heap space  java.util.Arrays.copyOf (Arrays.java:3332)
```

[range]: http://clojuredocs.org/clojure.core/range


But the cool thing about lazy sequences is that they can be both filtered and
transformed while retaining their laziness. So unless every element is needed
simultaneously your program can just thew one or a few elements at the time.

I will argue that Clojures (infinite) sequences can be used to model the world
we live in closer to "reality". The sequence lengths (hardware limits) and
element types (be that users, dates, invoices or... lumps of clay) wont
needlessly clutter the code. It makes it easy to implement and recognize
business logic, and we all love code that is easy to read, write, maintain and
extend.

Lets take a look at the infinite "Assembly line 3" from before where the demand
is adjusted. This avoids the "out of memory" issue and everything before the end
point is untouched. All code (business logic) is exactly the same whether you
need 1 "lump of clay" or a billion.

<div class="assembly-line">
  <h4>Assembly line 3-C</h4>
  <div class="belt">
    <div class="icon arrow"></div>
    <div class="count">∞</div>
    <div class="description">Lumps of clay</div>

    <div class="icon worker">A</div>
    <div class="count">∞</div>
    <div class="description">Incremental id</div>

    <div class="icon worker">B</div>
    <div class="count">∞</div>
    <div class="description">Remove odd ids</div>

    <div class="icon worker">C</div>
    <div class="count">∞</div>
    <div class="description">Boxing</div>

    <div class="icon arrow"></div>
    <div class="count">5</div>
    <div class="description">Take only 5</div>
  </div>
</div>

<div style="clear:both;">&nbsp;</div>


Okay - this post has already gotten too long but at least we've established that **lazy sequences are awesome**.
I have some ideas for a bit more on Clojures lazy sequences which involves code instead of t


## Lazy sequences in Clojure

No matter at which number the realization of `range` from before died with
insufficient memory, the following (which only returns the even numbers):

``` clojure
(filter even? (range))
```

will realize roughly the same sequence length before running out of memory,
BUT get around twice as far into the `range` sequence before it does. This of
couse depends on garbage collection, and probably a bunch of other stuff too.

The `filter` function is a function the produces a lazy sequence just like
`range`. The main difference is that `filter` takes a sequence as input to
generate its output, while `range` just takes a starting point.

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
(filter first-char-vocal? name-seq)
```

The above filters only names starting with (capital) vovels. Evaluation still
stops when there isn't need for any more data.

So to wrap up... the thing I love about Clojures sequences is the elegant
decoupling, the language semantics encurage you to use them because they are
easy to write.


TODO: Mention infinite sequences where only a few elements are used will take long computation time.
TODO: Transformation "Take last name" capitalize first name

[lazy-evaluation]: http://en.wikipedia.org/wiki/Lazy_evaluation
http://clojure.org/sequences
http://theatticlight.net/posts/Lazy-Sequences-in-Clojure/
[repeatedly]: https://clojuredocs.org/clojure.core/repeatedly

Beskrivelse af lazy sequences
behøvere ikke joins og forks
Snak om en simpel producer i stedet
Tekst beskriver et og visualiseringer noget andet