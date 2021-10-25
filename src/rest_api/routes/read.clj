(ns rest-api.routes.read
  (:import org.bson.types.ObjectId)
  (:require
   [monger.collection :as mc]))

(defn aggregate [db]
  (mc/aggregate db
                "books"
                [{:$match {}}]
                :cursor {:batch-size 0}))

(defn read [db body uri]
  (map
   (fn [item] (assoc item :_id (str (item :_id))))
   (aggregate db)))


