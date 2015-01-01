---
layout: post
status: publish
published: true
title: MP3 encoding with Sound-Juicer
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
wordpress_id: 99
wordpress_url: http://emcken.dk/wp/archives/99-mp3-encoding-with-sound-juicer.html
date: '2005-03-07 23:11:38 +0100'
date_gmt: '2005-03-07 23:11:38 +0100'
categories:
- Debian
tags: []
comments:
- id: 18
  author: mike
  author_email: ''
  author_url: ''
  date: '2005-08-01 16:35:43 +0200'
  date_gmt: '2005-08-01 15:35:43 +0200'
  content: "here's what I did\r\n\r\nsudo gnome-audio-profiles-properties\r\n(add
    the stuff mentioned above)\r\nsudo gconf-editor\r\n - navigate to /system/gstreamer/audio/global
    and make the setting there default (from the right-click menu)\r\n - navigate
    to /system/gstreamer/audio/profiles/CD%..../ and make
    all the settings default (from the right-click menu)"
- id: 19
  author: thieumf
  author_email: edallpo@gmail.com
  author_url: ''
  date: '2005-08-02 13:03:24 +0200'
  date_gmt: '2005-08-02 12:03:24 +0200'
  content: You have to click on the "active" box (bottom-left) in the gnome-audio-profiles-properties
    panel  ;-)
- id: 21
  author: John Coonrod
  author_email: jc@thp.org
  author_url: www.hungerproject.org
  date: '2005-06-27 01:34:29 +0200'
  date_gmt: '2005-06-27 00:34:29 +0200'
  content: 'Slight correction: gnome-audio-profiles-properties will NOT let you create
    a duplicate CD Quality, Lossy profile, but it will let you edit the current one.
    ALSO - be sure to run the program in your own terminal, not the root terminal.'
- id: 22
  author: matt
  author_email: matt@webtech.co.nz
  author_url: dakota.net.nz
  date: '2005-06-30 14:11:16 +0200'
  date_gmt: '2005-06-30 13:11:16 +0200'
  content: did all of that stuff mentioned above but the new profile still wasn't
    available in Sound Juicer so no joy for me.  In all of th forums I've looked at
    I haven't noticed anyone's complained of the same thing ... is there something
    real obvious that I'm missing that isn't explicitly stated there? (I mean the
    help file in Sound juicer basically gives the sam instructions)
- id: 23
  author: Jacob Emcken
  author_email: jacob@emcken.dk
  author_url: http://www.emcken.dk/
  date: '2005-07-02 15:57:09 +0200'
  date_gmt: '2005-07-02 14:57:09 +0200'
  content: "I dunno how but I have 2 profiles named 'CD Quality, Lossy'. One defined
    global (in `/etc/gconf/schemas/gnome-audio-profiles.schemas`)
    and one local for my user (in `~/.gconf/system/gstreamer/audio/profiles/mp3/%gconf.xml`).\r\n."
- id: 24
  author: Jacob Emcken
  author_email: jacob@emcken.dk
  author_url: http://www.emcken.dk/
  date: '2005-07-02 16:04:47 +0200'
  date_gmt: '2005-07-02 15:04:47 +0200'
  content: "Apart from what John wrote:\r\n\r\nRemember to run Sound-Juicer as the
    same user as you did with `gnome-audio-profiles-properties`. Because the profiels
    are user specific."
- id: 26
  author: Jacob's Weblog
  author_email: ''
  author_url: http://www.emcken.dk/weblog/archives/127-Sound-Juicer-and-MP3-in-Ubuntu.html
  date: '2005-08-09 23:26:20 +0200'
  date_gmt: '2005-08-09 22:26:20 +0200'
  content: |-
    After my success with installing Ubuntu I wanted to rip 2 new CD's I got today. But the gstreamer MP3 encoder (gstreamer0.8-lame) isn't in the Ubuntu repositories.

    I found it at Marillat's Debian repository (direct link) where I downloaded it and install
- id: 33
  author: newbie
  author_email: rrodman@mac.com
  author_url: ''
  date: '2005-11-10 20:22:29 +0100'
  date_gmt: '2005-11-10 19:22:29 +0100'
  content: "Can't find the gstreamer file anywhere, gstreamer-8.0-lame, you could
    have included a link to it, that's the hard part.\r\n\r\nUbuntu looks pretty and
    is easy to install, but it has these opacities throughout. They should just come
    out and say, 'You can't encode to MP3 because of stupid software patents', or
    whatever, and let the user know he's hosed, rather than pussyfooting around the
    issue. This is indeed Lame."
- id: 34
  author: Ravnos
  author_email: ''
  author_url: ''
  date: '2006-01-19 20:27:02 +0100'
  date_gmt: '2006-01-19 19:27:02 +0100'
  content: "Hey, for everyone looking to make CBR MP3s, I found this on the Fedora
    Forums.\r\n\r\naudio/x-raw-int,rate=44100,channels=2 ! lame name=enc vbr=0
    bitrate=128\r\n\r\nChange bitrate to whatever number you'd like. I like 192 myself,
    it's a nice balance between size and quality."
- id: 39
  author: matt
  author_email: matt@webtech.co.nz
  author_url: dakota.net.nz
  date: '2005-07-03 13:28:50 +0200'
  date_gmt: '2005-07-03 12:28:50 +0200'
  content: thanks for that I was editing the root profiles then trying to run it on
    my own one working good now.
