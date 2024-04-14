---
layout: post
status: publish
published: true
title: My first patch to FriFinans in the longest time
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
wordpress_id: 116
wordpress_url: http://emcken.dk/wp/archives/116-my-first-patch-to-frifinans-in-the-longest-time.html
date: '2005-06-23 10:40:46 +0200'
date_gmt: '2005-06-23 10:40:46 +0200'
categories:
- FriFinans
tags: []
comments: false
---
After alot of tutoring by my great colleague [Frank Bille][] I have wrote a small, yet good [patch][] for [FriFinans][]. It saves you from a key stroke every time you enter data a field in the lists.

Before when you wrote a date in a field you and pressed Enter the date would be validated and the field state would change from editable to just showing you the inserted value (if ofcourse the value was valid). Then you had to press Enter again for the focus to move to the next field.

Now when you hit Enter and if the value in the edited field is valid, the focus will automatically shift to the next field. This new funcionality isn't ofcourse limited to the date field but to all fields in tables.

[Frank Bille]: http://frank-bille.dk/
[patch]: http://cvs.frifinans.dk/frifinans/src/org/frifinans/client/util/AdvancedCellEditor.java?r1=text&amp;tr1=1.6&amp;r2=text&amp;tr2=1.5&amp;diff_format=h
[FriFinans]: http://www.frifinans.dk/

