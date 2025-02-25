/*
 Given an integer array nums sorted in non-decreasing order, 
return an array of the squares of each number sorted in non-decreasing order.

Input Format:
-------------
Line-1: An integer N
Line-2: N space seperated integers

Output Format:
--------------
Line-1: A list of integers

Sample Input-1:
---------------
5
-4 -1 0 3 10

Sample Output-1: 
----------------
[0, 1, 9, 16, 100]

 */
import java.util.*;
public class SquaresOfSortedArr {

    // Approach:i) T.C:- O(n) , S.C:- O(n)
    private static List<Integer> getSquaresOfSortedArr(int[] arr,int n){

        List<Integer> dq = new ArrayList<>();

        int i = 0 , j = n - 1;

        while(i <= j){
            if(Math.abs(arr[i]) > arr[j]){
                dq.addFirst(arr[i] * arr[i]);
                i++;
            }else{
                dq.addFirst(arr[j] * arr[j]);
                j--;
            }
            
        }

        return dq;
    }

    private static int getFirstPosIndex(int[] arr,int n){
        int low = 0 , high = n - 1;
        int res = 0;
        while(low <= high){
            int mid = (low + high) / 2;

            if(arr[mid] >= 0){
                res = mid;
                // go left
                high = mid - 1;
            }
            else{
                low = mid + 1;
            }
        }

        return res;
    }

    private static void reverse(int[] arr,int i,int j){
        while(i <= j){
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }
    }

    private static List<Integer> mergeArrays(int[] arr,int l,int mid,int r){
        int i = l , j = mid + 1;
        List<Integer> ans = new ArrayList<>();
        while(i <= mid && j <= r){
            
            if(Math.abs(arr[i]) < arr[j]){
                ans.add(arr[i] * arr[i]);
                i++;
            }else{
                ans.add(arr[j] * arr[j]);
                j++;
            }
        }

        while (i <= mid) {
            ans.add(arr[i] * arr[i]);
            i++;
        }
        while (j <= r) {
            ans.add(arr[j] * arr[j]);
            j++;
        }

        return ans;
    }
    
    // Approach:-ii) T.C:- O(n + logn) , S.C:- O(N)
    private static List<Integer> getSquaresOfSortedArr2(int[] arr,int n){
        // find the index of first positive number in array
        int pos = getFirstPosIndex(arr,n); // logn

        // now reverse the array from 0 to pos - 1
        reverse(arr,0,pos-1); // O(n)

        // now there are two arrays from 0 to pos - 1 and pos to n - 1
        return mergeArrays(arr,0,pos-1,n-1); // O(n)

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();

        int[] arr = new int[n];

        for(int i = 0 ; i < n ; i++){
            arr[i] = sc.nextInt();
        }

        System.out.println(getSquaresOfSortedArr2(arr,n));

        
        sc.close();
    }
}
