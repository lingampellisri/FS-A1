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
import java.util.Arrays;
import java.util.Scanner;
public class PrimeSetBits {

    private static final int[] getPrimesArr(){
        // T.C:- O(N * log(logN))
        final int n = 31; // because a number have 32 bits out of which 31 is the maximum prime number 
        int[] primes = new int[n]; // so that right is also included 

        Arrays.fill(primes,1);
        primes[0] = primes[1] = 0;

        for(int i = 2 ; i * i <= n ; i++){
            if(primes[i] == 1){
                // if this is prime mark all its multiples as non primes 
                for(int j = 2 ; i * j <= n ; j++){
                    primes[i*j] = 0;
                }

            }
        }

        return primes;

    }

    private static final int countSetBitsUsingBrianAlgo(int n){
        // T.C:- O(k) , where k is number of set bits 
        int cnt = 0;
        while(n != 0){
            n = ((n & (n-1))); // this clears the last set bit of a number 
            cnt++;
        }

        return cnt;
    }

    private static final int primeSetBitsInRange(int left,int right){
        // T.C:- O(N * log(logN) + k * (right - left))
        // first get the primes array
        int[] primes = getPrimesArr(); // O(N * log(logN))
        int ans = 0;
        for(int i = left ; i <= right ; i++){ // O(right - left)
            int setBitsCnt = countSetBitsUsingBrianAlgo(i); // O(k)
            if(primes[setBitsCnt] == 1){
                ans++;
            }
        }

        return ans;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int left = sc.nextInt();
        int right = sc.nextInt();
        
        System.out.println(primeSetBitsInRange(left,right));
        sc.close();
    }
}
