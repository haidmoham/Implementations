/**
 * Created by Mohammad Haider (haidmoham) on 7/20/16.
 */
import java.util.*;

public class RandomArrayGenerator {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int[] ar = new int[n];
        System.out.println(n);
        for (int i = 0; i < n; i++) {
            ar[i] = (int) (Math.random() * n * n + 1);
            System.out.print(ar[i] + " ");
        }
    }
}
