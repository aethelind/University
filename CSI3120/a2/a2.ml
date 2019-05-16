(*** CSI 3120 Assignment 2 ***)
(*** Aethelind Rose Racic ***)
(*** 7686783 ***)
(*** 4.07.0 ***)
(* If you use the version available from the lab machines via VCL, the
   version is 4.05.0 ***)

(*************)
(* PROBLEM 1 *)
(*************)

(* For each part of problem 1, explain in the given string why the code
   will not typecheck, then follow the instructions for that part to
   change the code so that it typechecks while keeping the same values
   as intended by the erroneous code. Once you have done so, uncomment
   the fixed expression for submission.
*)

(* Problem 1a - Give your explanation in exp1a and then fix the
   right-hand-side of the assignment to match the listed type. 
   (Do not change the left-hand-side.)
*)


let exp1a : string = "Numbers were incorrectly separated with commas instead of semicolons which are used for list creation."
let prob1a : int list = [1; 2; 3;]


(* Problem 1b - Give your explanation in exp1b and then fix the type
   of variable prob1b to match the type of the expression on the
   right-hand-side of the assignment. (Do not change the
   right-hand-side.)
 *)


let exp1b : string = "Brackets are added to remove ambiguity."
let prob1b : (string * int) list = [("CSI", 3125); ("CSI", 3525)]


(* Problem 1c - Give your explanation in exp1c and then fix the
   right-hand-side of the expression to match the variable prob1c's
   listed type. 
   (Do not change the left-hand-side.)  
*)

let exp1c : string = "Combining multiple float lists does not result in a list of individual floats, it results in a list of lists. Therefore I changed the RHS to separate each value out appropriately."
let prob1c : float list = 2.0 :: 3.0 :: 4.0 :: 5.0 :: []
(* 
	An alternative solution..
	let prob1c : float list = 2.0 :: 3.0 :: 4.0 :: 5.0 :: []
	let prob1c : float list = [2.0; 3.0; 4.0; 5.0]
*)


(*************)
(* PROBLEM 2 *)
(*************)

(* Fill in expressions to satisfy the following types: 
 *
 * NOTE: for option, list, and function types, you must 
 * provide a nontrivial answer. For a list that means a 
 * non-empty one, for an option type that means a Some 
 * construction, and for a function, that means using 
 * its arguments to generate the return value.
 * example problems:
 *   let x : int option = ???
 *   let y : int list = ???
 *   let f (x: int) (y: int) : int = ???
 * incorrect answers:
 *   let x : int option = None
 *   let y : int list = []
 *   let f (x: int) (y: int) : int = 7
 * possible correct answers:
 *   let x : int option = Some 1
 *   let y : int list = [1]
 *   let y : int list = [1; 2]
 *   let y : int list = 1 :: [2]
 *   let f (x: int) (y: int) : int = x + y
 *   let f (x: int) (y: int) : int = 
 *         String.length  ((string_of_int x) ^ (string_of_int y))
 *)

(*>* Problem 2a *>*)

let prob2a : (float * (string * int) option list) list = [(1.0, [(Some ("CEG3185", 8)); (Some ("CSI3130", 6));]);(2.0, [(Some ("CSI3120", 7)); (Some ("CSI4139", 9));]);]

(*>* Problem 2b *>*)
(* a student is a (name, age option) pair *)

type student = string * int option

let prob2b : (student list option * int) list = [(Some [("Jenny", Some 14); ("Bob", Some 14); ("Tom", Some 13);], 9); (Some [("Danielle", Some 17); ("Carlos", Some 18); ("Vince", Some 18);], 12)]

(*>* Problem 2c *>*)
(* Fill in a valid function call to foo to make prob2c typecheck *)

let prob2c =
  let rec foo bar =
    match bar with
    | (a, (b, c)) :: xs -> if a then (b + c + (foo xs)) else foo xs
    | _ -> 0
  in 
  foo [(true, (10, 20)); (false, (1000, 1000)); (true, (15, 13))]


(*************)
(* PROBLEM 3 *)
(*************)

(* Consider the following terribly written function: *)

let rec zardoz ls acc =
  if (((List.length (ls@[])) = 1) = true) then ((List.hd(ls)) + (acc))
  else if (((List.length ls) = 0) = true) then acc
  else
    let hd = List.hd(ls) in
        let tl = List.tl(ls) in
      let ans = (hd) + (acc) in
    let ans = zardoz tl ans in
        ans

(* Rewrite the code above so that it does the same thing
 * but style-wise is far superior.  
 * Be sure to provide types for the function's arguments and to 
 * call itself (not the original zardoz) recursively as needed.
 * You may want to write some assert statements
 * to check that your function is doing the same thing as zardoz. *)

let rec myzardoz (ls : int list) (acc : int) : int = 
	if (ls == [])
	then acc
	else myzardoz (List.tl(ls)) (acc + List.hd(ls))

(*************)
(* PROBLEM 4 *)
(*************)

(* Given a list of integers l, return a new list obtained by reading
   off adjacent groups of identical elements in l. For example, when
   the input is:

l = [2; 2; 2]

the output should be:

[3; 2]

because l is exactly "three twos." Similarly, if when the input is:

l = [1; 2; 2]

the output should be:

[1; 1; 2; 2]

because l is exactly "one ones, then two twos."

When l is the empty list, the result is also the empty list.

For full credit your solution should be a linear time solution.  *)


let count_sequences (xs : int list) : int list = 
	if (xs == []) then []
	else 
		let rec ff (prev : int) (count : int) (xxs : int list) (outxs : int list) =
			if (xxs == []) then (outxs@[count]@[prev])
			else if (List.hd(xxs) == prev)
				then ff prev (count + 1) (List.tl(xxs)) outxs
				else ff (List.hd(xxs)) 1 (List.tl(xxs)) (outxs@[count]@[prev])
		in ff (List.hd(xs)) 0 xs []

(*************)
(* PROBLEM 5 *)
(*************)
          
(* Write a function that flattens a list of lists in to a single
 * list with all of the elements in the same order they appeared in 
 * the original list of lists. eg:
 *
 * flatten [[1;2;3]; []; [4]; [5;6]] = [1;2;3;4;5;6] 
 * flatten [[]; ['e';'d']; ['a';'b';'c']] = ['e';'d';'a';'b';'c'] 
 *)

(*)
let rec flatten (xss:'a list list) : 'a list = 
(*)