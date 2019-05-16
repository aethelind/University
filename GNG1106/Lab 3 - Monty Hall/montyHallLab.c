/*----------------------------------------------------------*/
/* Name: Aethelind Rose Racic, Student Number: 7686783      */
/* Date: Oct 23, 2014.                                      */
/* Program: montyHallLab.c                                  */
/* Description: This program simulates the Monty Hall door  */
/*              problem. It can simulate one or many games. */
/*----------------------------------------------------------*/

#include <stdio.h>
#include <stdlib.h>

/* This function determines which losing door Monty Hall will open for the user. */

int randOpenDoor(int win, int user)
{
    int open, end=1;

    while (end)
    {
        /* This assigns a random door value to 'open'. */

        open=rand()%3+1;

        /* If the assigned value is different than both the user and win values, the while loop is exited. */

        if (open!=win && open!=user)
            end=0;
    }

    /* The function returns the door number that Monty Hall will open. */

    return open;
}

/* This function simulates N games with the user's chosen strategy. */

int simulateNGames(int N, int opt)
{
    int win_door, switch_door, user_door;
    int count=0, wins=0;

    /* The following loop will run N number of times, simulating N games. */

    for (count=0; count<=N; count++)
    {
        /* For each new game, a different winning door and switch door are created. */

        win_door=rand()%3+1;
        switch_door=5-randOpenDoor(win_door, 1);

        /* If the user chose to switch every game, their door is assigned as the switch door. */
        /* If the user chose to stay every game, their door is assigned as 1. */

        if (opt==1)
            user_door=switch_door;
        if (opt==2)
            user_door=1;

        /* If the user's door is the same as the winning door, the number of wins is increased. */

        if (user_door==win_door)
            wins++;
    }

    return wins;
}

int main()
{
    /* Main loop declarations: */

    int main_opt, leave_loop=0;

    /* Game mode declarations: */

    int user_door, winner, open, switch_door;
    int game_wins=0, choice, total_games=0;

    /* Research mode declarations: */

    int opt, N, wins;
    float percent_won;

    /* This while loop will run until the user chooses to exit the program. */

    while(1)
    {
        printf("Welcome to the Monty Hall Problem Simulator!\n");
        printf("Please chose an option from below:\n");
        printf("1. Game Mode\n2. Research Mode\n3. Exit\n");
        scanf("%d", &main_opt);

        switch(main_opt)
        {
            case 1:
                /* Increase the total number of games played for the user's statistics. */

                total_games++;
                user_door=-100;

                /* Print the selections available to the user, and scan in their door choice. */

                printf("Welcome to game mode!\n");
                printf("Pick a door:\n");
                printf("DOOR 1\tDOOR 2\tDOOR 3\n");
                scanf("%d", &user_door);
                while (user_door>3 || user_door<1)
                {
                    printf("Invalid Selection. Choose again:\n");
                    printf("DOOR 1\tDOOR 2\tDOOR 3\n");
                    scanf("%d", &user_door);
                }

                /* Assign values for the winning door, the door Monty Hall opens, and the door the user may switch to. */

                winner=rand()%3+1;
                open=randOpenDoor(winner, user_door);
                switch_door=6-user_door-open;

                /* Ask the user if they would like to switch to the switch door, and scan in their choice. */

                printf("Door %d does not have a prize behind it.\n", open);
                printf("Would you like to switch to door %d instead?\n\n", switch_door);
                printf("1. Yes\n2. No\n");
                scanf("%d", &choice);

                /* If the user chose to switch, change their door selection. */

                if (choice=1)
                    user_door=switch_door;

                /* Check if the user's door is the same as the winning door and print the appropriate message. */
                /* If the user won, increase their game wins. */

                if (user_door==winner)
                {
                    printf("\nYou win!\n");
                    game_wins++;
                }
                if (user_door!=winner)
                    printf("\nYou lose!\n");

                /* Print the user's statistics, and return to the user menu. */

                printf("\nYou have won %d times out of %d games.\n\n", game_wins, total_games);

                break;
            case 2:
                printf("Welcome to Research Mode!\n");

                /* Prompt the user to enter their strategy and number of games to simulate, and scan in their selection. */

                printf("Which strategy would you like to test?\n");
                printf("1. Always switch\n2. Always stay\n");
                scanf("%d", &opt);

                printf("How many games would you like to simulate?\n");
                scanf("%d", &N);

                /* Find the games won using the simulation function. */
                /* Find the percentage won by dividing the number of games won by the total games played, and multiplying by 100. */
                /* Cast the N value as a float so that the percentage will appear as a non-zero number */

                wins=simulateNGames(N, opt);
                percent_won=(wins/(float)N)*100;

                /* Print the percentage of games won. */

                printf("\nThis strategy won %.2f percent of %d games.\n\n", percent_won, N);

                break;
            case 3:
                leave_loop=1;
                break;
            default:
                printf("Not a valid selection.\n");

        }

        if (leave_loop)
            break;

    }

    return 0;

}
