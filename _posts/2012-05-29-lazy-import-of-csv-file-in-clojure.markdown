---
layout: post
status: publish
published: true
title: Lazy import of csv file in Clojure
author:
  display_name: Jacob Emcken
  login: je
  email: jacob@emcken.dk
  url: http://www.emcken.dk/
author_login: je
author_email: jacob@emcken.dk
author_url: http://www.emcken.dk/
wordpress_id: 351
wordpress_url: http://www.emcken.dk/weblog/?p=351
date: '2012-05-29 20:17:23 +0200'
date_gmt: '2012-05-29 19:17:23 +0200'
categories:
- Random hacks
tags: []
comments: []
---
<p>I've been looking at Clojure now and then for a while... really wanna learn this stuff :)<br />
The other day I needed to import a very large CSV file... and it seemed like a good problem to try solve in Clojure.</p>
<p>I'm using Leiningen and started with the following project setup.</p>
<p>project.clj:</p>
<p>    (defproject bbr "1.0.0"<br />
      :description "Import BBR data"<br />
      :dependencies [[org.clojure&#47;clojure "1.3.0"]<br />
                     [org.clojure&#47;clojure-contrib "1.2.0"]<br />
                     [org.clojure&#47;java.jdbc "0.0.6"]<br />
                     [mysql&#47;mysql-connector-java "5.1.6"]<br />
                     [clojure-csv "2.0.0-alpha2"]])</p>
<p>core.clj:</p>
<p>    (ns bbr.core<br />
      (:require [clojure-csv.core :as csv]<br />
                [clojure.java.jdbc :as sql])<br />
      (:use [clojure.java.io :only [reader]]))</p>
<p>    (def db {:classname "com.mysql.jdbc.Driver"<br />
             :subprotocol "mysql"<br />
             :subname "&#47;&#47;localhost:3306&#47;mydb"<br />
             :user "root"})</p>
<p>    (defn parse-row [row]<br />
      (let [v (first (csv&#47;parse-csv row :delimiter \;))]<br />
        (zipmap [:kvhx :road :no :floor :door :zip_code :city] v)))</p>
<p>    (defn parse-file<br />
      [filename]<br />
      (with-open [file (reader filename :encoding "ISO-8859-1")]<br />
        (sql&#47;with-connection db<br />
          (doseq [line (line-seq file)]<br />
            (let [record (parse-row line)]<br />
              (sql&#47;update-or-insert-values "bbr" ["kvhx=?" (:kvhx record)] record))))))</p>
<p>The above should be able to import VERY large CSV files into the database because it reads one line from the file at the time.</p>
