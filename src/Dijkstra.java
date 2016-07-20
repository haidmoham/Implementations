/**
 * Created by Mohammad Haider (haidmoham) on 7/20/16.
 */
import java.util.*;

public class Dijkstra {
    static int N, E, S, T;
    static ArrayList<ArrayList<Edge>> edges;
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        N = scan.nextInt();
        E = scan.nextInt();
        S = scan.nextInt();
        T = scan.nextInt();
        edges = new ArrayList<ArrayList<Edge>>();
        int p;
        for (p = 0; p < N; p++){
            ArrayList<Edge> temp = new ArrayList<Edge>();
            edges.add(temp);
        }
        for (int i = 0; i < E; i++){
            int s = scan.nextInt();
            int e = scan.nextInt();
            int l = scan.nextInt();

            edges.get(s).add(new Edge(e, l, i));
            edges.get(e).add(new Edge(s, l, i));
        }
        PriorityQueue<Edge> queue = new PriorityQueue<Edge>();

        for (Edge e:edges.get(S))
            queue.add(e);
        boolean found = false;
        Set<Integer> seen = new HashSet<Integer>();
        int total = 0;
        while (!found){
            Edge e = queue.poll();
            if (!seen.contains(e.x)){
                seen.add(e.x);
                if (e.x == T){
                    found = true;
                    total = e.length;
                }
                else {
                    for (Edge k : edges.get(e.x)){
                        queue.add(new Edge(k.x, k.length + e.length, p++));
                    }
                }
            }
        }
        System.out.println(total + "");


    }
    static class Edge implements Comparable<Edge> {
        public int length;
        public int x;
        public int id;

        public Edge (int X, int L, int ID) {
            x = X;
            length = L;
            id = ID;
        }
        public int compareTo(Edge other) {
            int temp = length - other.length;
            if (temp != 0)
                return temp;
            return id - other.id;
        }

    }
}