- id: 40
  author: jel
  author_email: emanuel.landeholm@gmail.com
  author_url: file:///etc/passwd
  date: '2005-08-29 16:50:16 +0200'
  date_gmt: '2005-08-29 15:50:16 +0200'
  content: "Great tip. I tried this and it's working fine. It produces VBR mp3s by
    default though and I'd like CBR so I'll just see if I can tweak it somehow\r\n\r\nOne
    thing though: gstreamer complains that:\r\n\r\nCouldn't find matching gstreamer
    tag for track-count\r\nCouldn't find matching gstreamer tag for encoder\r\nCouldn't
    find matching gstreamer tag for encoder-version\r\n\r\nfor each track converted
    in sound-juicer although the produced mp3s work OK (tested in beep media player).\r\n\r\nIs
    it possible to silence gstreamer somehow? Or is this a serious warning?"
- id: 42
  author: Jacob Emcken
  author_email: jacob@emcken.dk
  author_url: http://www.emcken.dk/
  date: '2005-11-16 22:05:12 +0100'
  date_gmt: '2005-11-16 21:05:12 +0100'
  content: "The `gstreamer-lame` package isn't in Ubuntus repositories put you can
    fetch them here at [Marillat's Debian repository][1]\r\n\r\n[1]: http://www.las.ic.unicamp.br/pub/debian-marillat/"
- id: 44
  author: Alberto
  author_email: ''
  author_url: ''
  date: '2005-07-27 12:24:42 +0200'
  date_gmt: '2005-07-27 11:24:42 +0200'
  content: I did add the profile as user, but, still, when I run sound-juicer the
    new profile doesn't appear in the properties list. :-(
- id: 47
  author: Bill
  author_email: ''
  author_url: ''
  date: '2006-02-01 21:25:59 +0100'
  date_gmt: '2006-02-01 20:25:59 +0100'
  content: A word of warning!  You need to restart juicer (well, I did anyway) to
    get it to pick up the new entry, and if you've just spent ages typing in the track
    names because it didn't pick them up automatically, you loose them. :(
- id: 48
  author: Fabian
  author_email: auberginepop@yahoo.co.uk
  author_url: ''
  date: '2006-02-10 00:28:38 +0100'
  date_gmt: '2006-02-09 23:28:38 +0100'
  content: What bit rate does the mp3 file get encoded with?
- id: 52
  author: Rehcz
  author_email: re_hall@yahoo.com
  author_url: ''
  date: '2006-10-17 08:22:34 +0200'
  date_gmt: '2006-10-17 07:22:34 +0200'
  content: I just wanted to turn my CD collection into mp3s. Spent hours trying to
    get sound juicer to work and failed. I then installed ****grip****, selected the
    lame encoder and it just worked!
- id: 56
  author: Leonya
  author_email: ''
  author_url: ''
  date: '2006-11-08 14:13:24 +0100'
  date_gmt: '2006-11-08 13:13:24 +0100'
  content: "As of Ubuntu Edgy 6.10, the mp3 (lame) encoding comes in the gstreamer0.10
    plugins.  \r\nI installed those using these instructions:\r\nhttp://ubuntuguide.org/wiki/Ubuntu_Edgy#How_to_install_Multimedia_Codecs\r\nAfter
    installing the above, followed your instructions on setting up gnome-audio-profiles-properties,
    and it worked!  Thanks!"
- id: 59
  author: boiled
  author_email: ''
  author_url: ''
  date: '2006-11-13 04:14:50 +0100'
  date_gmt: '2006-11-13 03:14:50 +0100'
  content: add "! id3v2mux" to the end of the pipeline.
- id: 60
  author: Tersius
  author_email: ''
  author_url: ''
  date: '2006-05-27 15:47:05 +0200'
  date_gmt: '2006-05-27 14:47:05 +0200'
  content: "To get all the parameters for the gst-lame plugin:\r\n#gst-inspect-0.8
    lame"
- id: 61
  author: Luke771
  author_email: ''
  author_url: ''
  date: '2006-05-28 11:01:00 +0200'
  date_gmt: '2006-05-28 10:01:00 +0200'
  content: "Now I can extract music in mp3 format instead of converting .ogg files:
    cuts the time by a half.\r\nThanks dude."
- id: 62
  author: zac
  author_email: ''
  author_url: ''
  date: '2006-05-28 14:00:57 +0200'
  date_gmt: '2006-05-28 13:00:57 +0200'
  content: how do you get it to label the id tag?
- id: 65
  author: ephemerae
  author_email: ''
  author_url: ''
  date: '2006-06-15 07:34:13 +0200'
  date_gmt: '2006-06-15 06:34:13 +0200'
  content: "GStreamer is a pain in the ass.\r\n\r\nInstall grip.\r\nInstall lame and
    liblame0.\r\n\r\nRead the lame man page to see what options you would like to
    use for encoding your MPs and tell Grip to use these with lame.\r\n\r\nMyself,
    I tell Grip to have the following Encoder command line  under Config/Encode/Encoder:\r\n\r\n-q0
    -v -V0 -b 160 -B 320 \"%w\" \"%m\"\r\n\r\n(highest quality, variable bit rate,
    highest quality variable bit rate, minimum bit rate 160, maximum bit rate 320,
    wav file, mp3 file).\r\n\r\nTry this, you'll be happy."
