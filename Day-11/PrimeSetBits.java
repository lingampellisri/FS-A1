/*
Given two integers left and right, return the count of numbers in the inclusive range [left, right]
 having a prime number of set bits in their binary representation.

Recall that the number of set bits an integer has the number of 1's present when written in binary.

For example, 21 written in binary is 10101, which has 3 set bits.
 
Input Format:
-------------
Line-1: Two separated integers

Output Format:
--------------
Line-1: An integer

Sample Input-1:
---------------
6  10

Sample Output-1:
---------------
4

Explanation:
------------
6  -> 110 (2 set bits, 2 is prime)
7  -> 111 (3 set bits, 3 is prime)
8  -> 1000 (1 set bit, 1 is not prime)
9  -> 1001 (2 set bits, 2 is prime)
10 -> 1010 (2 set bits, 2 is prime)
4 numbers have a prime number of set bits.

Sample Input-2:
---------------
10 15

Sample Output-2:
---------------
5

Explanation:
------------
10 -> 1010 (2 set bits, 2 is prime)
11 -> 1011 (3 set bits, 3 is prime)
12 -> 1100 (2 set bits, 2 is prime)
13 -> 1101 (3 set bits, 3 is prime)
14 -> 1110 (3 set bits, 3 is prime)
15 -> 1111 (4 set bits, 4 is not prime)
5 numbers have a prime number of set bits.

 */
import java.util.*;
public class PrimeSetBits {
    // private static boolean isPrime(int n){
    //     if(n < 2) return false;
    //     if (n == 2) return true;  // 2 is the smallest prime number
    //     for(int i = 2 ; i <= Math.pow(n,0.5) ; i++){
    //         if(n % i == 0) return false;
    //     } 

    //     return true;
    // }
    private static int countSetBits(int num){
        // T.C:- O(log(n))
        int cnt = 0;
        while(num != 0){
            cnt += (num & 1);
            num = num >> 1;
        }

        return cnt;
    }

    private static int countSetBitsUsingBrians(int n) {
        // I can use Brian Kernighan's Algorithm to find number of set bits in O(k) where k is number of set bits   
        int cnt = 0;
        while(n != 0){
            n = (n & (n-1)); // this clears the rightmost set bits and now we can increment our count
            cnt++;
        }

        return cnt;
    }
    private static int countPrimeSetBits(int left,int right){
        // Since the constraints are small till 10^6 we can create a hashmap , prime bits can go till 19 max
        HashSet<Integer> primes = new HashSet<>(Arrays.asList(2,3,5,7,11,13,17,19));
        int cnt = 0;
        for(int i = left ; i <= right ; i++){
            if(primes.contains(countSetBitsUsingBrians(i))){
                cnt++;
            }
        }

        return cnt;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int left = sc.nextInt();
        int right = sc.nextInt();
        
        System.out.println(countPrimeSetBits(left,right));
        
        sc.close();
    }
}
