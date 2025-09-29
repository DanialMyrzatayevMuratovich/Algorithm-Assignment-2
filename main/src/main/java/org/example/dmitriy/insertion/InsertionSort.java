package org.example.dmitriy.insertion;

import org.example.Sorter;

public class InsertionSort implements Sorter {
    @Override
    public void sort(int[] array) {
        if (array.length <= 1) {
            return;
        }

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
