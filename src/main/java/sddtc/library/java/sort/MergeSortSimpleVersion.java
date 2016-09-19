package sddtc.library.java.sort;

import java.util.Arrays;

/**
 * Created by sddtc on 16/9/19.
 */
public class MergeSortSimpleVersion {
    public static void main(String[] args) {
        int[] a = {2, 6, 3, 5, 1};
        new MergeSortSimpleVersion().mergeSort(a);
        System.out.println(Arrays.toString(a));
    }

    public void mergeSort(int[] a) {
        int n = a.length;
        if (n < 2) return;
        int mid = n / 2;

        int[] left = new int[mid];
        int[] right = new int[n - mid];

        for (int i = 0; i < mid; i++) {
            left[i] = a[i];
        }

        for (int j = mid; j < n; j++) {
            right[j - mid] = a[j];
        }

        mergeSort(left);
        mergeSort(right);
        merge(a, left, right);
    }

    private void merge(int[] a, int[] left, int[] right) {
        int leftLength = left.length;
        int rightLength = right.length;
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < leftLength && j < rightLength) {
            if (left[i] < right[j]) {
                a[k] = left[i];
                i++;
                k++;
            } else {
                a[k] = right[j];
                j++;
                k++;
            }
        }
        while (i < leftLength) {
            a[k] = left[i];
            i++;
            k++;
        }
        while (j < rightLength) {
            a[k] = right[j];
            j++;
            k++;
        }
    }
}
