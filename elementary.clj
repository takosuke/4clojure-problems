; A nil key
; Write a function which, given a key and map, returns true iff the map contains an entry with that key and its value is nil.
; (true?  (__ :a {:a nil :b 2}))

#(and (contains? %2 %1) (= nil (%1 %2)))

; Map defaults
; Write a function which takes a default value and a sequence of keys and constructs a map.
; (= (__ 0 [:a :b :c]) {:a 0 :b 0 :c 0})

#(zipmap %2 (repeat %1))

