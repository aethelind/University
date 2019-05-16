// Author: Aethelind Rose Racic
// Student number: 7686783
// Course: ITI 1121C
// Group: 95 (Single)
// Assignment: 2


import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.JPanel;


/**
 * The class <b>BoardView</b> provides the current view of the board. It extends
 * <b>JPanel</b> and lays out a two dimensional array of <b>DotButton</b> instances.
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */
public class BoardView extends JPanel {
  
  private JPanel[] p;
  private FlowLayout f;
  private GridLayout g;
  private DotButton[][] boardView;
  private GameModel gm;
  private GameController gc;
  private int size;

    


 /**
     * Constructor used for initializing the board. The action listener for
     * the board's DotButton is the game controller
     * 
     * @param gameModel
     *            the model of the game (already initialized)
     * @param gameController
     *            the controller
     */

    public BoardView(GameModel gameModel, GameController gameController) {
      super();
      gm = gameModel;
      gc = gameController;
      size = gm.getSize();
      f = new FlowLayout();
      p = new JPanel[size];

      
      
      boardView = new DotButton[size][size];
      g = new GridLayout((size+1), 1);
      setLayout(g);
      
      Border leftBorder = BorderFactory.createEmptyBorder(0, 20, 5, 0);
      Border rightBorder = BorderFactory.createEmptyBorder(0, 0, 5, 20);  
      Border emptyBorder = BorderFactory.createEmptyBorder(0, 0, 0, 0);
      for(int r=0; r<size; r++){
        // A new JPanel for each row,...
        p[r] = new JPanel(f);
        if(r%2 == 0){
          // Even rows ...
          p[r].setBorder(rightBorder);
        }
        else{
          // Odd rows ...
          p[r].setBorder(leftBorder);
        }
        for(int c=0; c<size; c++){
          //... and each element of the row initialized and added.
          boardView[r][c] = new DotButton(r, c, gameModel.getCurrentStatus(r, c));
          
          boardView[r][c].setContentAreaFilled(false);
          boardView[r][c].setBorder(emptyBorder);
          boardView[r][c].setBorderPainted(false);
          boardView[r][c].setVisible(true);
          boardView[r][c].addActionListener(gc);
          
          p[r].add(boardView[r][c]);
        }
        add(p[r]);
      }

      
    }

  /**
  * update the status of the board's DotButton instances based on the current game model
  */

    public void update(){
      
      for(int r=0; r<size; r++){
        for(int c=0; c<size; c++){
          boardView[r][c].setType(gm.getCurrentStatus(r, c));
        }
      }
      
    }

}
