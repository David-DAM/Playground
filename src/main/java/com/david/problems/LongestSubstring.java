package com.david.problems;

import java.util.*;

public class LongestSubstring {
    /**
     * Given a string s, find the length of the longest
     * substring
     *  without repeating characters.
     * @param args
     */
    public static void main(String[] args) {
        /**
         * abcabcbb = 3
         * bbbbb = 1
         * pwwkew = 3
         */
        int length = lengthOfLongestSubstring("pwwkew");

        System.out.println(length);
    }

    public static int lengthOfLongestSubstring(String s) {
        String[] split = s.split("");
        List<String> substrings = new ArrayList<>();
        StringBuilder builder = new StringBuilder();

        for (String letter: split) {

            if (builder.toString().contains(letter)){
                substrings.add(builder.toString());

                builder = new StringBuilder();
                builder.append(letter);
            }else {
                builder.append(letter);
            }

        }

        return substrings.stream()
                .reduce( (x,y) -> x.length() > y.length() ? x : y )
                .get()
                .length();
    }
}
