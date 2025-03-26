/*
There are n football players standing in the ground, coach wants to know the 
P-th largest height of the players. Given an array of heights[] and the value of P. 
Help the coach to find the P'th largest height.

Note: You are supposed to print the P'th largest height in the sorted order of heights[].
      Not the P'th distinct height.

Input Format:
-------------
Line-1: Size of array n and P value(space separated)
Line-2: Array elements of size n.

Output Format:
--------------
Print P'th largest height.

Sample input-1:
---------------
8 2
1 2 1 3 4 5 5 5

Sample output-1:
----------------
5

Sample input-2:
---------------
6 3
2 4 3 1 2 5

Sample output-2:
----------------
3
 */
import java.util.*;
class Solution {
    private static int ans;

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    private static void heapify(int[] arr, int n, int i) {
        // T.C:- log(n)

        int curr = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && arr[left] >= arr[curr]) {
            curr = left;
        }

        if (right < n && arr[right] >= arr[curr]) {
            curr = right;
        }

        if (curr != i) {
            swap(arr, curr, i);
            // now node i has got placed at correct position now it's time for placing curr
            heapify(arr, n, curr);
        }
    }

    private static void heapify(int[] arr) {
        int n = arr.length;
        // heapify is used to build a maxHeap from this array
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i); // place this node at's it's correct place
        }
    }

    private static void heapSort(int[] arr, int n, int k) {
        // T.C:- O(nlogn)
        // if we sort a maxHeap we get result in ascending order
        // convert array to max heap first
        heapify(arr);

        int heapSize = n;
        int cnt = 0;
        for (int i = n - 1; i >= 0; i--) {
            // first swap first and last
            cnt++;
            swap(arr, 0, i);
            if (cnt == k) {
                ans = arr[i]; // once reached k stop
                return;
            }
            heapSize--;
            heapify(arr, heapSize, 0); // alaway heapify the root node
        }

    }

    private static int findKthLargest(int[] nums, int k) {
        // may be heap sort could help us
        ans = 0;
        int n = nums.length;
        heapSort(nums, n, k);
        return ans;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int p = sc.nextInt();

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.println(findKthLargest(arr,p));
        sc.close();

    }
}

/*
 * import java.util.*;
 * class TreapNode
 * {
 * int data;
 * int priority;
 * TreapNode left;
 * TreapNode right;
 * TreapNode(int data)
 * {
 * this.data = data;
 * this.priority = new Random().nextInt(1000);
 * this.left = this.right = null;
 * }
 * }
 * 
 * class KthLargest
 * {
 * static int k;
 * public static TreapNode rotateLeft(TreapNode root)
 * {
 * 
 * }
 * 
 * public static TreapNode rotateRight(TreapNode root)
 * {
 * 
 * }
 * 
 * public static TreapNode insertNode(TreapNode root, int data){
 * 
 * }
 * 
 * static void inorder(TreapNode root)
 * {
 * 
 * }
 * 
 * // static void printTreap(TreapNode root)
 * // {
 * // if (root == null)
 * // return;
 * // printTreap(root.left);
 * // System.out.println(root.data + " " + root.priority);
 * // printTreap(root.right);
 * // }
 * public static void main(String[] args)
 * {
 * Scanner sc = new Scanner(System.in);
 * int n = sc.nextInt();
 * int p = sc.nextInt();
 * k=n-p+1;
 * int arr[] = new int[n];
 * for(int i=0;i<n;i++){
 * arr[i] = sc.nextInt();
 * }
 * TreapNode root = null;
 * for(int a:arr){
 * root = insertNode(root,a);
 * }
 * // printTreap(root);
 * inorder(root);
 * }
 * }
 */