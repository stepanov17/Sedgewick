
package graph.undirected.weighted;

// minimum spanning tree

import java.util.ArrayList;
import java.util.PriorityQueue;

// lazy Prim
public class MST {

    private final Graph g;

    private boolean marked[];
    private PriorityQueue<Edge> pq; // crossing and ineligible

    private ArrayList<Edge> mst;

    private double weight; // MST weight

    public MST(Graph g) { this.g = g; }

    public void findMST() {

        marked = new boolean[g.getNV()];
        pq = new PriorityQueue<>();
        mst = new ArrayList<>();

        visit(0);

        while (pq.size() > 0) {

            Edge e = pq.poll();
            int v = e.getAny();
            int w = e.getOther(v);

            if (marked[v] && marked[w]) { continue; } // skip ineligible

            mst.add(e);

            weight += e.getWeight();

            if (!marked[v]) { visit(v); }
            if (!marked[w]) { visit(w); }
        }
    }

    private void visit(int v) {

        marked[v] = true;
        for (Edge e: g.getAdj(v)) {
            if (!marked[e.getOther(v)]) { pq.add(e); }
        }
    }

    public Iterable<Edge> getEdges() { return mst; }

    public double getWeight() { return weight; }

    public static void main(String args[]) {

        Graph g = new Graph(Example.n2, Example.e2);
        MST mst = new MST(g);

        mst.findMST();

        for (Edge e: mst.getEdges()) { System.out.println(e); }
        System.out.println("w = " + mst.getWeight());
    }
}
