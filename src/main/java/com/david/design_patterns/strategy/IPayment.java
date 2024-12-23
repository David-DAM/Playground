package com.david.design_patterns.strategy;

public interface IPayment {
    boolean pay(double amount);

    boolean retrive();
}
