package graph.undirected.unweighted;

import java.util.LinkedList;

public class DepthFirstPaths {

    private final Graph g;
    private final int   v0;

    private boolean marked[];
    private int     edgeTo[];

    public DepthFirstPaths(Graph g, int v0) {

        this.g  = g;
        this.v0 = v0;
    }

    public void traversal() {

        marked = new boolean[g.getNV()];
        edgeTo = new int[g.getNV()];

        dfs(v0);
    }

    private void dfs(int v) {
 
        marked[v] = true;
        for (int w: g.getAdj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(w);
            }
        }
    }

    public boolean isReachable(int v) { return marked[v]; }

    // may not be the shortest!
    public Iterable<Integer> pathTo(int v) {

        if (!isReachable(v)) { return null; }
        LinkedList<Integer> path = new LinkedList<>();
        for (int w = v; w != v0; w = edgeTo[w]) { path.addFirst(w); }
        path.addFirst(v0);
        return path;
    }


    public static void main(String args[]) {

        Graph g = new Graph(Example.n3, Example.e3);
        DepthFirstPaths dfp = new DepthFirstPaths(g, 0);
        dfp.traversal();

        System.out.println(dfp.pathTo(8));
        System.out.println(dfp.pathTo(13));
        System.out.println(dfp.pathTo(16));
    }
}
