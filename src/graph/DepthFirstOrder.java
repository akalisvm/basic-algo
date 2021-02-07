package graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class DepthFirstOrder {
    private boolean[] marked;
    private Queue<Integer> pre; // pre order for all vertices
    private Queue<Integer> post; // post order for all vertices
    private Stack<Integer> reversePost; // reverse post order for all vertices

    DepthFirstOrder(Digraph G) {
        pre = new LinkedList<>();
        post = new LinkedList<>();
        reversePost = new Stack<>();
        marked = new boolean[G.V()];

        for(int v = 0; v < G.V(); v++) {
            if(!marked[v]) {
                dfs(G, v);
            }
        }
    }

    private void dfs(Digraph G, int v) {
        pre.add(v);

        marked[v] = true;
        for(int w : G.adj(v)) {
            if(!marked[w]) {
                dfs(G, w);
            }
        }
        post.add(v);
        reversePost.push(v);
    }

    Iterable<Integer> pre() {
        return pre;
    }

    Iterable<Integer> post() {
        return post;
    }

    Iterable<Integer> reversePost() {
        return reversePost;
    }
}
