package graph;

import java.util.Stack;

public class DirectedCycle {
    private boolean[] marked;
    private int[] edgeTo;
    private Stack<Integer> cycle;
    private boolean[] onStack;

    private DirectedCycle(Digraph G) {
        onStack = new boolean[G.V()];
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        for(int v = 0; v < G.V(); v++) {
            if(!marked[v]) dfs(G, v);
        }
    }

    private void dfs(Digraph G, int v) {
        onStack[v] = true;
        marked[v] = true;
        for(int w : G.adj(v)) {
            if(this.hasCycle()) return;
            else if(!marked[w]) { edgeTo[w] = v; dfs(G, w); }
            else if(onStack[w]) {
                cycle = new Stack<>();
                for(int x = v; x != w; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
            }
        }
        onStack[v] = false;
    }

    private boolean hasCycle() { return cycle != null; }

    private Iterable<Integer> cycle() { return cycle; }

    public static void main(String[] args) {
        int[][] tinyDG = new int[][]{
                {13}, {22}, {4,2}, {2,3}, {3,2}, {6,0}, {0,1}, {2,0}, {11,12}, {12,9},
                {9,10}, {9,11}, {8,9}, {10,12}, {11,4}, {4,3}, {3,5}, {7,8}, {8,7},
                {5,4}, {0,5}, {6,4}, {6,9}, {7,6}
        };
        System.out.println("tinyDG:");
        Digraph G = new Digraph(tinyDG);
        DirectedCycle C = new DirectedCycle(G);
        System.out.print(G);
        if(C.hasCycle()) {
            System.out.println("This graph has cycle.");
            for(int v : C.cycle()) {
                System.out.print(v + " ");
            }
            System.out.println();
        }
        else System.out.println("This graph does NOT have cycle.");
    }
}
