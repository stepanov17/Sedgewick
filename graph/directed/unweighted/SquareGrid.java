
package graph.directed.unweighted;

import java.util.concurrent.ThreadLocalRandom;


public class SquareGrid {

    // generate k * k grid graph with random edge directions
    public static Graph generateRandom(int k) {

        if (k < 2) {
            throw new IllegalArgumentException("k < 2");
        }

        ThreadLocalRandom rnd = ThreadLocalRandom.current();

        int ne = 2 * (k - 1) * k;

        int e[][] = new int[ne][2]; // edges

        int ie = 0;

        for (int i = 0; i < k; ++i) {

            for (int j = 0; j < k; ++j) {

                int v1 = i * k + j;

                if (i < k - 1) { // add "to bottom" edbe

                    int j1 = rnd.nextBoolean() ? 0 : 1;
                    e[ie][j1] = v1;
                    e[ie][1 - j1] = v1 + k;
                    ++ie;
                }

                if (j < k - 1) { // add "to right" edge

                    int j1 = rnd.nextBoolean() ? 0 : 1;
                    e[ie][j1] = v1;
                    e[ie][1 - j1] = v1 + 1;
                    ++ie;
                }
            }
        }

        return new Graph(k * k, e);
    }

    public static void main(String args[]) {

        int k = 5;

        int nSim = 100_000;
        int e = 0;

        for (int i = 0; i < nSim; ++i) {

            Graph g = SquareGrid.generateRandom(k);
            BreadthFirstPaths dfp = new BreadthFirstPaths(g, 0);
            boolean pathExists = (dfp.pathTo(g.getNV() - 1) != null);

            System.out.println(">> " + i + ": " + pathExists);

            if (pathExists) { ++e; }
        }

        double p = ((double) e) / nSim;
        System.out.printf("\np ~ %.3f\n", p);
    }
}
