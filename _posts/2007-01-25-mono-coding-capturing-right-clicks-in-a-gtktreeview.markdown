---
layout: post
status: publish
published: true
title: 'Mono coding: Capturing right clicks in a Gtk.TreeView'
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
wordpress_id: 179
wordpress_url: http://emcken.dk/wp/archives/179-mono-coding-capturing-right-clicks-in-a-gtktreeview.html
date: '2007-01-25 19:26:06 +0100'
date_gmt: '2007-01-25 19:26:06 +0100'
categories:
- Random hacks
tags: []
comments:
- id: 460
  author: Jared
  author_email: jaredljennings@gmail.com
  author_url: http://jaredjennings.org
  date: '2010-11-02 22:28:05 +0100'
  date_gmt: '2010-11-02 21:28:05 +0100'
  content: "<p>Thanks for the example, I was able to use it and with some additional
    code get the selected row.<&#47;p>\n\n<pre><code>    &#47;&#47; The TreeView has
    a build in function which is called upon a OnButtonPressEvent\n    &#47;&#47;
    We override the function to capture right clicks with the mouse\n    protected
    override bool OnButtonPressEvent (Gdk.EventButton evnt)\n    {\n\n            if(evnt.Button
    == 3) {\n                    TreeModel model;\n                    TreeIter iter;\n
    \                   if (this.Selection.GetSelected (out model, out iter)) {\n
    \                       Console.WriteLine (\"Selected row during right click\"
    + model.GetValue (iter, 0).ToString ());\n                    }               \n
    \                   return true;\n            }\n            return base.OnButtonPressEvent(evnt);\n
    \   }\n<&#47;code><&#47;pre>\n"
- id: 568
  author: Cutler
  author_email: qintadev@seznam.cz
  author_url: ''
  date: '2012-03-09 21:08:12 +0100'
  date_gmt: '2012-03-09 20:08:12 +0100'
  content: |
    <p>Hey, nice, but is it possible to determine which column was pressed? Not just row? Respectively which node exactly was pressed?<&#47;p>
- id: 633
  author: Jacob Emcken
  author_email: jacob@emcken.dk
  author_url: http://www.emcken.dk/
  date: '2012-05-27 21:11:48 +0200'
  date_gmt: '2012-05-27 20:11:48 +0200'
  content: |
    <p>I'm sorry its a long time since I did any Mono coding - I'm not able to help sorry.<&#47;p>
---
<p>I had a hard time figuring out how to capture a right click on a TreeView, which I needed to be able to make a context menu or popup menu or what ever you wanna call it.<br />
The way I expected it would work didn't... I think it have something to do with [a change in Mono some time ago][1] (Why don't I get ButtonPressEvents from my Button&#47;Treeview?).</p>
<p>[1]: http:&#47;&#47;gtk-sharp.sourceforge.net&#47;faq.html#3.3</p>
<p>This example is based on the "[Shortcuts - Writing Less Code][2]" example from the www.mono-project.com website.<br />
The main difference is that the TreeView is no longer setup in the main class but is now a separate class with the function `OnButtonPressEvent` overwritten.</p>
<p>[2]: http:&#47;&#47;www.mono-project.com&#47;GtkSharp_TreeView_Tutorial#Shortcuts_-_Writing_Less_Code</p>
<p>    public class TreeViewExample {<br />
    	public static void Main ()<br />
    	{<br />
    		Gtk.Application.Init ();<br />
    		new TreeViewExample ();<br />
    		Gtk.Application.Run ();<br />
    	}</p>
<p>    	public TreeViewExample ()<br />
    	{<br />
    		Gtk.Window window = new Gtk.Window ("TreeView Example");<br />
    		window.SetSizeRequest (500,200);</p>
<p>    		MusicTreeView tree = new MusicTreeView ();<br />
    		window.Add (tree);<br />
    		window.ShowAll ();<br />
    	}<br />
    }</p>
<p>    &#47;&#47; Creating a new class MusicTreeView which is derived from the TreeView class<br />
    public class MusicTreeView : Gtk.TreeView {</p>
<p>            public MusicTreeView ()<br />
            {<br />
            		Gtk.ListStore musicListStore = new Gtk.ListStore (typeof (string), typeof (string));</p>
<p>            		this.AppendColumn ("Artist", new Gtk.CellRendererText (), "text", 0);<br />
            		this.AppendColumn ("Title", new Gtk.CellRendererText (), "text", 1);</p>
<p>            		musicListStore.AppendValues ("Garbage", "Dog New Tricks");<br />
            		this.Model = musicListStore;<br />
            }</p>
<p>            &#47;&#47; The TreeView has a build in function which is called upon a OnButtonPressEvent<br />
            &#47;&#47; We override the function to capture right clicks with the mouse<br />
            protected override bool OnButtonPressEvent (Gdk.EventButton evnt)<br />
            {<br />
                    if(evnt.Button == 3) {<br />
                            System.Console.WriteLine ("Right click");<br />
                            return true;<br />
                    }<br />
                    &#47;&#47; Now if we would ever get this far<br />
                    &#47;&#47; we run the TreeViews OnButtonPressEvent function<br />
                    &#47;&#47; to make sure everything else works as normal<br />
                    return base.OnButtonPressEvent(evnt);<br />
            }<br />
    }</p>
<p>**Update:** I wrote a [new "right click in Gtk.TreeView" example][1].</p>
<p>[1]: http:&#47;&#47;www.emcken.dk&#47;weblog&#47;archives&#47;185-Gtk.TreeView-right-click-part-2.html</p>
