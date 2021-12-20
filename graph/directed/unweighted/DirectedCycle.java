
package graph.directed.unweighted;

import java.util.LinkedList;

public class DirectedCycle {

    private final Graph g;
    private boolean marked[];
    private boolean onStack[];
    private int edgeTo[];

    private LinkedList<Integer> cycle;

    public DirectedCycle(Graph g) { this.g = g; }

    public Iterable<Integer> findCycle() { 

        int nv = g.getNV();

        marked = new boolean[nv];
        onStack = new boolean[nv];
        edgeTo = new int[nv];

        for (int v = 0; v < g.getNV(); v++) {
            if (!marked[v]) { dfs(v); }
        }

        return cycle;
    }


    private void dfs(int v) {

        marked[v] = true;
        onStack[v] = true;

        for (int w: g.getAdj(v)) {

            if (cycle != null) { return; }
            else if (!marked[w]) { 
                edgeTo[w] = v;
                dfs(w); 
            }
            else if (onStack[w]) {
                cycle = new LinkedList<>();
                for (int u = v; u != w; u = edgeTo[u]) { cycle.push(u); }
                cycle.push(w);
                cycle.push(v);
            }
        } // for

        onStack[v] = false;
    }

    public static void main(String args[]) {

        Graph g = new Graph(Example.n3, Example.e3);
        System.out.println(new DirectedCycle(g).findCycle());
    }
}
