import javax.swing.JOptionPane;


/**
 * The class <b>MontyHall</b> simulates one or several games. Is uses three <b>Door</b> objects
 * to simulate the three doors. One game consists of the following steps
 * <ol>
 * <li>Resets all  doors</li>
 * <li>Simulates the selection of one of the doors by the player</li>
 * <li>Simulates opening of all other (empty) door save one by the host</li>
 * <li> provide the outcome for switching and not switching door</li>
 * </ol>
 * @author gvj (gvj@eecs.uottawa.ca), implementation by Group 250: Aethelind Racic and Changeun Jeong.
 *
 */
public class MontyHall {

 private Door[] doors;
 private Door selectedDoor;
 private Door prizeDoor;
 private int numberOfDoors;

 /** 
     * Initializes the list of doors.
     * 
     * @param numberOfDoors number of door used in the simulation
     */
  public MontyHall(int numberOfDoors){
    
    this.numberOfDoors = numberOfDoors;
    
    // Door array for n doors:
    doors = new Door[this.numberOfDoors];
    
    // Initializing all n doors:
    for(int i=0; i<numberOfDoors; i++){
      doors[i] = new Door("i");
    }
    
    selectedDoor = new Door("selectedDoor");
    prizeDoor = new Door("prizeDoor");
 }
 
 /** 
     * Runs a series of Monty Hall games and displays the resulting statistics in a 
     * message dialog or on the standard output  
     * 
     * @param numberOfGames the number of games to simulate
     * @param commandLine if true, sends the results as CSV to standard output, else uses a message dialog
     */
  public void runGames(int numberOfGames, boolean commandLine){
  
    // Create a statistics instance, and run n games:
   Statistics stats = new Statistics(numberOfDoors);
   
   for(int k=0; k<numberOfGames; k++)
    {
      this.oneGame();
      stats.updateStatistics(doors);
    }

  if(commandLine) {
   System.out.println(stats.toCSV());
  }
  else {
   JOptionPane.showMessageDialog (null,stats.toString(), "Results", JOptionPane.INFORMATION_MESSAGE);
  }
 }

 /** 
     * Simulates one Monty Hall game.  
     * <ol>
     * <li>Resets all the doors</li>
  * <li>Simulates the selection of one of the doors by the player</li>
  * <li>Simulates opening of an empty door by the host</li>
  * <li>prints the outcome for switching and not switching door to standard output</li>
  * </ol>
     */
  public void oneGame(){
    
    // Reset all doors:
    prizeDoor.reset();
    selectedDoor.reset();
    
    for(int i=0; i<numberOfDoors; i++){
      doors[i].reset();
    }
    
    // Randomly select a prize door, player's door, and doors to open.
    prizeDoor = this.pickADoor();
    prizeDoor.setPrize();
    
    selectedDoor = this.pickADoor();
    selectedDoor.choose();

    this.openOtherDoors(prizeDoor,selectedDoor);
  
 }

 /** 
     * Simulates a random selection of one of the  doors.
     * @return the door randomly selected  
     */
  private Door pickADoor(){
    int r;
    r = (int) (Math.random()*numberOfDoors);
    // Return door at random index.
    return(doors[r]);
    
 }
 
 /** 
     * Simulates the opening of numberOfDoors-2 doors once the player selected one.
     * It should  open doors chosen randomly among the ones that don't have the prize and
     * that are not selected by the player
     * 
     *   @param prizeDoor the door that hides the prize
     *   @param selectedDoor the door that was selected by the player
     */
  private void openOtherDoors(Door prizeDoor, Door selectedDoor){
    int flag = 0;
    int r;
    
    // Randomly choose the doors to open so as not to reveal extra information to the player:
    // Go to random index and check if it is available to open. Do this to open n-2 doors.
    while(flag<(numberOfDoors-2)){
      r = (int)(Math.random()*numberOfDoors)+0;
      if( !(doors[r].hasPrize()) && !(doors[r].isChosen()) && !(doors[r].isOpen()) ){
        doors[r].open();
        flag++;
     }     
    }    
   }  
    
 
 
  /**
    * The main method of this program. 
    * @param args (optional) the number of games to simulate, and the number of doors to use
 */
 public static void main(String[] args) {

  MontyHall montyHall;
  int numberOfGames;
  int numberOfDoors;
  boolean commandLine = false;
  
  StudentInfo.display();

  if (args.length == 2) {
   numberOfGames = Integer.parseInt(args[0]);
   numberOfDoors = Integer.parseInt(args[1]);
   commandLine = true;
  } else {
   numberOfGames = Integer.parseInt(JOptionPane.showInputDialog("Input the number of games to play", "1000"));
   numberOfDoors = Integer.parseInt(JOptionPane.showInputDialog("Input the number of doors", "3"));
  }

  
  montyHall = new MontyHall(numberOfDoors);  
  montyHall.runGames(numberOfGames, commandLine);
 }

}
