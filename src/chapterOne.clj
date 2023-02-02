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
