package org.example.dmitriy.shell;

import org.example.Sorter;

import java.util.ArrayList;
import java.util.List;

public class ShellSort implements Sorter {
    private final GapSequence gapSequence;

    public ShellSort(GapSequence gapSequence) {
        this.gapSequence = gapSequence;
    }

    @Override
    public void sort(int[] array) {
        if (array.length <= 1) {
            return;
        }
        int[] gaps = generateGaps(gapSequence, array.length);


        for (int gap : gaps) {
            for (int i = gap; i < array.length; i++) {
                int temp = array[i];
                int j = i;
                while (j >= gap && array[j - gap] > temp) {
                    array[j] = array[j - gap];
                    j -= gap;
                }
                array[j] = temp;
            }
        }
    }

    private static int[] generateGaps(GapSequence gapSequence, int n) {
        List<Integer> gaps = new ArrayList<>();

        switch (gapSequence) {
            case SHELL:
                for (int gap = n / 2; gap > 0; gap /= 2) {
                    gaps.add(gap);
                }
                break;
            case KNUTH:
                int gap = 1;
                while (gap < n / 3) {
                    gap = 3 * gap + 1;
                }
                while (gap > 0) {
                    gaps.add(gap);
                    gap = (gap - 1) / 3;
                }
                break;
            case SEDGEWICK:
                int k = 0;
                int sedgeGap;
                do {
                    if (k % 2 == 0) {
                        sedgeGap = 9 * ((1 << (2 * k)) - (1 << k)) + 1;
                    } else {
                        sedgeGap = 8 * (1 << (2 * k)) - 6 * (1 << (k + 1)) + 1;
                    }
                    if (sedgeGap < n) gaps.addFirst(sedgeGap);
                    k++;
                } while (sedgeGap < n);

                gaps.add(1);

                break;
        }

        return gaps.stream().mapToInt(i -> i).toArray();
    }
}
