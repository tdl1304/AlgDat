package algdat8;

public class Node implements Comparable {
    String navn;
    int id;
    int kode;
    double breddegrad;
    double cos_bredde;
    double lengdegrad;
    int kjoretid;
    int fcost;
    boolean found = false;
    int cameFrom;

    public Node(int cameFrom, int fcost, int id, String navn, int kode, double breddegrad, double lengdegrad, int kjoretid) {
        this.id = id;
        this.navn = navn;
        this.kode = kode;
        this.breddegrad = breddegrad;
        this.lengdegrad = lengdegrad;
        this.kjoretid = kjoretid;
        this.fcost = fcost;
        this.cameFrom = cameFrom;
    }

    public Node(int id, double breddegrad, double lengdegrad) {
        this.cameFrom = -1;
        this.id = id;
        navn = "";
        kode = 0;
        this.breddegrad = breddegrad;
        cos_bredde = Math.cos(breddegrad);
        this.lengdegrad = lengdegrad;
        kjoretid = Integer.MAX_VALUE;
        fcost = Integer.MAX_VALUE;
    }

    public Node clone() {
        return new Node(cameFrom,fcost, id, navn, kode, breddegrad, lengdegrad, kjoretid);
    }

    @Override
    public String toString() {
        return "Node{" +
                "navn='" + navn + '\'' +
                ", id=" + id +
                ", kode=" + kode +
                ", breddegrad=" + breddegrad +
                ", cos_bredde=" + cos_bredde +
                ", lengdegrad=" + lengdegrad +
                ", kjoretid=" + kjoretid +
                ", fcost=" + fcost +
                ", found=" + found +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        Node a = this;
        Node b = (Node)o;
        if(a.equals(b)) return 0;

        if(a.fcost == Integer.MAX_VALUE || b.fcost == Integer.MAX_VALUE) {
            return a.kjoretid-b.kjoretid;
        } else {
            return a.fcost-b.fcost;
        }


    }
}
