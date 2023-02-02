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
  (when (= x true)
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
            :last-name  "McFishwich"})

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

;; FUNCTION CALLS, MACRO CALLS AND SPECIAL FORMS
; DEFINING FUNCTIONS

(defn too-enthusiastic
  "Return a cheer that might be a bit too enthusiastic"
  [name]
  (str "OH. MY. GOD! " name " YOU ARE MOST DEFINITELY LIKE THE BEST "
       "MAN SLASH WOMAN EVER I LOVE YOU AND WE SHOULD RUN AWAY SOMEWHERE"))
(too-enthusiastic "zelda")
; => "OH. MY. GOD! Zelda YOU ARE MOST DEFINITELY LIKE THE BEST MAN SLASH WOMAN EVER I LOVE YOU AND WE SHOULD RUN AWAY SOMEWHERE"

;PARAMS AND ARITY

(defn no-params
  []
  "I take no parameters!")                                  ;=> 0-ARITY FUNCTION

(defn one-param
  [x]
  (str "I take one parameter: " x))                         ;=> 1-ARITY FUNCTION

(defn two-params
  [x y]
  (str "Two parameters! That's nothing! Pah! I will smoosh them "
       "together to spite you! " x y))                      ;=> 2-ARITY FUNCTION

; THIS IS AN EXAMPLE OF OVERLOADING
(defn Arity
  ;; 3 arguments
  ([first-arg second-arg third-arg]
   (str "Okay, this is a 3-Ary example, so you have: " first-arg " " second-arg " " third-arg))
  ([first-arg second-arg]
   (str "Okay, this is a 2-Ary example, so you have less. Let me show you: " first-arg " " second-arg " "))
  ([first-arg]
   (str "Now you are poor, you only have: " first-arg)))

