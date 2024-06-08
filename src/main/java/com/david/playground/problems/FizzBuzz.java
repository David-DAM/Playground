package com.david.playground.problems;

public class FizzBuzz {
    /**
     * Print each number from 1 to 100 on a new line
     * For each multiply of 3 print Fizz instead of a number
     * For each multiply of 5 print Buzz instead of a number
     * For each multiply of 3 and 5 print FizzBuzz instead of a number
     * @param args
     */
    public static void main(String[] args) {

        for (int i = 0; i < 100; i++) {
           if (i % 3 == 0 && i % 5 == 0) System.out.println("FizzBuzz");
           else if (i % 3 == 0) System.out.println("Fizz");
           else if (i % 5 == 0) System.out.println("Buzz");
           else System.out.println(i);

        }

    }

}
