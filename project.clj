(defproject data-project "0.1.0-SNAPSHOT"
  :description "Parse, sort and display a test file on github."
  :url "http://llsouder.github.com/data-project"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [hiccup "1.0.4"]]
  :main ^:skip-aot data-project.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
