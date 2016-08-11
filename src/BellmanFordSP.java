/**
 * Created by mohammad on 8/10/16.
 */
import java.util.*;
import java.lang.*;
import java.io.*;


public class BellmanFordSP {
    class Edge{
        int src, dest, weight;
        Edge (){
            src = dest = weight = 0;
        }
    };

    int V, E;
    Edge edge[];

    //Creates a graph with V vertices and E edges
    BellmanFordSP(int v, int e){
        V = v;
        E = e;
        edge = new BellmanFordSP.Edge[e];
        for (int i = 0; i < e; i++)
            edge[i] = new Edge();
    }
    // The main function that finds shortest distances from src
    // to all other vertices using Bellman-Ford algorithm.  The
    // function also detects negative weight cycle
    void BellmanFord(BellmanFordSP graph, int src){
        int V = graph.V, E = graph.E;
        int dist[] = new int[V];

        //Step 1 : Initialize distances from src to all other verts as INF
        for (int i = 0; i < V; i++)
            dist[i] = Integer.MAX_VALUE;
        dist[src] = 0;
        /*Step 2: Relax all edges |V| - 1 times
        * Simple shortest path from src to any other vertex
        * can have at most |V| - 1 edges*/
        for (int i = 1; i < V; i++){
            for (int j = 0; j < E; j++){
                int u = graph.edge[j].src;
                int v = graph.edge[j].dest;
                int weight = graph.edge[j].weight;
                if (dist[u] != Integer.MAX_VALUE &&
                        dist[u] + weight < dist[v])
                    dist[v] = dist[u] + weight;
            }
        }
        /*Step 3: check for negative-weight cycles. The above
        * step guarantees shortest distances if graph doesn't
        * contain negative weight cycle. If we get a shorter path,
        * then there is a cycle*/
        for (int j = 0; j < E; j++){
            int u = graph.edge[j].src;
            int v = graph.edge[j].dest;
            int weight = graph.edge[j].weight;
            if (dist[u]!=Integer.MAX_VALUE &&
                    dist[u]+weight<dist[v])
                System.out.println("Graph contains negative weight cycle");
        }
        printArr(dist, V);
    }

    //A utility function used to print the solution
    void printArr(int[] dist, int V){
        System.out.println("Vertex   Distance from Source");
        for (int i = 0; i < V; i++)
            System.out.println(i + "\t\t" + dist[i]);
    }

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        /*Input is in the form
        V E S
        S1 D1 W1
        ........
        Se De We
        where V = Vertex, E = Edges, S = Source
        S1 = first source node, D1 = first end node, first edge weight
        through to e nodes, where e = E
        * */
        int V = in.nextInt();
        int E = in.nextInt();
        int S = in.nextInt();

        BellmanFordSP graph = new BellmanFordSP(V, E);
        for (int i = 0; i < E; i++) {
            graph.edge[i].src = in.nextInt();
            graph.edge[i].dest = in.nextInt();
            graph.edge[i].weight = in.nextInt();
        }
        graph.BellmanFord(graph, S);
    }
}
