package FibonacciHeap.Tests;

import FibonacciHeap.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class FibonacciHeapTest extends FibonacciHeap {
    private FibonacciHeap obj;

    @Before
    public void setup() {
        obj = new FibonacciHeap();
        obj.insert(1);
        obj.insert(2);
        obj.insert(3);
        obj.insert(4);
        obj.insert(5);
        obj.insert(999);
        obj.insert(10);
        obj.insert(999);

    }

    @After
    public void displayTest() {
        //obj.display();
    }

    @Test
    public void delete() {
        obj.extractMin();
        obj.extractMin();
        Node o = obj.find(10);
        obj.delete(o);
        assert obj.find(10) == null : "Did not delete correctly";
    }

    @Test
    public void decreaseKey() {
        obj.extractMin();
        obj.extractMin();
        obj.decreaseKey(3, 1);
        assertEquals(obj.find(1).getKey(), obj.getMin(), "decrease key failed");
    }

    @Test
    public void find() {
        assertEquals(obj.find(4).getKey(), 4,"Node not found" );
    }

    @Test
    public void extractMinTest() {
        boolean test = true;
        for (int i = 0; i < 8; i++) {
            if(obj.getMin() != obj.extractMin()) {
                test = false;
            }
        }
        assert test : "min extracted is wrong";
    }


    public void assertEquals(Object o1, Object o2, String errorMessage) {
        assert (o1.equals(o2)) : "Object: "+o1+" Object: "+o2+" Error: "+errorMessage;
    }

}
