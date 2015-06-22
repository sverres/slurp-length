(ns slurp-length.core
  (:gen-class)
  (:use [clojure.string :only [split-lines split]]))

(defn xyzSplit
  [csvFile]
  (def csvLines (split-lines (slurp csvFile)))
  (for [csvLine csvLines] (mapv bigdec (split csvLine #";"))))

(defn edgeLength
  [[[v1x v1y] [v2x v2y]]]
  (Math/sqrt (+ (Math/pow (- v2x v1x) 2) (Math/pow (- v2y v1y) 2))))

(defn -main
  []
  (def vertexes (xyzSplit "sykkeltur.csv"))
  (def edges (mapv vector vertexes (rest vertexes)))
  (def lineLength (reduce + (mapv edgeLength edges)))
  (println lineLength))
