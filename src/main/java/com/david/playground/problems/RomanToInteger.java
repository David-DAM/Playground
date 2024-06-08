package com.david.playground.problems;

import java.util.HashMap;
import java.util.Map;

public class RomanToInteger {
    public static void main(String[] args) {
        int romanToInt = romanToInt("MCMXCIV");
        System.out.println(romanToInt);
    }

    public static int romanToInt(String s) {

        int number = 0;

        Map<String,Integer> letters = new HashMap<>();
        letters.put("I",1);
        letters.put("V",5);
        letters.put("X",10);
        letters.put("L",50);
        letters.put("C",100);
        letters.put("D",500);
        letters.put("M",1000);

        String[] split = s.split("");

        for (int i = 0; i < split.length; i++) {

            if (i != split.length - 1 ){

                if (letters.get(split[i+1]) > letters.get(split[i])){

                    number += letters.get(split[i + 1]) - letters.get(split[i]);
                    i++;

                }else {

                    number += letters.get(split[i]);

                }

            }else {

                number += letters.get(split[i]);

            }


        }

        return number;
    }
}