- id: 66
  author: lluisanunez
  author_email: lluisanunez@gmail.com
  author_url: ''
  date: '2006-06-30 19:30:21 +0200'
  date_gmt: '2006-06-30 18:30:21 +0200'
  content: If you export them from soundjuicer to MP3, they are 128 bits. I got much
    better results exporting them to OGG, and then converting them to MP3 with audio-converter
    -high quality parameters.
- id: 67
  author: leonblanco
  author_email: ''
  author_url: ''
  date: '2006-08-13 08:06:23 +0200'
  date_gmt: '2006-08-13 07:06:23 +0200'
  content: hi.. first tnkz for all the tips.. is the best place i found to make soundjuicer
    work with lame.. but i have a problem.. the track are encoded with all the information
    ok but icant play'em.. sometimes i get a MIME error.. what can i do? tnkz in advance.
- id: 68
  author: Mike
  author_email: ''
  author_url: ''
  date: '2006-08-14 19:05:22 +0200'
  date_gmt: '2006-08-14 18:05:22 +0200'
  content: If you activate ALL Ubuntu repositories in Synaptic (backports, universe,
    multiverse, etc), you can download the gstreamer-8.0-lame directly from Synaptic.
    Nothing complicated needed. ;)
- id: 69
  author: David Haas
  author_email: dc_haas@yahoo.com
  author_url: none
  date: '2005-12-17 18:36:50 +0100'
  date_gmt: '2005-12-17 17:36:50 +0100'
  content: "Sound Juicer gives the instructions for converting to MP3 format at ripping
    in its Help>Preferences section:\r\n\r\n\"If you need to store tracks in the MP3
    format (for example, because your portable music player only supports MP3 and
    not Ogg Vorbis), you will need to create a new profile. To do this, run gnome-audio-profiles-properties,
    press New and name it MP3. Then press Edit and set GStreamer Pipeline to audio/x-raw-int,rate=44100,channels=2
    ! lame name=enc, the File Extenstion to mp3, and check Active. Then start Sound
    Juicer and select the MP3 format.\r\n\r\nThis profile uses the LAME MP3 encoder,
    so you will need to have the GStreamer LAME plugin installed.\"\r\n\r\nAnd now
    Ubuntu Hoary has the needed \"LAME\" GStreamer plugin installed by default. If
    not, it can readily be installed, of course, in Synaptic."
- id: 70
  author: compilerbitch
  author_email: compilerbitch@livejournal.com
  author_url: http://compilerbitch.livejournal.com/
  date: '2005-12-18 16:38:42 +0100'
  date_gmt: '2005-12-18 15:38:42 +0100'
  content: Excellent -- many thanks for the useful post (found via google). It has
    solved our problem. :-)
- id: 71
  author: zazoo
  author_email: ''
  author_url: ''
  date: '2005-12-30 04:01:46 +0100'
  date_gmt: '2005-12-30 03:01:46 +0100'
  content: use synaptic to install gstreamer lame and mad.
- id: 74
  author: kristarella
  author_email: kristarella@gmail.com
  author_url: kristarella.com
  date: '2006-11-14 05:28:08 +0100'
  date_gmt: '2006-11-14 04:28:08 +0100'
  content: "Worked beautifully! Thanks for the tip.\r\nI saw someone say that it's
    in the program's help section on how to do this but I rarely look there, usually
    because it's so unhelpful :P Your page was at the top of my Google search."
- id: 76
  author: dzlatkov
  author_email: ''
  author_url: ''
  date: '2006-11-20 08:29:45 +0100'
  date_gmt: '2006-11-20 07:29:45 +0100'
  content: "I'm a gentoo user and this does not work for me. The registry doesn't
    have the 'x-raw-int' option. I got it work by changing the gnome-audio settings
    to:\r\n\r\nGStreamer Pipeline: audio/mpeg, mpegversion=1 layer=3 rate=44100,channels=2
    ! lame name=enc\r\n\r\nI don't know why x-raw-int should even be a value..."
- id: 77
  author: Hansi
  author_email: Steuerer@net-base.de
  author_url: ''
  date: '2006-11-27 00:10:39 +0100'
  date_gmt: '2006-11-26 23:10:39 +0100'
  content: "I saw gstreamer is installed on my SUSE 10.1, lame as well, changed my
    \ gnome-audio-profiles-properties and it seems to work fine. Just it turned out
    that the MP3 files were about twice the sice of the .flac files.\r\nI tried: gst-inspect-0.10
    lame\r\nNo such element or plugin 'lame'\r\nInstalling gstreamer010-plugins-ugly
    solved the problem, it seems to include gstreamer010-lame plugin."
- id: 79
  author: Rob
  author_email: ''
  author_url: ''
  date: '2006-12-07 00:07:49 +0100'
  date_gmt: '2006-12-06 23:07:49 +0100'
  content: Only if you leave bitrate=128.  Set that to 356 if you want... personally
    I like 192.  But don't blame that on sound juicer; it's your error.
