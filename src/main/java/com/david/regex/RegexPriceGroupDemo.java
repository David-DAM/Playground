package com.david.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexPriceGroupDemo {
    static void main(String[] args) {

        //This pattern matches any price in dollars or euros
        Pattern pricePattern = Pattern.compile("\\$([\\d.]+)|€([\\d.]+)");

        String input = "The price is $15.99 and €10.50";
        Matcher priceMatcher = pricePattern.matcher(input);

        while (priceMatcher.find()) {
            String dollarPrice = priceMatcher.group(1);
            String euroPrice = priceMatcher.group(2);

            if (dollarPrice != null) {
                System.out.println("Found dollar price: $" + dollarPrice);
            }
            if (euroPrice != null) {
                System.out.println("Found euro price: €" + euroPrice);
            }
        }
    }
}
