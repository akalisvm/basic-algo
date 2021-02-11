package string;

import java.util.Random;

public class RabinKarp {
    private long patHash;
    private int M;
    private long Q;
    private int R = 256;
    private long RM;

    private RabinKarp(String pat) {
        this.M = pat.length();
        Q = longRandomPrime(); // a random long type prime number
        RM = 1;
        for(int i = 1; i <= M-1; i++) {
            RM = (R * RM) % Q;
        }
        patHash = hash(pat, M);
    }

    private boolean check(int i) {
        return true;
    }

    private long hash(String key, int M) {
        long h = 0;
        for(int j = 0; j < M; j++) {
            h = (R * h + key.charAt(j) % Q);
        }
        return h;
    }

    private int search(String txt) {
        int N = txt.length();
        long txtHash = hash(txt, M);
        if(patHash == txtHash && check(0)) return 0;
        for(int i = M; i < N; i++) {
            txtHash = (txtHash + Q - RM*txt.charAt(i-M) % Q) % Q;
            txtHash = (txtHash*R + txt.charAt(i) % Q);
            if(patHash == txtHash) {
                if(check(i - M + 1)) {
                    return i - M + 1;
                }
            }
        }
        return N;
    }

    private long longRandomPrime() {
        int max= 0, min;
        Random num = new Random();

        for(int i = 0; i < 31; i++) {
            max += (long) Math.pow(2, i);
        }
        min = (int) Math.pow(2, 30);

        long randomNum = num.nextInt(max-min+1) + min;
        if(isPrime(randomNum)) {
            return randomNum;
        }
        return longRandomPrime();
    }

    private static boolean isPrime(long num) {
        for(int i = 2; i < num; i++) {
            if(num % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String pat = "AACAA";
        String txt = "AABRAACADABRAACAADABRA";
        RabinKarp rabinKarp = new RabinKarp(pat);
        System.out.println("text: " + txt);
        int offset = rabinKarp.search(txt);
        System.out.println("pattern: " + pat);
        System.out.println(txt);
        for(int i = 0; i < offset; i++) {
            System.out.print(" ");
        }
        System.out.println(pat);
        System.out.println("offset: " + offset);
    }
}
