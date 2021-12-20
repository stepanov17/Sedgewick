
package graph.directed.unweighted;

import java.util.HashSet;

public class DepthFirstSearch {

    private final Graph g;
    private boolean marked[];

    public DepthFirstSearch(Graph g) { this.g = g; }

    public void traverse(int v0) { 

        marked = new boolean[g.getNV()];
        dfs(v0);
    }

    private void dfs(int v) {

        marked[v] = true;

        for (int w: g.getAdj(v)) {
            if (!marked[w]) { dfs(w); }
        }
    }

    public boolean isReachable(int v) { return marked[v]; }

    public Iterable<Integer> getReachable() {

        HashSet<Integer> r = new HashSet<>();
        for (int i = 0; i < g.getNV(); i++) {
            if (marked[i]) { r.add(i); }
        }
        return r;
    }

    public static void main(String[] args) {

        Graph g = new Graph(Example.n1, Example.e1);

        DepthFirstSearch dfs = new DepthFirstSearch(g);

        int V0[] = {0, 7, 9};
        for (int v0: V0) {

            dfs.traverse(v0);

            System.out.println(v0 + " -> " + dfs.getReachable());
        }
    }
}
