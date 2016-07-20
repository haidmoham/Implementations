/**
 * Created by Mohammad Haider (haidmoham) on 7/20/16.
 */
import java.util.*;

public class QuickSort {
    private int array[];
    private int length;

    public void sort(int[] inputArr){
        if (inputArr == null || inputArr.length == 0){
            return;
        }
        this.array = inputArr;
        length = inputArr.length;
        quickSort(0, length-1);
    }
    private void quickSort(int lowerIndex, int higherIndex){
        int i = lowerIndex;
        int j = higherIndex;
        // calculate the pivot
        int pivot = array[lowerIndex + (higherIndex-lowerIndex)/2];
        // divide into two arrays
        while (i <= j){
            while (array[i] < pivot){
                i++;
            }
            while (array[j] > pivot){
                j--;
            }
            if (i <= j){
                exchangeNumbers(i, j);
                //shift both indices
                i++;
                j--;
            }
        }
        if (lowerIndex < j)
            quickSort(lowerIndex, j);
        if (i < higherIndex)
            quickSort(i, higherIndex);
    }
    private void exchangeNumbers(int i, int j){
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    public static void main(String a[]){
        QuickSort sorter = new QuickSort();
        int[] input = {24,2,45,20,56,75,2,56,99,53,12};
        sorter.sort(input);
        for (int i:input)
            System.out.print(i + " ");
    }
}