- id: 80
  author: Jon Grosshart
  author_email: ''
  author_url: ''
  date: '2006-12-17 07:59:10 +0100'
  date_gmt: '2006-12-17 06:59:10 +0100'
  content: "Here is a patch for gnome-media to fix this upstream if anyone is interested..
    I prefer to do it this way rather than on a per user basis... Probably only usefull
    for people who compile Gnome themselves, like I do...\r\n\r\nhttp://www.angelfire.com/linux/madpenguin1/misc/gnome-media-2.16.1-add_mp3_profile.patch"
- id: 88
  author: Andy
  author_email: ''
  author_url: ''
  date: '2006-12-29 22:46:22 +0100'
  date_gmt: '2006-12-29 21:46:22 +0100'
  content: Really simple to do this now, the latest version of Sound Juicer tells
    you how to encode mp3's in its help menu.  A few clicks and you should be away!
- id: 91
  author: populas
  author_email: populas@vengence.com
  author_url: ''
  date: '2007-01-05 02:01:51 +0100'
  date_gmt: '2007-01-05 01:01:51 +0100'
  content: Sweet man, thanks for the syntax. Worked like a champ!
- id: 92
  author: b-llwyd
  author_email: ''
  author_url: ''
  date: '2007-01-08 18:39:43 +0100'
  date_gmt: '2007-01-08 17:39:43 +0100'
  content: "Yeah...same here. Fresh ubuntu edgy, and making sound juicer do the right
    thing was too much of a hassle atm (ok, I only spent 10 mins on it, but got bored
    of the fiddling/version intermezzos).\r\n\r\nsudo apt-get install grip lame"
- id: 94
  author: Aron van Ammers
  author_email: aron@multitof.com
  author_url: ''
  date: '2007-01-09 12:31:26 +0100'
  date_gmt: '2007-01-09 11:31:26 +0100'
  content: "I'm a Ubuntu Edgy (6.10) user. I followed the suggestions from the Sound
    Juicer help file and used the following pipeline:\r\n\r\naudio/x-raw-int,rate=44100,channels=2
    ! lame name=enc vbr=0 bitrate=196 ! id3v2mux\r\n\r\nThis caused Sound Juicer to
    hang every time upon pressing the Extract button. Only a 0-byte file was written.\r\n\r\nAfter
    tinkering with the options I found out the option \"vbr=0\" caused it to hang.
    When I removed that, it worked flawlessly. So the following line worked for me:\r\n\r\naudio/x-raw-int,rate=44100,channels=2
    ! lame name=enc bitrate=196 ! id3v2mux\r\n\r\nCheers,\r\nAron"
