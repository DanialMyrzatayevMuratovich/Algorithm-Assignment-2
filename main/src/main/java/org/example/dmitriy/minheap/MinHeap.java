package org.example.dmitriy.minheap;

import java.util.ArrayList;
import java.util.List;

public class MinHeap {
    private final List<Integer> heap;

    public MinHeap() {
        heap = new ArrayList<>();
    }

    public MinHeap(List<Integer> elements) {
        heap = new ArrayList<>(elements);
        buildHeap();
    }

    public void insert(int key) {
        heap.add(key);
        siftUp(heap.size() - 1);
    }

    public int getMin() {
        if (heap.isEmpty()) throw new IllegalStateException("Heap is empty");
        return heap.getFirst();
    }

    public int extractMin() {
        if (heap.isEmpty()) throw new IllegalStateException("Heap is empty");
        int min = heap.getFirst();
        int last = heap.removeLast();
        if (!heap.isEmpty()) {
            heap.set(0, last);
            siftDown(0);
        }
        return min;
    }

    public void decreaseKey(int index, int newKey) {
        if (index < 0 || index >= heap.size()) throw new IllegalArgumentException("Invalid index");
        if (newKey > heap.get(index)) throw new IllegalArgumentException("New key is greater than current key");

        heap.set(index, newKey);
        siftUp(index);
    }

    public static MinHeap merge(MinHeap h1, MinHeap h2) {
        List<Integer> merged = new ArrayList<>(h1.heap);
        merged.addAll(h2.heap);
        return new MinHeap(merged);
    }

    private void buildHeap() {
        for (int i = heap.size() / 2 - 1; i >= 0; i--) {
            siftDown(i);
        }
    }

    private void siftUp(int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;
            if (heap.get(parent) <= heap.get(index)) break;
            swap(parent, index);
            index = parent;
        }
    }

    private void siftDown(int index) {
        int size = heap.size();
        while (true) {
            int left = 2 * index + 1;
            int right = 2 * index + 2;
            int smallest = index;

            if (left < size && heap.get(left) < heap.get(smallest)) smallest = left;
            if (right < size && heap.get(right) < heap.get(smallest)) smallest = right;

            if (smallest == index) break;
            swap(index, smallest);
            index = smallest;
        }
    }

    private void swap(int i, int j) {
        int temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    public void printHeap() {
        System.out.println(heap);
    }
}
