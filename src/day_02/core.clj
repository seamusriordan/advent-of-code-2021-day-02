(ns day-02.core
  (:require [clojure.string :as str]
            [clojure.java.io :as io]))

(defn apply-command
  [position command]
  (let [value (:value command)]
    (condp = (:command command)
      "forward" (update (update position :horiz #(+ % value)) :depth #(+ % (* (:aim position) value)))
      "up" (update position :aim #(- % value))
      "down" (update position :aim #(+ % value))
      ))
  )


(defn get-position
  [input]
  (let [lines (str/split-lines input)
        tokens (map #(str/split % #"\s+") lines)
        command-pairs (map #(vector (first %) (Integer. (second %))) tokens)
        commands (map #(zipmap [:command :value] %) command-pairs)]
    (reduce apply-command {:horiz 0 :depth 0 :aim 0} commands)
    ;commands
    ))

(defn main-
  []
  (let [lines (slurp (io/resource "input.txt"))]
    (get-position lines)))