package com.david.problems;

public class CountWaysToBuilding {
    /**
     * Given the integers zero, one, low, and high, we can construct a string by starting with an empty string, and then at each step perform either of the following:
     *
     * Append the character '0' zero times.
     * Append the character '1' one times.
     * This can be performed any number of times.
     *
     * A good string is a string constructed by the above process having a length between low and high (inclusive).
     *
     * Return the number of different good strings that can be constructed satisfying these properties. Since the answer can be large, return it modulo 109 + 7.
     *
     *
     *
     * Example 1:
     *
     * Input: low = 3, high = 3, zero = 1, one = 1
     * Output: 8
     * Explanation:
     * One possible valid good string is "011".
     * It can be constructed as follows: "" -> "0" -> "01" -> "011".
     * All binary strings from "000" to "111" are good strings in this example.
     * @param args
     */
    public static void main(String[] args) {
        countGoodStrings(3,3,1,1);
    }
    static int countGoodStrings(int low, int high, int zero, int one) {
        StringBuilder finalString = new StringBuilder("");

        for (int i = 0; i < zero; i++) {
            finalString.append(i);
        }

        for (int j = 0; j < one; j++) {
            finalString.append(j);
        }

        while (finalString.length() < low){
            finalString.append("1");
        }

        return 0;
    }
}
