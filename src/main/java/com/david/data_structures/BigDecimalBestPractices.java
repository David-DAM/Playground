package com.david.data_structures;

import java.math.BigDecimal;

public class BigDecimalBestPractices {

    static void main(String[] args) {

        BigDecimal value = new BigDecimal("3.123456789");
        BigDecimal result = value.add(BigDecimal.valueOf(0.1));

        System.out.println(result);
    }

}
