(*** CSI 3120 Assignment 4 ***)
(*** Aethelind Rose Racic ***)
(*** 7686783 ***)
(*** 4.07.0 ***)

(**********************)
(* PROBLEMS 1a and 1b *)
(**********************)

(* See the PDF file for instructions *)

type prop = string

type form =
  | True
  | False
  | Prop of prop 
  | And of form * form
  | Or of form * form
  | Imp of form * form
  | Neg of form

(* Problem 1a *)
let rec pretty_print_form (f:form) : string = 
  match f with
    | True -> "true"
    | False -> "false"
    | Prop p -> p
    | And (f1, f2) -> "(" ^ (pretty_print_form f1) ^ " ^ " ^ (pretty_print_form f2) ^ ")"
    | Or (f1, f2) -> "(" ^ (pretty_print_form f1) ^ " v " ^ (pretty_print_form f2) ^ ")"
    | Imp (f1, f2) -> "(" ^ (pretty_print_form f1) ^ " => " ^ (pretty_print_form f2) ^ ")"
    | Neg f1 -> "(~" ^ (pretty_print_form f1) ^ ")"

let form1 = Imp (And (Prop "p",Prop "q"),Or (Prop "r",Prop "s"))
let form2 = And (Or (Prop "p", Prop "q"), Prop "r")
let form3 = Or (Or (Or (Or (Prop "p1",Neg (Prop "p2")),
                             Imp(And(Prop "p3", Prop "p4"), Prop "r")),
                         Imp(Prop "p5", Prop "q")),
                     Or (Prop "p6",Prop "p"))

(* Problem 1b: Complete the above definition of form3 and
                     uncomment the 3 lines of test code below. *)
let _ = pretty_print_form form1
let _ = pretty_print_form form2
let _ = pretty_print_form form3
 

(*
- : string = "((p ^ q) => (r v s))"
- : string = "((p v q) ^ r)"
- : string = "((((p1 v (~p2)) v ((p3 ^ p4) => r)) v (p5 => q)) v (p6 v p))"
 *)          
