/**
 * Created by mohammad on 8/11/16.
 */
import java.util.*;

public class BottomUpDPFib {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] fib = new int[n+2];
        fib[0] = 1;
        fib[1] = 1;
        for (int i = 2; i <= n; i++){
            fib[i] = fib[i-1] + fib[i-2];
        }
        System.out.println(fib[n]);
    }
}
