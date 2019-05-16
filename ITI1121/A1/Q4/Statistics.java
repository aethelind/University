
/**
 * The class  <b>Statistics</b> accumulates information about a series of games:
 * <ol>
 * <li>Number of game played</li>
 * <li>Number of times the switching strategy was successful</li>
 * <li>Number of times the switching strategy was not successful</li>
 * <li>Number of times each door has the prize behind it</li>
 * <li>Number of times each door was chosen by the player</li>
 * <li>Number of times each door was open by the host</li>
 * </ol>
 *
 * @author gvj (gvj@eecs.uottawa.ca), implementation by Group 250: Aethelind Racic and Changeun Jeong.
 *
 */
public class Statistics {

  // Initializing variables:

  private int gamesPlayed;
  private int switchWorked;
  private int stayWorked;
  private int numberOfDoors;
  
  // Statistics for multiple doors stored in a two-dimensional array.
  // Each row of array corresponds to a door,
  // Each column corresponds to a statistic - the number of times the door:
  // held the prize, was selected by player, was opened by Monty Hall.
  private int[][] doorStats;

 /** 
     * Initializes the statistics.
     * 
     * @param numberOfDoors the number of doors used
     */
  public  Statistics(int numberOfDoors){
    this.numberOfDoors = numberOfDoors;
    doorStats = new int[numberOfDoors][3];
    gamesPlayed=0;
    switchWorked=0;
    stayWorked=0;
 }
 
 /** 
     * Updates statistics after one game.
     *   @param doorArray the list of Doors used during the game
     */
 public void updateStatistics(Door[] doorArray){
    gamesPlayed++;
    
    // Update individual door statistics.
    for(int i=0; i<numberOfDoors; i++){   
      if((doorArray[i].hasPrize()) && (doorArray[i].isChosen())){
        stayWorked++;
      }
      this.oneDoor(doorArray[i], i);
    }
    switchWorked = gamesPlayed - stayWorked;
    

 }
 
 private void oneDoor(Door door, int index){
   
  if(door.hasPrize()){
     doorStats[index][0]++;
   }
   
   if(door.isChosen()){
     doorStats[index][1]++;
   }
   
   if(door.isOpen()){
     doorStats[index][2]++;
     
   }
  
}

 /** 
     *  @return Returns the complete statistics information
     */
 public String toString(){
 double tmp;
   
   System.out.println("Number of games played: " + gamesPlayed);
   
   tmp = ((double)(stayWorked)/(double)(gamesPlayed))*100;
   System.out.print("Staying strategy won " + stayWorked + " times.");
   System.out.format("\t(%.2f", tmp);
   System.out.print("%)\n");
   
   tmp = ((double)(switchWorked)/(double)(gamesPlayed))*100;
   System.out.print("Switching strategy won " + switchWorked + " times.");
   System.out.format("\t(%.2f", tmp);
   System.out.print("%)\n");
   
   System.out.println("\nWinning Doors:");
   for(int j=0; j<numberOfDoors; j++)
   {
     System.out.print("\t Door " + (j+1) + ":  " + doorStats[j][0]);
     tmp = ((double)(doorStats[j][0])/(double)(gamesPlayed))*100;
     System.out.format("\t(%.2f", tmp);
     System.out.print("%)\n");
   }
   
   System.out.println("\nChosen Doors:");
   for(int j=0; j<numberOfDoors; j++)
   {
     System.out.print("\t Door " + (j+1) + ":  " + doorStats[j][1]);
     tmp = ((double)(doorStats[j][1])/(double)(gamesPlayed))*100;
     System.out.format("\t(%.2f", tmp);
     System.out.print("%)\n");
   }
   
   System.out.println("\nOpened Doors:");
   for(int j=0; j<numberOfDoors; j++)
   {
     System.out.print("\t Door " + (j+1) + ":  " + doorStats[j][2]);
     tmp = ((double)(doorStats[j][2])/(double)(gamesPlayed))*100;
     System.out.format("\t(%.2f", tmp);
     System.out.print("%)\n");
   }
   return("");

 }
 
 /** 
     *  @return Returns the complete statistics information in CSV format
     */
public String toCSV(){
// Unable to implement.
  return("");
 }

}
