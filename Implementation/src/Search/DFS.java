package Search;

import Util.Graph.*;

import java.util.ArrayList;

public class DFS extends SearchAlgorithm {

    public DFS(final Graph graph) {
        super(graph);
    }

    @Override
    protected void preSearch() {
        graph.startNode.visited = true;
    }

    @Override
    protected void startSearch(final Node startNode) {

        if (graph.isGoalNode(startNode)) {
            theReachedGoalNode = startNode;
            path = new ArrayList<>();
            path.add(theReachedGoalNode);
            return;
        }

        for (final Edge e : startNode.edgesOut) {
            final Node n = e.dst;
            if (!n.visited) {
                n.visited = true;
                startSearch(n);
                if (goalNodeFound()) {
                    prependToPath(startNode);
                    cost += e.val;
                    return;
                }
            }
        }
    }
}
