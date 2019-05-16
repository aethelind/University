// ITI 1120 Winter 2015
// Name: Aethelind Racic, ID: 7686783 

// Assignment 4, QE
// This program creates a mirror image of a given 2D array.

import java.util.Scanner;

class QuestionE
{
  public static void main(String[] args)
  {
    int rows, columns; // for the dimensions of the matrix.
    int[][] arr; // for the matrix (2D array)
    Scanner s = new Scanner(System.in); // for scanning user values.
    
    // Receive user values for array dimensions.
    do
    {
      System.out.println("How many rows in the matrix?");
      rows=s.nextInt();
    }while(rows<1);
    
    do
    {
      System.out.println("How many columns in the matrix?");
      columns=s.nextInt();
    }while(columns<1);
    
    // Create the matrix with these dimensions.
    arr=new int[rows][columns];
    
    // Fill the matrix elements with the user's values.
    for(int i=0; i<rows; i++)
    {
      for(int j=0; j<columns; j++)
      {
        System.out.println("Please enter the value for row " + (i+1) + ", column " + (j+1) + ".");
        arr[i][j]=s.nextInt();
      }
    }
    
    // Print the matrix before and after mirroring.
    System.out.println("The matrix before mirroring:");
    printArray(arr, rows, columns);
    
    mirrorImage(arr);
    System.out.println("The matrix after mirroring:");
    printArray(arr, rows, columns);
  }
  // A function that mirrors an 2D array, and returns this mirrored array.
  public static int[][] mirrorImage(int[][]arr)
  {
    int middle; // the value of the middle column.
    int tmp; // intermediate for swapping.
    int num_rows, num_cols; // number of rows and columns.
    
    // Find the number of rows and columns.
    num_rows=arr.length;
    num_cols=arr[0].length;
    
    // Calculate the halfway point of the array.
    middle= num_cols/2;
    
    // Now traversing row by row, swap the first value in a row with the last value in a row, 
    // getting closer and closer until hitting the middle of the array.
    for(int i=0; i<num_rows; i++)
    {
      for(int j=0; j<middle; j++)
      {
        tmp=arr[i][j];
        arr[i][j]=arr[i][(num_cols-1)-j];
        arr[i][(num_cols-1)-j]=tmp;
      }
    }
    
    return arr;
  }
  // A small function that prints a 2D array.
  public static void printArray(int[][]arr, int rows, int columns)
  {
    for(int i=0; i<rows; i++)
    {
      for(int j=0; j<columns; j++)
      {
        System.out.print(arr[i][j] + "\t");
      }
      System.out.println();
    }
  }
  
}