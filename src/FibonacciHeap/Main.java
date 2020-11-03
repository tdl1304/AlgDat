package FibonacciHeap;

import FibonacciHeap.Tests.FibonacciHeapTest;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;

public class Main {
    public static void main(String[] args) {
        runTest();
        FibonacciHeap fh = new FibonacciHeap();
        fh.insert(15);
        fh.insert(4);
        fh.insert(700);
        fh.insert(10);
        fh.insert(45);
        fh.insert(32);
        for (int i = 0; i < 6; i++) {
            fh.display();
            System.out.println(fh.extractMin()+" extracted");
        }
    }

    public static void runTest() {
        JUnitCore junit = new JUnitCore();
        junit.addListener(new TextListener(System.out));
        junit.run(FibonacciHeapTest.class);
    }
}
