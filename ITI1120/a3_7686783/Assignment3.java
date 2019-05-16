// ITI 1120 Winter 2015
// Name: Aethelind Racic, ID: 7686783 


// Question A
import java.util.Random;
import java.util.Scanner;

class Assignment3
{
  // This function finds random numbers within a user-given range.
  public static void main (String[] args)
  {   
    int x, y; // To store upper and lower bounds.
    Scanner s = new Scanner(System.in); // For reading inputs.
    
    // Recieve the user's inputs for the upper and lower bounds.
    System.out.println("Please enter the lower bound.");
    x = s.nextInt();
    
    System.out.println("Please enter the upper bound.");
    y = s.nextInt();
    
    // Call getRandomNumber with the user inputs and print the result.
    System.out.println("The random number generated is " + getRandomNumber(x, y) + ".");

  }
  
  // This method gets a random number within the given range. 
  public static int getRandomNumber(int x, int y)
  {
    Random r = new Random(); // Used for generating randoms.
    int z; // The returned random.
    
    z=(r.nextInt(y+1))+x;
    // The upper bound is y+1 since it is read as an EXCLUSIVE bound, but y is an INCLUSIVE bound.
    // x is added to accomodate the lower bound.
    
    return z;
  }
}
 

/*
// Question B

import java.util.Scanner;

class Assignment3
{
 // This function prints out a user determined character, a user determined amount of times on one line.
 public static void main(String[] args)
 {
   int n; // To store the number of character iterations.
   char c; // To store the character to be iterated.
   Scanner s = new Scanner(System.in); // To read user inputs.
   
   System.out.println("Which character would you like to print?");
   c=s.next().charAt(0); 
   // This reads the input as a string, identifies the first character of this string, and assigns it to c.
   
   System.out.println("How many times would you like to print " + c + "?");
   n=s.nextInt();
   
   System.out.println("Thank you. Printing:");
   drawLine(c, n);
   System.out.println("(The cursor is on a new line now)"); 
   // This checks that the cursor is returned to a new line.
 }
 
 // This function prints the character c, n times, with a MAX of 50.
 public static void drawLine(char c, int n)
 {
   if(n>=0 && n<=50) 
   {
     // ASSUMING 0 is a valid input!
     // If n is within the range of [0, 50], it prints n characters.
     for(int i=1; i<=n; i++)
     {
       System.out.print(c);
     }
   }
   else // If n is not within range, c is printed 50 times.
   {
     for(int i=1; i<=50; i++)
     {
       System.out.print(c);
     }
   }
   
   System.out.println(); // This returns the cursor to a new line.
 }
}
*/

/*
// Question C

class Assignment3
{
 // This function prints half of a 'Christmas Tree' drawing using drawLine.
 public static void main(String[] args)
 {
   for(int i=1; i<=10; i++)
   {
     drawLine('*', i); 
     // Prints * i times on a line, ten separate times, with i increasing by one each time.
     // This is the upper part of the tree.
   }
   for(int i=1; i<=10; i++)
   {
     drawLine('*', 2);
     // Prints 10 lines, all containing "**".
     // This is the stem of the tree.
   }
 }
 // drawLine function exactly as in question B.
 public static void drawLine(char c, int n)
 {
   if(n>=0 && n<=50) // If n is within [0, 50], it prints n characters.
   {
     for(int i=1; i<=n; i++)
     {
       System.out.print(c);
     }
   }
   else // If n is not within range, c is printed 50 times.
   {
     for(int i=1; i<=50; i++)
     {
       System.out.print(c);
     }
   }
   
   System.out.println(); // This returns the cursor to a new line.
 }
}
*/

/*
// Question D
import java.util.Scanner;

class Assignment3
{
  // This function take an array of floats from the user and prints them back in reverse order.
 public static void main(String[] args)
 {
   float[] a = new float[10]; // the array of floats.
   Scanner s = new Scanner(System.in); // for scanning user values.
   
   // Recieve array from user using for loop and scanner.
   for(int j=0; j<10; j++)
   {
     System.out.println("Please enter the value for element " + j + ".");
     a[j]=s.nextFloat();
   }
   
   System.out.println("Array obtained. Printing backwards:");
   // Printing array starting from element 9 and proceeding towards element 0.
   for(int j=9; j>-1; j--)
   {
     System.out.print(a[j] + "  ");
   }
   // Putting the cursor on a new line for user.
   System.out.println();
 }
}
*/

/*
// Question E

class Assignment3
{
  // This function fills an array of length 20 with the element's corresponding factorial, and prints them.
 public static void main(String[] args)
 {
   long[] a = new long[20]; // Array of length 20 to store the factorials.
   
   for(int j=0; j<20; j++)
   {
     a[j]=factorial(j); // Fills each element from a[0] to a[19] with the factorial of the element number.
   }
   for(int j=0; j<20; j++)
   {
     System.out.println("Factorial " + j + " is " + a[j] + "."); // Prints the factorial and element.
   }
 }
 // This function calculates and returns the factorial of a given number.
 public static long factorial(int n)
 {
   long f=1; // For storing the factorial of n. (This value is returned)
   
   if(n!=0)  
   {
     // If n IS NOT zero, every number in the range [1, n] is multiplied together.
    for(int j=1; j<=n; j++)
    {
      f=f*j;
    }
   }
   // If n IS zero, f stays at its original value of 1, and is returned as such.
   
   return f;
 }
}
*/