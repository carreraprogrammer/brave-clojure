;; excersise one

(def counter (atom 0))   ; create an atom with an initial value of 0

(swap! counter inc)      ; increment the value of the atom by 1
(swap! counter inc)      ; increment the value of the atom by 1 again

@counter                 ; dereference the atom to get its current value

;; Excersise three

(def character1 (ref {:name "Character 1" :hit-points 15 :max-hit-points 40}))
(def character2 (ref {:name "Character 2" :hit-points 30 :max-hit-points 40 :inventory ["healing potion"]}))

(dosync
  (let [potion "healing potion"
        heal-amount 10]
    (when (some #(= potion %) (:inventory @character2))
      (alter character2 update-in [:inventory] #(remove #{potion} %))
      (alter character1 update-in [:hit-points] #(min (+ heal-amount %) (:max-hit-points @character1)))
      (println "Character 2 used a healing potion on Character 1, healing" heal-amount "hit points."))))

; => Character 2 used a healing potion on Character 1, healing 10 hit points.