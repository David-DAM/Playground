package com.david.playground.design_patterns.strategy;

public class VisaPaymentService implements IPayment{

    public VisaPaymentService() {
    }

    @Override
    public boolean pay(double amount) {
        return false;
    }

    @Override
    public boolean retrive() {
        return false;
    }
}
