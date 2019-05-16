/*-------------------------------------------------------*/
/* Name: Aethelind Rose Racic, Student Number: 7686783   */
/* Date: Oct 2, 2014.                                    */
/* Program: pythagoreanTriplesLab.c                      */
/* Description: This program prints out all Pythagorean  */
/*              Triples within the range 0 to X.         */
/*-------------------------------------------------------*/

#include <stdio.h>

/* The first fuction finds the number of triples in a given range. */

int findNumberOfTriples(int N)
{
    /* The variables a, b, and c represent the side-legths of the triangle, and i is the count of pythagorean triples. */

    int a, b, c, i=0;

    /* The following three 'for' statements ensure that a<b<c<N */

    for (c=0; c<N; c++)
    {
        for (b=0; b<c; b++)
        {
            for (a=0; a<b; a++)
            {
                /* This 'if' statement increases the count of pythagorean triples if one is found. */

                if (a*a+b*b==c*c)
                    i++;
            }
        }
    }

    /* The function returns the count of pythagorean triples in a given range. */

    return i;
}

/* The main function will read the user input, compute the number of triples, list them, and state the largest. */

int main()
{
    int a, b, c, X, I, a_max, b_max, c_max;

    c_max=-999;
    X=-999;

    /* Prompt the user to input a value, and assign this value to X. */

    printf("Enter a positive integer:\n");
    scanf("%d", &X);

    /* Use the findNumberOfTriples function to find the number of triples below X, and assign the number to I. */

    I=findNumberOfTriples(X);

    /* If there are any triples found, print the number of them, compute their values, and state the largest. */

    if (I>0)
    {
        printf("\nThere are %d Pythagorean triples in this range.\n\n", I);

        for (c=0; c<X; c++)
        {
            for (b=0; b<c; b++)
            {
                for (a=0; a<b; a++)
                {
                    /* This 'if' prints (a, b, c) if the values are a pythagorean triple. */

                    if (a*a+b*b==c*c)
                    {
                        printf("(%d, %d, %d)\n", a, b, c);

                        /* This 'if' assigns the values of the largest pythagorean triple in the range to the _max variables. */

                        if (c>c_max)
                        {
                            a_max=a;
                            b_max=b;
                            c_max=c;
                        }
                    }
                }
            }
        }

        /* The largest pythagorean triple set is printed for the user. */

        printf("\nThe triple with the largest value of c is (%d, %d, %d).\n", a_max, b_max, c_max);

    }

    /* If there are no triples found, no calculations will be done, and the following message printed. */

    else
    {
        printf("\nThere are no Pythagorean triples in this range.\n");
    }


    return 0;
}
