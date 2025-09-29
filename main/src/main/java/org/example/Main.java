package org.example;

import org.example.dmitriy.shell.GapSequence;
import org.example.dmitriy.shell.ShellSort;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] test = {1, 4, 5, 2};

        System.out.println(Arrays.toString(test));

        Sorter sorter = new ShellSort(GapSequence.SEDGEWICK);
        sorter.sort(test);

        System.out.println(Arrays.toString(test));
    }
}