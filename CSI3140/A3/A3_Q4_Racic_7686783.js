/*  
 *	Aethelind Racic
 *	7686783 
 *		Assignment Three
 *		Question Four
 */
 
 function start(){
	var a = new Array(1000); // Array to be analyzed.
	var str = "<ul>"; // String which will hold all the found primes. 
	
	// Each value in the array is initialized to 1
	for(var i=0; i<a.length; i++){
		a[i] = 1;
	}
	
	// Base case: 0 and 1 are not prime.
	a[0] = 0;
	a[1] = 0;
	
	// loop over each element in the array.
	for(var i=2; i<a.length; i++){
		if(a[i] == 1){
			// if a[i] is 1, then i is prime, so it is added to the primes list
			str += "<li>" + i + "</li>";
			// all multiples of i are marked as composite
			var j = i+i;
			while(j<a.length){
				a[j] = 0;
				j += i;
			}
		}
	}
	str += "</ul>";
	var primes = document.getElementById( "primes" );
	primes.innerHTML = str;
}

// ensure function runs once page is loaded.
window.addEventListener( "load", start, false );