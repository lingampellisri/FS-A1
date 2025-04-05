package Treaps;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Treap treap = new Treap();

        System.out.print("Enter number of elements: ");
        int n = sc.nextInt();

        System.out.println("Enter elements:");
        for (int i = 0; i < n; i++) {
            treap.insert(sc.nextInt());
        }

        treap.printTree();

        System.out.print("Enter key to search: ");
        int key = sc.nextInt();
        TreapNode result = treap.search(key);
        if (result != null)
            System.out.println("Found key " + result.key + " with priority " + result.priority);
        else
            System.out.println("Key not found!");

        while (true) {
            System.out.print("Enter key to delete (-1 to stop): ");
            key = sc.nextInt();
            if (key == -1 || treap.isEmpty()) break;
            treap.delete(key);
            treap.printTree();
        }

        System.out.println("Treap is now empty or deletion stopped.");
    }
}
