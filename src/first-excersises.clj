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

;




