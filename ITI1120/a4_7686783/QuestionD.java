// ITI 1120 Winter 2015
// Name: Aethelind Racic, ID: 7686783 

// Assignment 4, QD
// This program creates a 3x4 array, and finds values within it.

import java.util.Scanner;

class QuestionD
{
  public static void main(String[] args)
  {
    Scanner s = new Scanner(System.in); // For scanning user values.
    int x; // The user's matrix coefficient.
    int n; // The user's value to be searched for.
    int row; // The row in which n appears.
    int[][] arr = new int[3][4]; // The 3x4 matrix.
    
    // Receive the user's x value:
    System.out.println("Please enter the value for x:");
    x=s.nextInt();
    
    // Make the array with this x value.
    createArray(arr, x);
    
    // Receive the user's value to be searched for:
    System.out.println("Please enter the value to be searched for:");
    n=s.nextInt();
    
    // Find the row this number appears in.
    row=findRow(arr, n);
    
    // If the function returns -1, n is not in the matrix.
    // The function returns the element number of the row it appears in, so adding one reflects the actual row number.
    // The print statements are made accordingly.
    if(row!=-1)
      System.out.println(n + " appears in row " + (row+1) + ".");
    else
      System.out.println(n + " does not appear in the matrix.");
                           
  }
  // This function creates a 3x4 array and fills its elements according to a pattern.
  public static void createArray(int[][] arr, int x)
  {
    // Fill this array's elements with the according to the pattern defined in Question D.
    for(int i=0; i<3; i++)
    {
      for(int j=0; j<4; j++)
      {
        arr[i][j]=x*(4*i + (j+1));
      }
    }
  }
  // This array finds which row a number appears in, if it appears in any.
  public static int findRow(int[][] arr, int n)
  {
    int row=-1; 
    // This represents the row # in which n appears/
    // row is initialized to -1, so if n is not found in the array, -1 is returned to indicate that.
    
    for(int i=0; i<3; i++)
    {
      for(int j=0; j<4; j++)
      {
        if(arr[i][j]==n)
          row=i;
      }
    }
    
    return row;
  }
}