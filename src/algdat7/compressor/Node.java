package algdat7.compressor;

public class Node implements Comparable<Node> {
    int num;
    char c;
    Node right;
    Node left;

    public Node() {}

    public Node(int num) {
        this.num = num;
        this.c = '-';
    }

    public Node(int num, char c) {
        this.num = num;
        this.c = c;
    }


    @Override
    public String toString() {
        return "Node{" +
                "num=" + num +
                ", c=" + c +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return Integer.compare(this.num, o.num);
    }
}
