
package graph.directed.unweighted;

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
        }
    }

    public Graph getReversed() {

        int eRev[][] = new int[ne][2];

        int i = 0;
        for (int v1 = 0; v1 < nv; ++v1) {
            for (int v2: adj.get(v1)) {

                eRev[i][0] = v2;
                eRev[i][1] = v1;
                ++i;
            }
        }

        return new Graph(nv, eRev);
    }

    public int getNV() { return nv; }
    public int getNE() { return ne; }

    public Iterable<Integer> getAdj(int v) { return adj.get(v); }


    public static void main(String[] args) {

        Graph g = new Graph(Example.n4, Example.e4);
        System.out.println(g.adj);

        Graph rg = g.getReversed();
        System.out.println(rg.adj);
    }
}
