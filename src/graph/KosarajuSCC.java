package graph;

import java.util.LinkedList;

public class KosarajuSCC {
    private boolean[] marked; // visited vertices
    private int[] id; // identifier of strongly connected components
    private int count; // the number of strongly connected components

    private KosarajuSCC(Digraph G) {
        marked = new boolean[G.V()];
        id = new int[G.V()];
        DepthFirstOrder order = new DepthFirstOrder(G.reverse());
        for(int s : order.reversePost()) {
            if(!marked[s]) {
                dfs(G, s);
                count++;
            }
        }
    }

    private void dfs(Digraph G, int v) {
        marked[v] = true;
        id[v] = count;
        for(int w : G.adj(v)) {
            if(!marked[w]) {
                dfs(G, w);
            }
        }
    }

    boolean stronglyConnected(int v, int w) {
        return id[v] == id[w];
    }

    int id(int v) {
        return id[v];
    }

    int count() {
        return count;
    }

    private static void graphPrint(int[][] digraph) {
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

        Digraph G = new Digraph(tinyDG);
        KosarajuSCC scc = new KosarajuSCC(G);

        int M = scc.count;
        System.out.println(M + " components");

        LinkedList<Integer>[] components = new LinkedList[M];
        for(int i = 0; i < M; i++) {
            components[i] = new LinkedList<>();
        }
        for(int v = 0; v < G.V(); v++) {
            components[scc.id(v)].add(v);
        }
        for(int i = 0; i < M; i++) {
            for(int v : components[i]) {
                System.out.print(v + " ");
            }
            System.out.println();
        }
    }
}
