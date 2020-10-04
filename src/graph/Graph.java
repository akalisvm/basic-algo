package graph;

import java.util.LinkedList;

public class Graph {
    private int V; // number of vertices
    private int E; // number of edges
    private LinkedList<Integer>[] adj; // adjacency list

    public Graph(int V) {
        this.V = V;
        this.E = 0;
        adj = (LinkedList<Integer>[]) new LinkedList[V]; // create an adjacency list
        for(int v = 0; v < V; v++) { // initialize all adjacency lists to empty
            adj[v] = new LinkedList<>();
        }
    }

    public Graph(int[][] array) {
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
        adj[v].add(w); // add w to the linked list of v
        adj[w].add(v); // add v to the linked list of w
        E++;
    }

    public Iterable<Integer> adj(int v) { return adj[v]; }

    public static int degree(Graph G, int v) {
        int degree = 0;
        for(int w : G.adj(v)) degree++;
        return degree;
    }

    public static int maxDegree(Graph G) {
        int max = 0;
        for(int v = 0; v < G.V(); v++) {
            if(degree(G, v) > max) {
                max = degree(G, v);
            }
        }
        return max;
    }

    public static double avgDegree(Graph G) { return 2.0 * G.E() / G.V(); }

    public static int numberOfSelfLoops(Graph G) {
        int count = 0;
        for(int v = 0; v < G.V(); v++) {
            for(int w : G.adj(v)) {
                if (v == w) count++;
            }
        }
        return count/2; // every edge is marked twice
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

    public static void graphPrint(int[][] graph) {
        Graph G = new Graph(graph);
        System.out.print(G);
        System.out.println("The largest degree is: " + maxDegree(G));
        System.out.println("The average number of degrees is: " + avgDegree(G));
        System.out.println("The number of self loops is: " + numberOfSelfLoops(G));
    }

    public static void main(String[] args) {
        int[][] tinyG = new int[][]{
                {13}, {13}, {0, 5}, {4, 3}, {0, 1}, {9, 12}, {6, 4}, {5, 4},
                {0, 2}, {11, 12}, {9, 10}, {0, 6}, {7, 8}, {9, 11}, {5, 3}
        };
        int[][] tinyCG = new int[][] {
                {6}, {8}, {0, 5}, {2, 4}, {2, 3}, {1, 2}, {0, 1}, {3, 4}, {3, 5}, {0, 2}
        };
        System.out.println("tinyG:");
        graphPrint(tinyG);
        System.out.println("\ntinyCG:");
        graphPrint(tinyCG);
    }
}
