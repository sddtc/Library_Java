package sddtc.library.java.sort;

import java.util.Arrays;

/**
 * Created by sddtc on 16/9/13.
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] a = {2, 6, 3, 5, 1};
        new QuickSort().quickSort(a, 0, a.length-1);
        System.out.println(Arrays.toString(a));
    }

    private void quickSort(int[] a, int start, int end) {
        if (start < end) {
            int pIndex = partition(a, start, end);
            quickSort(a, start, pIndex - 1);
            quickSort(a, pIndex + 1, end);
        }
    }

    private int partition(int[] a, int start, int end) {
        int pivot = a[end];
        int partitionIndex = start;

        for (int i = start; i < end; i++) {
            if (a[i] <= pivot) {
                int temp = a[i];
                a[i] = a[partitionIndex];
                a[partitionIndex] = temp;

                partitionIndex++;
            }
        }

        int temp = a[partitionIndex];
        a[partitionIndex] = a[end];
        a[end] = temp;

        return partitionIndex;
    }
}
