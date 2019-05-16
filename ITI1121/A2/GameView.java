// Author: Aethelind Rose Racic
// Student number: 7686783
// Course: ITI 1121C
// Group: 95 (Single)
// Assignment: 2


import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The class <b>GameView</b> provides the current view of the entire Game. It extends
 * <b>JFrame</b> and lays out an instance of  <b>BoardView</b> (the actual game) and 
 * two instances of JButton. The action listener for the buttons is the controller.
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */

public class GameView extends JFrame {

  private BoardView b;
  private JButton reset;
  private JButton quit;
  private JPanel p;

 
    /**
     * Constructor used for initializing the Frame
     * 
     * @param model
     *            the model of the game (already initialized)
     * @param gameController
     *            the controller
     */

    public GameView(GameModel model, GameController gameController) {
      super("Circle The Dot!"); 
          
      setBackground(Color.white);
      setSize(((model.getSize())*40)+80,((model.getSize())*40)+120); // or something
      setVisible(true);
      setResizable(false);
      setDefaultCloseOperation(EXIT_ON_CLOSE);
          
      b = new BoardView(model, gameController);
      add(b);

      p = new JPanel();
      p.setLayout(new FlowLayout());
      
      reset = new JButton("Reset!");
      reset.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
          gameController.reset();
        }
      });
      
      quit = new JButton("Quit!");
      quit.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
          setVisible(false);
          dispose();
        }
      });
      
      p.add(reset);
      p.add(quit);
      add(p, BorderLayout.SOUTH);
      
      
    }

   /**
     * Getter method for the attribute board.
     * 
     * @return a reference to the BoardView instance
     */

    public BoardView getBoardView(){
      return b;
   }

}
