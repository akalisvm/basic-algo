package searching;

public class SparseVector {
    private int n;
    private SequentialSearchST<Integer, Double> st;

    public SparseVector(int n) {
        this.n = n;
        st = new SequentialSearchST<>();
    }

    public void put(int i, double x) { st.put(i, x); }

    public double get(int i) {
        if (!st.contains(i)) return 0.0;
        else return st.get(i);
    }

    public double dot(double[] x) {
        double sum = 0.0;
        for (int i = 0; i < n; i++) {
            if(st.get(i) != null){
                sum += x[i] * st.get(i);
            }
        }
        return sum;
    }

    public void print() { st.print(); }

    public static void main(String[] args) {
        double[][] rawA = new double[][]{
                {0.0,   0.90,   0.0,    0.0,    0.0},
                {0.0,   0.0,    0.36,   0.36,   0.18},
                {0.0,   0.0,    0.0,    0.90,   0.0},
                {0.90,  0.0,    0.0,    0.0,    0.0},
                {0.47,  0.0,    0.47,   0.0,    0.0}
        };
        int n = rawA.length;
        int m = rawA[0].length;
        SparseVector[] a = new SparseVector[5];
        for(int i = 0; i < n; i++) {
            a[i] = new SparseVector(m);
        }
        double[] x = new double[]{0.05, 0.04, 0.36, 0.37, 0.19};
        double[] b = new double[n];
        for(int i = 0; i < rawA.length; i++) {
            for(int j = 0; j < rawA[i].length; j++) {
                if(rawA[i][j] != 0.0) {
                    a[i].put(j, rawA[i][j]);
                }
            }
        }
        for(int i = 0; i < a.length; i++) {
            System.out.print(i + " -> " + " ");
            a[i].print();
            System.out.println();
        }
        for(int i = 0; i < n; i++) {
            b[i] = a[i].dot(x);
        }
        System.out.println();
        for(double d : b) {
            System.out.printf("%.4f\n", d);
        }
    }
}
