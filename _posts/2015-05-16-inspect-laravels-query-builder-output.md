---
layout: post
title: Inspect Laravels query builder output
categories:
- Programming
tags:
- PHP
- Laravel
- Eloquent
comments: true
---
When learning something new (in this case Laravel and it's ORM Eloquent) it is
always nice to be able to "look behind the curtains" to see what actually
happens. This both help you understand the internals of the things you are
using and can sometime make bug squashing a lot easier.

I wanted to be able to see the SQL that Eloquents query builder was producing,
because SQL is something I'm fairly proficient with.

I found several questions and answers on [different][1] [approaches][2] on
StackOverflow but one approach stood out for me: Using Laravels internal events.

Every time Laravel does anything through a database connection an event i fired
with the prepared statement and its bindings. But creating SQL queries from
this still required some manual labor because I needed to replace all the
question marks with the binding in order.

So to close this gab further I had the prepared SQL and bindings crunched by
`sprintf` before writing it to a log file or stdout, like so:

``` php
\Event::listen(
    'illuminate.query',
    function($query, $params, $time, $conn)
    {
        $sql = str_replace('?', '%s', $query);
        $preppedSql = call_user_func_array('sprintf', array_merge([$sql], $params));

        echo $preppedSql . "\n"; // or write it to a log file
    });
```

[1]: http://stackoverflow.com/questions/14536165/get-the-query-executed-in-laravel-3-4
[2]: http://stackoverflow.com/questions/18236294/how-do-i-get-the-query-builder-to-output-its-raw-sql-query-as-a-string