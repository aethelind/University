; Aethelind Rose Racic
; 7686783

; CSI2120 Winter 2018
; Assignment Four
; Question Three

#lang racket

;; MAIN ROUTINES AND FILE I/O
; try: (findRoute "wading-pools-scheme.txt")
(define findRoute (lambda (filetxt) (let* ((T (preorder (construct-tree (sort (from-file filetxt) (lambda (a b) (< (caddr a) (caddr b)))))))                                        
                                           (S (combine-solution T (sum-list T))))
                                      S)))

; file writing
; try: (saveRoute (findRoute "wading-pools-scheme.txt") "scheme-solution.txt")
(define saveRoute (lambda (route filetxt)
                  (let ((p (open-output-file filetxt)))
                    (let f ((route route))
                      (if (not (null? route))
                          (begin                        
                            (display (caar route) p)
                            (fprintf p " ")                        
                            (display (cadar route) p)
                            (fprintf p "~n")
                            (f (cdr route)))
                          (close-output-port p))))
                  #t))

; file reading (as seen in notes)
(define from-file (lambda (filetxt)
                    (let ((p (open-input-file filetxt)))
                      (let f ((x (read p)))   
                        (if (eof-object? x)  
                            (begin
                              (close-input-port p)
                              '())
                            (cons x (f (read p))))))))
;;

;; TREE
; tree construction
(define construct-tree (lambda (pools)
                         (let construct ((T (list (car pools)))
                                         (current (cadr pools))
                                         (done (list (car pools)))
                                         (todo (cddr pools))) 
                           (if (null? todo) 
                               (insert T (closest-pool done current) current) 
                               (construct (insert T (closest-pool done current) current) (car todo) (cons current done) (cdr todo))))))

; distance calculation
(define dist (lambda (p1 p2)
               (let ((lat1 (cadr p1))
                     (lon1 (caddr p1))
                     (lat2 (cadr p2))
                     (lon2 (caddr p2))
                     (rad (lambda (deg) (* pi (/ deg 180)))))
                 (* 6371.0 (* 2 (asin (sqrt
                                       (+ (expt (sin (/ (- (rad lat1) (rad lat2)) 2)) 2)
                                          (* (* (cos (rad lat1)) (cos (rad lat2)))
                                             (expt (sin (/ (- (rad lon1) (rad lon2)) 2)) 2))))))))))

; get pool closest to p from lst
(define closest-pool (lambda (lst p)
                       (let closest ((lst lst)
                                   (p p)
                                   (minpool '())
                                   (mindist 100000))
                         (cond
                           ((empty? lst) minpool)
                           ((< (dist p (car lst)) mindist) (closest (cdr lst) p (car lst) (dist p (car lst)))) 
                           (else (closest (cdr lst) p minpool mindist))
                           ))))

; insert a new child below parent in tree T
(define insert (lambda (T parent child)
                 (cond
                   ((null? T) '())
                   ((equal? parent (car T)) (append T (list (list child)))) 
                   ((string? (caar T)) (cons (car T) (insert (cdr T) parent child))) 
                   (else (cons (insert (car T) parent child) (insert (cdr T) parent child))))))
;;

;; SOLUTION + TRAVERSAL
; gets pools from tree in order
(define preorder (lambda (T)
                  (cond ((null? T) '())
                        ((not (string? (caar T))) (append (preorder (car T)) (preorder (cdr T))))
                        (else (cons (car T) (preorder (cdr T)))))))

; finds distance sum for each line
(define sum-list (lambda (pools)
                   (let summation ((todo (cddr pools))
                                   (current (cadr pools))
                                   (last (car pools))
                                   (sum '(0.0)))
                     (let ((sum (append (list (+ (car sum) (dist current last))) sum)))
                       (if (null? todo)
                           (reverse sum)
                           (summation (cdr todo) (car todo) current sum ))))))

; combine list of pools with list of distance sums
(define combine-solution (lambda (route sums)
                           (if (or (null? route) (null? sums))
                               '()
                               (cons (list (caar route) (car sums)) (combine-solution (cdr route) (cdr sums))))))
;;