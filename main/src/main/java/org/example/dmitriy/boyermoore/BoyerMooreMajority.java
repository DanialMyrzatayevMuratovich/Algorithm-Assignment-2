package org.example.dmitriy.boyermoore;

public class BoyerMooreMajority {
    public static int findMajorityElement(int[] array) {
        int candidate = 0;
        int count = 0;

        for (int num : array) {
            if (count == 0) {
                candidate = num;
                count = 1;
            } else if (num == candidate) {
                count++;
            } else {
                count--;
            }
        }

        count = 0;

        for (int num : array) {
            if (num == candidate) {
                count++;
            }
        }

        return count > array.length / 2 ? candidate : Integer.MIN_VALUE;
    }
}
