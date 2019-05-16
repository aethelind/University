import javax.swing.JOptionPane;

/**
 * The class <b>MontyHall</b> simulates one or several games. Is uses three <b>Door</b> objects
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
  private Door openDoor;
  private Door chosenDoor;
  private Door prizeDoor;
     


   /**
     * Initializes the variables.
     */
  public MontyHall(){
    door1 = new Door("door1"); 
    door2 = new Door("door2"); 
    door3 = new Door("door3"); 
    
    openDoor = new Door("openDoor"); 
    chosenDoor = new Door("chosenDoor");
    prizeDoor = new Door("prizeDoor");
    }
 
 /** 
     * Runs a series of Monty Hall games and displays the resulting statistics in a 
     * message dialog or on the standard output  
     * 
     * @param numberOfGames the number of games to simulate
     * @param commandLine if true, sends the results to standard output, else uses a message dialog
     */
  public void runGames(int numberOfGames, boolean commandLine){
   Statistics stats = new Statistics();
   
   // Run specified number of games, and update statistics along the way:
   for(int i=0; i<numberOfGames; i++)
   {
     this.oneGame();
     stats.updateStatistics(door1, door2, door3);
   }
   
 
  if(commandLine) {
   System.out.println(stats.toString());
  }
  else {
   JOptionPane.showMessageDialog (null,stats.toString(), "Results", JOptionPane.INFORMATION_MESSAGE);
  }
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
    // Reseting doors:
    door1.reset();
    door2.reset();
    door3.reset();
    prizeDoor.reset();
    chosenDoor.reset();
    openDoor.reset();

    // Random selection of a prize door, player's door, and open door:
    prizeDoor = this.pickADoor();
    prizeDoor.setPrize();
    
    chosenDoor = this.pickADoor();
    chosenDoor.choose();
    
    openDoor=openOtherDoor(prizeDoor, chosenDoor);
   
 }
  
  /**
     * Simulates a random selection of one of the three doors.
     * @return the door randomly selected
     */
  private Door pickADoor(){
    // Random variable between 1 and 3 returns the corresponding door.
    int i;
    i = (int) (Math.random()*3)+1;
    if (i == 1){
      return door1;
    }
    else if(i == 2){
      return door2;
    }
    else if (i == 3){
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
    // ELSE, IF the player chose a different door than the prize door,
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
    * @param args (optional) the number of games to simulate
 */
 public static void main(String[] args) {

  MontyHall montyHall;
  int numberOfGames;
  boolean commandLine = false;
  
  //StudentInfo.display();

  if (args.length == 1) {
   numberOfGames = Integer.parseInt(args[0]);
   commandLine = true;
  } else {
   numberOfGames = Integer.parseInt(JOptionPane.showInputDialog("Input the number of games to play", "1000"));
  }

  
  montyHall = new MontyHall();  
  montyHall.runGames(numberOfGames, commandLine);
 }

}
