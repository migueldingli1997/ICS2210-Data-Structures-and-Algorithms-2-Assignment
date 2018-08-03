import Search.*;
import Util.Graph.*;

public class Main {

    public static void main(String[] args) {

        // Instantiate graph and algorithms
        System.out.println("******************** INITIALIZING GRAPH AND ALGORITHMS");
        final Graph graph = new Graph();
        final SearchAlgorithm algorithms[] = {
                new DFS(graph),
                new BFS(graph),
                new IDS(graph),
                new UCS(graph),
                new Astar(graph),
                new Astar_ID(graph)
        };

        // Print graph details
        graph.outputDetails();

        // Run searches and print results
        System.out.println("******************** STARTING SEARCHES");
        for (final SearchAlgorithm alg : algorithms) {
            alg.performSearch();
            alg.outputResults();
        }
        System.out.println("******************** FINISHED SEARCHES");
    }
}
