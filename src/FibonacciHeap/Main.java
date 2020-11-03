package FibonacciHeap;

public class Main {
    public static void main(String[] args) {
        FibonacciHeap fh = new FibonacciHeap();
        fh.insert(15);
        fh.insert(4);
        fh.insert(700);
        fh.insert(10);
        fh.insert(45);
        fh.insert(32);
        for (int i = 0; i < 5; i++) {
            fh.display();
            System.out.println(fh.extractMin()+" extracted");
        }


    }
}