- id: 102
  author: dogs
  author_email: dogsthatchasecars@hotmail.com
  author_url: ''
  date: '2007-02-04 15:59:28 +0100'
  date_gmt: '2007-02-04 14:59:28 +0100'
  content: "Second that vote for *grip* -- I tried for a couple of hours to get Sound
    Juicer to rip at 192kbps, but it just wouldn't play ball & I ain't that bright
    :(\r\n\r\nInstalled grip in a couple of minutes, _finally_ managed to get lame
    library downloaded from:\r\nhttp://www.rarewares.org/mp3.html\r\nhttp://www.rarewares.org/files/mp3/lame-3.97b-Linux86.tar.gz\r\n\r\nPut
    the library in /usr/bin/ , and then made sure the Config->Encode->Encoder
    executable was pointing at /usr/bin/lame\r\n\r\nIt would be nice if
    there was an easier way for it to \"just work\" ;)"
- id: 104
  author: Vladimir Prieto
  author_email: vladimirprieto@gmail.com
  author_url: ''
  date: '2006-03-27 17:59:55 +0200'
  date_gmt: '2006-03-27 16:59:55 +0200'
  content: "hi, this explanation was cool, but i spend hours trying to find out howto
    change \"mode\" of my encoded mp3 files.\r\n\r\ni want them with stereo mode and
    not with joint stereo mode.  and even with the lame preset to insane i get joint
    stereo.\r\n\r\nafters hours searching i finally found that with this command:\r\n\r\ngst-inspect-0.8
    lame\r\n\r\ni can see all options/parameters that can be passed to the gstreamer
    pipeline.  so i finally use this one :\r\n\r\naudio/x-raw-int,rate=44100,channels=2
    ! lame name=enc bitrate=320 mode=0\r\n\r\nhope this helps someone..."
- id: 107
  author: Chris
  author_email: ''
  author_url: ''
  date: '2006-04-14 00:19:19 +0200'
  date_gmt: '2006-04-13 23:19:19 +0200'
  content: "In Dapper you need to install gstreamer0.10-plugins-ugly-multiverse\r\n\r\nIt's
    working perfectly for me now.  Cheers."
- id: 108
  author: noname
  author_email: ''
  author_url: ''
  date: '2006-04-21 23:28:45 +0200'
  date_gmt: '2006-04-21 22:28:45 +0200'
  content: "128.  As mentioned above, use this line in gnome-audio-profiles-properties
    to change it:\r\n\r\naudio/x-raw-int,rate=44100,channels=2 ! lame name=enc
    vbr=0 bitrate=128\r\n\r\nAnd change '128' to whatever you want."
- id: 110
  author: Andy Kennedy
  author_email: jazzdude@ntlworld.com
  author_url: ''
  date: '2006-05-08 19:17:36 +0200'
  date_gmt: '2006-05-08 18:17:36 +0200'
  content: I tried this and the size of the mp3 files is massive. I ripped a trackwith
    sound-juicer and it came out at 70MB where the same track was about 5MB with KaudioCreator
    using the same encoder.
- id: 111
  author: nick
  author_email: ''
  author_url: ''
  date: '2006-05-18 23:15:47 +0200'
  date_gmt: '2006-05-18 22:15:47 +0200'
  content: 128 by default
- id: 112
  author: ivan
  author_email: ivst011@hotmail.com
  author_url: ''
  date: '2006-05-22 21:15:58 +0200'
  date_gmt: '2006-05-22 20:15:58 +0200'
  content: "i have let say same problem with sound juicer \r\nwhen i whant to ripp
    some cd to mp3 program give me this message \"could not create gstreamer encoder
    (null)\" ,\r\ni create mp3 profile, but i when i run sudo gconf-editor - navigate
    to run/system/gstreamer/audio/global i don`t have mp3 profile
    folder it`s that normal  help"
- id: 115
  author: Tim LePes
  author_email: luvdownbabylon@gmail.com
  author_url: ''
  date: '2007-02-20 07:05:09 +0100'
  date_gmt: '2007-02-20 06:05:09 +0100'
  content: "I finally got this working on Edgy (6.10) by installing the 0.10 ugly
    package and using the \"audio/mpeg, mpegversion=1 layer=3 rate=44100,channels=2
    ! lame name=enc ! id3v2mux\" pipeline.\r\n\r\nThis made sense since the \"gst-inspect-0.10
    lame\" command showed me that lame is audio/mpeg on my setup.  I can't seem
    to get it to work with the bitrate= or vbr= settings, though.  My default is 128bps,
    which is fine for the task at hand.  But how do I get them changed for future
    reference?  Is there a way to modify the default of 128 CBR (I presume it is CBR),
    or is there some other syntax I should be using in the pipeline?\r\n\r\nThanks
    all!"
- id: 116
  author: a owen
  author_email: aowen@swissonline.ch
  author_url: ''
  date: '2007-02-24 17:25:20 +0100'
  date_gmt: '2007-02-24 16:25:20 +0100'
  content: "i'm having trouble with the file name. when i select \"track artis (sortable)
    - track title\" it takes the artist from the cd and not the track! what am i doing
    wrong?\r\n\r\ni'm using ubuntu 6.10\r\n\r\ncheers\r\nandi"
- id: 117
  author: Neurowiz
  author_email: ''
  author_url: ''
  date: '2007-02-28 21:02:40 +0100'
  date_gmt: '2007-02-28 20:02:40 +0100'
  content: "Your pipeline is what worked for me as well.\r\n\r\nHere are my steps:\r\n\r\nInstall
    lame\r\nInstall gstreamer8.0-lame\r\nInstall gstreamer0.10-plugins-ugly\r\nInstall
    gstreamer0.10-plugins-ugly-multiverse\r\n\r\n\"audio/mpeg, mpegversion=1 layer=3
    rate=44100,channels=2 ! lame name=enc bitrate=192 ! id3v2mux\"\r\n\r\nThis encodes
    at 192 bitrate. It worked for me on an Edgy 6.10 system. Hope this helps!\r\n\r\n(BTW,
    I did the original instructions, but the lame plugin wasn't successfully installed
    until I installed the ugly-multiverse set.)"
- id: 118
  author: Jim Nutt
  author_email: jim@nuttz.org
  author_url: http://jim.nuttz.org
  date: '2007-03-07 20:32:10 +0100'
  date_gmt: '2007-03-07 19:32:10 +0100'
  content: Use "vbr=new" instead of "vbr=0", that tells lame to use the new vbr algorithm  and
    seems to work correctly. I believe the vbr=0 was for the 0.8 version of gstreamer.
- id: 123
  author: Ghost
  author_email: imnot@home.now
  author_url: www.whatever.com
  date: '2007-04-02 00:03:22 +0200'
  date_gmt: '2007-04-01 23:03:22 +0200'
  content: "Hey, just an FYI, remember that after creating a new one in Juicer, you
    have to exit out of it, then restart it fresh...\r\n\r\nMagically, you'll see
    the one you created in the list, providing you set it for active.\r\n\r\nCheers,\r\nGhost"
- id: 124
  author: arcibald
  author_email: arcibald@gmx.net
  author_url: arcibald.blogspot.com
  date: '2007-04-22 09:35:21 +0200'
  date_gmt: '2007-04-22 08:35:21 +0200'
  content: "i found this very useful. now i can extract mp3s from the juicer... but
    would anyone know how to adjust the size of the mp3 as the extracted ones range
    from 20mb to 50mb per song?\r\n\r\nthanks"
- id: 128
  author: weklof
  author_email: ''
  author_url: ''
  date: '2007-04-29 14:37:25 +0200'
  date_gmt: '2007-04-29 13:37:25 +0200'
  content: "I'm not getting this to work, I don't know what I'm doing wrong...\r\n\r\nI
    can see the profile when I click \"Edit Profiles\", but I can't choose it as an
    output format"
- id: 129
  author: Ryan
  author_email: secret@top.com
  author_url: ''
  date: '2007-04-29 20:15:49 +0200'
  date_gmt: '2007-04-29 19:15:49 +0200'
  content: "Arcibald, you may have already solved it, but I ran into the same problem.
    \r\n\r\nWhen you go to Edit -> Preferences -> Edit Profiles you should get a list
    of badly named audio profiles. Click new or edit and another window will come
    up that you're unable to modify (that's the bug.) The work around is easy: Just
    close the \"Edit GNOME Audio Profiles\" but leave the \"Editing Profile ...\"
    window open. You can then modify your profile and save your changes.\r\n\r\nI'm
    really happy with Sound Juicer so far. I have badly scratched CD's that I'm making
    .flac backups of, and so far I've tried the following:\r\n1. Grip\r\n2. Rubyripper\r\n3.
    wine + exact audio copy (yes, it works if installed correctly. This guide helps:
    http://www.teqnilogik.com/tutorials/eac.htm#CompressionOptionsFLAC\r\n4.
    Sound Juicer\r\n\r\nThey all work but I found that Sound Juicer was not only the
    fastest, but I have yet to detect any errors with the rips. Oddly enough, Grip
    (which I think is an awesome tool!) took about 40 minutes on one cd that had a
    lot of scratches, and Exact Audio Copy couldn't get past the errors on track 3
    of the same cd. Granted I probably don't have them configured optimally or to
    be permissive with errors, but I ran the same CD through Sound Juicer and it came
    out great! I turned the volume up, played track 3 and couldn't hear any problems.
    So I'm really impressed. It ripped that mangled CD in probably 5-7 mins too.\r\n\r\nI
    love my Ubuntu Desktop :)"
