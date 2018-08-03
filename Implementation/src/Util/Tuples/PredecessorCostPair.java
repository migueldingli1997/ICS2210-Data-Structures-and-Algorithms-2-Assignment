package Util.Tuples;

import Util.Graph.Node;

public class PredecessorCostPair {

    final public Node pred;
    final public double cost;

    public PredecessorCostPair(final Node pred, final double cost) {
        this.pred = pred;
        this.cost = cost;
    }
}