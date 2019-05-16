(*** CSI 3120 Assignment 3 ***)
(*** Aethelind Rose Racic ***)
(*** 7686783 ***)
(*** 4.07.0 ***)
(* If you use the version available from the lab machines via VCL, the
   version is 4.05.0 ***)

(*************)
(* PROBLEM 1 *)
(*************)

(* Below is a definition of a datatype for propositional logic with
   connectives for conjunction, (the AND operator written /\),
   disjunction (the OR operator written \/), and logical implication
   (written =>).  For example, the following is a formula of
   propositional logic.

   (p /\ q) => (r \/ s)

   Note that strings are used to represent propositional variables.

   The type env is used to map propositional variables to boolean values.
 *)

type prop = string

type form =
  | True
  | False
  | Prop of prop 
  | And of form * form
  | Or of form * form
  | Imp of form * form

type env = (prop * bool) list

(* Problem 1a: Write a function that takes a "prop list list" as input
   and returns its value as a "form" in conjunctive normal form (CNF).
   A CNF expression is represented as a series of OR expressions
   joined together by AND.

   e.g. transform this:

   [ ["x1"; "x2"]; ["x3"; "x4"; "x5"]; ["x6"] ]

   into an expression of type "form" that represents the formula

   (x1 \/ x2) /\ (x3 \/ x4 \/ x5) /\ x6

   Note that \/ and /\ are associative, so for example
   (x3 \/ x4 \/ x5) represents ((x3 \/ x4) \/ x5) and is equivalent
   to (x3 \/ (x4 \/ x5)), can thus be represented as
   Or (Or (Prop "x3",Prop "x4"),Prop "x5")  or
   Or (Prop "x3",Or (Prop "x4",Prop "x5"))

   If an inner list is empty, it should be replaced by False, e.g.,

   [ ["x1"; "x2"]; []; ["x6"] ] as input results in output:

   (x1 \/ x2) /\ False /\ x6

   If the input list is empty, return True.  

   Optional: you may want to use map (from class) and/or reduce
   (from Tutorial 1), which are included below *)

