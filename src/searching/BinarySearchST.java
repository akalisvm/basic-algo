package searching;

import java.util.Scanner;

// Binary search ST based on two sorted arrays
// choose Iter as optimized option
// position count from 0
public class BinarySearchST<Key extends Comparable<Key>, Value> {
    private Key[] keys;
    private Value[] vals;
    private int n;
    public BinarySearchST(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        vals = (Value[]) new Object[capacity];
    }

    public int size() { return n; }

    public boolean isEmpty() { return size() == 0; }

    public Value get(Key key) {
        if(isEmpty()) return null;
        int i = rankIter(key);
        if(i < n && keys[i].compareTo(key) == 0) return vals[i];
        else return null;
    }

    public int rankIter(Key key) {
        int lo = 0, hi = n-1;
        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(keys[mid]);
            if(cmp < 0) hi = mid - 1;
            else if(cmp > 0) lo = mid + 1;
            else return mid;
        }
        return lo;
    }

    public int rankRecursion(Key key) {
        return rankRecursion(key, 0, n-1);
    }

    public int rankRecursion(Key key, int lo, int hi) {
        if(hi < lo) return lo;
        int mid = lo + (hi - lo) / 2;
        int cmp = key.compareTo(keys[mid]);
        if(cmp < 0) return rankRecursion(key, lo, mid-1);
        else if(mid > 0) return rankRecursion(key, mid+1, hi);
        else return mid;
    }

    public void put(Key key, Value val) {
        int i = rankIter(key);
        if(i < n && keys[i].compareTo(key) == 0) {
            vals[i] = val;
            return;
        }
        for(int j = n; j > i; j--) {
            keys[j] = keys[j-1];
            vals[j] = vals[j-1];
        }
        keys[i] = key;
        vals[i] = val;
        n++;
    }

    public void print() {
        for(int i = 0; i < n; i++) {
            System.out.println(keys[i] + ": " + vals[i]);
        }
    }

    public static String getKey() {
        Scanner sc = new Scanner(System.in);
        return sc.next();
    }

    public static void main(String[] args) {
        String str = "SEARCHEXAMPLE";
        BinarySearchST<Character, Integer> st = new BinarySearchST<>(str.length());
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
