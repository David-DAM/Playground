package com.david.streams;

import com.david.oop.Father;

import java.util.Collection;
import java.util.List;

public class ConcatenatedListFromObjects {

    static void main(String[] args) {

        Father father1 = new Father("Pepe", 38);
        father1.setPhoneNumbers(List.of("1", "2", "3"));

        Father father2 = new Father("Juan", 40);
        father2.setPhoneNumbers(List.of("4", "5", "6"));

        List<Father> fathers = List.of(father1, father2);

        List<String> phones = fathers.stream()
                .map(Father::getPhoneNumbers)
                .flatMap(Collection::stream)
                .toList();

        System.out.println(phones);
    }

}
