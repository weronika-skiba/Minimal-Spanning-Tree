package pl.edu.pw.ee;

import org.junit.Test;

public class PrimMinHeapTest {
    private PrimMinHeap primMinHeap;
    private PrimGraph primGraph;

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowException_putNull() {
        primMinHeap = new PrimMinHeap();
        primMinHeap.put(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowException_popEmpty() {
        primMinHeap = new PrimMinHeap();
        primGraph = new PrimGraph();
        primMinHeap.pop(primGraph);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowException_inputGraphIsNull() {
        primMinHeap = new PrimMinHeap();
        primMinHeap.pop(null);
    }
}