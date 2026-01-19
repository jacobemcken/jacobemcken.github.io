---
layout: post
status: publish
published: true
title: Encrypted USB drive in Ubuntu
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
wordpress_id: 164
wordpress_url: http://emcken.dk/wp/archives/164-encrypted-usb-drive-in-ubuntu.html
date: '2006-10-28 17:24:09 +0200'
date_gmt: '2006-10-28 17:24:09 +0200'
categories:
- Ubuntu
tags: []
comments:
- id: 51
  author: Kim Tholstorf
  author_email: kim.tholstorf@gmail.com
  author_url: ''
  date: '2006-10-28 22:16:48 +0200'
  date_gmt: '2006-10-28 21:16:48 +0200'
  content: "Works like a charm :-)\r\n\r\n\r\nA tip:\r\n\r\nBefore setting up LUKS
    on the drive overwrite it with random data in order to slow down an eventually
    attack on the encryption. The vise zealot would also perform a bad blocks scan
    to make sure the hard drive is not going to die too soon ;-) Do this in a single
    command:\r\n\r\n\r\n      badblocks -c 10240 -s -w -t random -v /dev/sda\r\n\r\n\r\nParanoid
    beyond regular self toture? Willing to spend one or two days on the extra security?
    Do this:\r\n\r\n\r\n      dd if=/dev/urandom of=/dev/sda\r\n\r\n\r\nWarning:
    This wil definitely make your cpu and harddisk  temprature rise and be in the
    high range the whole time!"
- id: 58
  author: Johan
  author_email: ''
  author_url: ''
  date: '2006-11-12 02:10:51 +0100'
  date_gmt: '2006-11-12 01:10:51 +0100'
  content: "I dunno.  \r\n\r\nFlash drives have a limited number of read-write cycles.
    \ Making a filesystem (for some filesystems; others may be different), performs
    many repeated writes to superblock pages. \r\n\r\nin some cases you can wear out
    a flashcard just by making a filesystem on it. \r\n\r\nIt's better to loopback-mount
    an image of the proper size, create the filesystem on that, and then just write
    the ready made image to the disk."
- id: 81
  author: Thomas
  author_email: Thomas.Maier@uni-kassel.de
  author_url: ''
  date: '2006-11-29 17:17:38 +0100'
  date_gmt: '2006-11-29 16:17:38 +0100'
  content: "Have you ever tried using ntfs on a LUKS encrypted usb key?  Setting it
    up works fine.  When plugging in the key I get asked for the password.  It even
    gets mounted, unfortunately as ntfs instead of ntfs-3g (which I have installed,
    non-LUKS volumes work fine, the same key just formatted with mkntfs gets mounted
    as ntfs-3g).  Any hints on where I could say \"prefer ntfs-3g over ntfs\"?\r\n\r\nI
    have Ubuntu Edgy Eft installed with packages ntfs-3g and pmount from \r\n\r\ndeb
    http://flomertens.keo.in/ubuntu/ dapper main main-all"
- id: 121
  author: Fil
  author_email: ''
  author_url: ''
  date: '2007-03-28 01:21:33 +0200'
  date_gmt: '2007-03-28 00:21:33 +0200'
  content: I forget the password any way to recover it??? I thought it was a certain
    pass but it says incorrect
- id: 122
  author: Jacob Emcken
  author_email: jacob@emcken.dk
  author_url: ''
  date: '2007-03-30 11:56:11 +0200'
  date_gmt: '2007-03-30 10:56:11 +0200'
  content: "If you forgot your password I'm sorry to say that you are fucked. The
    password is the key to decrypt it. There is no way around it.\r\n\r\nI hope you
    will remember it... good luck mate :)"
- id: 151
  author: Nate Bounds
  author_email: n8bounds@gmail.com
  author_url: ''
  date: '2008-01-23 16:36:51 +0100'
  date_gmt: '2008-01-23 15:36:51 +0100'
  content: Thanks, Jacob. Worked like a charm. And thanks, Kim, I used your badblocks
    command.
- id: 157
  author: smiki
  author_email: micouk@gmail.com
  author_url: ''
  date: '2008-01-11 14:29:54 +0100'
  date_gmt: '2008-01-11 13:29:54 +0100'
  content: "How the hell does ubuntu know that there is an encrypted volume on the
    key. It should only look like an unformated partition with complete random data.\r\n\r\nThere
    is a BIG difference between having a usbdrive with random data (=nothing on it)
    and an usbdrive where i can tell that there IS an encrypted partition.\r\n\r\nCan
    you give me a clue? My googling was unsuccesfull\r\n\r\nthanx"
