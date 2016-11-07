/**
 * Created on 11/7/16.
 */

import java.util.*;
import java.io.*;

import static java.lang.Math.*;

/**
 * Implemented from pseudocode on wikipedia
 * https://en.wikipedia.org/wiki/Merge_sort
 */
public class TopDownMergeSort {
    public static void main(String[] args) {
        //FastScanner sc = new FastScanner();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        int[] b = new int[n];
        for (int i = 0; i < n; i++)
            a[i] = sc.nextInt();
        //apparently calling it twice makes it sort???
        topDownMergeSort(a, b, n);
        topDownMergeSort(a, b, n);
        for (int i = 0; i < n; i++)
            System.out.print(b[i] + " ");
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

    public static void topDownMergeSort(int[] A, int[] B, int n) {
        copyArray(A, 0, n, B);
        topDownSplitMerge(B, 0, n, A);
    }

    public static void copyArray(int[] A, int begin, int end, int[] B) {
        for (int i = begin; i < end; i++)
            B[i] = A[i];
    }
    public static void topDownSplitMerge(int[] B, int begin, int end, int[] A){
        if (end - begin < 2) {
            return;
        }
        int mid = (end + begin) / 2;
        topDownSplitMerge(A, begin, mid, B);
        topDownSplitMerge(A, mid, end, B);

        topDownMerge(B, begin, mid, end, A);
    }
    public static void topDownMerge(int[] A, int begin, int mid, int end, int[] B) {
        int i = begin, j = mid;
        for (int k = begin; k < end; k++) {
            if (i < mid && (j >= end || A[i] <= A[j])) {
                B[k] = A[i];
                i++;
            }
            else {
                B[k] = A[j];
                j++;
            }
        }
    }
}
