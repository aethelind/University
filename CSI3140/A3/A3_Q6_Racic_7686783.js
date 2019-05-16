/*  
 *	Aethelind Racic
 *	7686783 
 *		Assignment Three
 *		Question Six
 */

 // set eventListener for translate button.
 function start(){
	var button = document.getElementById( "button" );
	button.addEventListener( "click", translate, false );
}

// handles button press and parses user's input
function translate(){
	var newPhrase = document.getElementById("usr").value.split(" "); // gets an array of strings, each element of the array is one word.
	var res = "";
	
	// For each element in array, get translation. 
	for(var i=0; i<newPhrase.length; i++){
		res += printLatinWord(newPhrase[i]);
	}

	// Now the old phrases are collected, and this new phrase is put at the top of the text area, with the old phrases following it.
	var oldPhrases = document.getElementById("phrases").value;
	oldPhrases = res + "\n" + oldPhrases;
	document.getElementById("phrases").value = oldPhrases;
}

// translates a word from English to Pig Latin.
function printLatinWord(word){
	// combine the word without its first letter, then the first letter, then 'ay'.
	return word.slice(1) + word.slice(0, 1) + "ay ";
}

// ensure function runs once page is loaded.
window.addEventListener( "load", start, false );