let rec map (f:'a -> 'b) (xs: 'a list) : 'b list =
  match xs with
  | [] -> []
  | hd::tl -> (f hd) :: (map f tl)

let rec reduce (f:'a -> 'b -> 'b) (u:'b) (xs:'a list) : 'b =
  match xs with
  | [] -> u
  | hd::tl -> f hd (reduce f u tl);;

(* In this and all exercises, uncomment the function header, and
   sample tests, fill in the missing parts.  Be sure to test your code
   fully using your own tests also.  Also, be sure to add "rec" to the
   header if you define a recursive function. *)

let cnf (pll:prop list list) : form =

let test1a_1() = cnf [ ["x1"; "x2"]; ["x3"; "x4"; "x5"]; ["x6"] ];;
let test1a_2() = cnf [];;
let test1a_3() = cnf [ ["x1"; "x2"]; []; ["x6"] ];;
 
let p1 : prop = "x1";;
let p2 : prop = "x2";;
let p3 : prop = "x3";;
let p4 : prop = "x4";;



(* Problem 1b: Define a new datatype called form' that replaces form,
   but is just like form except that it also represents negation (the
   NOT operator written ~).  Your code for both 1b and 1c operate only
   on the new type form' (which does not depend at all on the type
   form from a1).
   This should be easy. *)


type form' =
  | True
  | False
  | Prop of prop 
  | And of form' * form'
  | Or of form' * form'
  | Imp of form' * form'
  | Not of form'

(* Problem 1c: Define a function that takes a form' and an env as
   arguments and computes the truth value. For example, if

   e = [("p",true);("q",false);("r",true);("s",false)]

   then your function should return true for the formula mentioned above:
   (p /\ q) => (r \/ s)
   
   See the truth tables at https://en.wikipedia.org/wiki/Truth_table
   for information on how to compute truth values of propositional
   formulas.

   You may use the lookup function below.  Your function should return
   a bool. Assume that any of the variables in the input form' that do
   not appear in the input env have the value false.
 *)

(* return the value of proposition p in environment e *)
let rec lookup (e:env) (p:prop) : bool option =
  match e with
  | [] -> None
  | (hd_p,hd_b)::tl_e -> 
      if p=hd_p then Some hd_b
      else lookup tl_e p

(*
let eval_prop (f:form') (e:env) : bool =

The following may be useful for testing:
let e = [("p",true);("q",false);("r",true);("s",false)]
let test1b (f:form') : bool = eval_prop f e
 *)

(*************)
(* PROBLEM 2 *)
(*************)

(* Below is an implementation of a module that implements a dictionary
   data structure (also called associative array), see
   https://en.wikipedia.org/wiki/Associative_array.  In this
   implementation, keys are strings and dictionaries are lists.  (Note
   that there are many more efficient ways to implement this data
   structure, which we ignore here.) *)

(* 
module type DICT =
  sig
    type ('key,'value) dict
    TODO: Insert your code for Problem 2c here
  end
 *)

(* TODO: Uncomment the module type in the line immediately following
   this comment after completing Problem 2c *) 
module ListDict (* : DICT *) =
  struct
    type ('key,'value) dict = ('key * 'value) list

    (* Module invariant: the operations are implemented so that there
       is never more than one occurrence of a given key, and the list
       is always in sorted order. *)
              
    let empty () = []

    let rec lookup d k =
      match d with
      | [] -> None
      | (hd_k,hd_v)::tl -> if hd_k=k then Some hd_v
                           else lookup tl k

    let rec member d k =
      match d with
      | [] -> false
      | (hd_k,hd_v)::tl -> hd_k=k || member tl k

    let rec insert d k v =
      match d with
      | [] -> [(k,v)]
      | (hd_k,hd_v)::tl -> if (k < hd_k || k = hd_k)
                           then (k,v)::(hd_k,hd_v)::tl
                           else (hd_k,hd_v)::insert tl k v

    let rec remove d k = d  (* TODO: Add types for problem 2b *)
      (* TODO: Insert your code for Problem 2a here *)
  end

(* Problem 2a: Replace the implementation of "remove" with a correct
   one, i.e., one that removes an element from the dictionary if it is
   present in the dictionary, otherwise leaves the dictionary
   unchanged.  Note that because of the way that insert is
   implemented, there may be more than one value associated with a
   given key k, but because the dictionary is sorted, these pairs will
   appear next to each other in the list.  In other words, there could
   be a segment of the form 
   ...::(k,v1)::(k,v2)::...::(k,vn)::...
   in the dictionary where all keys to the left have lower value and
   all keys to the right have greater value.  Be sure to remove
   all pairs having key k. *)

(* Problem 2b: Note that when you load this file into OCaml, the types
   that are inferred include 'a and 'b.  This type is more general
   than we intend it to be.  Add types to all of the function
   headers in the ListDict module so that all occurrences of
   'a and 'b disappear.  Use instead the types 'key, 'value, and
   ('key,'value) dict.
 *)

(* Problem 2c: Complete the DICT signature above.  Include
   signature items for all of the functions that occur in
   ListDict.  Add comments describing what each operation is.
   Once you are done, remove the comment from the
   first line of the ListDict module definition.
 *)

(* Problem 2d: Reimplement your solution to Problem 1c using the
   following new definition of the lookup function.  Develop code for
   testing similar to what you did for Problem 1c.  env0 and env1
   below provide some initial code that you can build on.
   Note: This is a simple exercise, showing how easy it is to swap
   one implementation with another. *)

(* return the value of proposition p in environment e *)
type env' = (string,bool) ListDict.dict
let lookup' (e:env') (p:prop) : bool option =
  ListDict.lookup e p

let env0 = ListDict.empty()
let env1 = ListDict.insert env0 "p" true

(*
let eval_prop' ... = ...
 *)

(* Problem 2e: Note that the above dictionary is a functional version.
   Design an imperative version of the DICT signature, but include
   only the empty, lookup, and insert operations.  (Omit member and
   remove.)  In this version, the empty operation creates and returns
   a mutable dictionary.  The insert operation takes a dictionary as
   input, but instead of returning a new dictionary, it modifies the
   input dictionary.  Note: there will only be very minor
   modifications, similar to the modifications made to STACK to get
   IMP_STACK in the course notes. *)

module type IMP_DICT =
  sig
    type ('key,'value) dict
    (* TODO: Insert your code for Problem 2e here *)
  end

(* Problem 2f: Implement a module with type IMP_DICT.  Hint: do so by
   making a copy of the contents of the ListDict module, removing the
   functions member and remove, and modifying the rest as needed.  *)

(*
module ImpListDict : IMP_DICT =
  struct
    type ('key,'value) dict = TODO: fill in the type and the definitions
                                    of empty, lookup, and insert
  end

Some code to help test your implementation:

type env'' = (string,bool) ImpListDict.dict
let lookup'' (e:env'') (p:prop) : bool option =
  ImpListDict.lookup e p

let e'' = ImpListDict.empty()
let _ = ImpListDict.insert e'' "p" true

 *)    
