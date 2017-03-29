(ns data-project.core
      (:gen-class))

(require '[clojure.string :as str])
(require '[clojure.java.io :as io])

(defn -main
  "this program will read, sort and display the test data"
  [& args]
  (println "use read-data file in the repl"))


(defn load-txt 
  [file]
  (str/split (slurp file) #"\n"))

(defn makerecord 
  [thedata]
  (if (> (count thedata) 1)
  (reduce merge {} (map #(let  [line %1 splitline (str/split line #":")]
        { (keyword (nth splitline 0)) (nth splitline 1)})
      thedata))
  )
)

(defn read-data 
  [file]
  (remove nil? (map makerecord (partition-by empty? (load-txt file)) ))
)

(defn count-by-color
  [file]
  (for [g (group-by :Color (read-data file))]
        (let [g-name (first g) values (second g) ]
             (println g-name " " (count values)))
  )
)





