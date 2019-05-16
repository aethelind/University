// ITI 1120 Winter 2015
// Name: Aethelind Racic, ID: 7686783 

// Assignment 5: main method, for testing Mall and Customer classes.

class mallTest
{
  public static void main(String[] args)
  {
    Mall m = new Mall ("Bayshore", 12, 8);
    Customer a = new Customer ("Sarah", 24680);
    Customer b = new Customer ("Tim", 13579);
    
    m.printGrid(); // No customers in the mall yet.
    
    if(m.locationEmpty(5,5))
      System.out.println("Location (5,5) is empty."); // Should print out.
    
    a.enterMall(m);
    m.printGrid();
    
    if( !(m.locationEmpty(0,0)) )
      System.out.println("Location (0,0) has a customer."); // Statement should print.
    
    a.makeMove(m);
    b.enterMall(m);
    
    m.printGrid();
    System.out.println("Now both customers have entered the mall, and will move around.");
    
    for(int j=0; j<15; j++)
    {
      a.makeMove(m);
      b.makeMove(m);
    }
    
    m.printGrid();
    
  }
}