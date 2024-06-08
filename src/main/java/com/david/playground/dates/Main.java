package com.david.playground.dates;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.*;

public class Main {
    public static void main(String[] args) {
        LocalDate nowDate = LocalDate.now();
        LocalTime nowTime = LocalTime.now();
        String spainDate = "21/07/2023";

        /*
        LocalDate formattedDate = formattedDate(spainDate);
        System.out.println(formattedDate);
        */

        /*
        LocalDate addTimeToDate = addTimeToDate(nowDate, 2,"DAYS");
        System.out.println(addTimeToDate);
        */

        System.out.println(nowTime);
    }

    private static LocalDate formattedDate(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(date,formatter);
    }

    private static LocalDate addTimeToDate(LocalDate date, long time,String type){

        switch (type){
            case "DAYS" -> date = date.plusDays(time);
            case "MONTHS" -> date = date.plusMonths(time);
            case "YEARS" -> date = date.plusYears(time);
        }

        return date;
    }
}
