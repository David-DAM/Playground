package com.david.design_patterns.chain_responsibility;

public class EmailValidator extends AbstractHandler {

    @Override
    public void handle(User user) {
        if (!user.email().contains("@")) {
            throw new IllegalArgumentException("Invalid email");
        }
        next(user);
    }
}
