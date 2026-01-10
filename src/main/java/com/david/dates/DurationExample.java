package com.david.dates;

import java.time.Duration;
import java.time.LocalTime;

public class DurationExample {

    static void main(String[] args) {

        LocalTime start = LocalTime.now();
        LocalTime end = start.plusSeconds(90);

        Duration duration = Duration.between(start, end);

        System.out.println(duration.getSeconds());
    }

}
