// ITI 1120 Winter 2015
// Name: Aethelind Racic, ID: 7686783 

// This program computes the standart deviation of 5 user-given numbers.

import java.util.Scanner;

class progE
{
  public static void main (String[] args)
  {
   double num1, num2, num3, num4, num5; 
   // to store the user inputs, and their further manipulations.
   double avg, sum_SOD, std_deviation; 
   // to store the average, the sum of the square of differences, and standard deviation.
   
   Scanner s=new Scanner(System.in); // for scanning user inputs.
   
   // The next few lines will read in each of the user's inputs.
   System.out.println("Please enter the first number.");
   num1=s.nextDouble();
   
   System.out.println("Please enter the second number.");
   num2=s.nextDouble();
   
   System.out.println("Please enter the third number.");
   num3=s.nextDouble();
   
   System.out.println("Please enter the fourth number.");
   num4=s.nextDouble();
   
   System.out.println("Please enter the fifth number.");
   num5=s.nextDouble();
   
   avg=(num1+num2+num3+num4+num5)/5; // This computes the average of the numbers.
   
   // Now calculating the square of the differences.
   // Note: Math.pow is not used to square, as it slows computation time.
   num1=(num1-avg)*(num1-avg); 
   num2=(num2-avg)*(num2-avg);
   num3=(num3-avg)*(num3-avg);
   num4=(num4-avg)*(num4-avg);
   num5=(num5-avg)*(num5-avg);
   
   // Calculating the sum of the square of differences, all divided by 5.
   sum_SOD=(num1+num2+num3+num4+num5)/5;
   
   // Calculating the standard deviation:
   std_deviation=Math.sqrt(sum_SOD);
   
   System.out.println("The standard deviation of these numbers is " + std_deviation + ".");
  }
}
