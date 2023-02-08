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

;; CAN I FIRST, REST AND CONST IT?

;;; (map my-university [ "Nariño" "Harvard" "London"])

(seq '(1 2 3))
; => (1 2 3)

(seq [1 2 3])
; => (1 2 3)

(seq #{1 2 3})
; => (1 2 3)

(seq {:name "Bill Compton" :occupation "Dead mopey guy"})
; => ([:name "Bill Compton"] [:occupation "Dead mopey guy"])

(first {:name "Bill Compton" :occupation "Dead mopey guy"})
;=> [:name "Bill Compton"]

;; You can convert the seq back into a map by using into to stick the result into an empty map

;; MAP


;; Map works with one colection
(map inc [1 2 3])
; => (2 3 4)

(map dec [4 5 7])
;=> (3 4 6)

;; And with two collections
(map str ["a" "b" "c"] ["A" "B" "C"])
; => ("aA" "bB" "cC")

(map str
     ["Today's gonna be " "Today isn't gonna be " "Never is gonna be " "Ever is gonna be "]
     ["a good day " "a horrible day " "a hard day " "a tricky day " "a sad day " "a productive "]
     ["because you're pretty." "because you're smart." "because you're ugly" "because you're small." "because you're tall" ])
;=>
;("Today's gonna bea good day because you're pretty."
; "Today isn't gonna be a horrible day because you're smart."
; "Never is gonna bea hard day because you're ugly"
; "Ever is gonna be a tricky day because you're small.")


;;; Defite vampire diet in l
(def human-consumption   [8.1 7.3 6.6 5.0])
(def critter-consumption [0.0 0.2 0.3 1.1])

;;; Create an object to save the data of critter or human
(defn unify-diet-data
  [human critter]
  {:human human
   :critter critter})

(map unify-diet-data human-consumption critter-consumption)
; => ({:human 8.1, :critter 0.0} {:human 7.3, :critter 0.2} {:human 6.6, :critter 0.3} {:human 5.0, :critter 1.1})

(def sum #(reduce + %))
(def avg #(/ (sum %) (count %)))

;;; Map in a vector of functions

(defn stats
  [numbers]
  (map #(% numbers) [sum count avg]))

(stats [3 4 10])
; => (17 3 17/3)

(stats [80 1 44 13 6])
; => (144 5 144/5)

(def identities
  [{:alias "Batman" :real "Bruce Wayne"}
   {:alias "Spider-Man" :real "Peter Parker"}
   {:alias "Santa" :real "Your mom"}
   {:alias "Easter Bunny" :real "Your dad"}])

(map :real identities)
; => ("Bruce Wayne" "Peter Parker" "Your mom" "Your dad"

;; PROBLEM THAT I CREATED

;;; created a functions that returns a list of the animals that have more than 30 years
(def zoo
  {:mammals {
             :lion {
                    :age 50
                    :genre "male"
                    :weight 400
                    }
             :tiger {:age 40
                     :genre "male"
                     :weight 350}
             }
   :reptiles {:crocodile {:age 35
                          :genre "male"
                          :length 20}
              :snake {:age 15
                      :length 10}}
   :birds {:eagle {:age 10
                  :weight 5}
          :penguin {:age 5
                    :weight 2}
          }})


(defn new-function [{:keys [reptiles mammals birds]}]
  (into []
        (concat
          (map (fn [[animal-name animal-data]]
                 {:animal-name animal-name :age (:age animal-data)})
               (filter (fn [[_ animal-data]]
                         (> (:age animal-data) 30))
                       mammals))
          (map (fn [[animal-name animal-data]]
                 {:animal-name animal-name :age (:age animal-data)})
               (filter (fn [[_ animal-data]]
                         (> (:age animal-data) 30))
                       reptiles))
          (map (fn [[animal-name animal-data]]
                 {:animal-name animal-name :age (:age animal-data)})
               (filter (fn [[_ animal-data]]
                         (> (:age animal-data) 30))
                       birds)))))


; => [{:animal-name :lion, :age 50} {:animal-name :tiger, :age 40} {:animal-name :crocodile, :age 35}]

