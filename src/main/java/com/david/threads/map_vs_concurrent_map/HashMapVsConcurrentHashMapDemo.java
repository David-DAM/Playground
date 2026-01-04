package com.david.threads.map_vs_concurrent_map;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class HashMapVsConcurrentHashMapDemo {
    static void main(String[] args) {
        Map<BadKey, Integer> hashMap = new HashMap<>();
        Map<BadKey, Integer> concurrentHashMap = new ConcurrentHashMap<>();

        try (ExecutorService executor = Executors.newFixedThreadPool(8)) {

            int operations = 100_000;

            for (int i = 0; i < operations; i++) {
                int value = i;
                executor.submit(() -> hashMap.put(new BadKey(value), value));
                executor.submit(() -> concurrentHashMap.put(new BadKey(value), value));
            }

            executor.shutdown();
            executor.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("HashMap size: " + hashMap.size());
        System.out.println("ConcurrentHashMap size: " + concurrentHashMap.size());
    }
}
