/*
In a forest, There are N redwoord trees in a row.
You are given the heights of the trees as heights[],

You are task is to find the longest tree arrangement as follows:
	- Minimum size of the tree arrangement is 3.
	- And there exist a Tree-'i' with heights[i], where 0 < i < N-1.
		- heights[0] < heights[1] < heights[2] <...< heights[i] and
		-  heights[i] > heights[i+1] > heights[i+2] >...>heights[N-1] 

And return the length of the longest tree arrangement.
If there is no such arrangement, return 0.

Input Format:
-------------
Line-1: An integer N, number of elements.
Line-2: N space separated integers, value of the elements.

Output Format:
--------------
Print an integer result.


Sample Input-1:
---------------
8
4 2 5 7 4 2 3 6

Sample Output-1:
----------------
5

Explanation:
------------
The longest tree arrangement is : 2 5 7 4 2


Sample Input-2:
---------------
4
2 4 5 7

Sample Output-2:
----------------
0
 */
import java.util.*;
public class TallestTree {

    // T.C:- O(N^2) , S.C:- O(1)
    private static int getLongestTreeArrangment(int[] heights,int n){
        int longest = 0;

        // the first element cannot be my peak , and last element cannot be my peak

        for(int i = 1 ; i <= n - 2 ; i++){
            // check if it's a peak element 
            if(heights[i] > heights[i-1] && heights[i] > heights[i+1]){
                // it is a peak since this is greater than both of it's nbrs

                // go and find out the left lower points of this valley
                int j = i;

                while(j > 0 && heights[j] > heights[j-1]){
                    j--;
                }

                int left_height = i - j + 1;

                // now go and find right lower points of this valley

                j = i;

                while(j + 1 < n && heights[j] > heights[j+1]){
                    j++;
                }

                int right_height = j - i + 1;

                longest = Math.max(longest,left_height + right_height - 1); // since peak element got added twice remove one of the occrrence
            }
        }
        return longest;
    }

    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();

        int[] heights = new int[n];

        for(int i = 0 ; i < n ; i++){
            heights[i] = sc.nextInt();
        }

        System.out.println(getLongestTreeArrangment(heights,n));
        
        sc.close();
    }
}
