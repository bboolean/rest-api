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
    (catch Exception e
      (println (str "    " (.getMessage e)))
      (str "There was an error. Printing it out to the log."))))

