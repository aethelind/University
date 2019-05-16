 /* FINAL PROJECT	Dec. 9th, 2017.			*
 * name: 			Aethelind Rose Racic 	*
 * student number: 	7686783 				*/

//Dice.h

#include <random>
#include <iostream>

using namespace std;

//////////////////////////////////////
// Colour:
enum class Colour {
		RED=0, YELLOW=1, GREEN=2, BLUE=3, WHITE=4
	};
	
// output operator..
ostream& operator<<(ostream& os, const Colour& c);
	
//////////////////////////////////////
// Random Dice:
struct RandomDice{
	static default_random_engine gen;
	static uniform_int_distribution<int> dis;
	
	int rand();
};

//////////////////////////////////////
//Dice

struct Dice{
	const Colour colour;
	int face;
	
	Dice(Colour c, int f);
	int roll(RandomDice& rd);
};

//output operator
ostream& operator<<(ostream& os, const Dice& d);

