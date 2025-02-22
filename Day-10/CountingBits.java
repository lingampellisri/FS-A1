/*
You are given an integer N. Your task is to return an array ans of length N + 1 
where each ans[i] represents the number of 1's in the binary representation of i.

Input Format:
--------------
A single integer N, representing the range from 0 to N.

Output Format:
---------------
An array of N+1 integers where each element represents the count of 1s in the binary representation of each number from 0 to N.

Sample Input-1:
---------------
2

Sample Output-1:
----------------
[0,1,1]

Explanation:
------------
0 --> 0
1 --> 1
2 --> 10

Sample Input-2:
---------------
5

Sample Output-2:
--------------- 
[0,1,1,2,1,2]

Explanation:
------------
0 --> 0
1 --> 1
2 --> 10
3 --> 11
4 --> 100
5 --> 101

*/
import java.util.*;
public class CountingBits{
    private static int hammingWeight(int n) {
        // I can use Brian Kernighan's Algorithm to find number of set bits in O(k) where k is number of set bits   
        int cnt = 0;
        while(n != 0){
            n = (n & (n-1)); // this clears the rightmost set bits and now we can increment our count
            cnt++;
        }

        return cnt;
    }
    private static int countBits(int n){
        int cnt = 0;
        while(n != 0){
           
            cnt += (n & 1);
            n = n >> 1;
        }

        return cnt;
    }
    // bm = bm ^ (1 << ascii)
    // return bm == 0 || (bm & (bm - 1) == 0)
    private static int[] getBinaryArray(int n){
        int[] bin = new int[n+1];

        for(int i = 0 ; i <= n ; i++){
            bin[i] = countBits(i);
        }

        return bin;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        
        System.out.println(Arrays.toString(getBinaryArray(n)));
        sc.close();
    }
}