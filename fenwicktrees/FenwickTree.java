package fenwicktrees;

public class FenwickTree {
    // create instance variables 
    private int n; // this is size of BIT Array
    private int[] arr; // this is original array
    private int[] BIT; // this is resultant BIT array

    public FenwickTree(int[] nums){
        arr = nums;
        n = nums.length;
        BIT = new int[n+1]; // since this is 1 based indexing
    }
    public void update(int id,int val){

        // this is where you are building the entire fenwick tree
        // for each val possible in array you are checking where all can this contribute i mean to what all indices it can contribute

        while(id <= n){
            BIT[id] += val; // add this val , till where ever it can go in powers of two
            id += (id & -id);
        }
    }

    public int rangeSum(int id){ // it gives the range sum from 1 to id

        int sum = 0;

        while(id >= 0){
            sum += BIT[id]; // go and get the value from that index in BIT array
            id -= (id & -id);
        }

        return sum;
    }

}
