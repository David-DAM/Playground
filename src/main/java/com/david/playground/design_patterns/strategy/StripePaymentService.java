package com.david.playground.design_patterns.strategy;

public class StripePaymentService implements IPayment{

    public StripePaymentService() {
    }

    @Override
    public boolean pay(double amount) {
        return true;
    }

    @Override
    public boolean retrive() {
        return true;
    }
}
