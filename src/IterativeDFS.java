import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by mohammad on 9/1/16.
 */

public class IterativeDFS {
    // Use a stack for the iterative DFS version
    public static String dfs_iterative(ArrayList<ArrayList<Integer>> adj, int s){
        boolean[] visited = new boolean[adj.size()];
        Stack<Integer> st = new Stack<Integer>();
        StringBuilder sb = new StringBuilder();
        st.push(s);
        while(!st.isEmpty()){
            int v = st.pop();
            if(!visited[v]){
                visited[v] = true;
                sb.append(v + " ");
                // auxiliary stack to visit neighbors in the order they appear
                // in the adjacency list
                // alternatively: iterate through ArrayList in reverse order
                // but this is only to get the same output as the recursive dfs
                // otherwise, this would not be necessary
                Stack<Integer> auxStack = new Stack<Integer>();
                for(int w : adj.get(v)){
                    if(!visited[w]){
                        auxStack.push(w);
                    }
                }
                while(!auxStack.isEmpty()){
                    st.push(auxStack.pop());
                }
            }
        }
        return sb.toString();
    }


    // ----------------------------------------------------------------------
    // Testing our implementation
    public static void main(String[] args) {

        // Create adjacency list representation
        ArrayList<ArrayList<Integer>> adjLists = new ArrayList<ArrayList<Integer>>();
        final int n = 7;

        // Add an empty adjacency list for each vertex
        for(int v=0; v<n; v++){
            adjLists.add(new ArrayList<Integer>());
        }

        // insert neighbors of vertex 0 into adjacency list for vertex 0
        adjLists.get(0).add(1);
        adjLists.get(0).add(2);
        adjLists.get(0).add(3);

        // insert neighbors of vertex 1 into adjacency list for vertex 1
        adjLists.get(1).add(5);
        adjLists.get(1).add(6);

        // insert neighbors of vertex 2 into adjacency list for vertex 2
        adjLists.get(2).add(4);

        // insert neighbors of vertex 3 into adjacency list for vertex 3
        adjLists.get(3).add(2);
        adjLists.get(3).add(4);

        // insert neighbors of vertex 4 into adjacency list for vertex 4
        adjLists.get(4).add(1);

        // insert neighbors of vertex 5 into adjacency list for vertex 5
        // -> nothing to do since vertex 5 has no neighbors

        // insert neighbors of vertex 6 into adjacency list for vertex 5
        adjLists.get(6).add(4);

        // Print vertices in the order in which they are visited by dfs()
        System.out.println(dfs_iterative(adjLists, 0));
    }
}
