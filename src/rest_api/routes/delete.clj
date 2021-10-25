(ns rest-api.routes.delete
  (:import org.bson.types.ObjectId)
  (:require
   [monger.collection :as mc]))

 (defn delete [db body uri]
  (try
    (do
      (mc/remove-by-id db "books" (ObjectId. (second uri)))
      {:deleted true})
    {:error 1})) 
