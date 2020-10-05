package graph;

import java.util.LinkedList;

public class Digraph {
    private int V;
    private int E;
    private LinkedList<Integer>[] adj;

    public Digraph(int V) {
        this.V = V;
        this.E = 0;
        adj = (LinkedList<Integer>[]) new LinkedList[V];
        for(int v = 0; v < V; v++) {
            adj[v] = new LinkedList<>();
        }
    }

    public Digraph(int[][] array) {
        this(array[0][0]); // read V and initialize the graph
        int E = array[1][0]; // read E
        for(int i = 2; i < E+2; i++) {
            int v = array[i][0]; // read one vertex
            int w = array[i][1]; // read another one vertex
            addEdge(v, w); // add an edge to connect them
        }
    }

    public int V() { return V; }

    public int E() { return E; }

    public void addEdge(int v, int w) {
        adj[v].add(w);
        E++;
    }

    public Iterable<Integer> adj(int v) { return adj[v]; }

    public Digraph reverse() {
        Digraph R = new Digraph(V);
        for(int v = 0; v < V; v++) {
            for(int w : adj(v)) {
                R.addEdge(w, v);
            }
        }
        return R;
    }

    public String toString() {
        StringBuilder s = new StringBuilder(V + " vertices, " + E + " edges.\n");
        for(int v = 0; v < V; v++) {
            s.append(v).append(": ");
            for(int w : this.adj(v)) {
                s.append(w).append(" ");
            }
            s.append("\n");
        }
        return String.valueOf(s);
    }

    public static void graphPrint(int[][] digraph) {
        Digraph G = new Digraph(digraph);
        System.out.print(G);
    }

    public static void main(String[] args) {
        int[][] tinyDG = new int[][]{
                {13}, {22}, {4,2}, {2,3}, {3,2}, {6,0}, {0,1}, {2,0}, {11,12}, {12,9},
                {9,10}, {9,11}, {8,9}, {10,12}, {11,4}, {4,3}, {3,5}, {7,8}, {8,7},
                {5,4}, {0,5}, {6,4}, {6,9}, {7,6}
        };
        System.out.println("tinyDG:");
        graphPrint(tinyDG);
    }
}
