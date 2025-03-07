// Method-2 to implement same segment tree without an array
import java.util.*;

// let's create a interface
interface SegmentTreeOperations{
    int sumRange(int start,int end);
    void update(int idx,int val);
}

class SegmentTreeNode {
    int l;
    int r;
    int sum;
    SegmentTreeNode left;
    SegmentTreeNode right;

    public SegmentTreeNode(int l, int r) {
        this.l = l;
        this.r = r;
        left = right = null;
        sum = 0;
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
            node.sum = nums[l];
            return node;
        }
        int mid = (l + r) / 2;
        node.left = buildTree(l, mid);
        node.right = buildTree(mid + 1, r);

        node.sum = node.left.sum + node.right.sum;

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
            node.sum = val;
            return;
        }

        int mid = (node.l + node.r) / 2;

        if (idx <= mid) {

            updateHelper(node.left, idx, val);
        } else {

            updateHelper(node.right, idx, val);
        }

        node.sum = node.left.sum + node.right.sum;
       
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

public class SegmentTreeTest2 {
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
                default:
                    System.out.println("Default executed");
                    break;

            }
        }

        sc.close();
    }
}