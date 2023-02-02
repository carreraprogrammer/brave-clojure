(defn foo [x]
  (println (+ x x)))

(+ 1 2 3)
; => 6

(str "It was the panda " "in the library " "with a dust buster")
; => "It was the panda in the library with a dust buster"

(if true
  "By Zeus's hammer!"
  "By Aquaman's trident!")

(if false
  "By Zeus's hammer!"
  "By Aquaman's trident!")

(if true
  (do (println "Success!")
      "By Zeus's hammer!")
  (do (println "Failure!")
      "By Aquaman's trident!"))
; => Success!
; => "By Zeus's hammer!"

(defn bool [x]
  (when (= x true )
    (println "Success!")
    "abra cadabra"))


(= 1 1)
; => true

(= nil nil)
; => true

(= 1 2)
; => false

;;Or: I can return the first thruty value

(or false nil :large_I_mean_venti :why_cant_I_just_say_large)
; => :large_I_mean_venti

(or (= 0 1) (= "yes" "no"))
; => false

(or nil)
; => nil

;; AND: I can return the first falsy value

(and :free_wifi :hot_coffee)
; => :hot_coffee

(and :feeling_super_cool nil false)
; => nil

(defn error-message
  [severity]
  (str "OH GOD! IT'S A DISASTER! WE'RE "
       (if (= severity :mild)
         "MILDLY INCONVENIENCED!"
         "DOOOOOOOMED!")))



(defn qualify-my-habit
  [qualification]
  (str "YOU HAVE A "
       (if (< qualification 3)
         "HORRIBLE HABIT"
         "INCREDIBLE HABIT"))
  )


;; STRINGS

(def namee "Chewbacca")
(str "\"Uggllglglglglglglglll\" - " name)
; => "Uggllglglglglglglglll" - Chewbacca

;; MAPS

(def lista {:first-name "Charlie"
           :last-name "McFishwich"})

(get lista :first-name)                                     ; => "Charlie"

(hash-map :a 1 :b 2)

(get {:a 0 :b 1} :b)
; => 1

(get {:a 0 :b {:c "ho hum"}} :b)
; => {:c "ho hum"}

;; VECTORS

(def first_vector
  [3 2 1])

(get first_vector 1)                                        ; => "2"

(get ["a" {:name "Pugsley Winterbottom"} "c"] 1)
; => {:name "Pugsley Winterbottom"}

(vector "creepy" "full" "moon")
; => ["creepy" "full" "moon"]

(conj [1 2 3] 4)
; => [1 2 3 4]

(def second-vector [1 2 3])

(defn add [x]
  (if (= x 4)
    (do (def new-vector (conj second-vector x))
        new-vector)
    second-vector))

;; LISTS

;  if you need to easily add items to the beginning of a sequence or if you’re writing a macro, you should use a list.

(nth '(:a :b :c) 0)
; => :a

(nth '(:a :b :c) 2)
; => :c

(list 1 "two" {3 4})
; => (1 "two" {3 4})

; Elements are added to the beginning:

(conj '(1 2 3) 4)
; => (4 1 2 3)

;;SETS

#{"kurt vonnegut" 20 :icicle}

(hash-set 1 1 2 2)
; => #{1 2}

(conj #{:a :b} :b)
; => #{:a :b}

(set [3 3 3 4 4])
; => #{3 4}

(contains? #{:a :b} :a)
; => true

(contains? #{:a :b} 3)
; => false

(contains? #{nil} nil)
; => true

(:a #{:a :b})
; => :a

(get #{:a :b} :a)
; => :a

(get #{:a nil} nil)
; => nil

(get #{:a :b} "kurt vonnegut")
; => nil


;; FUNCTIONS

(or + -)

; => #<core$_PLUS_ clojure.core$_PLUS_@76dace31>

((or + -) 1 2 3)
; => 6

((and + -) 1 2 3)
; => -4

((and (= 1 1) +) 1 2 3)
; => 6

((first [+ 0]) 1 2 3)
; => 6

(inc 1.1)
; => 2.1

(map inc [0 1 2 3])
; => (1 2 3 4)

(+ (inc 199) (/ 100 (- 7 2)))
(+ 200 (/ 100 (- 7 2))) ; evaluated "(inc 199)"
(+ 200 (/ 100 5)) ; evaluated (- 7 2)
(+ 200 20) ; evaluated (/ 100 5)
220 ; final evaluation