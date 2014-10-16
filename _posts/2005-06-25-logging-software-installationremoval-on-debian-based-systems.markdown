---
layout: post
status: publish
published: true
title: Logging software installation&#47;removal on Debian based systems
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
wordpress_id: 117
wordpress_url: http://emcken.dk/wp/archives/117-logging-software-installationremoval-on-debian-based-systems.html
date: '2005-06-25 09:55:19 +0200'
date_gmt: '2005-06-25 09:55:19 +0200'
categories:
- Debian
- Ubuntu
tags: []
comments: []
---
<p>At work we have several servers running Debian anb because we are multiple persones maintaining them we end up having software (packages) installed that no one remember the reason for. I thought I would be of great if apt&#47;dpkg had some build-in preference you could enable so people had to ad a comment why they install the software which they are about to, and the comment would be saved along with a list of the packages installed.<br />
Perhapes if using `sudo` you could also log who is installing what. You cannot trust that people always will leave a name for a number of reasons. I think the two greatest reasons would be:</p>
<p>*   pure laziness<br />
*   hiding that they are about to fuck something up</p>
<p>I have to google around some more, to see if something like this have actually been implemented.</p>
<p>A colleague pointed out that 'rpm' have the ability to roll back you system to a specific day... though havn't tried it so sadly I don't know how well it works. But that would be a great thing to have in 'dpkg' also.</p>
