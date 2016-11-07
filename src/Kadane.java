/**
 * Created by mo on 10/5/16.
 */

import java.util.*;
import java.io.*;

public class Kadane {
    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
        int T = sc.nextInt();
        for (int i = 0; i < T; i++){
            int n = sc.nextInt();
            int[] in = new int[n];
            for (int j = 0; j < n; j++)
                in[j] = sc.nextInt();
            int out = biggestSubSum(in);
            for (int j = 0; j < n; j++){
                int temp = in[j];
                in[j] = 0;
                int check = biggestSubSum(in);
                //System.err.println(check);
                out = check > out ? check : out;
                in[j] = temp;
            }
            System.out.println(out);
        }
    }
    public static int biggestSubSum (int[] arr) {
        int maxHere = arr[0], maxSoFar = arr[0];
        for (int i = 0; i < arr.length; i++){
            maxHere = Math.max(Integer.MIN_VALUE, maxHere + arr[i]);
            maxSoFar = Math.max(maxSoFar, maxHere);
        }
        return maxSoFar;
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
