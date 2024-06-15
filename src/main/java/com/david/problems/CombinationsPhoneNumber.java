package com.david.problems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CombinationsPhoneNumber {
    public static void main(String[] args) {
        List<String> letterCombinations = letterCombinations("23");
        System.out.println(letterCombinations);
    }

    public static List<String> letterCombinations(String digits) {
        Map<String,String[]> digistsLetters = new HashMap<>();
        digistsLetters.put("2",new String[]{"a","b","c"});
        digistsLetters.put("3",new String[]{"d","e","f"});
        digistsLetters.put("4",new String[]{"g","h","i"});
        digistsLetters.put("5",new String[]{"j","k","l"});
        digistsLetters.put("6",new String[]{"m","n","o"});
        digistsLetters.put("7",new String[]{"p","q","r"});
        digistsLetters.put("8",new String[]{"s","t","u"});
        digistsLetters.put("9",new String[]{"w","x","y","z"});

        List<String> combinations = new ArrayList<>();

        StringBuilder combination = new StringBuilder();

        String[] split = digits.split("");

        for (int i = 0; i < split.length - 1; i++) {
            //2

            for (int j = 0; j < digistsLetters.get(split[i]).length; j++) {
                //a
                String letterOrigin = digistsLetters.get(split[i])[j];

                combination.append(letterOrigin);

                String letterNext = digistsLetters.get(split[i+1])[j];

                combination.append(letterNext);

                combinations.add(combination.toString());

                combination = new StringBuilder();

            }
        }

        return  combinations;
    }
}