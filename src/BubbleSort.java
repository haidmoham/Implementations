/**
 * Created by Mohammad Haider (haidmoham) on 7/19/16.
 */
import java.util.*;

public class BubbleSort {
    public static void main(String[] args){
        long start = System.currentTimeMillis();
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int[] num = new int[n];
        for (int i = 0; i < n; i++){
            num[i] = s.nextInt();
        }
        int l, r, temp;
        l = 0;
        r = 1;

        for (int i = l; i < n - 1; i++){
            if (num[l] > num[r]){
                temp = num[l];
                num[l] = num[r];
                num[r] = temp;
            }
            l++;
            r++;
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}