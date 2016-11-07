/**
 * Created by mo on 10/31/16.
 */

import java.util.*;
import java.io.*;

import static java.lang.Math.*;

public class PalindromicTree {
    public static int MAXN = 100100;
    public static int sig = 26;
    public static char BASE = 'a';
    public static char[] s = new char[MAXN];

    public static class State {
        int len;
        int link;
        int[] to = new int[sig];
    }
    static State[] st = new State[MAXN + 2];

    public static class EerTree {
        public static int last;
        public static int sz;
        public static int n;
        public EerTree() {
            last = 1;
            sz = 2;
            n = 0;
            st[0].len = st[0].link = -1;
            st[1].len = st[1].link = 0;
        }
        public static int extend() {
            char c = s[n++];
            int p = last;
            while (n - st[p].len - 2 < 0 || c != s[n - st[p].len - 2])
                p = st[p].link;
            if (st[p].to[c - BASE] != 1) {
                int q = last = sz++;
                st[p].to[c - BASE] = q;
                st[q].len = st[p].len + 2;
                do {
                    p = st[p].link;
                }
                while (p != -1 && (n < st[p].len + 2 || c != s[n - st[p].len - 2]));
                if (p == -1) st[q].link = 1;
                else st[q].link = st[p].to[c-BASE];
                return 1; }

            last = st[p].to[c - BASE];
            return 0;
            }
        }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        EerTree tree = new EerTree();
        String in = sc.next();
        int n = in.length();
        for (int i = 0; i < n; i++) {
            tree.extend();
        }
    }
}
