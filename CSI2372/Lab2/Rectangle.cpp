/* LAB TWO 			Sept. 22nd, 2017.		*
 * name: 			Aethelind Rose Racic 	*
 * student number: 	7686783 				*/

#include "Rectangle.h"
#include <iostream>

using namespace std;
int main(){
	
	Rectangle r1 = Rectangle(3,4,10,5);
	Rectangle r2 = Rectangle(8,8,3,4);
	Rectangle r3 = Rectangle(4,1,10,2);
	Rectangle r4 = Rectangle(14,2,3,5);
	Rectangle r5 = Rectangle(1,2,15,9);
	
	cout<<"Testing Intersection...\n\n";
	cout<<"Test One:\n";
	r1.print();
	r2.print();
	
	Rectangle rtest = r1.intersection(r2);
	
	cout<<"The intersecting rectangle is\n";
	rtest.print();
	/*Expected result, intersection is (8,8) to (10,8)*/
	//
	cout<<"\nTest Two:\n";
	r1.print();
	r3.print();
	
	rtest = r1.intersection(r3);
	
	cout<<"The intersecting rectangle is\n";
	rtest.print();
	/*Expected result, no intersection, default rectangle.*/
	//
	cout<<"\nTest Three:\n";
	r1.print();
	r4.print();
	
	rtest = r1.intersection(r4);
	
	cout<<"The intersecting rectangle is\n";
	rtest.print();
	/*Expected result, no intersection, default rectangle.*/
	//
	cout<<"\nTest Four:\n";
	r1.print();
	r5.print();
	
	rtest = r1.intersection(r5);
	
	cout<<"The intersecting rectangle is\n";
	rtest.print();
	/*Expected result, intersection is (3,4) to (12,8)*/
	
	cout<<"\nTesting Split...\n\n";
	r1.print();
	
	array<Rectangle,4> rArr = r1.split();
	cout<<"Quadrants are...\n";
	rArr[0].print();
	rArr[1].print();
	rArr[2].print();
	rArr[3].print();
	
	return 0;
}

