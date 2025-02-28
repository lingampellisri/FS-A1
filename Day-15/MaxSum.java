/*
Mr.Sharvanand is playing with array of integers, his task is to find largest sum. The sum is calculated by adding individual products( multiplying each element of array with its position) and every time rotate nums in clockwise direction, like this find n different  sums if there are n elements in an array, finally return the largest of these sums. Help sharvanand to find largest sum.  
you are given an integer array nums of length n.

Assume arrk to be an array obtained by rotating nums by k positions clock-wise. We define the rotation function F on nums as follow:

F(k) = 0 * arrk[0] + 1 * arrk[1] + ... + (n - 1) * arrk[n - 1].
Return the maximum value of F(0), F(1), ..., F(n-1).

The test cases are generated so that the answer fits in a 32-bit integer.
Example 1:
Input: 4 
 4 3 2 6
Output: 26
Explanation:
F(0) = (0 * 4) + (1 * 3) + (2 * 2) + (3 * 6) = 0 + 3 + 4 + 18 = 25
F(1) = (0 * 6) + (1 * 4) + (2 * 3) + (3 * 2) = 0 + 4 + 6 + 6 = 16
F(2) = (0 * 2) + (1 * 6) + (2 * 4) + (3 * 3) = 0 + 6 + 8 + 9 = 23
F(3) = (0 * 3) + (1 * 2) + (2 * 6) + (3 * 4) = 0 + 2 + 12 + 12 = 26
So the maximum value of F(0), F(1), F(2), F(3) is F(3) = 26.

Example 2:
Input: 1
100
Output: 0
 */
import java.util.*;
public class MaxSum {
    private static int maxi;
    
    // first  calc sum
    private static int indexWiseSum(Deque<Integer> lst,int n){
        int sum = 0;
        int i = 0;
        for(int num : lst){
            sum += i * num;
            i++;
        }

        return sum;
    }
    // Approach:-i) T.C:- O(N^2) , S.C:- O(N + N)
    private static void solveRecurse(int idx,Deque<Integer> lst,int n){

        // this is a recursive way to solve the question , 
        // always move the last element to the front 
        
        if(idx == n){
            return;
        }
        maxi = Math.max(maxi,indexWiseSum(lst,n));

        // now move the last element to first 
        int last = lst.removeLast();

        lst.addFirst(last);

        solveRecurse(idx+1,lst,n);

    }

    // Approach-ii) Use tabulation  instead of recursion 
    // T.C:- O(N) , S.C:- O(N)

    private static int maxRotationSum1(int[] arr,int n){

        int[] dp = new int[n];
        // calc intial dp[0]
        
        for(int i = 0 ; i < n ; i++){
            dp[0] += i * arr[i];
        }
        int sum = Arrays.stream(arr).sum();
        int maxRotateSum = dp[0];
        for(int i = 1 ; i < n ; i++){
            dp[i] = dp[i-1] + sum - arr[n-i]*n;
            maxRotateSum = Math.max(maxRotateSum,dp[i]);
        }

        return maxRotateSum;
    }

    // Approach-iii) Use tabulation  instead of recursion and also don't use a data structute to store the sum , just a varaible itself is sufficent
    // T.C:- O(N) , S.C:- O(1)
    private static int maxRotationSum2(int[] arr,int n){

        int F = 0;
        
        for(int i = 0 ; i < n ; i++){
            F += i * arr[i];
        }
        int sum = Arrays.stream(arr).sum();
        int maxRotateSum = F;
        for(int i = 1 ; i < n ; i++){
            F += sum - arr[n-i]*n;
            maxRotateSum = Math.max(maxRotateSum,F);
        }

        return maxRotateSum;
    }



    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        maxi = Integer.MIN_VALUE;
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i = 0 ; i < n ; i++){
            arr[i] = sc.nextInt();   
        }
        
        System.out.println(maxRotationSum2(arr,n));

        // uncomment this for method-i)
        // Deque<Integer> dq = new ArrayDeque<>();
        // for(int i = 0 ; i < n ; i++){
        //     dq.addLast(arr[i]);
        // }
        // solveRecurse(0,dq,n);
        // System.out.println(maxi);
        
        sc.close();
    }
}
