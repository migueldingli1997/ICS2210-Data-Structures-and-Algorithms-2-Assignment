package Util.Graph;

import Util.UtilityMethods;

import java.util.Arrays;

public class Graph {

    public final Node nodes[];
    public final Node startNode, goalNodes[];

    public Graph() {

        Node.resetGlobalID(); // Reset Node's global ID

        // "Create a graph containing n nodes..."
        nodes = new Node[UtilityMethods.randInt(10, 12)];
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = new Node(UtilityMethods.randInt(1, 4));
        }

        // "Designate a random node as the starting node."
        startNode = nodes[UtilityMethods.randInt(0, nodes.length - 1)];

        // "Designate 3 distinct random nodes as goal nodes. "
        goalNodes = new Node[]{null, null, null};
        for (int i = 0; i < 3; /*no increment*/) {
            final Node gNode = nodes[UtilityMethods.randInt(0, nodes.length - 1)];
            if (gNode != goalNodes[(i + 1) % 3] && gNode != goalNodes[(i + 2) % 3]) {
                goalNodes[i] = gNode;
                i++;
            }
        }

        // "For each node, create m distinct directed edges..."
        for (final Node from : nodes) {
            for (int edgeIndex = 0; edgeIndex < from.edgesOut.length; /*no increment*/) {
                final int dstIndex = UtilityMethods.randInt(0, nodes.length - 1);
                if (!existsEdgeBetween(from.ID, dstIndex)) {
                    from.edgesOut[edgeIndex] = new Edge(nodes[dstIndex], Math.random());
                    edgeIndex++;
                }
            }
        }

//        startNode = nodes[2];
//        goalNodes = new Node[]{ nodes[3], nodes[7], nodes[6] };
//        nodes[0].edgesOut[0] = new Edge(nodes[4], 0.7110888978799483);
//        nodes[0].edgesOut[1] = new Edge(nodes[3], 0.6621180630076551);
//        nodes[1].edgesOut[0] = new Edge(nodes[4], 0.013998232614498396);
//        nodes[1].edgesOut[1] = new Edge(nodes[9], 0.8114964683908406);
//        nodes[2].edgesOut[0] = new Edge(nodes[8], 0.5743522940696959);
//        nodes[2].edgesOut[1] = new Edge(nodes[0], 0.2421158525355689);
//        nodes[3].edgesOut[0] = new Edge(nodes[5], 0.60975996472695);
//        nodes[3].edgesOut[1] = new Edge(nodes[7], 0.8139462758773169);
//        nodes[4].edgesOut[0] = new Edge(nodes[2], 0.11905425216249577);
//        nodes[4].edgesOut[1] = new Edge(nodes[1], 0.9953258014854419);
//        nodes[5].edgesOut[0] = new Edge(nodes[7], 0.8148710988590928);
//        nodes[5].edgesOut[1] = new Edge(nodes[6], 0.2790814720020416);
//        nodes[6].edgesOut[0] = new Edge(nodes[3], 0.375584383757154);
//        nodes[6].edgesOut[1] = new Edge(nodes[1], 0.5147621325150399);
//        nodes[7].edgesOut[0] = new Edge(nodes[4], 0.8636732065771006);
//        nodes[7].edgesOut[1] = new Edge(nodes[2], 0.4557197784972036);
//        nodes[8].edgesOut[0] = new Edge(nodes[2], 0.6624807538057211);
//        nodes[8].edgesOut[1] = new Edge(nodes[0], 0.1527839174305522);
//        nodes[9].edgesOut[0] = new Edge(nodes[4], 0.6944568922128042);
//        nodes[9].edgesOut[1] = new Edge(nodes[5], 0.5655309036876192);

//        startNode = nodes[1];
//        goalNodes = new Node[]{ nodes[2], nodes[2], nodes[2] };
//        nodes[0].edgesOut[0] = new Edge(nodes[2], 0.7);
//        nodes[0].edgesOut[1] = new Edge(nodes[4], 0.6);
//        nodes[1].edgesOut[0] = new Edge(nodes[3], 0.5);
//        nodes[1].edgesOut[1] = new Edge(nodes[0], 0.2);
//        nodes[2].edgesOut[0] = new Edge(nodes[2], 0.6);
//        nodes[2].edgesOut[1] = new Edge(nodes[4], 0.8);
//        nodes[3].edgesOut[0] = new Edge(nodes[1], 0.6);
//        nodes[3].edgesOut[1] = new Edge(nodes[0], 0.1);
//        nodes[4].edgesOut[0] = new Edge(nodes[4], 1000);
//        nodes[4].edgesOut[1] = new Edge(nodes[4], 1000);
    }

    private boolean existsEdgeBetween(final int srcId, final int dstId) throws RuntimeException {

        if (srcId >= nodes.length || dstId >= nodes.length)
            throw new RuntimeException("A node's ID exceeded the nodes array's index limit.");

        for (final Edge e : nodes[srcId].edgesOut) {
            if (e == null) {
                break; // array might not have been filled yet
            } else if (e.dst.ID == dstId) {
                return true;
            }
        }
        return false;
    }

    public void resetVisitedNodes() {
        Arrays.asList(nodes).forEach(n -> n.visited = false);
    }

    public boolean isGoalNode(final Node node) {
        return node.ID == goalNodes[0].ID || node.ID == goalNodes[1].ID || node.ID == goalNodes[2].ID;
    }

    public void outputDetails() {

        // Calculating number of edges
        int totalEdges = 0;
        for (Node n : nodes) {
            totalEdges += n.edgesOut.length;
        }

        // Output details
        System.out.println("Start node: " + startNode.ID);
        System.out.println("Goal nodes: " + Arrays.toString(goalNodes));
        System.out.println("#Nodes: " + nodes.length);
        System.out.println("#Edges: " + totalEdges);
        for (Node n : nodes) {
            System.out.println(n + " -> " + Arrays.asList(n.edgesOut).toString());
        }
    }
}