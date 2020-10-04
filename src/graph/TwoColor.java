package graph;

public class TwoColor {
    private boolean[] marked;
    private boolean[] color;
    private boolean isTwoColorable = true;
    public TwoColor(Graph G) {
        marked = new boolean[G.V()];
        color = new boolean[G.V()];
        for(int s = 0; s < G.V(); s++) {
            if(!marked[s]) {
                dfs(G, s);
            }
        }
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        for(int w : G.adj(v)) {
            if(!marked[w]) {
                color[w] = !color[v];
                dfs(G, w);
            }
            else if(color[w] == color[v]) { isTwoColorable = false; }
        }
    }

    public boolean isBipartite() { return isTwoColorable; }

    public static void twoColorPrint(int[][] graph) {
        Graph G = new Graph(graph);
        TwoColor T = new TwoColor(G);
        System.out.print(G);
        if(T.isBipartite()) System.out.println("This graph is a bipartite graph.");
        else System.out.println("This graph is NOT a bipartite graph.");
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
        twoColorPrint(tinyG);
        System.out.println("\ntinyCG:");
        twoColorPrint(tinyCG);
    }
}
