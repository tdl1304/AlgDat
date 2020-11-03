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
        if (min != null) {
            removeAndInsertChildren();
            this.n--;
            return currentMin.key;
        }
        return Integer.MAX_VALUE;
    }

    private void removeAndInsertChildren() {
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
        display(min);
        System.out.println();
    }

    private void display(Node c) {
        System.out.print("(");
        if (c == null) {
            System.out.print(")");
            return;
        } else {
            Node temp = c;
            Node child;
            do {
                System.out.print(temp.key);
                child = temp.child;
                display(child);
                System.out.print("->");
                temp = temp.right;
            } while (temp != c);
            System.out.print(")");
        }
    }

    //Issue with uniting roots...
    //fix tree, link roots with same degrees together
    private void consolidate() {
        int size = log2(n) + 1;
        Node[] nodes = new Node[size];
        Node currentNode = min;
        Node prevMin = min;
        Node temp = null;
        Node temp2 = null;
        int degree;
        do {
            degree = currentNode.degree;
            temp = currentNode;
            while (nodes[degree] != null) {
                temp2 = nodes[degree];
                //Link roots with same degrees together
                if (currentNode.key < temp2.key) {
                    temp = linkHeap(temp, temp2);
                } else {
                    temp = linkHeap(temp2, temp);
                }

                if(temp.key == prevMin.key) {
                    //retain the same min-key throughout the consolidation
                    prevMin = temp;
                } else if(prevMin.parent != null){
                    //if min-key node was moved to a child
                    prevMin = prevMin.parent;
                }

                currentNode = temp;
                nodes[degree] = null;
                degree++;
            }
            nodes[degree] = temp;
            currentNode = currentNode.right;
        } while (currentNode!= prevMin);
        this.min = null;
        for (int i = 0; i < size; i++) {
            if (nodes[i] != null) {
                insert(nodes[i]);
                n--;
            }
        }
    }

    //Root node h1 is always less than h2
    private Node linkHeap(Node h1, Node h2) {
        h2.left.right = h2.right;
        h2.right.left = h2.left;
        if (h1.child == null) {
            h2.right = h2;
            h2.left = h2;
        } else {
            h2.right = h1.child;
            h2.left = h1.child.left;
            h1.child.left.right = h2;
            h1.child.left = h2;
        }
        h2.parent = h1;
        h1.child = h2;
        h1.degree++;
        h2.marked = false;
        return h1;
    }

    private int log2(int N) {
        return (int) (Math.log(N) / Math.log(2));
    }

    public int getMin() {
        return min.key;
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

    public void insert(int key) {
        insert(new Node(key));
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
        if(val > node.key) return; //value did not decrease
        node.key = val; //decreased value
        Node parent = node.parent;
        if(node.parent != null && node.parent.key > val) {
            //cut node off
            cut(node, parent);
            //do a cascade check
            cascading_cut(parent);
        }
        if(val < min.key) min = node;
    }

    private void cascading_cut(Node node) {
        Node parent = node.parent;
        if(parent != null) {
            if(!node.marked) node.marked = true;
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
        Node decrease= find(key);
        decreaseKey(decrease, val);
    }

    public void delete(Node node) {
        decreaseKey(node, Integer.MIN_VALUE);
        extractMin();
    }
}