- id: 160
  author: Jacob Emcken
  author_email: jacob@emcken.dk
  author_url: http://www.emcken.dk/
  date: '2008-02-27 22:32:52 +0100'
  date_gmt: '2008-02-27 21:32:52 +0100'
  content: Sorry I don't know :(
- id: 165
  author: Jon Garvin
  author_email: jon@garvinz.com
  author_url: http://5valleys.com
  date: '2008-03-21 04:40:53 +0100'
  date_gmt: '2008-03-21 03:40:53 +0100'
  content: Great post!  I just got a new case in the mail that converts an internal
    SATA harddrive into an enormous external USB.  Now, thanks to the instructions
    above, it's encrypted too.  Works like a charm on my Gutsy laptop.
- id: 167
  author: max stirner
  author_email: ''
  author_url: ''
  date: '2008-04-16 00:48:09 +0200'
  date_gmt: '2008-04-15 23:48:09 +0200'
  content: "IMO the luks unified header is detected, the idea is to have a standard
    way of GUI opening encrypted partitions etc.\r\n\r\nThis does make the partition
    transparent to some extent I guess, thus reducing security; nonetheless this method
    is deemed secure as far as I know."
- id: 176
  author: Kalle
  author_email: kalle@creativecommons.se
  author_url: ''
  date: '2008-07-07 22:10:41 +0200'
  date_gmt: '2008-07-07 21:10:41 +0200'
  content: "I've ordered a new USB hard disk that I plan to encrypt and now I'm experimenting
    with a USB stick. Everything seems to work but there is no magical password prompting
    when I insert the newly formated stick.\r\n\r\nI'm running Kubuntu 7.04. Anyone
    know if I need to install something more than what is mentioned above\r\n\r\nAnd
    how do I manually mount the encrypted drive?"
- id: 178
  author: Jacob Emcken
  author_email: jacob@emcken.dk
  author_url: http://www.emcken.dk/weblog/
  date: '2008-08-24 11:34:54 +0200'
  date_gmt: '2008-08-24 10:34:54 +0200'
  content: "Hi Kalle\r\n\r\nI dont know how this is supposed to work in KDE but if
    you wanna do this manually I guess it would look something like this:\r\n\r\nFirst
    attach the encrypted partition:\r\n\r\n    $ sudo cryptsetup luksOpen /dev/sda1
    sda1\r\n    Enter LUKS passphrase:\r\n    key slot 0 unlocked\r\n    Command successful.\r\n\r\n\r\nNow
    mount the encryptet filesystem:\r\n\r\n    sudo mount /dev/mapper/sda1
    /mnt\r\n\r\n\r\nWhen you are done unmount it again:\r\n\r\n    sudo umount
    /mnt\r\n\r\nand remove the tempoary device mapped to the encrypted partition:\r\n\r\n
    \   sudo cryptsetup luksClose sda1"
- id: 187
  author: Sunadrad
  author_email: sunadrad@gmail.com
  author_url: ''
  date: '2008-12-02 15:41:37 +0100'
  date_gmt: '2008-12-02 14:41:37 +0100'
  content: "I just set up an external USB hard drive with this guide, and it worked
    great. \r\n\r\nOnly one thing - when I now connect the USB drive, i get prompted
    for a password, but the drive doesn't get mounted.\r\n\r\nA /dev/mapper
    entry is set up:\r\n<pre><code>\r\nls -l /dev/mapper\r\ntotal 0\r\ncrw-rw----
    1 root root  10, 63 2008-12-01 11:40 control\r\nbrw-rw---- 1 root disk 254,  0
    2008-12-02 09:07 luks_crypto_4e09dc8f-e411-4a34-b54c-326e7104a8ed\r\n</code></pre>\r\n\r\nany
    suggestions on how to get it to be mounted automatically?  there's nothing useful
    in /var/log/messages.  i suppose i can add an entry to /etc/fstab,
    but that still won't make it mount automatically when it's connected.  i'm running
    ubuntu gutsy.  thanks,"
- id: 195
  author: Jacob Emcken
  author_email: jacob@emcken.dk
  author_url: http://www.emcken.dk/
  date: '2009-02-05 19:51:50 +0100'
  date_gmt: '2009-02-05 18:51:50 +0100'
  content: "I'm sorry I have not idea why your disk won't mount automatically :(\r\n\r\nI've
    tried Ubuntu choking on the disk a few times but after a reboot everything went
    back to normal. But these case are very rare.\r\n\r\nWhich Ubuntu release are
    you running?"
- id: 304
  author: How to create an encrypted USB drive?
  author_email: ''
  author_url: http://rp.ubuntusolutions.org/2009/03/how-to-create-encrypted-usb-drive.html
  date: '2009-04-10 03:33:40 +0200'
  date_gmt: '2009-04-10 02:33:40 +0200'
  content: '[...] Encrypted USB drive in Ubuntu [...]'
