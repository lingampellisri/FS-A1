





/*
Malika taught a new fun time program practice for Engineering Students.
As a part of this she has given set of N numbers, and asked the students 
to perform the operations listed below:
1. sumRange(start, end) - return the sum of numbers between the indices start and end, both are inclusive.
2. update(ind, val) - update the value at the index 'ind' to 'val'.
3. Find the min value in the given range
4. Find the max value in the given range 

Your task is to solve this problem using Segment Tree concept.

Input Format:
-------------
Line-1: Two integers N and Q, size of the array(set of numbers) and query count.
Line-2: N space separated integers.
next Q lines: Three integers option, start/ind and end/val.

Output Format:
--------------
An integer result, for every sumRange query.


Sample Input:
-------------
8 7
4 2 13 3 25 16 17 8
1 2 6		//sumRange
1 0 7		//sumRange
3 2 6       //get max value in the range
2 2 18	    //update
2 4 17	    //update
1 2 6		//sumRange
4 1 5       // get min value in the range
 

Sample Output:
--------------
74
88
25
71
2
 */
import java.util.*;
class SegmentTreeNode{
    SegmentTreeNode left;
    SegmentTreeNode right;
    int sum;
    int min;

    
    public SegmentTreeNode(){
        left = right = null;
        sum = 0;
        min = Integer.MAX_VALUE;
    }

}

class SegmentTree{
    int[] nums;
    int n;
    public SegmentTree(int[] nums){
        this.nums = nums;
        n = nums.length;
        // root = buildTree(root,0,n-1); // (root,l,r)
    }

    public SegmentTreeNode buildTree(int l,int r){
        
        SegmentTreeNode root = new SegmentTreeNode();

        if(l == r){
            root.sum = nums[l];
            return root;
        }
        int mid = (l + r)/2;
        root.left = buildTree( l, mid);
        root.right = buildTree( mid+1, r);

        root.sum = root.left.sum + root.right.sum;

        return root;
    }

    public int sumRange(SegmentTreeNode node,int l,int r,int start,int end){ // 0 , n - 1 , actual start , actual end

        // Case-i) The current range is completely outside the required range(start,end)
        if(l > end || r < start){ 
            return 0;
        }

        // Case-ii) The current range is completely inside the required range so return this node contribution
        if(l >= start && r <= end){
            return node.sum;
        }

        // Case-iii) Overlapping ranges , get answer from left and right , add them and return
        int mid = (l + r)/2;
        return sumRange(node.left, l, mid, start, end) + sumRange(node.right, mid+1, r, start, end);
    }

    public void update(SegmentTreeNode node,int idx,int val,int l,int r){
        if(node == null) return;
        if(l == r ){
            nums[idx] = val;
            node.sum = val;
            return;
        }

        int mid = (l + r)/2;

        if(idx <= mid){

            update(node.left,idx,val,l,mid);
        }else{

            update(node.right,idx,val,mid+1,r);
        }

        node.sum = node.left.sum + node.right.sum;
    }

    public void printSegmentTree(SegmentTreeNode node){
        if(node == null) return ;

        printSegmentTree(node.left);
        printSegmentTree(node.right);

        System.out.print(node.sum + " ");
    }

    
        
}
    
public class SegmentTreeMinMax {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();

        int[] arr = new int[n];
        for(int i = 0 ; i < n ; i++){
            arr[i] = sc.nextInt();
        }
        SegmentTree segTree = new SegmentTree(arr);

        SegmentTreeNode root = segTree.buildTree( 0, n-1);
        segTree.printSegmentTree(root);

        System.out.println("\nRange sum from 0 to 2 " + segTree.sumRange(root, 0, n-1, 0, 2));
        segTree.update(root, 2, 1, 0, n-1);
        System.out.println("After Update : Range sum from 0 to 2 " + segTree.sumRange(root, 0, n-1, 0, 2));
        segTree.printSegmentTree(root);

        sc.close();
    }
}
/* This is correct code
`import java.util.*;

class SegmentTree 
{
    private SegmentTree left;
    private SegmentTree right;
    private int start;
    private int end;
    private int sum;
    private int maxValue;
    private int minValue;

    public SegmentTree(int[] nums, int start, int end) 
    {
        this.start = start;
        this.end = end;
        
        if (start == end) 
        {
            this.sum = nums[start];  
            this.maxValue = nums[start];
            this.minValue = nums[start];
        }
        
        else 
        {
            int mid = start + (end - start) / 2;
            this.left = new SegmentTree(nums, start, mid);
            this.right = new SegmentTree(nums, mid + 1, end);
            this.sum = left.sum + right.sum;
            this.maxValue = Math.max(left.maxValue, right.maxValue);
            this.minValue = Math.max(left.minValue, right.minValue);
        }
    }

    public void update(int index, int val) 
    {
        if (start == end) 
        {
            this.maxValue = Math.max(val, this.maxValue);
            this.minValue = Math.max(val, this.minValue);
            this.sum = val;
        }
        
        else 
        {
            int mid = start + (end - start) / 2;
            
            if (index <= mid) 
                left.update(index, val);
            else 
                right.update(index, val);
            
            this.sum = left.sum + right.sum;
            this.maxValue = Math.max(left.maxValue, right.maxValue);
            this.minValue = Math.max(left.minValue, right.minValue);
        }
    }

    public int sumRange(int start, int end) 
    {
        if (this.start == start && this.end == end) 
            return this.sum;
            
        int mid = this.start + (this.end - this.start) / 2;
        
        if (end <= mid) 
            return left.sumRange(start, end);
            
        if (start > mid) 
            return right.sumRange(start, end);
            
        
        return left.sumRange(start, mid) + right.sumRange(mid + 1, end);
    }
    
    public int getMax(int start, int end) 
    {
        if (this.start == start && this.end == end) 
            return this.maxValue;
            
        int mid = this.start + (this.end - this.start) / 2;
        
        if (end <= mid) 
            return left.getMax(start, end);
            
        if (start > mid) 
            return right.getMax(start, end);
            
        
        return Math.max(left.getMax(start, mid), right.getMax(mid + 1, end));
    }
    
    public int getMin(int start, int end) 
    {
        if (this.start == start && this.end == end) 
            return this.minValue;
            
        int mid = this.start + (this.end - this.start) / 2;
        
        if (end <= mid) 
            return left.getMin(start, end);
            
        if (start > mid) 
            return right.getMin(start, end);
            
        
        return Math.min(left.getMin(start, mid), right.getMin(mid + 1, end));
    }

}

public class SegmentTreeTest 
{
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int q = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) 
        {
            nums[i] = sc.nextInt();
        }
        
        SegmentTree root = new SegmentTree(nums, 0, n - 1);
        ArrayList<Integer> result = new ArrayList<> ();
        
        while (q-- > 0) 
        {
            int option = sc.nextInt();
            int a = sc.nextInt();
            int b = sc.nextInt();
            
            if (option == 1) 
                result.add(root.sumRange(a, b));
            else if (option == 2) 
                root.update(a, b);
            else if (option == 3) 
                result.add(root.getMax(a, b));
            else if (option == 4) 
                result.add(root.getMin(a, b));
        }
        
        for(int r: result)
        {
            System.out.println(r);  
        }
    }

}
 */