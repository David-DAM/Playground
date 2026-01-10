package com.david.data_structures;

import java.util.ArrayDeque;
import java.util.Queue;

public class QueueUsingDeque {

    static void main(String[] args) {
        //FIFO
        Queue<String> queue = new ArrayDeque<>();

        queue.add("piano");
        queue.add("flat");
        queue.add("guitar");

        queue.poll();

        queue.forEach(System.out::println);
    }

}
