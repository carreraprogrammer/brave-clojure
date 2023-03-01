;; excersise 2

(defmacro my-or
  "macro for or logic"
  ([] nil)
  ([x] x)
  ([form & forms]
   `(let [sym# ~form]
      (if sym# sym# (my-or ~@forms)))))

(my-or nil false 2 1)
(macroexpand '(my-or nil false 2 1))

;; excersise 3

(defmacro defattrs
  [& assignments]
  `(do
     ~@(map
         (fn [[retr attr]] `(def ~retr ~attr))
         (partition 2 assignments))))

(defattrs c-int :intelligence wokring? :should-work)

(print wokring? c-int)

(macroexpand '(defattrs c-int :intelligence test :should-work))