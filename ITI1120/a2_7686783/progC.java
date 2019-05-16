// ITI 1120 Winter 2015
// Name: Aethelind Racic, ID: 7686783 

// This program recieves ten numbers from the user, and prints their maximum. 

import java.util.Scanner;

class progC
{
  public static void main (String[] args)
  {
    int max=Integer.MIN_VALUE; // This will hold the max value, and is initialized to the lowest possible value.
    int count=0; // To go through the while loop ten times.
    int tmp; // To hold the number given by the user.
    
    Scanner s=new Scanner(System.in); // To help scan in the user's values.
    
    while(count<10)
    {
      System.out.println("Please enter a number:");
      tmp=s.nextInt();
      
      if(tmp>max)
      {
        max=tmp; 
        // If the user's number is larger than any of the previous numbers, it is stored in max.
        // On the first iteration, the number will be replaced no matter what the user enters, since max=MIN_VALUE.
      }
      
      count++; // Increment the loop.
    }
    
    System.out.println("The maximum value is " + max);
  }
}