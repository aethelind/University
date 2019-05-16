; Aethelind Rose Racic
; 7686783

; CSI2120 Winter 2018
; Assignment Two

#lang racket

;;;;;;;;;;;;;;;
;; QUESTION ONE
;;;;;;;;;;;;;;;;

(define rad (lambda (deg) (* pi (/ deg 180)) )) ; helper function to convert to radians

(define distanceGPS (lambda (lat1 lon1 lat2 lon2)
                      (* 6371.0 (* 2 (asin (sqrt
                                            (+
                                             (expt (sin (/ (- (rad lat1) (rad lat2)) 2)) 2)
                                             (* (* (cos (rad lat1)) (cos (rad lat2)))
                                                (expt (sin (/ (- (rad lon1) (rad lon2)) 2)) 2)))))))
                      )) 

;;;;;;;;;;;;;;;
;; QUESTION TWO
;;;;;;;;;;;;;;;;

; part A
(define absDiffA (lambda (L1 L2)
                   (cond
                     ((and (null? L1) (null? L2)) '()) ; both lists at their end
                     ((null? L1) (cons (abs (car L2)) (absDiffA '() (cdr L2))) ) ; if L1 is empty, only consider L2
                     ((null? L2) (cons (abs (car L1)) (absDiffA (cdr L1) '())) ) ; if L2 is empty, only consider L1
                     (else (cons (abs(- (car L1) (car L2))) (absDiffA (cdr L1) (cdr L2)))) ; if neither are empty, absdiff is evaluated
                     )))

; part B
(define absDiffB (lambda (L1 L2)
                   (cond
                     ((or (null? L1) (null? L2)) '()) ; if either of the lists are empty, end recursion               
                     (else (cons (abs(- (car L1) (car L2))) (absDiffB (cdr L1) (cdr L2)))) ; if neither are empty, absdiff is evaluated
                     )))
;;;;;;;;;;;;;;;;;
;; QUESTION THREE
;;;;;;;;;;;;;;;;;
; helper function which finds how many occurences of element there are in list.
(define how-many (lambda (element list)
                   (if (member element list) ; if element is a member of list
                       (if (eqv? element (car list)) ; and if element is car of list
                           (+ 1 (how-many element (cdr list))) ; add one to count, continue to cdr
                           (how-many element (cdr list))) ; else continue without adding one
                       0))) ; theres 0 occurences of element in list

; helper function which removes duplicates from list. as seen in class.
(define remove-duplicates (lambda (list)
                            (cond
                              ((null? list) '()) 
                              ((member (car list) (cdr list)) (remove-duplicates (cdr list))) ; if car is repeated, remove it now
                              (else (cons (car list) (remove-duplicates (cdr list)))) ; if car is not repeated, it is left in
                              )))

; part A
(define duplicatePair (lambda (L) ; map how-many to the given list, and remove any duplicates, elements combined with cons
                        (remove-duplicates (map (lambda (element) (cons element (how-many element L))) L))))

; part B
(define duplicateList (lambda (L) ; map how-many to the given list, and remove any duplicates, elements combined with list
                        (remove-duplicates (map (lambda (element) (list element (how-many element L))) L))))

; part C
(define duplicateListSorted (lambda (L) ; sorts duplicateList with lambda comparing their counts (cadr)
                              (sort (duplicateList L) (lambda (a b) (> (cadr a) (cadr b))))))

;;;;;;;;;;;;;;;;
;; QUESTION FOUR
;;;;;;;;;;;;;;;;

(define children (lambda (n lst) (cond
                                   ((null? lst) #f) ; if theres no list, return #f 
                                   ((eqv? n (car lst)) (sort (map car (cdr lst)) >)) ; if n is found, children of node are obtained and sorted
                                   ((list? (car lst)) (or (children n (car lst)) (children n (cdr lst)))) ; if current element is a list, it is recursed through as well as cdr
                                   (else (children n (cdr lst)))))) ; otherwise, just cdr is recursed through
