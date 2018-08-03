package Search;

import Util.Graph.*;

import java.util.ArrayList;

public class IDS extends SearchAlgorithm {

    public IDS(final Graph graph) {
        super(graph);
    }

    @Override
    protected void startSearch(final Node startNode) {

        for (int depth = 0; depth < graph.nodes.length; depth++) {
            startNode.visited = true;
            depthLtdSearch(startNode, depth); // depth-limited search
            if (goalNodeFound()) {
                break;
            }
            graph.resetVisitedNodes();
        }
    }

    private void depthLtdSearch(final Node start, final int depth) {

        if (depth == 0 && graph.isGoalNode(start)) {
            theReachedGoalNode = start;
            path = new ArrayList<>();
            path.add(theReachedGoalNode);
        } else if (depth > 0) {
            for (final Edge e : start.edgesOut) {
                final Node n = e.dst;
                if (!n.visited) {
                    n.visited = true;
                    depthLtdSearch(n, depth - 1);
                    if (goalNodeFound()) {
                        prependToPath(start);
                        cost += e.val;
                        return;
                    }
                }
            }
        }
    }
}
