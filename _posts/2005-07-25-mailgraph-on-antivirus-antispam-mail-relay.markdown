---
layout: post
status: publish
published: true
title: Mailgraph on antivirus &#47; antispam mail relay
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
excerpt: "The last couple of days I have tinkered with a new antivirus &#47; antispam
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
  wanted.\r\nRead on to see what (small) changes I made.\r\n\r\n\r\n[1]: http:&#47;&#47;www.debian.org&#47;\r\n[2]:
  http:&#47;&#47;www.postfix.org&#47;\r\n[3]: http:&#47;&#47;spamassassin.org&#47;\r\n[4]:
  http:&#47;&#47;www.clamav.net&#47;\r\n[5]: http:&#47;&#47;www.amavis.org&#47;\r\n[6]:
  http:&#47;&#47;people.ee.ethz.ch&#47;~dws&#47;software&#47;mailgraph&#47;\r\n[7]:
  http:&#47;&#47;www.webmail.us&#47;testvirus\n"
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
<p>The last couple of days I have tinkered with a new antivirus &#47; antispam server at work. Its foundation is a [Debian][1] Sarge running [Postfix][2],  [Spamassassin][3], [ClamAV][4] through [Amavis-ng][5] (Amavis is installed from current unstable) and of top if it all [mailgraph][6].</p>
<p>All packages was taken from the stable Debian release - Sarge, except of amavis-ng which does not exist in Sarge. This package was instead downloaded from unstable... fortunately it had no dependencies from unstable what so ever.</p>
<p>The documentation on the Spamassassin homepage is great which is just the opposite for amavis-ng which seems non-exsisting. The configuration file shipped with Debian makes up for the lack of documentation. It seems that amavis-ng should be a (more modular) reimplementation of amavisd-new. Even though people on the mailinglists recommend amavisd-new :-D</p>
<p>I have a serious problem keeping my hands off the bleeding edge stuff so I couldn't resist installing `amavis-ng`. I have tried using it before, but at that time I couldn't make it fork (it became a serious bottleneck). I'm not saying that it didn't work, it might as well hav been me. Though I cannot seem to find the difference from my previous installation and my new one. Anyway it seems to fork correctly in this new installation and to test the virus filter I recommend this [web site][7]</p>
<p>The reason why I write this entry is because I made some changes to mailgrap to make it work the way I wanted.<br />
Read on to see what (small) changes I made.</p>
<p>[1]: http:&#47;&#47;www.debian.org&#47;<br />
[2]: http:&#47;&#47;www.postfix.org&#47;<br />
[3]: http:&#47;&#47;spamassassin.org&#47;<br />
[4]: http:&#47;&#47;www.clamav.net&#47;<br />
[5]: http:&#47;&#47;www.amavis.org&#47;<br />
[6]: http:&#47;&#47;people.ee.ethz.ch&#47;~dws&#47;software&#47;mailgraph&#47;<br />
[7]: http:&#47;&#47;www.webmail.us&#47;testvirus<br />
<a id="more"></a><a id="more-122"></a><br />
*   First I changed the startup script to be able to use 2 log files (one for emails and one for virus).<br />
     Code for `&#47;etc&#47;init.d&#47;mailgraph`:</p>
<p>        #!&#47;bin&#47;sh</p>
<p>        MAILGRAPH_CONFIG="&#47;etc&#47;default&#47;mailgraph"<br />
        NAME="mailgraph"<br />
        DAEMON="&#47;usr&#47;sbin&#47;mailgraph.pl"<br />
        PID_FILE="&#47;var&#47;run&#47;mailgraph.pid"<br />
        PID_VIRUS_FILE="&#47;var&#47;run&#47;mailgraph_virus.pid"<br />
        RRD_DIR="&#47;var&#47;lib&#47;mailgraph"<br />
        IGNORE_OPTION=""</p>
<p>        if [ -f $MAILGRAPH_CONFIG ]; then<br />
          . $MAILGRAPH_CONFIG<br />
        else<br />
          exit 0<br />
        fi</p>
<p>        test -x &#47;usr&#47;sbin&#47;mailgraph.pl || exit 0</p>
<p>        if [ "$IGNORE_LOCALHOST" = "true" ]; then<br />
          IGNORE_OPTION="--ignore-localhost"<br />
        fi</p>
<p>        case "$1" in<br />
          start)<br />
            echo -n "Starting Postfix Mail Statistics: $NAME"<br />
            if [ -f $VIRUS_LOG ]; then<br />
              start-stop-daemon -S -q -b -p $PID_FILE -x $DAEMON -- --only-mail-rrd -l $MAIL_LOG -d --daemon_rrd=$RRD_DIR $IGNORE_OPTION<br />
              start-stop-daemon -S -q -b -p $PID_VIRUS_FILE -x $DAEMON -- --daemon-pid=$PID_VIRUS_FILE --only-virus-rrd -l $VIRUS_LOG -d --daemon_rrd=$RRD_DIR $IGNORE_OPTION<br />
            else<br />
              start-stop-daemon -S -q -b -p $PID_FILE -x $DAEMON -- -l $MAIL_LOG -d --daemon_rrd=$RRD_DIR $IGNORE_OPTION<br />
            fi<br />
            echo "."<br />
            ;;<br />
          stop)<br />
            echo -n "Stopping Postfix Mail Statistics: $NAME"<br />
            if [ -f $PID_FILE ]; then<br />
              kill `cat $PID_FILE`<br />
              rm $PID_FILE<br />
            fi<br />
            if [ -f $PID_VIRUS_FILE ]; then<br />
              kill `cat $PID_VIRUS_FILE`<br />
              rm $PID_VIRUS_FILE<br />
            fi<br />
            echo "."<br />
            ;;<br />
          restart)<br />
            $0 stop<br />
            $0 start<br />
            ;;<br />
          force-reload)<br />
            $0 restart<br />
            ;;<br />
          *)<br />
            echo "Usage: $0 start|stop|restart|force-reload"<br />
            exit 1<br />
            ;;<br />
        esac</p>
<p>    Remember to define the virus log file in `&#47;etc&#47;default&#47;mailgraph` ;-)</p>
<p>*   Second I modified the `mailgraph.pl` code because I didn't recognize the output from Amavis (ClamAV):</p>
<p>    > Jul 25 20:04:59 gargoyle amavis[18319]: CLAMD found:<br />
    > Jul 25 20:04:59 gargoyle amavis[18319]:  Eicar-Test-Signature<br />
    > Jul 25 20:04:59 gargoyle amavis[18319]: AMAVIS::MTA::SMTP: Dropping message (Message-ID: )</p>
<p>    It might be because I use amavis-ng, I dunno. But I found a line to put into `&#47;usr&#47;sbin&#47;mailgraph.pl`. Around line 596 within the amavis case put this:</p>
<p>        ...<br />
        elsif($tqext =~ &#47;^\CLAMD found\b&#47;) {<br />
            event($time, 'virus');<br />
        }<br />
        ...</p>
