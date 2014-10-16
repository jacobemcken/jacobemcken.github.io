---
layout: post
status: publish
published: true
title: Wordpress with Markdown
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
wordpress_id: 279
wordpress_url: http://emcken.dk/weblog/?p=279
date: '2009-05-29 21:42:24 +0200'
date_gmt: '2009-05-29 20:42:24 +0200'
categories:
- Uncategorized
tags:
- wordpress
- markdown
- plugin
comments:
- id: 357
  author: juan
  author_email: jmgolivares@gmail.com
  author_url: ''
  date: '2009-12-11 16:32:31 +0100'
  date_gmt: '2009-12-11 15:32:31 +0100'
  content: |
    <p>In where part of the code I need to put this to fix this error<&#47;p>

    <p>i'm using markdown on a wpmu instalation<&#47;p>
---
<p>Today I updated Wordpress which went smooth so I decided to fix a problem on my blog which appeared when I first migrated to Wordpress. I'm using the plugin [Markdown for WordPress and bbPress][1] so I can write posts with Markdown syntax and I use code sections heavily. The problem was that using special characters like: &amp; " '  within code paragraph would look like this:</p>
<p>    &amp; &#8221; &#8216;  < ></p>
<p>For now I just reverts posts upon displaying so the html entities are replaced by special characters. I know this is not the right way but its temporary :)</p>
<p>I asked on the Wordpress IRC and a nice guy called 'ansimation' gave me this to work with:</p>
<p>    function reconvert_pre_entities( $d ) {<br />
        return preg_replace_callback('&#47;
<pre>(.*?)&#47;sim', create_function('$matches', 'return( html_entity_decode($matches[0]) );'), $d );<br />
    }<br />
    add_action('the_content', 'reconvert_pre_entities' );</p>
<p>When I get a little time I'm going to incorporate this into the Markdown plugin so it wont affect any of the other blogs not using Markdown.</p>
<p>[1]: http:&#47;&#47;wordpress.org&#47;extend&#47;plugins&#47;markdown-for-wordpress-and-bbpress&#47;</p>
