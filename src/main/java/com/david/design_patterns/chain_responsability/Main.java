package com.david.design_patterns.chain_responsability;

public class Main {
    public static void main(String[] args) {

        var chain = UserValidatorChain.build();

        var user = new User("test@example.com", "123456");

        chain.handle(user);

        System.out.println("Valid user!");
    }
}
