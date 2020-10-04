package sorting;

class MaxPQ<Key extends Comparable<Key>>{
    private Key[] pq;
    private int n = 0;
    public MaxPQ (int maxN){
        pq = (Key[]) new Comparable[maxN+1];
    }

    public boolean isEmpty(){ return n == 0; }

    public int size(){ return n; }

    public void insert(Key v){
        pq[++n] = v;
        swim(n);
    }

    public Key delMax(){
        Key max = pq[1];
        exchange(1, n--);
        pq[n+1] = null;
        sink(1);
        return max;
    }

    public boolean less(int i, int j){ return pq[i].compareTo(pq[j]) < 0; }

    private void exchange(int i, int j){ Key t = pq[i]; pq[i] = pq[j]; pq[j] = t; }

    private void swim(int k){
        while(k > 1 && less(k/2, k)){
            exchange(k/2, k);
            k = k/2;
        }
    }

    private void sink(int k){
        while (2*k <= n){
            int j = 2*k;
            if (j < n && less(j, j+1)) j++;
            if (!less(k, j)) break;
            exchange(k, j);
            k = j;
        }
    }
}
