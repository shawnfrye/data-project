(ns data-project.page
  (:require [reagent.core :as r]
            [data-project.color :as color]
            [data-project.dog :as dog]
            [data-project.parse :as parse]
            [data-project.scene :as scene]
            [data-project.upload :as upload]
            [clojure.string :as str]))

(enable-console-print!)

(defonce app-state (r/atom #{}))
(defonce bg-color (r/atom #{"0xCCC"}))

(defn show-text
  [color]
  (let [names (parse/get-names color (parse/parse-data @upload/file-data))]
    (doall
     (map
       #(scene/display-text % (color/name-to-hex color) (rand-int 100) (- (rand-int 100) 70)) names)))
  (reset! bg-color #{color})
  (swap! app-state conj color))

(defn remove-text
  [color]
  (let [names (parse/get-names color (parse/parse-data @upload/file-data))]
    (doall (map #(scene/remove-text % ) names)))
  (reset! bg-color #{"gray"})
  (swap! app-state disj color))

(defn toggle
  [color]
  (if (contains? @app-state color)
    (remove-text color)
    (show-text color)))

(defn button
  "Make a colorful button that will show the all the names which are in this
  color group."
  [color]
  [:input {:key color :type "button" :value "show" :onClick #(toggle color) :style {:background color}}])

(defn dog-button
  []
  [:input {:key "dog" :type "button" :value "dog?" :onClick dog/update!}])
  

;; -------------------------
;; Views
(defn home-page []
  [:div {:style {:background-color @bg-color}}
   [:h2 "Data-Project GL"]
   [upload/input-component]
   [dog-button]
   (when (not-empty @dog/image) [:img {:src @dog/image}])
   [:div (map button
              (parse/list-colors (parse/parse-data @upload/file-data)))]])
