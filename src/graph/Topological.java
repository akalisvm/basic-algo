package graph;

public class Topological {
    private Iterable<Integer> order;

    private Topological(Digraph G) {
        DirectedCycle cycleFinder = new DirectedCycle(G);
        if(!cycleFinder.hasCycle()) {
            DepthFirstOrder dfs = new DepthFirstOrder(G);
            order = dfs.reversePost();
        }
    }

    Iterable<Integer> order() {
        return order;
    }

    boolean isDAG() {
        return order != null;
    }
}
