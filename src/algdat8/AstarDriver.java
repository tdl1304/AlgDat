package algdat8;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/*
Krav:
Tidsbruk, antall noder fra køen og plott alt
X 10 bensinstasjoner nærmest "Trondheim lufthavn", plott på kart med djikstra: 2
X 10 ladestasjoner nærmest "røros hotell" plott kart med djikstra: 4
Xfinner en vei med A* og djikstra, plott på kart
X"kårvåg-gjemnes" og "trondheim-helsinki" djik og A*
 */
public class AstarDriver {
    static String mappe, navnFil, kantFil, nodeFil;
    static int src, goal;
    static Date start,end;
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
        src = Norden.TRONDHEIM.getNode();
        goal = Norden.ROROSHOTELL.getNode();

        Astar astar = new Astar(verticesCount, adj, nodes);
        System.out.println("A*");
        start = new Date();
        boolean a = astar.shortestRoute(src, goal);
        end = new Date();
        if(!a) System.out.println("Couldn't find a valid path");
        else {
            writeTo(mappe + "astar.txt", astar.getRoute());
            System.out.println(toTime(astar.getTime()));
        }
        System.out.println(end.getTime() - start.getTime() + " ms");
        System.out.println(astar.getNodesProcessed() + " processed\n");

    }

    public static String toTime(int hundrethOfAsec) {
        int timer = ((int)hundrethOfAsec/100)/3600;
        int minutter = (((int)hundrethOfAsec/100)/60)-timer*60;
        int sekunder = ((int)hundrethOfAsec/100)-timer*3600-minutter*60;
        return timer + "h, " + minutter + "min, " + sekunder + "seconds";
    }

    public static void writeTo(String filename, String data) throws IOException {
        BufferedWriter br = new BufferedWriter(new FileWriter(filename));
        br.write(data);
        br.close();
    }


}