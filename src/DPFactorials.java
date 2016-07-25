/**
 * Created by mohammad on 7/25/16.
 */
import java.util.*;
public class DPFactorials {
    public static long factorial(int N){
        long[] fact = new long[N+1];
        fact[0] = 1;
        for (int i = 1; i < N+1; i++){
            fact[i] = i * fact[i-1];
        }
        return fact[N];
    }
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int N = s.nextInt();
        System.out.println(factorial(N));
    }
}
