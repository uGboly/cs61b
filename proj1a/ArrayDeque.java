public class ArrayDeque <T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    public int size() {
        return size;

    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int addOne(int a) {
        return (a + 1) % items.length;
    }

    private int subOne(int a) {
        return (a - 1 + items.length) % items.length;
    }

    private void resize(int sz) {
        T[] newItems = (T[]) new Object[sz];
        int start = addOne(nextFirst);

        for(int i = 0; i < size; i++) {
            newItems[i] = items[addOne(start+i)];
        }

        items = newItems;
        nextFirst = sz - 1;
        nextLast = size;
    }



    private ArrayDeque(ArrayDeque<T> other) {
        size = other.size;
        items = (T[]) new Object[other.items.length];
        int start = addOne(other.nextFirst);

        for(int i = 0; i < size; i++ ) {
            items[i] = other.items[addOne(start+i)];
        }

        nextFirst = items.length - 1;
        nextLast = size;
    }



    public void addLast(T x) {
        if(size == items.length){
            resize(size*2);
        }
        items[nextLast] = x;
        nextLast = addOne(nextLast);
        size++;
    }

    public void addFirst(T x) {
        if(size == items.length){
            resize(size*2);
        }

        items[nextFirst] = x;
        nextFirst = subOne(nextFirst);
        size++;
    }

    public  T get(int index) {
        return items[addOne(nextFirst + index)];
    }

    public void printDeque() {
        int i = addOne(nextFirst);
        for (int j = 0; j < size; j++) {
            System.out.print(items[i] + " ");
            i = addOne(i);
        }
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }

        T rm = items[addOne(nextFirst)];
        nextFirst = subOne(nextFirst);
        size--;


        if (items.length >= 16 && size < (items.length/4)) {
            resize(size/2);
        }
        return rm;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }

        T rm = items[subOne(nextLast)];
        nextLast = addOne(nextLast);
        size--;

        if (items.length >= 16 && size < (items.length/4)) {
            resize(size/2);
        }
        return rm;
    }
}
