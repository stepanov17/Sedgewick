
package graph.undirected.weighted;

public class Edge implements Comparable<Edge> {

    private final int v1;
    private final int v2;
    private final double w; // weight

    public Edge(int v1, int v2, double w) {

        this.v1 = v1;
        this.v2 = v2;
        this.w  = w;
    }

    public int getAny() { return v1; }

    public int getOther(int v) {

        if (v == v1) { return v2; }
        else if (v == v2) { return v1; }
        else { throw new RuntimeException("wrong edge"); }
    }

    public double getWeight() { return w; }

    @Override
    public int compareTo(Edge o) {

        if (w < o.w) { return -1; }
        if (w > o.w) { return 1; }
        return 0;
    }

    @Override
    public String toString() { return String.format("%d  %d  %.2f", v1, v2, w); }
}
