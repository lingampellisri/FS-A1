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
interface Heap{
    void insert(int val); // insert into Heap
    int poll(); // delete the top element either max element in max heap or min element in min heap
    void display();
    
}

class MaxHeap implements Heap{
    private int[] heap;
    int size;

    public MaxHeap(int maxSize){
        this.size = 0;
        heap = new int[maxSize+1];
    }

    private void swap(int i, int j){
        if (i == j) return;
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }
    

    @Override
    public void insert(int val){
        size++;
        heap[size] = val;

        // now move this node to it's correct place
        int i = size;
        while(i > 1){
            int parent = i / 2;
            if(heap[i] > heap[parent]){
                swap(i,parent);
                i = parent;
            }else{
                return;
            }
        }
    }

    @Override
    public int poll(){
        // replace the first element with last element
        int maxEle = heap[1];
        heap[1] = heap[size];

        // decrement the size
        size--;

        // Now move root node to it's correct place
        int i = 1;
        while(i < size){
            int left = 2 * i;
            int right = 2 * i + 1;

            if(left <= size && heap[left] > heap[i]){
                swap(i,left);
                i = left;
            }else if(right <= size && heap[right] > heap[i]){
                swap(i,right);
                i = right;
            }else{
                return maxEle;
            }
        }

        return maxEle;
    }

    @Override
    public void display(){
        for(int i = 1 ; i <= size ; i++){
            System.out.print(heap[size] + " ");
        }
    }

    public int KthLargestElement(int k){
        // pop element k times 
        int cnt = 0;
        while(cnt < k - 1){
            poll();
            cnt++;

        }

        return poll();

    }
}
public class Kth_Largest_Value {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        int p = sc.nextInt();
        MaxHeap maxheap = new MaxHeap(n);

        for(int i = 0 ; i < n ; i++){
            maxheap.insert(sc.nextInt());
        }

        System.out.println(maxheap.KthLargestElement(p));
        sc.close();

    }
}

/*
import java.util.*;
class TreapNode
{
    int data;
    int priority;
    TreapNode left;
    TreapNode right;
    TreapNode(int data)
    {
        this.data = data;
        this.priority = new Random().nextInt(1000);
        this.left = this.right = null;
    }
}

class KthLargest
{
    static int k;
    public static TreapNode rotateLeft(TreapNode root)
    {
       
    }

    public static TreapNode rotateRight(TreapNode root)
    {
        
    }

    public static TreapNode insertNode(TreapNode root, int data){
        
    }

    static void inorder(TreapNode root)
    {
        
    }

// 	static void printTreap(TreapNode root)
//     {
//         if (root == null)
//             return;
//         printTreap(root.left);
//         System.out.println(root.data + " " + root.priority);
//         printTreap(root.right);
//     }
    public static void main(String[] args)
	{
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int p = sc.nextInt();
        k=n-p+1;
        int arr[] = new int[n];
        for(int i=0;i<n;i++){
            arr[i] = sc.nextInt();
        }
        TreapNode root = null;
        for(int a:arr){
            root = insertNode(root,a);
        }
// 		printTreap(root);
        inorder(root);
    }
}
 */