package graph;

import java.util.LinkedList;

public class ConnectComponent {
    private boolean[] marked;
    private int[] id;
    private int count;

    public ConnectComponent(Graph G) {
        marked = new boolean[G.V()];
        id = new int[G.V()];
        for(int s = 0; s < G.V(); s++) {
            if(!marked[s]) {
                dfs(G, s);
                count++;
            }
        }
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        id[v] = count;
        for(int w : G.adj(v)) {
            if(!marked[w]) {
                dfs(G, w);
            }
        }
    }

    public boolean connected(int v, int w) { return id[v] == id[w]; }

    public int id(int v) { return id[v]; }

    public int count() { return count; }

    public static void ccPrint(int[][] graph) {
        Graph G = new Graph(graph);
        ConnectComponent cc = new ConnectComponent(G);

        int m = cc.count();
        if(m == 1) System.out.println("1 component");
        else System.out.println(m + " components");

        LinkedList<Integer>[] components = new LinkedList[m];
        for(int i = 0; i < m; i++) { components[i] = new LinkedList<>(); }
        for(int v = 0; v < G.V(); v++) { components[cc.id(v)].add(v); }
        for(int i = 0; i < m; i++) {
            for(int v : components[i]) {
                System.out.print(v + " ");
            }
            System.out.println();
        }
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
        ccPrint(tinyG);
        System.out.println("\ntinyCG:");
        ccPrint(tinyCG);
    }
}
