package com.david.playground.problems;

import java.util.ArrayList;
import java.util.List;

public class GroupAnagrams {

    /**
     * Given an array of strings strs, group the anagrams together. You can return the answer in any order.
     *
     * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.
     * @param args
     */
    public static void main(String[] args) {

        List<String> words = List.of("eat","tea","tan","ate","nat","bat");
        List<List<String>> groupedWords = new ArrayList<>();

        words.forEach(word ->{

            String[] split = word.split("");

            List<String> sameLettersWords = words.stream()
                    .filter(wordCompared -> {

                        for(String letter: split){
                            boolean contains = wordCompared.contains(letter);

                            if (!contains) return false;
                        }

                        return true;
                    })
                    .toList();

            groupedWords.add(sameLettersWords);
        });

        List<List<String>> distinct = groupedWords.stream().distinct().toList();
        System.out.println(distinct);

    }

}
