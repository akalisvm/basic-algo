package graph;

import java.util.*;

public class BreadthFirstPaths {
    private boolean[] marked; // whether the shortest path to this vertex is known
    private int[] edgeTo; // the last vertex on a known path from a vertex to another vertex
    private final int s; // start vertex

    private BreadthFirstPaths(Graph G, int s) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        bfs(G, s);
    }

    private void bfs(Graph G, int s) {
        Queue<Integer> queue = new LinkedList<>();
        marked[s] = true; // mark the start vertex
        queue.offer(s); // add this vertex into the queue
        while(!queue.isEmpty()) {
            int v = queue.poll(); // remove the next vertex from the queue
            for(int w : G.adj(v)) {
                if(!marked[w]) { // for every unmarked adjacent vertices
                    edgeTo[w] = v; // save the last edge of the shortest path
                    marked[w] = true; //  mark it since the shortest path is known
                    queue.offer(w); // and add it into the queue
                }
            }
        }
    }

    private boolean hasPathTo(int v) { return marked[v]; }

    private Iterable<Integer> pathTo(int v) {
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
                {6}, {8}, {0,5}, {2,4}, {2,3}, {1,2}, {0,1}, {3,4}, {3,5}, {0,2}
        };
        Graph G = new Graph(tinyCG);
        System.out.println(G);
        Scanner in = new Scanner(System.in);
        System.out.print("(Breadth First)\nEnter start vertex to show paths to other adjacent vertices: ");
        int s = in.nextInt();
        BreadthFirstPaths paths = new BreadthFirstPaths(G, s);

        System.out.print("edgeTo[]: ");
        for(int w : paths.edgeTo) {
            System.out.print(w + " "); // edgeTo[] is actually a tree made by father nodes
        }
        System.out.println();

        for(int v = 0; v < G.V(); v++) {
            System.out.print(s + " to " + v + ": ");
            if(paths.hasPathTo(v)) {
                for(int x : Objects.requireNonNull(paths.pathTo(v))) {
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
