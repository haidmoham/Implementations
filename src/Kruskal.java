/**
 * Created by mohammad on 8/10/16.
 */
import java.util.*;
import java.lang.*;
import java.io.*;

public class Kruskal {
    class Edge implements Comparable<Edge>{
        int src, dest, weight;

        //Comparator function used for sorting edges by wght
        public int compareTo(Edge compareEdge){
            return this.weight - compareEdge.weight;
        }
    };
    //Class for subset for union-find
    class subset {
        int parent, rank;
    };

    int V, E;       // V = # of Vertices, E = # of edges
    Edge edge[];    // collection of all edges

    //Creates a graph with V vertices and E edges
    Kruskal(int v, int e){
        V = v;
        E = e;
        edge = new Edge[E];
        for (int i = 0; i < e; i++)
            edge[i] = new Edge();
    }

    //util function for finding set of a DJS (uses path compression)
    int find(subset subsets[], int i){
        //find root & make root as parent of i via path comp
        if (subsets[i].parent != i)
            subsets[i].parent = find(subsets, subsets[i].parent);
        return subsets[i].parent;
    }
    //Does union of two sets x and y
    void Union(subset subsets[], int x, int y){
        int xroot = find(subsets, x);
        int yroot = find(subsets, y);

        //Attach smaller rank under root of high rank tree
        //Union by rank, not naive
        if (subsets[xroot].rank < subsets[yroot].rank)
            subsets[xroot].parent = yroot;
        else if (subsets[xroot].rank > subsets[yroot].rank)
            subsets[yroot].parent = xroot;

        //If ranks are same, make arbitrary choice and inc rank
        else {
            subsets[yroot].parent = xroot;
            subsets[xroot].rank++;
        }
    }
    void KruskalMST(){
        Edge result[] = new Edge[V];    //Store the resultant MST
        int e = 0;                      //Indexing variable, used for result[]
        int i = 0;                      //indexing variable, used for sorted edges
        for (i = 0; i < V; i++)
            result[i] = new Edge();

        /*Step 1: Sort all edges in non-decreasing order by weight
        * If we aren't allowed to change the given graph, we
        * can create a copy of array of edges*/
        Arrays.sort(edge);

        //Allocate memory for creating V subsets
        subset subsets[] = new subset[V];
        for (i = 0; i < V; i++)
            subsets[i] = new subset();

        //Create V subsets with single elements
        for (int v = 0; v < V; v++){
            subsets[v].parent = v;
            subsets[v].rank = 0;
        }
        i = 0; //Index used to pick next edge

        //Number of edges to be taken is equal to V-1
        while (e < V - 1){
            /*Step 2: Pick the smallest edge, and increment the index
            * for next iteration*/
            Edge next_edge = new Edge();
            next_edge = edge[i++];

            int x = find(subsets, next_edge.src);
            int y = find(subsets, next_edge.dest);

            /*If addition doesn't create a cycle, include
            * In result and increment the index of result for next edge*/
            if (x != y){
                result[e++] = next_edge;
                Union(subsets, x, y);
            }
            //Else discard the next edge
        }

        //Print the contents of result to display the built MST
        for (i = 0; i < e; i++)
            System.out.println(result[i].src + " -- " + result[i].dest + " == "
            + result[i].weight);
    }
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int V = in.nextInt();
        int E = in.nextInt();
        Kruskal graph = new Kruskal(V, E);
        /*Input is of the form
        * V E
        * S1 D1 W1
        * ........
        * Se De We
        * where V = # of vert, E = # of edge,
        * S1 = first source node, D1 = first end node, first edge weight
        through to e nodes, where e = E
        For this implementation, node numbering must start at 0
        */
        for (int i = 0; i < E; i++){
            graph.edge[i].src = in.nextInt();
            graph.edge[i].dest = in.nextInt();
            graph.edge[i].weight = in.nextInt();
        }
        graph.KruskalMST();
    }
}
