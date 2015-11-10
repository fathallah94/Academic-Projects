#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <unistd.h>
#include <errno.h>
#include<string.h>
#include<ctype.h>
#include<sys/signal.h>

void child_terminated();
//parsing input line
void parse(char line[],char *arguments[],int *args_num,int *background){
    int i=0;
    *args_num=0;

    while(line[i]!='\0'){
        while(line[i]==' ' || line[i]=='\t' || line[i]=='\n')
            line[i++]='\0';
        if(line[i]=='\0')break;
        /////////////handle CD to replace ~ by /home////////
        if(*args_num==1 && strcmp(arguments[0],"cd")==0){
            char *path =malloc(sizeof(char) * strlen(line+10));
            int ind=0;
            while(1){
                if(line[i]=='\n')
                    line[i]='\0';
                if(line[i]=='~'){
                    path[ind++]='/';
                    path[ind++]='h';
                    path[ind++]='o';
                    path[ind++]='m';
                    path[ind++]='e';
                }
                else
                    path[ind++]=line[i];
                if(line[i]=='\0')
                    break;
                i++;
            }
            arguments[*args_num]=path;
            *args_num+=1;
            break;
        }
        // handle echo
        else if(*args_num==1 && strcmp(arguments[0],"echo")==0){
            int start=i,end=i;
            while(line[end]!='\0'){
                end++;
            }
            end--;
            while(line[end]==' '|| line[end]=='\t'){
                line[end]='\0';
                end--;
            }
            if((line[start]=='"' && line[end]=='"') || (line[start]=='\'' && line[end]=='\'')){
                line[end]='\0';
                start+=1;
                arguments[*args_num]=&line[start];
                *args_num+=1;
                break;
            }
        }
        //////////////////////////////////////////
        arguments[*args_num]=&line[i];
        *args_num+=1;
        while(line[i]!=' ' && line[i]!='\t' && line[i]!='\n' && line[i]!='\0')
            i++;
    }
    /////////////handle background & ////////////
    if(*args_num>0 && strcmp(arguments[*args_num-1],"&")==0){
        *background=1;
        *args_num-=1;
        arguments[*args_num]='\0';
    }
    else{
        arguments[*args_num]='\0';
        *background=0;
    }

}
//creating child process
void CreateProcess(char *arguments[],int args_num,int background,char *log_dir){
    // CD
    if(strcmp(arguments[0],"cd")==0){
        int val=0;
        val=chdir(arguments[1]);
        if(val==-1){
            perror(strerror(errno));
        }
        return;
    }
    ////////
    signal(SIGCHLD,child_terminated);
    pid_t pid=fork();
    int status;
    if (pid  == -1)
        perror("error creating child process");
    else if (pid == 0) {
        //execute
        int val = execute(arguments,args_num);
        if(val==-1){
            perror(strerror(errno));
        }
        exit(0);
     }
     else{
        if(background)
            waitpid(pid,&status,WNOHANG);
        else
            waitpid(pid,&status,0);
        return;
     }
}
//calling execv
int execute(char *arguments[],int args_num){

    char *str = getenv("PATH");
    char *part = strtok(str,":");
    char *path= malloc(sizeof(char) * strlen(str));
    int flag=0;
    while(part!=NULL){
        strcpy(path,part);
        strcat(path,"/");
        strcat(path,arguments[0]);
        int val=0;
        val=execv(path,arguments);
        if(val!=-1) {
            flag=1;
            break;
        }
        part=strtok(NULL,":");
    }
    if(!flag){
        int val=0;
        val=execv(arguments[0],arguments);
        if(val!=-1)
            flag=1;
    }
    if(!flag)
        return -1;
    return 0;
}

void readHistory(char *history[],int *history_cnt,char *history_dir){
    FILE* file=fopen(history_dir,"r");
    *history_cnt=0;
    char *temp = malloc(sizeof(char)*10000);
    while(fgets(temp,10000,file)!=NULL){
        history[*history_cnt]=malloc(sizeof(char)*10000);
        strcpy(history[*history_cnt],temp);
        *history_cnt+=1;
    }
    fclose(file);
    return;
}
void writeHistory(char *history[],int history_cnt,char *history_dir){
    FILE* file=fopen(history_dir,"w");
    int i=0;
    for(i=0;i<history_cnt;i++){
        fprintf(file,"%s",history[i]);
    }
    fclose(file);
    return;
}
int checkExpression(char *line){
    int i=0,equals=0,start=0;
    int equalInd=0;
    while(line[i]==' ' || line[i]=='\t'){
        i++;
    }
    start=i;
    while(line[i]!='\0'){
        if(line[i]=='='){
            equals++;
            equalInd=i;
        }
        i++;
    }
    i--;
    int end=i;
    while(line[i]==' '||line[i]=='\t'){
        i--;
    }
    end=i;
    int flag=1;
    if(equals==1 ){
        for(i=equalInd-1;i>=start;i--){
            if(!isalpha(line[i]) && !isdigit(line[i]) && line[i]!='_'){
                flag=0;
                break;
            }
        }
        if(!isalpha(line[start]))
            flag=0;
        if((line[equalInd+1]=='"' && line[end]=='"') || (line[equalInd+1]=='\'' && line[end]=='\'')){}
        else{
            for(i=equalInd+1;i<=end;i++){
                if(line[i]==' ' || line[i]=='\t'){
                    flag=0;
                    break;
                }
            }
        }
    }
    else
        flag=0;
    return flag;
}

