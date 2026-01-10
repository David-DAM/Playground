package com.david.data_structures;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class MapImplementations {

    static void main(String[] args) {

        Map<String, String> hashMap = new HashMap<>();
        Map<String, String> linkedHashMap = new LinkedHashMap<>();
        Map<String, String> treeMap = new TreeMap<>();

        hashMap.put("piano", "instrument");
        hashMap.put("flat", "apartment");

        linkedHashMap.putAll(hashMap);
        treeMap.putAll(hashMap);

        hashMap.forEach((k, v) -> System.out.println(k + ":" + v));
    }

}
