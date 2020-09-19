package searching;

import java.util.LinkedList;
import java.util.Scanner;

// Red black tree ST
// red black tree is 2-3 tree if red links are flat
// definition:
// Red black tree is a binary search tree that contains red and black links and satisfies the following conditions
// 1.Red links are all left links.
// 2.No one node connects with two red links.
// 3.This tree is perfectly balanced in black which means the number of black links on the path of any empty connection to the root node is same.
// position counts from 0
public class RedBlackBST<Key extends Comparable<Key>, Value> {
    private Node root;
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node {
        Key key;
        Value val;
        Node left, right;
        int n;
        boolean color;

        Node (Key key, Value val, int n, boolean color) {
            this.key = key;
            this.val = val;
            this.n = n;
            this.color = color;
        }
    }

    public int size() { return size(root); }

    private int size(Node x) {
        if(x == null) return 0;
        else return x.n;
    }

    private boolean isRed (Node x) {
        if(x == null) return false;
        return x.color == RED;
    }

    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.n = h.n;
        h.n = size(h.left) + size(h.right) + 1;
        return x;
    }

    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.n = h.n;
        h.n = size(h.left) + size(h.right) + 1;
        return x;
    }

    private void flipColors(Node h) {
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }

    public Value get(Key key) { return get(root, key); }

    public Value get(Node x, Key key) {
        if(x == null) return null;
        int cmp = key.compareTo(x.key);
        if(cmp < 0) return get(x.left, key);
        else if(cmp > 0) return get(x.right, key);
        else return x.val;
    }

    public void put(Key key, Value val) {
        root = put(root, key, val);
        root.color = BLACK;
    }

    public Node put(Node h, Key key, Value val) {
        if(h == null) return new Node(key, val, 1, RED);

        int cmp = key.compareTo(h.key);
        if(cmp < 0) h.left = put(h.left, key, val);
        else if(cmp > 0) h.right = put(h.right, key, val);
        else h.val = val;

        if(isRed(h.right) && !isRed(h.left)) h = rotateLeft(h);
        if(isRed(h.left) && !isRed(h.left.left)) h = rotateRight(h);
        if(isRed(h.left) && isRed(h.right)) flipColors(h);

        h.n = size(h.left) + size(h.right) + 1;
        return h;
    }

    public static String getKey() {
        Scanner sc = new Scanner(System.in);
        return sc.next();
    }

    // pre order traversal ROOT -> LEFT -> RIGHT
    public void preOrderTraversal(){ preOrderTraversal(root);}
    public void preOrderTraversal(Node x) {
        if(x == null) return;
        System.out.print(x.key + ": "+ x.val + "\t");
        preOrderTraversal(x.left);
        preOrderTraversal(x.right);
    }

    // in order traversal LEFT -> ROOT -> RIGHT
    public void inOrderTraversal() { inOrderTraversal(root); }
    public void inOrderTraversal(Node x) {
        if(x == null) return;
        inOrderTraversal(x.left);
        System.out.print(x.key + ": "+ x.val + "\t");
        inOrderTraversal(x.right);
    }


    // post order traversal LEFT -> RIGHT -> ROOT
    public void postOrderTraversal() { postOrderTraversal(root); }
    public void postOrderTraversal(Node x) {
        if(x == null) return;
        postOrderTraversal(x.left);
        postOrderTraversal(x.right);
        System.out.print(x.key + ": "+ x.val + "\t");
    }

    // level order traversal
    public void levelOrderTraversal() {levelOrderTraversal(root); }
    public void levelOrderTraversal(Node x) {
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(x);
        while(!queue.isEmpty()) {
            x = queue.pop();
            System.out.print(x.key + ": "+ x.val + "\t");
            if(x.left != null) queue.add(x.left);
            if(x.right != null) queue.add(x.right);
        }
    }

    public static void main(String[] args) {
        String str = "SEARCHEXAMPLE";
        RedBlackBST<Character, Integer> st = new RedBlackBST<>();
        for(int i = 0; i < str.length(); i++) {
            st.put(str.charAt(i), i);
        }
        System.out.println("Pre Order Traversal: ");
        st.preOrderTraversal();
        System.out.println("\nIn Order Traversal: ");
        st.inOrderTraversal();
        System.out.println("\nPost Order Traversal: ");
        st.postOrderTraversal();
        System.out.println("\nLevel Order Traversal: ");
        st.levelOrderTraversal();
        System.out.print("\nEnter a key：");
        String key = getKey();
        if(key.length() == 1) {
            Character c = key.charAt(0);
            if(st.get(c) != null) {
                System.out.println("try to search: " + c + "\n"
                        + c + " is at position " + st.get(c));
            }
            else { System.out.println("invalid input!"); }
        }
        else { System.out.println("invalid input!"); }
    }
}
