/*--------------------------------------------------------------*/
/* Name: Aethelind Rose Racic, Student Number: 7686783          */
/* Date: Nov 28, 2014.                                          */
/* Program: studentGradesLab.c                                  */
/* Description: This program calculates the total grade for     */
/*              students whose marks are in lab5_inputFile.txt  */
/*--------------------------------------------------------------*/

#include <stdio.h>

int main()
{
    double hmk, lab, mid, fin, tot;
    char IDs[7];
    char tmp[5];
    FILE *inputFile;
    FILE *outputFile;

    inputFile=fopen("lab5_inputFile.txt", "r");
    /* Open the input file. */

    /* Check if the input file could actually be opened. */
    if(inputFile==NULL)
        printf("lab5_inputFile.txt could not be opened.\n");
    else
    {
        outputFile=fopen("lab5_outputFile.txt", "w");
        /* This opens the new file that is going to contain all of the new data. */

        /* Check if the output file was successfully created/modified. */
        if(outputFile==NULL)
            printf("Output file could not be created.\n");
        else
        {
            fprintf(outputFile, "ID\tHmk\tLab\tMid\tFin\tTOTAL\n");
            /* Print the final .txt file headings before printing the data. */

            fscanf(inputFile, "%s%s%s%s%s", tmp, tmp, tmp, tmp, tmp);
            /* This scans the first input line in, so that the program can look at the actual data that comes after it. */

            while (fscanf(inputFile, "%s%lf%lf%lf%lf", IDs, &hmk, &lab, &mid, &fin) != EOF)
            {
                /* The scan statement must return a value of 5, since there are 5 marks to process.
                   These 5 data pieces are the student's marks/IDs */

                IDs[0]='X';
                IDs[1]='X';
                IDs[2]='X';
                /* This changes the first three characters of a student's ID to X's. */

                if(fin>mid)
                    tot=(0.1*hmk)+(0.2*lab)+(0.7*fin);
                else
                    tot=(0.1*hmk)+(0.2*lab)+(0.3*mid)+(0.4*fin);
                /* If the final mark is higher than the midterm mark, the midterm mark is overridden with the final mark, and the total mark is calculated. */

                fprintf(outputFile, "%s\t%.1lf\t%.1lf\t%.1lf\t%.1lf\t%.1lf\n", IDs, hmk, lab, mid, fin, tot);
                /* Finally, the censored ID, the 4 inputted marks, and the calculated final mark are printed in the output file. */
            }
        }

        fclose(outputFile);

    }

    fclose(inputFile);

    return 0;
}
