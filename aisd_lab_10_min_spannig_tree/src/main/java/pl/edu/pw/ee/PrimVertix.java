package pl.edu.pw.ee;

import java.util.ArrayList;
import java.util.List;

public class PrimVertix {
    private boolean visited;
    private List<Edge> edges;

    PrimVertix() {
        edges = new ArrayList<>();
        visited = false;
    }

    public void setVisited() {
        visited = true;
    }

    public boolean getVisited() {
        return visited;
    }

    public List<Edge> getEdges() {
        return edges;
    }

}
