/**
 * This class contains a set of JUnit 5 tests for the TreeMap class.
 * It tests various functionalities of the TreeMap class, including insertion, retrieval, clearing,
 * size calculation, key existence check, and key array extraction.
 *
 * @author Your Name
 * @version 1.0
 */
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class TreeMapTest {

    private TreeMap<Integer, String> treeMap;

    /**
     * Sets up a new TreeMap instance before each test method is run.
     */
    @BeforeEach
    public void setUp() {
        treeMap = new TreeMap<>();
    }

    /**
     * Tests the size() method of the TreeMap.
     * Ensures that the size is correctly updated when new key-value pairs are added.
     */
    @Test
    public void testSize() {
        // Ensure that the size of an empty tree is 0
        assertEquals(0, treeMap.size());

        // Add key-value pairs and verify the size
        treeMap.put(2767052, "Suzanne Collins");
        treeMap.put(41865, "Stephenie Meyer");
        assertEquals(2, treeMap.size());

        // Add another key-value pair and verify the updated size
        treeMap.put(2657, "Harper Lee");
        assertEquals(3, treeMap.size());
    }

    /**
     * Tests the clear() method of the TreeMap.
     * Ensures that the TreeMap is correctly cleared of all entries.
     */
    @Test
    public void testClear() {
        // Add key-value pairs
        treeMap.put(2767052, "Suzanne Collins");
        treeMap.put(41865, "Stephenie Meyer");

        // Clear the TreeMap and verify its size is 0
        treeMap.clear();
        assertEquals(0, treeMap.size());

        // Verify that getting values for non-existing keys returns null
        assertNull(treeMap.get(1));
        assertNull(treeMap.get(2));
    }

    /**
     * Tests the get() method of the TreeMap.
     * Checks that the correct value is returned for existing keys and null for non-existing keys.
     */
    @Test
    public void testGet() {
        // Add key-value pairs
        treeMap.put(2767052, "Suzanne Collins");
        treeMap.put(41865, "Stephenie Meyer");

        // Verify that values can be retrieved correctly
        assertEquals("Suzanne Collins", treeMap.get(2767052));
        assertEquals("Stephenie Meyer", treeMap.get(41865));

        // Verify that getting values for non-existing keys returns null
        assertNull(treeMap.get(3));
    }

    /**
     * Tests the put() method of the TreeMap.
     * Verifies that new entries can be added and existing entries can be updated.
     */
    @Test
    public void testPut() {
        // Add a key-value pair and verify the value
        treeMap.put(2767052, "Suzanne Collins");
        assertEquals("Suzanne Collins", treeMap.get(2767052));

        // Update the existing key with a new value and verify the updated value
        treeMap.put(2767052, "New Suzanne Collins");
        assertEquals("New Suzanne Collins", treeMap.get(2767052));
    }

    /**
     * Tests the containsKey() method of the TreeMap.
     * Checks that the method correctly identifies the presence or absence of keys.
     */
    @Test
    public void testContainsKey() {
        // Add key-value pairs
        treeMap.put(2767052, "Suzanne Collins");

        // Verify that the TreeMap contains the added key and not another key
        assertTrue(treeMap.containsKey(2767052));
        assertFalse(treeMap.containsKey(41865));
    }

    /**
     * Tests the toKeyArray() method of the TreeMap.
     * Verifies that the method correctly returns an array of all keys in sorted order.
     */
    @Test
    public void testToKeyArray() {
        // Add key-value pairs
        treeMap.put(2767052, "Suzanne Collins");
        treeMap.put(41865, "Stephenie Meyer");
        treeMap.put(2657, "Harper Lee");

        // Create an expected array of keys and verify the sorted key array
        Integer[] expected = {2657, 41865, 2767052};
        Integer[] actual = treeMap.toKeyArray(new Integer[treeMap.size()]);
        assertArrayEquals(expected, actual);
    }

    /**
     * Tests the toValueArray() method of the TreeMap.
     * Verifies that the method correctly returns an array of all values in sorted order.
     */
    @Test
    public void testToValueArray() {
        // Add key-value pairs
        treeMap.put(2767052, "Suzanne Collins");
        treeMap.put(41865, "Stephenie Meyer");
        treeMap.put(2657, "Harper Lee");

        // Create an expected array of values and verify the sorted value array
        String[] expected = {"Harper Lee", "Stephenie Meyer", "Suzanne Collins"};
        String[] actual = treeMap.toValueArray(new String[treeMap.size()]);
        assertArrayEquals(expected, actual);
    }

    /**
     * Tests the keySet() method of the TreeMap.
     * Verifies that the method correctly returns a Set of all keys in the TreeMap.
     */
    @Test
    public void testKeySet() {
        // Add key-value pairs
        treeMap.put(2767052, "Suzanne Collins");
        treeMap.put(41865, "Stephenie Meyer");
        treeMap.put(2657, "Harper Lee");

        // Get the key set and verify key existence
        Set<Integer> keySet = treeMap.keySet();
        assertTrue(keySet.contains(2767052));
        assertTrue(keySet.contains(41865));
        assertTrue(keySet.contains(2657));
        assertFalse(keySet.contains(12345));
    }
}
