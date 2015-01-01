---
layout: post
status: publish
published: true
title: Dual screen in Ubuntu
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
wordpress_id: 198
wordpress_url: http://emcken.dk/wp/archives/198-dual-screen-in-ubuntu.html
date: '2007-07-14 12:42:40 +0200'
date_gmt: '2007-07-14 12:42:40 +0200'
categories:
- Ubuntu
- Games
tags: []
comments:
- id: 174
  author: dimi3
  author_email: dimitar.valov@gmail.com
  author_url: ''
  date: '2008-06-24 08:29:57 +0200'
  date_gmt: '2008-06-24 07:29:57 +0200'
  content: "Hi Jacob,\r\n\r\nI have an ATI card, but after your how-to I think I'll
    by a nVidia one - maybe FX 1100 second hand. \r\n\r\nIn my setup I have one monitor
    with resolution 1680x1050 and a second LCD TV with resolution 1360x768. I noticed
    that you have different resolutions on both displays and I have only one question
    - do you have any 'dead zones' on the displays (sections that are not visible
    on the monitor but you can drag something in them). \r\n\r\nRegards :)"
- id: 175
  author: dimi3
  author_email: dimitar.valov@gmail.com
  author_url: ''
  date: '2008-06-24 09:38:17 +0200'
  date_gmt: '2008-06-24 08:38:17 +0200'
  content: "Hi Jacob,\r\n\r\nI wonder do you have 'dead zones' like areas where you
    can drag something but you can't see it on the display with the lower resolution.
    Because I read some articles that had this problem.\r\n\r\nFrom your screenshot
    I didn't understand if your displays are with different resoulution - why does
    the second one (with firefox on it) has the same Vertical Resolution as the first
    one?\r\n\r\nRegards."
- id: 177
  author: Jacob Emcken
  author_email: jacob@emcken.dk
  author_url: http://www.emcken.dk/weblog/
  date: '2008-08-24 11:29:32 +0200'
  date_gmt: '2008-08-24 10:29:32 +0200'
  content: "Hi dimi3.\r\n\r\nI might have had so called \"dead zones\"... but I think
    it was due to the fact that I was using screens with different resolutions. If
    its a bug or by design I don't know... I didn't feel the way it worked was a problem
    for me.\r\n\r\nAbout the screenshot: I did have different resolutions but the
    screenshot application can't make screenshots in non-retangular shapes so it just
    use the background there... on the monitors that part just isn't visible.\r\n\r\nI
    don't have this clear in mind because I haven't bothered with this in Ubuntu for
    a while now after the setup broke after an update. Anyways I'm pretty sure that
    maximizing windows worked as it should (not using dead zone space,which you can
    also see in the screenshot where Firefox is maximized)."
---
Today I got dual screen in Ubuntu working... I have been fiddling around with it a few times before but nothing seriously. Never got it working the way I wanted. Earlier I edited the `xorg.conf` by hand while following guides from the internet and yesterday I stumbled upon a graphical Nvidia X configuration tool by accident... the solution was a bit of both.

The tool is called `nvidia-settings` and looks something like the image below.
<!-- s9ymdb:42 --><img width='758' height='729' style="padding:5px" src="/weblog/uploads/NVIDIAXServerSettings.png" alt="" />

As far as I know there are 2 ways of doing dual screen in Linux. Either you can use Xinerama or the Nvidia built-in feature called TwinView (I might be wrong here :D). Anyways I chose TviewView because that was the default in the Nvidia config tool. After making X aware of my second monitor with the Nvidia tool I saved the X configuration and restarted the X server with the new (Nvidia generated) configuration. The Nvidia generated configuration had 2 problems:

*   It removed my danish keyboard
*   It made my old monitor and the VGA outled the default monitor.
    I want my new monitor on the DVI outled to be the default.

By hand I added the danish keyboard configuration which I copy-pasted from the old `xorg.conf`:

    Section "InputDevice"
        Identifier     "Keyboard0"
        Driver         "kbd"
        Option         "CoreKeyboard"
        Option         "XkbRules"      "xorg"
        Option         "XkbModel"      "pc104"
        Option         "XkbLayout"     "dk"
    EndSection

To force the DVI to be the primary monitor I used the following:

    Section "Device"
        Identifier     "NVIDIA Corporation NV43 [GeForce 6600]"
        Driver         "nvidia"
        VendorName     "NVIDIA Corporation"
        BoardName      "GeForce 6600"
        BusID          "PCI:1:0:0"
        Option         "NoLogo" "1"
        Option         "TwinView" "1"
        Option         "TwinViewXineramaInfoOrder" "DFP, CRT"
        Option         "TwinViewOrientation" "LeftOf"
        Option         "MetaModes" "DFP: 1600x1200, CRT: 1280x1024"
    EndSection

    Section "Screen"
        Identifier     "Screen0"
        Device         "NVIDIA Corporation NV43 [GeForce 6600]"
        Monitor        "Monitor0"
        DefaultDepth    24
        SubSection     "Display"
            Depth       24
            Modes      "1600x1200" "1280x1024" "1024x768" "800x600" "640x480"
        EndSubSection
    EndSection

First I don't want to see the Nvidia Logo when X is started... it is a nice logo though :)
`TwinViewXineramaInfoOrder` is the important part because this makes sure that the DVI is the default monitor. You can read more about all the possible [options for the Nvidia driver][1] on Nvidias homepage.

<a class='serendipity_image_link' href='/weblog/uploads/Dualscreen.png'><!-- s9ymdb:41 --><img width='110' height='46' style="float: left;border: 0px;padding-left: 5px;padding-right: 5px" src="/weblog/uploads/Dualscreen.thumb.png" alt="" /></a> My only "problem" is that the background image is streched out on both monitors, but I guess I have to make a custom background image for my dual screen setup. Now I can play World of Warcraft in a dual screen setup in Linux as well which was one of the only things that kept me booting into Windows. To bad performance drops a bit in Linux :( But I have a strong feeling that we are to blame Nvidia for that rather than Wine... but its just a gut feeling. :D

[1]: http://us.download.nvidia.com/XFree86/Linux-x86/1.0-9755/README/appendix-d.html

