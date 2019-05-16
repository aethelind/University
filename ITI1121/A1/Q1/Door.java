/**
 * The class <b>Door</b> stores the information about one of the door:
 * does it have the prize behind it? Is it open or closed? Was it
 * selected by the player?
 *
 * It provides other objects access to these information through some
 * <b>setters</b> and <b>getters</b>.
 *
 * @author gvj (gvj@eecs.uottawa.ca), implementation by Group 250: Aethelind Racic and Changeun Jeong.
 *
 */
public class Door {

 private boolean open; // True when open, false when closed.
 private boolean prize; // True when prize behind this door, false otherwise.
 private boolean choosen; // True when choosen by the player, false otherwise. 
 private String name; // The "name" of the door.
 

 /**
   * Creates an instance of the Door object.
   * Initially, the door is closed, doesn't have a prize behind it
   * and has not been chosen by the player.
   *
   * @param name identifier for that door
   */
 public Door(String name){
   // Initializing door characteristics and identifier:
   this.name = name;
   open = false;
   prize = false;
   choosen = false;
 }


 /**
     * Resets the door to its initial state: closed, without a prize behind it
     * and not chosen by the player.
     */
 public void reset(){
   open = false;
   prize = false;
   choosen = false;
 }


 /**
     * Sets this door open.
     */
 public void open(){
  open = true;
 }

 
 /**
     * Checks if the door is open.
     * @return true if the door is open
     */
 public boolean isOpen(){
   return open;
 }
 
 
 /**
     * Puts the prize behind this door.
     */
 public void setPrize(){
   prize = true;
 }


 /**
     * Checks if the door has the prize.
     * @return true if the door has the prize
     */
 public boolean hasPrize(){
   return prize;
 }

 
 /**
     * Sets this door as selected by the player.
     */
 public void choose(){
   choosen = true;
 }


 /**
     * Checks if the door is selected by the player.
     * @return true if the door is selected by the player
     */
 public boolean isChosen(){
   return choosen;
 }


 /**
     * @return the door's identifier
     */
 public String getName(){
   return name;
 }
}
