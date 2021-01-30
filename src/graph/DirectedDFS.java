package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class DirectedDFS {
    private boolean[] marked;

    public DirectedDFS(Digraph G, int s) {
        marked = new boolean[G.V()];
        dfs(G,s);
    }

    private DirectedDFS(Digraph G, Iterable<Integer> sources) {
        marked = new boolean[G.V()];
        for(int s : sources) {
            if(!marked[s]) dfs(G, s);
        }
    }

    private void dfs(Digraph G, int v) {
        marked[v] = true;
        for(int w : G.adj(v)){
            if(!marked[w]) dfs(G, w);
        }
    }

    private boolean marked(int v) { return marked[v]; }

    public static void main(String[] args) {
        int[][] tinyDG = new int[][]{
                {13}, {22}, {4,2}, {2,3}, {3,2}, {6,0}, {0,1}, {2,0}, {11,12}, {12,9},
                {9,10}, {9,11}, {8,9}, {10,12}, {11,4}, {4,3}, {3,5}, {7,8}, {8,7},
                {5,4}, {0,5}, {6,4}, {6,9}, {7,6}
        };

        Digraph G = new Digraph(tinyDG);
        LinkedList<Integer> sources = new LinkedList<>();

        Scanner in = new Scanner(System.in);
        System.out.print("Enter how many vertices do you want to search: ");
        int n = in.nextInt();
        for(int i = 0; i < n; i++) {
            System.out.print("Enter vertex " + Integer.parseInt(String.valueOf(i+1)) + " : ");
            int s = in.nextInt();
            sources.add(s);
        }

        DirectedDFS reachable = new DirectedDFS(G, sources);

        for(int v = 0; v < G.V(); v++) {
            if(reachable.marked(v)) {
                System.out.print(v + " ");
            }
        }
        System.out.println();
    }
}
