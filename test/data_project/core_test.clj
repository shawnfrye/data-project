(ns data-project.core-test
  (:require [clojure.test :refer :all]
            [data-project.core :refer :all]))

(deftest load-txt-test 
  (testing "load a file"
    (is (= (load-txt "test-data.txt") ["Date:12.21.16" "Name:Ronan Dalton" "Company:At Limited" "Color:green" "" "Date:01.16.17" "Name:Holly Rice" "Company:Lacus PC" "Color:yellow"]))))

(deftest makekvpair-test
  (testing "single delimited string should return a key/value pair"
    (is (= (makekvpair "Color:orange") {:Color "orange"}))))

(deftest makerecord-test
  (testing "take a list of : delimited strings return a  map"
    (is (= (makerecord ["Name:test mctest" "Color:orange"]) {:Name "test mctest" :Color "orange"}))))

(deftest read-data-test 
  (testing "read data into the record format"
    (is (= (read-data "test-data.txt") '({:Date "12.21.16", :Name "Ronan Dalton", :Company "At Limited", :Color "green"} {:Date "01.16.17", :Name "Holly Rice", :Company "Lacus PC", :Color "yellow"})))))

(deftest showhide-test
  (testing "testing the function the write out javascrpt to show/hide the data"
    (is (= (showhide "blue") ))))

(def blue-box [:div {:class "box col-sm-12" :id "blue-box" :style "width:20px;height:20px; margin:5px; border:1px solid rgba(0, 0, 0, .2);background-color:blue"}])

(deftest box-test
  (testing "box html test"
    (is (= (box "blue") blue-box))))

(def listofitems `("Fred" [:br] "Joe" [:br]))

(deftest list-items-test
  (testing "item list html test"
    (is (= (list-items [{:Name "Fred"} {:Name "Joe"}]) listofitems))))

(deftest create-html-test
  (testing "create html test"
    (is (= (create-html '({:Name "Fred" :Color "red"} {:Name "Joe" :Color "blue"})) "<!DOCTYPE html>\n<html><head><title>Data-Project Example in Clojure</title><link href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\" rel=\"stylesheet\" type=\"text/css\"><script src=\"https://code.jquery.com/jquery-1.10.2.js\" type=\"text/javascript\"></script><script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\" type=\"text/javascript\"></script></head><div class=\"container\"><div class=\"row\"><div class=\"box col-sm-12\" id=\"red-box\" style=\"width:20px;height:20px; margin:5px; border:1px solid rgba(0, 0, 0, .2);background-color:red\"></div><div class=\"box col-sm-12\" id=\"blue-box\" style=\"width:20px;height:20px; margin:5px; border:1px solid rgba(0, 0, 0, .2);background-color:blue\"></div></div></div><script>$( \"div[id='red-box']\" ).click(function() {\n  $( \"div[id='red']\" ).toggle(\"slow\");});</script><script>$( \"div[id='blue-box']\" ).click(function() {\n  $( \"div[id='blue']\" ).toggle(\"slow\");});</script><div id=\"red\" style=\"display:none\"><font color=\"red\">Fred<br></font></div><div id=\"blue\" style=\"display:none\"><font color=\"blue\">Joe<br></font></div></html>"))))

(deftest main-test 
  (testing "run the main for full ccoverage"
    (is (= (-main "test-data.txt") nil))))