- id: 131
  author: mikk0
  author_email: mikko.s.saarinen@mbnet.fi
  author_url: ''
  date: '2007-04-30 10:05:28 +0200'
  date_gmt: '2007-04-30 09:05:28 +0200'
  content: "I am using Ubuntu 7.04 and these two variations work for me:\r\n\r\naudio/x-raw-int,rate=44100,channels=2
    ! lame name=enc mode=0 vbr=4 vbr-quality=2 ! id3v2mux\r\n\r\nthis is for vbr-encoded
    files. Good quality, but not insanely large files. For heavy metal it produces
    something like 7,1 MB for a 4.08 minute song (average of 234 kbps) and 7,7 MB
    for a 4.35 minute song (230 kbps). Little less for something like Alexia - Summer
    is Crazy (199 kbps).\r\n\r\naudio/x-raw-int,rate=44100,channels=2 ! lame name=enc
    mode=0 bitrate=192 ! id3v2mux\r\n\r\nThe latter uses cbr of 192 kbps which is
    also rather good quality.\r\n\r\nBy the way, the id3v2mux makes the id3tags to
    the files, but they aren't compatible with my SE W850i phone. So I had to install
    easytag to handle with the id3tags. Now everything works like charm =)"
- id: 138
  author: Brendan
  author_email: briley@curragh-labs.org
  author_url: ''
  date: '2007-06-19 17:09:26 +0200'
  date_gmt: '2007-06-19 16:09:26 +0200'
  content: "I'm also having trouble getting SJ to see the new profile.  I've created
    it using the instructions here and in the \"help\" pages, but no matter what I
    do, Sound Juicer is not seeing the new format option.\r\n\r\nI am not running
    either program as root.  Any other ideas?"
- id: 140
  author: CrazyTonic
  author_email: ''
  author_url: ''
  date: '2007-07-08 17:21:12 +0200'
  date_gmt: '2007-07-08 16:21:12 +0200'
  content: "Hi i found this site, by searching a way to configure Sound-Juicer.\r\n\r\nI
    currently use the following to make vbr mp3s. This i use since a long time with
    lame. Its the best kompromiss.\r\n\r\naudio/x-raw-int,rate=44100,channels=2
    ! lame name=enc vbr=new  b=128 B=320 k\r\n\r\nthe lowest used bitrate is 128 the
    highest 320, so i get te best optimum i think.\r\n\r\nlame normal cut highs and
    lows, on Pop-Musik wtih \"k\", lame dont cut them. On some Musik with a wide range
    of Instruments, especialy Folk and classic u will can feel the effect. On Mainstream
    POP-Music, the effect is low."
