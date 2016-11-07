/**
 * Created by mo on 9/27/16.
 */

import java.util.*;
import java.io.*;

public class SimpleDijkstra {
    static HashMap<Integer, Integer>[] adjacent;
    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
        int n = sc.nextInt(), e = sc.nextInt(), s = sc.nextInt(), t = sc.nextInt();
        adjacent = new HashMap[n];
        for (int i = 0; i < n; i++)
            adjacent[i] = new HashMap<>();
        for (int i = 0; i < e; i++){
            int a = sc.nextInt(), b = sc.nextInt(), dist = sc.nextInt();
            adjacent[a].put(b, dist);
            adjacent[b].put(a, dist);
        }

        State fin = dijkstra(new State(s, 0), t);
        System.out.println(fin.distance);
    }

    static State dijkstra(State start, int end){
        PriorityQueue<State> pq = new PriorityQueue<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        pq.offer(start);
        map.put(start.node, 0);
        while(!pq.isEmpty()){
            State curr = pq.poll();
            if (!map.containsKey(curr.node) || curr.distance <= map.get(curr.node)) {
                if (curr.node == end)
                    return curr;
                for (State s : curr.getNeighbors()) {
                    if (!map.containsKey(s.node) || s.distance < map.get(s.node)) {
                        pq.offer(new State(s.node, s.distance));
                        map.put(s.node, s.distance);
                    }
                }
            }
        }
        return null;
    }

    static class State implements Comparable<State> {
        int node;
        int distance;

        public State(int n, int d){
            node = n;
            distance = d;
        }

        public int HashCode() {
            return node;
        }

        public int compareTo(State s){
            return Integer.compare(distance, s.distance);
        }

        public boolean equals(Object o){
            State s = (State) o;
            if (node == s.node) return true;
            return false;
        }

        public ArrayList<State> getNeighbors() {
            ArrayList<State> neighbors = new ArrayList<State>();
            for (Integer i : adjacent[node].keySet()) {
                State s = new State(i, distance + adjacent[node].get(i));
                neighbors.add(s);
            }
            return neighbors;
        }
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
