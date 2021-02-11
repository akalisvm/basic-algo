package string;

import java.util.ArrayList;
import java.util.Stack;
import graph.Digraph;
import graph.DirectedDFS;

public class NFA {
    private char[] re;
    private Digraph G;
    private int M;

    private NFA(String regexp) {
        Stack<Integer> ops = new Stack<>();
        re = regexp.toCharArray();
        M = re.length;
        G = new Digraph(M+1);

        for(int i = 0; i < M; i++) {
            int lp = i;
            if(re[i] == '(' || re[i] == ')') {
                ops.push(i);
            } else if(re[i] == '|') {
                int or = ops.pop();
                if(re[or] == '|') {
                    lp = ops.pop();
                    G.addEdge(lp, or+1);
                    G.addEdge(or, i);
                } else {
                    lp = or;
                }
            }

            if(i < M-1 && re[i+1] == '*') {
                G.addEdge(lp, i+1);
                G.addEdge(i+1, lp);
            }

            if(re[i] == '(' || re[i] == '*' || re[i] == ')') {
                G.addEdge(i, i+1);
            }
        }
    }

    private boolean recognizes(String txt) {
        ArrayList<Integer> pc = new ArrayList<>();
        DirectedDFS dfs = new DirectedDFS(G, 0);
        for(int v = 0; v < G.V(); v++) {
            if(dfs.marked(v)) {
                pc.add(v);
            }
        }

        for(int i = 0; i < txt.length(); i++) {
            ArrayList<Integer> match = new ArrayList<>();
            for(int v : pc) {
                if(v < M) {
                    if(re[v] == txt.charAt(i) || re[v] == '.') {
                        match.add(v+1);
                    }
                }
            }
            pc = new ArrayList<>();
            dfs = new DirectedDFS(G, match);
            for(int v = 0; v < G.V(); v++) {
                if(dfs.marked(v)) {
                    pc.add(v);
                }
            }
        }

        for(int v : pc) {
            if(v == M) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String[] txt = new String[]{
                "AC", "AD", "AAA", "ABD",
                "ADD", "BCD", "ABCCBD",
                "BABAAA", "BABBAAA"
        };
        String regexp = "(.*" + "A*BD" + ".*)";
        NFA nfa = new NFA(regexp);
        for(String s : txt) {
            if(nfa.recognizes(s)) {
                System.out.println(s);
            }
        }
    }
}
