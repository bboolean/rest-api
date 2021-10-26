(ns rest-api.routes.create
  (:import org.bson.types.ObjectId)
  (:require
   [monger.collection :as mc]
   [clojure.spec.alpha :as s]))

(s/def :rest-api/title string?)

(s/def :rest-api/year #(and (< %1 3000) (> %1 0)))

(s/def :rest-api/rating #(and (integer? %1) (<= %1 5) (>= %1 0)))

(s/def :rest-api/books
  (s/keys :req-un [:rest-api/title
                   :rest-api/year
                   :rest-api/rating]))

(defn create [db body uri]
  (try
    (if (s/valid? :rest-api/books body)
      (do (mc/insert db "books" body)
          {:created true})
      {:error "The input does not meet the spec: {\"title\": \"String\", \"year\": \"Number: >0 <3000\", \"rating\": \"Number: 1, 2, 3, 4, or 5\"}"})
    (catch Exception e
      (println (str "    " (.getMessage e)))
      {:error (str "There was an error. Printing it out to the log.")})))
