package com.david.streams;

import java.util.Arrays;
import java.util.List;

public class RemoveDuplicates {

    static void main(String[] args) {

        Integer[] numbers = {5, 6, 7, 8, 5};

        List<Integer> distinct =
                Arrays.stream(numbers)
                        .distinct()
                        .toList();

        System.out.println(distinct);
    }

}
