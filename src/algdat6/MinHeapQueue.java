package algdat6;

//A priority queue
public class MinHeapQueue {
    //priority queue
    private Node[] nodes;
    private int elements;


    public MinHeapQueue(int vertices) {
        nodes = new Node[vertices];
        elements = 0;
    }

    /*
    public static MinHeapQueue buildHeap(Node[] input) {
        int length = input.length;
        MinHeapQueue temp = new MinHeapQueue(length);
        for (int i = 0; i < length; i++) {
            temp.nodes[i] = input[i];
            temp.elements++;
        }
        return temp;
    }*/

    public boolean add(Node e) {
        if (nodes.length > elements) {
            nodes[elements] = e;
            elements++;
            return true;
        } else return false;
    }

    //swap top node with last node
    public Node remove() {
        if (elements > 0) {
            buildMinHeap();
            Node returnNode = nodes[0];
            Node swap = nodes[elements - 1];
            nodes[elements - 1] = nodes[0];
            nodes[0] = swap;
            elements--;
            return returnNode;
        } else throw new ArrayIndexOutOfBoundsException("Priority queue was empty");
    }

    public void buildMinHeap() {
        int n = elements;
        for (int i = n / 2 - 1; i >= 0; i--) {
            minHeapify(nodes, n, i);
        }
    }

    // To heapify a subtree rooted with node i which is
    // an index in arr[]. n is size of heap
    private void minHeapify(Node[] arr, int n, int i) {
        int smallest = i; // Initialize smallest as root
        int l = 2 * i + 1; // left = 2*i + 1
        int r = 2 * i + 2; // right = 2*i + 2

        // If left child is smaller than root
        if (l < n && arr[l].getCost() < arr[smallest].getCost())
            smallest = l;

        // If right child is smaller than smallest so far
        if (r < n && arr[r].getCost() < arr[smallest].getCost())
            smallest = r;

        // If smallest is not root
        if (smallest != i) {
            Node swap = arr[i];
            arr[i] = arr[smallest];
            arr[smallest] = swap;

            // Recursively heapify the affected sub-tree
            //minHeapify(arr, n, smallest);
        }
    }

}
