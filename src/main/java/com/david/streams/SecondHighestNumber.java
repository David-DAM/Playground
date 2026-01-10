package com.david.streams;

import java.util.Arrays;
import java.util.Comparator;

public class SecondHighestNumber {

    static void main(String[] args) {

        int[] numbers = {1, 3, 14, 24, 35, 56, 68, 23};

        Integer secondHighest =
                Arrays.stream(numbers)
                        .boxed()
                        .sorted(Comparator.reverseOrder())
                        .skip(1)
                        .findFirst()
                        .orElseThrow();

        System.out.println(secondHighest);
    }

}