void storeVariable(char line[], char *variable_names[],char *variable_vals[],int *variable_cnt){
    int i=0,start=0,equal=0,end=0;
    while(line[i]==' ' || line[i]=='\t'){
        line[i]='\0';
        i++;
    }
    start=i;
    while(line[i]!='='){
        i++;
    }
    equal=i;
    while(line[i]!='\0')
        i++;
    i--;
    while(line[i]==' ' || line[i]=='\t'){
        line[i]='\0';
        i--;
    }
    end=i;
    int value=equal+1;
    if(line[end]=='\'' || line[end]=='"')
        line[end]='\0';
    if(line[value]=='\'' || line[value]=='"'){
        line[value]='\0';
        value+=1;
    }
    line[equal]='\0';
    int stored=0;
    for(i=0;i<*variable_cnt;i++){
        if(strcmp(variable_names[i],&line[start])==0){
            strcpy(variable_vals[i],&line[value]);
            stored=1;
            break;
        }
    }
    if(!stored){
        variable_names[*variable_cnt]=malloc(sizeof(char)*(equal-start+10));
        variable_vals[*variable_cnt]=malloc(sizeof(char)*(end-value+10));
        strcpy(variable_names[*variable_cnt],&line[start]);
        strcpy(variable_vals[*variable_cnt],&line[value]);
        *variable_cnt+=1;
    }
}

void replaceVariables(char *arguments[],int args_num, char *variable_names[],char *variable_vals[],int variable_cnt){
    int i,j;
    for(i=0;i<args_num;i++){
        if(arguments[i][0]=='$'){
            char *temp = malloc(sizeof(char)*strlen(arguments[i]));
            temp=&arguments[i][1];
            int done=0;
            for(j=0;j<variable_cnt;j++){
                if(strcmp(temp,variable_names[j])==0){
                    arguments[i]=variable_vals[j];
                    done=1;
                    break;
                }
            }
            if(!done)
                arguments[i]="\0";
        }
    }
    //check for ~ incase of cd
    if(strcmp(arguments[0],"cd")==0){
        char *temp = malloc((strlen(arguments[1])+10)*sizeof(char));
        int ind=0,i=0;
        while(1){
            if(arguments[1][i]=='~'){
                temp[ind++]='/';
                temp[ind++]='h';
                temp[ind++]='o';
                temp[ind++]='m';
                temp[ind++]='e';
            }
            else
                temp[ind++]=arguments[1][i];
            if(arguments[1][i]=='\0')
                break;
            i++;
        }
        arguments[1]=temp;
    }
}
FILE *log_file;
void child_terminated(){
    fprintf(log_file,"child process terminated\n");
}
void child_created(){
    fprintf(log_file,"child process created\n");
}
int main(int arg_number , char *arg[])
{
    //command storage
    char line[1000];
    char *arguments[500];
    int args_num=0,background;
    //history storage
    char *history[1000];
    int history_cnt=0;
    char *history_dir=malloc(sizeof(char)*1000);
    //variables storage
    char *variable_names[1000];
    char *variable_vals[1000];
    int variable_cnt=0;
    //set history file directory
    getcwd(history_dir,1000);
    strcat(history_dir,"/history.txt");
    //set log file directory
    char *log_dir=malloc(sizeof(char)*1000);
    getcwd(log_dir,1000);
    strcat(log_dir,"/log.txt");
    log_file = fopen(log_dir,"a");
    //read history from previous runs
    readHistory(history,&history_cnt,history_dir);
    //store path into variables
    variable_names[0]="PATH";
    variable_vals[0]=getenv("PATH");
    variable_cnt+=1;
    //batch file opening
    FILE *batch;
    int correctFile=1;
    if(arg_number==2){
        batch=fopen(arg[1],"r");
        if(!batch){
            perror("File not found\n");
            correctFile=0;
        }
    }
    while(correctFile){
        //interactive mode
        if(arg_number==1){
            printf("Shell > ");
            //read and check for CTRL-D
            if(gets(line)==NULL){
                break;
            }
        }
        //batch mode
        else if(arg_number==2){
            //read and check for EOF
            if(fgets(line,1000,batch)==NULL){
                break;
            }
            printf("%s\n",line);
        }
        //enter line into history
        history[history_cnt] = malloc(sizeof(char) * (strlen(line)+10));
        strcpy(history[history_cnt],line);
        strcat(history[history_cnt],"\n");
        history_cnt+=1;
        //check for expression
        if(checkExpression(line)){
            //if expression store it's name and value as strings
            storeVariable(line,variable_names,variable_vals,&variable_cnt);
            continue;
        }
        //parse line into arguments and whether it's background or foreground
        parse(line,arguments,&args_num,&background);
        //if empty line
        if(args_num==0){
            continue;
        }
        //replace $ variables by their values
        replaceVariables(arguments,args_num,variable_names,variable_vals,variable_cnt);

        //if exit
        if (strcmp(arguments[0], "exit")==0)
            break;
        // history command
        else if(strcmp(arguments[0],"history")==0){
            if(args_num==1){
                int i=0;
                for(i=0;i<history_cnt;i++)
                    printf("%s",history[i]);
            }
            else{
                perror("wrong history command\n");
            }
            continue;
        }
        //in case of comment
        if(arguments[0][0]=='#')
            continue;
        //creating child process to perform command
        CreateProcess(arguments,args_num,background,log_dir);
        printf("\n");
    }
    //close batch file if was in batchmode
    if(arg_number==2 && correctFile){
        fclose(batch);
    }
    //close log file
    fclose(log_file);
    //write history into history file
    writeHistory(history,history_cnt,history_dir);
    return 0;
}
