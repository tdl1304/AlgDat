package com.stddev.algdat5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class algdat5 {
    public static void main(String[] args) throws IOException {
        TextFileHandler L7g6_file = new TextFileHandler("graphs/L7g6.txt");
        ArrayList<int[]> L7g6_edges = L7g6_file.getIntArray();
        Graph L7g6_graph = new Graph(L7g6_edges.get(0)[0]);

        for (int i = 1; i < L7g6_edges.size(); i++) {
            L7g6_graph.addEdge(L7g6_edges.get(i)[0],L7g6_edges.get(i)[1]);
        }

        L7g6_graph.DFS(7);


        System.out.println(L7g6_graph.toString());
        System.out.println(L7g6_graph.getVisited());
        System.out.println(L7g6_graph.getFinishStack());

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
            stringArray.add(line.split(" "));
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

    public Graph(int totalNodes) {
        vertices = new Vertex[totalNodes];
        finishStack = new LinkedList<>();
        visited = new HashSet<>();
        for (int i = 0; i < vertices.length ; i++) {
            vertices[i] = new Vertex(i);
        }
    }

    public void DFS(int vertexValue) {
        Vertex v = vertices[vertexValue-1];
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

    public HashSet<Integer> getVisited() {
        return visited;
    }

    public LinkedList<Integer> getFinishStack() {
        return finishStack;
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
