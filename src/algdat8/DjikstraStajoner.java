package algdat8;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class DjikstraStajoner {
    static String mappe, navnFil, kantFil, nodeFil;
    static int src, goal;
    static Date start, end;
    static Djikstra djik;
    public static void main(String[] args) throws IOException, InterruptedException {
        mappe = "graphs/oblig8/";
        navnFil = "interessepkt.txt";
        kantFil = "kanter.txt";
        nodeFil = "noder.txt";

        System.out.println("Filhåndtering:");
        start = new Date();
        FileHandler fh = new FileHandler(mappe, navnFil, nodeFil, kantFil);
        end = new Date();
        System.out.println(end.getTime()-start.getTime() + " ms");

        List<List<Node>> adj = fh.getAdj();


        Node[] nodes = fh.getNodes();
        int nodeCount = fh.getNodeCount();
        int verticesCount = fh.getVerticesCount();
        djik = new Djikstra(verticesCount, adj, nodes);
        stasjonTest();
    }

    public static void stasjonTest() {
        System.out.println("Bensinstasjoner rundt Trondheim lufthavn:");
        src = Norden.TRONDHEIMLUFTHAVN.getNode();
        start = new Date();
        djik.nearestStations(10, src, 2);
        end = new Date();
        System.out.println(end.getTime()-start.getTime() + " ms");
        System.out.println(djik.getNodesProcessed() + " processed");
        System.out.println(djik.getStations()+"\n");

        System.out.println("Ladestasjoner rundt Røros hotell:");
        src = Norden.ROROSHOTELL.getNode();
        start = new Date();
        djik.nearestStations(10, src, 4);
        end = new Date();
        System.out.println(end.getTime()-start.getTime() + " ms");
        System.out.println(djik.getNodesProcessed() + " processed");
        System.out.println(djik.getStations()+"\n");
    }
}

