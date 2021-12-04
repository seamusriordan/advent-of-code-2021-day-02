(ns day-02.core-test
  (:require [clojure.test :refer :all]
            [day-02.core :refer :all]))

(deftest example-input-gives-150
  (testing "example input gives 150"
    (let [example-input "forward 5\ndown 5\nforward 8\nup 3\ndown 8\nforward 2"
          result (get-position example-input)]


      (is (= 900 (* (:horiz result) (:depth result))))
      )))
