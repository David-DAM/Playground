package com.david.playground;

import com.david.playground.design_patterns.builder.Person;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Person person = new Person.PersonBuilder()
                .setAge(13)
                .setPhones(null)
                .build();

        //String s = person.getPhones().get(0);
        String string = Optional.ofNullable(person)
                .map(Person::getPhones)
                .map(x -> x.get(0))
                .orElse("");


        System.out.println(string);
    }
}