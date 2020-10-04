package graph;

import java.util.ArrayList;
import java.util.Stack;
import java.util.Scanner;

public class DepthFirstPaths {
    private boolean[] marked; // whether method dfs() is called on this vertex or not
    private int[] edgeTo; // the last vertex on a known path from a vertex to another vertex
    private final int s; // start vertex

    public DepthFirstPaths(Graph G, int s) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        dfs(G, s);
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        for(int w : G.adj(v)) {
            if(!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            }
        }
    }

    public boolean hasPathTo(int v) { return marked[v]; }

    public Iterable<Integer> pathTo(int v) {
        if(!hasPathTo(v)) return null;
        ArrayList<Integer> temp = new ArrayList<>();
        for(int x = v; x != s; x = edgeTo[x]) {
            temp.add(x);
        }
        temp.add(s);
        Stack<Integer> path = new Stack<>();
        for(int i = temp.size()-1; i >= 0; i--) {
            path.push(temp.get(i));
        }
        return path;
    }

    public static void main(String[] args) {
        int[][] tinyCG = new int[][] {
                {6}, {8}, {0, 5}, {2, 4}, {2, 3}, {1, 2}, {0, 1}, {3, 4}, {3, 5}, {0, 2}
        };
        Graph G = new Graph(tinyCG);
        System.out.println(G);
        Scanner in = new Scanner(System.in);
        System.out.print("(Depth First)\nEnter start vertex to show paths to other adjacent vertices: ");
        int s = in.nextInt();
        DepthFirstPaths paths = new DepthFirstPaths(G, s);

        System.out.print("edgeTo[]: ");
        for(int w : paths.edgeTo) {
            System.out.print(w + " "); // edgeTo[] is actually a tree made by father nodes
        }
        System.out.println();

        for(int v = 0; v < G.V(); v++) {
            System.out.print(s + " to " + v + ": ");
            if(paths.hasPathTo(v)) {
                for(int x : paths.pathTo(v)) {
                    if(x == s) {
                        System.out.print(x);
                    }
                    else System.out.print("-" + x);
                }
            }
            System.out.println();
        }
    }
}
