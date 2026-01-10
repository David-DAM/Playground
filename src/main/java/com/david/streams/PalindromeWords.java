package com.david.streams;

import java.util.List;

public class PalindromeWords {
    static void main(String[] args) {

        List<String> words = List.of("ala", "pepe", "lol", "lola");

        words.stream()
                .filter(PalindromeWords::isPalindrome)
                .forEach(System.out::println);
    }

    private static boolean isPalindrome(String word) {
        return new StringBuilder(word).reverse().toString().equals(word);
    }
}
