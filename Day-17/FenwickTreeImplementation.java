/*
 Malika taught a new fun time program practice for Engineering Students.
As a part of this she has given set of N numbers, and asked the students 
to perform the operations listed below:
1. sumRange(start, end) - return the sum of numbers between the indices start and end, both are inclusive.
2. update(ind, val) - update the value at the index 'ind' to 'val'.

Your task is to solve this problem using Fenwick Tree concept.

Input Format:
-------------
Line-1: Two integers N and Q, size of the array(set of numbers) and query count.
Line-2: N space separated integers.
next Q lines: Three integers option, start/ind and end/val.

Output Format:
--------------
An integer result, for every sumRange query.


Sample Input-1:
---------------
8 5
1 2 13 4 25 16 17 8
1 2 6		//sumRange
1 0 7		//sumRange
2 2 18	//update
2 4 17	//update
1 2 7		//sumRange

Sample Output-1:
----------------
75
86
80



Sample Input-2:
---------------
8 5
1 2 13 4 25 16 17 8
1 2 6		
1 0 7		
2 2 18	
2 4 17	
1 0 7

Sample Output-2:
----------------
75
86
83
*/
import java.util.*;

class FenwickTree {
    private int[] nums;
    private int size;
    private int[] BIT;

    public FenwickTree(int[] nums){
        this.nums = nums;
        size = nums.length;
        BIT = new int[size+1]; // 1-based indexing
        init(); 
    }

    private void init(){
        for(int i = 0; i < size; i++){  // Populate Fenwick Tree
            update(i + 1, nums[i]);  
        }
    }

    public void update(int id, int val){
        while(id <= size){
            BIT[id] += val;
            id += (id & -id);
        }
    }

    public int sumQuery(int id){
        int sum = 0;
        while(id > 0){  // Corrected indexing
            sum += BIT[id];
            id -= (id & -id);
        }
        return sum;
    }

    public int getRangeSum(int a, int b){
        return sumQuery(b) - sumQuery(a - 1);  // Corrected to match 1-based indexing
    }
}

public class FenwickTreeImplementation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        int ops = sc.nextInt();

        int[] arr = new int[n + 1]; // 1-based indexing
        for(int i = 1; i <= n; i++){ // Start from index 1
            arr[i] = sc.nextInt();   
        }

        List<Integer> ans = new ArrayList<>();
        FenwickTree fnwkTree = new FenwickTree(arr);
        while(ops-- > 0){
            int op = sc.nextInt();

            if(op == 1){
                int l = sc.nextInt();
                int r = sc.nextInt();
                ans.add(fnwkTree.getRangeSum(l, r)); 
            } else if(op == 2){
                int id = sc.nextInt();
                int val = sc.nextInt();
                fnwkTree.update(id, -arr[id]); // Remove old value
                arr[id] = val; // Update array
                fnwkTree.update(id, val); // Add new value
            }
        }

        for(int rangeSum : ans){
            System.out.println(rangeSum);
        }
        
        sc.close();
    }
}
