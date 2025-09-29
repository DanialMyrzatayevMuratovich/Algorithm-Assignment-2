package org.example.dmitriy;

public class InsertionSort {
    public static void sort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int k = array[i];
            int j = i - 1;

            while (j >= 0 && array[j] > k) {
                array[j + 1] = array[j];
                j = j - 1;
            }

            array[j + 1] = k;
        }
    }
}
