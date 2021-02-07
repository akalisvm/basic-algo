package searching;

import java.util.Scanner;

// Hash table ST based on linear probe
// position counts from 0
public class LinearProbingHashST<Key, Value> {
    private int n;
    private int m;
    private Key[] keys;
    private Value[] vals;

    private LinearProbingHashST() {
        this(16);
    }

    private LinearProbingHashST(int m) {
        this.m = m;
        keys = (Key[]) new Object[m];
        vals = (Value[]) new Object[m];
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % m;
    }

    private void resize(int capacity) {
        LinearProbingHashST<Key, Value> st = new LinearProbingHashST<>(capacity);
        for (int i = 0; i < m; i++) {
            if(keys[i] != null) st.put(keys[i], vals[i]);
        }
        keys = st.keys;
        vals = st.vals;
        m = st.m;
    }

    private void put(Key key, Value val) {
        if(n >= m/2) resize(2*m);
        int i;
        for(i = hash(key); keys[i] != null; i = (i+1) % m) {
            if(keys[i].equals(key)) { vals[i] = val; return; }
        }
        keys[i] = key;
        vals[i] = val;
        n++;
    }

    private Value get(Key key) {
        for(int i = hash(key); keys[i] != null; i = (i+1) % m) {
            if(keys[i].equals(key)) { return vals[i]; }
        }
        return null;
    }

    private static String getKey() {
        Scanner sc = new Scanner(System.in);
        return sc.next();
    }

    public void print() {
        for(int i = 0; i < m; i++) {
            System.out.println(i + " -> " + keys[i] + ": " + vals[i]);
        }
    }

    public static void main(String[] args) {
        String str = "SEARCHEXAMPLE";
        LinearProbingHashST<Character, Integer> st = new LinearProbingHashST<>();
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
