(defn wisdom
  [words]
  (str words ", Daniel-san"))

(wisdom "Always bathe on Fridays")
; => "Always bathe on Fridays, Daniel-san"

(defn year-end-evaluation
  []
  (if (> (rand) 0.5)
    "You get a raise!"
    "Better luck next year!"))

(mapv :name '({:name "Daniel" :age 26} {:name "Pam" :age 22} {:name "Rosa" :age 60}))

(def great-baby-name "Rosanthony")
great-baby-name
; => "Rosanthony"

(let [great-baby-name "Bloodthunder"]
  great-baby-name)
; => "Bloodthunder"

great-baby-name
; => "Rosanthony"

(defn sum
  ([vals] (sum vals 0))
  ([vals accumulating-total]
       (if (empty? vals)
            accumulating-total
            (sum (rest vals) (+ (first vals) accumulating-total)))))

(sum [39 5 1])                                              ;=> 45
(sum [39 5 1] 0)                                            ; => 45
(sum [5 1] 39)                                              ; => 45
(sum [1] 44)                                                ; => 45
(sum [] 45)                                                 ; => 45

(defn sum
  ([vals]
   (sum vals 0))
  ([vals accumulating-total]
   (if (empty? vals)
     accumulating-total
     (recur (rest vals) (+ (first vals) accumulating-total)))))

(require '[clojure.string :as s])
(defn clean
  [text]
  (s/replace (s/trim text) #"lol" "LOL"))

(clean "My boa constrictor is so sassy lol!  ")
; => "My boa constrictor is so sassy LOL!"