(defn weird-arity
  ([]
   "Destiny dressed you this morning, my friend, and now Fear is
   trying to pull off your pants. If you give up, if you give in,
   you're gonna end up naked with Fear just standing there laughing
   at your dangling unmentionables! - the Tick")
  ([number]
   (inc number)))

(defn codger-communication
  [whippersnapper]
  (str "Get off my lawn, " whippersnapper "!!!"))

(defn codger
  [& whippersnappers]
  (map codger-communication whippersnappers))

(codger "Billy" "Anne-Marie" "The Incredible Bulk")         ;=> ("Get off my lawn, Billy!!!" "Get off my lawn, Anne-Marie!!!" "Get off my lawn, The Incredible Bulk!!!")

(defn favorite-things
  [name & things]
  (str "Hi, " name ", here are my favorite things: "
       (clojure.string/join ", " things)))

(favorite-things "Doreen" "gum" "shoes" "kara-te")
; => "Hi, Doreen, here are my favorite things: gum, shoes, kara-te"

;DESTRUCTURING

; This is not related to DESTRUCTURING, I WAS JUST PRACTICING

(defn falsy [bool]
  (if (= bool false)
    (do (println "The function works, so you obtain:")
        bool)
    "You can't do this, it's a falsy function"))

;This is related to DESTRUCTURING

;; Return the first element of a collection
(defn my-first
  [[first-thing]]                                           ; Notice that first-thing is within a vector
  first-thing)

(my-first ["oven" "bike" "war-axe"])
; => "oven"

(defn chooser
  [[first-choice second-choice & unimportant-choices]]
  (println (str "Your first choice is: " first-choice))
  (println (str "Your second choice is: " second-choice))
  (println (str "We're ignoring the rest of your choices. "
                "Here they are in case you need to cry over them: "
                (clojure.string/join ", " unimportant-choices))))

(chooser ["Marmalade", "Handsome Jack", "Pigpen", "Aquaman"])
; => Your first choice is: Marmalade
; => Your second choice is: Handsome Jack
; => We're ignoring the rest of your choices. Here they are in case \ you need to cry over them: Pigpen, Aquaman

(defn announce-treasure-location
  [{lat :lat lng :lng}]
  (println (str "Treasure lat: " lat))
  (println (str "Treasure lng: " lng)))

(announce-treasure-location {:lat 28.22 :lng 81.33})
; => Treasure lat: 28.22
; => Treasure lng: 81.33

(defn receive-treasure-location
  [{:keys [lat lng] :as treasure-location}]
  (println (str "Treasure lat: " lat))
  (println (str "Treasure lng: " lng))

  ;; One would assume that this would put in new coordinates for your ship
  (println treasure-location))

;; FUNCTION BODY

(defn illustrative-function
  []
  (+ 1 304)
  30
  "joe")

(illustrative-function)
; => "joe"

(defn number-comment
  [x]
  (if (> x 6)
    "Oh my gosh! What a big number!"
    "That number's OK, I guess"))

(number-comment 5)
; => "That number's OK, I guess"

(number-comment 7)
; => "Oh my gosh! What a big number!"

;; ANONYMOUS FUNCTIONS

(map (fn [name] (str "Hi, " name))
     ["Darth Vader" "Mr. Magoo"])
; => ("Hi, Darth Vader" "Hi, Mr. Magoo")

((fn [x] (* x 3)) 8)
; => 24

(#(* % 3) 8)
; => 24

(map #(str "Hi, " %)
     ["Darth Vader" "Mr. Magoo"])
; => ("Hi, Darth Vader" "Hi, Mr. Magoo")

(#(str %1 " and " %2) "cornbread" "butter beans")
; => "cornbread and butter beans"

(#(identity %&) 1 "blarg" :yip)
; => (1 "blarg" :yip)

;; RETURNING FUNCTIONS

(defn inc-maker
  "Create a custom incrementor"
  [inc-by]
  #(+ % inc-by))

(def inc3 (inc-maker 3))

(inc3 7)
; => 10


;; HOBBIT PROBLEM

(def asym-hobbit-body-parts [{:name "head" :size 3}
                             {:name "left-eye" :size 1}
                             {:name "left-ear" :size 1}
                             {:name "mouth" :size 1}
                             {:name "nose" :size 1}
                             {:name "neck" :size 2}
                             {:name "left-shoulder" :size 3}
                             {:name "left-upper-arm" :size 3}
                             {:name "chest" :size 10}
                             {:name "back" :size 10}
                             {:name "left-forearm" :size 3}
                             {:name "abdomen" :size 6}
                             {:name "left-kidney" :size 1}
                             {:name "left-hand" :size 2}
                             {:name "left-knee" :size 2}
                             {:name "left-thigh" :size 4}
                             {:name "left-lower-leg" :size 3}
                             {:name "left-achilles" :size 1}
                             {:name "left-foot" :size 2}])

(defn matching-part
  [part]
  {:name (clojure.string/replace (:name part) #"^left-" "right-")
   :size (:size part)})                                     ; => In this part I'm creating a function that can transform parts of boddy that starts with #"^left-" and I'm changing for right

(defn symmetrize-body-parts
  "Expects a seq of maps that have a :name and :size"
  [asym-body-parts]                                         ; => This is the argument that the function recives, a map with asymetric-body-parts
  (loop [remaining-asym-parts asym-body-parts               ;=> The body is taking as argument the asymetric body, and there is an empty vector  with the final-body-parts
         final-body-parts []]
    (if (empty? remaining-asym-parts)                       ;=> Here we are evaluating if the remaining-asym-parts are empty
      final-body-parts                                      ;=> If the variable is empty, we will return the final-body-parts, that is an empty vector
      (let [[part & remaining] remaining-asym-parts]        ;=> If it is not, we will create two variables, the part with the first part of the argument, and the remaining that are the rest of the body parts
        (recur remaining                                    ;=> After that, we are going to call the same function, but now with the remaining
               (into final-body-parts                       ;=> we introduce into the final body parts
                     (set [part (matching-part part)])))))));=> In this part, we are using set to introduce only one copy of the value in the vector

; LET

(def x 0)                                                   ; => global scope
(let [x (inc x)] x)                                         ; => Local scope
; => 1

(def dalmatian-list ["Pongo" "Perdita" "Puppy 1" "Puppy 2"])

(let [[first & others] dalmatian-list] [first others])

(def husbands ["Pacho" "Paco" "Pepe" "Pablo"])

;; Lola just like mens which a name that starts with "PA"
(let [lola-husbands (into [] (filter #(re-find #"^Pa" %) husbands))] lola-husbands)

;; INTO

(into [] (set [:a :a]))

; => [:a]

;; LOOP

(loop [iteration 0]
  (println (str "Iteration " iteration))
  (if (> iteration 3)
    (println "Goodbye!")
    (recur (inc iteration))))
; => Iteration 0
; => Iteration 1
; => Iteration 2
; => Iteration 3
; => Iteration 4
; => Goodbye!


;; REGEX

(re-find #"^left-" "left-eye")
; => "left-"

(re-find #"^left-" "cleft-chin")
; => nil

(re-find #"^left-" "wongleblart")
; => nil

(def some-part {:name "left-arm" :size 3})

(defn matching-part
  [part]
  {:name (clojure.string/replace (:name part) #"^left-" "right-")
   :size (:size part)})
(matching-part {:name "left-eye" :size 1})
; => {:name "right-eye" :size 1}]

(matching-part {:name "head" :size 3})
; => {:name "head" :size 3}]

(def string-part "left-arm")

(defn change-string
  [part]
  (clojure.string/replace part #"^left" "right"))

;; BETTER SYMETRIZE WITH REDUCER

(defn better-symmetrize-body-parts
  "Expects a seq of maps that have a :name and :size"
  [asym-body-parts]                                         ;=> Here we define the argument
  (reduce (fn [final-body-parts part]                       ;=> Here we start to define the acummulator of the reducer
            (into final-body-parts (set [part (matching-part part)]))) ;=> the acumulator is a function that receives 2 parametters, an empty vector and the asym-body-parts
          []                                                ;=> acumulator
          asym-body-parts))                                 ;=>
