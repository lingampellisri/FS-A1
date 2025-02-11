/*
You are given an array books, where each element represents the time (in minutes) required to read a book. 
You are also given an integer k, which represents the total available reading time in minutes.

Your task is to determine the maximum number of books that can be read within the given time k 
while ensuring that the sum of reading times does not exceed k.

You must find the longest contiguous subarray where the sum of elements is less than or equal to k, 
and return its length.

Input Format:
-------------
Line-1: An integer 'n' representing the number of books.
Line-2: 'n' space-separated integers representing the time (in minutes) required to read each book.
Line-3: An integer 'k', the available reading time in minutes.

Output Format:
--------------
Line-1: An integer representing the maximum number of books that can be read within 'k' minutes.

Sample Input-1:
---------------
8
1 1 2 1 1 1 4 2
8

Sample Output-1:
----------------
6

Explanation:
------------
We must find the longest subarray where the sum is ≤ 8.
Possible valid subarrays: [1, 1, 2, 1, 1, 1], [1, 2, 1, 1, 1], [2, 1, 1, 1], etc.
The longest valid subarray has a length of 6.

Sample Input-2:
---------------
7
2 3 1 2 4 3 1
5

Sample Output-2:
----------------
3

Explanation:
-------------
Possible valid subarrays: [2, 3], [3, 1, 2], [1, 2, 1], [2, 3], etc.
The longest valid subarray has a length of 3.



 */
import java.util.Scanner;
public class P1_Flexible_Sliding_Window {

    private static int maxBooksNaive(int[] books,int k,int n){

        int maxBooksRead = 0;

        
        for(int i = 0 ; i < n ; i++){   
            int timeTaken = 0;

            for(int j = i ; j < n ; j++){
                timeTaken += books[j];
                if(timeTaken > k) break;
                maxBooksRead = Math.max(maxBooksRead,j - i + 1);
             }   
        }

        return maxBooksRead;
        
    }
    // Approach-2 Two pointer
    private static int maxBooksTwoPointer(int[] books,int k,int n){
        // Flexible size sliding window is as same as two pointer approach
        int left = 0 , right = 0;
        
        int time = 0;
        int maxBooksRead = 0;
        while(right < n){
            if(time > k){
                time -= books[left];
                left++;
            }
            
            time += books[right];
            maxBooksRead = Math.max(maxBooksRead,right - left);

            
            right++;
        }

        return maxBooksRead;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();

        int[] arr = new int[n];
        for(int i = 0 ; i < n ; i++){
            arr[i] = sc.nextInt();   
        }

        int k = sc.nextInt();
        
        System.out.println(maxBooksTwoPointer(arr, k, n));

        
        sc.close();
    }
}
