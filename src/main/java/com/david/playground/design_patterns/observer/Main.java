package com.david.playground.design_patterns.observer;

public class Main {
    public static void main(String[] args) {
        NewsAgency newsAgency = new NewsAgency();
        NewsChannel newsChannel = new NewsChannel();

        newsAgency.addObserver(newsChannel);
        newsAgency.setNews("news");
        System.out.println(newsChannel.getNews());
    }
}
