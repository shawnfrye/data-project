(ns data-project.core-test
  (:require [clojure.test :refer :all]
            [data-project.core :refer :all]))

(deftest makekvpair-test
  (testing "single delimited string should return a key/value pair"
    (is (= (makekvpair "Color:orange") {:Color "orange"}))))

(deftest makerecord-test
  (testing "take a list of : delimited strings return a  map"
    (is (= (makerecord ["Name:test mctest" "Color:orange"]) {:Name "test mctest" :Color "orange"}))))

(def blue-box [:div {:class "box", :style "width:20px;height:20px; margin:5px; border:1px solid rgba(0, 0, 0, .2);background-color:blue", :onclick "alert('blue')"}])

(deftest box-test
  (testing "box html test"
    (is (= (box "blue") blue-box))))
