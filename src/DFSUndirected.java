/**
 * Created by mohammad on 8/9/16.
 */
import java.util.*;

public class DFSUndirected {
//    recursive dfs
    private static void dfs_rec(ArrayList<ArrayList<Integer>> adj, boolean[] visited, int v){
        visited[v] = true;
        System.out.print(v + " ");
        for (int w : adj.get(v)){
            if (!visited[w])
                dfs_rec(adj, visited, w);
        }
    }
    /*dfs method for not having to pass bool array*/
    private static void dfs(ArrayList<ArrayList<Integer>> adj, int s){
        int n = adj.size();
        boolean[] visited = new boolean[n];
        dfs_rec(adj, visited, s);
    }

    public static void main(String[] args){
        Scanner s = new Scanner(System.in);

        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        int n = s.nextInt();
        int start = s.nextInt();
        for (int i = 0; i < n; i++)
            graph.add(new ArrayList<>());

        for (int i = 0; i < n; i++){
            int u = s.nextInt();
            int v = s.nextInt();

            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        dfs(graph, start);
    }
}
