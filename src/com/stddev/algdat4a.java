package com.stddev;

public class algdat4a {
    //Driver code
    public static void main(String[] args) {
        StringHashTable names = new StringHashTable();


    }

}

//simple linked list
class Node {
    String key;
    String value;
    // Linked list of same hash entries.
    Node next;

    public Node(String key, String value) {
        this.key = key;
        this.value = value;
        this.next = null;
    }

    private void add(Node node) {
        Node head = this;
        while(true) {
            if(head.equals(node)) {
                head.value = node.value;
            } else if (head.next == null) {
                next = node;
            } else {
                head = head.next;
            }
        }
    }

    public void put(String key, String value) {
        Node node = new Node(key, value);
        add(node);
    }

    @Override
    public String toString() {
        return "[" + key + ", " + value + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null) return false;
        if(this.getClass() != obj.getClass()) return false;

        Node node = (Node) obj;
        return key.equals(node.key);
    }
}

class StringHashTable {
    private Node[] nodes;

    //lastfactor is elements divided by total

    int hashCode(String key) {
        final int p = 31;
        int hash = 0;
        //rekkefølgen for bokstavene spiller en rolle, for hver char blir multiplisert med p^i
        for (int i = 0; i < key.length(); i++) {
            hash = p*hash + key.charAt(i);
        }
        return hash;
    }


    void put(String key, String value) {
    }

    int getSize() {
        return 0;
    }

    String get(String key) {
        return toString();
    }
}