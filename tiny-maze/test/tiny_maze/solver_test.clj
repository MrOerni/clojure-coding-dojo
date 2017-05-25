(ns tiny-maze.solver-test
  (:require [clojure.test :refer :all]
            [tiny-maze.solver :refer [solve-maze]]))

(defn biggest-maze [x y] (-> (vec (repeat x (vec (repeat y 0))))
                             (assoc-in [0 0] :S)
                             (assoc-in [(dec x) (dec y)] :E)))

(defn solution-maze [x y] (vec (mapv #(assoc % (dec y) :x) (assoc (biggest-maze x y) 0 (vec (repeat x :x))))))

;; Hint: Start with a smaller maze
(deftest test-solve-maze
  (testing "can find way to exit with 3x3 maze"
    (let [maze [[:S 0 1]
                [1  0 1]
                [1  0 :E]]
          sol [[:x :x 1]
               [1  :x 1]
               [1  :x :x]]]
      (is (= sol (solve-maze maze)))))

  (testing "can find way to exit with 4x4 maze"
    (let [maze [[:S 0  0 1]
                [1  1  0 0]
                [1  0  0 1]
                [1  1  0 :E]]
          sol [[:x :x :x 1]
               [1  1 :x 0]
               [1  0 :x 1]
               [1  1 :x :x]]]
     (is (= sol (solve-maze maze)))))

  (testing "can find shortest path"
    (let [maze [[0 :S  0  0  1]
                [0  1  1  0  1]
                [0  0  1  0  1]
                [1  0  0  0 :E]]
          sol [[0 :x :x :x  1]
               [0  1  1 :x  1]
               [0  0  1 :x  1]
               [1  0  0 :x :x]]]
      (is (= sol (solve-maze maze)))))

  (testing "the big stuff"
    (let [x 5]
      (is (= (solution-maze x x) (solve-maze (biggest-maze x x)))))))

