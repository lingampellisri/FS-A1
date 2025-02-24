/*
 There is a bookstore owner that has a store open for n minutes. Every minute, some number of customers enter the store.
You are given an integer array customers of length n where customers[i] is the number of the customer
that enters the store at the start of the ith minute and all those customers leave after the end of that minute.

On some minutes, the bookstore owner is grumpy. You are given a binary array grumpy where grumpy[i] is 1
if the bookstore owner is grumpy during the ith minute, and is 0 otherwise.

When the bookstore owner is grumpy, the customers of that minute are not satisfied, 
otherwise, they are satisfied.

The bookstore owner knows a secret technique to keep themselves not grumpy for minutes consecutive minutes, 
but can only use it once.

Return the maximum number of customers that can be satisfied throughout the day.
 
Sample Input-1:
---------------
8
1 0 1 2 1 1 7 5
0 1 0 1 0 1 0 1
3

Sample Output-1: 
----------------
16

Explanation:
------------
The bookstore owner keeps themselves not grumpy for the last 3 minutes. 
The maximum number of customers that can be satisfied = 1 + 1 + 1 + 1 + 7 + 5 = 16.

Sample Input-2:
---------------
1
1
0
1

Sample Output-2:
----------------
1
 

Constraints:

n == customers.length == grumpy.length
1 <= minutes <= n <= 2 * 104
0 <= customers[i] <= 1000
grumpy[i] is either 0 or 1.
 */
import java.util.Scanner;
public class BookStore {

    // T.C: O(N^2) , S.C: O(1)
    private static int getMaxCustomerSatisfactionCount(int[] customers,int[] grumpy,int n,int k){

        // first cacl inital number of customers
        int curr_custs = 0;
        for(int i = 0 ; i < n ; i++){
            if(grumpy[i] == 0){
                curr_custs += customers[i];
            }
        }

        int maxCustomers = curr_custs;

        int sum = curr_custs;

        int i = 0 , j = 0;
        while(i < n && j < n){
            if(j < i + k){
                // if grumpy change to not grumpy and add value
                if(grumpy[j] == 1){
                    sum += customers[j];
                }
                j++;
            }else{
                maxCustomers = Math.max(maxCustomers,sum);
                i++;
                j = i;
                sum = curr_custs;
            }
            
            
        }
        maxCustomers = Math.max(maxCustomers,sum);
        return maxCustomers;

    }
    // T.C:- O(N) , S.C:- O(1)
    private static int getMaxCustomerSatisfactionCount2(int[] customers,int[] grumpy,int n,int k){

        // first cacl inital number of customers
        int curr_custs = 0;
        for(int i = 0 ; i < n ; i++){
            if(grumpy[i] == 0){
                curr_custs += customers[i];
            }
        }

        
        int sum = curr_custs;
        int j = 0;
        int maxCustomers = curr_custs;
        for( ; j < k ; j++){
            sum += (grumpy[j] == 1) ? customers[j] : 0;
        }

        int i = 0;
        while(j < n ){

            maxCustomers = Math.max(maxCustomers,sum);
            // remove previous window contribution
            sum -= (grumpy[i] == 1) ? customers[i] : 0;
            sum += (grumpy[j] == 1) ? customers[j] : 0;
            i++;
            j++;

            
        }
        maxCustomers = Math.max(maxCustomers,sum);

        return maxCustomers;

    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        int[] customers = new int[n]; // each customers[i] represents number of customers entered at start of ith minute
        for(int i = 0 ; i < n ; i++){
            customers[i] = sc.nextInt();   
        }

        int[] grumpy = new int[n];
        for(int i = 0 ; i < n ; i++){
            grumpy[i] = sc.nextInt();
        }

        int k = sc.nextInt(); // consective minutes for which the book store owner will not be grumpy , but this can be done only once

        System.out.println(getMaxCustomerSatisfactionCount2(customers,grumpy,n,k));
        
        sc.close();
    }
}
