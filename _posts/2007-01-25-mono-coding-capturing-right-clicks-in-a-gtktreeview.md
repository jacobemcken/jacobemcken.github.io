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
  content: "Thanks for the example, I was able to use it and with some additional
    code get the selected row.
\n\n<pre><code>    // The TreeView has
    a build in function which is called upon a OnButtonPressEvent\n    //
    We override the function to capture right clicks with the mouse\n    protected
    override bool OnButtonPressEvent (Gdk.EventButton evnt)\n    {\n\n            if(evnt.Button
    == 3) {\n                    TreeModel model;\n                    TreeIter iter;\n
    \                   if (this.Selection.GetSelected (out model, out iter)) {\n
    \                       Console.WriteLine (\"Selected row during right click\"
    + model.GetValue (iter, 0).ToString ());\n                    }               \n
    \                   return true;\n            }\n            return base.OnButtonPressEvent(evnt);\n
    \   }\n</code></pre>\n"
- id: 568
  author: Cutler
  author_email: qintadev@seznam.cz
  author_url: ''
  date: '2012-03-09 21:08:12 +0100'
  date_gmt: '2012-03-09 20:08:12 +0100'
  content: |
    Hey, nice, but is it possible to determine which column was pressed? Not just row? Respectively which node exactly was pressed?

- id: 633
  author: Jacob Emcken
  author_email: jacob@emcken.dk
  author_url: http://www.emcken.dk/
  date: '2012-05-27 21:11:48 +0200'
  date_gmt: '2012-05-27 20:11:48 +0200'
  content: |
    I'm sorry its a long time since I did any Mono coding - I'm not able to help sorry.

---
I had a hard time figuring out how to capture a right click on a TreeView, which I needed to be able to make a context menu or popup menu or what ever you wanna call it.
The way I expected it would work didn't... I think it have something to do with [a change in Mono some time ago][1] (Why don't I get ButtonPressEvents from my Button/Treeview?).

[1]: http://gtk-sharp.sourceforge.net/faq.html#3.3

This example is based on the "[Shortcuts - Writing Less Code][2]" example from the www.mono-project.com website.
The main difference is that the TreeView is no longer setup in the main class but is now a separate class with the function `OnButtonPressEvent` overwritten.

[2]: http://www.mono-project.com/GtkSharp_TreeView_Tutorial#Shortcuts_-_Writing_Less_Code

    public class TreeViewExample {
    	public static void Main ()
    	{
    		Gtk.Application.Init ();
    		new TreeViewExample ();
    		Gtk.Application.Run ();
    	}

    	public TreeViewExample ()
    	{
    		Gtk.Window window = new Gtk.Window ("TreeView Example");
    		window.SetSizeRequest (500,200);

    		MusicTreeView tree = new MusicTreeView ();
    		window.Add (tree);
    		window.ShowAll ();
    	}
    }

    // Creating a new class MusicTreeView which is derived from the TreeView class
    public class MusicTreeView : Gtk.TreeView {

            public MusicTreeView ()
            {
            		Gtk.ListStore musicListStore = new Gtk.ListStore (typeof (string), typeof (string));

            		this.AppendColumn ("Artist", new Gtk.CellRendererText (), "text", 0);
            		this.AppendColumn ("Title", new Gtk.CellRendererText (), "text", 1);

            		musicListStore.AppendValues ("Garbage", "Dog New Tricks");
            		this.Model = musicListStore;
            }

            // The TreeView has a build in function which is called upon a OnButtonPressEvent
            // We override the function to capture right clicks with the mouse
            protected override bool OnButtonPressEvent (Gdk.EventButton evnt)
            {
                    if(evnt.Button == 3) {
                            System.Console.WriteLine ("Right click");
                            return true;
                    }
                    // Now if we would ever get this far
                    // we run the TreeViews OnButtonPressEvent function
                    // to make sure everything else works as normal
                    return base.OnButtonPressEvent(evnt);
            }
    }

**Update:** I wrote a [new "right click in Gtk.TreeView" example][1].

[1]: 2007-02-26-gtktreeview-right-click-part-2.md
