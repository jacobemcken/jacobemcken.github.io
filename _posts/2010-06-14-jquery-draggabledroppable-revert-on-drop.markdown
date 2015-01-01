---
layout: post
status: publish
published: true
title: 'jQuery Draggable/Droppable: Revert on drop'
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
wordpress_id: 325
wordpress_url: http://www.emcken.dk/weblog/?p=325
date: '2010-06-14 10:47:43 +0200'
date_gmt: '2010-06-14 09:47:43 +0200'
categories:
- Random hacks
- Work
tags:
- jquery
- draggable
- droppable
- revert
comments:
- id: 442
  author: Kasper Johansen
  author_email: k@spernj.org
  author_url: http://kaspernj.org
  date: '2010-08-05 09:18:38 +0200'
  date_gmt: '2010-08-05 08:18:38 +0200'
  content: |
    Hi Jacob.


    Not sure if I understand your problem rigth, but have you tried turning async off like this:


    $.ajax((type: "GET", url: "...", async: false...


    That way the function wont return before the callback is done.

- id: 443
  author: Jacob Emcken
  author_email: jacob@emcken.dk
  author_url: http://www.emcken.dk/
  date: '2010-08-08 15:44:49 +0200'
  date_gmt: '2010-08-08 14:44:49 +0200'
  content: |
    Yes I know but I think this would lock the UI until a response is returned.


    This is what discouraged me:


    <blockquote>
      By default, all requests are sent asynchronous (i.e. this is set to true by default). If you need synchronous requests, set this option to false. Cross-domain requests and dataType: "jsonp" requests do not support synchronous operation. Note that synchronous requests may temporarily lock the browser, disabling any actions while the request is active.

    </blockquote>

    Source: http://api.jquery.com/jQuery.ajax/

- id: 545
  author: Michael L Watson
  author_email: developercoffeeshop@gmail.com
  author_url: http://michaellwatson.co.uk/website
  date: '2011-12-20 12:28:25 +0100'
  date_gmt: '2011-12-20 11:28:25 +0100'
  content: |
    nice post thanks, I found in a live enviroment I had to set the left and top position, then add the offset and use ui.helper, if this helps anyone.

- id: 572
  author: Hussain Al-Mutawa
  author_email: hussain.mutawa@gmail.com
  author_url: http://jprobe.googlecode.com
  date: '2012-03-22 15:38:29 +0100'
  date_gmt: '2012-03-22 14:38:29 +0100'
  content: |
    did you try this:
    <code>
    $(dragged).draggable( "option", "revert", true );
    </code>

- id: 574
  author: Peter Drinnan
  author_email: contact@pdvictor.com
  author_url: http://www.pdvictor.com
  date: '2012-03-29 15:47:13 +0200'
  date_gmt: '2012-03-29 14:47:13 +0200'
  content: |
    This was almost exactly what I needed but I made a little tweak to ensure the draggable item reverts to the correct original position every time. 


    $(this).data('startPosition', $(this).position());


    CHANGED TO 


    $(this).data('startPosition', ui.position);

- id: 905
  author: wifibooster.com.my
  author_email: elizafarthing@t-online.de
  author_url: http://wifibooster.com.my/contact/
  date: '2014-07-02 16:10:21 +0200'
  date_gmt: '2014-07-02 15:10:21 +0200'
  content: "Attractive component of content. I just stumbled \nupon your site and
    in accession capital to say that I get in fact loved account your weblog posts.\nAny
    way I will be subscribing for your feeds or even I achievement you get right of
    entry to constantly quickly.
\n"
---
# Background:
At work we are currently in the process of creating a general data hub which is easily expendable and configurable. It's already in use exchanging EDI messages (EDIFACT) for a supplier on the danish electricity market.

For the configuration we settled on a GUI where you could drag different kinds of data manipulations into the data flow and this is all implemented in HTML with jQuery.

# The problem:
Upon drop I had to use Ajax to ask the server if it was ok to drop the draggable. Since Ajax is asynchronous the drop event would return before the Ajax actually had finished. I decided that I didn't want to force the request to the server to be synchronous instead I wanted the ajax success callback to be able to do the reverting.

Upon searching the net, the closest thing I came to a solution was defining a [callback function for revert on the draggable][1], and this functionality isn't even documented by the time of this writing.

# The solution:
After playing a bit around I found an acceptable solution. I've created a small [example reverting a drop][2] using a confirm where you can test it (and see the code).

[1]: http://www.agilepro.com/blog/2009/12/while-this-functionality-is-built-into.html
[2]: http://pastebin.me/eafc2763df039a0579eda5a7adc7fba1

