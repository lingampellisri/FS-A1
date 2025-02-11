/*
You are given a 0-indexed string blocks of length n, 
where each character in blocks[i] represents the color of the i-th block:
-> 'W' represents a white block.
-> 'B' represents a black block.
You are also given an integer k, which represents the desired number of consecutive black blocks.

Operation Allowed is You can recolor a white block (W) to black (B) in a single operation.

Your goal is to determine the minimum number of operations required 
to ensure that at least one sequence of k consecutive black blocks exists.

You are given a 0-indexed string blocks of length n, 
where blocks[i] is either 'W' or 'B', representing the color of the ith block. 
The characters 'W' and 'B' denote the colors white and black, respectively.


Input Format: 
-------------
Line-1: A string 'blocks' representing the sequence of blocks.
Line-2: An integer 'k' (desired consecutive black blocks).

Output Format:
--------------
Line-1: A single integer representing the minimum number of operations required.
 
Sample Input-1:
---------------
WBBWWBBWBW
7

Sample Output-1:
----------------
3

Explanation:
-------------
One way to achieve 7 consecutive black blocks is to recolor the 0th, 3rd, and 4th blocks
so that blocks = "BBBBBBBWBW". 
It can be shown that there is no way to achieve 7 consecutive black blocks in less than 3 operations.
Therefore, we return 3.


Sample Input-2:
---------------
WBWBBBW
2

Sample Output-2:
----------------
0

Explanation:
-------------
No changes need to be made, since 2 consecutive black blocks already exist.
Therefore, we return 0.
 

Constraints:
------------
-> n == blocks.length
-> 1 <= n <= 100
-> blocks[i] is either 'W' or 'B'.
-> 1 <= k <= n
 */
import java.util.Scanner;
public class P4_ReColor {
    private static int getMinOps(String s,int k,int n){
        int minOps = n;

        int i = 0 , j = 0;

        int whiteCnt = 0; // we need to minimize the white count in a sub array of size k

        while(j < n){
            if(s.charAt(j) == 'W'){
                whiteCnt++;
            }
            if(j - i + 1 == k){
                minOps = Math.min(minOps,whiteCnt);
               
                if(s.charAt(i) == 'W'){
                    whiteCnt--;
                }

                i++;
            }

            j++;
        }


        return minOps;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        String s = sc.next();

        int k = sc.nextInt();

        System.out.println(getMinOps(s,k,s.length()));
        
        sc.close();
    }
}




        

