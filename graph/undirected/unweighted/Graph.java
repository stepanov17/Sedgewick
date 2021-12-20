
package graph.undirected.unweighted;

import java.util.ArrayList;
import java.util.HashSet;

public class Graph {

    private final int nv;
    private final int ne;

    private final ArrayList<HashSet<Integer>> adj;

    public Graph(int nv, int e[][]) {

        this.nv = nv;

        adj = new ArrayList<>();
        for (int i = 0; i < nv; i++) { adj.add(new HashSet<>()); }

        this.ne = e.length;
        for (int i = 0; i < ne; i++) {

            if (e[i].length != 2) { throw new RuntimeException("invalid e[][]"); }

            int v1 = e[i][0], v2 = e[i][1];
            adj.get(v1).add(v2);
            adj.get(v2).add(v1);
        }
    }

    public int getNV() { return nv; }
    public int getNE() { return ne; }

    public Iterable<Integer> getAdj(int v) { return adj.get(v); }

    public static void main(String args[]) {

        Graph g = new Graph(Example.n1, Example.e1);
        System.out.println(g.adj);
    }
}
