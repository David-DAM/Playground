package com.david.design_patterns.chain_responsability;

public interface Handler {
    void setNext(Handler next);
    void handle(User user);
}

