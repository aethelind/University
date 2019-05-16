// Author: Aethelind Rose Racic
// Student number: 7686783
// Course: ITI 1121C
// Group: 95 (Single)
// Assignment: 2


import javax.swing.JButton;
import javax.swing.ImageIcon;



/**
 * In the application <b>Circle the dot</b>, a <b>DotButton</b> is a specialized type of
 * <b>JButton</b> that represents a dot in the game. It uses different 
 *s to
 * visually reflect its state: a blue icon if the blue dot is currently on this location
 * an orange icon is the dot has been selected and a grey icon otherwise.
 * 
 * The icon images are stored in a subdirectory ``data''. They are:
 * data/ball-0.png => grey icon
 * data/ball-1.png => orange icon
 * data/ball-2.png => blue icon
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */

public class DotButton extends JButton {
  public static final int AVAILABLE  = 0; // Grey dots.
  public static final int SELECTED  = 1; // Orange dots.
  public static final int DOT   = 2; // Blue dot.
  private static ImageIcon[] dots = new ImageIcon[3]; // An array to hold the icons.
  
  private int row, column, type;
 

    /**
     * Constructor used for initializing a cell of a specified type.
     * 
     * @param row
     *            the row of this Cell
     * @param column
     *            the column of this Cell
     * @param type
     *            specifies the type of this cell
     */

    public DotButton(int row, int column, int type) {
      super();
      this.row=row;
      this.column=column;
      this.type=type;
      setEnabled(true);
      
      if (dots[type] == null){  
        // If the array of icons has yet to be initialized, then it is initialized.
        String str;
        for(int i=0; i<3; i++){
          str = Integer.toString(i);
          dots[i] = new ImageIcon("data/ball-" + str + ".png");
        }
      }
      
     setIcon(dots[type]);
    }



    /**
     * Changes the cell type of this cell. The image is updated accordingly.
     * 
     * @param type
     *            the type to set
     */

    public void setType(int type) {
      this.type = type;
      setIcon(dots[this.type]);
    }

 
    /**
     * Getter method for the attribute row.
     * 
     * @return the value of the attribute row
     */

    public int getRow() {
      return row;
    }

    /**
     * Getter method for the attribute column.
     * 
     * @return the value of the attribute column
     */

    public int getColumn() {
      return column;
    }
}
