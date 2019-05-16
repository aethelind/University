 /* FINAL PROJECT	Dec. 9th, 2017.			*
 * name: 			Aethelind Rose Racic 	*
 * student number: 	7686783 				*/

//Dice.cpp

#include "Dice.h"
#include <random>
#include <iostream>

using namespace std;

//////////////////////////////////////
// Colour:
// output operator..
ostream& operator<<(ostream& os, const Colour& c){
		if( c == Colour::RED ) 
			os << "RED";
		else if( c == Colour::YELLOW ) 
			os << "YELLOW"; 
		else if( c == Colour::GREEN ) 
			os << "GREEN";
		else if( c == Colour::BLUE ) 
			os << "BLUE";
		else if( c == Colour::WHITE ) 
			os << "WHITE";
	}
	
//////////////////////////////////////
// Random Dice:
uniform_int_distribution<int> RandomDice::dis(1,6);
default_random_engine RandomDice::gen(15976);

int RandomDice::rand(){
		return dis(gen);
	}

//////////////////////////////////////
//Dice
Dice::Dice(Colour c, int f) : colour(c), face(f) {}

int Dice::roll(RandomDice& rd){
	face = rd.rand();
		return face;
}

//output operator
ostream& operator<<(ostream& os, const Dice& d){
		return os << "colour is " << d.colour <<"; value is "<<d.face << endl;
	}









