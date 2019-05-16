/*  
 *	Aethelind Racic
 *	7686783 
 *		Assignment Four
 *		Question One
 */
 
// The correct ordering of tiles.
var sortedOrder = [11, 12, 13, 14, 21, 22, 23, 24, 31, 32, 33, 34, 41, 42, 43, 44];

// The actual ordering of tiles. (This gets scrambled and changed when the user clicks tiles)
var tileOrder = [11, 12, 13, 14, 21, 22, 23, 24, 31, 32, 33, 34, 41, 42, 43, 44];

// Keep track of the position of the empty tile.
var emptyTile = 44;

// Shuffles the entire board using Fisher-Yates shuffle.
function shuffle() {
    for (var i = tileOrder.length - 1; i > 0; i--) {    
		var j = Math.floor(Math.random() * (i + 1));
		var temp = tileOrder[i];
        tileOrder[i] = tileOrder[j];
        tileOrder[j] = temp;
		
		// Update the emptyTile as necessary
		if(tileOrder[i] == 44){
			emptyTile = sortedOrder[i];
		}
    }
	// Once the board is shuffled, update the view.
	updateDisplay();
}

// Cycle through each tile in sortedOrder and update the image appropriately.
function updateDisplay(){
	for(var i = 0; i<sortedOrder.length; i++){
		var t = document.getElementById(sortedOrder[i]);
		t.src = tileOrder[i] + ".png";
	}
}

// Validates a swap move.
function validateClick(tileNum){
	// ensure the number is added rather than concatenated.
	var num = parseFloat(tileNum);

	if (tileNum == emptyTile){
		alert("Illegal move! That tile is the empty tile.")
	} else if (num-10 == emptyTile || num+10 == emptyTile || num-1 == emptyTile || num+1 == emptyTile){
		// checks if the empty tile is adjacent to the clicked position.
		swapTile(num);
    } else {
        alert("Illegal move! That tile is not adjacent to the empty slot.");
    }
}

// Switches the blank tile and tileNum
function swapTile(num){
	var swapNum = document.getElementById(num);
	var emptyNum = document.getElementById(emptyTile);
	
	emptyNum.src = swapNum.src;
	swapNum.src = "44.png";
	
	emptyTile = num;
}

function start(){
	// shuffle the board initially.
	shuffle();
	
	// event listener for each element of puzzle.
	var tile11 = document.getElementById("11");
	tile11.addEventListener("click", function(){validateClick("11");}, false);
	var tile12 = document.getElementById("12");
	tile12.addEventListener("click", function(){validateClick("12");}, false);
	var tile13 = document.getElementById("13");
	tile13.addEventListener("click", function(){validateClick("13");}, false);
	var tile14 = document.getElementById("14");
	tile14.addEventListener("click", function(){validateClick("14");}, false);
	var tile21 = document.getElementById("21");
	tile21.addEventListener("click", function(){validateClick("21");}, false);
	var tile22 = document.getElementById("22");
	tile22.addEventListener("click", function(){validateClick("22");}, false);
	var tile23 = document.getElementById("23");
	tile23.addEventListener("click", function(){validateClick("23");}, false);
	var tile24 = document.getElementById("24");
	tile24.addEventListener("click", function(){validateClick("24");}, false);
	var tile31 = document.getElementById("31");
	tile31.addEventListener("click", function(){validateClick("31");}, false);
	var tile32 = document.getElementById("32");
	tile32.addEventListener("click", function(){validateClick("32");}, false);
	var tile33 = document.getElementById("33");
	tile33.addEventListener("click", function(){validateClick("33");}, false);
	var tile34 = document.getElementById("34");
	tile34.addEventListener("click", function(){validateClick("34");}, false);
	var tile41 = document.getElementById("41");
	tile41.addEventListener("click", function(){validateClick("41");}, false);
	var tile42 = document.getElementById("42");
	tile42.addEventListener("click", function(){validateClick("42");}, false);
	var tile43 = document.getElementById("43");
	tile43.addEventListener("click", function(){validateClick("43");}, false);
	var tile44 = document.getElementById("44");
	tile44.addEventListener("click", function(){validateClick("44");}, false);
}
window.addEventListener("load", start, false);