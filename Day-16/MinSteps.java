/*
Given a positive integer n, you can apply one of the following operations:

If n is even, replace n with n / 2.
If n is odd, replace n with either n + 1 or n-1 .
Return the minimum number of operations needed for n to become 1.

Sample Input-1:
---------------
8


Sample Output-1: 
----------------
3

Explanation: 8 -> 4 -> 2 -> 1

Sample Input-2:
---------------
7

Sample Output-2: 
----------------
4

Explanation: 7 -> 8 -> 4 -> 2 -> 1
or 7 -> 6 -> 3 -> 2 -> 1

13

4 -> 100
3 -> 011

 */
import java.util.*;
public class MinSteps {
    
    // Approach-i) T.C:- O(n) , S.C:- O(n)
    private static int minStepsRecursion(int n){
        if(n == 1) return 0;

        if((n & 1) == 0){
            return 1 + minStepsRecursion(n>>1);
        }

        return 1 + Math.min(minStepsRecursion(n-1),minStepsRecursion(n+1));
    }

    // Approach-ii) T.C:- O(logn) , S.C:- O(1)
    private static int getMinSteps(int n){
        int ops = 0;

        while(n != 1){
            // check if n is even or odd
            boolean isEven = (n & 1) == 0;
            if(isEven){
                // just right shift by 1 bit is same as division by 2
                n >>= 1;
            }else{
                // check which has more ending zeros 
                if(n == 3 || ((n & 2) == 0)){
                    --n;
                }else{
                    ++n;
                }
            }

            ops++;
        }

        return ops; 
    }
    // 100 -> 50 -> 25 -> 24 -> 12 -> 6 -> 3 -> 2 -> 1
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();

        // System.out.println(getMinSteps(n));
        System.out.println(minStepsRecursion(n));
        
        sc.close();
    }
}
 