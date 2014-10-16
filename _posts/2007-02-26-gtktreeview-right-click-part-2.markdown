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
comments: []
---
<p>While on my winter vacation I played around with the Gtk.TreeView and the right click challanges.<br />
I found a way to extract the row which is clicked on and the data within.<br />
The following example shows how (it builds upon my last [Gtk.TreeView right click example][1]:</p>
<p>    public class TreeViewExample {<br />
        public static void Main ()<br />
        {<br />
            Gtk.Application.Init ();<br />
            new TreeViewExample ();<br />
            Gtk.Application.Run ();<br />
        }</p>
<p>        public TreeViewExample ()<br />
        {<br />
            Gtk.Window window = new Gtk.Window ("TreeView Example");<br />
            window.SetSizeRequest (500,200);</p>
<p>            MusicTreeView tree = new MusicTreeView ();<br />
            window.Add (tree);<br />
            window.ShowAll ();<br />
        }<br />
    }</p>
<p>    &#47;&#47; Creating a new class MusicTreeView which is derived from the TreeView class<br />
    public class MusicTreeView : Gtk.TreeView {</p>
<p>            private Gtk.ListStore musicListStore;</p>
<p>            public MusicTreeView ()<br />
            {<br />
                    musicListStore = new Gtk.ListStore (typeof (string), typeof (string));</p>
<p>                    this.AppendColumn ("Artist", new Gtk.CellRendererText (), "text", 0);<br />
                    this.AppendColumn ("Title", new Gtk.CellRendererText (), "text", 1);</p>
<p>                    musicListStore.AppendValues ("Garbage", "Dog New Tricks");<br />
                    musicListStore.AppendValues ("The Chemical Brothers", "Galvanize");<br />
                    this.Model = musicListStore;<br />
            }</p>
<p>            &#47;&#47; The TreeView has a build in function which is called upon a OnButtonPressEvent<br />
            &#47;&#47; We override the function to capture right clicks with the mouse<br />
            protected override bool OnButtonPressEvent (Gdk.EventButton evnt)<br />
            {<br />
                    if(evnt.Button == 3) {<br />
                            Gtk.TreePath path = new Gtk.TreePath();<br />
                            &#47;&#47; Get TreePath from the xy-coordinates of the mouse from the event<br />
                            &#47;&#47; GetPathAtPos is a function from the TreeView class<br />
                            GetPathAtPos (System.Convert.ToInt16 (evnt.X), System.Convert.ToInt16 (evnt.Y), out path);<br />
                            Gtk.TreeIter iter;<br />
                            &#47;&#47; Get iter from the path<br />
                            if (this.musicListStore.GetIter (out iter, path)) {<br />
                                    &#47;&#47; Get artist and title from the iter<br />
                                    string artist = (string) this.musicListStore.GetValue (iter, 0);<br />
                                    string title = (string) this.musicListStore.GetValue (iter, 1);<br />
                                    System.Console.WriteLine ("Artist: " + artist + ", Title: " + title);<br />
                            }<br />
                            return true;<br />
                    }<br />
                    &#47;&#47; Now if we would ever get this far<br />
                    &#47;&#47; we run the TreeViews OnButtonPressEvent function<br />
                    &#47;&#47; to make sure everything else works as normal<br />
                    return base.OnButtonPressEvent(evnt);<br />
            }<br />
    }</p>
<p>Notes I found in Monodoc:</p>
<p>*   TreePath represents a particular node of a TreeView<br />
*   The TreeIter is the primary structure for accessing a tree row.</p>
<p>Whatever that means :)</p>
<p>[1]: http:&#47;&#47;www.emcken.dk&#47;weblog&#47;archives&#47;179-Mono-coding-Capturing-right-clicks-in-a-Gtk.TreeView.html</p>
