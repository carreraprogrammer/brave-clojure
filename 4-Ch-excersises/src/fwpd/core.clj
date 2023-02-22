(ns fwpd.core)

;; Define the filename for the suspects CSV file
(def filename "suspects.csv")

;; Read the contents of the CSV file into a string
(slurp filename)

;; Define the keys that will be used in the maps for each row
(def vamp-keys [:name :glitter-index])

;; Define a function to convert a string to an integer
(defn str->int
  [str]
  (Integer. str))

;; Define a map of conversion functions for each key
(def conversions {:name identity
                  :glitter-index str->int})

;; Define a function to convert a value based on its key
(defn convert
  [vamp-key value]
  ((get conversions vamp-key) value))

;; Test the convert function with a sample value
(convert :glitter-index "3")

;; Define a function to parse a CSV string into rows of columns
(defn parse
  "Convert a CSV into rows of columns"
  [string]
  (map #(clojure.string/split % #",")
       (clojure.string/split (clojure.string/replace string #"\r" "") #"\n")))

;; Test the parse function using the contents of the CSV file
(parse (slurp filename))
; => (["Edward Cullen" "10"] ["Bella Swan" "0"] ["Charlie Swan" "0"]
; ["Jacob Black" "3"] ["Carlisle Cullen" "6"])

;; Define a function to convert rows of columns into a seq of maps
(defn mapify
  "Return a seq of maps like {:name "Edward Cullen" :glitter-index 10}"
  [rows]
  (map (fn [unmapped-row]
         (reduce (fn [row-map [vamp-key value]]
                   (assoc row-map vamp-key (convert vamp-key value)))
                 {}
                 (map vector vamp-keys unmapped-row)))
       rows))

;; Test the mapify function using the results of the parse function
(first (mapify (parse (slurp filename))))

;; Define a function to filter records based on a minimum glitter index
(defn glitter-filter
  [minimum-glitter records]
  (filter #(>= (:glitter-index %) minimum-glitter) records))

;; Test the glitter-filter function with a sample minimum glitter index
(glitter-filter 3 (mapify (parse (slurp filename))))
({:name "Edward Cullen", :glitter-index 10}
 {:name "Jacob Black", :glitter-index 3}
 {:name "Carlisle Cullen", :glitter-index 6})