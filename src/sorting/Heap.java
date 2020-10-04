package sorting;

public class Heap {
    public static void sort(Comparable[] a){
        // heap order
        for(int i = a.length/2-1; i >= 0; i--){
            sink(a, i, a.length);
        }
        // sink sorting
        for(int j = a.length-1; j > 0; j--){
            exchange(a, 0, j);
            sink(a, 0, j);
        }
    }
    public static boolean less(Comparable v, Comparable w){ return v.compareTo(w) < 0; }

    public static void exchange(Comparable[] a, int i, int j){ Comparable t = a[i]; a[i] = a[j]; a[j] = t; }

    public static void sink(Comparable[] a, int i, int n){
        Comparable temp = a[i]; // assign the value of a[i] to temp as a temporary valuable
        for(int k =i*2+1; k < n; k = k*2+1){ //start from left child node, which is 2i+1
            if(k+1 < n && less(a[k], a[k+1])){
                // if left child node less than right child node, k++ to point to right child node
                k++;
            }
            if(less(temp, a[k])){
                // if child node larger than father node, assign father node to child node
                // (exchange operation is not needed here)
                a[i] = a[k];
                i = k;
            }
            else {
                break;
            }
        }
        a[i] = temp; // assign temp at final position to a[i]
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
