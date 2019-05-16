 /* FINAL PROJECT	Dec. 9th, 2017.			*
 * name: 			Aethelind Rose Racic 	*
 * student number: 	7686783 				*/

//RollOfDice.h

#include <random>
#include <iostream>
#include <vector>
#include "Dice.h"

using namespace std;

//////////////////////////////////////
// RollOfDice
struct RollOfDice{
	vector<Dice> data; //container
	RandomDice rd;
	
	//constructor
	RollOfDice(RandomDice& rd);
	
	//methods
	void roll(); //rolls all die in vector
	operator int();	// converts data to int
	RollOfDice pair(Dice d1, Dice d2); // rolls two die, returns rod
};

//output operator
ostream& operator<<(std::ostream& os, RollOfDice& rod);
//////////////////////////////////////

