// Author: Aethelind Rose Racic
// Student number: 7686783
// Course: ITI 1121C
// Group: 95 (Single)
// Assignment: 2


/**
 * The class <b>Point</b> is a simple helper class that stares a 2 dimentional element on a grid
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */

public class Point {

   // INSTANCE VARIABLES:
  private int x; // The row which point appears in.
  private int y; // The column which point appears in.
  
    /**
     * Constructor 
     * 
     * @param x
     *            the x coordinate
     * @param y
     *            the y coordinate
     */
    public Point(int x, int y){
      // Set given x and y coordinates:
      this.x = x;
      this.y = y;
    }

    /**
     * Getter method for the attribute x.
     * 
     * @return the value of the attribute x
     */
    public int getX(){
      return x;
    }
    
    /**
     * Getter method for the attribute y.
     * 
     * @return the value of the attribute y
     */
    public int getY(){
      return y;
    }
    
    /**
     * Setter for x and y.
     * @param x
     *            the x coordinate
     * @param y
     *            the y coordinate
     */
    public void reset(int x, int y){
      this.x=x;
      this.y=y;
  }

 }
