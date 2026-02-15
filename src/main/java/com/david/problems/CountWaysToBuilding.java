package com.david.problems;

public class CountWaysToBuilding {
    /**
     * Given the integers zero, one, low, and high, we can construct a string by starting with an empty string, and then at each step perform either of the following:
     * <p>
     * Append the character '0' zero times.
     * Append the character '1' one times.
     * This can be performed any number of times.
     * <p>
     * A good string is a string constructed by the above process having a length between low and high (inclusive).
     * <p>
     * Return the number of different good strings that can be constructed satisfying these properties. Since the answer can be large, return it modulo 109 + 7.
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input: low = 3, high = 3, zero = 1, one = 1
     * Output: 8
     * Explanation:
     * One possible valid good string is "011".
     * It can be constructed as follows: "" -> "0" -> "01" -> "011".
     * All binary strings from "000" to "111" are good strings in this example.
     *
     * @param args
     */
    static void main(String[] args) {
        countGoodStrings(3, 3, 1, 1);
    }

    static int countGoodStrings(int low, int high, int zero, int one) {
        StringBuilder finalString = new StringBuilder("");

        for (int i = 0; i < zero; i++) {
            finalString.append(i);
        }

        for (int j = 0; j < one; j++) {
            finalString.append(j);
        }

        while (finalString.length() < low) {
            finalString.append("1");
        }

        return 0;
    }
}
