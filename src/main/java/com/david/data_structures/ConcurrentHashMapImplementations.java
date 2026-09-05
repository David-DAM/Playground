package com.david.data_structures;

import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapImplementations {

    static void main(String[] args) {
        System.out.println("=== ConcurrentHashMap Atomic Operations Examples ===\n");

        putIfAbsentExample();
        removeExample();
        replaceExample();
        computeExample();
        mergeExample();
    }

    /**
     * putIfAbsent() - Atomically puts a value if the key is not present
     * Returns null if key was absent (value was put), or the existing value if key was present
     */
    private static void putIfAbsentExample() {
        System.out.println("--- putIfAbsent() Example ---");
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();

        // First call - key doesn't exist, returns null and puts the value
        Integer result1 = map.putIfAbsent("count", 1);
        System.out.println("First putIfAbsent('count', 1): " + result1); // null
        System.out.println("Map contains: " + map); // {count=1}

        // Second call - key exists, returns existing value and doesn't update
        Integer result2 = map.putIfAbsent("count", 5);
        System.out.println("Second putIfAbsent('count', 5): " + result2); // 1
        System.out.println("Map contains: " + map); // {count=1}
        System.out.println();
    }

    /**
     * remove(key, value) - Atomically removes the entry only if the key maps to the specified value
     * Returns true if removed, false otherwise
     */
    private static void removeExample() {
        System.out.println("--- remove(key, value) Example ---");
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        map.put("status", "active");

        // Try to remove with wrong value - returns false
        boolean removed1 = map.remove("status", "inactive");
        System.out.println("Remove with wrong value: " + removed1); // false
        System.out.println("Map contains: " + map); // {status=active}

        // Remove with correct value - returns true
        boolean removed2 = map.remove("status", "active");
        System.out.println("Remove with correct value: " + removed2); // true
        System.out.println("Map contains: " + map); // {}
        System.out.println();
    }

    /**
     * replace() operations - Atomically replace values
     * replace(key, value) - replaces value if key exists
     * replace(key, oldValue, newValue) - replaces only if current value matches oldValue
     */
    private static void replaceExample() {
        System.out.println("--- replace() Examples ---");
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
        map.put("score", 100);

        // replace(key, value) - returns old value if key exists, null otherwise
        Integer oldValue = map.replace("score", 200);
        System.out.println("replace('score', 200) returned: " + oldValue); // 100
        System.out.println("Map contains: " + map); // {score=200}

        // replace(key, oldValue, newValue) - returns true if replacement happened
        boolean replaced1 = map.replace("score", 100, 300);
        System.out.println("replace('score', 100, 300): " + replaced1); // false (current is 200)

        boolean replaced2 = map.replace("score", 200, 300);
        System.out.println("replace('score', 200, 300): " + replaced2); // true
        System.out.println("Map contains: " + map); // {score=300}
        System.out.println();
    }

    /**
     * compute family operations - Atomically compute new values based on existing ones
     * compute() - computes new value for a key
     * computeIfAbsent() - computes value only if key is absent
     * computeIfPresent() - computes value only if key is present
     */
    private static void computeExample() {
        System.out.println("--- compute() Family Examples ---");
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();

        // computeIfAbsent - only computes if key is absent
        map.computeIfAbsent("visits", k -> 1);
        System.out.println("After computeIfAbsent: " + map); // {visits=1}

        map.computeIfAbsent("visits", k -> 10); // Won't update
        System.out.println("Second computeIfAbsent (no change): " + map); // {visits=1}

        // computeIfPresent - only computes if key is present
        map.computeIfPresent("visits", (k, v) -> v + 1);
        System.out.println("After computeIfPresent (increment): " + map); // {visits=2}

        // compute - always computes (can be used to add, update, or remove)
        map.compute("visits", (k, v) -> v == null ? 1 : v * 2);
        System.out.println("After compute (double): " + map); // {visits=4}

        // Returning null removes the entry
        map.compute("visits", (k, v) -> null);
        System.out.println("After compute returning null: " + map); // {}
        System.out.println();
    }

    /**
     * merge() - Atomically updates a value by merging it with a new value
     * If key is absent, puts the new value
     * If key is present, applies the remapping function
     * If remapping function returns null, removes the entry
     */
    private static void mergeExample() {
        System.out.println("--- merge() Example ---");
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();

        // Key doesn't exist - puts the value
        map.merge("count", 1, Integer::sum);
        System.out.println("After first merge: " + map); // {count=1}

        // Key exists - applies the function (sum in this case)
        map.merge("count", 5, Integer::sum);
        System.out.println("After second merge: " + map); // {count=6}

        map.merge("count", 10, Integer::sum);
        System.out.println("After third merge: " + map); // {count=16}

        // Practical example: counting word occurrences
        ConcurrentHashMap<String, Integer> wordCount = new ConcurrentHashMap<>();
        String[] words = {"hello", "world", "hello", "java", "world", "hello"};

        for (String word : words) {
            wordCount.merge(word, 1, Integer::sum);
        }
        System.out.println("Word count: " + wordCount); // {java=1, world=2, hello=3}
        System.out.println();
    }

}
