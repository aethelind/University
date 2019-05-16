/* CSI3131 LAB ONE */
/* Lab Group 25 */

#include <sys/types.h>
#include <sys/wait.h>
#include <stdio.h>
#include <unistd.h>

int main(){
	pid_t pid;

	/* fork a child process */
	for(int i=0;i<5;i++){
		printf ("fork \n");
		pid = fork();
		
		if (pid < 0){
			/* error occured */
			fprintf(stderr, "Fork Failed");
			return 1;
		} else if (pid == 0){
			/* child process */
			sleep(1);
			execlp("/bin/ls", "ls", NULL);
		} else {
			/* parent process */
			/* parent will wait for child to complete */
			wait(NULL);			
			printf("Child Complete \n");
		}
	}
	return 0;
}