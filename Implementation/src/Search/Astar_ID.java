package Search;
import Util.Graph.*;

import java.util.ArrayList;

import static Util.UtilityMethods.heuristic_cost_estimate;

public class Astar_ID extends SearchAlgorithm {

    private static final double FOUND = -1;
    private double gScores[];
    private double depth;

    public Astar_ID(final Graph graph) {
        super(graph);
    }

    @Override
    protected void startSearch(final Node startNode) {

        depth = heuristic_cost_estimate(startNode);
        do {
            // Reset gScores values
            gScores = new double[graph.nodes.length];
            for (int i = 0; i < gScores.length; i++) {
                gScores[i] = Double.MAX_VALUE;
            }
            gScores[graph.startNode.ID] = 0;

            // Perform depth-limited search
            depth = depthLtdSearch(startNode);
        } while (depth != FOUND && depth != Double.MAX_VALUE);
    }

    private double depthLtdSearch(final Node node) {

        // Calculate fScore
        final double fScore = gScores[node.ID] + heuristic_cost_estimate(node);
        if (fScore > depth) {
            // fScore exceeded the depth
            return fScore;
        } else if (graph.isGoalNode(node)) {
            // A goal node was found
            theReachedGoalNode = node;
            path = new ArrayList<>();
            path.add(theReachedGoalNode);
            return FOUND;
        }

        // Calculate the minimum depth greater than the max depth allowed
        double min = Double.MAX_VALUE;
        for (final Edge e : node.edgesOut) {
            if (gScores[node.ID] + e.val < gScores[e.dst.ID]) {
                gScores[e.dst.ID] = gScores[node.ID] + e.val;
                final double result = depthLtdSearch(e.dst);
                if (result == FOUND) {
                    // Goal node was found
                    prependToPath(node);
                    cost += e.val;
                    return FOUND;
                } else if (result < min) {
                    min = result; // Update minimum
                }
            }
        }
        return min;
    }
}
