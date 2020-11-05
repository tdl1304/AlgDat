package algdat5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Finds strongly connected components on specified graph using Kosaraju's algorithm
 */
public class algdat5 {
    //Driver code
    public static void main(String[] args) throws IOException {
        //Change name on path
        String path = "graphs/L7g6.txt";
        TextFileHandler graph_file = new TextFileHandler(path);
        ArrayList<int[]> graph_edges = graph_file.getIntArray();
        Graph graph = new Graph(graph_edges.get(0)[0]);
        for (int i = 1; i < graph_edges.size(); i++) {
            graph.addEdge(graph_edges.get(i)[0],graph_edges.get(i)[1]);
        }
        graph.stronglyConnectedComponents();
        System.out.println("File: "+ graph_file.getFile().getName());
        System.out.println(graph.getStronglyCC());
    }
}

class TextFileHandler {
    private File file;
    private ArrayList<String[]> stringArray;

    /**
     * Constructor method
     *
     * @param path path to text file
     */
    public TextFileHandler(String path) throws IOException {
        file = new File(path);
        stringArray = new ArrayList<>();
        this.fill();
    }

    public File getFile() {
        return file;
    }

    public ArrayList<String[]> getStringArray() {
        return stringArray;
    }

    public ArrayList<int[]> getIntArray() {
        ArrayList<int[]> temp = new ArrayList<>();
        for (String[] s : stringArray
        ) {
            temp.add(new int[]{Integer.parseInt(s[0]), Integer.parseInt(s[1])});
        }
        return temp;
    }

    private void fill() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        while ((line = br.readLine()) != null) {
            stringArray.add(line.trim().split("\\s+\\s+\\s|\\s|\t"));
        }
        br.close();
    }

    public String toString() {
        String names = "";
        for (String[] s : stringArray
        ) {
            names += s[0] + "," + s[1] + "\n";
        }
        return names;
    }
}

class Graph {
    private Vertex[] vertices;
    private LinkedList<Integer> finishStack;
    private HashSet<Integer> visited;
    //Strongly connected components
    private ArrayList<LinkedList<Integer>> stronglyCC;

    public Graph(int totalNodes) {
        stronglyCC = new ArrayList<>();
        vertices = new Vertex[totalNodes];
        finishStack = new LinkedList<>();
        visited = new HashSet<>();
        for (int i = 0; i < vertices.length ; i++) {
            vertices[i] = new Vertex(i);
        }
    }

    public void DFS(int vertexValue) throws StackOverflowError{
        if (visited.contains(vertexValue)) return;
        Vertex v = vertices[vertexValue];
        visited.add(vertexValue);
        for (int vertex : v.getAdjacentVertex()
        ) {
            if(visited.contains(vertex)) {
                continue;
            }
            DFS(vertex);
        }
        finishStack.offerFirst(vertexValue);
    }

    public void stronglyConnectedComponents() {
        //Step 1. DFS on normal graph
        int l = getLength();
        int i = 0;
        while(getVisitedSize() < l) {
            DFS(i);
            i++;
        }
        //Step 2. reverse graph and clear visited

        Graph reversed = this.reversedGraph();

        //Step 3. DFS on reversed graph

        while(reversed.getVisitedSize() < l) {
            reversed.DFS(finishStack.pop());
            if(!reversed.getFinishStack().isEmpty()) {
                stronglyCC.add(reversed.getFinishStack());
                reversed.clearFinishStack();
            }
        }
        clearFinishStack();
    }

    public ArrayList<LinkedList<Integer>> getStronglyCC() {
        return stronglyCC;
    }

    public int getVisitedSize() {
        return visited.size();
    }

    public LinkedList<Integer> getFinishStack() {
        LinkedList<Integer> copy = (LinkedList<Integer>) this.finishStack.clone();
        return copy;
    }

    public void clearFinishStack() {
        this.finishStack.clear();
    }

    public Graph reversedGraph() {
        int length = this.getLength();
        Graph reversedGraph = new Graph(length);
        for (Vertex v: vertices
        ) {
            for (int n: v.getAdjacentVertex()
            ) {
                reversedGraph.addEdge(n, v.getThisVertex());
            }
        }
        return reversedGraph;
    }

    public void addEdge(int vertexFrom, int vertexTo) {
        vertices[vertexFrom].add(vertexTo);
    }

    public int getLength() {
        return vertices.length;
    }

    @Override
    public String toString() {
        String s = "";
        for (Vertex v: vertices
        ) {
            s += v.toString()+"\n";
        }
        return s;
    }
}

class Vertex {
    private int vertexValue;
    private LinkedList<Integer> vertices;

    public Vertex(int i) {
        vertices = new LinkedList<>();
        vertexValue = i;
    }

    public void add(int vertexTo) {
        vertices.add(vertexTo);
    }
    
    public Iterator getIterator() {
        return vertices.iterator();
    }
    
    public int getThisVertex() {
        return vertexValue;
    }
    
    public LinkedList<Integer> getAdjacentVertex() {
        return vertices;
    }

    @Override
    public String toString() {
        return "Vertex:"+ vertexValue +" Connected vertices:" + vertices;
    }


}
