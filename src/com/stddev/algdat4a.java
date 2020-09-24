package com.stddev;

import java.io.*;
import java.util.ArrayList;

public class algdat4a {
    //Driver code
    public static void main(String[] args) throws IOException {
        textFileHandler textFile = new textFileHandler("navn20.txt");
        ArrayList<String[]> strings = textFile.getStringArray();
        int size = strings.size();
        StringHashTable names = new StringHashTable(size);

        for (String[] string : strings) {
            //key er etternavn, value er hele navnet
            names.put(string[1], string[0] + "," + string[1]);
        }

        double lastFactor = names.getLastFactor();
        String stud1 = names.get("Luu");
        String stud2 = names.get("Julsen");
        System.out.println(stud1 + " : " + stud2);
        System.out.println("collision/person: "+ names.getCollisionPerPerson());
        System.out.println("lastfaktor "+ lastFactor + " collisions: " + names.getCollision());


        /*
        //for printing all names
        for (Node e : names.getEntries()
        ) {
            System.out.println(e.toString());
        }
        */

    }
}

class textFileHandler {
    private File file;
    private ArrayList<String[]> stringArray;

    /**
     * Constructor method
     *
     * @param path path to text file
     */
    public textFileHandler(String path) throws IOException {
        file = new File(path);
        stringArray = new ArrayList<>();
        this.fill();
    }

    public ArrayList<String[]> getStringArray() {
        return stringArray;
    }

    private void fill() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        while ((line = br.readLine()) != null) {
            stringArray.add(line.split(","));
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

//simple linked list
class Node {
    private String key;
    private String value;
    // Linked list of same hash entries.
    private Node next;

    public Node(String key, String value) {
        this.key = key;
        this.value = value;
        this.next = null;
    }

    private void add(Node node) {
        Node head = this;
        while (true) {
            if (head.equals(node)) {
                head.value = node.value;
                break;
            } else if (head.next == null) {
                head.next = node;
                break;
            } else {
                head = head.next;
            }
        }
    }

    public void put(String key, String value) {
        Node node = new Node(key, value);
        add(node);
    }

    public String get(String key) {
        Node start = this;
        int i = 0;
        while (true) {
            if (start.key.equals(key)) {
                if (i > 0) System.out.println("Kollisjon i søk med [" + key + ", " + start.value + "]");
                return start.value;
            } else if (start.next == null) {
                return null;
            } else {
                start = start.next;
                i++;
            }
        }
    }

    @Override
    public String toString() {
        return "[" + key + ", " + value + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;

        Node node = (Node) obj;
        return key.equals(node.key);
    }
}

class StringHashTable {
    private Node[] buckets;
    private final int size;
    //teller kollisjoner ved innsetting
    int collision;
    //antall innlagte elementer i buckets[]
    int elements;
    //alle innlagte nøkler, også tall på antall personer
    ArrayList<String> keys;

    //lastfactor is elements divided by total
    public StringHashTable(int size) {
        this.size = size;
        buckets = new Node[size];
        collision = 0;
        elements = 0;
        keys = new ArrayList<>();
    }

    public double getLastFactor() {
        double m = size;
        double n = elements;
        return n / m;
    }

    public double getCollisionPerPerson() {
        return (double) collision/keys.size();
    }

    public int getSize() {
        return size;
    }

    public int getCollision() {
        return collision;
    }

    private int hashCode(String key) {
        final int p = 31;
        int hash = 31;
        //rekkefølgen for bokstavene spiller en rolle, for hver char blir multiplisert med p^i
        for (int i = 0; i < key.length(); i++) {
            hash = p*hash + key.charAt(i);
        }
        return Math.abs(hash)%(size-1);
    }

    public void put(String key, String value) {
        keys.add(key);
        int hash = hashCode(key);
        if (buckets[hash] == null) {
            elements++;
            buckets[hash] = new Node(key, value);
        } else {
            System.out.println("kollisjon ved innsetting av [" + key + ", " + value + "]");
            collision++;
            buckets[hash].put(key, value);
        }
    }

    public String get(String key) {
        int hash = hashCode(key);
        if (buckets[hash] == null) {
            return null;
        } else {
            return buckets[hash].get(key);
        }
    }

    public ArrayList<Node> getEntries() {
        ArrayList<Node> nodes = new ArrayList<>();
        Node node;
        String value;
        for (String key : keys) {
            value = get(key);
            node = new Node(key, value);
            nodes.add(node);
        }
        return nodes;
    }
}