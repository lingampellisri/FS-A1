/*
Malika taught a new fun time program practice for Engineering Students.
As a part of this she has given set of N numbers, and asked the students 
to perform the operations listed below:
1. sumRange(start, end) - return the sum of numbers between the indices start and end, both are inclusive.
2. update(ind, val) - update the value at the index 'ind' to 'val'.

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
5 5
4 2 13 3 25
1 0 4		//sumRange
1 1 3		//sumRange
2 2 18	//update
2 4 17	//update
1 0 4		//sumRange

5 5
4 2 13 3 25
1 0 4
1 1 3
2 2 18	
2 4 17
1 0 4	

Sample Output:
--------------
47
18
44

 */
import java.util.*;

class SegmentTree {
    private int[] segTree;
    private int[] arr;

    public SegmentTree(int[] arr, int n) {
        this.arr = arr;
        boolean isPowerOfTwo = ((n & (n-1)) == 0);
        segTree = new int[isPowerOfTwo ? 2*n -1 : 4 * n]; // Safe size for segment tree
        buildTree(0, 0, n - 1);
    }

    private void buildTree(int i, int l, int r) {
        if (l == r) {
            segTree[i] = arr[l]; // Leaf node
            return;
        }

        int mid = l + (r - l) / 2;
        buildTree(2 * i + 1, l, mid);
        buildTree(2 * i + 2, mid + 1, r);

        segTree[i] = segTree[2 * i + 1] + segTree[2 * i + 2];
    }

    public void update(int index, int val, int i, int l, int r) {
        if (l == r) {
            arr[index] = val;  // Update the original array
            segTree[i] = val;   // Update the segment tree
            return;
        }

        int mid = l + (r - l) / 2;
        if (index <= mid) {
            update(index, val, 2 * i + 1, l, mid);
        } else {
            update(index, val, 2 * i + 2, mid + 1, r);
        }

        segTree[i] = segTree[2 * i + 1] + segTree[2 * i + 2];
    }

    public int sumQuery(int i, int l, int r, int start, int end) {
        if (l >= start && r <= end) { // Fully inside the range
            return segTree[i];
        }

        if (r < start || l > end) { // Completely outside the range
            return 0;
        }

        int mid = l + (r - l) / 2;
        return sumQuery(2 * i + 1, l, mid, start, end) +
               sumQuery(2 * i + 2, mid + 1, r, start, end);
    }
}

public class SegmentTreeTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        int ops = sc.nextInt();

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();   
        }

        SegmentTree segTree = new SegmentTree(arr, n);

        while (ops-- > 0) {
            int op = sc.nextInt();
            if (op == 1) {
                int l = sc.nextInt();
                int r = sc.nextInt();
                System.out.println(segTree.sumQuery(0, 0, n - 1, l, r));
            } else if (op == 2) {
                int id = sc.nextInt();
                int val = sc.nextInt();
                segTree.update(id, val, 0, 0, n - 1);
            }
        }
                
        sc.close();
    }
}
