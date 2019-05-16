import java.util.Random;
import java.util.concurrent.Semaphore;

public class Lab4
{
  // Configuration
  final static int PORT0 = 0;
  final static int PORT1 = 1;
  final static int MAXLOAD = 5;

  public static void main(String args[]) {
     int NUM_CARS = 10;
     int NUM_AMBS = 1;
     int NUM_CROSSING = 10;
    int i;
    
    System.out.println ("num args: " + args.length);
    if (args.length > 0) {
      try {
        NUM_CROSSING = Integer.parseInt(args[0]);
      } catch (NumberFormatException e) {}
    }
    if (args.length > 1) {
      try {
        NUM_CARS = Integer.parseInt(args[1]);
      } catch (NumberFormatException e) {}
    }
    if (args.length > 2) {
      try {
        NUM_AMBS = Integer.parseInt(args[2]);
      } catch (NumberFormatException e) {}
    }

    Ferry ferry = new Ferry(PORT0,NUM_CROSSING);

    Auto [] automobile = new Auto[NUM_CARS];
    for (i=0; i< 7; i++) automobile[i] = new Auto(i,PORT0,ferry);
    for ( ; i<NUM_CARS ; i++) automobile[i] = new Auto(i,PORT1,ferry);

    // Ambulance ambulance = new Ambulance(PORT0,ferry);
    Ambulance [] ambulance = new Ambulance[NUM_AMBS];
    for (i=0; i< NUM_AMBS; i++) ambulance[i] = new Ambulance(i, (PORT0+i) % 2,ferry);

    /* Start the threads */
    ferry.start();   // Start the ferry thread.
    for (i=0; i<NUM_CARS; i++) automobile[i].start();  // Start automobile threads
    for (i=0; i<NUM_AMBS; i++) ambulance[i].start();  // Start the ambulance thread.

    try {ferry.join();} catch(InterruptedException e) { }; // Wait until ferry terminates.
    System.out.println("Ferry stopped.");
    // Stop other threads.
    for (i=0; i<NUM_CARS; i++) automobile[i].interrupt(); // Let's stop the auto threads.
    for (i=0; i<NUM_AMBS; i++) ambulance[i].interrupt(); // Stop the ambulance thread.
  }
}


class Auto extends Thread { // Class for the auto threads.

  private int id_auto;
  private int port;
  private Ferry fry;
  

  public Auto(int id, int prt, Ferry ferry)
  {
    this.id_auto = id;
    this.port = prt;
    this.fry = ferry;
  }

  public void run() {

    while (true) {
      // Delay
      try {sleep((int) (300*Math.random()));} catch (Exception e) { break;}
      System.out.println("Auto " + id_auto + " arrives at port " + port);
		//
		try {
		  fry.loadingSemaphore[port].acquire();
		  fry.loadingDone.acquire();
		 } catch (Exception e){ break; }
  
  
  
  
      // Board
      System.out.println("Auto " + id_auto + " boards on the ferry at port " + port);
      if (fry.getPort() != port) System.out.println ("<<<<<<<<<< error loading at wrong port >>>>>>>>");
      fry.addLoad();  // increment the ferry load
      /**/
	  
	  
	  
      // Arrive at the next port
      port = 1 - port ;   
      
      // disembark    
      System.out.println("Auto " + id_auto + " disembarks from ferry at port " + port);
      if (fry.getPort() != port) System.out.println ("<<<<<<<<<< error unloading at wrong port >>>>>>>>");
      fry.reduceLoad();   // Reduce load
  
      // Terminate
      if(isInterrupted()) break;
    }
    System.out.println("Auto "+id_auto+" terminated");
  }
}

class Ambulance extends Thread { // the Class for the Ambulance thread

  private int id;
  private int port;
  private Ferry fry;

  public Ambulance(int id, int prt, Ferry ferry)
  {
    this.port = prt;
    this.fry = ferry;
    this.id = id;
  }

  public void run() {
     while (true) {
      // Attente
      try {sleep((int) (1000*Math.random()));} catch (Exception e) { break;}
      System.out.println("Ambulance " + id + " arrives at port " + port);
  
      // Board
      System.out.println("Ambulance " + id + " boards the ferry at port " + port);
      if (fry.getPort() != port) System.out.println ("<<<<<<<<<< error loading at wrong port >>>>>>>>");
      fry.loadAmbulance();  // increment the load  
      
      // Arrive at the next port
      port = 1 - port ;   
      
      //Disembarkment    
      System.out.println("Ambulance " + id + " disembarks the ferry at port " + port);
      if (fry.getPort() != port) System.out.println ("<<<<<<<<<< error unloading at wrong port >>>>>>>>");
      fry.unloadAmbulance();   // Reduce load
  
      // Terminate
      if(isInterrupted()) break;
    }
    System.out.println("Ambulance " + id + " terminated.");
  }
}

class Ferry extends Thread // The ferry Class
{
  final static int MAXLOAD = 5;
  private int port=0;  // Start at port 0
  private int load=0;  // Load is zero
  private int numCrossings;  // number of crossings to execute
  private boolean ambulance_loaded = false;
  
  // Semaphores
	// As described in text
	// to wait for vehicles to be loaded before starting a crossing
	public Semaphore loadingDone; 
	// to wait for vehicles to unload before allowing vehicles to load
	public Semaphore unloadingDone; 
  // More Semaphores for synchronizing loading/unloading of vehicles on each port
	public Semaphore loadingSemaphore[] = new Semaphore[] {new Semaphore(0), new Semaphore(0)};
	public Semaphore unloadingSemaphore[] = new Semaphore[] {new Semaphore(0), new Semaphore(0)};
  
  public Ferry(int prt, int nbtours)
  {
    this.port = prt;
    numCrossings = nbtours;
	// Semaphores
	loadingDone = new Semaphore(1); 
	unloadingDone = new Semaphore(1); 
  }

  public void run() {
    System.out.println("Start at port " + port + " with a load of " + load + " vehicles");

    // numCrossings crossings in our day
    for(int i=0 ; i < numCrossings ; i++) {
		//
		
		//
      // The crossing
      System.out.println("Departure from port " + port + " with a load of " + load + " vehicles");
      System.out.println("Crossing " + i + " with a load of " + load + " vehicles");
      if (ambulance_loaded) {
        if (load == 0) System.out.println("<<<<<<<<<< error ferry leaving with less load! >>>>>>>>");
      } 
      else {
        if (load != MAXLOAD) System.out.println("<<<<<<<<<< error ferry leaving with less load! >>>>>>>>");
      }
      port = 1 - port;
      try {sleep((int) (100*Math.random()));} catch (Exception e) { }
      // Arrive at port
      System.out.println("Arrive at port " + port + " with a load of " + load + " vehicles");
      // Disembarkment et loading
    }
  }

  // methodes to manipulate the load of the ferry
  public int getLoad() { return(load); }
  public int getPort() { return(port); }
  
  // the following methods are mutually exclusive..
  public void addLoad() {
	//
    if (load >= MAXLOAD) System.out.println ("<<<<<<<<<< error loading in a full Ferry! >>>>>>>>");
    load = load + 1; 
    System.out.println ("added load, now " + load);
	//
  }
  public void reduceLoad()  { 
	//
    if (load <= 0) System.out.println ("<<<<<<<<<< error unloading an empty Ferry! >>>>>>>>");
    load = load - 1 ; 
    System.out.println ("removed load, now " + load);
	//
  }
  public void loadAmbulance() {
    ambulance_loaded = true;
    addLoad();
  }
  public void unloadAmbulance(){
    ambulance_loaded = false;
    reduceLoad();
  }
}
