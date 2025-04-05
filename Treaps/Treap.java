package Treaps;

interface BST{
    
    void insert(int key);
    void delete(int key);
    TreapNode search(int key);
    void printTree();
    boolean isEmpty();
}

interface Heap{
    void insert(int key);
    void delete(int key);
    TreapNode search(int key);
}

public class Treap implements BST,Heap{ // A class can implement multiple interfaces
    private TreapNode root;

    public Treap() {
        root = null;
    }

    
    private TreapNode rightRotate(TreapNode y) {
        System.out.println("Right rotate at key: " + y.key);
        TreapNode x = y.left;
        y.left = x.right;
        x.right = y;
        return x;
    }

    
    private TreapNode leftRotate(TreapNode x) {
        System.out.println("Left rotate at key: " + x.key);
        TreapNode y = x.right;
        x.right = y.left;
        y.left = x;
        return y;
    }

    // Time: O(log N) avg, O(N) worst | Space: O(H)
    private TreapNode insert(TreapNode node, int key) {
        if (node == null) {
            TreapNode newNode = new TreapNode(key);
            System.out.println("Inserted key: " + newNode.key + " with priority: " + newNode.priority);
            return newNode;
        }

        if (key <= node.key) {
            node.left = insert(node.left, key);
            if (node.left != null && node.left.priority > node.priority) {
                node = rightRotate(node);
            }
        } else {
            node.right = insert(node.right, key);
            if (node.right != null && node.right.priority > node.priority) {
                node = leftRotate(node);
            }
        }
        return node;
    }

    @Override
    public void insert(int key) {
        root = insert(root, key);
    }

    // Time: O(log N) avg, O(N) worst | Space: O(H)
    private TreapNode delete(TreapNode node, int key) {
        if (node == null) return null;

        if (key < node.key) {
            node.left = delete(node.left, key);
        } else if (key > node.key) {
            node.right = delete(node.right, key);
        } else {
            System.out.println("Deleting key: " + key);
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;

            if (node.left.priority < node.right.priority) {
                node = leftRotate(node);
                node.left = delete(node.left, key);
            } else {
                node = rightRotate(node);
                node.right = delete(node.right, key);
            }
        }
        return node;
    }

    @Override
    public void delete(int key) {
        root = delete(root, key);
    }

    // Time: O(log N) avg, O(N) worst | Space: O(H)
    private TreapNode search(TreapNode node, int key) {
        if (node == null || node.key == key) return node;

        return key < node.key ? search(node.left, key) : search(node.right, key);
    }

    @Override
    public TreapNode search(int key) {
        return search(root, key);
    }

    // Time: O(N) | Space: O(H)
    private void preorder(TreapNode node) {
        if (node == null) return;
        System.out.println("Key: " + node.key + ", Priority: " + node.priority);
        preorder(node.left);
        preorder(node.right);
    }

    @Override
    public void printTree() {
        System.out.println("Treap (Preorder Traversal):");
        preorder(root);
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }
}
