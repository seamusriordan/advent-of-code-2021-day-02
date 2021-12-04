(ns day-02.core
  (:require [clojure.string :as str]
            [clojure.java.io :as io]))

(defn update-forward-with-aim
  [position value]
  (let [aim (:aim position)
        aim-value-product (* aim value)
        updated-horiz (update position :horiz + value)
        updated-depth (update updated-horiz :depth + aim-value-product)]
    updated-depth
    ))


(defn apply-command
  [position command]
  (let [value (:value command)]
    (condp = (:command command)
      "forward" (update-forward-with-aim position value)
      "up" (update position :aim - value)
      "down" (update position :aim + value)
      ))
  )


(defn get-position
  [input]
  (let [lines (str/split-lines input)
        tokens (map #(str/split % #"\s+") lines)
        command-pairs (map #(vector (first %) (Integer/parseInt (second %))) tokens)
        commands (map #(zipmap [:command :value] %) command-pairs)]
    (reduce apply-command {:horiz 0 :depth 0 :aim 0} commands)
    ))

(defn -main
  []
  (let [lines (slurp (io/resource "input.txt"))]
    (get-position lines)))