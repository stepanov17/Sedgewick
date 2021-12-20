
package graph.directed.weighted;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


// https://www.baeldung.com/java-dijkstra
public class Dijkstra {

    private final Graph g;

    private double dist[];
    private List<List<Integer>> path; // shortest paths

    private final int s; // start

    Set<Integer> settled, unsettled;

    public Dijkstra(Graph g, int s) {

        this.g = g;
        checkWeights();
        this.s = s;
    }

    private void checkWeights() {

        for (int v = 0; v < g.getNV(); ++v) {
            for (Edge e: g.getAdj(v)) {
                if (e.getWeight() < 0) {
                    int to = e.getTo();
                    throw new IllegalArgumentException(
                            v + " -> " + to + ": negative edge weight");
                }
            }
        }
    }


    public void calculateShortestPaths() {

        // init
        int nv = g.getNV();

        dist = new double[nv];
        path = new ArrayList<>(nv);

        for (int v = 0; v < nv; ++v) {

            dist[v] = (v == s) ? 0. : Double.MAX_VALUE;
            path.add(new LinkedList<>());
        }

        settled = new HashSet<>();
        unsettled = new HashSet<>();

        unsettled.add(s);

        while (!unsettled.isEmpty()) {

            int current = getNearest();
            unsettled.remove(current);

            for (Edge e: g.getAdj(current)) {
                int to = e.getTo();
                double w = e.getWeight();
                if (!settled.contains(to)) {
                    relax(current, to, w);
                    unsettled.add(to);
                }
            }
            settled.add(current);
        }

        for (int v = 0; v < g.getNV(); ++v) {
            if (v == s) { continue; }
            System.out.println(v + ": " + path.get(v) + ", d = " + dist[v]);
        }
    }

    private int getNearest() {

        int nearest = -1;

        double minD = Double.MAX_VALUE;
        for (int v: unsettled) {
            double d = dist[v];
            if (d < minD) {
                minD = d;
                nearest = v;
            }
        }

        if (nearest < 0) { throw new AssertionError("nearest not found"); }
        return nearest;
    }

    private void relax(int from, int to, double w) {

        double d = dist[from] + w;
        if (d < dist[to]) {
            dist[to] = d;
            List<Integer> p = new LinkedList<>(path.get(from));
            p.add(from);
            path.set(to, p);
        }
    }

    public static void main(String[] args) {

        Graph g = new Graph(Example.n2, Example.e2);
        new Dijkstra(g, 0).calculateShortestPaths();
    }
}
