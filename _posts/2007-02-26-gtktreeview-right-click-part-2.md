---
layout: post
status: publish
published: true
title: Gtk.TreeView right click part 2
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
wordpress_id: 185
wordpress_url: http://emcken.dk/wp/archives/185-gtktreeview-right-click-part-2.html
date: '2007-02-26 11:53:56 +0100'
date_gmt: '2007-02-26 11:53:56 +0100'
categories:
- Random hacks
tags: []
comments: false
---
While on my winter vacation I played around with the Gtk.TreeView and the right click challanges.
I found a way to extract the row which is clicked on and the data within.
The following example shows how (it builds upon my last [Gtk.TreeView right click example][1]:

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

            private Gtk.ListStore musicListStore;

            public MusicTreeView ()
            {
                    musicListStore = new Gtk.ListStore (typeof (string), typeof (string));

                    this.AppendColumn ("Artist", new Gtk.CellRendererText (), "text", 0);
                    this.AppendColumn ("Title", new Gtk.CellRendererText (), "text", 1);

                    musicListStore.AppendValues ("Garbage", "Dog New Tricks");
                    musicListStore.AppendValues ("The Chemical Brothers", "Galvanize");
                    this.Model = musicListStore;
            }

            // The TreeView has a build in function which is called upon a OnButtonPressEvent
            // We override the function to capture right clicks with the mouse
            protected override bool OnButtonPressEvent (Gdk.EventButton evnt)
            {
                    if(evnt.Button == 3) {
                            Gtk.TreePath path = new Gtk.TreePath();
                            // Get TreePath from the xy-coordinates of the mouse from the event
                            // GetPathAtPos is a function from the TreeView class
                            GetPathAtPos (System.Convert.ToInt16 (evnt.X), System.Convert.ToInt16 (evnt.Y), out path);
                            Gtk.TreeIter iter;
                            // Get iter from the path
                            if (this.musicListStore.GetIter (out iter, path)) {
                                    // Get artist and title from the iter
                                    string artist = (string) this.musicListStore.GetValue (iter, 0);
                                    string title = (string) this.musicListStore.GetValue (iter, 1);
                                    System.Console.WriteLine ("Artist: " + artist + ", Title: " + title);
                            }
                            return true;
                    }
                    // Now if we would ever get this far
                    // we run the TreeViews OnButtonPressEvent function
                    // to make sure everything else works as normal
                    return base.OnButtonPressEvent(evnt);
            }
    }

Notes I found in Monodoc:

*   TreePath represents a particular node of a TreeView
*   The TreeIter is the primary structure for accessing a tree row.

Whatever that means :)

[1]: {% post_url 2007-01-25-mono-coding-capturing-right-clicks-in-a-gtktreeview %}
