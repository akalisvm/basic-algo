package string;

public class Quick3string {
    private static int charAt(String s, int d) {
        if(d < s.length()) {
            return s.charAt(d);
        } else {
            return -1;
        }
    }

    private static void sort(String[] a) {
        sort(a, 0, a.length-1, 0);
    }

    private static void exchange(Comparable[] a, int i, int j) {
        Comparable t = a[i]; a[i] = a[j]; a[j] = t;
    }

    private static void sort(String[] a, int lo, int hi, int d) {
        if(hi <= lo) return;
        int lt = lo, gt = hi;
        int v = charAt(a[lo], d);
        int i = lo + 1;
        while(i <= gt) {
            int t = charAt(a[i], d);
            if(t < v) {
                exchange(a, lt++, i++);
            } else if(t > v) {
                exchange(a, i, gt--);
            } else {
                i++;
            }
        }

        sort(a, lo, lt-1, d);
        if(v >= 0) {
            sort(a, lt, gt, d+1);
        }
        sort(a, gt+1, hi, d);
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
