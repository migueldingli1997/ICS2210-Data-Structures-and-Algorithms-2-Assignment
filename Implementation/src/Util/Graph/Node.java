package Util.Graph;

import java.util.ArrayList;
import java.util.List;

public class Node {

    private static int globalID = 0;
    public final int ID = globalID++;
    public final Edge edgesOut[];
    public boolean visited = false;

    public Node(final int numberOfEdges) {
        this.edgesOut = new Edge[numberOfEdges];
    }

    static void resetGlobalID() {
        globalID = 0;
    }

    @Override
    public String toString() {
        return "(" + ID + ")";
    }
}
