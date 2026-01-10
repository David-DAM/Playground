package com.david.design_patterns.chain_responsibility;

public class UserExistsValidator extends AbstractHandler {

    @Override
    public void handle(User user) {
        if ("existing@example.com".equals(user.email())) {
            throw new IllegalArgumentException("User already exists");
        }
        next(user);
    }
}

