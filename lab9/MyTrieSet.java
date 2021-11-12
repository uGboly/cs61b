import java.util.ArrayList;
import java.util.List;

public class MyTrieSet implements TrieSet61B{
//    private static final int R = 128;
    private Node root;

    public MyTrieSet() {
        root = new Node( false);
    }
    private class Node {
        private boolean isKey;
        private DataIndexedCharMap<Node> map;

        public Node(boolean b) {
            isKey = b;
            map = new DataIndexedCharMap<>(128);
        }
    }

    public class DataIndexedCharMap<V> {
        private V[] items;
        public DataIndexedCharMap(int R) {
            items = (V[]) new Object[R];
        }
        public void put(char c, V val) {
            items[c] = val;
        }
        public V get(char c) {
            return items[c];
        }
        public boolean containsKey(char c) {
            return items[c] != null;
        }

        public List<Character> keys () {
            List<Character>  keys = new ArrayList<>();
            for (char i = 0; i < 128; i++) {
                if (containsKey(i)) {
                    keys.add(i);
                }
            }
            return keys;
        }
    }


    /** Clears all items out of Trie */
    public void clear() {
        root = new Node( false);
    }

    /** Returns true if the Trie contains KEY, false otherwise */
    public boolean contains(String key) {
        if (key == null || key.length() < 1 ) {
            return false;
        }
        Node curr = root;
        for (int i = 0, n = key.length(); i < n; i++) {
            char c = key.charAt(i);
            if (!curr.map.containsKey(c)) {
                return false;
            }
            curr = curr.map.get(c);
        }
        if (curr.isKey) {
            return true;
        }
        return false;
    }

    /** Inserts string KEY into Trie */
    @Override
    public void add(String key) {
        if (key == null || key.length() < 1) {
            return;
        }
        Node curr = root;
        for (int i = 0, n = key.length(); i < n; i++) {
            char c = key.charAt(i);
            if (!curr.map.containsKey(c)) {
                curr.map.put(c, new Node(false));
            }
            curr = curr.map.get(c);
        }
        curr.isKey = true;
    }


    /** Returns a list of all words that start with PREFIX */
    public List<String> keysWithPrefix(String prefix) {
        List<String> x = new ArrayList<>();
        Node curr = root;
        for (int i = 0, n = prefix.length(); i < n; i++) {
            char c = prefix.charAt(i);
            if (!curr.map.containsKey(c)) {
                return x;
            }
            curr = curr.map.get(c);
        }

        if (curr.isKey) {
            x.add(prefix);
        }

        for (char c : curr.map.keys()) {
            colHelper(prefix + c, x, curr.map.get(c));
        }
        return x;
    }

    public List<String> collect() {
        List<String> x = new ArrayList<>();
        String s = "";

        colHelper(s, x, root);
        return x;
    }

    public void colHelper(String s, List<String> x, Node n) {
        if (n.isKey) {
            x.add(s);
        }

        for (char c : n.map.keys()) {
            colHelper(s + c, x, n.map.get(c));
        }
    }
    /** Returns the longest prefix of KEY that exists in the Trie
     * Not required for Lab 9. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    public String longestPrefixOf(String key) {
        throw new UnsupportedOperationException();
    }


}
