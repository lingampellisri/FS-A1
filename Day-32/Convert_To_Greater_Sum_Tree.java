/*
Imagine you're the chief curator at a renowned museum that houses a rare collection 
of ancient artifacts. These artifacts are arranged in a special display that 
follows a strict rule: any artifact positioned to the left of another has a lower 
value, and any artifact on the right has a higher value. 

Your task is to transform this display into what is known as a "Greater Artifact 
Display." In this new arrangement, each artifact’s new value will be its original 
value plus the sum of the values of all artifacts that are more valuable than it.

Sample Input-1:
---------------
4 2 6 1 3 5 7

Sample Output-1:
----------------
22 27 13 28 25 18 7

Explanation:
-------------
Input structure 
       4
      / \
     2   6
    / \ / \
   1  3 5  7

Output structure:
        22
      /   \
     27   13
    / \   / \
   28 25 18  7

Reverse updates:
- Artifact 7: 7
- Artifact 6: 6 + 7 = 13
- Artifact 5: 5 + 13 = 18
- Artifact 4: 4 + 18 = 22
- Artifact 3: 3 + 22 = 25
- Artifact 2: 2 + 25 = 27
- Artifact 1: 1 + 27 = 28
 */

import java.util.*;

class Node {
    int val;
    Node left;
    Node right;

    public Node(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}

class BST {
    private Node root;

    public BST() {
        this.root = null;
    }

    public void buildTree(int[] arr) {
        for (int key : arr) {
            if (key == -1)
                continue;
            root = insertIntoBST(root, key);
        }
    }

    private Node insertIntoBST(Node root, int key) {
        if (root == null) {
            return new Node(key);
        }

        if (key < root.val) {
            root.left = insertIntoBST(root.left, key);
        } else {
            root.right = insertIntoBST(root.right, key);
        }

        return root;
    }

    // Converts BST To Greater Sum Tree
    private Node helper(Node root, int[] sum) {
        if (root == null)
            return null;

        // Traverse right subtree first
        helper(root.right, sum);

        // Update current node
        sum[0] += root.val;
        root.val = sum[0];

        // Traverse left subtree
        helper(root.left, sum);

        return root;
    }

    public void convert_BST_To_GST() {
        helper(root, new int[] { 0 });
    }

    public void printLevelOrder() {
        if (root == null)
            return;

        Queue<Node> qu = new LinkedList<>();
        qu.offer(root);

        while (!qu.isEmpty()) {
            Node node = qu.poll();
            System.out.print(node.val + " ");

            if (node.left != null)
                qu.offer(node.left);
            if (node.right != null)
                qu.offer(node.right);
        }
    }
}

public class Convert_To_Greater_Sum_Tree {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] s = sc.nextLine().split(" ");
        int[] arr = new int[s.length];

        for (int i = 0; i < s.length; i++) {
            arr[i] = Integer.parseInt(s[i]);
        }

        BST bst = new BST();
        bst.buildTree(arr); // Build the BST
        bst.convert_BST_To_GST(); // Convert it to Greater Sum Tree
        bst.printLevelOrder(); // Print the result in level-order

        sc.close();
    }
}
