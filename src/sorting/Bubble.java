package sorting;

public class Bubble {
    public static void sort(Comparable[] a) {
        int n = a.length;
        for(int i = 0; i < n-1; i++){
            for(int j = 0; j < n-1; j++) {
                if(less(a[j+1],a[j])) {
                    exchange(a, j, j+1);
                }
            }
        }
    }

    private static boolean less(Comparable v, Comparable w){
        return v.compareTo(w) < 0;
    }

    private static void exchange(Comparable[] a, int i, int j) {
        Comparable t = a[i]; a[i] = a[j]; a[j] = t;
    }

    private static void show(Comparable[] a) {
        for (Comparable comparable : a) {
            System.out.print(comparable + " ");
        }
        System.out.println();
    }

    private static boolean isSorted(Comparable[] a) {
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
