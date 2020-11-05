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
finner en vei med A* og djikstra, plott på kart
"kårvåg-gjemnes" og "trondheim-helsinki" djik og A*
 */
public class DjikstraDriver {
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
        src = Norden.TRONDHEIM.getNode();
        goal = Norden.HELSINKI.getNode();

        djik = new Djikstra(verticesCount, adj, nodes);
        System.out.println("Djikstra");
        start = new Date();
        boolean a = djik.shortestRoute(src, goal);
        end = new Date();
        if(!a) System.out.println("Couldn't find a valid path");
        else {
            writeTo(mappe + "djikstra.txt", djik.getRoute());
            System.out.println(toTime(djik.getTime()));
        }
        System.out.println(end.getTime() - start.getTime() + " ms");
        System.out.println(djik.getNodesProcessed() + " processed\n");
        //stasjonTest();

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

    public static String toTime(int hundrethOfAsec) {
        double hrs = 0;
        double min = 0;

        if (hundrethOfAsec > 360000) {
            hrs = hundrethOfAsec / 360000;
            hundrethOfAsec -= hrs * 360000;
        }
        if (hundrethOfAsec > 6000) {
            min =hundrethOfAsec / 6000;
            hundrethOfAsec -= min * 6000;
        }
        double sec = hundrethOfAsec / 100;
        return hrs + "h, " + min + "min, " + sec + "seconds";
    }

    public static void writeTo(String filename, String data) throws IOException {
        BufferedWriter br = new BufferedWriter(new FileWriter(filename));
        br.write(data);
        br.close();
    }


}