---
layout: post
status: publish
published: true
title: Teamspeak start stop script
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
wordpress_id: 208
wordpress_url: http://emcken.dk/wp/archives/208-teamspeak-start-stop-script.html
date: '2008-07-22 16:17:30 +0200'
date_gmt: '2008-07-22 16:17:30 +0200'
categories:
- Random hacks
- Linux
- Games
tags: []
comments: []
---
<p>After my server was restarted twice by my hosting center I decided to make sure [Teamspeak][1] would start automatically.<br />
I read a few posts on how other people did it but in the end I decided that I created my own minimal script.</p>
<p>My two goals with this init script was:</p>
<p>  * Run the Teamspeak server as an unprivileged user.<br />
  * Make sure the Teamspeak server starts up after reboot even though it wasn't shut down properly<br />
  * A little bonus was that it reuses much of the init script that is bundled with the installation</p>
<p>Init script (&#47;etc&#47;init.d&#47;teamspeak):</p>
<p>    #!&#47;bin&#47;sh</p>
<p>    TEAMSPEAK_DIR=&#47;usr&#47;local&#47;teamspeak<br />
    TEAMSPEAK_USER=teamspeak</p>
<p>    # Make sure that Teamspeak starts even though it wasn't closed nicely last time (ie. by a power cut)<br />
    if [ $(su - $TEAMSPEAK_USER -c "ps ux" |grep tsserver2.pid|grep -v grep|wc -l) -eq 0 ] &amp;&amp; [ -f ${TEAMSPEAK_DIR}&#47;tsserver2.pid ]<br />
    then<br />
        rm ${TEAMSPEAK_DIR}&#47;tsserver2.pid<br />
    fi</p>
<p>    cd ${TEAMSPEAK_DIR}<br />
    su - $TEAMSPEAK_USER -c ".&#47;teamspeak2-server_startscript $1"<br />
    cd -</p>
<p>[1]: http:&#47;&#47;www.teamspeak.com&#47;</p>
