package string;

public class LSD {
    // Sorting strings in an array according the beginning W characters.
    public static void sort(String[] a, int W) {
        int N = a.length;
        int R = 256;
        String[] aux = new String[N];

        for(int d = W-1; d >= 0; d--) {
            // Key-indexed Sorting according the dth character.

            int[] count = new int[R+1];
            for(int i = 0; i < N; i++) { // Calculating the frequency to occurrences.
                count[a[i].charAt(d)+1]++;
            }

            for(int i = 0; i < R; i++) { // Converting frequency to index.
                count[i+1] += count[i];
            }

            for(int i = 0; i < N; i++) { // Classifying the elements.
                aux[count[a[i].charAt(d)]++] = a[i];
            }

            for(int i = 0; i < N; i++) { // Writing the elements back.
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
