(ns data-project.core
(:require [clojure.string :as str])
(:require [clojure.java.io :as io])
(:require [hiccup.page :as page])
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

(def box-style 
  "width:20px;height:20px; margin:5px; border:1px solid rgba(0, 0, 0, .2);background-color:")

(defn showhide [id] [:script (str "$( \"div[id='" id "-box']\" ).click(function() {
  $( \"div[id='" id "']\" ).toggle(\"slow\");});")])

(defn box 
"returns a 20x20px colored div and alert. color arg is used to color the box and specify the group to show."
  [color] 
  [:div (assoc {:class "box col-sm-12" :id (str color "-box")} :style (str box-style color))])

(defn list-items 
  [records]
  (mapcat (fn [record] [(:Name record) [:br]]) records ))

(defn create-html 
"returns a static html page which can display the data grouped by color."
  [data] 
  (let [colors (keys (group-by :Color data))]
    (page/html5
      [:head
        [:title "Data-Project Example in Clojure"]
        (page/include-css "https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css")
        (page/include-js "https://code.jquery.com/jquery-1.10.2.js")
        (page/include-js "https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js")]

      ;;show buttons
      [:div {:class "container"} 
        (into [:div {:class "row"}] (map box colors ))]

      ;;bunch of javascript to show or hide colors
      (map showhide colors)

      ;;list the names of the people in color group 
      (for [g (group-by :Color data)]
        (let [color (first g) values (second g) ]
          (into [:div {:id color :style "display:none"} ] [[:font {:color color} (list-items values)]])))))) 

(defn -main
  "this program will read, sort and display the test data"
  [& args]
  (do
  (println "reading data...")
  (spit "test.html" (create-html (read-data (first args))))))
