package graph;

public class Cycle {
    private boolean[] marked;
    private boolean hasCycle;
    public Cycle(Graph G) {
        marked = new boolean[G.V()];
        for(int s = 0; s < G.V(); s++) {
            if(!marked[s]) {
                dfs(G, s, s);
            }
        }
    }

    private void dfs(Graph G, int v, int u) {
        marked[v] = true;
        for(int w : G.adj(v)) {
            if(!marked[w]) {
                dfs(G, w, v);
            }
            else if(w != u) hasCycle = true;
        }
    }

    public boolean hasCycle() { return hasCycle; }

    public static void cyclePrint(int[][] graph) {
        Graph G = new Graph(graph);
        Cycle C = new Cycle(G);
        System.out.print(G);
        if(C.hasCycle) System.out.println("This graph has cycle.");
        else System.out.println("This graph does NOT have cycle.");
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
        cyclePrint(tinyG);
        System.out.println("\ntinyCG:");
        cyclePrint(tinyCG);
    }
}
