// ITI 1120 Winter 2015
// Name: Aethelind Racic, ID: 7686783 

// Assignment 5: Customer class.

import java.util.Random;

class Customer
{
  String name; // Customer's name.
  int loyaltyNumber; // Curstomer's loyalty number.
  int x, y; // x corresponds to the row Customer is in, and y corresponds to the column.
  Mall m; // The mall that customer is in.
  
  // Constructor:
  Customer(String name, int loyaltyNumber)
  {
    // Initialize the customer's name/number, and set coordinates to (-1, -1)
    this.name=name;
    this.loyaltyNumber=loyaltyNumber;
    x=-1;
    y=-1;
  }
  
  // "Setters and Getters" for name, number, coordinates.
  void setName(String name)
  {
    this.name=name;
  }
  String getName()
  {
    return name;
  }
  void setLoyaltyNumber(int loyaltyNumber)
  {
    this.loyaltyNumber=loyaltyNumber;
  }
  int getLoyaltyNumber()
  {
    return loyaltyNumber;
  }
  int getRowCoordinate()
  {
    return x;
  }
  int getColumnCoordinate()
  {
    return y;
  }
  // (Customer's coordinates cannot be changed through a 'setter').

  // Puts Customer in mall m at (0,0).
  int enterMall(Mall m)
  {
    // update the customer's mall m.
    this.m=m;
    
    // if (0,0) is unoccupied, put customer there, and return 1 (to indicate success). 
    // Otherwise, return 0 (to indicate unsuccessful entry).
    if(m.locationEmpty(0,0))
    {
      x=0;
      y=0;
      m.setLocation(this, 0, 0);
      return 0;
    }
    else
      return -1;
  }
  
  // Moves the Customer to a random adjacent space that is valid and unoccupied.
  int makeMove(Mall m)
  {
    int choice; // the direction Customer is trying to move in.
    Random r = new Random(); // for random direction selection.

    // Make sure the customer is in mall m.
    if(m!=this.m)
    {
      System.out.println(this.getName() + " is not in mall " + m.getMallName() + "!");
      return -1;
    }

    // Picks a number from 1 - 4 inclusive.
    // Each number corresponds to an adjacent space.
    choice=r.nextInt(4)+1;


    for(int attempts=0; attempts<4; attempts++) // while customer has not tried all the adjacent squares, ...
    {
      if(choice==1) // corresponds to the space above customer.
      {
        // Each if statement follows the same algorithm: 
        // (note that locationEmpty also checks that (x,y) is a valid space)
        
        if(m.locationEmpty(x-1, y)) // if this space is empty AND valid, ...
        {
          // Customer's x and y coordinates are updated, and set in the mall.
          x-=1;
          m.setLocation(this, x, y);
          return 0;
        }
        else
          choice=2; // otherwise, the next space is tried (each space is only tried once).
      }
      else if(choice==2) // corresponds to space right of customer.
      {
        if(m.locationEmpty(x, y+1)) 
        {
          y+=1;
          m.setLocation(this, x, y);
          return 0;
        }
        else
          choice=3;
      }
      else if(choice==3) // corresponds to space below customer.
      {
        if(m.locationEmpty(x+1, y)) 
        {
          x+=1;
          m.setLocation(this, x, y);
          return 0;
        }
        else
          choice=4;
      }
      else if(choice==4) // corresponds to space left of customer.
      {
        if(m.locationEmpty(x, y-1)) 
        {
          y-=1;
          m.setLocation(this, x, y);
          return 0;
        }
        else
          choice=1;
      }
      else
      {
        // if variable 'choice' is not in the range, an error has occured.
        // (this should never happen, and is only a safety net!)
        System.out.println("An error occured.");
        return -1;
      }
    }
    
    // If none of the adjacent squares were valid and empty, 
    // -1 is returned to indicate there was no move.
    return -1;
  }
}