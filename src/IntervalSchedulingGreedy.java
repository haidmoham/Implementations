/**
 * Created by mohammad on 8/17/16.
 */
import java.io.IOError;
import java.io.IOException;
import java.util.*;

public class IntervalSchedulingGreedy {
    static final int N = 20;
    static int[] Start = new int[N];
    static int[] Finish = new int[N];
    static int[] Value = new int[N];

    static private void init(){
        Random ranGenerator = new Random();
        //create random start and finish times
        for (int i = 0; i < N; ++i){
            int s = ranGenerator.nextInt(N/2);
            int f = ranGenerator.nextInt(N/2);
            while (s == f) f = ranGenerator.nextInt(N/2);
            if (s > f) { int x = s; s = f; f = x; }
            Start[i] = i + s;
            Finish[i] = i + f;
            Value[i] = ranGenerator.nextInt(N);
        }

        log("\nJobs start times:\n");
        printTable(Start);
        log("\nJob values:\n");
        printTable(Finish);
    }

    public static void printTable(int[] tab){
        for (int i = 0; i < N; i++){
            int x = tab[i] + 1;
            if (x < 10) log(" " + x);
            else log(" " + x);
        }
        log("\n");
    }

    private static void log(String aMessage){
        System.out.print(aMessage);
    }

    public static void main(String[] args) throws IOException {
        init();

        //Sort jobs by finish time via insertionSort
        int[] orderFinish = new int[N];
        for (int i = 0; i < N; i++) orderFinish[i] = i;

        for (int out = 1; out < N; out++){
            int temp = orderFinish[out]; //remove marked
            int in = out;                //start shifts at out until one smaller
            while (in > 0 && Finish[orderFinish[in-1]] > Finish[temp]) {
                orderFinish[in] = orderFinish[in - 1]; //shift to right
                --in;                                //go left one pos
            }
            orderFinish[in] = temp; //insert marked
            }
            log("\nJobs  sorted by finish times:\n");
        printTable(orderFinish);

        //compute soln
        int[] solutionSet = new int[N];
        int solution = 0;

        int f = 0;
        for (int i = 0; i < N; i++){
            int j = orderFinish[i];
            if (f <= Start[j]){
                solutionSet[solution++] = j;
                f = Finish[j];
            }
        }
        log("\nMaximum set of compatible jobs:\n");
        for (int i = 0; i < solution; i++) log ((1 + solutionSet[i]) + ", ");
    }
}
