(ns tiny-maze.solver
  "Example:
  [[:S 0  1]
   [1  0  1]
   [1  0 :E]]

  - :S : start of the maze
  - :E : end of the maze
  - 1 : This is a wall that you cannot pass through
  - 0 : A free space that you can move through.")


(defn find-in-maze [k maze]
  (first (filter #(<= 0 (second %)) (zipmap (range) (map #(.indexOf % k) maze)))))

(defn get-neighbours [position maze]
  (let [x-size (count maze)
        y-size (count (first maze))
        positions [[-1 0] [0 1] [1 0] [0 -1]]]
    (filter
      (fn [[a b]] (and (< -1 a x-size) (< -1 b y-size)))
      (map #(map + position %) positions))))

(defn get-free-neighbours [start maze]
  (filter #(#{0 :E} (get-in maze %)) (get-neighbours start maze)))

(defn maze->tree [start maze]
  (if (= (get-in maze start) :E)
    (assoc-in maze start :x)
    (mapcat #(maze->tree % (assoc-in maze start :x))
         (get-free-neighbours start maze))))

(defn path-length [path]
  (count (filter #{:x} (flatten path))))

(defn sorted-paths [paths]
  (sort-by path-length paths))

(defn solve-maze [maze]
  (let [paths (partition (count maze) (maze->tree (find-in-maze :S maze) maze))]
    (first (sorted-paths paths))))

