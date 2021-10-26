(ns rest-api.validation)

(defn uri-invalid? [uri]
  (not= "books" (first uri)))

(defn no-_id? [uri method]
  (and
   (contains? #{:put :delete} method)
   (not (string? (second uri)))))

(defn _id-invalid? [uri method]
  (and
   (or
    (contains? #{:put :delete} method)
    (and
     (= :get method)
     (string? (second uri))))
   (not (re-matches #"^[0-9a-fA-F]{24}$" (str (second uri))))))
