package com.david.playground.oop;

public class Son extends Father{


    private String hobby;


    public Son(String name, int age,String hobby) {
        super(name, age);
        this.hobby = hobby;
    }
    @Override
    public void walk(){
        System.out.println("Walking slow");
    }
}
