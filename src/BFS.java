/**
 * Created by mohammad on 7/24/16.
 */
import java.util.*;

public class BFS {
    static int bfs(ArrayList<ArrayList<Integer>> graph, int start, int end){
        int[] dist = new int[graph.size()];

        Queue<Integer> queue = new ArrayDeque<>();
        Set<Integer> visited = new HashSet<>();

        queue.offer(start);
        visited.add(start);
        dist[start] = 0;

        while(!queue.isEmpty()){
            int current = queue.poll();
            int d = dist[current];
            if(current == end) return d;

            for (int adj : graph.get(current)){
                if (!visited.contains(adj)){
                    visited.add(adj);
                    queue.offer(adj);
                    dist[adj] = d+1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n, e, s, b;
        n = sc.nextInt();
        e = sc.nextInt();
        s = sc.nextInt();
        b = sc.nextInt();

        int u,v;
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++)
            graph.add(new ArrayList<Integer>());
        for (int i = 0; i < e; i++){
            u = sc.nextInt();
            v = sc.nextInt();
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        int dist = bfs(graph, s, b);
        int min = dist / 6 + (dist % 6 == 0 ? 0 : 1); //Not a part of bfs, specifically for Problem 2 of PCS
        System.out.println(min + " " + dist);
    }
}
