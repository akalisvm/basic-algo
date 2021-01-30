package graph;

import java.util.Scanner;

public class DepthFirstSearch {
    private boolean[] marked;
    private int count;

    private DepthFirstSearch(Graph G, int s) {
        marked = new boolean[G.V()];
        dfs(G, s);
    }

    private void dfs(Graph G, int v) {
        marked[v] = true; // mark the vertex as visited
        count++;
        for(int w : G.adj(v)) {
            if(!marked[w]) {
                dfs(G, w); // recursively accessing unmarked adjacent vertices
            }
        }
    }

    private boolean marked(int w) { // all vertices connect with start vertex
        return marked[w];
    }

    private int count() {
        return count;
    }

    private boolean connected(DepthFirstSearch dfs, Graph G) { // whether this graph is connected
        return dfs.count() == G.V();
    }

    private void markedPrint(DepthFirstSearch dfs, Graph G) {
        for(int v = 0; v < G.V(); v++) {
            if(dfs.marked(v)) {
                System.out.print(v + " ");
            }
        }
        System.out.println();
    }

    private void connectedPrint(DepthFirstSearch dfs, Graph G) {
        System.out.print("This graph is ");
        if(!dfs.connected(dfs, G))
            System.out.print("NOT ");
        System.out.println("connected");
    }

    public static void main(String[] args) {
        int[][] tinyG = new int[][]{
                {13}, {13}, {0,5}, {4,3}, {0,1}, {9,12}, {6,4}, {5,4},
                {0,2}, {11,12}, {9,10}, {0,6}, {7,8}, {9,11}, {5,3}
        };
        int[][] tinyCG = new int[][] {
                {6}, {8}, {0,5}, {2,4}, {2,3}, {1,2}, {0,1}, {3,4}, {3,5}, {0,2}
        };
        Graph G = new Graph(tinyCG);
        System.out.println(G);
        Scanner in = new Scanner(System.in);
        System.out.print("Enter start vertex to search all vertices connect with it: ");
        int s = in.nextInt();
        DepthFirstSearch dfs = new DepthFirstSearch(G, s);
        dfs.markedPrint(dfs, G);
        dfs.connectedPrint(dfs, G);
    }
}
