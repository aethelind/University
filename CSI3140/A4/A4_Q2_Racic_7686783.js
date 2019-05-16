/*  
 *	Aethelind Racic
 *	7686783 
 *		Assignment Four
 *		Question Two
 */
 
/* Thinking of the board as a grid with coordinate system as..
  	 1 2 3 4
	1
	2
	3
	4
*/
 
// Keeps track of [grid location] : [tile's correct location.]
var tiles = {11:11, 12:12, 13:13, 14:14, 21:21, 22:22, 23:23, 24:24, 31:31, 32:32, 33:33, 34:34, 41:41, 42:42, 43:43, 44:44};

// Keeps track of shuffled order and appropriate location.
var tileOrder = [11, 12, 13, 14, 21, 22, 23, 24, 31, 32, 33, 34, 41, 42, 43, 44];
var sortedOrder = [11, 12, 13, 14, 21, 22, 23, 24, 31, 32, 33, 34, 41, 42, 43, 44];

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
	
	// update key value pairs with shuffled order
	var x = 0;
	for(var key in tiles){
		tiles[key] = tileOrder[x];
		x++;
	}
	
	// Once the board is shuffled, update the view.
	updateDisplay();
}

// Cycle through each tile in map and updates the image appropriately.
function updateDisplay(){
	for(var key in tiles){
		var t = document.getElementById(key);
		t.src = tiles[key] + ".png";
	}
}

// Validates a swap move.
function validateClick(tileNum){
	// ensure the number is added rather than concatenated.
	var num = parseFloat(tileNum);

	if (tileNum == emptyTile){
		alert("Illegal move! That tile is the empty tile.");
	} else if (num-10 == emptyTile || num+10 == emptyTile || num-1 == emptyTile || num+1 == emptyTile){
		// checks if the empty tile is adjacent to the clicked position.
		swapTile(num);
    } else {
        alert("Illegal move! That tile is not adjacent to the empty slot.");
    }
}

// Switches the blank tile and clicked tile.
function swapTile(num){
	var swapNum = document.getElementById(num);
	var emptyNum = document.getElementById(emptyTile);
	
	emptyNum.src = swapNum.src;
	swapNum.src = "44.png";
	
	// update key/value pairs
	tiles[emptyTile] = tiles[num];
	tiles[num] = 44;

	emptyTile = num;
	gameOver(); // checks if game has been won.
}

// Checks if game has been won.
function gameOver(){
	var flag = true;
	
	// checks if every key/value pair matches.
	for(var key in tiles){
		if(key != tiles[key]){
			flag = false;
		}
	}
	
	if(flag){
		var restart = confirm("YOU WIN!! Would you like to play again?");
		if(restart){
			shuffle();
		}
	}
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