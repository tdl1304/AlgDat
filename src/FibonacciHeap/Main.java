package FibonacciHeap;

public class Main {
    public static void main(String[] args) {
        FibonacciHeap fh = new FibonacciHeap();
        fh.insert(15);
        fh.insert(4);
        fh.insert(276);
        fh.insert(45);
        fh.insert(32);
        for (int i = 0; i < 5; i++) {
            System.out.println(fh.n);
            System.out.println(fh.extractMin()+" extracted");
        }


    }
}
