(ns slurp-length.core
  (:gen-class)
  (:use [clojure.string :only [split-lines split]]))

(defn xyzSplit
  [csvFile]
  (def csvLines (split-lines (slurp csvFile)))
  (for [csvLine csvLines] (mapv bigdec (split csvLine #";"))))

(defn edgeLength
  [[[v1x v1y] [v2x v2y]]]
  (Math/sqrt
    (+
      (-> (- v2y v1y) (Math/pow 2))
      (-> (- v2x v1x) (Math/pow 2)))))

(defn -main
  []
  (let [vertexes (xyzSplit "sykkeltur.csv")
        edges (mapv vector vertexes (rest vertexes))
        lineLength (reduce + (mapv edgeLength edges))]
    (println lineLength)))
