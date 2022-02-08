package pl.edu.pw.ee;

public class Edge implements Comparable<Edge> {

    private final String firstVertex;
    private final String secondVertex;
    private final int weight;

    Edge(String first, String second, int weight) {
        firstVertex = first;
        secondVertex = second;
        this.weight = weight;
    }

    public String getFirstVertex() {
        return firstVertex;
    }

    public String getSecondVertex() {
        return secondVertex;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public int compareTo(Edge edge) {
        if (edge == null)
            throw new IllegalArgumentException("Input edge cannot be null");
        return this.weight - edge.weight;
    }

    @Override
    public String toString() {
        return firstVertex + "_" + weight + "_" + secondVertex;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null)
            throw new IllegalArgumentException("Input object cannot be null");
        String oFirstVertex = ((Edge) o).getFirstVertex();
        String oSecondVertex = ((Edge) o).getSecondVertex();
        return (o instanceof Edge && ((firstVertex.equals(oFirstVertex) && secondVertex.equals(oSecondVertex))
                || (firstVertex.equals(oSecondVertex) && secondVertex.equals(oFirstVertex))));
    }

    @Override
    public int hashCode() {
        return 9 * firstVertex.hashCode() * secondVertex.hashCode();
    }
}
