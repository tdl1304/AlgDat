package algdat8;

import java.util.*;

public class Astar {
    private int V;
    private List<List<Node>> adj;
    private Node[] nodes;
    private int nodesProcessed;
    private Queue<Node> queue;
    private Node goal;
    private LinkedList<Integer> route;
    private int[] dist;
    private Node src;
    private HashMap<Integer, Integer> trackNodes;

    public Astar(int V, List<List<Node>> adj, Node[] nodes) {
        trackNodes = new HashMap<>();
        dist = new int[nodes.length];
        queue = new PriorityQueue<>();
        route = new LinkedList<>();
        this.V = V;
        this.adj = adj;
        this.nodes = nodes;
        nodesProcessed = 0;
    }

    public boolean shortestRoute(int src, int goal) {
        this.src = nodes[src];
        queue.clear();
        this.goal = nodes[goal];
        for (int i = 0; i < dist.length; i++) dist[i] = Integer.MAX_VALUE;
        dist[src] = 0;
        this.src.fcost = 0;
        queue.add(this.src);
        int u = 0;
        try {
            while (true) {
                u = queue.poll().id;
                if (u == goal) {
                    return true;
                }
                explore_Neighbours(u);
                nodesProcessed++;
            }
        } catch (NullPointerException e) {
            return false;
        }
    }

    //returnerer tiden i hundredels sekunder
    public int getTime() {
        return dist[goal.id];
    }

    public String getRoute() {
        int temp = goal.id;
        while(temp != src.id) {
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

    private double radiantsToDegrees(double radiants) {
        return radiants * 180.0 / Math.PI;
    }

    public int getNodesProcessed() {
        return nodesProcessed;
    }

    private void explore_Neighbours(int u) {
        int newDistance;
        int node;
        int gcost;
        int hcost;

        // Check all the neighbors of u
        for (int i = 0; i < adj.get(u).size(); i++) {
            Node n = adj.get(u).get(i);
            node = n.id;

            if (!trackNodes.containsKey(node)) {
                newDistance = n.kjoretid + dist[u];

                if(newDistance < dist[node]) {
                    hcost = avstand(n, goal);
                    n.kjoretid = newDistance;
                    nodes[node].kjoretid = newDistance;
                    nodes[node].cameFrom = u;
                    dist[node] = newDistance;
                    n.fcost = newDistance + hcost;
                    nodes[node].fcost = newDistance + hcost;
                }

                if(!trackNodes.containsKey(node)) {
                    trackNodes.put(node, node);
                    queue.add(n);
                }
            }
        }

    }


    //Jordens radius er 6371 km, høyeste fartsgrense 130km/t, 3600 sek/time
    //For å få hundredels sekunder: 2*6371/130*3600*100 = 35285538.46153846153846153846
    private int avstand(Node n1, Node n2) {
        double sin_bredde = Math.sin((n1.breddegrad - n2.breddegrad) / 2.0);
        double sin_lengde = Math.sin((n1.lengdegrad - n2.lengdegrad) / 2.0);
        return (int) (35285538.46153846153846153846 * Math.asin(Math.sqrt(
                sin_bredde * sin_bredde + n1.cos_bredde * n2.cos_bredde * sin_lengde * sin_lengde)));
    }

}
