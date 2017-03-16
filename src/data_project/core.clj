(ns data-project.core
      (:gen-class))

(require '[clojure.string :as str])
(require '[clojure.java.io :as io])

(defn -main
  "this program will read, sort and display the test data"
  [& args]
  (println "use read-data file in the repl"))



(defn processLine  
  [line]
  (println (str "test:" line (empty? line)))
  (if (empty? line)
      (println "skip") 
   (let  [splitline (str/split line #":")]
        { (keyword (nth splitline 0)) (nth splitline 1)})
  ))

(defn read-data 
  [file]
  (with-open [rdr (io/reader file)]
         (reduce merge {} (doall(map processLine (line-seq rdr)))))
  )

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
