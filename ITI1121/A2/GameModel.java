// Author: Aethelind Rose Racic
// Student number: 7686783
// Course: ITI 1121C
// Group: 95 (Single)
// Assignment: 2


import java.util.Random;
import java.lang.Math;


/**
 * The class <b>GameModel</b> holds the model, the state of the systems. 
 * It stores the followiung information:
 * - the current location of the blue dot
 * - the state of all the dots on the board (available, selected or 
 *  occupied by the blue dot
 * - the size of the board
 * - the number of steps since the last reset
 *
 * The model provides all of this informations to the other classes trough 
 *  appropriate Getters. 
 * The controller can also update the model through Setters.
 * Finally, the model is also in charge of initializing the game
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */
public class GameModel {


    /**
     * predefined values to capture the state of a point
     */
  public static final int AVAILABLE  = 0; // Grey dots.
  public static final int SELECTED  = 1; // Orange dots.
  public static final int DOT   = 2; // Blue dot.
  public static final double INITIAL_PROBA = 10.0;
  
  private static Random r = new Random();
  private int[][] board; 
  private int sizeOfGame; 
  private Point blueDot;
  private static int steps;

    /**
     * Constructor to initialize the model to a given size of board.
     * 
     * @param size
     *            the size of the board
     */
    public GameModel(int size) {
      steps = 0;
      sizeOfGame = size;
      blueDot = new Point(0,0);

      // 'board' contains the state of all points on the board.
      board = new int[sizeOfGame][sizeOfGame];
      reset();
    }


    /**
     * Resets the model to (re)start a game. The previous game (if there is one)
     * is cleared up . The blue dot is positioned as per instructions, and each 
     * dot of the board is either AVAILABLE, or SELECTED (with
     * a probability 1/INITIAL_PROBA). The number of steps is reset.
     */
    public void reset(){
      // Reset number of steps taken:
      steps = 0;
      
      // Reset all points on board:
      for(int i=0; i<sizeOfGame; i++){
        for(int j=0; j<sizeOfGame; j++){
          board[i][j] = AVAILABLE;
        }
      }
      
      // Position the blue dot:
      int min;
      if(sizeOfGame%2 == 0){
        // An even board...
        min=(sizeOfGame-2)/2;
        blueDot.reset((r.nextInt(2)+min), (r.nextInt(2)+min));
      }
      else{
        // An odd board...
        min=(sizeOfGame-3)/2;
        blueDot.reset( (r.nextInt(3)+min) , (r.nextInt(3)+min) );
      }
      // Update board to reflect position of blue dot:
      board[blueDot.getX()][blueDot.getY()] = DOT;
      
      // 1/INITIAL_PROBA dots SELECTED:
      double count;
      int a, b;
      
      count=(sizeOfGame*sizeOfGame)/INITIAL_PROBA; // Find number of dots to be selected,
      Math.round(count); // and round it.
      
      while(count>0){
        // Find two random row/column values,
        a=r.nextInt(sizeOfGame);
        b=r.nextInt(sizeOfGame);
        
        // if the point is available, select it and decrease the count.
        if(board[a][b] == AVAILABLE){
          board[a][b] = SELECTED;
          count--;
        }
      }


    }


    /**
     * Getter <b>class</b> method for the size of the game
     * 
     * @return the value of the attribute sizeOfGame
     */   
    public  int getSize(){
      return sizeOfGame;
   }

    /**
     * returns the current status (AVAILABLE, SELECTED or DOT) of a given dot in the game
     * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     * @return the status of the dot at location (i,j)
     */   
    public int getCurrentStatus(int i, int j){
      // That is, row i and column j.
      return board[i][j];
    }


    /**
     * Sets the status of the dot at coordinate (i,j) to SELECTED, and 
     * increases the number of steps by one
     * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     */   
    public void select(int i, int j){
      board[i][j] = SELECTED;
      steps++;
    }

    /**
     * Puts the blue dot at coordinate (i,j). Clears the previous location 
     * of the blue dot. If the i coordinate is "-1", it means that the blue 
     * dot exits the board (the player lost)
     *
     * @param i
     *            the new x coordinate of the blue dot
     * @param j
     *            the new y coordinate of the blue dot
     */   
    public void setCurrentDot(int i, int j){
      board[blueDot.getX()][blueDot.getY()] = AVAILABLE; // clear previous location.
      if(i == -1){
        reset();
      }
      else{
        blueDot.reset(i, j);
        board[blueDot.getX()][blueDot.getY()] = DOT;
      } 
   }

    /**
     * Getter method for the current blue dot
     * 
     * @return the location of the curent blue dot
     */   
    public Point getCurrentDot(){
      return blueDot;
    }

    /**
     * Getter method for the current number of steps
     * 
     * @return the current number of steps
     */   
    public int getNumberOfSteps(){
      return steps;
    }
}