- id: 145
  author: barry
  author_email: none
  author_url: none
  date: '2007-07-28 23:43:01 +0200'
  date_gmt: '2007-07-28 22:43:01 +0200'
  content: "For Ubuntu 7.10 Feisty Fawn:\r\nI set up everything according to instructions.
    I installed gsteamer0.08-lame. And created a new mp3 profile (which wasn't needed)
    But sound juicer would not see the mp3 profile. \r\nI also tried a couple of other
    useless things. \r\n\r\nMy analysis:\r\nI found that are two versions of gstreamer:
    0.8 and 0.10. And gstreamer0.08-lame will not work with Sound Juicer on this version
    of Ubuntu. I used the command gst-inspect-0.10 to try to find lame.\r\ncommand>gst-inspect-0.10
    | grep -i lame\r\n\r\nThe solution:\r\nI the following package with Synaptic.\r\ngstreamer0.10-plugins-ugly-multiverse\r\nAnd
    this package contains gstreamer.10-lame\r\nAnd now Sound Juicer can see MP3s.\r\n\r\nWhatMeWorry!"
- id: 146
  author: will
  author_email: illnes@donotreply.com
  author_url: ''
  date: '2007-08-05 17:29:36 +0200'
  date_gmt: '2007-08-05 16:29:36 +0200'
  content: "I too am having grip trouble, but now that link you provided doesn't seem
    to be there anymore. it keeps telling me my encoder executable is invalid, even
    though i've installed lame (and restarted.) I'm lost as to what to do.\r\n\r\nI
    had a weird sound juicer issue which caused me to try to use grip: i could not
    alter any text in the edit profiles section, and even though the \"CD Quality
    - mp3\" profile was listed in my 'edit profiles' list upon install, and it was
    listed as \"active,\" it did not turn up in my available profiles.\r\n\r\np.s.
    I'm using feisty fawn."
- id: 147
  author: Will
  author_email: illnes@donotreply.com
  author_url: ''
  date: '2007-08-05 17:43:06 +0200'
  date_gmt: '2007-08-05 16:43:06 +0200'
  content: 'Update: i set the executable line to "usr/bin/lame" like dogs
    did, and it just... worked. i didn''t tell the library to go there; i guess it
    just knew to.'
- id: 148
  author: kehan
  author_email: kehanharman@gmail.com
  author_url: http://kehan.wordpress.com
  date: '2007-08-08 23:19:13 +0200'
  date_gmt: '2007-08-08 22:19:13 +0200'
  content: "Is it just me or does this create files which are padded with about 20
    minutes of blank audio at the end (in rhythmbox at least)?\r\n\r\nGreat howto
    by the way."
- id: 149
  author: Mikhail
  author_email: ''
  author_url: ''
  date: '2007-08-29 20:46:28 +0200'
  date_gmt: '2007-08-29 19:46:28 +0200'
  content: '"audio/mpeg..." also works on Ubuntu 6.06. The string "audio/x-raw-int..."
    from the reference produced 50 mb files.'
- id: 159
  author: Epibeta
  author_email: ''
  author_url: http://www.epibeta.com/
  date: '2008-02-17 00:05:28 +0100'
  date_gmt: '2008-02-16 23:05:28 +0100'
  content: CrazyTonic, you are my hero! Upgrading to gstreamer0.10 made Sound Juicer
    see my new profiles instantly. That was pretty confusing until I read your comment,
    so thanks! For the record, my setup was Sound Juicer 2.16.3 under Ubuntu Feisty
    Fawn (7.04).
- id: 166
  author: Miller L
  author_email: miller.larson@gmail.com
  author_url: ''
  date: '2008-05-14 09:09:57 +0200'
  date_gmt: '2008-05-14 08:09:57 +0200'
  content: "Having luck with the following in Arch using Sound Juicer 2.22.0:\r\n\r\naudio/x-raw-int,rate=44100,channels=2
    ! lame name=enc mode=0 quality=2 vbr=4 vbr-quality=0 ! id3v2mux\r\n\r\nI found
    that using \"gst-inspect-0.10 lame\" in conjunction with \"man lame\" helped me
    -greatly- in figuring out which options I wanted to setup for SJ!\r\n\r\nThanks
    all for the guidance ;)\r\n\r\nOnce you get lame tweaked in SJ, I think its MUCH
    better and easier than Grip...  which I've sworn by for 6 years."
