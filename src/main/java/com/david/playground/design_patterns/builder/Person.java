package com.david.playground.design_patterns.builder;

import java.util.List;

public class Person {

    String name;
    String lastname;
    int age;
    double height;
    List<String> phones;

    private Person(PersonBuilder builder) {
        this.name = builder.name;
        this.lastname = builder.lastname;
        this.age = builder.age;
        this.height = builder.height;
        this.phones = builder.phones;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public List<String> getPhones() {
        return phones;
    }

    public void setPhones(List<String> phones) {
        this.phones = phones;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", age=" + age +
                ", height=" + height +
                ", phones=" + phones +
                '}';
    }

    public static class PersonBuilder{

        String name;
        String lastname;
        int age;
        double height;
        List<String> phones;

        public PersonBuilder() {

        }

        public PersonBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public PersonBuilder setLastname(String lastname) {
            this.lastname = lastname;
            return this;
        }

        public PersonBuilder setAge(int age) {
            this.age = age;
            return this;
        }

        public PersonBuilder setHeight(double height) {
            this.height = height;
            return this;
        }

        public PersonBuilder setPhones(List<String> phones) {
            this.phones = phones;
            return this;
        }

        public Person build(){
            return new Person(this);
        }
    }
}
