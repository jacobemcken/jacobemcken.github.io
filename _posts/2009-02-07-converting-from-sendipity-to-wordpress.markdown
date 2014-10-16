---
layout: post
status: publish
published: true
title: Converting from Sendipity to Wordpress
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
excerpt: "Alright... my blog is now officially migrated from [Serendipity][1] (s9y)
  to [Wordpress][2]. I created the new webdesign a while back and made a few small
  changes tonight just before the switch... let me know if you find any problems with
  it. The design was inspired by a Wordpress theme by [webdemar.com][3].\r\n\r\nA
  while back I found a s9y importer on [Michael Tysons weblog][4] which worked pretty
  well. Since my last visit [a german guy][5] had made a few small changes to it to
  import extended posts. I had a few of those so I grabbed his script. I had to make
  a small change to the import script in order to get drafts imported as drafts (and
  not published).\r\nI also had to insert an extra newline before and after the more-tag
  ("
wordpress_id: 214
wordpress_url: http://emcken.dk/wp/?p=214
date: '2009-02-07 10:02:40 +0100'
date_gmt: '2009-02-07 09:02:40 +0100'
categories:
- Computer
- Graphics
- Random hacks
tags:
- wordpress
comments: []
---
<p>Alright... my blog is now officially migrated from [Serendipity][1] (s9y) to [Wordpress][2]. I created the new webdesign a while back and made a few small changes tonight just before the switch... let me know if you find any problems with it. The design was inspired by a Wordpress theme by [webdemar.com][3].</p>
<p>A while back I found a s9y importer on [Michael Tysons weblog][4] which worked pretty well. Since my last visit [a german guy][5] had made a few small changes to it to import extended posts. I had a few of those so I grabbed his script. I had to make a small change to the import script in order to get drafts imported as drafts (and not published).<br />
I also had to insert an extra newline before and after the more-tag (<a id="more"></a><a id="more-214"></a>) in order to get my markdown working as intended.</p>
<p>To get [Markdown][6] support in my posts I installed the [plugin 'text-control'][7] (even though its fairly old). I had to get the [newest markdown.php][8] and replace the one provided by the plugin to make it work (to get rid of php errors).</p>
<p>I had a few posts which have been pretty popular, so I made sure that old links would still work by using "Wordpress Permalinks - Custom Structure":</p>
<p>    &#47;archives&#47;%post_id%-%postname%.html</p>
<p>I had to make a "last minute" change which affects my Wordpress installation in order to get it working. I will blog about that later in a "Hacking Wordpress MU 2.7 - Part 3".</p>
<p>I hope you like the change :D</p>
<p>[1]: http:&#47;&#47;s9y.org&#47;<br />
[2]: http:&#47;&#47;mu.wordpress.org&#47;<br />
[3]: http:&#47;&#47;webdemar.com&#47;<br />
[4]: http:&#47;&#47;michael.tyson.id.au&#47;2008&#47;09&#47;04&#47;serendipity-s9y-importer-for-wordpress&#47;#comment-332<br />
[5]: http:&#47;&#47;www.dobschat.de&#47;index.php&#47;dobschat&#47;entry&#47;serendipity-s9y-importer-for-wordpress-13#englishversion<br />
[6]: http:&#47;&#47;daringfireball.net&#47;projects&#47;markdown&#47;<br />
[7]: http:&#47;&#47;wordpress.org&#47;extend&#47;plugins&#47;text-control&#47;<br />
[8]: http:&#47;&#47;michelf.com&#47;projects&#47;php-markdown&#47;</p>
