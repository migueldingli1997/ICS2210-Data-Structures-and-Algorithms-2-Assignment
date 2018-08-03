package Search;

import Util.*;
import Util.Graph.*;
import Util.Tuples.*;

import java.util.Arrays;
import java.util.Comparator;

public class UCS extends SearchAlgorithm {

    private PredecessorCostPair predCostPairs[];
    private PriorityQueue<Node> nodeQueue;
    private double dist[];

    public UCS(final Graph graph) {
        super(graph);
    }

    @Override
    protected void preSearch() {

        // Initializing predecessor-cost-pair array
        predCostPairs = new PredecessorCostPair[graph.nodes.length];
        for (int i = 0; i < predCostPairs.length; i++) {
            predCostPairs[i] = null;
        }

        // Initialize node queue
        nodeQueue = new PriorityQueue<>(new DistanceComparator());
        nodeQueue.addAll(Arrays.asList(graph.nodes));

        // Initializing distances array
        dist = new double[graph.nodes.length];
        for (int i = 0; i < dist.length; i++) {
            dist[i] = Double.MAX_VALUE;
        }
        dist[graph.startNode.ID] = 0;
    }

    @Override
    protected void startSearch(final Node startNode) {

        if (graph.isGoalNode(startNode)) {
            theReachedGoalNode = startNode;
            return;
        }

        while (nodeQueue.size() != 0) {
            final Node head = nodeQueue.getHighestPriority();
            head.visited = true;
            for (final Edge e : head.edgesOut) {
                final Node n = e.dst;
                if (!n.visited && (dist[n.ID] > dist[head.ID] + e.val)) {
                    predCostPairs[n.ID] = new PredecessorCostPair(head, e.val);
                    dist[n.ID] = dist[head.ID] + e.val;
                    if (graph.isGoalNode(n) && (!goalNodeFound() || dist[n.ID] < dist[theReachedGoalNode.ID])) {
                        theReachedGoalNode = n;
                    }
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

    private class DistanceComparator implements Comparator<Node> {
        @Override
        public int compare(final Node n1, final Node n2) {
            // Result inverted since smaller distance is higher priority
            return -Double.compare(dist[n1.ID], dist[n2.ID]);
        }
    }
}
