/*
You are given an array consisting of N integers, and an integer, K. 
Your task is to determine the minimum element in subarrays of size K.

Sample Input1:
--------------
5
10 12 14 11 15

3

Sample Output1:
---------------
10 11 11

Sample Input2:
--------------
5
5 2 1 1 1
4

Sample Output2:
---------------
1 1


*/

import java.util.Scanner;

public class P2_Smallest_Element_Subarray{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        
        for(int i = 0 ; i < n ; i++){
            arr[i] = sc.nextInt();
        }
        
        int k = sc.nextInt();
        
        int i = 0 , j = 0;
        int mini = Integer.MAX_VALUE;
        while(j < n){
            if(j < i + k){
                mini = Math.min(mini,arr[j]);
                j++;
                
            }else{
                System.out.print(mini + " ");
                i++;
                j = i;
                mini = Integer.MAX_VALUE;
                
                
                
            }
        }
        
        System.out.print(mini);
        sc.close();
    }
}