(ns data-project.core
(:require [clojure.string :as str])
(:require [clojure.java.io :as io])
(:require [hiccup.core :as h])
(:require [hiccup.page :as hp])
      (:gen-class))

(defn load-txt 
"loads the text and returns list of strings. Each string is a line."
  [file]
  (str/split (slurp file) #"\n"))

(defn makekvpair
"makes a key value pair from a : delimited string where the 1st value is the key and the second is the value"
  [line]
  (let [splitline(str/split line #":")]
    {(keyword (nth splitline 0)) (nth splitline 1)}))

(defn makerecord 
"makes a record from a list of strings.  Each string is a : delimited kv pair."
  [thedata]
  (if (> (count thedata) 1)
  (reduce merge {} (map makekvpair thedata))))

(defn read-data 
"reads the file and return a list of records."
  [file]
  (remove nil? (map makerecord (partition-by empty? (load-txt file)) )))

(defn count-by-color
"test function which will print out the number of records in each color."
  [file]
  (for [g (group-by :Color (read-data file))]
        (let [g-name (first g) values (second g) ]
             (println g-name " " (count values)))))

(def box-style 
  "width:20px;height:20px; margin:5px; border:1px solid rgba(0, 0, 0, .2);background-color:")

(defn box 
"returns a 20x20px colored div and alert. color arg is used to color the box and specify the group to show."
  [color] 
  [:div (assoc {:class "box"} :style (str box-style color) :onclick (str "alert('" color "')"))])

(defn create-html 
"returns a static html page which can display the data grouped by color."
  [data] 
  (let [colors (keys (group-by :Color data))]
  (hp/html5 (into [:div] (map box colors )))))

(defn -main
  "this program will read, sort and display the test data"
  [& args]
  (do
  (println "reading data...")
  (spit "test.html" (create-html (read-data (first args))))))
