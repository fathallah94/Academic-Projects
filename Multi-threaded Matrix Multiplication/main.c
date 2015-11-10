#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <unistd.h>
#include <errno.h>
#include<string.h>
#include<ctype.h>
#include<sys/signal.h>
#include<pthread.h>
#include<time.h>

int n1,m1,n2,m2;
double **matrix1;
double **matrix2;
double **matrix3;

void read_file1(FILE* file){
    fscanf(file,"row=%d col=%d",&n1,&m1);
    matrix_allocate(&matrix1,n1,m1);
    int i,j;
    for(i=0;i<n1;i++){
        for(j=0;j<m1;j++){
            fscanf(file,"%lf",&matrix1[i][j]);
        }
    }
}
void read_file2(FILE* file){
    fscanf(file,"row=%d col=%d",&n2,&m2);
    matrix_allocate(&matrix2,n2,m2);
    int i,j;
    for(i=0;i<n2;i++){
        for(j=0;j<m2;j++){
            fscanf(file,"%lf",&matrix2[i][j]);
        }
    }
}
void matrix_allocate(double ***matrix,int n,int m){
    *matrix = (double **)malloc(sizeof(double *)*n);
    int i;
    for(i=0;i<n;i++)
        (*matrix)[i]=malloc(m*sizeof(double *));
}

void * method1(void * row_num){
    int row=(int)row_num;
    int i,j;
    for(i=0;i<m2;i++){
        matrix3[row][i]=0;
        for(j=0;j<n2;j++){
            matrix3[row][i]+=matrix1[row][j]*matrix2[j][i];
        }
    }
    pthread_exit(NULL);
}

void* method2(void* tID){
    int thread_id=(int)tID;
    int row=thread_id/m2;
    int col=thread_id%m2;
    int i;
    for(i=0;i<n2;i++){
        matrix3[row][col]+=matrix1[row][i]*matrix2[i][col];
    }
    pthread_exit(NULL);
}
void write_file(FILE* file){
    int i,j;
    for(i=0;i<n1;i++){
        for(j=0;j<m2;j++){
            fprintf(file,"%.2lf\t",matrix3[i][j]);
        }
        fprintf(file,"\n");
    }
}

int main(int args_num,char *args[])
{
    //modify output file name
    char output[1000];
    if(args_num==4){
        int i=0;
        for(i=0;i<strlen(args[3]);i++){
            if(args[3][i]=='.'){
                output[i]='\0';
                break;
            }
            output[i]=args[3][i];
        }
    }
    // open files
    char *file_dir = malloc(sizeof(char) * 1000);
    FILE* file1;
    FILE* file2;
    FILE* file3;
    FILE* file4;
    //open file 1
    if(args_num>=2){
        getcwd(file_dir,1000);
        strcat(file_dir,"/");
        strcat(file_dir,args[1]);
        file1=fopen(file_dir,"r");
        if(!file1){
            getcwd(file_dir,1000);
            strcat(file_dir,"/a.txt");
            file1=fopen(file_dir,"r");
        }
    }
    else{
        getcwd(file_dir,1000);
        strcat(file_dir,"/a.txt");
        file1=fopen(file_dir,"r");
    }
    //open file 2
    if(args_num>=3){
        getcwd(file_dir,1000);
        strcat(file_dir,"/");
        strcat(file_dir,args[2]);
        file2=fopen(file_dir,"r");
        if(!file2){
            getcwd(file_dir,1000);
            strcat(file_dir,"/b.txt");
            file2=fopen(file_dir,"r");
        }
    }
    else{
        getcwd(file_dir,1000);
        strcat(file_dir,"/b.txt");
        file2=fopen(file_dir,"r");
    }
    //open file 3 and 4
     if(args_num==4){
        getcwd(file_dir,1000);
        strcat(file_dir,"/");
        strcat(file_dir,output);
        strcat(file_dir,"_1");
        file3=fopen(file_dir,"w");

        getcwd(file_dir,1000);
        strcat(file_dir,"/");
        strcat(file_dir,output);
        strcat(file_dir,"_2");
        file4 = fopen(file_dir,"w");

        if(!file3 || !file4){
            getcwd(file_dir,1000);
            strcat(file_dir,"/c_1.out");
            file3=fopen(file_dir,"w");

            getcwd(file_dir,1000);
            strcat(file_dir,"/c_2.out");
            file4=fopen(file_dir,"w");
        }
    }
    else{
       getcwd(file_dir,1000);
        strcat(file_dir,"/c_1.out");
        file3=fopen(file_dir,"w");

        getcwd(file_dir,1000);
        strcat(file_dir,"/c_2.out");
        file4=fopen(file_dir,"w");
    }
    if(!file1 || !file2){
        printf("Error in input files.\n");
        exit(0);
    }
    //read file 1
    read_file1(file1);
    //read file 2
    read_file2(file2);
    // check for multiplication condition
    if(m1!=n2){
        printf("m1 does not equal n2, can not do multiplication.\n");
        exit(0);
    }
    //allocate matrix3 and matrix4
    matrix_allocate(&matrix3,n1,m2);
    //method 1
    int i,j;
    pthread_t threads[n1][m2];
    clock_t start=clock();

    int flag1=1,flag2=1;
    for(i=0;i<n1;i++){
        int return_code= pthread_create(&threads[i][0],NULL,method1,(void *)i);
        if(return_code){
            //error occured creating thread
            flag1=0;
            break;
        }
    }
    //join threads to continue with method2
    int k;
    for(k=0;k<i;k++){
        pthread_join(threads[k][0],NULL);
    }
    //output1
    if(flag1){
        clock_t mthd1=clock();
        printf("Method 1\n");
        printf("Number of threads = %d\n",n1);
        printf("Execution time = %lf milliseconds\n",(double) (mthd1-start)/CLOCKS_PER_SEC*1000.0);
        write_file(file3);
    }
    else
        printf("Error occured in method 1.\n");

    //initialize matrix3
    for(i=0;i<n1;i++){
        for(j=0;j<m2;j++)
            matrix3[i][j]=0;
    }
    //method 2
    clock_t start2 = clock();
    for(i=0;i<n1;i++){
        for(j=0;j<m2;j++){
            int return_code= pthread_create(&threads[i][j],NULL,method2,(void *)(i*m2+j));
            if(return_code){
                flag2=0;
                break;
            }
        }
    }
    int w;
    for(w=0;w<i;w++){
        for(k=0;k<j;k++){
            pthread_join(threads[w][k],NULL);
        }
    }
    //output 2
    if(flag2){
        clock_t mthd2=clock();
        printf("Method 2\n");
        printf("Number of threads = %d\n",n1*m2);
        printf("Execution time = %lf milliseconds\n",(double)(mthd2-start2)/CLOCKS_PER_SEC*1000.0);
        write_file(file4);
    }
    else
        printf("Error occured in method 2.\n");


    //close files
    fclose(file1);
    fclose(file2);
    fclose(file3);
    fclose(file4);
    return 0;
}
