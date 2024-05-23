#!/usr/bin/env bb
(ns optimize-img
  (:require [babashka.fs :as fs]
            [babashka.http-client :as http]
            [cheshire.core :as json]
            [clojure.java.io :as io]
            [clojure.string :as str]
            [clojure.tools.cli :as cli]))

(def cli-options
  [["-w" "--width WIDTH" "Width(s) - if multiple seperate with comma."]
   ["-i" "--height HEIGHT" "Height(s) - if multiple seperate with comma."]
   ["-m" "--resize-method METHOD" "Method can be either scale, fit or cover."]
   ["-t" "--type TYPE" "Convert to image type: image/webp, image/png or image/jpeg."]
   ["-k" "--api-key APIKEY" "Reads key from file if exists, falls back to use as actual API key."]
   ["-h" "--help"]])

(defn shrink
  [api-key img-file]
  (http/post "https://api.tinify.com/shrink"
             {:basic-auth ["api" api-key]
              :body img-file}))

(def valid-image-types
  #{"image/png" "image/webp" "image/jpeg" nil})

(defn pad [n coll]
  (take n (concat coll (repeat nil))))

(defn request-bodies
  "Construct a list of valid request bodies or throw an exception."
  [{:keys [resize-method type] :as options}]
  (let [width (some-> (:width options) (str/split #","))
        height (some-> (:height options) (str/split #","))]

    (when-not (contains? valid-image-types type)
      (throw (ex-info "Invalid argument: type" {})))

    (when (= "scale" resize-method)
      (when-not (or (and width (not height))
                    (and (not width) height))
        (throw (ex-info "Invalid argument: resize-method, requires either width or height" {}))))

    (when (contains? #{"fit" "cover"} resize-method)
      (when-not (and width height
                     (= (count width) (count height)))
        (throw (ex-info "Invalid argument: resize-method, requires width & height to match" {}))))

    (let [padding (max (count width) (count height))]
      (-> (map (fn [w h] {:resize (-> {:method resize-method}
                                      (conj (when w [:width (Integer/parseInt w)]))
                                      (conj (when h [:height (Integer/parseInt h)])))})
               (pad padding width)
               (pad padding height))
          (conj {}) ; always get optimized version of original size
          (->> (map #(conj % (when type [:convert {:type type}]))))))))

(comment
    (request-bodies {:width "1440,930", :resize-method "scale", :type "image/webp"})
)

(defn get-image-output
  "Takes an API key, an output URL from the TinyPNG shrink operation
   and a request body, optionally specifying resizing and type convertion.
   Returns a HTTP response with the output result.
   See: https://tinypng.com/developers/reference#resizing-images"
  [api-key url body]
  (http/post url
             {:basic-auth ["api" api-key]
              :headers {:content-type "application/json"}
              :as :stream
              :body (json/encode body)}))

(def image-extension
  {"image/jpeg" "jpg"
   "image/webp" "webp"
   "image/png" "png"})

(def variant-header
  {:width "image-width"
   :height "image-height"})

(defn save-output-response
  "Takes a base file name (without extension), and variants order (width, height) and the TinyPNG output response.
   Saves the image file from reponse body (InputStream) to a file."
  [base-name response]
  (let [variants (some-> (get-in response [:request :body]) ; only original HTTP request reveals if resizing was requested
                         (json/parse-string true)
                         :resize
                         (select-keys [:width :height])
                         keys
                         reverse) ; ensure width is always first when defined (width x height)
        ext (image-extension (get-in response [:headers "content-type"]))
        name-postfix (some->> variants ; only postfix resized file names with size
                              (map #(str "_" (get-in response [:headers (variant-header %)])))
                              (str/join ""))]

    (println "Saving" (str base-name name-postfix "." ext))
    (io/copy
     (:body response)
     (io/file (str base-name name-postfix "." ext)))))

(defn usage
  []
  (->> '("Usage: ./optimize_img.clj <img file> [<out path>] -k <keyfile|key> \\"
         "                          [-t <image type>] \\"
         "                          [-m <resize method>] [-w <widths>] [-h <heights>]"
         "Example: ./optimize_img.clj unoptimized.jpg out-dir -k tinypng_api.txt \\"
         "                            -t image/webp -m scale -w 1280,920")
      (str/join "\n")
      println))

(defn main
  [& args]
  (let [{:keys [options arguments _summary]} (cli/parse-opts args cli-options)
        [img-source-path out-path] arguments
        img-source (io/file img-source-path)
        api-key (when-let [key-file (:api-key options)]
                  (if (.exists (io/file key-file))
                    (slurp key-file)
                    key-file))]
    (when (:help options)
      (usage)
      (System/exit 0))

    (when-not (.exists img-source)
      (println "Invalid img source path:" (or img-source-path "<unspecified>"))
      (System/exit 2))

    (when-not api-key
      (println "Invalid API key" (:api-key options))
      (System/exit 2))

    (println "Optimizing" img-source-path)
    (let [[_ name ext] (->> (.getName img-source)
                            (re-find #"^(.+)(\.[^.]+)$"))
          destination (io/file (or out-path "."))
          base-name (str (.getPath destination) "/" name)
          url (-> (shrink api-key img-source) (get-in [:headers "location"]))]

      (when (= (.getCanonicalPath (.getParentFile img-source))
               (.getCanonicalPath destination))
        (println "Avoid overwrite by moving original to" (str base-name "_orig" ext))
        (fs/move img-source
                 (io/file (str base-name "_orig" ext))))

      (->> options
           (request-bodies)
           (map (partial get-image-output api-key url))
           (map (partial save-output-response base-name))
           doall))))

(apply main *command-line-args*)

(comment
  (shrink (slurp "tinypng_api.txt")
          "assets/img/pipeline_putting_things_in_the_cloud.png")

  )