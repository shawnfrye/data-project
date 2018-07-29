(ns data-project.core )
 
(enable-console-print!)

(defn init []

  ;;First initiate the basic elements of a THREE scene
  (let [scene    (js/THREE.Scene.)
        view-angle 75
        aspect     1
        near       0.1
        far        1000
        p-camera (js/THREE.PerspectiveCamera.
                   view-angle aspect near far)
        box      (js/THREE.BoxGeometry.
                   200 200 200)
        mat      (js/THREE.MeshBasicMaterial.
                   (js-obj "color" 0x550055
                           "wireframe" true))
        mesh     (js/THREE.Mesh. box mat)
        renderer (js/THREE.WebGLRenderer.)
        controls (js/THREE.OrbitControls. p-camera (aget renderer "domElement" ))
        ]

    ;;Change the starting position of cube and camera
    (aset p-camera "name" "p-camera")
    (aset p-camera "position" "z" 50)
    (aset mesh "rotation" "x" 45)
    (aset mesh "rotation" "y" 0)
    (.setSize renderer 500 500)
    (aset controls "target" (THREE.Vector3. 0 0 0))

    ;;Add camera, mesh and box to scene and then that to DOM node.
    (.add scene p-camera)
    (.add scene mesh)
    (.appendChild js/document.body (.-domElement renderer))
    (js/addTheFloor scene)
    (js/addLights scene)
    (js/maketheskycube scene)
    (let [data #js {:text   "llsouder"
                     :size   10
                     :height 3
                     :x      10
                     :y      10
                     :z      10
                     }]
          (js/add3dText data scene))

    ;Kick off the animation loop updating
    (defn render []
      (aset mesh "rotation" "y" (+ 0.01 (.-y (.-rotation mesh))))
      (.render renderer scene p-camera))

    (defn animate []
      (.requestAnimationFrame js/window animate)
      (render))

    (animate)))

(init)

(println "This text is printed from src/data-p/core.cljs. Go ahead and edit it and see reloading in action.")

;; define your app data so that it doesn't get over-written on reload

(defonce app-state (atom {:text "Hello world!"}))


(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)
