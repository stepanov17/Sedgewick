
package graph.directed.weighted;

import java.util.ArrayList;
import java.util.HashSet;

public class Graph {

    private final int nv;
    private final int ne;

    private final ArrayList<HashSet<Edge>> adj;

    public Graph(int nv, double e[][]) { // ugly, but simple

        this.nv = nv;

        adj = new ArrayList<>();
        for (int i = 0; i < nv; i++) { adj.add(new HashSet<>()); }

        this.ne = e.length;
        for (int i = 0; i < ne; i++) {

            if (e[i].length != 3) { throw new RuntimeException("invalid e[][]"); }

            int from = (int) e[i][0];
            int to   = (int) e[i][1];
            double weight  = e[i][2];

            adj.get(from).add(new Edge(from, to, weight));
        }
    }

    public Graph getReversed() {

        double eRev[][] = new double[ne][3];

        int i = 0;
        for (int v1 = 0; v1 < nv; ++v1) {
            for (Edge e: adj.get(v1)) {

                eRev[i][0] = e.getFrom(); // v1
                eRev[i][1] = e.getTo();
                eRev[i][2] = e.getWeight();
                ++i;
            }
        }

        return new Graph(nv, eRev);
    }


    public int getNV() { return nv; }

    public Iterable<Edge> getAdj(int v) { return adj.get(v); }


    public static void main(String[] args) {

        Graph g = new Graph(Example.n1, Example.e1);
        for (int v = 0; v < g.getNV(); ++v) {
            System.out.println(g.getAdj(v));
        }
    }
}


