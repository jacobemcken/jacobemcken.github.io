---
layout: post
status: publish
published: true
title: Migration from old 8-bit locales to UTF-8 locales
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
wordpress_id: 118
wordpress_url: http://emcken.dk/wp/archives/118-migration-from-old-8-bit-locales-to-utf-8-locales.html
date: '2005-07-06 12:35:12 +0200'
date_gmt: '2005-07-06 12:35:12 +0200'
categories:
- Uncategorized
tags: []
comments: []
---
<p>Several times I have had "great fun" converting filenames from the old 8-bit locales to use the new UTF-8 locales. Everytime, I used some time to find find a tool for it because everytime I forget the name of the tool `convmv`.<br />
The fuckup usually shows itself on the file server (Samba) where the users happily uses &aelig;&oslash;&aring; for filenames.</p>
<p>Now I'll blog about so hopefully next time I can remember the name of the tool or atleast just make a search on my blog.</p>
<p>Debian has packages in their repository bu for SLES you need to download the tool manually. I found the tool on [download.com][1]</p>
<p>[1]: http:&#47;&#47;www.download.com&#47;Convmv&#47;3000-2238_4-10305901.html</p>
