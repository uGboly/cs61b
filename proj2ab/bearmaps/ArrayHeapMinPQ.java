package bearmaps;
import java.util.NoSuchElementException;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T>{
    T[] heap;
    double[] priorities;
    int capability;
    int size;

    public ArrayHeapMinPQ () {
        heap = (T[]) new Object[101];//heap[0] unused
        priorities = new double[101];
        capability = 100;
        size = 0;
    }

    private void resize(int sz) {
        capability = sz;
        T[] newHeap = (T[]) new Object[sz + 1];
        double [] newPrio = new double[sz + 1];
        System.arraycopy(heap, 1,newHeap, 1, size);
        System.arraycopy(priorities, 1,newPrio, 1, size);

        heap = newHeap;
        priorities = newPrio;
    }

    private void moveItem(int currentIndex, int targetIndex) {
        T temp = heap[currentIndex];
        heap[currentIndex] = heap[targetIndex];
        heap[targetIndex] = temp;

        double tem = priorities[currentIndex];
        priorities[currentIndex] = priorities[targetIndex];
        priorities[targetIndex] = tem;
    }

    /* Adds an item with the given priority value. Throws an
     * IllegalArgumentExceptionb if item is already present.
     * You may assume that item is never null. */
    public void add(T item, double priority){
        if (contains(item)) {
            throw new IllegalArgumentException();
        }
        if (size == capability ) {
            resize(2 * capability);
        }

        size++;
        int index = size;
        heap[index] = item;
        priorities[index] = priority;


        while (index > 1 && priorities[index] < priorities[index / 2]) {
            moveItem(index, index / 2);
            index /= 2;
        }

    }
    /* Returns true if the PQ contains the given item. */
    public boolean contains(T item) {
        for (int i = 1; i <= size; i++) {
            if (heap[i].equals(item)) {
                return true;
            }
        }
        return false;
    }
    /* Returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    public T getSmallest() {
        return heap[1];
    }
    /* Removes and returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    public T removeSmallest() {
        size --;

        T smallest = heap[1];
        heap[1] = heap[size + 1];
        priorities[1] = priorities[size + 1];

        int index = 1;

        while (index * 2 < size + 1 &&
        (priorities[index] > priorities[index * 2]
        ||priorities[index] > priorities[index * 2 + 1]))
        {
            if (priorities[index * 2] < priorities[index * 2 + 1]) {
                moveItem(index, index * 2);
                index *= 2;
            }else {
                moveItem(index, index * 2 + 1);
                index = index * 2 + 1;
            }
        }

        if (capability > 100 && size  < capability / 4) {
            resize(size / 2);
        }
        return smallest;
    }

    /* Returns the number of items in the PQ. */
    public int size() {
        return size;
    }

    /* Changes the priority of the given item. Throws NoSuchElementException if the item
     * doesn't exist. */
    public void changePriority(T item, double priority) {
        for (int i = 1; i < size + 1; i++) {
            if (heap[i].equals(item)) {
                priorities[i] = priority;
                int index = i;
                while (index > 1 && priorities[index] < priorities[index/2]) {
                    moveItem(index, index / 2);
                }
                while (index * 2 < size + 1 &&
                        (priorities[index] > priorities[index * 2]
                                ||priorities[index] > priorities[index * 2 + 1]))
                {
                    if (priorities[index * 2] < priorities[index * 2 + 1]) {
                        moveItem(index, index * 2);
                        index *= 2;
                    }else {
                        moveItem(index, index * 2 + 1);
                        index = index * 2 + 1;
                    }
                }

                return;
            }
        }
        throw new NoSuchElementException();
    }
}
