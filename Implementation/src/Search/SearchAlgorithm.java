package Search;
import Util.Graph.*;

import java.util.List;

public abstract class SearchAlgorithm {

    final Graph graph;
    double cost = 0.0;
    public List<Node> path = null;
    Node theReachedGoalNode = null;

    SearchAlgorithm(final Graph graph) {
        this.graph = graph;
    }

    public void performSearch() {

        preSearch();
        startSearch(graph.startNode);
        postSearch();

        // Reset visited nodes
        graph.resetVisitedNodes();
    }

    void prependToPath(final Node node) {
        path.add(0, node);
    }

    boolean goalNodeFound() {
        return theReachedGoalNode != null;
    }

    /**
     * This method will be called before a search is performed.
     * It is intended to be overridden by subclasses to add
     * behaviour before the startSearch method is called.
     */
    protected void preSearch() {}

    /**
     *
     * @param startNode
     */
    protected abstract void startSearch(final Node startNode);

    /**
     * This method will be called after a search is finished.
     * It is intended to be overridden by subclasses to add
     * behaviour after the startSearch method is called.
     */
    protected void postSearch() {}

    public void outputResults() {

        // Output results
        System.out.println(this.getClass().getSimpleName() + ":");
        System.out.println("\tPath: " + (goalNodeFound() ? path.toString() : "(Goal node not found)"));
        System.out.println("\tCost: " + (goalNodeFound() ? cost : "(Goal node not found)"));
    }
}
