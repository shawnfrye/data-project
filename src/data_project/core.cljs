(ns data-project.core
  (:require [reagent.core :refer [render atom]]
            [data-project.page :as page]))

(enable-console-print!)

(defonce scene (js/THREE.Scene.))
(defonce renderer
         (let [renderer (js/THREE.WebGLRenderer.)]
           (.setSize renderer 500 500)
           renderer))

(defonce p-camera (js/THREE.PerspectiveCamera.
                    75 1 0.1 1000))

(defn init []

  ;;First initiate the basic elements of a THREE scene
  (let [controls (js/THREE.OrbitControls. p-camera (aget renderer "domElement"))]

    ;;Change the starting position of cube and camera
    (aset p-camera "name" "p-camera")
    (aset p-camera "position" "z" 50)
    (aset controls "target" (THREE.Vector3. 0 0 0))

    ;;Add camera, mesh and box to scene and then that to DOM node.
    (.add scene p-camera)
    (.appendChild js/document.body (.-domElement renderer))
    ;Kick off the animation loop updating
    (defn render-3d []
      (.render renderer scene p-camera))

    (defn animate []
      (.requestAnimationFrame js/window animate)
      (render-3d))
    (animate)))

(defn setup
  []
  (js/addTheFloor scene)
  (js/addLights scene))

(let [data #js {:text   "test"
                :name   "otherthing"
                :size   10
                :height 3
                :color  0xaa00aa
                :x      0
                :y      10
                :z      10}]

    (js/add3dText data scene))

;; define your app data so that it doesn't get over-written on reload
(defonce startup-state
         (do
           (init)
           (setup)
           :done))



(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
  )

(render [page/home-page] (.getElementById js/document "app"))
