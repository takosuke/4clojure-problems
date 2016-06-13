; Penultimate element
; Write a function which returns the second to last element from a sequence.
; (= (__ (list 1 2 3 4 5)) 4)

(comp second reverse)

; Nth-element
; Write a function which returns the Nth element from a sequence.
; (= (__ '(4 5 6 7) 2) 6)
; Special Restrictions
; nth

(= (__ '(4 5 6 7) 2) 6)

; Count a sequence
; Write a function which returns the total number of elements in a sequence.
; Special Restrictions: count
; (= (__ '(1 2 3 3 1)) 5)

#(apply + (map (fn [_] 1) %))

; Sum it all up
; Write a function which returns the sum of a sequence of numbers.
; (= (__ (list 0 -2 5 5)) 8)

apply +

; Find the odd numbers
; Write a function which returns only the odd numbers from a sequence.
; (= (__ #{1 2 3 4 5}) '(1 3 5))

filter odd?

; Reverse a Sequence
; (= (__ [1 2 3 4 5]) [5 4 3 2 1])
; Special Restrictions
; reverse
; rseq

reduce conj ()

; Palindrome detector 
; Write a function which returns true if the given sequence is a palindrome.
; (true? (__ "racecar"))

#(= (seq %) (reverse %))

; Fibonacci sequence 
; Write a function which returns the first X fibonacci numbers.
; (= (__ 8) '(1 1 2 3 5 8 13 21))

#(take % ( map last 
               (iterate (fn [[x y]] [y (+ x y)] )[0 1]))) 

; Maximum Value
; Write a function which takes a variable number of parameters and returns the maximum value.
; Special Restrictions
; max
; max-key
; (= (__ 1 8 3 4) 8)

#(last(sort %&))

; Get the caps
; Write a function which takes a string and returns a new string containing only the capital letters.
; (= (__ "HeLlO, WoRlD!") "HLOWRD")
; (empty? (__ "nothing"))

#(apply str (re-seq #"[A-Z]" %))

; Duplicate a sequence
; Write a function which duplicates each element of a sequence.
; (= (__ [1 2 3]) '(1 1 2 2 3 3))'

mapcat #(list % %)

; Implement range
; Write a function which creates a list of all integers in a given range.
; Special Restrictions
; range
; (= (__ 1 4) '(1 2 3))'

#(take (- %2 %) (iterate inc %))

; Compress a Sequence
; Write a function which removes consecutive duplicates from a sequence.
; (= (apply str (__ "Leeeeeerrroyyy")) "Leroy")

#(map first (partition-by identity %))

; Factorial Fun
; Write a function which calculates factorials.
; (= (__ 8) 40320)

#(apply * (range 1 (inc %)))

; Flatten a Sequence 
; Write a function which flattens a sequence.
; (= (__ '((1 2) 3 [4 [5 6]])) '(1 2 3 4 5 6))
; Special Restrictions
; flatten

#(filter (complement coll?) (tree-seq coll? identity %))

; Interleave two Sequences
; Write a function which takes two sequences and returns the first item from each, then the second item from each, then the third, etc.
; (= (__ [1 2 3] [:a :b :c]) '(1 :a 2 :b 3 :c))'
; Special Restrictions
; interleave

mapcat list

; Replicate a sequence
; Write a function which replicates each element of a sequence a variable number of times.
; (= (__ [:a :b] 4) '(:a :a :a :a :b :b :b :b))'

#(mapcat (fn [x] (repeat %2 x)) %)

; Interpose a Seq
; Write a function which separates the items of a sequence by an arbitrary value.
; Special Restrictions
; interpose
; (= (__ 0 [1 2 3]) [1 0 2 0 3])

#(drop-last (mapcat list %2 (repeat %1)))

; Pack a Sequence
; Write a function which packs consecutive duplicates into sub-lists.
; (= (__ [1 1 2 1 1 1 3 3]) '((1 1) (2) (1 1 1) (3 3)))')

partition-by identity

; Drop Every Nth Item
; Write a function which drops every Nth item from a sequence.
; (= (__ [1 2 3 4 5 6 7 8] 3) [1 2 4 5 7 8])

(fn [xs n] 
    (mapcat
         #(if (= n (count %)) (drop-last %) %) (partition-all n xs)))

; Split a sequence
; Write a function which will split a sequence into two parts.
; (= (__ 3 [1 2 3 4 5 6]) [[1 2 3] [4 5 6]])
; Special Restrictions
; split-at

#(vector (take %1 %2) (drop %1 %2))

; A Half-Truth
; Write a function which takes a variable number of booleans. 
; Your function should return true if some of the parameters are true, 
; but not all of the parameters are true. Otherwise your function should return false.
; (= true (__ true true true false))

#(= (count (into #{} %&)) 2)

; Map Construction
; Write a function which takes a vector of keys and a vector of values and constructs a map from them.
; (= (__ [:a :b :c] [1 2 3]) {:a 1, :b 2, :c 3})
; Special Restrictions
; zipmap

#(apply assoc {} (interleave %1 %2))

; Greatest Common Divisor
; (= (__ 1023 858)

#(loop 
   [div (max % %2) quo (min % %2)]
   (if (= 0 (rem div quo))  
     quo      
     (recur  quo (rem div quo))))

; Set Intersection
; (= (__ #{0 1 2 3} #{2 3 4 5}) #{2 3})
; Special Restrictions
; intersection

#(loop [s1 %1 so #{}]
   (if (empty? s1)
     so
     (recur (disj s1 (first s1)) (if (contains? %2 (first s1)) (conj so (first s1)) so))))

; Comparisons
; For any orderable data type it's possible to derive all of the basic comparison operations 
; (<, ≤, =, ≠, ≥, and >) from a single operation (any operator but = or ≠ will work).
;  Write a function that takes three arguments, a less than operator for the data and two items to compare. 
; The function should return a keyword describing the relationship between the two items. 
; The keywords for the relationship between x and y are as follows:
; x = y → :eq
; x > y → :gt
; x < y → :lt
; (= :eq (__ (fn [x y] (< (count x) (count y))) "pear" "plum"))

#(cond (%1 %2 %3) :lt (%1 %3 %2) :gt :else :eq)

; Re-implement iterate
; Given a side-effect free function f and an initial value x write a function which returns
; an infinite lazy sequence of x, (f x), (f (f x)), (f (f (f x))), etc.
; (= (take 5 (__ #(* 2 %) 1)) [1 2 4 8 16])

(fn my-iter [myfn x]
  (cons x (lazy-seq (my-iter myfn (myfn x)))))

; Simple Closures
; Lexical scope and first-class functions are two of the most basic building blocks of a functional 
; language like Clojure. When you combine the two together, you get something very powerful called lexical 
; closures. With these, you can exercise a great deal of control over the lifetime of your local bindings, 
; saving their values for use later, long after the code you're running now has finished.
; 
; It can be hard to follow in the abstract, so let's build a simple closure. Given a positive 
; integer n, return a function (f x) which computes xn. Observe that the effect of this is to preserve 
; the value of n for use outside the scope in which it is defined.
; (= [1 8 27 64] (map (__ 3) [1 2 3 4]))

partial #(reduce * (repeat %1 %2))

; Product Digits
; Write a function which multiplies two numbers and returns the result as a sequence of its digits.
; (= (__ 999 99) [9 8 9 0 1])

(fn [x y] (map #(read-string (str %)) (seq (str (* x y)))))

; dot product
; (= 3 (__ [1 1 1] [1 1 1]))

#(apply + (map * %1 %2))

; Cartesian product
; (= (__ #{"ace" "king" "queen"} #{"♠" "♥" "♦" "♣"})
;   #{["ace"   "♠"] ["ace"   "♥"] ["ace"   "♦"] ["ace"   "♣"]
;     ["king"  "♠"] ["king"  "♥"] ["king"  "♦"] ["king"  "♣"]
;     ["queen" "♠"] ["queen" "♥"] ["queen" "♦"] ["queen" "♣"]})

#(set(for [x %1 y %2] [x y]))

