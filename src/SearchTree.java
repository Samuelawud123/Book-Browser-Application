/**
 * A generic binary search tree (BST) implementation.
 *
 * @param <E> The type of elements in the tree. Must be a Comparable type.
 */
public class SearchTree<E extends Comparable<E>> {
    private SearchTreeNode<E> overallRoot; // Root node of the tree
    private int size; // Size of the tree

    /**
     * Constructs an empty search tree.
     */
    public SearchTree() {
        overallRoot = null;
        size = 0; // Initialize the size to 0
    }

    /**
     * Adds a value to the tree while maintaining the BST property.
     *
     * @param value The value to be added. Must not be null.
     * @throws IllegalArgumentException if the value is null.
     */
    public void add(E value) {
        if (value == null) {
            throw new IllegalArgumentException("Cannot add null to the tree.");
        }
        overallRoot = add(overallRoot, value);
    }

    /**
     * Recursive helper method to add a value in the tree.
     *
     * @param root  The root of the current subtree.
     * @param value The value to be added.
     * @return The root of the updated subtree.
     */
    private SearchTreeNode<E> add(SearchTreeNode<E> root, E value) {
        if (root == null) {
            size++; // Increment size when adding a new node
            return new SearchTreeNode<>(value);
        } else if (value.compareTo(root.data) <= 0) {
            root.left = add(root.left, value);
        } else {
            root.right = add(root.right, value);
        }
        return root;
    }

    /**
     * Searches for a value in the tree.
     *
     * @param value The value to search for.
     * @return true if the value is in the tree, false otherwise.
     */
    public boolean contains(E value) {
        return contains(overallRoot, value);
    }

    /**
     * Recursive helper method to search for a value in the tree.
     *
     * @param root  The root of the current subtree.
     * @param value The value to search for.
     * @return true if the value is found, false otherwise.
     */
    private boolean contains(SearchTreeNode<E> root, E value) {
        if (root == null) {
            return false;
        } else {
            int compare = value.compareTo(root.data);
            if (compare == 0) {
                return true;
            } else if (compare < 0) {
                return contains(root.left, value);
            } else {
                return contains(root.right, value);
            }
        }
    }

    /**
     * Removes all elements from the tree.
     */
    public void clear() {
        overallRoot = null;
        size = 0; // Reset the size to 0
    }

    /**
     * Returns the number of elements in the tree.
     *
     * @return The size of the tree.
     */
    public int size() {
        return size;
    }

    /**
     * Inner class representing a node in the BST.
     *
     * @param <E> The type of data stored in the node.
     */
    private static class SearchTreeNode<E> {
        E data; // Data stored in the node
        SearchTreeNode<E> left; // Reference to the left subtree
        SearchTreeNode<E> right; // Reference to the right subtree

        /**
         * Constructs a node with the given data.
         *
         * @param data The data to be stored in this node.
         */
        public SearchTreeNode(E data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }
}
