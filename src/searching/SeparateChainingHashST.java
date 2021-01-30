package searching;

import java.util.Scanner;

// Hash table ST based on separate chains
// position counts from 0
public class SeparateChainingHashST<Key, Value> {
    private int n;
    private int m;
    private SequentialSearchST<Key, Value>[] st;

    public SeparateChainingHashST() { this(997); } // default set

    private SeparateChainingHashST(int m) {
        this.m = m;
        st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[m];
        for(int i = 0; i < m; i++) {
            st[i] = new SequentialSearchST<>();
        }
    }

    private int hash(Key key) { return (key.hashCode() & 0x7fffffff) % m; }

    private void put(Key key, Value val) { st[hash(key)].put(key, val); }

    private Value get(Key key) { return st[hash(key)].get(key); }

    private static String getKey() {
        Scanner sc = new Scanner(System.in);
        return sc.next();
    }

    public void print() {
        for(int i = 0; i < st.length; i++) {
            System.out.print(i + " -> ");
            st[i].print();
            System.out.println();
        }
    }

    public static void main(String[] args) {
        String str = "SEARCHEXAMPLE";
        SeparateChainingHashST<Character, Integer> st = new SeparateChainingHashST<>(str.length());
        for(int i = 0; i < str.length(); i++) {
            st.put(str.charAt(i), i);
        }
        st.print();
        System.out.print("Enter a key：");
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
