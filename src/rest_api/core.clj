(ns rest-api.core
  (:gen-class)
  (:require
   [clojure.java.io :as io]
   [clojure.data.json :as json]
   [org.httpkit.server :refer [run-server]]
   [monger.core :as mg]
   [monger.collection :as mc]
   [monger.conversion :refer [from-db-object]]
   [rest-api.routes.create]
   [rest-api.routes.read]
   [rest-api.routes.update]
   [rest-api.routes.delete]))

(def conn (mg/connect))

(def db (mg/get-db conn "local"))

(def coll "documents")

(defn routes [& {:keys [db method uri body]}]
  (case method
    :post (rest-api.routes.create/create db body uri)
    :delete (rest-api.routes.delete/delete db body uri)
    :get (rest-api.routes.read/read db "" uri)
    :put (rest-api.routes.update/update db body uri)
    "Route not found"))

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

(defn app [req]
  (println (str "### " (req :request-method) " " (req :uri)))
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body (json/write-str (routes
                          :db db
                          :method (req :request-method)
                          :uri (parse-uri (req :uri))
                          :body (parse-body (req :body))))})

(defonce server (atom nil))

(defn stop-server []
  (when-not (nil? @server)
    (@server :timeout 100)
    (reset! server nil)))

(defn -main [& args]
  (reset! server (run-server #'app {:port 8080})))
