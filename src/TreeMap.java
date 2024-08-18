import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

/**
 * A custom TreeMap implementation using a binary search tree.
 * This class manages a collection of key-value pairs, with keys ordered according to their natural ordering.
 *
 * @param <K> The type of keys maintained by this map. Must be Comparable.
 * @param <V> The type of mapped values.
 */
public class TreeMap<K extends Comparable<K>, V>  implements TreeMapInterface<K, V> {
    private TreeMapNode<K, V> root;
    private int size;

    /**
     * Constructs an empty TreeMap.
     */
    public TreeMap() {
        root = null;
        size = 0;
    }

    /**
     * Adds a key-value pair to the TreeMap. If the tree previously contained a mapping for the key,
     * the old value is replaced by the specified value.
     *
     * @param key   The key with which the specified value is to be associated.
     * @param value The value to be associated with the specified key.
     * @throws IllegalArgumentException if the key is null.
     */
    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        root = put(root, key, value);
    }

    /**
     * Private helper method for adding a key-value pair to the TreeMap.
     *
     * @param node  The current node being considered.
     * @param key   The key with which the specified value is to be associated.
     * @param value The value to be associated with the specified key.
     * @return The updated node structure after adding the key-value pair.
     */
    private TreeMapNode<K, V> put(TreeMapNode<K, V> node, K key, V value) {
        if (node == null) {
            size++;
            return new TreeMapNode<>(key, value);
        } else {
            int compare = key.compareTo(node.key);
            if (compare < 0) {
                node.left = put(node.left, key, value);
            } else

            if (compare > 0) {
                node.right = put(node.right, key, value);
            } else { // key already exists, update value
                node.value = value;
            }
            return node;
        }
    }

    /**
     * Retrieves the value to which the specified key is mapped.
     *
     * @param key The key whose associated value is to be returned.
     * @return the value to which the specified key is mapped, or null if this map contains no mapping for the key.
     * @throws IllegalArgumentException if the key is null.
     */
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        return get(root, key);
    }

    /**
     * Private helper method for retrieving the value associated with a key.
     *
     * @param node The current node being considered.
     * @param key  The key whose associated value is to be retrieved.
     * @return The value associated with the specified key, or null if not found.
     */
    private V get(TreeMapNode<K, V> node, K key) {
        if (node == null) {
            return null;
        } else if (key.compareTo(node.key) < 0) {
            return get(node.left, key);
        } else if (key.compareTo(node.key) > 0) {
            return get(node.right, key);
        } else {
            return node.value;
        }
    }

    /**
     * Checks if the TreeMap contains a specific key.
     *
     * @param key The key whose presence in this map is to be tested.
     * @return true if this map contains a mapping for the specified key.
     */
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    /**
     * Removes all key-value pairs from the TreeMap.
     */
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Returns the number of key-value pairs in this map.
     *
     * @return the number of key-value pairs in this map.
     */
    public int size() {
        return size;
    }
    /**
     * Converts the values of the TreeMap to an array.
     *
     * @param array An array of values to be filled. If it is too small, a new one will be created.
     * @return An array containing all the values in the TreeMap.
     */
    public V[] toValueArray(V[] array) {
        if (array.length < size) {
            array = Arrays.copyOf(array, size);
        }
        populateArrayWithValues(root, array, new int[1]);
        return array;
    }

    /**
     * Private helper method for filling an array with values from the TreeMap.
     *
     * @param node  The current node being considered.
     * @param array An array of values to be filled.
     * @param index An array index to keep track of the current position.
     */
    private void populateArrayWithValues(TreeMapNode<K, V> node, V[] array, int[] index) {
        if (node != null) {
            populateArrayWithValues(node.left, array, index);
            array[index[0]++] = node.value;
            populateArrayWithValues(node.right, array, index);
        }
    }

    /**
     * Converts the keys of the TreeMap to an array.
     *
     * @param array An array of keys to be filled. If it is too small, a new one will be created.
     * @return An array containing all the keys in the TreeMap.
     */
    public K[] toKeyArray(K[] array) {
        if (array.length < size) {
            array = Arrays.copyOf(array, size);
        }
        populateArrayWithKeys(root, array, new int[1]);
        return array;
    }

    /**
     * Private helper method for filling an array with keys from the TreeMap.
     *
     * @param node  The current node being considered.
     * @param array An array of keys to be filled.
     * @param index An array index to keep track of the current position.
     */
    private void populateArrayWithKeys(TreeMapNode<K, V> node, K[] array, int[] index) {
        if (node != null) {
            populateArrayWithKeys(node.left, array, index);
            array[index[0]++] = node.key;
            populateArrayWithKeys(node.right, array, index);
        }
    }

    /**
     * Returns a set containing all the keys in the TreeMap.
     *
     * @return A set of keys.
     */
    public Set<K> keySet() {
        Set<K> keys = new HashSet<>();
        populateKeySet(root, keys);
        return keys;
    }

    /**
     * Private helper method for filling a set with keys from the TreeMap.
     *
     * @param node The current node being considered.
     * @param keys A set of keys to be filled.
     */
    private void populateKeySet(TreeMapNode<K, V> node, Set<K> keys) {
        if (node != null) {
            keys.add(node.key);
            populateKeySet(node.left, keys);
            populateKeySet(node.right, keys);
        }
    }
    /**
    *Inner class representing a node in the TreeMap.
     */

    private static class TreeMapNode<K, V> {
        K key;
        V value;
        TreeMapNode<K, V> left, right;

        /**
         * Constructs a TreeMapNode with the specified key and value.
         *
         * @param key   The key associated with this node.
         * @param value The value associated with this node.
         */
        TreeMapNode(K key, V value) {
            this.key = key;
            this.value = value;
            left = right = null;
        }
    }
}

