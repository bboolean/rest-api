(ns rest-api.routes.read
  (:import org.bson.types.ObjectId)
  (:require
   [monger.collection :as mc]))

(defn find-fn [db uri]
  (mc/find-maps db
                "books"
                (if
                 (second uri)
                  {:_id (ObjectId. (second uri))}
                  {})))

(defn read [db _ uri]
  (try
    (map
     (fn [item] (assoc item :_id (str (item :_id))))
     (find-fn db uri))
    (catch Exception e
      (println (str "    " (.getMessage e)))
      (str "There was an error. Printing it out to the log."))))
