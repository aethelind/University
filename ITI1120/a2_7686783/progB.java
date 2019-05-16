// ITI 1120 Winter 2015
// Name: Aethelind Racic, ID: 7686783 

// This program prints all the numbers from (start) to (end) that are divisibly by (n). 

import java.util.Scanner;

class progB
{
  public static void main (String[] args)
  {
    int start, end; // These variables will take on a user given value and represent the range of dividends.
    int n; // This will take on a user given value and represent the divisor.
    Scanner s=new Scanner(System.in); // This is used to scan user values in.
    
    // The next few statements ask for and recieve numbers from the user, 
    // and assign them to their corresponding variables.
    
    System.out.println("What number would you like the range to begin at?");
    start=s.nextInt();
    
    System.out.println("What number would you like the range to end at?");
    end=s.nextInt();
    
    System.out.println("What number would you like to check divisibility for?");
    n=s.nextInt();
    
    System.out.println("The numbers between " + start + " and " + end + " that are divisible by " + n + " are:");
    
    while(start <= end)
    {
      if(start%n == 0)
      {
        System.out.println(start); // If the current number is divisible by n, it is printed.
      }
      
      start++; // The loop is incremented.
    }  
  }
}