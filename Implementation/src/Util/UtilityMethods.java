package Util;

import Util.Graph.Edge;
import Util.Graph.Node;
import Util.Tuples.PredecessorCostPair;
import Util.Tuples.PathCostPair;

import java.util.ArrayList;
import java.util.List;

public class UtilityMethods {

    public static int randInt(final int from, final int to) {
        return (int) (Math.random() * (1 + to - from)) + from;
    }

    public static PathCostPair toPathCostPair(final PredecessorCostPair predCostPairs[], final Node goalNode) {

        List<Node> path = new ArrayList<>();
        double cost = 0;

        if (goalNode != null) {
            path.add(goalNode);
            Node node = goalNode;
            while (predCostPairs[node.ID] != null) {
                final PredecessorCostPair pcPair = predCostPairs[node.ID];
                cost += pcPair.cost;
                path.add(0, pcPair.pred);
                node = pcPair.pred;
            }
        } else {
            path = null;
        }
        return new PathCostPair(path, cost);
    }

    public static double heuristic_cost_estimate(final Node node) {
        return 0;
    }
}
