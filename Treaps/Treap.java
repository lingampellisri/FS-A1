package Treaps;

interface BST{
    boolean isEmpty();
    void insert(int key);
    void delete(int key);
    boolean search(int key);
    void printInorder();
    int getKthLargestElement(int k);
}

interface Heap{
    void insert(int key);
    void delete(int key);
    boolean search(int key);
}
public class Treap implements BST,Heap{
    private TreapNode root;
    private TreapNode temp;
    public Treap(){
        this.root = null;
    }

    @Override
    public boolean isEmpty(){
        return root == null;
    }
    private TreapNode leftRotate(TreapNode x){
        System.out.println("Performing left rotation at :- " + x.key);
        TreapNode y = x.right;
        TreapNode T2 = y.left;

        // perform rotation
        y.left = x;
        x.right = T2;

        return y;
    }

    private TreapNode rightRotate(TreapNode y){
        System.out.println("Performing right rotation at :- " + y.key);

        TreapNode x = y.left;
        TreapNode T2 = x.right;

        // perform rotation
        x.right = y;
        y.left = T2;

        return x;
    }
    private TreapNode insert(TreapNode node,int key){
        if(node == null){
            TreapNode newNode = new TreapNode(key);
            System.out.println(" Inserted Node -> Key: " + newNode.key + ", Priority: " + newNode.priority);
            return newNode;
        }

        if(key < node.key){
            // go left
            node.left = insert(node.left,key);
            // Now after insertion we have to check heap order property
            if(node.left != null && node.left.priority > node.priority){
                // Now you should perform a right rotation
                node = rightRotate(node);
            }
        }else{
            node.right = insert(node.right,key);
            if(node.right != null && node.right.priority > node.priority){
                // Now you should perform a right rotation
                node = leftRotate(node);
            }
        }

        return node;
    }

    @Override
    public void insert(int key){
        root = insert(root,key);
    }

    private TreapNode delete(TreapNode node,int key){
        if(node == null){
            return null;
        }
        
        // First search for the key
        if(key < node.key){
            node.left = delete(node.left, key);
        }else if(key > node.key){
            node.right = delete(node.right,key);
        }else{
            // key == node.val
            System.out.println("🗑️ Deleting Node with Key: " + key);
            // Now check if it has only right child 
            if(node.left == null) return node.right;
            else if(node.right == null) return node.left;


            // Now the node has two children 
            // check the children priority
            if(node.right.priority > node.left.priority){
                // agar right ka priority zada hai toh do left rotation
                node = leftRotate(node);
                node.left = delete(node.left,key);
            }else{
                node = rightRotate(node);
                node.right = delete(node.right, key);
            }

        }

        return node;
        
    }

    @Override
    // Now writing code for deletion
    public void delete(int key){
        if (!search(key)) {
            System.out.println("❌ Key " + key + " not found, cannot delete.");
            return;
        }
        root = delete(root,key);
    }

    // Now write code for searching 
    private TreapNode search(TreapNode node,int key){
        if(node == null || node.key == key){
            return node;
        }

        if(key < node.key){
            return search(node.left, key);
        }
        
        return search(node.right,key);

    }

    @Override
    public boolean search(int key){
        return search(root,key) != null;
    }

    private void printInorder(TreapNode node){
        if(node == null) return;

        printInorder(node.left);
        System.out.println("Key: " + node.key + " | Priority: " + node.priority);
        printInorder(node.right);
    }

    @Override
    public void printInorder(){
        if (root == null) {
            System.out.println("🌲 Treap is empty.");
        } else {
            System.out.println("Inorder Traversal (Sorted Keys with Priorities):");
            printInorder(root);
        }
    }

    private void helperKthLargest(TreapNode node,int k){
        if(node == null) return ;

        helperKthLargest(node.right, k);
        
        k--;
        // check if k is zero 
        if(k == 0){
            temp = node; // this is the answer
            return;
        }

        helperKthLargest(node.left, k);

    }
    @Override
    public int getKthLargestElement(int k){
        helperKthLargest(root,k);
        
        return (temp!= null) ? temp.key : -1;
    }
}
