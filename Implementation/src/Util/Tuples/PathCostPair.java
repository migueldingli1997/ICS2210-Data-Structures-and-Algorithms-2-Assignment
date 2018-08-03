package Util.Tuples;

import Util.Graph.Node;

import java.util.List;

public class PathCostPair {

    final public List<Node> path;
    final public double cost;

    public PathCostPair(final List<Node> path, final double cost) {
        this.path = path;
        this.cost = cost;
    }
}
