#include "stdio.h"

void swap(char*,char*);
void printArray(char*,int);

// Process all permutations of Array arr in the if block.
// Call with i = 0 and length = length of arr.
void permute(char *arr,int i,int length) { 
    if (length == i){
        // Process Here
        printArray(arr,length);
        return;
    }
    for (int j = i; j < length; j++) { 
        swap(arr+i, arr+j);
        permute(arr, i+1, length);
        swap(arr+i, arr+j);
    }
}

void swap(char *a, char *b){
    int tmp = *a;
    *a = *b;
    *b = tmp;
}

void printArray(char* arr, int length){
    for (int i = 0; i < length; i++){
        printf("%c", arr[i]);
    }
    printf("\n");
}

int main(){
    char arr[] = "hello";
    int length = sizeof(arr)/sizeof(arr[0]) - 1; // -1 for Null Terminator for Strings
    permute(arr,0,length);
}
