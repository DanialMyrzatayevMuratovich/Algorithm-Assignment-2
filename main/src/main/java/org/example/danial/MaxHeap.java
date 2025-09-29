package org.example.danial;

import java.util.ArrayList;

public class MaxHeap {
    private ArrayList<Integer> heap;
    private int size;

    public MaxHeap() {
        heap = new ArrayList<>();
        size = 0;
    }

    public void insert(int key) {
        heap.add(key);
        siftUp(size);
        size++;
    }

    public void increaseKey(int index, int newKey) {
        if (index >= size || newKey < heap.get(index)) {
            return;
        }
        heap.set(index, newKey);
        siftUp(index);
    }

    public int extractMax() {
        if (size == 0) {
            throw new IllegalStateException("Куча пуста");
        }
        int max = heap.get(0);
        heap.set(0, heap.get(size - 1));
        heap.remove(size - 1);
        size--;
        if (size > 0) {
            siftDown(0);
        }
        return max;
    }

    private void siftUp(int i) {
        while (i > 0 && heap.get((i - 1) / 2) < heap.get(i)) {
            int temp = heap.get(i);
            heap.set(i, heap.get((i - 1) / 2));
            heap.set((i - 1) / 2, temp);
            i = (i - 1) / 2;
        }
    }

    private void siftDown(int i) {
        int maxIdx = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < size && heap.get(left) > heap.get(maxIdx)) {
            maxIdx = left;
        }
        if (right < size && heap.get(right) > heap.get(maxIdx)) {
            maxIdx = right;
        }
        if (maxIdx != i) {
            int temp = heap.get(i);
            heap.set(i, heap.get(maxIdx));
            heap.set(maxIdx, temp);
            siftDown(maxIdx);
        }
    }
}
