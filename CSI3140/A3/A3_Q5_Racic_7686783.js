/*  
 *	Aethelind Racic
 *	7686783 
 *		Assignment Three
 *		Question Five
 */

var tortoise = 1; // position of tortoise
var hare = 1; // position of hare
var ticker = 0; // how many seconds/turns have passed

// Set up text:
//document.writeln("<h1>Tortoise v. Hare</h1>");

function start(){
	var button = document.getElementById( "button" );
	button.addEventListener( "click", race, false );
	
}

function race(){
	tortoise = 1;
	hare = 1;
	ticker = 0;
	var track = document.getElementById("track")
	track.innerHTML = "";
	var pre = document.getElementById("pre")
	pre.innerHTML = "<strong><br>ON YOUR MARK, GET SET<br> BANG!!!<br> AND THEY’RE OFF!!!<br><br></strong>"
	
	// Each turn, the while loop checks if either contestant has passed the finish line.
	while(hare < 70 && tortoise < 70){
		moveH(); // Moves the hare
		moveT(); // Moves the tortoise
		printTrack(); // Prints the track
		ticker++; // increments the timer
	}
	
	var result;
	// Once a contestant has passed the finish line, it is determined who won and the appropriate message is displayed.
	if (hare == tortoise){
		result = "IT’S A TIE<br>";
	} else if (hare < tortoise){
		result = "TORTOISE WINS!!! YAY!!!<br>";
		
	} else if (tortoise < hare){
		result = "HARE WINS. YUCK!<br>";
	}

	// The time the race took is printed as well.
	var post = document.getElementById("post")
	post.innerHTML = result + "Time Elapsed: " + ticker + "s<br>"

}

// Changes position of the hare.
function moveH(){
	// Get random value to determine move
	var moveType = Math.floor(1+Math.random()*10);
	// Selects move type and increments/decrements as needed.
	if(moveType == 1 || moveType == 2){
		// Sleep
	} else if(moveType == 3 || moveType == 4){
		hare += 9; // Big hop
	} else if(moveType == 5){
		hare -= 12; // Big slip
	} else if(moveType == 6 || moveType == 7 || moveType == 8){
		hare += 1; // Small hop
	} else {
		hare -= 2; // Small slip
	}
	// ensures the position does not drop below 1.
	if(hare < 1){
		hare = 1;
	}
}

// Changes position of the tortoise.
function moveT(){
	var moveType = Math.floor(1+Math.random()*10);
	if(moveType == 1 || moveType == 2 || moveType == 3 || moveType == 4 || moveType == 5){
		tortoise += 3; // Fast plod
	} else if(moveType == 6 || moveType == 7){
		tortoise -= 6; // Slip
	} else {
		tortoise += 1; // Slow plod
	} 
	
	if(tortoise < 1){
		tortoise = 1;
	}
}

// Prints the current positions all on one line.
function printTrack(){
	// Prints 70 characters worth of track.
	var track = document.getElementById("track")
	var res = "";
	
	for(var pos = 1; pos <= 70; pos++){
		// Checks for collision between contestants
		if(pos == tortoise && pos == hare){
			res += "OUCH!!!";
			pos += 3;
		} else if (pos == tortoise){ // Otherwise prints the contestants character, or a space.
			res += "T";
		} else if (pos == hare) {
			res += "H"
		} else {
			res += "&nbsp;";
		}
	}
	res += "<br>";
	
	old = track.innerHTML;
	track.innerHTML = old + res;
	
}

// ensure function runs once page is loaded.
window.addEventListener( "load", start, false );