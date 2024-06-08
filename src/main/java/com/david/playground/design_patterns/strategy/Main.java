package com.david.playground.design_patterns.strategy;

public class Main {

    public static void main(String[] args) {

        IPayment paymentService = new StripePaymentService();
        //IPayment paymentService = new VisaPaymentService();

        boolean pay = paymentService.pay(20);

        System.out.println(pay);

    }


}
