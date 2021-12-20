
package graph.directed.weighted;

import java.util.LinkedList;

public class AcyclicPaths {

    private Edge edges[];
    private double dist[];

    private final Graph g;

    public AcyclicPaths(Graph g) { this.g = g; }

    private void findShortestOrLongest(int s, boolean shortest) {

        int nv = g.getNV();

        edges = new Edge[nv];
        dist  = new double[nv];

        for (int v = 0; v < nv; ++v) {
            dist[v] = (v == s) ? 0 :
                    (shortest ? Double.MAX_VALUE : Double.MIN_VALUE);
        }

        TopologicalSort ts = new TopologicalSort();
        ts.sort(g);
        Iterable<Integer> order = ts.getOrder();
        if (order == null) {
            System.err.println("not acyclic");
            return;
        }

        for (int v: order) {

            for (Edge e: g.getAdj(v)) { relax(e, shortest); }
        }
    }

    public void findShortest(int s) { findShortestOrLongest(s, true); }

    public void findLongest(int s) { findShortestOrLongest(s, false); }

    private void relax(Edge e, boolean shortest) {

        int v = e.getFrom(), w = e.getTo();

        if ( (shortest && dist[w] > dist[v] + e.getWeight()) ||
            (!shortest && dist[w] < dist[v] + e.getWeight()) ) 
        {

            dist[w] = dist[v] + e.getWeight();
            edges[w] = e;
        }
    }

    public double distTo(int v) { return dist[v]; }

    public boolean hasPathTo(int v) { return distTo(v) < Double.POSITIVE_INFINITY; }

    public Iterable<Integer> pathTo(int v) {

        if (!hasPathTo(v)) { return null; }

        LinkedList<Integer> path = new LinkedList<>();
        for (Edge e = edges[v]; e != null; e = edges[e.getFrom()]) {
            path.addFirst(e.getFrom());
        }
        return path;
    }

    public static void main(String args[]) {

        Graph g = new Graph(Example.n5, Example.e5);
        AcyclicPaths p = new AcyclicPaths(g);

        System.out.println(">> shortest");
        int v0 = 5;
        p.findShortest(v0);

        for (int v = 0; v < g.getNV(); ++v) {

            if (v == v0) { continue; }

            System.out.print(v + ":  " + p.pathTo(v));
            System.out.printf(", d = %.3f", p.distTo(v));
            System.out.println("");
        }

        System.out.println(">> longest");
        p.findLongest(v0);

        for (int v = 0; v < g.getNV(); ++v) {

            if (v == v0) { continue; }

            System.out.print(v + ":  " + p.pathTo(v));
            System.out.printf(", d = %.3f", p.distTo(v));
            System.out.println("");
        }
    }
}
