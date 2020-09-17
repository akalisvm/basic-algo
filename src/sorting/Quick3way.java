package sorting;

public class Quick3way {
    public static void sort(Comparable[] a){
        sort(a, 0, a.length-1);
    }
    public static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int lt = lo, i = lo+1, gt = hi;
        Comparable v = a[lo];
        while (i <= gt) {
            if (less(a[i],v)) exchange(a, lt++, i++);
            else if (less(v,a[i])) exchange(a, i, gt--);
            else i++;
        }
        sort(a, lo, lt-1);
        sort(a, gt+1, hi);
    }
    private static boolean less(Comparable v, Comparable w){ return v.compareTo(w) < 0; }
    private static void exchange(Comparable[] a, int i, int j){
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
    private static void show(Comparable[] a){
        for (Comparable comparable : a) {
            System.out.print(comparable + " ");
        }
        System.out.println();
    }
    public static boolean isSorted(Comparable[] a){
        for(int i = 0; i < a.length; i++){
            if(less(a[i], a[i-1])) return false;
        }
        return true;
    }
    public static void main(String[] args){
        String[] a = new String[]{"S","O","R","T","E","X","A","M","P","L","E"};
        System.out.println("The array before sorting:");
        show(a);
        sort(a);
        assert isSorted(a);
        System.out.println("The array after sorting:");
        show(a);
    }
}
