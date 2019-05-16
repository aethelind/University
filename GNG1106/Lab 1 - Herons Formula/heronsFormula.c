
#include <stdio.h>
#include <math.h>

int main()

{
    /* This program will use Heron's formula to determine the area of the triangle defined by three user-given points. */

    float x1, x2, x3, y1, y2, y3, d1, d2, d3, s, a;

    /* Prompt the user to input the coordinates. */

    printf("Input the x-coordinate of the first point:\n");
    scanf("%f", &x1);
    printf("Input the y-coordinate of the first point:\n");
    scanf("%f", &y1);

    printf("Input the x-coordinate of the second point:\n");
    scanf("%f", &x2);
    printf("Input the y-coordinate of the second point:\n");
    scanf("%f", &y2);

    printf("Input the x-coordinate of the third point:\n");
    scanf("%f", &x3);
    printf("Input the y-coordinate of the third point:\n");
    scanf("%f", &y3);

    /* Determine the side lengths of the triangle, defined by the variables d1, d2, and d3. */

    d1=sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1));

    d2=sqrt((x3-x2)*(x3-x2)+(y3-y2)*(y3-y2));

    d3=sqrt((x1-x3)*(x1-x3)+(y1-y3)*(y1-y3));

    /* Find the semiperimeter of the triangle (half of the entire perimeter), defined by variable s. */

    s=(d1+d2+d3)/2;

    /* Compute the area of the triangle using the semiperimeter and the sidelengths. */

    a=sqrt(s*(s-d1)*(s-d2)*(s-d3));

    /* Print the area of the triangle. */

    printf("The area of this triangle is %f.\n", a);


    return 0;
}
