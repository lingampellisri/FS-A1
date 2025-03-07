
/*
Sample Input and output for my segment Tree
5
4 6 1 7 12

Output:-
4 6 10 1 11 7 12 19 30 
Range sum from 0 to 2 11

 Before update min element in (0,2) is : 1

 Before update max element in (0,2) is : 6
After Update : Range sum from 0 to 2 18

 After update min element in (0,2) is : 4

 Before update max element in (0,2) is : 8
4 6 10 8 18 7 12 19 37 
 */
import java.util.*;

class SegmentTreeNode {
    SegmentTreeNode left;
    SegmentTreeNode right;
    int l;
    int r;
    int sum;
    int min;
    int max;

    public SegmentTreeNode(int l, int r) {
        this.l = l;
        this.r = r;
        left = right = null;
        sum = 0;
        min = Integer.MAX_VALUE;
        max = Integer.MIN_VALUE;
    }

}

class SegmentTree {
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

        // Case-iii) Overlapping ranges , get answer from left and right , add them and return
        // int mid = (node.l + node.r)/2; // no need of this because the node itself
        // have the l,r
        return sumRangeHelper(node.left, start, end) + sumRangeHelper(node.right, start, end);
    }

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

public class PractiseSegmentTree {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        SegmentTree segTree = new SegmentTree(arr);

        segTree.printSegmentTree();

        System.out.println("\nRange sum from 0 to 2 " + segTree.sumRange(0, 2));
        System.out.println("\n Before update min element in (0,2) is : " + segTree.getMinInRange(0, 2));
        System.out.println("\n Before update max element in (0,2) is : " + segTree.getMaxInRange(0, 2));

        segTree.update(2, 8);
        System.out.println("After Update : Range sum from 0 to 2 " + segTree.sumRange(0, 2));
        System.out.println("\n After update min element in (0,2) is : " + segTree.getMinInRange(0, 2));
        System.out.println("\n Before update max element in (0,2) is : " + segTree.getMaxInRange(0, 2));

        segTree.printSegmentTree();

        sc.close();
    }
}
