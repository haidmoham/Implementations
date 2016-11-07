/**
 * Created by mo on 10/2/16.
 */

import java.util.*;
import java.io.*;
import static java.lang.Math.*;


public class Hardy_Ramanujan_Partitions {
    static double e = E;
    static double c = PI * (sqrt(2) / sqrt(3));
    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
        System.out.println(partition(sc.nextInt()));
    }
    public static double partition(int n){
        /*
        Calculates the number of unordered ways to partition a number
        in constant time. Accurate in terms of percentage, but not as accurate on smaller cases.
        Perhaps code two different versions, one for small numbers, one for large?
         */
        double numerator = pow(e, c * sqrt(n));
        double denominator = (4 * n * sqrt(3));
        return numerator / denominator;
    }

    public static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        public FastScanner(Reader in) {
            br = new BufferedReader(in);
        }

        public FastScanner() {
            this(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String readNextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }

        int[] readIntArray(int n) {
            int[] a = new int[n];
            for (int idx = 0; idx < n; idx++) {
                a[idx] = nextInt();
            }
            return a;
        }

        long[] readLongArray(int n) {
            long[] a = new long[n];
            for (int idx = 0; idx < n; idx++) {
                a[idx] = nextLong();
            }
            return a;
        }
    }
}
