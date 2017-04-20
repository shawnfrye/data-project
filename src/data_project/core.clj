(ns data-project.core
(:require [clojure.string :as str])
(:require [clojure.java.io :as io])
(:require [hiccup.core :as h])
      (:gen-class))

(defn load-txt 
  [file]
  (str/split (slurp file) #"\n"))

(defn makerecord 
  [thedata]
  (if (> (count thedata) 1)
  (reduce merge {} (map #(let  [line %1 splitline (str/split line #":")]
        { (keyword (nth splitline 0)) (nth splitline 1)})
      thedata))))

(defn read-data 
  [file]
  (remove nil? (map makerecord (partition-by empty? (load-txt file)) )))

(defn count-by-color
  [file]
  (for [g (group-by :Color (read-data file))]
        (let [g-name (first g) values (second g) ]
             (println g-name " " (count values)))))

(def box-style 
  "width:20px;height:20px;
  margin:5px;
  border:1px solid rgba(0, 0, 0, .2);background-color:")

(defn box [color] (assoc {:class "box"} :style (str box-style color)))

(defn test-html [name] (h/html [:html 
 [:div {:class "input-color"}[:input {:type "text" :value "Orange"}]
                             [:div (box "green") ]
                             [:div (box "red") ]
                             [:div (box "blue") ]
                             [:div (box "purple") ]
                             ]]))

(defn -main
  "this program will read, sort and display the test data"
  [& args]
  (do 
  (println "reading data...")
  (spit "test.html" (test-html "lonnie"))))
  ;;(read-data (first args))))
