---
layout: post
status: publish
published: true
title: My Gnome 3 usability issues
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
wordpress_id: 401
wordpress_url: http://www.emcken.dk/weblog/?p=401
date: '2012-09-29 16:59:14 +0200'
date_gmt: '2012-09-29 15:59:14 +0200'
categories:
- Linux
- Ubuntu
tags:
- gnome-shell
- gnome3
- gnome
comments: false
---
I've just installed Gnome 3.6 (some packages might still be 3.5.9x) on my laptop by upgrading to Ubuntu 12.10 beta2 together with the Gnome 3 PPA for a few missing things like the updated Nautilus.

I'm a dedicated Gnome user and generally love the alternative focus Gnome 3.x has put on the desktop.
But as with all software, no matter how mature (no - Gnome 2.x wasn't perfect either) there is room for improvement. The following is some of my usability issues, I've come across during my daily use of Gnome since 3.0. Some of this might be due to Ubuntu breaking the intended Gnome 3 behavior.


### State toggle

It's possible to toggle both the overview (`Super`) and snap windows to the sides of the screen (`Super + <left/right>`).

I expected that it would be possible to toggle the new message tray as well with a subsequent `Super + M`. Instead `Escape` is needed to close the message tray. Having the `Escape` shortcut is fine, but I would like `Super + M` to work as well - just like with the overview.
Also it seems that the Maximize toggle has been removed. `Super + <up>` still works for maximizing but for restoring the window you now need to press `Super + <down>`. For ease of use and consistency I would have preferred that a subsequent `Super + <up>` would restore the window (just like with the side-snapping).

When maximizing windows by dragging them to the top, I would like it to be more clear that the area next to the application menu is a drag handle. I'm not sure exactly how to accomplish this but here are my immediate thoughts on the subject:

  * A transition effect when dropping the window where the title bar slides up behind the top bar.
  * Something visual that connects the title bar and the drag handle on top bar to make it clear that the top bar now serves as the title bar for the maximized window.
  * Have a mouse-drag-pointer fade out / zoom out over the drag handle on the top bar upon drop.


### Overview

From time to time I find myself wishing for arrow navigation between the application windows on the overview and to be able to select the window I want to have focus on my workspace with `Enter`.

Also if that worked it would be nice to also be able to move an application window to a new workspace using the `Ctrl + Alt + Shift + <up/down>` - just like outside the overview.

When a window is minimized I would like it to differ slightly visually from non-minimized windows - perhaps by being slightly transparent?... I dont know. When we look for a specific application I use everything we know about the application window to find it quickly: Application UI, window size, open content and if I could... the fact that it is minimized.

I don't like the automatic open of the of the overview when the last application is closed. Usually I have my windows maximized and can't always remember whether I have one or several applications on a workspace. I often find my self closing an application with `Alt+F4` and pressing `Super` right after to open a new one. If the application was the last one of the workspace I close the automatic opened overview instead of opening it. This is slightly annoying.


### File dialog

I expected that keyboard navigation for the file dialog would be like the navigation in Nautilus aka. Files. Ie. `Alt + <arrows>` for navigation.
All that said the Gnome experience is awesome and I already can't wait for all the cool stuff in 3.8 and ahead :)
