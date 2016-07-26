/**
 * Created by mohammad on 7/24/16.
 */
import java.util.*;

public class BFSShortestPath {
    public ArrayList<Integer> bfs(ArrayList<ArrayList<Integer>> graph, int start, int end){
        Queue<Integer> schedule = new ArrayDeque<Integer>();
        boolean[] visited = new boolean[graph.size()];
        int[] parent = new int[graph.size()];

        schedule.add(start);
        visited[start] = true;
        parent[start] = -1;

        while (!schedule.isEmpty()){
            int next = schedule.remove();

            if (next == end){
                ArrayList<Integer> path = new ArrayList<Integer>();
                int temp = end;
                while (temp != -1){
                    path.add(temp);
                    temp = parent[temp];
                }
                Collections.reverse(path);
                return path;
            }

            for (int neighbor : graph.get(next)){
                if (!visited[neighbor]) {
                    schedule.add(neighbor);
                    visited[neighbor] = true;
                    parent[neighbor] = next;
                }
            }
        }
        return null;
    }
}
