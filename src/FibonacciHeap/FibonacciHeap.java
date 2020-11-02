package FibonacciHeap;

public class FibonacciHeap {

    private Node min; //reference to a min node
    private int n; // amount of nodes
    private Node found; //reference to the found node


    public FibonacciHeap() {
        min = null;
        n = 0;
        found = null;
    }

    public int extractMin() {
        Node currentMin = min;
        if(min != null) {
            removeAndInsertChildren();
            Thread t1 = new Thread(()->consolidate());
            t1.start();
            return currentMin.key;
        }
        return Integer.MAX_VALUE;
    }

    private void removeAndInsertChildren() {
        Node minR = min.right;
        Node minL = min.left;
        Node child = min.child;
        Node firstChild = child;
        Node curr;
        //insert the min node's children into the queue
        if (child != null) {
            do {
                curr = child.right;
                child.parent = null;
                insert(child);
                child = curr;
            } while (child != firstChild);
        }
        //min-pointer to the next root and delete prev min root;
        minR.left = minL;
        minL.right = minR;
        min.child = null;
        if (min == min.right) min = null;
        else {
            min = min.right;
        }
        this.n--;
    }

    private void consolidate() {
        Node[] nodes = new Node[log2(n)];


    }

    private int log2(int N) {
        return (int)(Math.log(N)/Math.log(2));
    }

    public int getMin() {
        return min.key;
    }

    private void insert(Node n) {
        if(min == null) {
            min = n;
        } else { //insert node left to min in the doubly linked list
            n.right = min;
            n.left = min.left;
            min.left.right = n;
            min.left = n;
            if(n.key < min.key) {
                min = n;
            }
        }
        this.n++;
    }

    public void insert(int key) {
         insert(new Node(key));
    }

    private void find(int key, Node node) {
        if(node == null) {
            return;
        } else {
            Node current = node;
             do {
                if(key == current.key) found = current;
                else {
                    Node child = current.child;
                    find(key, child); //try finding the key in the the children nodes
                    current = current.right; //go to next node in heap list
                }
            } while(current != node && found == null);
        }
    }

    public Node find(int key) {
        found = null;
        find(key, this.min);
        return found;
    }

}
