package com.david.design_patterns.chain_responsibility;

public abstract class AbstractHandler implements Handler {

    private Handler next;

    @Override
    public void setNext(Handler next) {
        this.next = next;
    }

    protected void next(User user) {
        if (next != null) {
            next.handle(user);
        }
    }
}
