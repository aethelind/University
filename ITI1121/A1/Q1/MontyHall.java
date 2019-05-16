
/**
 * The class <b>MontyHall</b> simulates one game. Is uses three <b>Door</b> objects
 * to simulate the three doors. One game consists of the following steps
 * <ol>
 * <li>Resets all three doors</li>
 * <li>Simulates the selection of one of the doors by the player</li>
 * <li>Simulates opening of an empty door by the host</li>
 * <li> provide the outcome for switching and not switching door</li>
 * </ol>
 * @author gvj (gvj@eecs.uottawa.ca), implementation by Group 250: Aethelind Racic and Changeun Jeong.
 *
 */
public class MontyHall {

  // Instance Variables:
  
  // The three doors:
  private Door door1;
  private Door door2;
  private Door door3;
  
  // Placeholders for instance of a game:
  private Door opendoor;
  private Door choosendoor;
  private String prize;
     


   /**
     * Initializes the variables.
     */
  public MontyHall(){
    door1 = new Door("door1"); 
    door2 = new Door("door2"); 
    door3 = new Door("door3"); 
    
    opendoor = new Door("opendoor"); 
    choosendoor = new Door("choosendoor");
    }
  
  /** 
   * Simulates one Monty Hall game.  
   * <ol>
   * <li>Resets all three doors</li>
   * <li>Simulates the selection of one of the doors by the player</li>
   * <li>Simulates opening of an empty door by the host</li>
   * <li>prints the outcome for switching and not switching door to standard output</li>
   * </ol>
   */
  public void oneGame(){
    
    // Reseting all doors:
    door1.reset();
    door2.reset();
    door3.reset();
    choosendoor.reset();
    opendoor.reset();
    
    // Variable for random selection:
    int y;
    y = (int) (Math.random()*3)+1;

    // Random selection of a prize door, selected door, and open door:
    if (y == 1){
      door1.setPrize();
      prize = door1.getName();
      choosendoor=this.pickADoor();
      opendoor=openOtherDoor(door1,choosendoor);

    }
    else if(y == 2){
      door2.setPrize();
      prize = door2.getName();
      choosendoor=this.pickADoor();
      opendoor=openOtherDoor(door2,choosendoor);

    }
    else if (y == 3){
      door3.setPrize();
      prize = door3.getName();
      choosendoor=this.pickADoor();
      opendoor=openOtherDoor(door3,choosendoor);
    }
    
    // Display Results:
    System.out.println("The prize is behind  " + prize);
    System.out.println("The player selected " + choosendoor.getName());
    System.out.println("The host opened " + opendoor.getName());
  
    if(choosendoor.getName() == prize){
      System.out.println("Switching strategy would have lost");
    } else {
      System.out.println("Switching strategy would have won");
    }

 }
  
  /**
     * Simulates a random selection of one of the three doors.
     * @return the door randomly selected
     */
  private Door pickADoor(){
    // Random variable between 1 and 3 selects the corresponding door.
    int i;
    i = (int) (Math.random()*3)+1;
    if (i == 1){
      door1.choose();
      return door1;
    }
    else if(i == 2){
      door2.choose();
      return door2;

    }
    else if (i == 3){
      door3.choose();
      return door3;
    }
    return null;
 }


   /**
     * Simulates the opening of one of the other doors once the player selected one.
     * It should  open a door chosen randomly among the ones that don't have the prize and
     * that are not selected by the player
     *
     *   @param prizeDoor the door that hides the prize
     *   @param selectedDoor the door that was selected by the player
     *   @return the door opened
     */
  private Door openOtherDoor(Door prizeDoor, Door selectedDoor){
    
    // IF the user selected the door with the prize behind it, 
    // open either one of the other doors (randomly).
    if(prizeDoor.getName() == selectedDoor.getName()){
      int i;
      i = (int) (Math.random()*2)+1;
      
      if(prizeDoor.getName() == door1.getName()){
        if(i==1){
          door2.open();
          return door2;
        }
        if(i==2){
          door3.open();
          return door3;
        }
      }
      if(prizeDoor.getName() == door2.getName()){
        if(i==1){
          door1.open();
          return door1;
        }
        if(i==2){
          door3.open();
          return door3;
        }
      }
      if(prizeDoor.getName() == door3.getName()){
        if(i==1){
          door2.open();
          return door2;
        }
        if(i==2){
          door1.open();
          return door1;
        }
      }
    }
    // ELSE, the player chose a different door than the prize door,
    // determine and open the third door.
    else{
      if( (prizeDoor.getName() != door1.getName()) && (selectedDoor.getName() != door1.getName()) )
      {
        door1.open();
        return door1;
      }
      else if( (prizeDoor.getName() != door2.getName()) && (selectedDoor.getName() != door2.getName()) )
      {
        door2.open();
        return door2;
      }
      else if( (prizeDoor.getName() != door3.getName()) && (selectedDoor.getName() != door3.getName()) )
      {
        door3.open();
        return door3;
      }
    }
    return null;
 }


   /**
     * The main method of this program. 
     * Displays student information, and runs one game.
     * @param args ignored for now
     */
 public static void main(String[] args) {

  MontyHall montyHall;

  StudentInfo.display();
  montyHall = new MontyHall();
  montyHall.oneGame();
 }
}
