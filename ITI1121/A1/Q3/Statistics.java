
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
  
  // Statistics for multiple doors stored in a two-dimensional array.
  // Each row of array corresponds to a door,
  // Each column corresponds to a statistic - the number of times the door:
  // held the prize, was selected by player, was opened by Monty Hall.
  private int[][] doorStats;
  

 /** 
     * Initializes the statistics.
     */
  public  Statistics(){
    gamesPlayed=0;
    switchWorked=0;
    stayWorked=0;
    
 
    doorStats = new int[3][3];
    

 }
 
  
 /** 
     * Updates statistics after one game.
     *   @param door1 the first door in the game
     *   @param door2 the second door in the game
     *   @param door3 the third door in the game
     */
  public void updateStatistics(Door door1, Door door2, Door door3){
    gamesPlayed++;
    this.oneDoor(door1, 0);
    this.oneDoor(door2, 1);
    this.oneDoor(door3, 2);
    
    if ((door1.hasPrize() && door1.isChosen()) || (door2.hasPrize() && door2.isChosen()) || (door3.hasPrize() && door3.isChosen())){
      stayWorked++;
      switchWorked=(gamesPlayed-stayWorked);
    }
    
 }
 
  
 /** 
     * Updates statistics for one single door.
     *   @param door the door for which statistics are updated
     *   @param index index of that door (0, 1 or 2)
     */
 private void oneDoor(Door door, int index){
   
   if(door.hasPrize())
   {
     doorStats[index][0]++;
   }
   
   if(door.isChosen())
   {
     doorStats[index][1]++;
   }
   
   if(door.isOpen())
   {
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
   for(int j=0; j<3; j++)
   {
     System.out.print("\t Door " + j + ": " + doorStats[j][0]);
     tmp = ((double)(doorStats[j][0])/(double)(gamesPlayed))*100;
     System.out.format("\t(%.2f", tmp);
     System.out.print("%)\n");
   }
   
   System.out.println("\nChosen Doors:");
   for(int j=0; j<3; j++)
   {
     System.out.print("\t Door " + j + ": " + doorStats[j][1]);
     tmp = ((double)(doorStats[j][1])/(double)(gamesPlayed))*100;
     System.out.format("\t(%.2f", tmp);
     System.out.print("%)\n");
   }
   
   System.out.println("\nOpened Doors:");
   for(int j=0; j<3; j++)
   {
     System.out.print("\t Door " + j + ": " + doorStats[j][2]);
     tmp = ((double)(doorStats[j][2])/(double)(gamesPlayed))*100;
     System.out.format("\t(%.2f", tmp);
     System.out.print("%)\n");
   }
   return("");
 }
 
 
 /** 
     *  @return Returns the complete statistics information in CSV format
     */
 /*public String toCSV(){
  // Unable to implement. 

 }*/
 
}
