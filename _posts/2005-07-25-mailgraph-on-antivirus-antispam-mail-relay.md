---
layout: post
status: publish
published: true
title: Mailgraph on antivirus / antispam mail relay
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
excerpt: "The last couple of days I have tinkered with a new antivirus / antispam
  server at work. Its foundation is a [Debian][1] Sarge running [Postfix][2],  [Spamassassin][3],
  [ClamAV][4] through [Amavis-ng][5] (Amavis is installed from current unstable) and
  of top if it all [mailgraph][6].\r\n\r\nAll packages was taken from the stable Debian
  release - Sarge, except of amavis-ng which does not exist in Sarge. This package
  was instead downloaded from unstable... fortunately it had no dependencies from
  unstable what so ever.\r\n\r\nThe documentation on the Spamassassin homepage is
  great which is just the opposite for amavis-ng which seems non-exsisting. The configuration
  file shipped with Debian makes up for the lack of documentation. It seems that amavis-ng
  should be a (more modular) reimplementation of amavisd-new. Even though people on
  the mailinglists recommend amavisd-new :-D\r\n\r\nI have a serious problem keeping
  my hands off the bleeding edge stuff so I couldn't resist installing `amavis-ng`.
  I have tried using it before, but at that time I couldn't make it fork (it became
  a serious bottleneck). I'm not saying that it didn't work, it might as well hav
  been me. Though I cannot seem to find the difference from my previous installation
  and my new one. Anyway it seems to fork correctly in this new installation and to
  test the virus filter I recommend this [web site][7]\r\n\r\nThe reason why I write
  this entry is because I made some changes to mailgrap to make it work the way I
  wanted.\r\nRead on to see what (small) changes I made.\r\n\r\n\r\n[1]: http://www.debian.org/\r\n[2]:
  http://www.postfix.org/\r\n[3]: http://spamassassin.org/\r\n[4]:
  http://www.clamav.net/\r\n[5]: http://www.amavis.org/\r\n[6]:
  http://people.ee.ethz.ch/~dws/software/mailgraph/\r\n[7]:
  http://www.webmail.us/testvirus\n"
wordpress_id: 122
wordpress_url: http://emcken.dk/wp/archives/122-mailgraph-on-antivirus-antispam-mail-relay.html
date: '2005-07-25 23:25:00 +0200'
date_gmt: '2005-07-25 23:25:00 +0200'
categories:
- Debian
- Work
tags: []
comments:
- id: 84
  author: Trix
  author_email: ''
  author_url: http://trixtah.livejournal.com
  date: '2006-12-22 08:13:18 +0100'
  date_gmt: '2006-12-22 07:13:18 +0100'
  content: Jacob, thank you very much for explaining this process in words of one
    syllable. I'm going to give it a go and see if I can get it working in a similar
    way with Trend Micro IMSS (which appears to have a lot of uncanny resemblances
    with amavisd-new).
---
The last couple of days I have tinkered with a new antivirus / antispam server at work. Its foundation is a [Debian][1] Sarge running [Postfix][2],  [Spamassassin][3], [ClamAV][4] through [Amavis-ng][5] (Amavis is installed from current unstable) and of top if it all [mailgraph][6].

All packages was taken from the stable Debian release - Sarge, except of amavis-ng which does not exist in Sarge. This package was instead downloaded from unstable... fortunately it had no dependencies from unstable what so ever.

The documentation on the Spamassassin homepage is great which is just the opposite for amavis-ng which seems non-exsisting. The configuration file shipped with Debian makes up for the lack of documentation. It seems that amavis-ng should be a (more modular) reimplementation of amavisd-new. Even though people on the mailinglists recommend amavisd-new :-D

I have a serious problem keeping my hands off the bleeding edge stuff so I couldn't resist installing `amavis-ng`. I have tried using it before, but at that time I couldn't make it fork (it became a serious bottleneck). I'm not saying that it didn't work, it might as well hav been me. Though I cannot seem to find the difference from my previous installation and my new one. Anyway it seems to fork correctly in this new installation and to test the virus filter I recommend this [web site][7]

