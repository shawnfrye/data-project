(ns data-project.core
  (:require [reagent.core :refer [render atom]]
            ;;Including this causes the scene-setup.
            [data-project.scene :as scene]
            [data-project.page :as page]))

(enable-console-print!)

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
  )

(render [page/home-page] (.getElementById js/document "app"))
