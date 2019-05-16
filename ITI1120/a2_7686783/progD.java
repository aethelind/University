// ITI 1120 Winter 2015
// Name: Aethelind Racic, ID: 7686783 

// This program recieves ten numbers from the user, and prints the average of the max and min.

import java.util.Scanner;

class progD
{
  public static void main (String[] args)
  {
    int min=Integer.MAX_VALUE, max=Integer.MIN_VALUE; 
    // The variables for the minimum and maximum values are initialized 
    // to the highest and lowest possible values, respectively. 
    int tmp, i=0; // tmp is for keeping track of the user's inputted values, i is for looping.
    double avg; // avg will store the computed average.
    Scanner s=new Scanner(System.in); // for scanning user values.
    
    while(i<10)
    {
      System.out.println("Please enter a number:");
      tmp=s.nextInt();
      
      if(tmp>max)
      {
        max=tmp; // If the user's value is greater than the current maximum, it becomes the new max.
      }
      if(tmp<min)
      {
        min=tmp; // If the user's value is less than the current minimum, it becomes the new min.
      }
      
      i++; // The loop is incremented.
    }
    
    avg=(double)(max+min)/2; // The average is computed.
    // max and min are cast as double's in order to retain decimals in the average.
    
    System.out.println("The minimum is " + min + ", the maximum is " + max + ", and their average is " + avg + ".");
  }
}