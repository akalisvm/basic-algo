package string;

public class MSD {
    private static int R = 256;
    private static final int M = 15;
    private static String[] aux;
    private static int charAt(String s, int d) {
        if(d < s.length()) {
            return s.charAt(d);
        } else {
            return -1;
        }
    }

    private static void sort(String[] a) {
        int N = a.length;
        aux = new String[N];
        sort(a, 0 , N-1, 0);
    }

    private static void insertionSort(String[] a, int lo, int hi, int d) {
        for(int i = lo; i <= hi; i++) {
            for(int j = i; j >lo && less(a[j], a[j-1], d); j--) {
                exchange(a, j, j-1);
            }
        }
    }

    private static boolean less(String v, String w, int d) {
        return v.substring(d).compareTo(w.substring(d)) < 0;
    }

    private static void exchange(Comparable[] a, int i, int j) {
        Comparable t = a[i]; a[i] = a[j]; a[j] = t;
    }

    private static void sort(String[] a, int lo, int hi, int d) {
        // Sorting from a[lo] to a[hi] according to the dth character.
        if (hi <= lo + M) {
            insertionSort(a, lo, hi, d);
            return;
        }

        int[] count = new int[R+2]; // Calculating the frequency to occurrences.
        for(int i = lo; i <- hi; i++) {
            count[charAt(a[i], d) + 2]++;
        }

        for(int r = 0; r < R+1; r++) { // Converting frequency to index.
            count[r+1] += count[r];
        }

        for(int i = lo; i <= hi; i++) { // Classifying the elements.
            aux[count[charAt(a[i], d) + 1]++] = a[i];
        }

        for(int i = 0; i <= hi; i++) { // Writing the elements back.
            a[i] = aux[i-lo];
        }

        for(int r = 0; r < R; r++) { // Sorting each character as key recursively.
            sort(a, lo + count[r], lo + count[r+1] - 1, d+1);
        }
    }

    public static void print(String[] a) {
        for(String s : a) {
            System.out.println(s);
        }
    }

    public static void main(String[] args) {
        String[] array = new String[]{
                "she", "sells", "seashells",
                "by", "the", "sea", "shore",
                "the", "shells", "she", "sells",
                "are", "surely", "seashells"
        };
        sort(array);
        print(array);
    }
}
