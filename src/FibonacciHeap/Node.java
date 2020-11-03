package FibonacciHeap;

public class Node {
    Node parent;
    Node left;
    Node right;
    Node child;
    int degree;
    int key;
    boolean marked;

    public Node(int key) {
        this.degree = 0;
        this.marked = false;
        this.parent = null;
        this.left = this; //doubly linked list references
        this.right = this;
        this.child = null;
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    @Override
    public String toString() {
        return ""+key;
    }
}
