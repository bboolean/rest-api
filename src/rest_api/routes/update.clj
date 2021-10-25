(ns rest-api.routes.update
  (:import org.bson.types.ObjectId)
  (:require
   [monger.collection :as mc]))

(defn update [db body uri]
  (try
    (do
      (mc/update-by-id
       db
       "books"
       (ObjectId. (second uri))
       body)
      {:updated true})
    {:error 1}))
