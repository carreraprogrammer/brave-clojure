

(def character
  {:name "Smooches McCutes"
   :attributes {:intelligence 10
                :strength 4
                :dexterity 5}})



;; Solution one

;; =>

(defn attr [collection attr-key]
  (get-in collection [:attributes attr-key]))

(attr character :intelligence)

;; => 10

;; define a function that takes as argument  any functions

(defn my-comp [& functions]
  (fn [arg]
    (reduce #(%2 %1) arg functions)))

;; define diferent functions

(defn add-1 [x] (+ x 1))
(defn square [x] (* x x))

(defn add-2 [x] (+ x 2))

;; use comp to "merge" the two functions

(defn add-1-then-square [x]
  ((my-comp square add-1) x))

(defn add-2-then-square-then-dec [x]
  ((my-comp dec square add-2) x))

;;; use add-1-then-square in an example

(add-1-then-square 3) ; => 10

(add-2-then-square-then-dec 3); => 6



;;; solution 4

(defn assoc-in [m [k & ks] v]
  (if ks
    (assoc m k (assoc-in (get m k) ks v))
    (assoc m k v)))

;; solution 5

(def my-map {:a {:b {:c 1}}})

; Create a nested map with three levels of depth
(def my-map {:a {:b {:c 1}}})

; Print the original map
(println my-map) ; => {:a {:b {:c 1}}}

; Use the assoc-in function to update the value of :c in my-map to 2
(def updated-map (assoc-in my-map [:a :b :c] 2))

; Print the updated map
(println updated-map) ; => {:a {:b {:c 2}}}


(defn sum-5
  [x y]
  (+ 5 y x))

(defn sum-10
  [x]
  (partial (sum-5 5 x)))

(sum-10 35)

(defn ordenar [nums]
  (sort nums))

(def ordenar-inversa (partial sort >))

(ordenar-inversa [3 1 4 1 5 9 2 6])

(defn filtrar [pred coll]
  (filter pred coll))

(def filtrar-mayores-5 (juxt second (partial filtrar #(> % 5))))

(filtrar-mayores-5 "abcdefg" [1 2 6 7 3 9])

(defn multiplicar [x y]
  (* x y))

(defn multiplicar-por-10 [x] (partial multiplicar x 10))

((multiplicar-por-10 5))

;;1. From the next map, use comp to obtain all the information

(def nested-map {:name "Alice"
                 :age 25
                 :contact {:email "alice@example.com"
                           :phone "555-1234"
                           :address {:street "Main Street"
                                     :number 123
                                     :city "NY"
                                     :zip-code 12345}}
                 :interests [:hiking :reading :painting]})


(def email (comp :email :contact))

(get-in nested-map [:contact :email])                       ;=> "alice@example.com"

(email nested-map)                                          ; => "alice@example.com"

(def phone (comp :phone :contact))

; 2 Phone
(get-in nested-map [:contact :phone])                       ;=> "555-1234"
(phone nested-map)                                          ; => "555-1234"

; 3 Street
(def street (comp :street :address :contact))

(get-in nested-map [:contact :address :street])

(street nested-map)

;;4 Number

(def number (comp :number :address :contact))

(number nested-map)                                         ; =>  123

;;5 City

(def city (comp :city :address :contact))

(get-in nested-map [:contact :addres :city])

(city nested-map)                                           ;=> => "NY"

;;6 zip-code
(def zip-code (comp :zip-code :address :contact))

(get-in nested-map [:contact :address :zip-code])
(zip-code nested-map)

;;7 Given a list of numbers, return a vector with the numbers squared and then sorted in descending order:

(def squared-and-sorted (comp (partial into []) (fn [collection] (map #(Math/pow % 2) collection)) (partial sort <)))

(squared-and-sorted [ 2 4 8 3 1])                           ;; => [1.0 4.0 9.0 16.0 64.0]


;;8 Use partial to create a function that returns the square of an array

(defn square-array [array]
  (map #(Math/pow % 2) array))

(def square-array-partial (partial square-array))

(square-array-partial [1 2 3 4 5])

;;9 Given a map that contains employee information, use comp to obtain their email addresses as a list of strings.

(def employee-map {:employees [{:name "Alice" :email "alice@example.com"}
                               {:name "Bob" :email "bob@example.com"}]})

(def list-of-emails (comp (partial map :email) :employees))

(list-of-emails employee-map)                               ;;=>("alice@example.com" "bob@example.com")


;;10 Given a map that contains a person's information, use comp to obtain their full address as a list of strings.

(def person-map {:name "Alice"
                 :age 25
                 :address {:street "Main Street"
                           :number 123
                           :city "NY"
                           :zip-code 12345}})


(defn from-map-to-string [person-map]
  (let [address (get-in person-map [:address])]
    [(str (:street address))
     (str (:number address))
     (str (:city address))
     (str (:zip-code address))]))

(from-map-to-string person-map)

(def full-address (comp  (partial clojure.string/join " ") from-map-to-string))

(full-address person-map)                                   ; => "Main Street 123 NY 12345"

;; 11 Given a list of strings, use comp and partial to obtain a list of the lengths of the strings sorted in ascending order.

(def strings ["hello" "world" "clojure" "partial" "comp"])

(map #(count %) strings)

(def sort-string-lengths (comp (partial sort <) (partial map #(count %))))

(sort-string-lengths strings)                               ;=> (4 5 5 7 7)

;; 12 Given a list of integers, use comp and partial to obtain the sum of the squares of the even numbers in the list.

(def numbers [1 2 3 4 5 6 7 8 9 10])

;; first filter even numbers

(defn even-numbers [numbers]
  (vec (filter #(= (rem % 2) 0) numbers)))

(even-numbers numbers)

;; then square each number

(defn square-numbers [numbers]
  (vec (map #(Math/pow % 2) numbers)))

(square-numbers numbers)

;; then reduce the vector
(reduce + numbers )

;; Use comp


(def square-even-numbers (comp
                           (partial reduce +)
                           (partial square-numbers)
                           (partial even-numbers)))

(square-even-numbers numbers)                               ;; => 220

;; 13 Given a list of maps containing information about students, use comp to obtain the average grade for all the students.

(def students [{:name "Alice" :grade 8}
               {:name "Bob" :grade 7}
               {:name "Charlie" :grade 9}])

;; first obtain the grades in an array

(defn grades [students]
  (vec (map :grade students)))

(grades students)  ; => [8 7 9]

;; Average of grades

(def avg-grades (comp #(/ % 2) (partial reduce +) #(grades %)))

(avg-grades students)                                       ;; => 12


;; 14 Given a list of numbers, use comp and partial to obtain a list of the numbers cubed and then filtered to only include the ones that are greater than 100.

(def numbers [1 2 3 4 5 6 7 8 9 10])

(defn cube-numbers [numbers]
  (map #(Math/pow % 3) numbers))

(cube-numbers numbers)

(defn more-than-100 [numbers]
  (filter #(> % 100) numbers))

(more-than-100 (cube-numbers numbers))                      ;; => (125.0 216.0 343.0 512.0 729.0 1000.0)


;; 15 Given a list of strings, use comp and partial to obtain a list of the strings in reverse order with the first letter capitalized.

(def strings ["hello" "world" "clojure" "partial" "comp"])

;; first, reverse strings

(defn reverse-str [strings]
  (map #(apply str (reverse %)) strings))
(reverse-str strings)

(def reverse-capitalize (comp (partial map #(clojure.string/capitalize %)) (partial reverse-str)))

(reverse-capitalize strings)                                ;; =>  ("Olleh" "Dlrow" "Erujolc" "Laitrap" "Pmoc")



;; 16 Given a list of numbers, use comp and partial to obtain the absolute difference between the maximum and minimum values in the list.


(def numbers [1 5 -3 7 2 4])






