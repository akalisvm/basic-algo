package searching;

import java.util.LinkedList;
import java.util.Scanner;

// Binary search tree ST
// position counts from 0
public class BST<Key extends Comparable<Key>, Value> {
    private Node root;
    private class Node {
        private Key key;
        private Value val;
        private Node left, right;
        private int n;
        Node(Key key, Value val, int n) {
            this.key = key;
            this.val = val;
            this.n = n;
        }
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if(x == null) return 0;
        else return x.n;
    }

    private Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node x, Key key) {
        if(x == null) return null;
        int cmp = key.compareTo(x.key);
        if(cmp < 0) return get(x.left, key);
        else if(cmp > 0) return get(x.right, key);
        else return x.val;
    }

    private void put(Key key, Value val) { root = put(root, key, val); }

    private Node put(Node x, Key key, Value val) {
        if(x == null) return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if(cmp < 0) x.left = put(x.left, key, val);
        else if(cmp > 0) x.right = put(x.right, key, val);
        else x.val = val;
        x.n = size(x.left) + size(x.right) + 1;
        return x;
    }

    private static String getKey() {
        Scanner sc = new Scanner(System.in);
        return sc.next();
    }

    // pre order traversal ROOT -> LEFT -> RIGHT
    private void preOrderTraversal(){
        preOrderTraversal(root);
    }

    private void preOrderTraversal(Node x) {
        if(x == null) return;
        System.out.print(x.key + ": "+ x.val + "\t");
        preOrderTraversal(x.left);
        preOrderTraversal(x.right);
    }

    // in order traversal LEFT -> ROOT -> RIGHT
    private void inOrderTraversal() {
        inOrderTraversal(root);
    }

    private void inOrderTraversal(Node x) {
        if(x == null) return;
        inOrderTraversal(x.left);
        System.out.print(x.key + ": "+ x.val + "\t");
        inOrderTraversal(x.right);
    }

    // post order traversal LEFT -> RIGHT -> ROOT
    private void postOrderTraversal() {
        postOrderTraversal(root);
    }

    private void postOrderTraversal(Node x) {
        if(x == null) return;
        postOrderTraversal(x.left);
        postOrderTraversal(x.right);
        System.out.print(x.key + ": "+ x.val + "\t");
    }

    // level order traversal
    private void levelOrderTraversal() {
        levelOrderTraversal(root);
    }
    private void levelOrderTraversal(Node x) {
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
        BST<Character, Integer> st = new BST<>();
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
