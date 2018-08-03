package Util.Graph;

public class Edge {

    public final Node dst;
    public final double val;

    Edge(final Node dst, final double val) {
        this.dst = dst;
        this.val = val;
    }

    @Override
    public String toString() {
        return "(" + dst.ID + ",  " + val + ")";
    }
}
