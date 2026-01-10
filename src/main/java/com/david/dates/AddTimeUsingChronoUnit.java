package com.david.dates;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class AddTimeUsingChronoUnit {

    static void main(String[] args) {

        LocalDate date = LocalDate.now();

        LocalDate result = date.plus(10, ChronoUnit.DAYS);

        System.out.println(result);
    }

}
