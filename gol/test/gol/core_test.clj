(ns gol.core-test
  (:require [clojure.test :refer :all]
            [clojure.spec :as s]
            [clojure.spec.gen :as gen]
            [clojure.spec.test :as stest] 
            [gol.core :refer :all]))

(deftest alive-test
  (testing "Dead cell stays dead")
  (testing "Dead cell coming alive")
  (testing "Living cell dies"
    (is (= 0 (rule 0 true))))
  (testing "Living cell stays alive"
    (is (= 1 (rule 2 true)))
    (is (= 1 (rule 3 true)))))

