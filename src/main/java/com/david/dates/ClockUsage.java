package com.david.dates;

import java.time.Clock;
import java.time.LocalDate;

public class ClockUsage {

    static void main(String[] args) {

        Clock clock = Clock.systemUTC();

        LocalDate date = LocalDate.now(clock);

        System.out.println(date);
    }

}
