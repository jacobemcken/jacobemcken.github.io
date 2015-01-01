---
layout: post
status: publish
published: true
title: Sid Meier's Pirates with WINE
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
wordpress_id: 131
wordpress_url: http://emcken.dk/wp/archives/131-sid-meiers-pirates-with-wine.html
date: '2005-08-17 20:15:10 +0200'
date_gmt: '2005-08-17 20:15:10 +0200'
categories:
- Linux
- Ubuntu
- Games
tags: []
comments:
- id: 28
  author: Juan
  author_email: opyate@gmail.com
  author_url: http://www.opyate.com
  date: '2005-11-27 17:41:19 +0100'
  date_gmt: '2005-11-27 16:41:19 +0100'
  content: "whoohoo, another Netbeans user :)\r\nCongrats on getting Pirates working
    in ubuntu!!"
- id: 75
  author: almalaci
  author_email: almalaci@fastmail.to
  author_url: ''
  date: '2006-11-16 05:22:10 +0100'
  date_gmt: '2006-11-16 04:22:10 +0100'
  content: "I have failed to run this great game in ubuntu edgy eft. Could you  provide
    me with some details about how you managed? I only get to the 'splash screen'
    screen before the title menu when the game hangs..\r\nThanks,\r\n\r\nLaszlo Almasi"
- id: 109
  author: tim
  author_email: westsidemassive9@hotmail.com
  author_url: ''
  date: '2006-05-05 04:00:29 +0200'
  date_gmt: '2006-05-05 03:00:29 +0200'
  content: it's seem that you had some problems well i got a problem as well. I lost
    my cd key of the game and i can't find another one can i mayby give it a try with
    your cd key you could help me a lot thx The W
- id: 137
  author: braden nixon
  author_email: nixon_braden@hotmail.com
  author_url: ''
  date: '2007-06-16 01:58:04 +0200'
  date_gmt: '2007-06-16 00:58:04 +0200'
  content: u are my only hope
- id: 142
  author: Jacob Emcken
  author_email: jacob@emcken.dk
  author_url: http://www.emcken.dk/weblog/
  date: '2007-07-14 19:41:48 +0200'
  date_gmt: '2007-07-14 18:41:48 +0200'
  content: "Guys.... I havn't tried running the game myself for a long time and didn't
    get any closer than I was back then. But for you who keep trying I think [this
    link to Wines  application database][1] is worth following. Good luck :D\r\n\r\n[1]:
    http://appdb.winehq.org/appview.php?iVersionId=2719"
- id: 144
  author: Jacob
  author_email: lakenono@cox.net
  author_url: ''
  date: '2007-07-19 03:59:21 +0200'
  date_gmt: '2007-07-19 02:59:21 +0200'
  content: "Tim.... Use this:\r\nhttp://www.serialz.to/Sid+meiers+pirates!.htm\r\n\r\nI
    believe that the consumer doesnt have to buy another \r\n$50 game just to get
    the serial code when he already bought one and lost the cd key. Asking customer
    support at ataris website replies that you should buy another version because
    they dont supply ANY CD KEYS TO CUSTOMERS.\r\nThis gets them more money by buying
    another version.\r\nI hate companys like this, They are all the same."
- id: 171
  author: Khoi Nguyen
  author_email: khoi.nqq@gmail.com
  author_url: ''
  date: '2008-06-02 11:24:11 +0200'
  date_gmt: '2008-06-02 10:24:11 +0200'
  content: "Interesting, I also try to make another game to work with WINE under Ubuntu,
    the game (HyOnline) installed, can run, but the mouse cursor not appear in-game.\r\n\r\nDId
    you got any problem like that? If yes, please tell me how to solve it\r\n\r\nThanks"
- id: 322
  author: Nice Dude
  author_email: captian_flako_5@hotmail.com
  author_url: ''
  date: '2009-06-11 19:29:52 +0200'
  date_gmt: '2009-06-11 18:29:52 +0200'
  content: |
    Dang, I have been searching the internet high and low for someone who talks about ubuntu in plain english instead of tech forums.


    Also, I would like to add a comment before brandon nixon's comment


    "help me obi wan kenobi"

---
A few days ago i tried to make [Sid Meiers Pirates][2] run again with [WINE][3].

As usual when trying to install the game I got the following:

<a href="/weblog/uploads/Pirates/Piratesinstallerror.png"><img width='110' height='70' src="/weblog/uploads/Pirates/Piratesinstallerror.thumb.png" alt="" /></a>

Well that didn't stop me this time. I started to copy Pirates from my Windows XP partition to my fake WINE Windows drive (notice the cool new GNOME 2.12 copy dialog.

<img width='410' height='226' style="border: 0px;padding-left: 5px;padding-right: 5px" src="/weblog/uploads/Pirates/Screenshot-Copyingfiles.png" alt="" />

Now I tried to start Pirates again but this time I got a message about missing 2 dll files:

*   msvcp71.dll
*   msvcr71.dll

You can find them by searching on [Google][1]

I copied the files to my fake Windows' system folder ie. `/home/je/c/windows/system`.

Finally I applied a No-CD-patch and was now able to start the game, where I was greeted by a... black screen for a long time.

I'm serious.... a VERY long time.

I'm telling you - it's still there ;)

Then finally I was presented with the title menu. YEAH!

<a href='/weblog/uploads/Pirates/Piratesrunning.png'><img width='110' height='83' style="border: 0px;padding-left: 5px;padding-right: 5px" src="/weblog/uploads/Pirates/Piratesrunning.thumb.png" alt="" /></a>

I think I fiddled a little with some settings before I dared try starting the game.
Now a long waiting time again. But you don't wanna go fetch coffe because you want to see this ;)

Woah, I see an ingame movie... cool. I was so quickly taking the screenshot, that the menu didn't get time to disappear. They call my Lucky Luke ;-)

<a href='/weblog/uploads/Pirates/Gotpastthetitlemenu-w00t.png'><img width='110' height='83' style="border: 0px;padding-left: 5px;padding-right: 5px" src="/weblog/uploads/Pirates/Gotpastthetitlemenu-w00t.thumb.png" alt="" /></a>

I'm actaully able to choose with whom I want to got to the new world. The grey man was a little to creepy so England it was.

<a href='/weblog/uploads/Pirates/Selectingcountry.png'><img width='110' height='83' style="border: 0px;padding-left: 5px;padding-right: 5px" src="/weblog/uploads/Pirates/Selectingcountry.thumb.png" alt="" /></a>

Long wait time.... again, and no movie of me fighting the captain - perhaps I accidentally hit a key, dunno.
Well suddenly I saw a little ship sailing and joy filled my body - YES!... and before I knew it the game had crashed. :-( Perhaps tweaking with the video setting and stuff might make the game somewhat playable :-)

The few screenshots I created was worth it all. See you on the other side.

[1]: http://www.google.com/
[2]: http://www.2kgames.com/pirates/pirates/home.php
[3]: http://www.winehq.org/

