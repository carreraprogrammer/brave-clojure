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