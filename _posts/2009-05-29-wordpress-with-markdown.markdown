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
    In where part of the code I need to put this to fix this error


    i'm using markdown on a wpmu instalation

---
Today I updated Wordpress which went smooth so I decided to fix a problem on my blog which appeared when I first migrated to Wordpress. I'm using the plugin [Markdown for WordPress and bbPress][1] so I can write posts with Markdown syntax and I use code sections heavily. The problem was that using special characters like: &amp; " '  within code paragraph would look like this:

    &amp; &#8221; &#8216; &lt; &gt;

For now I just reverts posts upon displaying so the html entities are replaced by special characters. I know this is not the right way but its temporary :)

I asked on the Wordpress IRC and a nice guy called 'ansimation' gave me this to work with:

``` php
function reconvert_pre_entities( $d ) {
     return preg_replace_callback('/<pre>(.*?)/sim', create_function('$matches', 'return( html_entity_decode($matches[0]) );'), $d );
}
add_action('the_content', 'reconvert_pre_entities' );
```

When I get a little time I'm going to incorporate this into the Markdown plugin so it wont affect any of the other blogs not using Markdown.

[1]: http://wordpress.org/extend/plugins/markdown-for-wordpress-and-bbpress/

