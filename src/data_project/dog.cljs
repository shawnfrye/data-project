(ns data-project.dog
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [cljs-http.client :as http]
            [cljs.core.async :refer [<!]]))

(defn example-1
  "given in the library documentation."
  []
  (go (let [response (<! (http/get "https://api.github.com/users"
                                   {:with-credentials? false
                                    :query-params {"since" 135}}))]
        (prn (:status response))
        (prn (map :login (:body response))))))

(defn example-2
  []
  (go (let [response (<! (http/get "https://dog.ceo/api/breeds/image/random"
                                   {:with-credentials? false
                                    :query-params {"since" 135}}))]
        (println (:message (:body response)))
        (println (:status (:body response))))))

(defn example-3
  []
  (go (let [response (<! (http/get "https://api.thedogapi.com/v1/images/search"
                                   {:with-credentials? false}))]
        (println (:body response))
        (println (map :url (:body response))))))
