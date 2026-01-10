package com.david.dates;

import java.time.LocalDate;
import java.time.Period;

public class PeriodBetweenDates {

    static void main(String[] args) {

        LocalDate start = LocalDate.of(2020, 1, 1);
        LocalDate end = LocalDate.now();

        Period period = Period.between(start, end);

        System.out.println("Years: " + period.getYears());
        System.out.println("Months: " + period.getMonths());
        System.out.println("Days: " + period.getDays());
    }

}
