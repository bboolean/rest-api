(ns rest-api.routes.create
  (:import org.bson.types.ObjectId)
  (:require
   [monger.collection :as mc]
   [clojure.spec.alpha :as s]))

(s/def :rest-api/books
  (s/keys :req-un [:rest-api/name]))

(s/valid? :rest-api/books {:name 23})

(defn create [db body]
  (try
    (do
      (mc/insert db "books" body)
      {:created true})
    {:error 1}))
