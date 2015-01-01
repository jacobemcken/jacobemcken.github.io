---
layout: post
status: publish
published: true
title: Storing encrypted data in Amazon S3
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
wordpress_id: 332
wordpress_url: http://www.emcken.dk/weblog/?p=332
date: '2010-12-01 15:25:51 +0100'
date_gmt: '2010-12-01 14:25:51 +0100'
categories:
- Work
- Linux
tags:
- amazon s3
- encryption
- s3fs
- encfs
comments: false
---
I have a lot of digital photos which I would be very sad to loose. I was recommended [Amazon S3][1] as a cheap storage solution and even though it is possible to use https to transfer your data to S3 in a secure way the data itself isn't encryptet within Amazon. I'm looking for a solution where my photos of my son in the bathtub are secured from all prying eyes including the Amazon tech staff.

It is possible to implement encryption transparent on Linux with the following two modules:

  * [s3fs][2] - FUSE-based file system backed by Amazon S3
  * [encfs][3] - EncFS provides an encrypted filesystem in user-space

First you need to signup for Amazon S3 - remember you pay on for what you use.

Then [create a bucket][4], I used the Getting Stated Guide that Amazon provides. The following example will use the bucket name: `your.bucket`

Get an "Access key" from the AWS Management Console, I just used the one which was created upon AWS account creation.

Then I installed s3fs following the [instructions on the project website][5].

Put the "Access key" inside the file `.passwd-s3fs` in your home directory using the format `accessKeyId:secretAccessKey`. You can find more informaiton on the on [s3fs wiki page][6].

Now mount your S3 storage using:

    mkdir /media/s3
    s3fs your.bucket /media/s3

Now try create a file within you storage in the cloud ie.:

    touch /media/s3/test

After creation of the file check that you can see the file using the AWS Management Console. Permissions, timestamps etc. is stored in metadata in Amazon S3.

Now in order to apply a transparent encryption layer encfs needs to be installed. Using Ubuntu you do it like this:

    sudo aptitude install encfs

Now apply transparent encryption by mounting /media/s3 thorugh encfs like this:

    encfs /media/s3 /home/je/Pictures/Encrypted\ on\ S3

and follow the on screen setup. The setup process is only triggered the first time you mount a directory using encfs and it results in a XML filen with all your choices.
Don't delete the XML file (perhaps take a backup) and remember you encfs password.

Now try create a file within:

    /home/je/Pictures/Encrypted on S3

And verify that is is unreadable through the AWS Management Console and the directory:

    /media/s3

Thats it :)

To expose this to Windows clients then install Samba and share the directory:

    /home/je/Pictures/Encrypted on S3

NO WARRENTY:
I'm NOT an expert in encryption so I cannot guarantee that encfs is secure enough. Neither do I know how much overhead s3fs and encfs puts on top the actual data that you tranfer to S3.

[1]: http://aws.amazon.com/s3/
[2]: http://code.google.com/p/s3fs/
[3]: http://www.arg0.net/encfs
[4]: http://docs.amazonwebservices.com/AmazonS3/latest/gsg/
[5]: http://code.google.com/p/s3fs/wiki/InstallationNotes
[6]: http://code.google.com/p/s3fs/wiki/FuseOverAmazon
