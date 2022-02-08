package pl.edu.pw.ee;

import java.util.ArrayList;

public class PrimGraph {

    private RBTMap<String, PrimVertix> vertices;

    PrimGraph() {
        vertices = new RBTMap<>();
    }

    public void addEdge(String firstVertix, String secondVertix, int weight) {
        if (firstVertix == null || secondVertix == null)
            throw new IllegalArgumentException("Input strings cannot be null");
        if (vertices.getValue(firstVertix) == null)
            vertices.setValue(firstVertix, new PrimVertix());
        if (vertices.getValue(secondVertix) == null)
            vertices.setValue(secondVertix, new PrimVertix());
        Edge edgeForFirstVertix = new Edge(firstVertix, secondVertix, weight);
        Edge edgeForSecondVertix = new Edge(secondVertix, firstVertix, weight);
        ArrayList<Edge> firstVertixsEdges = (ArrayList<Edge>) vertices.getValue(firstVertix).getEdges();
        ArrayList<Edge> secondVertixsEdges = (ArrayList<Edge>) vertices.getValue(secondVertix).getEdges();
        if (!firstVertixsEdges.contains(edgeForFirstVertix))
            firstVertixsEdges.add(edgeForFirstVertix);
        if (!secondVertixsEdges.contains(edgeForSecondVertix))
            secondVertixsEdges.add(edgeForSecondVertix);
    }

    public PrimVertix getVertix(String label) {
        return vertices.getValue(label);
    }

    public PrimVertix getRootVertix() {
        return vertices.getRoot();
    }

    public boolean checkConnection() {
        return vertices.checkConnectionPrim();
    }
}
