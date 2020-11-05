package algdat6;

// Java implementation of Dijkstra's Algorithm
// using custom priority queue
import java.util.*;
public class DPQ {
    private int dist[];
    //private PriorityQueue<Node> pq;
    private MinHeapQueue pq;
    private Map<Integer, Integer> prevNode;
    private int V; // Number of vertices
    List<List<Node>> adj; // [][] index 1 is from, index 2 is to. e.g. [1][2] from 1 to 2

    public DPQ(int V) {
        this.V = V;
        dist = new int[V];
        //pq = new PriorityQueue<Node>(V, new Node());
        pq = new MinHeapQueue(V);
        prevNode = new HashMap<Integer, Integer>();
    }

    public Map<Integer, Integer> getPrevNode() {
        return prevNode;
    }

    // Function for Dijkstra's Algorithm
    public void dijkstra(List<List<Node>> adj, int src) {
        this.adj = adj;

        for (int i = 0; i < V; i++) dist[i] = Integer.MAX_VALUE;

        // Add source node to the priority queue
        pq.add(new Node(src, 0));

        // Distance to the source is 0
        dist[src] = 0;
        int i = 0;
        //If indexOutOfBounds exception occurs, there are nodes that can't be reached
        try {
            while (i != V) {
                // remove the minimum distance node
                // from the priority queue
                int u = pq.remove().getNode();
                e_Neighbours(u);
                i++;
            }
        } catch(Exception e) {
            System.out.println(e.getLocalizedMessage());
            System.out.println("Returning");
        }
    }

    public int[] getDist() {
        return dist;
    }

    // Function to process all the neighbours
    // of the passed node
    private void e_Neighbours(int u) {
        int edgeDistance = -1;
        int newDistance = -1;
        int cost;
        int node;

        // All the neighbors of v
        for (int i = 0; i < adj.get(u).size(); i++) {
            Node v = adj.get(u).get(i);
             node = v.getNode();
             cost = v.getCost();

            // If current node hasn't already been processed
            if (!v.isFound()) {
                v.setFound(true);
                edgeDistance = cost;
                newDistance = dist[u] + edgeDistance;
                prevNode.put(node, u);

                // If new distance is cheaper in cost
                if (newDistance < dist[node])
                    dist[node] = newDistance;
                    prevNode.replace(node,u);

                // Add the current node to the queue
                pq.add(new Node(node, dist[node]));
            }
        }
    }
}
