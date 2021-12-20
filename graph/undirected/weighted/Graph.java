
package graph.undirected.weighted;

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

            int v1 = (int) e[i][0];
            int v2 = (int) e[i][1];
            double w = e[i][2];

            adj.get(v1).add(new Edge(v1, v2, w));
            adj.get(v2).add(new Edge(v2, v1, w));
        }
    }

    @Override
    public String toString() {

        String sep = System.lineSeparator();

        StringBuffer sb = new StringBuffer();

        sb.append(nv).append(sep);
        for (int v = 0; v < nv; ++v) {

            sb.append(v).append(":   ");

            for (Edge e: adj.get(v)) {
                sb.append(e).append(";  ");
            }
            sb.append(sep);
        }

        return sb.toString();
    };

    public int getNV() { return nv; }
    public int getNE() { return ne; }

    public HashSet<Edge> getAdj(int v) { return adj.get(v); }

    public static void main(String args[]) {

        System.out.println(new Graph(Example.n2, Example.e2));
    }
}
