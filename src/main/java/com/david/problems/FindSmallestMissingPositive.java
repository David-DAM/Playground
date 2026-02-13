package com.david.problems;

import java.util.Arrays;
import java.util.List;

public class FindSmallestMissingPositive {

    static void main() {
        int result = findSmallestMissingPositive(Arrays.asList(3, 4, -1, 1));
        System.out.println("Result: " + result);
    }

    public static int findSmallestMissingPositive(List<Integer> orderNumbers) {

        if (orderNumbers == null || orderNumbers.isEmpty()) return 0;

        int result = 0;

        for (int i = 0; i < orderNumbers.size(); i++) {

            int number = orderNumbers.get(i);

            if (number > i + 1) {
                int temp = orderNumbers.get(number - 1);
                orderNumbers.set(number - 1, number);
                orderNumbers.set(i, temp);

            }

        }

        for (int i = 0; i < orderNumbers.size(); i++) {
            if (orderNumbers.get(i) != i + 1) {
                result = i + 1;
            }
        }

        return result;
    }
}
