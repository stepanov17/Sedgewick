package graph.undirected.unweighted;

public class DepthFirstSearch {

    private final Graph g;

    private boolean marked[];
    private int count;

    public DepthFirstSearch(Graph g) { this.g = g; }

    public void traversal(int v0) {

        marked = new boolean[g.getNV()];
        dfs(v0);
    }

    private void dfs(int v) {

        marked[v] = true;
        ++count;
        for (int w: g.getAdj(v)) {
            if (!marked[w]) { dfs(w); }
        }
    }

    public boolean isMarked(int v) { return marked[v]; }
    public int getCount() { return count; }


    public static void main(String args[]) {

        Graph g = new Graph(Example.n1, Example.e1);
        DepthFirstSearch dfs = new DepthFirstSearch(g);
        dfs.traversal(0);
        System.out.println(dfs.getCount() + " of " + Example.n1);
    }
}
