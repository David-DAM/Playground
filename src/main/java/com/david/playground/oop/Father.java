package com.david.playground.oop;

public class Father extends Human implements IPerson {

    private String name;
    private int age;

    public Father(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public void walk() {
        System.out.println("Waking fast");
    }

    @Override
    public void bread() {
        System.out.println("Breathing");
    }
}