- id: 305
  author: Aj
  author_email: go@gogo.com
  author_url: ''
  date: '2009-04-10 23:22:36 +0200'
  date_gmt: '2009-04-10 22:22:36 +0200'
  content: Can this encrypted drive you create, can it be used in windows as well
    or just in ubuntu?
- id: 306
  author: Jacob Emcken
  author_email: jacob@emcken.dk
  author_url: http://www.emcken.dk/
  date: '2009-04-11 09:56:48 +0200'
  date_gmt: '2009-04-11 08:56:48 +0200'
  content: Perhaps... never tried. But I'm sure it won't work out of the box.
- id: 309
  author: david
  author_email: david@yahoo.com
  author_url: ''
  date: '2009-04-23 17:18:04 +0200'
  date_gmt: '2009-04-23 16:18:04 +0200'
  content: "Anyway of changing the default path it mounts the encrypted drive to,
    because now it mounts the drive to /media/disk which I need for something
    else.\r\n\r\nOr maybe is there a way of disabling the auto-mount pass-phrase prompt,
    'cause then I could manually mount it to a better path."
- id: 311
  author: Jacob Emcken
  author_email: jacob@emcken.dk
  author_url: http://www.emcken.dk/
  date: '2009-04-24 14:14:58 +0200'
  date_gmt: '2009-04-24 13:14:58 +0200'
  content: "The mount point Ubuntu chooses is based on the label of the filesystem.
    Which means that if the label is ie. \"WD 1TB\" it will be mounted in /media/WD
    1TB\r\n\r\nSince the label cannot be read before the partition is actually decrypted
    I'm not sure Ubuntu can figure out to mount encrypted partitions using labels.\r\n\r\nI'm
    not aware of anything you can do to disable auto mounting of encrypted partitions."
- id: 315
  author: Malignus
  author_email: wootchismo@yahoo.com
  author_url: ''
  date: '2009-05-08 19:36:28 +0200'
  date_gmt: '2009-05-08 18:36:28 +0200'
  content: "n00b-ish question:\r\n\r\nI ran through all the steps which all cleared
    without compunction, but when I re-inserted the drive, and it prompted me for
    my passphrase (which I provided), it seemingly mounted but is inaccessible to
    me.  It populates as a device but generates the error \"Unable eto set up crypto
    device Error org.freedesktop.Hal.Device.Volume.Crypto.SetupError  /dev/sdc
    is already setup?\".  What have I done wrong?"
- id: 317
  author: Jacob Emcken
  author_email: jacob@emcken.dk
  author_url: http://www.emcken.dk/
  date: '2009-05-11 20:54:09 +0200'
  date_gmt: '2009-05-11 19:54:09 +0200'
  content: |-
    @Malignus

    If you done all the steps I must admit I don't know what you could have done wrong.
    Does the error persist even after a reboot?
- id: 333
  author: Preben
  author_email: stumpaki@yahoo.co.yk
  author_url: ''
  date: '2009-08-27 11:47:10 +0200'
  date_gmt: '2009-08-27 10:47:10 +0200'
  content: |
    Just a note. If you encrypt a USB stick you should use ext2 and not ext3 as the latter is very slow on sticks due to the journalling system.

- id: 340
  author: A. James Lewis (netlore) 's status on Monday, 19-Oct-09 14:51:21 UTC - Identi.ca
  author_email: ''
  author_url: http://identi.ca/notice/12398040
  date: '2009-10-19 15:51:39 +0200'
  date_gmt: '2009-10-19 14:51:39 +0200'
  content: |
    [...]  http://emcken.dk/weblog/archives/164-encrypted-usb-drive-in-ubuntu.html        a few seconds ago  from  Gwibber   in context [...]

- id: 380
  author: Free Your Inner Penguin &raquo; Blog Archive &raquo; Drive Encryption
  author_email: ''
  author_url: http://tranquilpenguin.com/wordpress/?p=221
  date: '2010-03-27 20:38:48 +0100'
  date_gmt: '2010-03-27 19:38:48 +0100'
  content: |
    [...] Encrypted USB drive in Ubuntu Posted by Jacob Emcken [...]

- id: 421
  author: Klass Pappa
  author_email: klasspappa@hotmail.com
  author_url: ''
  date: '2010-06-11 21:45:23 +0200'
  date_gmt: '2010-06-11 20:45:23 +0200'
  content: |
    I tryed this, everything is ok.
    Exept I cant copy files to the drive, Access denied!


    I run Ubuntu 9.04

