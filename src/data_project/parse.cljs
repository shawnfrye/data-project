(ns data-project.parse
  (:require [clojure.string :as str]))

(defn makekvpair
  "makes a key value pair from a : delimited string where the 1st value is the key and the second is the value"
  [line]
  (let [splitline(str/split line #":")]
    {(keyword (nth splitline 0)) (nth splitline 1)}))

(defn makerecord
  "makes a record from a list of strings.  Each string is a : delimited kv pair."
  [thedata]
  (println "thedata=" thedata)
  (if (> (count thedata) 1)
    (reduce merge {} (map makekvpair thedata))))

(defn parse-data
  "parse data into a list of records."
  [text]

  (js/console.log "text=" text)
  (js/console.log "joined text=" (str/join text))
  (js/console.log "text type=" (str (type text)))
  (keep makerecord (partition-by empty? (str/split-lines text))))

;;(defn list-colors
;;[data]
;;(let [colors (keys (group-by :Color data))]
    ;;show buttons
;;    [:div {:class "container"}
;;     (into [:div {:class "row"}] (map box colors ))]))

;;(defn group-by-color
  ;;[data]
    ;;;;list the names of the people in color group
    ;;(for [g (group-by :Color data)]
      ;;(let [color (first g) values (second g) ]
        ;;(into [:div {:id color :style "display:none"} ] [[:font {:color color} (list-items values)]])))):w

