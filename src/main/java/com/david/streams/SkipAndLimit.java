package com.david.streams;

import java.util.List;
import java.util.stream.IntStream;

public class SkipAndLimit {

    static void main(String[] args) {

        List<Integer> result =
                IntStream.rangeClosed(1, 10)
                        .boxed()
                        .skip(1)
                        .limit(7)
                        .toList();

        System.out.println(result);
    }

}
