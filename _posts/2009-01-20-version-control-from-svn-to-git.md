---
layout: post
status: publish
published: true
title: 'Version control: From svn to git'
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
wordpress_id: 211
wordpress_url: http://emcken.dk/wp/archives/211-version-control-from-svn-to-git.html
date: '2009-01-20 17:22:10 +0100'
date_gmt: '2009-01-20 17:22:10 +0100'
categories:
- Work
- Linux
tags: []
comments: false
---
Yesterday I played around with `git-svn` because git will be the future version control tool we will be using at work and we still have some projects stored in svn. I wanted to try out git but we are not about to convert the old projects just yet.

First I needed to check out the current code from svn and get it into git. I searched the net for how this was done and found something that cloned the entire svn repository to git... not quite what I needed but nice to know:

    git svn clone svn://192.168.1.5/htdocs git_htdocs

The above takes the project htdocs from my current svn repository and saves it locally into git repository in the folder git_htdocs.

I just wanted to start hacking so I found that the following fetched the code I wanted:

    mkdir git_htdocs
    cd git_htdocs
    git svn init svn://192.168.1.5/htdocs

Now you are ready to actually fetch some code from svn with:

    git svn fetch -r1519

I found the revision I needed (the newest) by issuing a `svn info` within my old svn checkout.

Make your changes and commit locally with:

    git commit lib/class.csv_import.php

When ready to push changes back to the central svn repository use:

    git svn dcommit

To keep your local git checkout in sync with the upstream svn repository you can run:

    git svn rebase

This article was great reading for me: [An introduction to git-svn for Subversion/SVK users and deserters][1]

Happy hacking :D

[1]: http://utsl.gen.nz/talks/git-svn/intro.html

