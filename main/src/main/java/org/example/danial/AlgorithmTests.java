package org.example.danial;

import java.util.Arrays;
import java.util.Random;

public class AlgorithmTests {
    public static void main(String[] args) {
        testSelectionSort();
        testHeapSort();
        testMaxHeap();
        testKadane();
    }

    private static int[] generateSortedArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = i;
        }
        return arr;
    }

    private static int[] generateReverseArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = size - i - 1;
        }
        return arr;
    }

    private static int[] generateRandomArray(int size) {
        Random rand = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(1000) - 500;
        }
        return arr;
    }

    private static boolean isSorted(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[i - 1]) {
                return false;
            }
        }
        return true;
    }

    private static void testSelectionSort() {
        System.out.println("=== Тестирование сортировки выбором ===");

        int[] sorted = generateSortedArray(1000);
        int[] sortedCopy = sorted.clone();
        long start = System.nanoTime();
        SelectionSort.selectionSort(sorted);
        long end = System.nanoTime();
        System.out.println("Отсортированный массив (n=1000): " + (end - start) / 1_000_000.0 + " мс");
        System.out.println("Корректность: " + (isSorted(sorted) && Arrays.equals(sorted, sortedCopy)));

        int[] reverse = generateReverseArray(1000);
        start = System.nanoTime();
        SelectionSort.selectionSort(reverse);
        end = System.nanoTime();
        System.out.println("Обратный порядок (n=1000): " + (end - start) / 1_000_000.0 + " мс");
        System.out.println("Корректность: " + isSorted(reverse));

        int[] random = generateRandomArray(1000);
        start = System.nanoTime();
        SelectionSort.selectionSort(random);
        end = System.nanoTime();
        System.out.println("Случайный массив (n=1000): " + (end - start) / 1_000_000.0 + " мс");
        System.out.println("Корректность: " + isSorted(random));

        int[] empty = new int[0];
        start = System.nanoTime();
        SelectionSort.selectionSort(empty);
        end = System.nanoTime();
        System.out.println("Пустой массив: " + (end - start) / 1_000_000.0 + " мс");
        System.out.println("Корректность: " + isSorted(empty));

        int[] single = {42};
        start = System.nanoTime();
        SelectionSort.selectionSort(single);
        end = System.nanoTime();
        System.out.println("Массив с одним элементом: " + (end - start) / 1_000_000.0 + " мс");
        System.out.println("Корректность: " + isSorted(single));
    }


    private static void testHeapSort() {
        System.out.println("\n=== Тестирование пирамидальной сортировки ===");

        int[] sorted = generateSortedArray(1000);
        int[] sortedCopy = sorted.clone();
        long start = System.nanoTime();
        HeapSort.heapSort(sorted);
        long end = System.nanoTime();
        System.out.println("Отсортированный массив (n=1000): " + (end - start) / 1_000_000.0 + " мс");
        System.out.println("Корректность: " + (isSorted(sorted) && Arrays.equals(sorted, sortedCopy)));

        int[] reverse = generateReverseArray(1000);
        start = System.nanoTime();
        HeapSort.heapSort(reverse);
        end = System.nanoTime();
        System.out.println("Обратный порядок (n=1000): " + (end - start) / 1_000_000.0 + " мс");
        System.out.println("Корректность: " + isSorted(reverse));

        int[] random = generateRandomArray(1000);
        start = System.nanoTime();
        HeapSort.heapSort(random);
        end = System.nanoTime();
        System.out.println("Случайный массив (n=1000): " + (end - start) / 1_000_000.0 + " мс");
        System.out.println("Корректность: " + isSorted(random));

        int[] empty = new int[0];
        start = System.nanoTime();
        HeapSort.heapSort(empty);
        end = System.nanoTime();
        System.out.println("Пустой массив: " + (end - start) / 1_000_000.0 + " мс");
        System.out.println("Корректность: " + isSorted(empty));

        int[] single = {42};
        start = System.nanoTime();
        HeapSort.heapSort(single);
        end = System.nanoTime();
        System.out.println("Массив с одним элементом: " + (end - start) / 1_000_000.0 + " мс");
        System.out.println("Корректность: " + isSorted(single));
    }

    private static void testMaxHeap() {
        System.out.println("\n=== Тестирование Max-Heap ===");
        MaxHeap heap = new MaxHeap();

        long start = System.nanoTime();
        Random rand = new Random();
        int[] values = generateRandomArray(1000);
        for (int value : values) {
            heap.insert(value);
        }
        int prev = Integer.MAX_VALUE;
        boolean correct = true;
        for (int i = 0; i < values.length; i++) {
            int max = heap.extractMax();
            if (max > prev) {
                correct = false;
                break;
            }
            prev = max;
        }
        long end = System.nanoTime();
        System.out.println("Вставка и извлечение 1000 случайных элементов: " + (end - start) / 1_000_000.0 + " мс");
        System.out.println("Корректность: " + correct);

        heap = new MaxHeap();
        heap.insert(10);
        heap.insert(20);
        heap.insert(5);
        start = System.nanoTime();
        heap.increaseKey(2, 25);
        end = System.nanoTime();
        int max1 = heap.extractMax();
        int max2 = heap.extractMax();
        int max3 = heap.extractMax();
        System.out.println("Increase-key: " + (end - start) / 1_000_000.0 + " мс");
        System.out.println("Корректность: " + (max1 == 25 && max2 == 20 && max3 == 10));

        heap = new MaxHeap();
        try {
            heap.extractMax();
            System.out.println("Пустая куча: Ошибка не выброшена");
        } catch (IllegalStateException e) {
            System.out.println("Пустая куча: Ошибка выброшена корректно");
        }
    }

    private static void testKadane() {
        System.out.println("\n=== Тестирование алгоритма Кадане ===");

        int[] random = generateRandomArray(1000);
        long start = System.nanoTime();
        Kadane.Result res = Kadane.kadane(random);
        long end = System.nanoTime();

        int expectedSum = random[res.start];
        for (int i = res.start + 1; i <= res.end; i++) {
            expectedSum += random[i];
        }
        System.out.println("Случайный массив (n=1000): " + (end - start) / 1_000_000.0 + " мс");
        System.out.println("Корректность: " + (res.maxSum == expectedSum));

        int[] negative = new int[1000];
        Random rand = new Random();
        for (int i = 0; i < negative.length; i++) {
            negative[i] = -(rand.nextInt(100) + 1);
        }
        start = System.nanoTime();
        res = Kadane.kadane(negative);
        end = System.nanoTime();
        expectedSum = negative[res.start];
        System.out.println("Все отрицательные (n=1000): " + (end - start) / 1_000_000.0 + " мс");
        System.out.println("Корректность: " + (res.maxSum == expectedSum && res.start == res.end));

        int[] single = {42};
        start = System.nanoTime();
        res = Kadane.kadane(single);
        end = System.nanoTime();
        System.out.println("Массив с одним элементом: " + (end - start) / 1_000_000.0 + " мс");
        System.out.println("Корректность: " + (res.maxSum == 42 && res.start == 0 && res.end == 0));

        try {
            int[] empty = new int[0];
            Kadane.kadane(empty);
            System.out.println("Пустой массив: Ошибка не выброшена");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Пустой массив: Ошибка выброшена корректно");
        }
    }
}
