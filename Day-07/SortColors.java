/*
You are given an array nums of size n, where each element represents an object colored red, white, or blue. 
Your task is to sort the array in-place so that all objects of the same color are adjacent, following the order:
-> Red (0)
-> White (1)
-> Blue (2)

Rules:
-> You must not use the built-in sort function.
-> You must modify the array in-place with a time-efficient approach.

Input Format:
-------------
Line 1: An integer n, representing the size of the array.
Line 2: n space-separated integers representing the array nums, where each element is either 0 (red), 1 (white), or 2 (blue).

Output Format:
--------------
Line-1: Print the sorted array as n space-separated integers.

Constraints:
------------
-> 1≤n≤300
-> nums[i]∈{0,1,2} (each element is either 0, 1, or 2)

Sample Input-1:
---------------
6
2 0 2 1 1 0

Sample Output-1:
----------------
0 0 1 1 2 2

Sample Input-2:
---------------
3
2 0 1

Sample Output-2:
----------------
0 1 2
 */
import java.util.Scanner;
import java.util.Arrays;
public class SortColors {
    // Main aim is to move all 2s to right side and all 0s to left side
    private static void swap(int[] arr,int i,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    private static void sortInPlaceUsingDNF(int[] colors,int n){

        int low = 0 , high = n - 1;
        int mid = 0;

        // just place all 0s at starting and all 2s at ending , automatically 1st get's placed in middle

        while(mid <= high){

            if(colors[mid] == 0){
                // push this 0 to the left
                swap(colors,low,mid);
                low++;
                mid++;
            }else if(colors[mid] == 1){
                // simply extend the 1 window size
                mid++;
            }else{
                // it means we found 2 , move it to the right 
                swap(colors,mid,high);
                high--;
            }
            
        }        

    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();

        int[] colors = new int[n];

        for(int i = 0 ; i < n ; i++){
            colors[i] = sc.nextInt();
        }

        sortInPlaceUsingDNF(colors,colors.length);
        System.out.println(Arrays.toString(colors));
        sc.close();
    }
}
