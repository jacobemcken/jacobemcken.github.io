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
comments: false
---
After my server was restarted twice by my hosting center I decided to make sure [Teamspeak][1] would start automatically.
I read a few posts on how other people did it but in the end I decided that I created my own minimal script.

My two goals with this init script was:

  * Run the Teamspeak server as an unprivileged user.
  * Make sure the Teamspeak server starts up after reboot even though it wasn't shut down properly
  * A little bonus was that it reuses much of the init script that is bundled with the installation

Init script (/etc/init.d/teamspeak):

    #!/bin/sh

    TEAMSPEAK_DIR=/usr/local/teamspeak
    TEAMSPEAK_USER=teamspeak

    # Make sure that Teamspeak starts even though it wasn't closed nicely last time (ie. by a power cut)
    if [ $(su - $TEAMSPEAK_USER -c "ps ux" |grep tsserver2.pid|grep -v grep|wc -l) -eq 0 ] &amp;&amp; [ -f ${TEAMSPEAK_DIR}/tsserver2.pid ]
    then
        rm ${TEAMSPEAK_DIR}/tsserver2.pid
    fi

    cd ${TEAMSPEAK_DIR}
    su - $TEAMSPEAK_USER -c "./teamspeak2-server_startscript $1"
    cd -

[1]: http://www.teamspeak.com/

