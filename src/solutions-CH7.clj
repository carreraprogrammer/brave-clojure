(eval (list 'str "Daniel " "Carrera"))

; => => "Daniel Carrera"

(eval '(str "Daniel " "Carrera"))

; => => "Daniel Carrera"

(eval (read-string "(str \"Daniel Carrera \" \"Interstellar\")"))

;=>  "Daniel Carrera Interstellar"

