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
comments: []
---
<p>I have a lot of digital photos which I would be very sad to loose. I was recommended [Amazon S3][1] as a cheap storage solution and even though it is possible to use https to transfer your data to S3 in a secure way the data itself isn't encryptet within Amazon. I'm looking for a solution where my photos of my son in the bathtub are secured from all prying eyes including the Amazon tech staff.</p>
<p>It is possible to implement encryption transparent on Linux with the following two modules:</p>
<p>  * [s3fs][2] - FUSE-based file system backed by Amazon S3<br />
  * [encfs][3] - EncFS provides an encrypted filesystem in user-space</p>
<p>First you need to signup for Amazon S3 - remember you pay on for what you use.</p>
<p>Then [create a bucket][4], I used the Getting Stated Guide that Amazon provides. The following example will use the bucket name: `your.bucket`</p>
<p>Get an "Access key" from the AWS Management Console, I just used the one which was created upon AWS account creation.</p>
<p>Then I installed s3fs following the [instructions on the project website][5].</p>
<p>Put the "Access key" inside the file `.passwd-s3fs` in your home directory using the format `accessKeyId:secretAccessKey`. You can find more informaiton on the on [s3fs wiki page][6].</p>
<p>Now mount your S3 storage using:</p>
<p>    mkdir &#47;media&#47;s3<br />
    s3fs your.bucket &#47;media&#47;s3</p>
<p>Now try create a file within you storage in the cloud ie.:</p>
<p>    touch &#47;media&#47;s3&#47;test</p>
<p>After creation of the file check that you can see the file using the AWS Management Console. Permissions, timestamps etc. is stored in metadata in Amazon S3.</p>
<p>Now in order to apply a transparent encryption layer encfs needs to be installed. Using Ubuntu you do it like this:</p>
<p>    sudo aptitude install encfs</p>
<p>Now apply transparent encryption by mounting &#47;media&#47;s3 thorugh encfs like this:</p>
<p>    encfs &#47;media&#47;s3 &#47;home&#47;je&#47;Pictures&#47;Encrypted\ on\ S3</p>
<p>and follow the on screen setup. The setup process is only triggered the first time you mount a directory using encfs and it results in a XML filen with all your choices.<br />
Don't delete the XML file (perhaps take a backup) and remember you encfs password.</p>
<p>Now try create a file within:</p>
<p>    &#47;home&#47;je&#47;Pictures&#47;Encrypted on S3</p>
<p>And verify that is is unreadable through the AWS Management Console and the directory:</p>
<p>    &#47;media&#47;s3</p>
<p>Thats it :)</p>
<p>To expose this to Windows clients then install Samba and share the directory:</p>
<p>    &#47;home&#47;je&#47;Pictures&#47;Encrypted on S3</p>
<p>NO WARRENTY:<br />
I'm NOT an expert in encryption so I cannot guarantee that encfs is secure enough. Neither do I know how much overhead s3fs and encfs puts on top the actual data that you tranfer to S3. </p>
<p>[1]: http:&#47;&#47;aws.amazon.com&#47;s3&#47;<br />
[2]: http:&#47;&#47;code.google.com&#47;p&#47;s3fs&#47;<br />
[3]: http:&#47;&#47;www.arg0.net&#47;encfs<br />
[4]: http:&#47;&#47;docs.amazonwebservices.com&#47;AmazonS3&#47;latest&#47;gsg&#47;<br />
[5]: http:&#47;&#47;code.google.com&#47;p&#47;s3fs&#47;wiki&#47;InstallationNotes<br />
[6]: http:&#47;&#47;code.google.com&#47;p&#47;s3fs&#47;wiki&#47;FuseOverAmazon</p>
