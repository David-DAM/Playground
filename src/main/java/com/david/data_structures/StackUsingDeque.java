package com.david.data_structures;

import java.util.ArrayDeque;
import java.util.Deque;

public class StackUsingDeque {

    static void main(String[] args) {
        //LIFO
        Deque<String> stack = new ArrayDeque<>();

        stack.push("piano");
        stack.push("flat");
        stack.push("guitar");

        stack.pop();

        stack.forEach(System.out::println);
    }

}
