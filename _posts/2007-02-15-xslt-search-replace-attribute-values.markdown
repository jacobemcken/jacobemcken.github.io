---
layout: post
status: publish
published: true
title: 'XSLT: Search replace attribute values'
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
wordpress_id: 184
wordpress_url: http://emcken.dk/wp/archives/184-xslt-search-replace-attribute-values.html
date: '2007-02-15 10:37:00 +0100'
date_gmt: '2007-02-15 10:37:00 +0100'
categories:
- Random hacks
- Work
- Linux
tags: []
comments:
- id: 169
  author: Francis Mathew
  author_email: fmathewitd@hotmail.com
  author_url: ''
  date: '2008-05-14 17:02:11 +0200'
  date_gmt: '2008-05-14 16:02:11 +0200'
  content: "I'm new to XSLT.\r\nHow do I run the same from a Java program?  \r\nI
    have the following requirements\r\n1)  I have to change attribute values using
    Java - but the resulting file should retain the same format, comments etc. but
    the values should change.\r\n2) Get a report of (atleast the no of instance) changes
    in a file \r\n3) Backup the file if there is a change (e.g. with a new extension
    like .replacedByXXX)"
- id: 400
  author: J&oslash;rn
  author_email: biz@tdcspace.dk
  author_url: ''
  date: '2010-04-22 08:55:49 +0200'
  date_gmt: '2010-04-22 07:55:49 +0200'
  content: |
    I have a similar problem, but I can't seem to find you xslt code in the post.

- id: 420
  author: Jacob Emcken
  author_email: jacob@emcken.dk
  author_url: http://www.emcken.dk/
  date: '2010-06-11 08:50:31 +0200'
  date_gmt: '2010-06-11 07:50:31 +0200'
  content: |
    I'm sorry about that.


    Wordpress keeps f****** me over when I want to display html and xml.
    I've updated the post so you should be able to figure out whats happening :)

- id: 829
  author: r_a_a
  author_email: ask@me.com
  author_url: ''
  date: '2013-12-30 10:27:09 +0100'
  date_gmt: '2013-12-30 09:27:09 +0100'
  content: |
    Thanks! Helped me a lot!

---
Yesterday I needed an xsl transformation which could replace a specific attribute value in a xml file and keep the rest intact. The following code is put together by pieces I found around the net (copy-paste FTW :) ):

    <?xml version="1.0" encoding="UTF-8"?>
    <xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
        <xsl:param name="attribute"/>
        <xsl:param name="oldvalue"/>
        <xsl:param name="newvalue"/>

        <xsl:template match="node()|@*">
        <xsl:copy>
            <xsl:apply-templates select="@*|node()"/>
        </xsl:copy>
        </xsl:template>

        <!-- This is a generic search replace of attribute values -->
        <xsl:template match="@*" priority="10">
            <xsl:attribute name="{name()}">
                <xsl:choose>
                    <xsl:when test="(name()=$attribute) and (. = $oldvalue)"><xsl:value-of select="$newvalue"/></xsl:when>
                    <xsl:otherwise><xsl:value-of select="."/></xsl:otherwise>
                </xsl:choose>
            </xsl:attribute>
        </xsl:template>
    </xsl:stylesheet>

Lets say that the above code is saved in a file called `attribute_replace.xslt`. Now with `xsltproc` you would be able to replace the vaule `5011` with `5015` in all attributes called `port`:

    xsltproc --stringparam attribute port --stringparam oldvalue 5011 --stringparam newvalue 5015 attribute_replace.xslt server_config.xml > new_server_config.xml

I used this to manupulate with some JBoss configuration files.

