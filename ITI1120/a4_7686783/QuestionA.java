// ITI 1120 Winter 2015
// Name: Aethelind Racic, ID: 7686783 

// Assignment 4, QA
// This program has a method which takes an array, and returns the sum of it's elements.

import java.util.Scanner;

class QuestionA
{
  public static void main(String[] args)
  {
    Scanner s = new Scanner(System.in); // For scanning user's values.
    int userLength=-1; // For the length of the user's array.
    int sum; // For returned sum of array elements.
    int[] array; // For user's array.
    
    // Intake user's array length.
    System.out.println("How long is your array?");
    userLength=s.nextInt();
    
    // Check the length in not invalid.
    while(userLength<1)
    {
      System.out.println("Invalid length!");
      System.out.println("How long is your array?");
      userLength=s.nextInt();      
    }
    
    // Create array of this length.
    array = new int[userLength];
    
    // Fill array with user's values
    for(int i=0; i<userLength; i++)
    {
      System.out.println("Please enter value for element " + i + ".");
      array[i]=s.nextInt();
    }
    
    // Calculate sum and print its value.
    sum=sum(array, userLength-1);
    System.out.println("The sum of the values in the array is " + sum + ".");
    
  }
  
  // This method finds the sume of elements in an array using RECURSION.
  public static int sum(int[] arr, int i)
  {
    int sum=0;
    
    if(i==0)
      sum=arr[i];
    else
      sum=arr[i]+(sum(arr, i-1));
    
    return sum;
  }
/*
  // This method finds the sum of all elements in an array. 
  // (** THIS method uses the described parameters from the question, however no recursion)
  public static int sum1(int[] arr)
  {
    int length; // For array length.
    int sum=0; // For the sum.
    
    length=arr.length; // Find array length.
    
    // Add each element to the value of sum.
    for(int j=0; j<length; j++)
      sum+=arr[j];
      
    return sum;
  }  
*/
}
