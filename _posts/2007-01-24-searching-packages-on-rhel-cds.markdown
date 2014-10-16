---
layout: post
status: publish
published: true
title: Searching packages on RHEL CD's
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
wordpress_id: 178
wordpress_url: http://emcken.dk/wp/archives/178-searching-packages-on-rhel-cds.html
date: '2007-01-24 16:29:46 +0100'
date_gmt: '2007-01-24 16:29:46 +0100'
categories:
- Random hacks
- Work
- Linux
tags: []
comments: []
---
<p>Sometimes you have to get primitive ... duh.</p>
<p>Today I got really annoyed about the "Package Management" tool on Red Hat EL 4 update 4. When I tried to install the "Development tools" I just got an error that krb5-libs could not be found which was a dependencie of krb5-workstation (1.3.4, 33). Both krb5-libs and krb5-workstation was installed...?!? I'm not Red Hat expert... and that is probably my biggest problem here :)</p>
<p>Back to the commandline... it always works.<br />
I had to search the CD'es (afterwards I found that all the packages I needed was on CD3). I made a little search script... dont think anyone can use it... just thought it was fun:</p>
<p>    for i in 1 2 3 4 5<br />
    do<br />
        mount -o loop &#47;root&#47;RHEL4-U4-i386-ES-disc$i.iso  &#47;mnt&#47;<br />
        echo "Results on cd $i"<br />
        find &#47;mnt&#47;RedHat&#47;RPMS&#47; -iname $1\*<br />
        umount &#47;mnt&#47;<br />
    done</p>
