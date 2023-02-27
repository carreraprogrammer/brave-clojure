

(def character
  {:name "Smooches McCutes"
   :attributes {:intelligence 10
                :strength 4
                :dexterity 5}})

(def c-int (comp :intelligence :attributes))
(def c-str (comp :strength :attributes))
(def c-dex (comp :dexterity :attributes))

(c-int character)
; => 10

(c-str character)
; => 4

(c-dex character)
; => 5

;; Solution one

;; =>

(defn attr [collection attr-key]
  (get-in collection [:attributes attr-key]))

(attr character :intelligence)

;; => 10

;; define a function that takes as argument  any functions

(defn my-comp [& functions]
  (fn [arg]
    (reduce #(%2 %1) arg functions)))

;; define diferent functions

(defn add-1 [x] (+ x 1))
(defn square [x] (* x x))

(defn add-2 [x] (+ x 2))

;; use comp to "merge" the two functions

(defn add-1-then-square [x]
  ((my-comp square add-1) x))

(defn add-2-then-square-then-dec [x]
  ((my-comp dec square add-2) x))

;;; use add-1-then-square in an example

(add-1-then-square 3) ; => 10

(add-2-then-square-then-dec 3); => 6



;;; solution 4

(defn assoc-in [m [k & ks] v]
  (if ks
    (assoc m k (assoc-in (get m k) ks v))
    (assoc m k v)))

;; solution 5

(def my-map {:a {:b {:c 1}}})

; Create a nested map with three levels of depth
(def my-map {:a {:b {:c 1}}})

; Print the original map
(println my-map) ; => {:a {:b {:c 1}}}

; Use the assoc-in function to update the value of :c in my-map to 2
(def updated-map (assoc-in my-map [:a :b :c] 2))

; Print the updated map
(println updated-map) ; => {:a {:b {:c 2}}}


(defn sum-5
  [x y]
  (+ 5 y x))

(defn sum-10
  [x]
  (partial (sum-5 5 x)))

(sum-10 35)

(defn ordenar [nums]
  (sort nums))

(def ordenar-inversa (partial sort >))

(ordenar-inversa [3 1 4 1 5 9 2 6])

(defn filtrar [pred coll]
  (filter pred coll))

(def filtrar-mayores-5 (juxt second (partial filtrar #(> % 5))))

(filtrar-mayores-5 "abcdefg" [1 2 6 7 3 9])

(defn multiplicar [x y]
  (* x y))

(defn multiplicar-por-10 [x] (partial multiplicar x 10))

((multiplicar-por-10 5))

