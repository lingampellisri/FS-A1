/* Leetcode-347 Top K Frequent Elements (Quick Select algo)
You are given an integer array containing numbers, and an integer k. 
Your task is to return the k most frequent elements in the array.

If multiple elements have the same frequency, the element with the higher value should be prioritized.
The output should be printed in descending order of frequency.

Input Format:
-------------
Line-1: An integer N, representing the number of elements in the array.
Line-2: A line with N space-separated integers representing the elements of the array.
Line-3: An integer k, representing the number of most frequent elements to return.

Output Format:
--------------
Line-1: An array, comma-separated integers in descending order of frequency. 
If two elements have the same frequency, the higher number should appear first.


Sample Input-1:
--------------
6
1 1 1 2 2 3
2

Sample Output-1:
----------------
[1, 2]


Sample Input-2:
--------------
1
1
1

Sample Output-2:
----------------
[1]
 */

import java.util.*;
class Pair {
    int freq;
    int num;
    public Pair(int freq,int num){
        this.freq = freq;
        this.num = num;
    }

    @Override
    public String toString(){
        return "(" + freq + "," + num + ")";
    }
}
public class TopKfrequent {
    
    // T.C:- O(n + nlogn + k) , S.C:- O(2*n)
    private static int[] topKFrequent(final int[] nums, int k) {
        // find the frequency of each element 
        HashMap<Integer,Integer> freq = new HashMap<>();

        for(int num : nums){
            freq.put(num,freq.getOrDefault(num,0)+1);
        }

        List<Pair> lst = new ArrayList<>(); // to store sorted freq,num as a Pair and sort in their descending order

        freq.forEach((ele,f)->{
            lst.add(new Pair(f,ele));
        });

        // Now sort the list according to the freq
        lst.sort((a,b)->{
            if(a.freq == b.freq){
                return b.num - a.num; // sorts according to higher number if freq is same
            }

            return b.freq - a.freq;
        });

        int[] res = new int[k];
        for(int i = 0 ; i < k ; i++){
            res[i] = lst.get(i).num;
        }
        
        return res;

    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int n = sc.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
            }
            
            int k = sc.nextInt();
            
            System.out.println(Arrays.toString(topKFrequent(arr,k)));
        }
    }
}

