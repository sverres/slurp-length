(ns slurp-length.core
  (:gen-class)
  (:use [clojure.string :only [split-lines split]]))

(defn xyzSplit
  [csvFile]
  (def csvLines (split-lines (slurp csvFile)))
  (for [csvLine csvLines] (mapv bigdec (split csvLine #";"))))

(defn edgeLength
  [[[v1x v1y] [v2x v2y]]]
  (let [dx (- v2x v1x)
        dy (- v2y v1y)
        d2x (Math/pow dx 2)
        d2y (Math/pow dy 2)]
    (Math/sqrt (+ d2x d2y))))

(defn -main
  []
  (let [vertexes (xyzSplit "sykkeltur.csv")
        edges (mapv vector vertexes (rest vertexes))
        lineLength (reduce + (mapv edgeLength edges))]
    (println lineLength)))
