/*
 You are given three sorted integer arrays A[], B[], and C[], and an integer target.

Your task is to find one element from each array (A[i], B[j], and C[k]) such that the sum of these three elements is equal to target.

If there is no exact match, return the triplet with the minimum absolute difference to the target.

Input Format:
-------------
Line 1: An integer N, the size of the first array.
Line 2: N space-separated integers representing elements of array A.
Line 3: An integer M, the size of the second array.
Line 4: M space-separated integers representing elements of array B.
Line 5: An integer P, the size of the third array.
Line 6: P space-separated integers representing elements of array C.
Line 7: An integer target, the required sum.

Output Format:
--------------
Line-1: Print the triplet (A[i], B[j], C[k]) that either matches the target or has the closest sum to the target.

Constraints:
------------
Time Complexity: O(N + M + P)
Space Complexity: O(1)

Sample Input-1:
---------------
4
5 10 20 30
4
1 3 7 10
4
2 5 8 12
25

Sample Output-1:
----------------
10 7 8

Explanation:
-------------
The sum 10 + 7 + 8 = 25, which exactly matches target.


Sample Input-2:
---------------
3
1 5 10
3
3 6 9
3
4 7 8
30

Sample Output-2:
----------------
10 9 8

Explanation:
------------
The sum 10 + 9 + 8 = 27, which is the closest sum to 30 (minimum absolute difference |30 - 27| = 3).
 */
import java.util.*;
public class P3_Closest_Triple_Sum {
    private static int[] closestTripletSum(int[] arr1,int m,int[] arr2,int n,int[] arr3,int p,int target){
        int closest = Integer.MAX_VALUE;
        int a = arr1[0];
        int b = arr2[0];
        int c = arr3[0];

        for(int i = 0 ; i < m ; i++){
            int j = 0;   
            int k = p - 1;

            while(j < n && k >= 0){
                int sum = arr1[i] + arr2[j] + arr3[k];

                if(sum == target){
                    return new int[]{arr1[i],arr2[j],arr3[k]};
                }

                int diff = Math.abs(target - sum);
                if(diff < closest){
                    closest = diff;
                    a = arr1[i];
                    b = arr2[j];
                    c = arr3[k];
                }
                


                if(sum < target){
                    j++;
                }else{
                    k--;
                }
            }
        }

        return new int[]{a,b,c};
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int m = sc.nextInt();
        int[] arr1 = new int[m];
        for(int i = 0 ; i < m ; i++){
            arr1[i] = sc.nextInt();
        }

        int n = sc.nextInt();
        int[] arr2 = new int[n];
        for(int i = 0 ; i < n ; i++){
            arr2[i] = sc.nextInt();
        }

        int p = sc.nextInt();
        int[] arr3 = new int[p];
        for(int i = 0 ; i < p ; i++){
            arr3[i] = sc.nextInt();
        }

        int target = sc.nextInt();

        int[] res = closestTripletSum(arr1,m,arr2,n,arr3,p,target);
        System.out.print(res[0] + " " + res[1] + " " + res[2]);
        
        sc.close();
    }
}
