public class ArrayDeque <T>{
    private T[] items;
    private int size;
    private int first;

    public ArrayDeque(){
        items = (T[]) new Object[100];
        size = 0;
    }

    public ArrayDeque(ArrayDeque other){
        size = other.size;
        items = (T[]) new Object[other.items.length];

        System.arraycopy(other.items,0,items,0,size);
    }

    public void bigger(){
        T[] temp=(T[]) new Object[items.length*2];
        System.arraycopy(items,0,temp,0,size);
    }


    public void addLast(T x) {
        if(size == items.length){
            T[] temp = (T[]) new Object[items.length*2];
            System.arraycopy(items,0,temp,0,size);

            items = temp;
        }
        size++;
        items[size] = x;
    }

    public void addfirst(T x) {
        if(size == items.length){
            T[] temp = (T[]) new Object[items.length*2];
            System.arraycopy(items,0,temp,1,size);

            items = temp;
        }
        size++;
        items[0] = x;
    }

    public  T get(int index){
        return items[index];
    }

    public T removefirst(){
        T firstitem = get(0);
        size--;

        for(int i = 0; i < size-1; i++){
            items[i]=items[i+1];
        }

        if(size / items.length<0.25){
            T[] temp = (T[]) new Object[items.length/2];
            System.arraycopy(items,0,temp,0,size);
            items = temp;
        }

        return firstitem;
    }

    public T removelast(){
        size--;
        if(size / items.length<0.25){
            T[] temp = (T[]) new Object[items.length/2];
            System.arraycopy(items,0,temp,0,size);
            items = temp;
        }

        return items[size+1];
    }
}
