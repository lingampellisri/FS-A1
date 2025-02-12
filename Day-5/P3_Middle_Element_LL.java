/*
You are given a singly linked list containing N nodes. 
Your task is to find the middle element of the linked list.

Input Format:
-------------
Line 1: An integer N, representing the number of nodes in the linked list.
Line 2: N space-separated integers representing the elements of the linked list.

Output Format:
--------------
Line-1: Print a single integer, the middle element of the linked list.

Sample Input-1:
---------------
5
1 2 3 4 5

Sample Output-1:
----------------
3


Sample Input-2:
---------------
6
1 2 3 4 5 6

Sample Output-2:
----------------
4

 */
import java.util.Scanner;
class Node{
    int data;
    Node next; // reference to next address in the linked list

    public Node(int data){
        this.data = data;
        next = null;
    }
}

class LinkedList{
    private Node head;
    private static int size;
    public LinkedList(int n){
        head = null;
        size = n;
    }
    // implement a linked list
    public void addElement(int data){
        if(head == null){
            head = new Node(data);
            return;
        }

        // now start traversing till you get null
        Node temp = head;

        while(temp.next != null){
            temp = temp.next;
        }

        temp.next = new Node(data);
    }

    public void printMiddleElement(){
        Node slow = head;
        Node fast = head;
    
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
    
        System.out.println(slow.data);
    }
    
}
public class P3_Middle_Element_LL {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        
        // Create a Linked List object
        LinkedList ll = new LinkedList(n);
        for(int i = 0 ; i < n ; i++){
            ll.addElement(sc.nextInt());
        }

        ll.printMiddleElement();

        
        sc.close();
    }
}
