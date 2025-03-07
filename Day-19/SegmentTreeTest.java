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

// let's create a interface
interface SegmentTreeOperations{
    int sumRange(int start,int end);
    void update(int idx,int val);
    int getMinInRange(int start,int end);
    int getMaxInRange(int start,int end);

}
class SegmentTreeNode {
    int l;
    int r;
    int sum;
    int min;
    int max;
    SegmentTreeNode left;
    SegmentTreeNode right;

    public SegmentTreeNode(int l, int r) {
        this.l = l;
        this.r = r;
        left = right = null;
        sum = 0;
        min = Integer.MAX_VALUE;
        max = Integer.MIN_VALUE;
    }

}

class SegmentTree implements SegmentTreeOperations{
    int[] nums;
    int n;
    SegmentTreeNode root;

    public SegmentTree(int[] nums) {
        this.nums = nums;
        n = nums.length;
        root = new SegmentTreeNode(0, n - 1); // this contains the root information for the segment tree
        root = buildTree(0, n - 1); // (l,r)
    }

    private SegmentTreeNode buildTree(int l, int r) {

        SegmentTreeNode node = new SegmentTreeNode(l, r);

        if (l == r) {
            node.sum = node.min = node.max = nums[l];
            return node;
        }
        int mid = (l + r) / 2;
        node.left = buildTree(l, mid);
        node.right = buildTree(mid + 1, r);

        node.sum = node.left.sum + node.right.sum;
        node.min = Math.min(node.left.min, node.right.min);
        node.max = Math.max(node.left.max, node.right.max);

        return node;
    }

    @Override // better to annotations , because we may by mistake overload the method
    public int sumRange(int start, int end) {

        return sumRangeHelper(root, start, end);
    }

    private int sumRangeHelper(SegmentTreeNode node, int start, int end) { // node, actual start , actual end

        // Case-i) The current range is completely outside the required range(start,end)
        if (node.l > end || node.r < start) {
            return 0;
        }

        // Case-ii) The current range is completely inside the required range so return
        // this node contribution
        if (node.l >= start && node.r <= end) {
            return node.sum;
        }

        // Case-iii) Overlapping ranges , get answer from left and right , add them and
        // int mid = (node.l + node.r)/2; // no need of this because the node itself
        // have the l,r
        return sumRangeHelper(node.left, start, end) + sumRangeHelper(node.right, start, end);
    }

    
    @Override // better to annotations , because we may by mistake overload the method
    public void update(int idx, int val) {
        updateHelper(root, idx, val);
    }

    private void updateHelper(SegmentTreeNode node, int idx, int val) {
        if (node == null)
            return;

        if (node.l == node.r) {
            nums[idx] = val;
            node.sum = node.min = node.max = val;
            return;
        }

        int mid = (node.l + node.r) / 2;

        if (idx <= mid) {

            updateHelper(node.left, idx, val);
        } else {

            updateHelper(node.right, idx, val);
        }

        node.sum = node.left.sum + node.right.sum;
        node.min = Math.min(node.left.min, node.right.min);
        node.max = Math.max(node.left.max, node.right.max);
    }

    
    @Override // better to annotations , because we may by mistake overload the method
    public int getMinInRange(int start, int end) {
        return getMinRangeHelper(root, start, end);
    }

    private int getMinRangeHelper(SegmentTreeNode node, int start, int end) {
        // Case-i) if current node range is out of bound return a large number
        if (node.l > end || node.r < start) {
            return Integer.MAX_VALUE;
        }

        // Case-ii) If a exact match found
        if (node.l >= start && node.r <= end) {
            return node.min;
        }

        // Case-iii) Overlapping get answer from left, right and take minimum
        return Math.min(getMinRangeHelper(node.left, start, end), getMinRangeHelper(node.right, start, end));
    }
    
    @Override // better to annotations , because we may by mistake overload the method
    public int getMaxInRange(int start, int end) {
        return getMaxRangeHelper(root, start, end);
    }

    private int getMaxRangeHelper(SegmentTreeNode node, int start, int end) {
        // Case-i) if current node range is out of bound return a large number
        if (node.l > end || node.r < start) {
            return Integer.MIN_VALUE;
        }

        // Case-ii) If a exact match found
        if (node.l >= start && node.r <= end) {
            return node.max;
        }

        // Case-iii) Overlapping get answer from left, right and take minimum
        return Math.max(getMaxRangeHelper(node.left, start, end), getMaxRangeHelper(node.right, start, end));
    }

    public void printSegmentTree() {

        printTree(root);

    }

    private void printTree(SegmentTreeNode node) {
        if (node == null)
            return;

        printTree(node.left);
        printTree(node.right);

        System.out.print(node.sum + " ");
    }

}

public class SegmentTreeTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int q = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        SegmentTree segTree = new SegmentTree(arr);

        // System.out.print("Segment tree is : ");
        // segTree.printSegmentTree();
        // System.out.println();

        while (q-- > 0) {
            int choice = sc.nextInt();
            int a = sc.nextInt();
            int b = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println(segTree.sumRange(a, b));
                    break;
                case 2:
                    segTree.update(a, b); // update value at index a to b
                    break;
                case 3:
                    System.out.println(segTree.getMaxInRange(a, b));
                    break;
                case 4:
                    System.out.println(segTree.getMinInRange(a, b));
                    break;
                default:
                    System.out.println("Default executed");
                    break;

            }
        }

        sc.close();
    }
}