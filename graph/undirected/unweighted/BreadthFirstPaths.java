package graph.undirected.unweighted;

import java.util.LinkedList;

public class BreadthFirstPaths {

    private final Graph g;
    private final int   v0;

    private boolean marked[];
    private int     edgeTo[];

    public BreadthFirstPaths(Graph g, int v0) {

        this.g  = g;
        this.v0 = v0;
    }

    public void traverse() { 

        marked = new boolean[g.getNV()];
        edgeTo = new int[g.getNV()];

        bfs(v0);
    }

    // gives shortest path
    private void bfs(int v) {

        LinkedList<Integer> list = new LinkedList<>();

        // mark and enqueue the initial v
        marked[v] = true;
        list.addLast(v);

        while (!list.isEmpty()) {

            int u = list.removeFirst(); // take next vertex from the queue
            for (int w: g.getAdj(u)) {
                if (!marked[w]) {      // for every unmarked adjacent vertex
                    edgeTo[w] = u;     // add to path
                    marked[w] = true;
                    list.addLast(w);   // enqueue
                }
            }
        }
    }

    public boolean isReachable(int v) { return marked[v]; }

    public Iterable<Integer> pathTo(int v) {

        if (!isReachable(v)) { return null; }
        LinkedList<Integer> path = new LinkedList<>();
        for (int w = v; w != v0; w = edgeTo[w]) { path.addFirst(w); }
        path.addFirst(v0);
        return path;
    }

    public static void main(String args[]) {

        Graph g = new Graph(Example.n3, Example.e3);
        BreadthFirstPaths dfp = new BreadthFirstPaths(g, 0);
        dfp.traverse();

        System.out.println(dfp.pathTo(8));
        System.out.println(dfp.pathTo(13));
        System.out.println(dfp.pathTo(16));
    }
}
