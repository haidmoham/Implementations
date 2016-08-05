/**
 * Created by mohammad on 8/5/16.
 */
import java.util.*;

public class HeapSort {
    private static int N;

    public static void sort(int arr[]){
        heapify(arr);
        for (int i = N; i > 0; i--){
            swap(arr, 0, i);
            N = N - 1;
            maxheap(arr, 0);
        }
    }
    public static void heapify(int arr[]) {
        N = arr.length-1;
        for (int i = N/2; i >= 0; i--)
            maxheap(arr, i);
    }
    public static void maxheap(int arr[], int i){
        int left = 2 * i;
        int right = 2 * i + 1;
        int max = i;
        if (left <= N && arr[left] > arr[i])
            max = left;
        if (right <= N && arr[right] > arr[max])
            max = right;

        if (max != i){
            swap(arr, i, max);
            maxheap(arr, max);
        }
    }
    public static void swap(int arr[], int i, int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        System.out.println("Heap Sort Test\n");
        int n, i;
        //# of elements
        System.out.println("Enter number of elements: ");
        n = s.nextInt();
        //Array of n elements
        System.out.println("Enter elements: ");
        int[] arr = new int[n];
        for (i = 0; i < n; i++)
            arr[i] = s.nextInt();
        //Sort array
        sort(arr);
        System.out.println("\nElements after sorting: ");
        for (i = 0; i < n; i++)
                System.out.print(arr[i] + " ");
        System.out.println();
    }

}
