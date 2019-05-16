// ITI 1120 Winter 2015
// Name: Aethelind Racic, ID: 7686783 

// This program prints all the numbers from 1 to 1000 that are divisibly by 3. 

class progA
{
  public static void main (String[] args)
  {
    int i=1; // This will be used to represent all of the number being checked.
    
    while(i<=1000)
    {
      if(i%3 == 0)
      {
        System.out.println(i); // If the current number is divisible by three, it is printed.
      }
      
      i++;
    }
  }
}