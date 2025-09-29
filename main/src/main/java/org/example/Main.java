package org.example;

import org.example.dmitriy.InsertionSort;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] test = {1, 4, 5, 2};

        System.out.println(Arrays.toString(test));
        InsertionSort.sort(test);
        System.out.println(Arrays.toString(test));
    }
}