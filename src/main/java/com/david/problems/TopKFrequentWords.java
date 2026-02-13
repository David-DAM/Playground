package com.david.problems;

import java.util.*;

public class TopKFrequentWords {

    static void main() {
        String[] words = {"i", "love", "leetcode", "i", "love", "coding"};
        int k = 2;
        List<String> result = topKFrequent(words, k);
        System.out.println(result);
    }

    public static List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> freq = new HashMap<>();

        for (String w : words)
            freq.put(w, freq.getOrDefault(w, 0) + 1);

        PriorityQueue<String> heap = new PriorityQueue<>(
                (a, b) -> {
                    int cmp = freq.get(a) - freq.get(b);
                    if (cmp == 0) return b.compareTo(a);
                    return cmp;
                }
        );

        for (String w : freq.keySet()) {
            heap.offer(w);
            if (heap.size() > k) heap.poll();
        }

        List<String> res = new ArrayList<>();
        while (!heap.isEmpty()) res.add(heap.poll());
        Collections.reverse(res);
        return res;
    }
}
