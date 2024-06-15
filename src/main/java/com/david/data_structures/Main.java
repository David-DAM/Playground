package com.david.data_structures;

import java.math.BigDecimal;
import java.util.*;

public class Main {
    public static void main(String[] args) {

       queue();

    }

    private static void bigDecimal() {
        BigDecimal bigDecimal = new BigDecimal(3.123456789);
        bigDecimal.add(BigDecimal.valueOf(0.1));
        System.out.println(bigDecimal);
    }

    private static void stack() {
        Stack<String> stack = new Stack<>();

        stack.push("piano");
        stack.push("flat");
        stack.push("piano");

        stack.pop();

        stack.forEach(System.out::println);
    }

    private static void queue() {
        Queue<String> queue = new ArrayDeque<>();

        queue.add("piano");
        queue.add("flat");
        queue.add("piano");

        queue.poll();

        queue.forEach(System.out::println);
    }

    private static void vector() {
        Vector<String> vector = new Vector<>();

        vector.add("piano");
        vector.add(0,"flat");
        vector.add("piano");

        vector.forEach(System.out::println);
    }

    private static void list() {
        List<String> list = new ArrayList<>();

        list.add("piano");
        list.add("flat");
        list.add("piano");

        list.forEach(System.out::println);
    }

    private static void set() {
        Set<String> set = new HashSet<>();

        set.add("piano");
        set.add("flat");
        set.add("piano");

        set.forEach(System.out::println);
    }

    private static void map() {
        Map<String,String> map = new HashMap<>();

        map.put("piano","instrument");
        map.put("flat","apartment");

        map.forEach((a,b) -> System.out.println(a + ":" + b) );
    }


}
