package FibonacciHeap;

public class Main {
    public static void main(String[] args) {
        FibonacciHeap fh = new FibonacciHeap();
        fh.insert(3);
        fh.insert(2);
        fh.insert(1);
        System.out.println(fh.find(2).toString());
    }
}
