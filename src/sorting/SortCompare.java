package sorting;

class Stopwatch {
    private final long start;
    public Stopwatch() { start = System.currentTimeMillis(); }
    public double elapsedTime() {
        long now = System.currentTimeMillis();
        return (now - start) / 1000.0;
    }
}

public class SortCompare {
    public static double time(String alg, Comparable[] a){
        Stopwatch timer = new Stopwatch();
        if (alg.equals("Bubble")) Bubble.sort(a);
        if (alg.equals("Selection")) Selection.sort(a);
        if (alg.equals("Insertion")) Insertion.sort(a);
        if (alg.equals("Shell")) Shell.sort(a);
        if (alg.equals("MergeUB")) MergeUB.sort(a);
        if (alg.equals("MergeBU")) MergeBU.sort(a);
        if (alg.equals("Quick")) Quick.sort(a);
        if (alg.equals("Quick3way")) Quick3way.sort(a);
        if (alg.equals("Heap")) Heap.sort(a);
        return timer.elapsedTime();
    }

    public static double timeRandomInput(String alg, int N, int T) {
        double total = 0.0;
        Comparable[] a = new Comparable[N];
        for(int t = 0; t < T; t++) {
            for(int i = 0; i < N; i++) {
                a[i] = Math.random();
            }
            total += time(alg, a);
        }
        return total;
    }

    public static void main(String[] args) {
        String alg1 = "Shell";
        String alg2 = "Insertion";
        int N = 1000;
        int T = 100;
        double t1 = timeRandomInput(alg1, N, T);
        double t2 = timeRandomInput(alg2 , N, T);
        System.out.printf("For %d random Doubles\n  %s is", N, alg1);
        System.out.printf(" %.1f times faster than %s\n", t2/t1, alg2);
    }
}
