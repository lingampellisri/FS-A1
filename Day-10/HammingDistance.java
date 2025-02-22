/*
Mr.Sathya is playing with numbers he converts the decimal to binary 
and try to find number of positions at which the corresponding
bits are different. 
Help sathya by giving solution to the problem 
to find sum of bits differences between all the pairs of the 
integers in given numbers.

Input Format:
--------------
Line-1: N, Number of integers
Line-2: N space separated list of integers

Output Format:
---------------
Line-1: A integer, Return the sum of bit differences between all the pairs of the integers.
 
Sample Input-1:
---------------
3
4 14 2

Sample Output-1: 
----------------
6

Explanation: 
------------
In binary representation, the 4 is 0100, 14 is 1110, and 2 is 0010 (just showing the four bits relevant in this case).
The answer will be:
HammingDistance(4, 14) + HammingDistance(4, 2) + HammingDistance(14, 2) = 2 + 2 + 2 = 6.

Sample Input-2: 
---------------- 
3
4 14 4

Sample Output-2: 
----------------
4
 */
import java.util.Scanner;
public class HammingDistance {
    // Approach:-i) Brute force , take xor of each pair and count set bits , T.C:- O(N^2) , S.C:- O(1)
    private static int countSetBits(int n){
        int cnt = 0;
        while(n != 0){
           
            cnt += (n & 1);
            n = n >> 1;
        }

        return cnt;
    }
    private static int getHammingSum(int[] arr,int n){

        int hamSum = 0;

        for(int i = 0 ; i < n ; i++){
            for(int j = i + 1 ; j < n ; j++){
                hamSum += countSetBits(arr[i] ^ arr[j]);
            }
        }

        return hamSum;
    }
    // Approach-ii) Optimized 
    private static int totalHammingDistance(int[] nums,int n) {
        /*  T.C:- O(32 * N) , S.C:- O(1)
            the main intution is for a particular bit position 
            -> If the bit is 0 -> check for how many numbers have 1 at this pos
            -> If the bit is 1 -> check for how many numbers have 0 at this bit pos
            total contribution = ones_contri + zero_contri
            => number of zeros +  number of ones
            => noOfZeros * noOfOnes
        */

        int totalHamSum = 0;
        for(int i = 0 ; i < 32 ; i++){
            int onesCnt = 0;

            for(int j = 0 ; j < n ; j++){
                onesCnt += (nums[j] & 1);
                nums[j] = nums[j] >> 1;
            }

            int zerosCnt = n - onesCnt;

            totalHamSum += onesCnt * zerosCnt;

        }

        return totalHamSum;

    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        int[] arr = new int[n];
        
        for(int i = 0 ; i < n ; i++){
            arr[i] = sc.nextInt(); 
        }

        // System.out.println(getHammingSum(arr,n)); // apprach-i
        System.out.println(totalHammingDistance(arr,n));
        
        sc.close();
    }
}
      

        
