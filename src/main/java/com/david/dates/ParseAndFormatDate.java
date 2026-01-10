package com.david.dates;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ParseAndFormatDate {

    static void main(String[] args) {

        String spanishDate = "21/07/2023";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate date = LocalDate.parse(spanishDate, formatter);

        String formatted = date.format(DateTimeFormatter.ISO_DATE);

        System.out.println(date);
        System.out.println(formatted);
    }

}
