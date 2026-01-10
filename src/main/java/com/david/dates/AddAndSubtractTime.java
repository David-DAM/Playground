package com.david.dates;

import java.time.LocalDate;

public class AddAndSubtractTime {

    static void main(String[] args) {

        LocalDate now = LocalDate.now();

        System.out.println(now.plusDays(5));
        System.out.println(now.minusMonths(2));
        System.out.println(now.plusYears(1));
    }

}
