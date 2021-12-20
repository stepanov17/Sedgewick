
package graph.directed.weighted;

import java.util.LinkedList;

public class TopologicalSort {

    private Graph g;
    private boolean marked[];
    private LinkedList<Integer> order;

    private void reversePostOrder() {

        int nv = g.getNV();
        marked = new boolean[nv];
        order = new LinkedList<>();

        for (int v = 0; v < nv; ++v) {
            if (!marked[v]) { dfs(v); }
        }
    }

    private void dfs(int v) {

        marked[v] = true;

        for (Edge e: g.getAdj(v)) {
            int w = e.getTo();
            if (!marked[w]) { dfs(w); }
        }

        order.push(v);
    }


    public void sort(Graph g) {

        // cannot be sorted if a cycle exists
        if (new DirectedCycle(g).findCycle() != null) {
            System.err.println("a cycle was found");
            return;
        }

        this.g = g;
        reversePostOrder();
    }

    public Iterable<Integer> getOrder() { return order; }

    public boolean isAcyclic() { return (order != null); }

    public static void main(String[] args) {

        Graph g = new Graph(Example.n4, Example.e4);

        TopologicalSort ts = new TopologicalSort();
        ts.sort(g);
        Iterable<Integer> order = ts.getOrder();
        if (order == null) { return; }
        System.out.println("reordering:");
        int i = 0;
        for (int v: order) { System.out.println(v + " -> " + i++); }
    }
}
