package Treaps;
public class TreapNode{
    int key;
    int priority;
    TreapNode left;
    TreapNode right;

    // Write a constructor
    public TreapNode(int key){
        this.key = key;
        this.priority = (int) (Math.random() * 100);
        this.left = this.right = null;
    }

}

