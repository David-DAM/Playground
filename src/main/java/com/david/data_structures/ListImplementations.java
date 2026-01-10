package com.david.data_structures;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListImplementations {

    static void main(String[] args) {

        List<String> arrayList = new ArrayList<>();
        List<String> linkedList = new LinkedList<>();

        arrayList.add("piano");
        linkedList.add("flat");

        System.out.println(arrayList);
        System.out.println(linkedList);
    }

}
