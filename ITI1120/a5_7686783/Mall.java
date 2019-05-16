// ITI 1120 Winter 2015
// Name: Aethelind Racic, ID: 7686783 

// Assignment 5: Mall class.

class Mall
{
  String name; // Mall's name.
  int width; // vertical length of Mall, or number of rows in mallGrid.
  int length; // horizontal length of Mall, or number of columns in mall Grid.
  private Customer[][] mallGrid; // 2D array for storing customers, which represents the 'map' of Map.
  
  // Constructor:
  Mall(String name, int width, int length)
  {
    // Initializes name and dimensions, and creates array with these dimensions.
    this.name=name;
    this.width=width;
    this.length=length;
    
    mallGrid = new Customer[width][length];
  }
  
  // "Setters and Getters" for name and dimensions.
  // (Assuming dimensions are final and cannot be changed after initialization)
  String getMallName()
  {
    return name;
  }
  void setMallName(String name)
  {
    this.name=name;
  }
  int getWidth()
  {
    return width;
  }
  int getLength()
  {
    return length;
  }
 
  // Prints out mallGrid in its current state.
  void printGrid()
  {
    System.out.println("\nGrid of " + name + " mall:\n"); 
    
    // Traversing the array row by row, if there is no customer in a space, the letter E is printed.
    // If a customer is in a space, their name is printed.
    for(int j=0; j<width; j++)
    {
      for(int k=0; k<length; k++)
      {
        if(mallGrid[j][k] == null)
        {
          System.out.print("E\t");
        }
        else
        {
          System.out.print((mallGrid[j][k]).getName() + "\t");
        }
      }
      System.out.println();
    }
    System.out.println();
  }
  
  // Checks if the space at row x, column y is empty.
  boolean locationEmpty(int x, int y)
  {
    // If the location exists AND has no customer, true is returned. Otherwise, false is returned.
    // (Done in nested loops instead of with '&&' to avoid an 'out of bounds exception')
    if(locationValid(x, y))
    {
      if(mallGrid[x][y] == null)
      {
        return true;
      }
    }

    return false;
  }
  
  // Checks if the space at row x, column y is a real location on the array.
  boolean locationValid(int x, int y)
  {
    // If both x and y are between 0 (inclusive) and their corresponding dimension (exclusive), they are in bound.
    if( (x>-1 && x<width) && (y>-1 && y<length) )
      return true;
    else
      return false;
  }
  
  // Puts Customer c in the space at row x, column y.
  void setLocation(Customer c, int x, int y)
  {
    // If the customer is at another location on mallGrid, remove them.
    for(int i=0; i<width; i++)
    {
      for(int j=0; j<length; j++)
      {
        if(mallGrid[i][j] == c)
        {
          mallGrid[i][j]=null;
          break;
        }
      }
    }
 
    // Place Customer at row x, column y.
    mallGrid[x][y]=c;
  } 
}