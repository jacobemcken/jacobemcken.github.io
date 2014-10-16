---
layout: post
status: publish
published: true
title: '"Hacking" Wordpress 2.7 MU - Part 2'
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
wordpress_id: 213
wordpress_url: http://emcken.dk/wp/archives/213-hacking-wordpress-27-mu-part-2.html
date: '2009-01-29 22:49:50 +0100'
date_gmt: '2009-01-29 22:49:50 +0100'
categories:
- Random hacks
tags: []
comments: []
---
<p>This is my part 2 of my pursuit on howto host multiple Wordpress blogs spreading over multiple different domains (not only sub-domains) on the same Wordpress MU installation. You can read the [first part here][1]. It seems to be possible without using the [Domain Mapping plugin][2] which also creates the extra table to provide the functionality (which seems unnecessary).</p>
<p>So far my research shows that it possible using what's already inside the Wordpress MU 2.7 release. There is no web interface for some of these things but with a few manual inserts into the database it seems to work like a charm.</p>
<p>After my last post I was able to create multiple blogs spread over mulitple domains. But before the blog was moved to its own domain you had to attach the users for the blog because the blog would disappear from the admin webinterface as soon as the site id changed (as soon as the blog was bound to another domain).</p>
<p>It was possible to login on the new domain with the admin user but the user would only serve as a blog admin and not as a site admin on that domain.</p>
<p>With the following I was able to make the admin user site admin for the new domain as well:</p>
<p>    INSERT INTO `wp_sitemeta`<br />
        ( `site_id` , `meta_key` , `meta_value` )<br />
    VALUES<br />
        ('2', 'site_admins', 'a:1:{i:0;s:5:"admin";}');</p>
<p>The number two (2) refers to the site_id your domain got in the table `wp_site`. If you want another user than admin to be the site admin you'll have to modify the strange string. Its not as hard as it might look. You can read about how to interpret the string on the [Wordpress MU forum - capabilities explanation][3] and the [PHP function serialize][4]</p>
<p>[1]: http:&#47;&#47;www.emcken.dk&#47;weblog&#47;archives&#47;212-Hacking-Wordpress-2.7-MU-pre-release.html<br />
[2]: http:&#47;&#47;wordpress.org&#47;extend&#47;plugins&#47;wordpress-mu-domain-mapping&#47;<br />
[3]: http:&#47;&#47;mu.wordpress.org&#47;forums&#47;topic.php?id=4915<br />
[4]: http:&#47;&#47;dk2.php.net&#47;serialize </p>
