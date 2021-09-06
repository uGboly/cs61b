public class LinkedListDeque<T> implements  Deque<T>{
    private  class Node{
        T item;
        Node prev;
        Node next;

        Node(T i, Node p, Node n){
            item=i;
            prev=p;
            next= n;
        }

    }

    private  Node sentinel;
    private int size;

    @Override
    public void addFirst(T item){
        Node add=new Node(item, sentinel, sentinel.next);
        sentinel.next.prev = add;
        sentinel.next = add;

        size++;
    }

    @Override
    public void addLast(T item){
        Node add = new Node(item, sentinel.prev, sentinel);
        sentinel.prev.next=add;
        sentinel.prev = add;

        size++;
    }

    @Override
    public T removeFirst(){
        if(isEmpty()){
            return null;
        }
        size--;
        T res = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev=sentinel;

        return res;
    }

    @Override
    public T removeLast(){
        if (isEmpty()){
            return null;
        }
        size--;
        T res = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next=sentinel;

        return res;
    }


    @Override
    public int size(){
        return size;
    }

    @Override
    public T get(int index){
        if (index>= size){
            return null;
        }

        Node N = sentinel;
        for (int i = 0; i <= index; i++){
            N = N.next;
        }
        return N.item;
    }

    private T getRecursive(Node n, int index){
        if (index == 0){
            return n.next.item;
        }
        return getRecursive(n.next, index-1);
    }

    public T getRecursive(int index){
        if (index >= size){
            return null;
        }
        return  getRecursive(sentinel, index);
    }


    public LinkedListDeque(){
        sentinel = new Node(null, null, null);
        sentinel.next=sentinel;
        sentinel.prev=sentinel;
        size=0;
    }

    private LinkedListDeque(LinkedListDeque other){
        sentinel = new Node(null, null, null);
        sentinel.next=sentinel;
        sentinel.prev=sentinel;
        size=0;

        Node n = other.sentinel.next;

        while (!n.equals(other.sentinel)){
            addLast(n.item);
        }
    }

    @Override
    public void printDeque(){
        Node n = sentinel.next;

        while (!n.equals(sentinel.prev)){
            System.out.println(n.item+" ");
            n = n.next;
        }
        while (n.equals(sentinel.prev) && n.equals(sentinel)){
            System.out.println(n.item+"\n");
        }
    }
}
