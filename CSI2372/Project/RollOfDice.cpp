 /* FINAL PROJECT	Dec. 9th, 2017.			*
 * name: 			Aethelind Rose Racic 	*
 * student number: 	7686783 				*/

//RollOfDice.cpp

#include "RollOfDice.h"

#include <random>
#include <iostream>
#include <vector>
#include "Dice.h"

using namespace std;

//////////////////////////////////////
// RollOfDice
RollOfDice::RollOfDice(RandomDice& rando) : rd(rando){
	// fill our data vector with common dice
	data.push_back(Dice(Colour::RED, 1));
	data.push_back(Dice(Colour::YELLOW, 2));
	data.push_back(Dice(Colour::GREEN, 3));
	data.push_back(Dice(Colour::BLUE, 4));
	data.push_back(Dice(Colour::WHITE, 5));
} 

//roll all die
void RollOfDice::roll(){
	for(auto d : data){
		d.roll(rd);
	}
}

//rolls two die, returns rod
RollOfDice RollOfDice::pair(Dice d1, Dice d2){
	RollOfDice pair(rd);
	pair.data.clear(); // get rid of common dice
	pair.data.push_back(d1); //add in d1 and d2
	pair.data.push_back(d2);
	return pair;
}

//convert to int
RollOfDice::operator int() {
	int s=0;
	for (auto d : data) {
		s+=d.face;
	}
	return s;
}

//output operator
ostream& operator<<(std::ostream& os, RollOfDice& rod) {
	for (auto d : rod.data) 
		os<<d<<"\n";
	os<<"sum is : "<<int(rod)<<endl;
	return os;
}
//////////////////////////////////////

