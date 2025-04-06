package Treaps;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BST treap = new Treap();

        System.out.print("🔢 Enter number of elements: ");
        int n = sc.nextInt();

        System.out.println("📥 Enter elements to insert:");
        for (int i = 0; i < n; i++) {
            treap.insert(sc.nextInt());
        }

        System.out.print("🔍 Enter key to search: ");
        int key = sc.nextInt();
        TreapNode found = treap.search(key);
        if (found != null)
            System.out.println("✅ Found key: " + key);
        else
            System.out.println("❌ Key not found!");

        System.out.print("🏅 Enter k to get the k-th largest element: ");
        int k = sc.nextInt();
        try {
            int resultK = ((Treap) treap).getKthLargestElement(k);
            System.out.println("🎯 " + k + "-th largest element is: " + resultK);
        } catch (IllegalArgumentException e) {
            System.out.println("❗ " + e.getMessage());
        }

        ((Treap) treap).printInorder();
        sc.close();
    }
}
