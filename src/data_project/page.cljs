(ns data-project.page
  (:require [reagent.core :as r]
            [data-project.parse :as parse]
            [data-project.upload :as upload]
            [clojure.string :as str]))

(defonce app-state (r/atom " "))

(defn button
  "Make a colorful button that will show the all the names which are in this
  color group."
  [color]
  [:input {:type "button" :value "show" :onclick str :style {:background color}}])

;; -------------------------
;; Views
(defn home-page []
  [:div
   [:h2 "Data-Project GL"]
   [upload/input-component]
   [:div (map button
              (parse/list-colors (parse/parse-data @upload/file-data)))]])
