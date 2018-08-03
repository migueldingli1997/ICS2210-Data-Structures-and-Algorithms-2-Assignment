package Search;

import Util.Graph.*;
import Util.Tuples.PredecessorCostPair;
import Util.Tuples.PathCostPair;
import Util.UtilityMethods;

import java.util.ArrayList;
import java.util.List;

public class BFS extends SearchAlgorithm {

    private PredecessorCostPair predCostPairs[];
    private List<Node> nodeList;

    public BFS(final Graph graph) {
        super(graph);
    }

    @Override
    protected void preSearch() {

        // Initializing predecessor-cost-pair array
        predCostPairs = new PredecessorCostPair[graph.nodes.length];
        for (int i = 0; i < predCostPairs.length; i++) {
            predCostPairs[i] = null;
        }

        // Initializing the node list
        nodeList = new ArrayList<>();
        nodeList.add(graph.startNode);
        graph.startNode.visited = true;
    }

    @Override
    protected void startSearch(final Node startNode) {

        if (graph.isGoalNode(startNode)) {
            theReachedGoalNode = startNode;
            return;
        }

        while (!nodeList.isEmpty()) {
            final Node head = nodeList.remove(0);
            for (final Edge e : head.edgesOut) {
                final Node n = e.dst;
                if (!n.visited) {
                    predCostPairs[n.ID] = new PredecessorCostPair(head, e.val);
                    if (graph.isGoalNode(n)) {
                        theReachedGoalNode = n;
                        return;
                    }
                    nodeList.add(n);
                    n.visited = true;
                }
            }
        }
    }

    @Override
    protected void postSearch() {

        final PathCostPair pathCost = UtilityMethods.toPathCostPair(predCostPairs, theReachedGoalNode);
        path = pathCost.path;
        cost = pathCost.cost;
    }
}
