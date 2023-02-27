

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

(defn attr [attr-key]
  (get-in character [:attributes attr-key]))

(attr :intelligence)

;; => 10

;; define a function that takes as argument  any functions

(defn my-comp [& functions]
  (fn [arg]
    (reduce #(%2 %1) arg functions)))

;; define diferent functions

(defn add-1 [x] (+ x 1))
(defn square [x] (* x x))

;; use comp to "merge" the two functions

(defn add-1-then-square [x]
  ((comp square add-1) x))

;;; use add-1-then-square in an example

(add-1-then-square 3) ; => 16

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
