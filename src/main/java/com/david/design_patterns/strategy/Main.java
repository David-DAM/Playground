package com.david.design_patterns.strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        //In a spring application we can inject that list in the constructor automatically
        List<IPayment> list = new ArrayList<>();
        list.add(new StripePaymentService());
        list.add(new VisaPaymentService());

        //And then fill our map variable in the class with this method
        Map<String, IPayment> paymentMap = list.stream().collect(Collectors.toMap(x -> x.getClass().getSimpleName(), Function.identity()));

        System.out.println(paymentMap);

        IPayment paymentService = paymentMap.get("VisaPaymentService");//StripePaymentService

        boolean pay = paymentService.pay(20);

        System.out.println(pay);

    }


}
