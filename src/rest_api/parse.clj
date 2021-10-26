(ns rest-api.parse
  (:gen-class)
  (:require
   [clojure.data.json :as json]))

(defn remove-first-last-slash [uri]
  (-> uri
      (clojure.string/replace #"^/" "")
      (clojure.string/replace #"/$" "")))

(defn parse-uri [uri]
  (clojure.string/split
   (remove-first-last-slash uri)
   #"/"))

(defn parse-body [body]
  (if body
    (json/read-str (slurp body) :key-fn keyword)
    ""))
