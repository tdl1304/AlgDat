package algdat6;

import java.io.IOException;
import java.util.*;


public class algdat6 {
    public static void main(String[] args) throws IOException {
        String path = "graphs/vgSkandinavia.txt";
        Date end;
        Date start = new Date();
        double time;
        TextFileHandler vg = new TextFileHandler(path);
        ArrayList<int[]> vgArray = vg.getIntArray();
        end = new Date();
        time = end.getTime()-start.getTime();
        System.out.println("importing file: "+ time);

        int V = vgArray.get(0)[0];
        //Change start node here!
        int source = 0;
        int[] connection;

        // Adjacency list representation of the
        // connected edges
        List<List<Node> > adj = new ArrayList<>();

        start = new Date();
        // Initialize list for every node
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        end = new Date();
        time = end.getTime()-start.getTime();
        System.out.println("initializing adj list: "+time);

        start = new Date();
        // Inputs for the DPQ graph
        for (int i = 1; i < vgArray.size() ; i++) {
             connection = vgArray.get(i);
            adj.get(connection[0]).add(new Node(connection[1], connection[2]));
        }
        time = end.getTime()-start.getTime();
        System.out.println("inserting into adj list: "+time);

        // Calculate the single source shortest path
        DPQ dpq = new DPQ(V);

        start = new Date();
        dpq.dijkstra(adj, source);
        end = new Date();
        time = end.getTime()-start.getTime();
        System.out.println("dijkstra: "+ time);

        // Print the shortest path to all the nodes
        // from the source node


        /*
        int dist[] = dpq.getDist();
        Map<Integer,Integer> prevNode = dpq.getPrevNode();
        System.out.println("The shorted path from node :");
        for (int i = 0; i < dist.length; i++)
            System.out.println(source + " to " + i + " is "
                    + dist[i] + ", previous node was " + prevNode.get(i));

         */


    }
}



