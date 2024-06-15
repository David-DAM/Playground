package com.david.design_patterns.builder;

public class Main {
    public static void main(String[] args) {
        Person person = new Person.PersonBuilder()
                .setHeight(1.80)
                .setAge(23)
                .setName("David")
                .setLastname("Jimenez")
                .build();

        System.out.println(person);
    }
}