- id: 426
  author: Chris
  author_email: chrisspen@gmail.com
  author_url: http://www.chrisspen.com/blog
  date: '2010-06-23 16:32:00 +0200'
  date_gmt: '2010-06-23 15:32:00 +0200'
  content: |
    Great guide. Your instructions worked nearly perfectly. The only  problem I ran into was that the mounted drive had root-only permissions, which is fixed with:
    sudo chown -R myuser:myuser /dev/usbdevicemountpoint


    After that, Gnome's default mount icon was fully accessible.

- id: 436
  author: Ubuntu Storage Medium Encryption Script | chrisspenblog
  author_email: ''
  author_url: http://www.chrisspen.com/blog/ubuntu-storage-medium-encryption-script.html
  date: '2010-07-08 23:44:03 +0200'
  date_gmt: '2010-07-08 22:44:03 +0200'
  content: |
    [...] flash drives, to protect personal data in the event they&#8217;re lost or stolen, and I found a few good write-ups on the subject using tools available in Ubuntu. Although the current distribution of [...]

- id: 453
  author: Herr Furher
  author_email: herrfurher@gmail.com
  author_url: ''
  date: '2010-09-28 12:03:55 +0200'
  date_gmt: '2010-09-28 11:03:55 +0200'
  content: |
    Very nice post still works on Ubuntu 10.04 x64 system. Thanks

- id: 552
  author: TechLW
  author_email: kyonke42@hotmail.com
  author_url: http://www.TechLW.com
  date: '2012-02-06 19:42:44 +0100'
  date_gmt: '2012-02-06 18:42:44 +0100'
  content: |
    Really nice,
    very cool sharing.
    better on Ubuntu 10.04 lts
redirect_from:
  - /weblog/archives/164-Encrypted-USB-drive-in-Ubuntu.html
  - /weblog/archives/164-encrypted-usb-drive-in-ubuntu.html
---
Today I went to the [Linuxforum BOF day][1] where I attended a session about encrypting your personal files. This made me remember [a post][2] read some time ago (check out the screen cast). I guessed that this functionality would be in Ubuntu Edgy by now so I just went ahead and tried to make my USB pen drive encrypted.

This is how I did it:

1.  First install the needed software

        sudo apt-get install cryptsetup

2.  Make sure your USB disk isn't mounted. Then partition the USB pendrive
    the way you want it, if it isn't already partitioned (I made one big
    partition on mine `/dev/sda1`).
    **Note:** Don't mount the disk afterwards!
3.  If you havn't rebooted your computer since you installed the `cryptsetup` package,
    you might have to load the device mapper crypt module manually:

        sudo modprobe dm-crypt

4.  Now make the partition encrypted:

        $ sudo cryptsetup --verbose --verify-passphrase luksFormat /dev/sda1

        WARNING!
        ========
        This will overwrite data on /dev/sda1 irrevocably.

        Are you sure? (Type uppercase yes): YES
        Enter LUKS passphrase:
        Verify passphrase:
        Command successful.

    If you get the error:

        Failed to setup dm-crypt key mapping.
        Check kernel for support for the aes-cbc-essiv:sha256 cipher spec and verify that /dev/sda1 contains at least 133 sectors.

    Make sure that the disk isn't mounted. And make sure you are using the right device. You can use `dmesg` to check which device the disk have been assigned. You might also wanna check that the the module `dm-crypt` is loaded (`lsmod | grep dm`).

5.  Now attach the encrypted partition.:

        $ sudo cryptsetup luksOpen /dev/sda1 sda1
        Enter LUKS passphrase:
        key slot 0 unlocked
        Command successful.

6.  Now create a filesystem on the new encryptet device:

        sudo mkfs.ext3 /dev/mapper/sda1

7.  Remove the tempoary device mapped to the encrypted partition:

        sudo cryptsetup luksClose sda1

8.  Now remove the your usbdisk from the USB plug, and reinsert it and Ubuntu should find it and ask for the passphrase.

<img width='441' height='171' style="border: 0px;padding-left: 5px;padding-right: 5px" src="/public/media/Screenshot-Encryptedvolumedetected.png" alt="Password prompt upon encrypted media detected." />

**Update:** I tried to insert my USB pen into a Ubuntu Dapper (which this guide also would work on I guess). I just thought it was cool that is atcually told me which package it needed to for it to work:

<img width='425' height='151' style="border: 0px;padding-left: 5px;padding-right: 5px" src="/public/media/Encrypteddevicesnotsupported.png" alt="Notice upon encrypted media detected with missing dependencies." />

[1]: http://bof.linuxforum.dk/2006/
[2]: http://blog.fubar.dk/?p=64

