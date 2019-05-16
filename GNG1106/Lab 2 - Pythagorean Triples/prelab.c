#include <stdio.h>
int main ()

{
    int i, j;
    for(i=0;i<8;i++)
    {
        printf("%d ", i);
        for (j=i; j<6; j++)
            printf("%d ", j);
        printf("\n");
    }


    return 0;
}
