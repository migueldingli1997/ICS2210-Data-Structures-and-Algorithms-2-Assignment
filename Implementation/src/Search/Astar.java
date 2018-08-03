package Search;

import Util.Graph.*;
import Util.Tuples.PredecessorCostPair;
import Util.Tuples.PathCostPair;
import Util.PriorityQueue;
import Util.UtilityMethods;

import java.util.*;

import static Util.UtilityMethods.heuristic_cost_estimate;

public class Astar extends SearchAlgorithm {

    private PredecessorCostPair predCostPairs[];
    private PriorityQueue<Node> openSet;
    private double gScore[];
    private double fScore[];

    public Astar(final Graph graph) {
        super(graph);
    }

    @Override
    protected void preSearch() {

        // Initializing predecessor-cost-pair array
        predCostPairs = new PredecessorCostPair[graph.nodes.length];
        for (int i = 0; i < predCostPairs.length; i++) {
            predCostPairs[i] = null;
        }

        // Initialize openSet queue
        openSet = new PriorityQueue<>(new FScoreComparator());
        openSet.add(graph.startNode);

        // Initialize gScore and fScore arrays
        gScore = new double[graph.nodes.length];
        fScore = new double[graph.nodes.length];
        for (int i = 0; i < graph.nodes.length; i++) {
            gScore[i] = Double.MAX_VALUE;
            fScore[i] = Double.MAX_VALUE;
        }
        gScore[graph.startNode.ID] = 0; // Cost of going from start to start is zero.
        fScore[graph.startNode.ID] = heuristic_cost_estimate(graph.startNode); // For first node, value is completely heuristic.
    }

    @Override
    protected void startSearch(final Node startNode) {

        Node node;
        while (!openSet.isEmpty()) {

            // Get node in openSet having the lowest fScore
            node = openSet.getHighestPriority();
            if (graph.isGoalNode(node)) {
                theReachedGoalNode = node;
                return;
            }

            // Remove node and set as visited
            openSet.remove(node);
            node.visited = true;

            // Traverse adjacent nodes
            for (final Edge e : node.edgesOut) {
                final Node n = e.dst;
                if (!n.visited) {
                    // Distance from start to the adjacent node
                    double tentative_gScore = gScore[node.ID] + e.val;
                    if (!openSet.contains(n)) {
                        openSet.add(n); // New node discovered
                    } else if (tentative_gScore >= gScore[n.ID]) {
                        continue; // Not a better path
                    }

                    // Until now, this path is the best, so it is stored
                    predCostPairs[n.ID] = new PredecessorCostPair(node, e.val);
                    gScore[n.ID] = tentative_gScore;
                    fScore[n.ID] = gScore[n.ID] + heuristic_cost_estimate(n);
                }
            }
        }
    }

    @Override
    protected void postSearch() {

        final PathCostPair pathCost = UtilityMethods.toPathCostPair(
                predCostPairs, theReachedGoalNode);
        path = pathCost.path;
        cost = pathCost.cost;
    }

    private class FScoreComparator implements Comparator<Node> {
        @Override
        public int compare(final Node n1, final Node n2) {
            // Result inverted since smaller distance is higher priority
            return -Double.compare(fScore[n1.ID], fScore[n2.ID]);
        }
    }
}
