// ITI 1120 Winter 2015
// Name: Aethelind Racic, ID: 7686783 

// Assignment 4, QC
// This program finds spaces within a character array.

import java.util.Scanner;

class QuestionC
{
  public static void main(String[] args)
  {
    Scanner s = new Scanner(System.in); // For scanning user values.
    char[] array; // For the user's char array.
    int start, end, num_spaces; // For the range, and number of spaces in this range.
    
    // Receive the user's array as a string, and convert it to a char array.
    System.out.println("Please enter your char array to be analysed:");
    array=s.nextLine().toCharArray();
  
    // Prompt the user to enter their desired range until they enter a valid number.
    while(true)
    {
      System.out.println("At which character would you like to begin?");
      start=s.nextInt();
      if(start<0 || start>=array.length)
        System.out.println("Invalid entry!");
      else
        break;
    }  
    while(true)
    {
      System.out.println("At which character would you like to end?");
      end=s.nextInt();
      if(end>=array.length || start>end)
        System.out.println("Invalid entry!");
      else
        break;
    } 
    
    // Calculate the number of spaces in this array, in this range.
    num_spaces=spaceCount(array, start, end);
    
    // Print this result.
    System.out.println("The character array has " + num_spaces + " spaces in this range.");
  }
  // This method counts the number of spaces in a given array/range using recursion.
  public static int spaceCount(char[] arr, int s, int e)
  {
    int spaces=0; // For the number of spaces. This value is returned.
    
    // If the range is only one element long, only check that element for space/no space.
    if(s==e)
    {
      if(arr[s]==' ')
      {
        spaces++;
      }
    }
    // Otherwise, the spaceCount is the sum of # spaces in the last element (range e to e), 
    // plus the # spaces in all the elements below it (range s to e-1).
    else
    {
      spaces=spaceCount(arr, e, e) + spaceCount(arr, s, e-1);
    }
    
    return spaces;
  }
}