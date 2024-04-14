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
tags:
- Clojure
comments: false
---
I've been looking at Clojure now and then for a while... really wanna learn this stuff :)
The other day I needed to import a very large CSV file... and it seemed like a good problem to try solve in Clojure.

I'm using Leiningen and started with the following project setup.


project.clj:

``` clojure
(defproject bbr "1.0.0"
  :description "Import BBR data"
  :dependencies [[org.clojure/clojure "1.3.0"]
                 [org.clojure/clojure-contrib "1.2.0"]
                 [org.clojure/java.jdbc "0.0.6"]
                 [mysql/mysql-connector-java "5.1.6"]
                 [clojure-csv "2.0.0-alpha2"]])
```

core.clj:

``` clojure
(ns bbr.core
  (:require [clojure-csv.core :as csv]
            [clojure.java.jdbc :as sql])
  (:use [clojure.java.io :only [reader]]))

(def db {:classname "com.mysql.jdbc.Driver"
         :subprotocol "mysql"
         :subname "//localhost:3306/mydb"
         :user "root"})

(defn parse-row [row]
  (let [v (first (csv/parse-csv row :delimiter \;))]
    (zipmap [:kvhx :road :no :floor :door :zip_code :city] v)))

(defn parse-file
  [filename]
  (with-open [file (reader filename :encoding "ISO-8859-1")]
    (sql/with-connection db
      (doseq [line (line-seq file)]
        (let [record (parse-row line)]
          (sql/update-or-insert-values "bbr" ["kvhx=?" (:kvhx record)] record))))))
```

The above should be able to import VERY large CSV files into the database because it reads one line from the file at the time.

