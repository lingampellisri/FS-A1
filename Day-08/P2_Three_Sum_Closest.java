/*
You are given an integer array nums of length n and an integer target. 
Your task is to find three integers in nums such that their sum is closest to the given target.

Return the sum of these three integers.

You may assume that each input would have exactly one solution.

Input Format:
-------------
Line-1: An integer n, the size of the array.
Line-2: n space-separated integers representing the elements of the array nums.
Line-3: A single integer target.

Output Format:
--------------
Line-1: Print a single integer, representing the sum of three integers closest to the target.

Sample Input-1:
---------------
4
-1 2 1 -4
1

Sample Output-1:
----------------
2

Sample Input-2:
---------------
3
0 0 0
1

Sample Output-2:
----------------
0

 */
import java.util.*;
public class P2_Three_Sum_Closest {
    
    private static int threeSum(int[] arr,int n,int target){
        int closest = Integer.MAX_VALUE;
        int a = arr[0];
        int b = arr[0];
        int c = arr[0];
        for(int i = 0 ; i < n ; i++){
            int j = i + 1;
            int k = n - 1;

            while(j < k){
                int sum = arr[i] + arr[j] + arr[k];
                if(sum == target) return sum;   
                int diff = Math.abs(target - sum);
                if(diff < closest){
                    closest = diff;
                    a = arr[i];
                    b = arr[j];
                    c = arr[k];
                }
                if(sum < target){
                    j++;
                }else{
                    k--;
                }
            }
        }

        return a + b + c;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        int[] arr = new int[n];

        for(int i = 0 ; i < n ; i++){
            arr[i] = sc.nextInt();   
        }

        int target = sc.nextInt();
        
        // first sort the array
        Arrays.sort(arr);

        
        System.out.println(threeSum(arr,n,target));
        sc.close();
    }
}
