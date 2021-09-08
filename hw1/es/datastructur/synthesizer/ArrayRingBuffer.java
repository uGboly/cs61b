package es.datastructur.synthesizer;
import java.util.Iterator;

//Make sure to that this class and all of its methods are public
//Make sure to add the override tag for all overridden methods
//Make sure to make this class implement BoundedQueue<T>

public class ArrayRingBuffer<T>  implements BoundedQueue<T>{
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;

    @Override
    public int fillCount() {
        return fillCount;
    }

    @Override
    public int capacity(){
        return rb.length;
    }


    private int subOne(int x) {
        return (x - 1 + capacity()) % capacity();
    }

    private int addOne(int x) {
        return (x + 1) % capacity();
    }
    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        //  Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        first = 0;
        last = 0;
        fillCount = 0;

        rb = (T[]) new Object[capacity] ;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    public void enqueue(T x) {
        //  Enqueue the item. Don't forget to increase fillCount and update
        //       last.
        if (isFull()) {
            throw new RuntimeException("Ring Buffer overflow");
        }

        fillCount--;
        rb[last] = x;
        last = addOne(last);
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    public T dequeue() {
        //  Dequeue the first item. Don't forget to decrease fillCount and
        //       update first.

        if (isEmpty()) {
            throw new RuntimeException("Ring Buffer underflow");
        }

        fillCount--;
        T temp = rb[first];
        first = addOne(first);
        return temp;
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    public T peek() {
        // Return the first item. None of your instance variables should
        //       change.
        if (isEmpty()) {
            throw new RuntimeException("Ring Buffer underflow");
        }

        return rb[first];
    }

    // When you get to part 4, implement the needed code to support
    //       iteration and equals.
    private class ArbIterator implements Iterator<T>{
        int pos;

        public ArbIterator(){
            pos = 0;
        }

        public boolean hasNext(){
            return pos < fillCount();
        }

        public T next(){
            T temp = rb[addOne(pos+first)];
            pos++;
            return temp;
        }
    }

    public Iterator<T> iterator(){
        return new ArbIterator();
    }

    @Override
    public boolean equals(Object other){
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (other.getClass() != this.getClass()) {
            return false;
        }

        ArrayRingBuffer<T> o = (ArrayRingBuffer<T>) other;
        if(!o.rb.equals(this.rb)){
            return false;
        }
        return true;
    }

}
