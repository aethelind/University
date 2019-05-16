/* LAB TWO 			Sept. 22nd, 2017.		*
 * name: 			Aethelind Rose Racic 	*
 * student number: 	7686783 				*/

#ifndef RECTANGLE_H
#define RECTANGLE_H

#include <array>

using namespace std;
class Rectangle
{
	private:
		int d_x_start;
		int d_x_end;
		int d_y_start;
		int d_y_end;
	public:
		Rectangle(int x=-1, int y=-1, int width=1, int height=1);
		
		void print();
		
		bool intersect(Rectangle r);
	
		Rectangle intersection(Rectangle r);
	
		array<Rectangle,4> split();	 
}; 

#endif
