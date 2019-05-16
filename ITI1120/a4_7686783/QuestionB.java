// ITI 1120 Winter 2015
// Name: Aethelind Racic, ID: 7686783 

// Assignment 4, QB
// This program has a method which finds the value at a requested index.

import java.util.Scanner;

class QuestionB
{
  public static void main(String[] args)
  {
    int index; // The index of the requested value.
    int t_i; // The value being requested.
    Scanner s = new Scanner(System.in); // For scanning in user values.
    
    // Receive user's index.
    System.out.println("Please enter the index of the value you would like to find:");
    index=s.nextInt();
    
    // Check that index is in valid range.
    while(index<0)
    {
      System.out.println("Invalid input!");
      System.out.println("Please enter the index of the value you would like to find:");
      index=s.nextInt();
    }
    
    // Find value of t_i using method sequenceNumber.
    t_i=sequenceNumber(index);
    
    // Print requested value of t_i.
    System.out.println("The value at index " + index + " is " + t_i + ".");
    
  }
  // This method uses recursion to find the value at index i, in a given sequence.
  public static int sequenceNumber(int i)
  {
    int t; // The value of the pattern at index i.
    
    // Base case: The index is 0, so the sequential value is 2.
    // Otherwise: Use recursion to calculate t, from given equation for the value of t at index i.
    if(i==0)
      t=2;
    else
      t=2*(sequenceNumber(i-1))+1;
    
    return t;
  }
}