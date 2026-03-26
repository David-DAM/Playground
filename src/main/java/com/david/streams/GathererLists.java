package com.david.streams;

import java.util.List;
import java.util.stream.Gatherers;

public class GathererLists {

    static void main(String[] args) {

        List<Integer> ages = List.of(10, 20, 30, 40, 50);

        List<List<Integer>> lists = ages.stream()
                .gather(Gatherers.windowFixed(2))
                .toList();

        lists.forEach(System.out::println);
    }

}
