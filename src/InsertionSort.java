/**
 * Created by Mohammad Haider (haidmoham) on 7/19/16.
 */
import java.util.*;

public class InsertionSort {
    public static void main(String[] args){
        long start = System.currentTimeMillis();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();
        int[] arr2 = doInsertionSort(arr);
        long end = System.currentTimeMillis();
        System.out.println(end - start);

    }
    public static int[] doInsertionSort(int[] input){
        int temp;
        for (int i = 1; i < input.length; i++){
            for (int j = i; j > 0; j --){
                if (input[j] < input[j-1]){
                    temp = input[j];
                    input[j] = input[j-1];
                    input[j-1] = temp;
                }
            }
        }
        return input;
    }
}
