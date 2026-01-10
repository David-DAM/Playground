package com.david.dates;

import java.time.LocalDate;

public class CompareDates {

    static void main(String[] args) {

        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plusDays(1);

        System.out.println(today.isBefore(tomorrow));
        System.out.println(today.isAfter(tomorrow));
        System.out.println(today.isEqual(tomorrow));
    }

}
