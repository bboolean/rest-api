(ns rest-api.spec
  (:require
   [clojure.spec.alpha :as s]))

(s/def :rest-api/title string?)

(s/def :rest-api/year #(and (< %1 3000) (> %1 0)))

(s/def :rest-api/rating #(and (integer? %1) (<= %1 5) (>= %1 0)))

(s/def :rest-api/books
  (s/keys :req-un [:rest-api/title
                   :rest-api/year
                   :rest-api/rating]))
