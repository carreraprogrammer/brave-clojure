;; Use the str, vector, list, hash-map, and hash-set functions.
"This is a string!"                         ; => STRING
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
;; the function that returns should take the number that is passed and rest by x

(defn dec-maker [x]
  (fn [y] (- y x)))

(def dec9 (dec-maker 9))
(dec9 10)
; => 1

;; Write a function, mapset, that works like map except the return value is a set:

;;; I need to create a function that takes two arguments, the first one is a function and the second one is a vector
;;; The function should be applied to each element in the vector, and add the values inside a set map

(defn mapset [func vec ]
  (set (map func vec)))





