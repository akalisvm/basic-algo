package searching;

import java.util.*;

// Sequential search ST based on an unsorted linked list
// position counts from 0
public class SequentialSearchST<Key, Value> {
    private Node first;
    private class Node {
        Key key;
        Value val;
        Node next;
        Node(Key key, Value val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    public int size() {
        int n = 0;
        while(first != null) {
            first = first.next;
            n++;
        }
        return n;
    }

    Value get(Key key) {
        for(Node x = first; x != null; x = x.next) {
            if(key.equals(x.key)) {
                return x.val;
            }
        }
        return null;
    }

    void put(Key key, Value val) {
        for(Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                x.val = val;
                return;
            }
        }
        first = new Node(key, val, first);
    }

    boolean contains(Key key) {
        while(first != null) {
            if(first.key.equals(key)) {
                return true;
            }
            first = first.next;
        }
        return false;
    }

    public void delete(Key key) {
        if(key == null) {
            throw new IllegalArgumentException();
        }
        while(first != null) {
            Node firstNext = first.next;
            if(first.key.equals(key)) {
                firstNext = firstNext.next;
                firstNext = null;
                return;
            }
            first = first.next;
        }
        throw new NoSuchElementException();
    }

    public void print() {
        for(Node x = first; x != null; x = x.next) {
            System.out.print(x.key + ": " + x.val + "\t");
        }
    }

    private static String getKey() {
        Scanner sc = new Scanner(System.in);
        return sc.next();
    }

    public static void main(String[] args) {
        String str = "SEARCHEXAMPLE";
        SequentialSearchST<Character, Integer> st = new SequentialSearchST<>();
        for(int i = 0; i < str.length(); i++) {
            st.put(str.charAt(i), i);
        }
        st.print();
        System.out.println();
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
