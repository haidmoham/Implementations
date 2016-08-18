/**
 * Created by mohammad on 8/17/16.
 */
import java.util.*;

public class MedianFinding {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int[] arr = {12, 16, -8, 4};
        Arrays.sort(arr);
        int n = arr.length;
        if (n % 2 == 0){
            System.out.println((arr[n/2-1] + arr[n/2]) / 2);
        }
        else System.out.println(arr[n/2]);
    }
}
