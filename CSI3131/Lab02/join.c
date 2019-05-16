/*------------------------------------------------------------
File: join.c   (CSI3131 Lab 2)

Lab Group: 		25
Student Name: 	Aethelind Racic
				Gavin Armoogum

-------------------------------------------------------------*/

#include <stdio.h>
#include <unistd.h>
#include <pthread.h>
#include <sys/types.h>
#include <sys/wait.h>

char ID;
char *cfg_fname;
pthread_t rcv_thread_id;

/* Prototypes */
void* readMsg(void *args);

int main(int argc, char **argv)
{
  int pid1, pid2, pid3;
  int cnt = 0;
  int status;

  pid1 = fork();
  if (pid1 < 0) fprintf(stderr, "Fork Failed");
  else if (pid1 == 0) { // child
    // printf("in child 1, pid: %d %d %d\n", pid1, pid2, pid3);
    sleep(3);
    execlp ("./task", "task", "1");
  }
  else {
    pid2 = fork();
    if (pid2 < 0) fprintf(stderr, "Fork Failed");
    if (pid2 == 0) { // child
      // printf("in child 2, pid: %d %d %d\n", pid1, pid2, pid3);
      sleep(2);
      execlp ("./task", "task", "2");
    }
    else {
      pid3 = fork();
      if (pid3 < 0) fprintf(stderr, "Fork Failed");
      if (pid3 == 0) {  // child
        // printf("in child 3, pid: %d %d %d\n", pid1, pid2, pid3);
        sleep(1);
        execlp ("./task", "task", "3");
      }
      else {
        // printf("in parent inside pid: %d %d %d\n", pid1, pid2, pid3);
		// Waiting for 3 other tasks before completing parent
		wait(&status);
        wait(&status);
        wait(&status);
		execlp ("./task", "task", "4");
      }	
    }
  }

  return(0);  // All is done.
}                   
















