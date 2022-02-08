package pl.edu.pw.ee;

import java.util.ArrayList;
import java.util.List;

class PrimMinHeap {

    private List<Edge> heap;
    private int n;

    PrimMinHeap() {
        heap = new ArrayList<>();
        n = 0;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public void put(Edge item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        if (!heap.contains(item)) {
            heap.add(n++, item);
            heapUp();
        }
    }

    public Edge pop(PrimGraph graph) {
        if (graph == null) {
            throw new IllegalArgumentException("Graph cannot be null.");
        }
        if (isEmpty()) {
            throw new IllegalArgumentException("Heap cannot be empty.");
        }
        Edge tmp = heap.get(0);
        heap.set(0, heap.get(--n));
        heap.set(n, tmp);
        heapDown();
        if (graph.getVertix(tmp.getFirstVertex()).getVisited() && graph.getVertix(tmp.getSecondVertex()).getVisited()) {
            if (!isEmpty())
                tmp = pop(graph);
            else
                return null;
        }
        return tmp;
    }

    private void heapUp() {
        int i = n - 1;
        while (i > 0) {
            int parent = (i - 1) / 2;
            if (heap.get(parent).compareTo(heap.get(i)) < 0)
                return;
            Edge tmp = heap.get(parent);
            heap.set(parent, heap.get(i));
            heap.set(i, tmp);
            i = parent;
        }
    }

    private void heapDown() {
        int i = 0;
        int child = 2 * i + 1;
        while (child < n) {
            if (child + 1 < n && heap.get(child + 1).compareTo(heap.get(child)) < 0)
                child++;
            if (heap.get(i).compareTo(heap.get(child)) <= 0)
                return;
            Edge tmp = heap.get(i);
            heap.set(i, heap.get(child));
            heap.set(child, tmp);
            i = child;
            child = 2 * i + 1;
        }
    }
}
