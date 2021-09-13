import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private Node root;

    private class Node{
        K key;           // sorted by key
        V val;         // associated data
        int size = 0;
        Node left, right;

        public Node(K k, V v, int size) {
            this.key = k;
            this.val = v;
            this.size = size;
        }
    }


    /** Removes all of the mappings from this map. */
    public void clear() {
        root = null;
    }

    /* Returns true if this map contains a mapping for the specified key. */
    public boolean containsKey(K key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    public V get(K key) {
        return getHelper(root, key);
    }

    private V getHelper(Node B, K key) {
        if (key == null) {
            throw new IllegalArgumentException("calls get() with a null key");
        }
        if (B == null) {
            return null;
        }
        if (key.compareTo(B.key) < 0) {
            return getHelper(B.left, key);
        }else if (key.compareTo(B.key) > 0) {
            return getHelper(B.right, key);
        }else {
            return B.val;
        }
    }

    /* Returns the number of key-value mappings in this map. */
    public int size(){
        return size(root);
    }

    int size(Node B) {
        if (B == null){
            return 0;
        }
        return B.size;
    }

    /* Associates the specified value with the specified key in this map. */
    public void put(K key, V value) {
        root = putHelper(root, key, value);
    }

    private Node putHelper(Node B, K key, V value) {
        if (B == null) {
            return new Node(key, value, 1);
        }else if (key.compareTo(B.key) < 0){
            B.left = putHelper(B.left, key, value);
        }else if (key.compareTo(B.key) > 0){
            B.right = putHelper(B.right, key, value);
        }else {
            B.val = value;
        }
        B.size = size(B.left) + size(B.right) + 1;
        return B;
    }

    /* Returns a Set view of the keys contained in this map. */
    public Set<K> keySet(){
        throw new UnsupportedOperationException();
    }

    /* Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException. */
    public V remove(K key){
        throw new UnsupportedOperationException();
    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    public V remove(K key, V value){
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        return null;
    }
}
