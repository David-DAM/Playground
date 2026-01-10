package com.david.dates;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class ZoneIdAndZonedDateTime {

    static void main(String[] args) {

        ZonedDateTime madrid = ZonedDateTime.now(ZoneId.of("Europe/Madrid"));
        ZonedDateTime tokyo = ZonedDateTime.now(ZoneId.of("Asia/Tokyo"));

        System.out.println(madrid);
        System.out.println(tokyo);
    }

}
