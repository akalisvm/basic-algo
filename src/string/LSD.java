package string;

public class LSD {
    public static void sort(String[] a, int w) {
        int n = a.length;
        int r = 256;
        String[] aux = new String[n];

        for(int d = w-1; d >= 0; d--) {

            int[] count = new int[r+1];
            for(int i = 0; i < n; i++) {
                count[a[i].charAt(d)+1]++;
            }

            for(int i = 0; i < r; i++) {
                count[i+1] += count[i];
            }

            for(int i = 0; i < n; i++) {
                aux[count[a[i].charAt(d)]++] = a[i];
            }

            for(int i = 0; i < n; i++) {
                a[i] = aux[i];
            }
        }
    }

    public static void print(String[] a) {
        for(String s : a) {
            System.out.println(s);
        }
    }

    public static void main(String[] args) {
        String[] array = new String[]{
                "4PGC938", "2IYE230", "3CI0720",
                "1ICK750", "10HV845", "4JZY524",
                "2RLA629", "3ATW723"
        };
        sort(array, 7);
        print(array);
    }
}
