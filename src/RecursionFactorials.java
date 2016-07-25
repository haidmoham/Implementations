/**
 * Created by mohammad on 7/25/16.
 */
import java.util.*;
public class RecursionFactorials {
    public static long factorial(int N) {
        if (N == 0) return 1;
        return N * factorial(N-1);
    }

    public static int fib(int N){
        if (N == 0) return 0;
        if (N == 1) return 1;
        return fib(N-1) + fib(N-2);
    }
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int N = s.nextInt();
        System.out.println(factorial(N));
    }
}
