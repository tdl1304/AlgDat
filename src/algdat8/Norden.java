package algdat8;

public enum Norden {
    TRONDHEIMLUFTHAVN(6198111),
    ROROSHOTELL(1117256),
    PRINSENKINO(3399069),
    DRAGVOLL(1528394),
    KÅRVÅG(6013683),
    OSLO(2353304),
    STOCKHOLM(5916504),
    HiT(5287663),
    GØTEBORG(6347328),
    TRONDHEIM(2399829),
    HELSINKI(1221382),
    GJEMNES(6225195);

    private int node;

    Norden(int i) {
        node = i;
    }

    public int getNode() {
        return node;
    }
}
