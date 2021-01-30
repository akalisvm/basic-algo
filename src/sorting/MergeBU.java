package sorting;

public class MergeBU {
    //Merge Sorting from Bottom to Up
    private static Comparable[] aux;
    public static void sort(Comparable[] a) {
        int n = a.length;
        aux = new Comparable[n];
        for(int sz = 1; sz < n; sz = 2*sz) {
            for(int lo = 0; lo < n-sz; lo += 2*sz) {
                merge(a, lo, lo+sz-1, Math.min(lo+sz+sz-1, n-1));
            }
        }
    }

    private static void merge(Comparable[] a, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;
        Comparable[] aux = new Comparable[a.length];
        for(int k = lo; k <= hi; k++) aux[k] = a[k];
        for(int k = lo; k <= hi; k++) {
            if(i > mid) a[k] = aux[j++];
            else if(j > hi) a[k] = aux[i++];
            else if(less(aux[j], aux[i])) a[k] = aux[j++];
            else a[k] = aux[i++];
        }
    }

    private static boolean less(Comparable v, Comparable w){
        return v.compareTo(w) < 0; }


    private static void show(Comparable[] a) {
        for (Comparable comparable : a) {
            System.out.print(comparable + " ");
        }
        System.out.println();
    }

    private static boolean isSorted(Comparable[] a) {
        for(int i = 0; i < a.length; i++) {
            if(less(a[i], a[i-1])) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String[] a = new String[]{"S","O","R","T","E","X","A","M","P","L","E"};
        System.out.println("The array before sorting:");
        show(a);
        sort(a);
        assert isSorted(a);
        System.out.println("The array after sorting:");
        show(a);
    }
}
