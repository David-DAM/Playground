package com.david.design_patterns.chain_responsibility;

public interface Handler {
    void setNext(Handler next);

    void handle(User user);
}

