package com.david.data_structures;

import java.util.TreeMap;
import java.util.TreeSet;

public class TreeImplementation {

    static void main(String[] args) {
        //TreeSet example - stores elements in sorted order
        TreeSet<Integer> treeSet = new TreeSet<>();

        treeSet.add(2);
        treeSet.add(5);
        treeSet.add(3);

        System.out.println("TreeSet sorted :");
        treeSet.forEach(System.out::println);

        System.out.printf("Higher value than 3 is %d%n", treeSet.higher(3));
        System.out.printf("Lower value than 3 is %d%n", treeSet.lower(3));
        System.out.printf("First element is %d%n", treeSet.first());
        System.out.printf("Last element is %d%n", treeSet.last());

        System.out.printf("Subset elements between 2 and 5 are %s%n", treeSet.subSet(2, 5));

        //TreeMap example - stores key-value pairs in sorted order by keys
        TreeMap<Integer, String> treeMap = new TreeMap<>();

        treeMap.put(3, "Third");
        treeMap.put(1, "First");
        treeMap.put(2, "Second");

        System.out.println("\nTreeMap (sorted by keys):");
        treeMap.forEach((key, value) -> System.out.println(key + ": " + value));

        System.out.println("Floor key menor o igual a 2: " + treeMap.floorKey(2));
        System.out.println("Ceiling key mayor o igual a 2: " + treeMap.ceilingKey(2));
        System.out.println("Head map (menores que 3): " + treeMap.headMap(3));
        System.out.println("Tail map (mayores o iguales que 3): " + treeMap.tailMap(3));
        System.out.println("Sub map (entre 2 y 3): " + treeMap.subMap(2, 3));
        System.out.println("First key: " + treeMap.firstKey());
        System.out.println("Last key: " + treeMap.lastKey());

    }


}
