package com.david.design_patterns.chain_responsability;

public class PasswordLengthValidator extends AbstractHandler {

    @Override
    public void handle(User user) {
        if (user.password().length() < 6) {
            throw new IllegalArgumentException("Password too short");
        }
        next(user);
    }
}

