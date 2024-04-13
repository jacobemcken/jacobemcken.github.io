---
layout: post
status: publish
published: true
title: Screen real estate
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
wordpress_id: 314
wordpress_url: http://www.emcken.dk/weblog/?p=314
date: '2010-03-30 15:19:20 +0200'
date_gmt: '2010-03-30 14:19:20 +0200'
categories:
- Uncategorized
tags: []
comments:
- id: 452
  author: Lau Jensen
  author_email: lau.jensen@bestinclass.dk
  author_url: http://www.bestinclass.dk
  date: '2010-09-26 22:09:11 +0200'
  date_gmt: '2010-09-26 21:09:11 +0200'
  content: |
    Hey buddy,


    If you want more screen real estate, try the browser Conkeror (not Konqeror) which is based on the Emacs keybindings and has virtually zero UI bloat.


    Best,
    Lau

---
{% include image-caption.html src="/public/media/yes-300x187.png" link="/public/media/yes.png" description="Gnome global menu and Gnome Do in action" %}

My last laptop was the IBM Thinkpad x40 with a 12" screen and a resolution of 1024x768. I used it both private and for work for over five years several hours a day and wore down three batteries in that time. With such a little screen you find yourself exploring ways to get the most out of your screen. Since I prefer the keyboard over the mouse any day I'm not forced to have big icons and menus all over the place.

Here are the steps I've taken (things I've removed) and how get things done (without them).

## Firefox

Press "View / Toolbars / Customize..." and remove anything except from the back, forward, location bar and search bar. Also select "Use small icons". To stop loading a page just hit escape, to reload use F5 and home use Alt+Home. To get to the location bar hit Ctrl+L and to get to the search bar hit Ctrl+K. I also hid the Bookmarks toolbar (because the suggestions in the address bar usually gives me what I want... alternative you can give bookmarks tags (keywords) which you can type in the address bar to open the bookmark.

## Bottom panel

I've removed the bottom panel on my Ubuntu installation since it had nothing I'd ever use. Hide all open windows or "Show desktop" is easily accessible using Ctrl+Alt+D. I never used the Trash icon i really rarely delete anything and when I do I usually hold shift while doing so (which skips the trash can) which leaves me with the "Window list". The window list show the open windows on all workspaces and although I use tabs whenever I can get away with it (Gedit, firefox, terminal and sometimes Nautilus) I still usually have about eight open windows spread over my six workspaces. I've come into the habit of using the same workspaces for the same tasks (often one application). Example:

* Workspace 1: Email
* Workspace 2: Browsing
* Workspace 3: Terminal
* Workspace 4: Virtual machines
* Workspace 5: Documents using OpenOffice or PDF using Evince
* Workspace 6: Editor (mostly emacs but somtimes gedit).

This way I alwas know where the application I want for the task at hand is. I use the keyboard shortcuts (Ctrl+Alt+arrow keys) to get to it. I always know where to look for an application. Having all applications on the same workspace and use Alt+Tab or the window list I would have to use my eyes to locate and identify the application I want. So I find this method much faster. Just to be on the safe side I put an other applet called "Window Selector" in my panel in case I ever found myself in need of being able to select a window with the mouse. I sometimes use to show me a list of all the applications I've opened.

## Gnome do

I can't remember excatly how why or when I started using Gnome-Do but it is one of those things you didn't know you needed until you started using it. It is one of the most powerful and versatile tools I've ever used on my desktop. Although its not there yet it certainly could be for the desktop whan a terminal is for a server. At the moment I've mostly use it to start applications, play, pause and skip music and start, stop, suspend and snapshot virtual machines in Virtualbox. Though I'm pretty sure the use of Gnome-Do will keep growing on me.

## Global panel

After I found my self not using the application menu to start stuff anymore I decided to remove it from my top panel to get the extra space and test gnome-global-menu. I belive the gnome-global-menu project was inspired by Mac but whether Apple came up with the idea themselves or they got inspired somewhere else I don't know. I the top of this post you can see how my desktop looks at the moment (just installed Ubuntu Lucid).

