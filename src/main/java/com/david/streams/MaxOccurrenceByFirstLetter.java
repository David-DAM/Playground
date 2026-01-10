package com.david.streams;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MaxOccurrenceByFirstLetter {

    static void main(String[] args) {

        List<String> names = List.of("David", "Dante", "Alberto", "Carolina", "Estrella", "Coral");

        Map.Entry<String, Long> result =
                names.stream()
                        .collect(Collectors.groupingBy(
                                name -> name.substring(0, 1),
                                Collectors.counting()
                        ))
                        .entrySet()
                        .stream()
                        .max(Map.Entry.comparingByValue())
                        .orElseThrow();

        System.out.println(result);
    }

}
