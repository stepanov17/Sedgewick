
package graph.directed.unweighted;

import java.util.LinkedList;


public class KosarajuSCC {

    private boolean marked[];
    private int SCCIds[];
    private int nSCC;

    private final Graph g, gRev;
    private final int nv;
    private LinkedList<Integer> orderRev;

    public KosarajuSCC(Graph g) {

        this.g = g;
        nv = g.getNV();
        gRev = g.getReversed();
    }

    private void orderRev() {

        marked = new boolean[nv];
        orderRev = new LinkedList<>();
        for (int v = 0; v < nv; ++v) {
            if (!marked[v]) { dfsRev(v); }
        }
    }

    private void dfsRev(int v) {

        marked[v] = true;

        for (int w: gRev.getAdj(v)) {
            if (!marked[w]) { dfsRev(w); }
        }
        orderRev.push(v);
    }

    public void findSCC() {

        orderRev();

        marked = new boolean[nv]; // reuse
        SCCIds = new int[nv];
        for (int i = 0; i < nv; ++i) { SCCIds[i] = -1; }

        for (int s: orderRev) {
            if (!marked[s]) {
                dfs(s);
                ++nSCC;
            }
        }

        // reorder
        for (int i = 0; i < SCCIds.length; ++i) { SCCIds[i] = nSCC - 1 - SCCIds[i]; }
    }

    private void dfs(int v) {

        marked[v] = true;
        SCCIds[v] = nSCC;

        for (int w: g.getAdj(v)) {
            if (!marked[w]) { dfs(w); }
        }
    }

    public boolean isSC(int v, int w) { return SCCIds[v] == SCCIds[w]; }

    public int getSCCId(int v) { return SCCIds[v]; }

    public int getNSCC() { return nSCC; }


    public static void main(String args[]) {

        Graph g = new Graph(Example.n6, Example.e6);
        KosarajuSCC scc = new KosarajuSCC(g);
        scc.findSCC();
        System.out.println("nSCC = " + scc.getNSCC());

        for (int v = 0; v < Example.n6; ++v) {
            System.out.println(v + ": " + scc.getSCCId(v));
        }
    }
}
