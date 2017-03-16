(ns data-project.core
      (:gen-class))

(require '[clojure.string :as str])
(require '[clojure.java.io :as io])

(defn -main
  "this program will read, sort and display the test data"
  [& args]
  (println "use read-data file in the repl"))


(defn load 
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

(defn mytest 
  [file]
  (remove nil? (map makerecord (partition-by empty? (load file)) ))
)