- id: 170
  author: Paulo Calipari
  author_email: P.vanderHeyden@gmail.com
  author_url: ''
  date: '2008-05-18 01:41:31 +0200'
  date_gmt: '2008-05-18 00:41:31 +0200'
  content: "This was my problem for some time:\r\nI edited an existing & created a
    new mp3 profile (which wasn't needed) but Sound Juicer would not show the mp3
    profile for applying.\r\n(Working with Ubuntu 7.04 on AMD_64.)\r\n\r\nSolution:
    sudo apt-get install gstreamer0.10-plugins-ugly-multiverse\r\nSee: http://ubuntuforums.org/showthread.php?t=581306"
- id: 172
  author: Kehan H
  author_email: kehanharman@gmail.com
  author_url: ''
  date: '2008-06-19 10:22:16 +0200'
  date_gmt: '2008-06-19 09:22:16 +0200'
  content: Just remembered I struggled with this for a while and then discovered the
    correct settings in sound juicer's help menu!!! Also with Hardy 8.04 this works
    out of the box no configuration needed.
- id: 188
  author: Floyd
  author_email: Floyd@Mabels.biz
  author_url: ''
  date: '2008-12-10 04:58:25 +0100'
  date_gmt: '2008-12-10 03:58:25 +0100'
  content: "I have struggled with this in Heron for a while, gave up on sound juicer
    because after editing in profiles it removed the one I edited and I was left with
    3 not four.... the MP3 option shows up as editable but never is available to be
    selected. I tried creating new profiles and they too were not available to be
    selected as options although they saved just fine. I am not sure why but I just
    cant seem to get this one.... I have not tried the sudo route noted above, perhaps
    that is next.. but with all the comments it would seem that I don't need to do
    that? sigh.... working with grip right now perhaps it will get the job done.....
    but not as of yet...\r\nLuck"
- id: 192
  author: mp3 players
  author_email: giftdeals@yahoo.com
  author_url: www.thegiftmallonline.com/category/mp4_players
  date: '2009-01-13 22:28:29 +0100'
  date_gmt: '2009-01-13 21:28:29 +0100'
  content: i also had the same problem and i tried to use the advise what i received
    from this blog and i still have the problem i dont know what to do anymore i am
    giving up!!
- id: 249
  author: Doug
  author_email: dshelton@san.rr.com
  author_url: ''
  date: '2009-02-22 20:33:39 +0100'
  date_gmt: '2009-02-22 19:33:39 +0100'
  content: "I had a lot of trouble getting high-quality mp3s out of sound-juicer/gstreamer.
    \ Using the version installed from the repository from Ubuntu 8.10, it seems the
    only way to get gstreamer to work the way you want is to override every one of
    its default settings with something you want. I was finally able to use this pipeline:\r\naudio/x-raw-int,rate=44100,channels=2
    ! lame name=enc mode=0 vbr=4 vbr-quality=0 quality=0 vbr-min-bitrate=32 vbr-max-bitrate=320
    lowpass-freq=25000 ath-lower=0 ! id3v2mux\r\n\r\nto get insane-quality VBR files
    equivalent to\r\nlame -m stereo -q 0 --vbr-new -V0 --add-id3v2 {infile} {outfile}\r\n\r\nI
    used VLC's statistics feature as suggested above and found these files to have
    the same stream bitrate and variability with files created by command-line invocation
    of LAME using the above parameters.  I hope this helps someone."
- id: 256
  author: Pitt
  author_email: peter@dpewen.de
  author_url: ''
  date: '2009-03-03 18:53:54 +0100'
  date_gmt: '2009-03-03 17:53:54 +0100'
  content: "your suggest:\r\naudio/x-raw-int,rate=44100,channels=2 ! lame name=enc
    mode=0 vbr=4 vbr-quality=0 quality=0 vbr-min-bitrate=32 vbr-max-bitrate=320 lowpass-freq=25000
    ath-lower=0 ! id3v2mux\r\nworks great, thanks it helps"
- id: 356
  author: Phil
  author_email: emcken@payneinthe.com
  author_url: ''
  date: '2009-12-04 16:06:41 +0100'
  date_gmt: '2009-12-04 15:06:41 +0100'
  content: |
    Can i suggest for high quality MP3's using:


    audio/x-raw-int,rate=44100,channels=2 ! lame name=enc mode=0 preset=extreme


    This uses the lame preset for extreme which is variable bitrate at a high bitrate. FOr more info just type


    lame --preset help.


    Just create a new preset in sounds juicer with the settings as above.

- id: 369
  author: Gregg Lebovitz
  author_email: gregg@lebovitz.net
  author_url: ''
  date: '2010-01-31 01:18:32 +0100'
  date_gmt: '2010-01-31 00:18:32 +0100'
  content: |
    Sound Juicer sounds great on paper (blog pages?), but 20% of the time I get the error message that it can't read the track listing.


    Grip, on the other hand, always works. I think clean and mean is great, but if it doesn't work then it isn't a very good piece of software.

- id: 418
  author: Pablo
  author_email: me.argentino@gmail.com
  author_url: ''
  date: '2010-05-31 12:01:03 +0200'
  date_gmt: '2010-05-31 11:01:03 +0200'
  content: |
    Hi, I used:


    audio/x-raw-int,rate=44100,channels=2 ! lame name=enc mode=0 vbr=3 vbr-quality=0 quality=0 vbr-min-bitrate=256 vbr-max-bitrate=320 lowpass-freq=25000 ath-lower=0 ! id3v2mux


    And its gives me 256kbps! I'm a big audio fan so 5MB audio files is fine by me. Taking it up to 320kbps would be nicer, but this will do. Thanks.

- id: 439
  author: Hamadi
  author_email: hamadi.mess@gmail.com
  author_url: http://www.cynapsys.de
  date: '2010-07-15 14:45:50 +0200'
  date_gmt: '2010-07-15 13:45:50 +0200'
  content: |
    Hi,


    gst-launch filesrc location=Bureau/test.ts ! mpegtsdemux name=demux program-number=12041 ! queue ! mpeg2dec ! ffmpegcolorspace ! xvimagesink demux. ! queue ! mad ! audioconvert ! audioresample ! alsasink 


    I like to play audio in mode stereo but audio is encoded in mono mode.


    someone help me to add a caps or any plugin to set audio mode?
    thanks

---
Countless times have I struggled with MP3 encoding under Linux. I seems for an eternity now. Every single time I had to fall back on the same #&curren;@?-ing console based ripping with `abcde`. And if it hadn't been that abcde is a great ripping tool, the pain that this problem have caused me wouldn't be describable.

Well now for the good news.... Sound-Juicer finally works (using Sound-Juicer 2.10 from Ubuntu Hoary)!
First I had to install the `gstreamer8.0-lame` package to encode audio to MP3 (gstreamer8.0-mad is needed for playback).

Then i ran `gnome-audio-profiles-properties` and created a new profile with the following values:

    Profile name: CD Quality, Lossy
    Profile Description: Test
    GStreamer Pipeline: audio/x-raw-int,rate=44100,channels=2 ! lame name=enc
    File Extension: mp3

Profile description and name is up to you. Dont worry, even though you have 2 profile with the same name i.e. "CD Quality, Lossy", Sound-Juicer will tell the difference between them because it also displays the extension.

I cant remember which package provided gnome-audio-profiles-properties.

**Update:** Glad to see this info actually helped people out there on the [Ubuntu forums][] :-D

[Ubuntu forums]: http://www.ubuntuforums.org/showthread.php?t=22010

