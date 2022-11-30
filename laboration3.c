//#include "header.h"
//#include "randomWrite.C"

#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <string.h>

FILE *openfil(char namn[]);
void skriv(FILE *fp);

int main(int argc, const char * argv[]) {

    int amount, choice;

    FILE *fp;
    fp=openfil("lotto.txt");

    printf("1. Create lottery & write to file\n");
    printf("2. Read File \n");
    printf("3. Close \n");

    scanf("%i", &choice);

    switch(choice){
        case 1:

            printf("Create lottery tickets \n");
            printf("How many tickets? \n");
            scanf("%i", &amount);
            printf("\n");

            srand((int)time(NULL));
            int lottery[7];

            char lotteryStr[2];

            for(int i = 0; i < amount; i++) {
                for(int j = 0; j < 7; j++) {

                    lottery[j] = rand() % 35 + 1;
                    sprintf(lotteryStr, "%i", lottery[j]);
                    printf( "%2s ", lotteryStr);

                    fputs(lotteryStr, fp);
                    fputs(" ", fp);
                    if ((j + 1) % 7 == 0){
                        fputc('\n',fp);
                    }
                }
                 printf(" | ");
                for (int l = 0; l < 7; l++){

                    for(int m = 0;  m < 7; m++){

                        int temp = lottery[l];
                        lottery[l] = 0;

                        while(temp == lottery[m]){
                            temp = rand() % 35 + 1;
                        }
                        lottery[l] = temp;
                    }
                }
                for(int n = 0; n < 7; n++ ){
                    printf("%2i ", lottery[n]);
                }
                printf("\n");
            }
           break;

        case 2:
            printf("Read File \n");
            char tkn;
          tkn=fgetc(fp);
          while(!feof(fp)){
            printf("%c",tkn);
            tkn=fgetc(fp);
           }
            break;

        case 3:
            printf("Program ended");
            exit(1);
            break;
    }

}

FILE *openfil(char namn[]){
  FILE *fp;
  if ((fp = fopen(namn, "r+")) == NULL)
    if ((fp = fopen(namn, "w+")) == NULL){
      printf("fel\n");
      return NULL;
  }
  return fp;
}
