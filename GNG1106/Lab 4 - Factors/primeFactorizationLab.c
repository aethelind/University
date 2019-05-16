/*----------------------------------------------------------*/
/* Name: Aethelind Rose Racic, Student Number: 7686783      */
/* Date: Nov 14, 2014.                                      */
/* Program: primeFactorizationLab.c                         */
/* Description: This program gives the prime factorization  */
/*              of any number between 2 and 1,000,000.      */
/*----------------------------------------------------------*/

#include <stdio.h>

int main()
{
    int N, n, i, j=0, k, tmp;
    int a[20];
    /* variables N and n will be used as placeholders for the user's chosen value. */
    /* array a will be used to store factors of N. 20 is the chosen size, as no number
       in the allowed range will have more than 20 factors. */

    printf("What number between 2 and 1,000,000 would you like to factor?\n");
    scanf("%d", &N);
    if (N<2 || N>1000000)
    {
        printf("Please enter a valid number.\n");
        scanf("%d", &N);
    }

    n=N;
    /* n will be kept as a copy of the user's original value while N will be manipulated to find the factors. */

    for (i=2; i<N; i++)
    {
        if (N%i==0)
        {
            a[j]=i;
            j++;
            /* once a factor is found, it is stored in the array. */
            N=N/i;
            i=1;
            /* N is changed to the original value divided by its newfound factor, i is reset
                to 1 to restart to process. */
        }
    }

    if (j==0)
    {
        /* if j is 0, no factors were stored in array, and therefore N is prime. */
        printf("\n%d is a prime number.", n);
    }
    else
    {
        a[j]=N;
        j++;

        /* If factors are found, the last factor is not stored in the array in the loop above,
           since it is the last N value, and is prime. This value is stored in the array here. */

        for (i=0; i<j; i++)
            for (k=i+1; k<j; k++)
                if (a[i]>a[k])
                {
                    tmp=a[k];
                    a[k]=a[i];
                    a[i]=tmp;
                }

        /* The above loop sorts the piece of the array that has been used to store the factors, in ascending order. */

        printf("\n%d is not prime. \n\n\t%d = ", n, n);

        for (i=0; i<j; i++)
        {
            printf("%d ", a[i]);
                if (i!=(j-1))
                    printf("x ");
        }

        /* This prints all the factors of N found. */
    }

    printf("\n");

    return 0;
}
