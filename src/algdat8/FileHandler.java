package algdat8;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*  interesse punkt: nodenr kode "navn" , kode=2 er bensinstasjon, kode=4 er ladestasjon,
    og kode 6 = 4+2
    Kanter lagres i adj[fra][til]
    Noder lagres i

 */
public class FileHandler {
    private String mappe;
    private String navnFil;
    private String nodeFil;
    private String kantFil;
    private int verticesCount;
    private int nodeCount;
    private List<List<Node>> adj = new ArrayList<>(); //liste over nodens naboer
    private Node[] nodes;


    public FileHandler(String mappe, String navnFil, String nodeFil, String kantFil) throws IOException, InterruptedException {
        this.navnFil = mappe+navnFil;
        this.kantFil = mappe+kantFil;
        this.nodeFil = mappe+nodeFil;
        fill();
    }

    public int getNodeCount() {
        return nodeCount;
    }

    public int getVerticesCount() {
        return verticesCount;
    }

    public Node[] getNodes() {
        return nodes;
    }

    public List<List<Node>> getAdj() {
        return adj;
    }

    private void fill() throws IOException, InterruptedException {
        fillInCoords(nodeFil);
        Thread t1 = new Thread(()-> {
            try {
                fillInNames(navnFil);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        Thread t2 = new Thread(()-> {
            try {
                fillInVertices(kantFil);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();

    }

    private void fillInVertices(String fileName) throws IOException {
        String[] felt = new String[10]; //Max 10 felt i en linje
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line = "";
        felt = hsplit(br.readLine(),1);
        verticesCount = Integer.parseInt(felt[0]);
        List temp;
        Node tempNode;
        for (int i = 0; i < nodeCount; i++) {
            adj.add(new ArrayList<>());
        }
        while((line = br.readLine()) != null) {
            felt = hsplit(line, 3);
            temp = adj.get(Integer.parseInt(felt[0]));
            tempNode = nodes[Integer.parseInt(felt[1])].clone();
            tempNode.kjoretid = Integer.parseInt(felt[2]);
            temp.add(tempNode);
        }
        br.close();
    }

    private void fillInCoords(String fileName) throws IOException {
        String[] felt = new String[10]; //Max 10 felt i en linje
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line = "";
        felt = hsplit(br.readLine(), 1);
        nodeCount = Integer.parseInt(felt[0]);
        nodes = new Node[nodeCount];
        int i = 0;
        while((line = br.readLine()) != null) {
            felt = hsplit(line, 3);
            nodes[i] = new Node(i,degreeToRadians(Double.parseDouble(felt[1])),
                    degreeToRadians(Double.parseDouble(felt[2])));
            i++;
        }
        br.close();
    }

    private void fillInNames(String fileName) throws IOException {
        String[] felt = new String[10]; //Max 10 felt i en linje
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line = "";
        br.readLine(); // get rid of first line;
        Node temp;
        while((line = br.readLine()) != null) {
            felt = hsplit(line, 3);
            temp = nodes[Integer.parseInt(felt[0])];
            temp.kode = Integer.parseInt(felt[1]);
            temp.navn = felt[2];
        }
        br.close();
    }


    /**
     * split a string
     * @param linje som skal splittes
     * @param antall felt som skal hentes ut
     */
    private String[] hsplit(String linje, int antall) {
        String[] felt = new String[10]; //Max 10 felt i en linje
        int j = 0;
        int lengde = linje.length();
        for (int i = 0; i < antall; ++i) {
            //Hopp over innledende blanke, finn starten på ordet
            while (linje.charAt(j) <= ' ') ++j;
            int ordstart = j;
            //Finn slutten på ordet, hopp over ikke-blanke
            while (j < lengde && linje.charAt(j) > ' ') ++j;
            felt[i] = linje.substring(ordstart, j);
        }
        return felt;
    }

    private double degreeToRadians(double degrees) {
        double radians = degrees*Math.PI/180.00;
        return radians;
    }



}
