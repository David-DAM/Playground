package com.david.libraries.faker;

import com.david.design_patterns.builder.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Faker {

    static void main(String[] args) {

        com.github.javafaker.Faker faker = new com.github.javafaker.Faker(Locale.forLanguageTag("es-ES"));

        List<Person> personList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {

            String name = faker.name().firstName();
            String lastName = faker.name().lastName();
            int age = faker.number().numberBetween(0, 99);
            String phoneNumber = faker.phoneNumber().cellPhone();
            double height = faker.number().randomDouble(2, 1, 2);

            Person person = new Person.PersonBuilder()
                    .setName(name)
                    .setLastname(lastName)
                    .setAge(age)
                    .setHeight(height)
                    .setPhones(List.of(phoneNumber))
                    .build();

            personList.add(person);
        }

        personList.forEach(System.out::println);


    }

}