The reason why I write this entry is because I made some changes to mailgrap to make it work the way I wanted.
Read on to see what (small) changes I made.

[1]: http://www.debian.org/
[2]: http://www.postfix.org/
[3]: http://spamassassin.org/
[4]: http://www.clamav.net/
[5]: http://www.amavis.org/
[6]: http://people.ee.ethz.ch/~dws/software/mailgraph/
[7]: http://www.webmail.us/testvirus
<a id="more"></a><a id="more-122"></a>
*   First I changed the startup script to be able to use 2 log files (one for emails and one for virus).
     Code for `/etc/init.d/mailgraph`:

        #!/bin/sh

        MAILGRAPH_CONFIG="/etc/default/mailgraph"
        NAME="mailgraph"
        DAEMON="/usr/sbin/mailgraph.pl"
        PID_FILE="/var/run/mailgraph.pid"
        PID_VIRUS_FILE="/var/run/mailgraph_virus.pid"
        RRD_DIR="/var/lib/mailgraph"
        IGNORE_OPTION=""

        if [ -f $MAILGRAPH_CONFIG ]; then
          . $MAILGRAPH_CONFIG
        else
          exit 0
        fi

        test -x /usr/sbin/mailgraph.pl || exit 0

        if [ "$IGNORE_LOCALHOST" = "true" ]; then
          IGNORE_OPTION="--ignore-localhost"
        fi

        case "$1" in
          start)
            echo -n "Starting Postfix Mail Statistics: $NAME"
            if [ -f $VIRUS_LOG ]; then
              start-stop-daemon -S -q -b -p $PID_FILE -x $DAEMON -- --only-mail-rrd -l $MAIL_LOG -d --daemon_rrd=$RRD_DIR $IGNORE_OPTION
              start-stop-daemon -S -q -b -p $PID_VIRUS_FILE -x $DAEMON -- --daemon-pid=$PID_VIRUS_FILE --only-virus-rrd -l $VIRUS_LOG -d --daemon_rrd=$RRD_DIR $IGNORE_OPTION
            else
              start-stop-daemon -S -q -b -p $PID_FILE -x $DAEMON -- -l $MAIL_LOG -d --daemon_rrd=$RRD_DIR $IGNORE_OPTION
            fi
            echo "."
            ;;
          stop)
            echo -n "Stopping Postfix Mail Statistics: $NAME"
            if [ -f $PID_FILE ]; then
              kill `cat $PID_FILE`
              rm $PID_FILE
            fi
            if [ -f $PID_VIRUS_FILE ]; then
              kill `cat $PID_VIRUS_FILE`
              rm $PID_VIRUS_FILE
            fi
            echo "."
            ;;
          restart)
            $0 stop
            $0 start
            ;;
          force-reload)
            $0 restart
            ;;
          *)
            echo "Usage: $0 start|stop|restart|force-reload"
            exit 1
            ;;
        esac

    Remember to define the virus log file in `/etc/default/mailgraph` ;-)

*   Second I modified the `mailgraph.pl` code because I didn't recognize the output from Amavis (ClamAV):

    > Jul 25 20:04:59 gargoyle amavis[18319]: CLAMD found:
    > Jul 25 20:04:59 gargoyle amavis[18319]:  Eicar-Test-Signature
    > Jul 25 20:04:59 gargoyle amavis[18319]: AMAVIS::MTA::SMTP: Dropping message (Message-ID: )

    It might be because I use amavis-ng, I dunno. But I found a line to put into `/usr/sbin/mailgraph.pl`. Around line 596 within the amavis case put this:

        ...
        elsif($tqext =~ /^\CLAMD found\b/) {
            event($time, 'virus');
        }
        ...

