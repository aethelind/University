// Author: Aethelind Rose Racic
// Student number: 7686783
// Course: ITI 1121C
// Group: 95 (Single)
// Assignment: 2


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * The class <b>GameController</b> is the controller of the game. It implements 
 * the interface ActionListener to be called back when the player makes a move. It computes
 * the next step of the game, and then updates model and view.
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */


public class GameController implements ActionListener {

  private GameModel gm;
  private GameView gv;
  private int sizeOfGame;
  private BoardView b;
  private Random r;
  private Point[] p;
  
     /**
     * Constructor used for initializing the controller. It creates the game's view 
     * and the game's model instances
     * 
     * @param size
     *            the size of the board on which the game will be played
     */
    public GameController(int size) {
      sizeOfGame = size;
      r = new Random();
      p = new Point[6];
      gm = new GameModel(sizeOfGame);
      b = new BoardView(gm, this);
      gv = new GameView(gm, this);
    }

  
    /**
     * Starts the game
     */
    public void start(){
      reset();
      
    }

 
    /**
     * resets the game
     */
    public void reset(){

    }

    /**
     * Callback used when the user clicks a button or one of the dots. 
     * Implements the logic of the game
     *
     * @param e
     *            the ActionEvent
     */

    public void actionPerformed(ActionEvent e) {
      if (e.getSource() instanceof DotButton) {
        DotButton d = (DotButton)e.getSource();
        int r = d.getRow();
        int c = d.getColumn();
        
        if(gm.getCurrentStatus(r,c) == 0){
          gm.select(r,c);
          d.setType(gm.SELECTED);
          
          moveBlue();

        }

        
      }
      b.update();
      gv.repaint();

    }
    
    
    // The following three methods are private helper methods for the action performance above.
    
    private boolean checkLost(){
      // The player loses if blue gets to the edges.
      boolean flag=false;
      Point p = (gm.getCurrentDot());
      
      if( (p.getX() == 0) || (p.getX() == (sizeOfGame-1)) || (p.getY() == 0) || (p.getY() == (sizeOfGame-1)) ){
        // If blue is on an edge, flag is true.
        flag = true;
      }
      
      return flag;
    }
    
   /*private boolean checkWon(){
      
    }
    */
    private void moveBlue(){
      updateAdjacentSpots(gm.getCurrentDot());
      boolean flag = true;
      int j;
      while(flag){
        j=r.nextInt(6);
        if( (gm.getCurrentStatus( (p[j].getX()),(p[j].getY()) )) == 0 ) {
          gm.setCurrentDot( (p[j].getX()),(p[j].getY()) );
          flag=false;
        }
      } 
    }
    private void updateAdjacentSpots(Point b){
      if(p[0] == null){
        for(int i=0; i<6; i++){
          p[i] = new Point(0,0);
        }
      }
      
      int r = b.getX();
      int c = b.getY();
      
      p[0].reset(r, c-1);
      p[1].reset(r, c+1);
      p[2].reset(r-1, c);
      p[3].reset(r+1, c);
      
      if(r%2 == 0){
        p[4].reset(r-1, c-1);
        p[5].reset(r+1, c-1);
      } else {
        p[4].reset(r-1, c+1);
        p[5].reset(r+1, c+1);
      }
    }

 
}
