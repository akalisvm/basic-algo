package searching;

import java.util.Arrays;
import java.util.Scanner;

// Binary search with both iter and recursion
// position count from 0
public class BinarySearchExample {
    public static int binarySearchIter(int[] a, int key) {
        int lo = 0, hi = a.length-1, mid;
        while(lo < hi) {
            mid = lo + (hi - lo) / 2;
            if(key < a[mid]) hi = mid-1;
            else if(key > a[mid]) lo = mid+1;
            else return mid;
        }
        return -1;
    }

    public static int binarySearchRecursion(int[] a, int key) {
        return binarySearchRecursion(a, key, 0, a.length-1);
    }

    public static int binarySearchRecursion(int[] a, int key, int lo, int hi) {
        if(lo >= hi) return -1;
        int mid = lo + (hi - lo) / 2;
        if(key < a[mid]) return binarySearchRecursion(a, key, lo, mid-1);
        else if(key > a[mid]) return binarySearchRecursion(a, key, mid+1, hi);
        else return mid;
    }

    public static void print(int[] a) {
        for(int i : a) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static int getKey() {
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }

    public static void main(String[] args) {
        int[] array = new int[10];
        for(int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * 100);
        }
        Arrays.sort(array);
        print(array);
        System.out.print("Enter a key: ");
        int i = getKey();
        System.out.println("Iter:\n"+ "try to search: "+ i + "\n"
                + i + " is at position "+ binarySearchIter(array, i));
        System.out.println("Recursion:\n"+ "try to search: "+ i + "\n"
                + i + " is at position "+ binarySearchRecursion(array, i));
    }
}
