import Search.*;
import Util.Graph.*;

import java.util.Arrays;
import java.util.List;

public class AdmissibilityTest {

    private static int count = 0;

    public static void main(String[] args) {

        final int ITERATIONS = 100000;
        for (count = 0; count < ITERATIONS; count++) {
            if (!run()) {
                return;
            }
        }
        System.out.println("All " + count + " runs were successful!");
    }

    private static boolean run() {

        // Run searches using UCS, Astar, Astar_ID
        final Graph g = new Graph();
        final SearchAlgorithm sa[] = { new UCS(g), new Astar(g), new Astar_ID(g) };
        for (final SearchAlgorithm a : sa) {
            a.performSearch();
        }

        // Check that none of the paths are null and that their length match
        if (sa[0].path != null && sa[1].path != null && sa[2].path != null &&
                sa[0].path.size() == sa[1].path.size() &&
                sa[0].path.size() == sa[2].path.size()) {
            // Compare all nodes of each path
            for (int i = 0; i < sa[0].path.size(); i++) {
                if (sa[0].path.get(i).ID != sa[1].path.get(i).ID ||
                        sa[0].path.get(i).ID != sa[2].path.get(i).ID) {
                    // Failed (one node is not equal to the others)
                    g.outputDetails();
                    Arrays.asList(sa).forEach(SearchAlgorithm::outputResults);
                    System.out.println("Failed (type 1 fail) at run " + count);
                    return false;
                }
            }
            return true; // Successful
        } else if (sa[0].path == null && sa[1].path == null && sa[2].path == null) {
            return true; // Successful
        } else {
            // Failed
            g.outputDetails();
            Arrays.asList(sa).forEach(SearchAlgorithm::outputResults);
            System.out.println("Failed (type 2 fail) at run " + count);
            return false;
        }
    }
}
