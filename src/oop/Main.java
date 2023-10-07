package oop;

public class Main {

    public static void main(String[] args) {
        Father father = new Father("Luis",42);
        Son son = new Son("Timmy",12,"Basketball");
        
        father.walk();
        son.walk();

        father.bread();
        son.bread();


    }
}
