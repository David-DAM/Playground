package com.david.dates;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public class TemporalAdjustersExample {

    static void main(String[] args) {

        LocalDate now = LocalDate.now();

        LocalDate firstDayOfMonth = now.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate lastDayOfYear = now.with(TemporalAdjusters.lastDayOfYear());

        System.out.println(firstDayOfMonth);
        System.out.println(lastDayOfYear);
    }

}
