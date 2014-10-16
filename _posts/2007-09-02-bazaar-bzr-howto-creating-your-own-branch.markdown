---
layout: post
status: publish
published: true
title: Bazaar (bzr) howto - Creating your own branch
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
wordpress_id: 200
wordpress_url: http://emcken.dk/wp/archives/200-bazaar-bzr-howto-creating-your-own-branch.html
date: '2007-09-02 00:45:00 +0200'
date_gmt: '2007-09-02 00:45:00 +0200'
categories:
- Random hacks
tags: []
comments:
- id: 168
  author: Anonymous
  author_email: ''
  author_url: ''
  date: '2008-04-22 10:35:13 +0200'
  date_gmt: '2008-04-22 09:35:13 +0200'
  content: Good article, mate. I've only installed the bazaar, today. Now am about
    to start committing my projects. Cheers.
- id: 183
  author: 'Anonymous '
  author_email: ''
  author_url: ''
  date: '2008-11-04 17:35:00 +0100'
  date_gmt: '2008-11-04 16:35:00 +0100'
  content: thank you very much for your clear howto.
- id: 307
  author: Anonymous
  author_email: test@test.de
  author_url: ''
  date: '2009-04-22 19:36:58 +0200'
  date_gmt: '2009-04-22 18:36:58 +0200'
  content: Tnx. A very good clear intro to bzr. Well written.
- id: 316
  author: Xavi
  author_email: xavier.depedro@ub.edu
  author_url: ''
  date: '2009-05-11 09:18:40 +0200'
  date_gmt: '2009-05-11 08:18:40 +0200'
  content: "thanks. Short, clean, clear. Enough to get me started with my bazaar tests
    (and there is a nice bazaar gui to make it even simpler, for those who are gui-oriented:
    Olive bzr-gtk ) \r\ncheers"
- id: 448
  author: Bob Dinitto
  author_email: bob@ninzo.com
  author_url: http://www.ninzo.com/
  date: '2010-09-19 16:56:18 +0200'
  date_gmt: '2010-09-19 15:56:18 +0200'
  content: |
    <p>Very nice, clear, concise instructions. Thanks greatly! -bob<&#47;p>
- id: 456
  author: 'Notas sobre programaci&oacute;n de aplicaciones de escritorio en python:
    teor&iacute;a y pr&aacute;ctica'
  author_email: ''
  author_url: http://blog.etxea.net/index.php/2010/10/21/notas-sobre-programacion-de-aplicaciones-de-escritorio-en-python-teoria-y-practica
  date: '2010-10-21 14:17:35 +0200'
  date_gmt: '2010-10-21 13:17:35 +0200'
  content: |
    <p>[...] Bazaar para la gesti&oacute;n del c&oacute;digo&nbsp;http:&#47;&#47;www.emcken.dk&#47;weblog&#47;archives&#47;200-bazaar-bzr-howto-creating-your-own-branch.html [...]<&#47;p>
---
<p>I have been learning a bit of [Mono][] over the last couple of months and yesterday I decided that I wanted my code in a verison control system. The choice fell on [Bazaar][] for various reasons which is unimportant and uninteresting at this point. Right now I just wanna write down how I did :D</p>
<p>First off you tell Bazaar who you are with:</p>
<p>    bzr whoami "Jacob Emcken "</p>
<p>To test it is set correct just type:</p>
<p>    bzr whoami<br />
    Jacob Emcken </p>
<p>For the sake of it, lets imagine my project I want to version control is called `Starfire`.<br />
Go to the directory with the project and initialze the directory as a Bazaar branch:</p>
<p>    cd Projects&#47;Starfire<br />
    bzr init</p>
<p>Within that directory a new directory called `.bzr` will be created:</p>
<p>    ls -la<br />
    drwxr-xr-x 5 je je 4096 2007-09-01 23:56 .<br />
    drwxr-xr-x 3 je je 4096 2007-09-01 23:37 ..<br />
    drwxr-xr-x 6 je je 4096 2007-09-01 23:37 .bzr<br />
    drwxr-xr-x 2 je je 4096 2007-09-01 23:37 Glade<br />
    -rw-r--r-- 1 je je 5342 2007-09-01 23:37 Starfire.cs<br />
    -rw-r-xr-x 1 je je 5342 2007-09-01 23:40 Starfire.exe</p>
<p>Now tell Bazaar which files your branch consist of. In the following example we'll tell Bazaar to ignore `Starfire.exe` because we dont need the compiled file within our version control:</p>
<p>    bzr ignore Starfire.exe<br />
    bzr add .<br />
    added Glade<br />
    added Starfire.cs<br />
    added Glade&#47;gui.glade<br />
    ignored 1 file(s).<br />
    If you wish to add some of these files, please add them by name.</p>
<p>Ignored files is found in the file `.bzrignore`, just try run `ls -la` if you wont take my word for it :P</p>
<p>And finally to actual save the code in the branch repository commit your files:</p>
<p>    bzr commit -m "Initial revision"<br />
    added .bzrignore<br />
    added Glade<br />
    added Starfire.cs<br />
    added Glade&#47;gui.glade<br />
    Committed revision 1. </p>
<p>By supplying commit with the parameter `-m` you avoid a text editor popping up asking you for a commit message.</p>
<p>Now you can just hack away and you can check changes with:</p>
<p>    bzr diff</p>
<p>Whenever you want to save you changes to the branch, just do a commit again. In the following example I change a line in Starfire.cs:</p>
<p>    bzr commit -m "Fixed small typo"<br />
    modified Starfire.cs<br />
    Committed revision 2. </p>
<p>Now if you want to make your code available on the another machine ie. a server on the internet you can push you code out there through ssh (and ftp). Place yourself in the root directory of your project. The following example will push the branch out to my server (emcken.dk) where I have a ssh key so I dont need to write a password when logging in:</p>
<p>    bzr push sftp:&#47;&#47;emcken.dk&#47;~&#47;development&#47;Starfire</p>
<p>Now a copy of my branch is available in my home directory on my server (`&#47;home&#47;je&#47;development&#47;Starfire`).</p>
<p>As a last thing I would like my server to be central for my development. From the root directory of my project I tell Bazaar that my current branch is a checkout of the branch on my server:</p>
<p>    bzr bind sftp:&#47;&#47;emcken.dk&#47;~&#47;development&#47;Starfire</p>
<p>Now whenever I commit changes they will be committed to the server as well so I don't need to push the copy of the branch out there every time. You can test the settings with:</p>
<p>    bzr info</p>
<p>My experience with version control systems are very limited to say the least which was why some of the above wasn't obvious to me before I made a few tests and read the man pages. But the above was what I needed to get started and I hope that this might help someone else. You can find more inspiration [here][1] and [here][2].</p>
<p>[Mono]: http:&#47;&#47;www.mono-project.org&#47;<br />
[Bazaar]: http:&#47;&#47;bazaar-vcs.org&#47;<br />
[1]: http:&#47;&#47;bazaar-vcs.org&#47;QuickHackingWithBzr<br />
[2]: http:&#47;&#47;russell.rucus.net&#47;blog&#47;Geek&#47;bzr-etc-howto</p>
