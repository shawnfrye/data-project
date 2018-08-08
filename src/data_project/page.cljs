(ns data-project.page
  (:require [reagent.core :as r]
            [data-project.parse :as parse]
            [data-project.upload :as upload]
            [clojure.string :as str]))

(defonce app-state (r/atom { }))

;;delete me
(def text-data #js {:text   "llsouder"
                  :name   "thing"
                  :size   10
                  :height 3
                  :color  0xaa00aa
                  :x      0
                  :y      20
                  :z      10}
  )

(defn show-text
  "test to see if it is possible."
  []
  (js/add3dText text-data data-project.core/scene))

(defn remove-text
  "test to see if it is possible."
  []
  (js/remove text-data data-project.core/scene))

(defn button
  "Make a colorful button that will show the all the names which are in this
  color group."
  [color]
  [:input {:key color :type "button" :value "show" :onClick show-text :style {:background color}}])

(defn remove-button
  "test remove."
  []
  [:input {:type "button" :value "remove" :onClick remove-text }])
(defn show-button
  "test show."
  []
  [:input {:type "button" :value "show" :onClick show-text }])


;; -------------------------
;; Views
(defn home-page []
  [:div
   [:h2 "Data-Project GL"]
   [upload/input-component]
   [:div (map button
              (parse/list-colors (parse/parse-data @upload/file-data)))]
   [show-button]
   [remove-button]])
