package FibonacciHeap;

public class Node {
    Node parent;
    Node left;
    Node right;
    Node child;
    int degree;
    int key; //route time
    int id; //node id
    boolean marked;

    public Node(int key, int id) {
        this.id = id;
        this.degree = 0;
        this.marked = false;
        this.parent = null;
        this.left = this; //doubly linked list references
        this.right = this;
        this.child = null;
        this.key = key;
    }

    public Node(Node parent, Node left, Node right, Node child, int degree, int key, int id, boolean marked) {
        this.parent = parent;
        this.left = left;
        this.right = right;
        this.child = child;
        this.degree = degree;
        this.key = key;
        this.id = id;
        this.marked = marked;
    }

    public int getId() {
        return id;
    }

    public int getKey() {
        return key;
    }


    public Node clone() {
        return new Node(parent, left, right, child, degree, key, id, marked);
    }

    @Override
    public String toString() {
        return ""+key;
    }
}
