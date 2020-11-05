package algdat6;

import java.util.Comparator;
//Retrieved from geeks for geeks
class Node implements Comparator<Node> {
    private int node;
    private int cost;
    private boolean found;

    public Node(){}

    public Node(int node, int cost)
    {
        this.node = node;
        this.cost = cost;
    }

    public boolean isFound() {
        return found;
    }

    public void setFound(boolean found) {
        this.found = found;
    }

    public int getCost() {
        return cost;
    }

    public int getNode() {
        return node;
    }

    @Override
    public int compare(Node node1, Node node2)
    {
        if (node1.getCost() < node2.getCost())
            return -1;
        if (node1.getCost() > node2.getCost())
            return 1;
        return 0;
    }



}