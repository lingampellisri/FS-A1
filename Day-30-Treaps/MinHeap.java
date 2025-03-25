/*
Design and implement a Min Heap data structure using an array in Java. 
In a Min Heap, every parent node is less than or equal to its child nodes, 
ensuring that the smallest element is always at the root.

Sample Input:
-------------
5
1 2 3 4 5

Sample Output:
--------------
The min value is 1
 */
import java.util.Scanner;

interface Heap{
    void insert(int val);
    void delete();
    void print();
    int extractMin();
}
public class MinHeap implements Heap{
    private int[] heap;
    private int size;

    // Constructor to initialize an empty min heap
    public MinHeap(int maxsize) {
        this.size = 0;
        heap = new int[maxsize+1]; // 1 based indexing
    }

    // Swap nodes at positions i and j
    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }
    
    @Override
    public void insert(int val){ // T.C:- O(logn)
        // insert at last
        size++;
        heap[size] = val;
        
        // Now move this to it's correct place
        int i = size;
        while(i > 1){
            int parent = i / 2;
            if(heap[i] < heap[parent]){
                swap(i,parent);
                i = parent;
            }else{
                return;
            }
        }
    }
    
    @Override
    public void delete(){ // T.C:- O(logn)
        if(size == 0){
            return;
        }
        // first replace the first element with last element
        heap[1] = heap[size];
        
        // Now decrement the size
        size--;
        
        // Now move root element to it's correct place
        int i = 1;
        while(i < size){
            int left = 2 * i;
            int right = 2 * i + 1;
            
            if(left < size && heap[left] < heap[i]){
                swap(i,left);
                i = left;
            }else if(right < size && heap[right] < heap[i]){
                swap(i,right);
                i = right;
            }else{
                return;
            }
        }
    }
    
    @Override
    public void print(){
        for(int i = 1 ; i <= size ; i++){
            System.out.print(heap[i] + " ");
        }
    }
    

    // Remove and return the minimum element from the heap
    public int extractMin() {
        int minEle = heap[1];
        delete(); // delete from min Heap
        return minEle;
    }

    // Display the heap's structure (for each parent, show its children)
    // public void print() {
    //     for (int i = 0; i < size / 2; i++) {
    //         System.out.print("Parent: " + Heap[i]);
    //         if (leftChild(i) < size)
    //             System.out.print(" Left Child: " + Heap[leftChild(i)]);
    //         if (rightChild(i) < size)
    //             System.out.print(" Right Child: " + Heap[rightChild(i)]);
    //         System.out.println();
    //     }
    // }

    // Main method to demonstrate the Min Heap operations
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        Heap minHeap = new MinHeap(n);
        
        // Insert n elements into the heap
        for (int i = 0; i < n; i++) {
            minHeap.insert(scan.nextInt());
        }
        
        // Display the heap structure
        //minHeap.print();
        // minHeap.delete();
        // Extract and display the minimum element, then print the updated heap
        System.out.println("The min value is " + minHeap.extractMin());

    }
}
