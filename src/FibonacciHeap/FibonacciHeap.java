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

    public Node extractMin() throws CloneNotSupportedException {
        Node currentMin = min;
        if (min != null) {
            removeAndInsertChildren();
            this.n--;
            return currentMin;
        }
        return null;
    }

    private void removeAndInsertChildren() throws CloneNotSupportedException {
        Node minRef = min; //reference to min
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
                n--;
            } while (child != firstChild);
        }
        //min-pointer to the next root and delete prev min root;
        minRef.left.right = minRef.right;
        minRef.right.left = minRef.left;
        minRef.child = null;
        if (minRef == minRef.right) {
            min = null;
        } else {
            this.min = minRef.right;
            consolidate();
        }
    }

    public void display() {
        StringBuilder sb = new StringBuilder();
        java.lang.String out = display(min, sb);
        System.out.println(out);
    }

    private java.lang.String display(Node c, StringBuilder sb) {
        sb.append("(");
        if (c == null) {
            sb.append(")");
            return sb.toString();
        } else {
            Node temp = c;
            Node child;
            do {
                sb.append(temp.key);
                child = temp.child;
                display(child, sb);
                sb.append("->");
                temp = temp.right;
            } while (temp != c);
            sb.append(")");
        }
        return sb.toString();
    }

    //Issue with uniting roots...
    //fix tree, link roots with same degrees together
    public void consolidate() {
        double phi = (1 + Math.sqrt(5)) / 2;
        int size = (int)(Math.log(n)/Math.log(phi));
        Node[] A = new Node[size+1];
        for (int i = 0; i <= size; ++i)
            A[i] = null;
        Node w = min;
        if (w != null) {
            Node check = min;
            do {
                Node x = w;
                int d = x.degree;
                while (A[d] != null) {
                    Node y = A[d];
                    if (x.key > y.key) {
                        Node temp = x;
                        x = y;
                        y = temp;
                        w = x;
                    }
                    linkHeap(y, x);
                    check = x;
                    A[d] = null;
                    d += 1;
                }
                A[d] = x;
                w = w.right;
            } while (w != null && w != check);
            this.min = null;
            for (int i = 0; i <= size; ++i) {
                if (A[i] != null) {
                    insert(A[i]);
                    n--;
                }
            }
        }
    }

    // Linking operation, x should be less than y
    private void linkHeap(Node y, Node x) {
        y.left.right = y.right;
        y.right.left = y.left;
        Node p = x.child;
        if (p == null) {
            y.right = y;
            y.left = y;
        } else {
            y.right = p;
            y.left = p.left;
            p.left.right = y;
            p.left = y;
        }
        y.parent = x;
        x.child = y;
        x.degree++;
        y.marked = false;
    }

    public Node getMin() {
        return min;
    }

    private void insert(Node n) {
        if (min == null) {
            min = n;
            min.right = min;
            min.left = min;
        } else { //insert node left to min in the doubly linked list
            n.right = min;
            n.left = min.left;
            min.left.right = n;
            min.left = n;
            if (n.key < min.key) {
                min = n;
            }
        }
        this.n++;
    }

    public void insert(int key, int id) {
        insert(new Node(key, id));
    }

    private void find(int key, Node node) {
        if (node == null) {
            return;
        } else {
            Node current = node;
            do {
                if (key == current.key) found = current;
                else {
                    Node child = current.child;
                    find(key, child); //try finding the key in the the children nodes
                    current = current.right; //go to next node in heap list
                }
            } while (current != node && found == null);
        }
    }

    public Node find(int key) {
        found = null;
        find(key, this.min);
        return found;
    }

    private void decreaseKey(Node node, int val) {
        if (val > node.key) return; //value did not decrease
        node.key = val; //decreased value
        Node parent = node.parent;
        if (node.parent != null && node.parent.key > val) {
            //cut node off
            cut(node, parent);
            //do a cascade check
            cascading_cut(parent);
        }
        if (val < min.key) min = node;
    }

    private void cascading_cut(Node node) {
        Node parent = node.parent;
        if (parent != null) {
            if (!node.marked) node.marked = true;
            else {
                cut(node, parent);
                cascading_cut(parent);
            }
        }
    }

    private void cut(Node node, Node parent) {
        node.right.left = node.left;
        node.left.right = node.right;
        parent.degree--;
        Node rightC = node.right;
        node.right = null;
        node.left = null;
        node.parent = null;
        node.marked = false;
        insert(node);
        parent.child = rightC;
    }

    public void decreaseKey(int key, int val) {
        Node decrease = find(key);
        decreaseKey(decrease, val);
    }

    public void delete(Node node) throws CloneNotSupportedException {
        decreaseKey(node, Integer.MIN_VALUE);
        extractMin();
    }

    public void insert(int key) {
        insert(new Node(key, 0));
    }

    @Override
    public String toString() {
        java.lang.String output = display(min, new StringBuilder());
        return output;
    }
}
