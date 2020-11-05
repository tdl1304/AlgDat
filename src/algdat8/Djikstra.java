package algdat8;

import java.util.*;

public class Djikstra {
    private int dist[];
    private Node[] nodes;
    private int nodesProcessed;
    private Queue<Node> queue;
    private int V; // Number of vertices
    private List<List<Node>> adj; // [][] index 1 is from, index 2 is to. e.g. [1][2] from 1 to 2
    private LinkedList<Integer> route; //Store shortest route in id of nodes
    private ArrayList<Integer> stations;
    private int goal;
    private int src;

    public Djikstra(int V, List<List<Node>> adj, Node[] nodes) {
        route = new LinkedList<>();
        this.nodes = nodes;
        this.V = V;
        dist = new int[V];
        queue = new PriorityQueue();
        this.adj = adj;
        stations = new ArrayList<>();
    }

    public String getStations() {
        return getCoords(stations);
    }

    public String getRoute() {
        int temp = goal;
        while(temp != src) {
            route.addLast(temp);
            temp = nodes[temp].cameFrom;
        }
        return getCoords(route);
    }

    private String getCoords(List list) {
        if (!list.isEmpty()) {
            StringBuilder b1 = new StringBuilder();
            Iterator iterator = list.iterator();
            Node n;
            while (iterator.hasNext()) {
                n = nodes[(int) iterator.next()];
                b1.append(radiantsToDegrees(n.breddegrad) + ", "
                        + radiantsToDegrees(n.lengdegrad) + "\n");
            }
            return b1.toString();
        }
        return null;
    }

    public int getNodesProcessed() {
        return nodesProcessed;
    }

    public void nearestStations(int number, int src, int identifier) {
        this.src = src;
        route.clear();
        stations.clear();
        queue.clear();
        nodesProcessed = 0;
        for (int i = 0; i < V; i++) dist[i] = Integer.MAX_VALUE;
        // Add source node to the priority queue
        queue.add(nodes[src]);
        // Distance to the source is 0
        dist[src] = 0;
        int found;
        int u;

        while (stations.size() < number) {
            //If indexOutOfBounds exception occurs, there are nodes that can't be reached
            //Fordi dette er et kart, kan jeg anta at første ruten er den korteste
            //Da djikstra gjennomfører søk i en sirkel
             do {
                // remove the minimum distance node
                // from the priority queue
                u = queue.poll().id; //node id
                e_Neighbours(u, -1);
                nodesProcessed++;
            } while (!queue.isEmpty() && queue.peek().kode != identifier);
            found = queue.peek().id;
            if(!stations.contains(found)) stations.add(found);
        }
        this.goal = stations.get(number-1);
    }

    /**
     * djikstra's algorythm
     *
     * @param src Id for node
     */
    public boolean shortestRoute(int src, int goal) {
        this.src = src;
        route.clear();
        queue.clear();
        nodesProcessed = 0;
        this.goal = goal;
        for (int i = 0; i < V; i++) dist[i] = Integer.MAX_VALUE;
        int u;
        // Add source node to the priority queue
        queue.add(nodes[src]);

        // Distance to the source is 0
        dist[src] = 0;
        //If indexOutOfBounds exception occurs, there are nodes that can't be reached
        //Fordi dette er et kart, kan jeg anta at første ruten er den korteste
        //Da djikstra gjennomfører søk i en sirkel

        try {
            while (true) {
                // remove the minimum distance node
                // from the priority queue
                u = queue.poll().id; //node id
                e_Neighbours(u, -1);
                nodesProcessed++;
                if (u == this.goal) return true;
            }
        } catch(NullPointerException e) {
            return false;
        }

    }

    public int[] getDist() {
        return dist;
    }

    private double radiantsToDegrees(double radiants) {
        return radiants * 180.0 / Math.PI;
    }

    public int getTime() {
        return dist[goal];
    }

    // Function to process all the neighbours
    // of the passed node
    private void e_Neighbours(int u, int kode) {
        int edgeDistance = -1;
        int newDistance = -1;
        int node;

        // All the neighbors of u
        for (int i = 0; i < adj.get(u).size(); i++) {
            Node v = adj.get(u).get(i);
            node = v.id;

            // If current node hasn't already been processed
            if (!v.found) {
                v.found = true;
                edgeDistance = v.kjoretid;
                newDistance = dist[u] + edgeDistance;

                // If new distance is cheaper in cost
                if (newDistance < dist[node]) {
                    dist[node] = newDistance;
                    nodes[node].cameFrom = u;
                }

                // Add the current node to the queue
                queue.add(v);
            }
        }
    }
}
