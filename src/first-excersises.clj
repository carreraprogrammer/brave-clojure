;; Use the str, vector, list, hash-map, and hash-set functions.
"This is a string!"                                         ; => STRING
[1 "hello" "bye"]                                           ; => VECTOR
'("hello again" "by again")                                 ; => LIST
{:name "Daniel" :age 26}                                    ;=> hash-map
#{"apple" "banana" "cherry"}                                ;=> hash-set

;; Write a function that takes a number and adds 100 to it.

(defn addition
  [num]
  (+ num 100))

;Write a function, dec-maker, that works exactly like the function inc-maker except with subtraction:

;; I need to define a function that returns other function and receives an argument x
;; the function that returns should take a y number that is passed and rest by x

(defn dec-maker [x]
  (fn [y] (- y x)))

(def dec9 (dec-maker 9))
(dec9 10)
; => 1

;; Write a function, mapset, that works like map except the return value is a set:

;;; I need to create a function that takes two arguments, the first one is a function and the second one is a vector
;;; The function should be applied to each element in the vector, and add the values inside a set map

(defn mapset [func vec]
  (set (map func vec)))

;; Create a function that’s similar to symmetrize-body-parts except that it has to work with weird space aliens with radial symmetry. Instead of two eyes, arms, legs, and so on, they have five.

(def alien-hobbit-body-parts [{:name "head" :size 3}
                              {:name "eye-1" :size 1}
                              {:name "ear-1" :size 1}
                              {:name "mouth" :size 1}
                              {:name "nose" :size 1}
                              {:name "neck" :size 2}
                              {:name "shoulder-1" :size 3}
                              {:name "upper-arm-1" :size 3}
                              {:name "chest" :size 10}
                              {:name "back" :size 10}
                              {:name "forearm-1" :size 3}
                              {:name "abdomen" :size 6}
                              {:name "kidney-1" :size 1}
                              {:name "hand-1" :size 2}
                              {:name "knee-1" :size 2}
                              {:name "thigh" :size 4}
                              {:name "lower-leg-1" :size 3}
                              {:name "achilles-1" :size 1}
                              {:name "foot-1" :size 2}])

(defn matching-alien-parts
  [{name :name size :size} n]
  (reduce (fn [all-parts n] (conj
                              all-parts
                              {:size size :name (clojure.string/replace name #"-(.*)" (str "-" n))}))
          #{}
          (range 1 (inc n))))

(defn symetrize-alien-body-parts
  "this is a generalisation of function symmetrize-body-parts''that is described in book.
   Function receives a collection of body parts and number of matching body parts to add"
  [alien-body-parts n]
  (reduce (fn [final-body-parts part]
            (into final-body-parts (matching-alien-parts part n)))
          []
          alien-body-parts))

(defn my-university
  [university]
  (str "I studied in the " university "'s university"))

(map my-university [ "Nariño" "Harvard" "London"])