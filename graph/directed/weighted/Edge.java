
package graph.directed.weighted;


public class Edge {

    private final int v1, v2;
    private final double w; // weight

    public Edge(int from, int to, double weight) {

        v1 = from;
        v2 = to;
        w  = weight;
    }

    public int getFrom() { return v1; }
    public int getTo() { return v2; }

    public double getWeight() { return w; }

    @Override
    public String toString() { return String.format("%d -> %d  %.2f", v1, v2, w); }
}
